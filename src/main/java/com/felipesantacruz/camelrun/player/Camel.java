package com.felipesantacruz.camelrun.player;

import static java.lang.String.format;

import com.felipesantacruz.camelrun.goalobserver.GoalReachedObserver;
import com.felipesantacruz.camelrun.goalobserver.GoalReachedSubject;
import com.felipesantacruz.camelrun.holesfield.ColorHole;

public class Camel implements GoalReachedSubject
{
	private int number;
	private int goal;
	private int totalSteps = 0;
	private ColorHole lastHole;
	private boolean goalReached = false;
	private GoalReachedObserver observer = () -> {};

	public Camel(int number)
	{
		this(number, 0);
	}
	
	public Camel(int number, final int goal)
	{
		if (number <= 0)
			throw new IllegalArgumentException("[" + number + "] should be > 0.");
		this.number = number;
		this.goal = goal;
		
	}

	public int getNumber()
	{
		return number;
	}

	public int getLastStepsMoved()
	{
		return lastHole.getSteps();
	}

	public int getTotalSteps()
	{
		return totalSteps >= goal ? goal : totalSteps;
	}

	public void move(ColorHole hole)
	{
		if (!goalReached)
			takeSteps(hole);
	}

	private void takeSteps(ColorHole hole)
	{
		lastHole = hole;
		totalSteps += hole.getSteps();
		if (goalWasReached())
			changeGoalStateAndNotifyObserver();
	}

	private boolean goalWasReached()
	{
		return getTotalSteps() >= goal;
	}

	private void changeGoalStateAndNotifyObserver()
	{
		goalReached = true;
		notifyObserver();
	}

	public String getName()
	{
		return "Camello " + number;
	}

	public String getReport()
	{
		return format("%s avanza %s y lleva %d posiciones", 
						getName(), getLastStringPosition(), getTotalSteps());
				
	}

	private String getLastStringPosition()
	{
		return lastHole.getStringPositions();
	}

	@Override
	public void setGoalObserver(GoalReachedObserver o)
	{
		observer = o;
	}

	@Override
	public void notifyObserver()
	{
		observer.update();
	}

	public boolean goalReached()
	{
		return goalReached;
	}

}
