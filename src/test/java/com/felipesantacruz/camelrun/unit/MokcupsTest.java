package com.felipesantacruz.camelrun.unit;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.felipesantacruz.camelrun.holesfield.ColorHole;
import com.felipesantacruz.camelrun.holesfield.HolesArea;

public class MokcupsTest
{
	public static final int TWO_STEPS = 2;
	public static final int ONE_STEP = 1;
	public static HolesArea holesAreaPlayer1 = mock(HolesArea.class);
	public static HolesArea holesAreaPlayer2 = mock(HolesArea.class);
	public static ColorHole oneStepHole = mock(ColorHole.class);
	public static ColorHole twoStepHole = mock(ColorHole.class);
	
	public static void setUpMockups()
	{
		when(oneStepHole.getSteps()).thenReturn(ONE_STEP);
		when(twoStepHole.getSteps()).thenReturn(TWO_STEPS);
	}
}
