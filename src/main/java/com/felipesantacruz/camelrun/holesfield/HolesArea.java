package com.felipesantacruz.camelrun.holesfield;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class HolesArea
{
	public static class Builder
	{
		private List<ColorHole> holesList;
		private int totalHoles;

		public Builder(int totalHoles)
		{
			this.totalHoles = totalHoles;
			holesList = new ArrayList<ColorHole>(totalHoles);
		}

		public Builder withNotScoringHoles(int howMany)
		{
			addColorHoles(howMany, new ColorHole("", 0));
			return this;
		}

		private void addColorHoles(int n, ColorHole hole)
		{
			for (int i = 0; i < n; i++)
				holesList.add(hole);
		}

		public Builder withHolesColor(ColorHole colorHole, int howMany)
		{
			addColorHoles(howMany, colorHole);
			return this;
		}

		public HolesArea build()
		{
			throwExceptionIfTotalHolesAreDifferentToListSize();
			Collections.shuffle(holesList);
			return new HolesArea(holesList.toArray(new ColorHole[] {}));
		}

		private void throwExceptionIfTotalHolesAreDifferentToListSize()
		{
			if (holesList.size() != totalHoles)
				throw new WrongHoleFieldConfigException(format(
						"Total holes and added holes should be equals [%d] != [%d]", totalHoles, holesList.size()));
		}

	}

	private ColorHole[] holes;
	private Random random = new Random();

	public HolesArea(ColorHole[] holes)
	{
		this.holes = holes;
	}

	public int getStepsToMove()
	{
		return holes[getRandomHole()].getSteps();
	}

	private int getRandomHole()
	{
		return random.nextInt(holes.length);
	}

}
