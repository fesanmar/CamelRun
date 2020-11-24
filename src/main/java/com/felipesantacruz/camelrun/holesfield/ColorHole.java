package com.felipesantacruz.camelrun.holesfield;

public class ColorHole
{
	public static ColorHole NO_STEPS_HOLE = new Builder("")
			.lettingMove(0, "cero posiciones")
			.build();

	private String color;
	private int steps;
	private String stepsString;

	public static class Builder
	{
		private String color;
		private int steps;
		private String stepsString;

		public Builder(String color)
		{
			this.color = color;
		}

		public Builder lettingMove(int steps, String stepsString)
		{
			this.steps = steps;
			this.stepsString = stepsString;
			return this;
		}

		public ColorHole build()
		{
			return new ColorHole(color, steps, stepsString);
		}
	}

	private ColorHole(String color, int stepsToMove, String stepsString)
	{
		this.color = color;
		this.steps = stepsToMove;
		this.stepsString = stepsString;
	}

	public String getColor()
	{
		return color;
	}

	public int getSteps()
	{
		return steps;
	}

	public String getStringPositions()
	{
		return stepsString;
	}
}
