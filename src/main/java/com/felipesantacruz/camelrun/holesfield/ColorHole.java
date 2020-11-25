package com.felipesantacruz.camelrun.holesfield;

public class ColorHole
{
	private static final int ONE_STEP = 1;
	private static final int TWO_STEPS = 2;
	private static final int THREE_STEPS = 3;
	public static final int TOTAL_HOLES = 10;
	public static final ColorHole RED_COLOR_HOLE = new ColorHole.Builder("red").lettingMove(THREE_STEPS, "tres posiciones").build();
	public static final ColorHole BLUE_COLOR_HOLE = new ColorHole.Builder("blue").lettingMove(TWO_STEPS, "dos posiciones").build();
	public static final ColorHole YELLOW_COLOR_HOLE = new ColorHole.Builder("yellow").lettingMove(ONE_STEP, "una posición").build();
	
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
