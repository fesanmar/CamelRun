package com.felipesantacruz.camelrun.player;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.List;

import com.felipesantacruz.camelrun.goalobserver.GoalReachedObserver;
import com.felipesantacruz.camelrun.goalobserver.GoalReachedSubject;
import com.felipesantacruz.camelrun.holesfield.ColorHole;

public class Camel implements GoalReachedSubject
{
	private int number;
	private int goal;
	private boolean goalReached = false;
	private List<ColorHole> movements = new ArrayList<>();
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
		return getLastHole().getSteps();
	}
	
	private ColorHole getLastHole()
	{
		return movements.get(movements.size() - 1);
	}

	public int getTotalSteps()
	{
		int stepsWalked = movements
				.stream()
				.mapToInt(hole -> hole.getSteps())
				.reduce(0, (totalSteps, lasStep) -> totalSteps + lasStep);
		return stepsWalked >= goal ? goal : stepsWalked;
	}

	public void move(ColorHole hole)
	{
		if (!goalReached)
			takeSteps(hole);
	}

	private void takeSteps(ColorHole hole)
	{
		movements.add(hole);
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
						getName(), setLasStringPosition(), getTotalSteps());
				
	}

	private String setLasStringPosition()
	{
		return getLastHole().getStringPositions();
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
