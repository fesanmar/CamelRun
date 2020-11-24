package com.felipesantacruz.camelrun.player;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.List;

import com.felipesantacruz.camelrun.holesfield.ColorHole;

public class Camel
{
	private int number;
	private List<ColorHole> movements = new ArrayList<>();

	public Camel(int number)
	{
		if (number <= 0)
			throw new IllegalArgumentException("[" + number + "] should be > 0.");
		this.number = number;
	}

	public int getNumber()
	{
		return number;
	}

	public int getLastStepsMoved()
	{
		return getLasHole().getSteps();
	}
	
	private ColorHole getLasHole()
	{
		return movements.get(movements.size() - 1);
	}

	public int getTotalSteps()
	{
		return movements
				.stream()
				.mapToInt(hole -> hole.getSteps())
				.reduce(0, (totalSteps, lasStep) -> totalSteps + lasStep);
	}

	public void move(ColorHole hole)
	{
		movements.add(hole);
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
		return getLasHole().getStringPositions();
	}

}
