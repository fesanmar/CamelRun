package com.felipesantacruz.camelrun.unit;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.felipesantacruz.camelrun.holesfield.ColorHole;
import com.felipesantacruz.camelrun.holesfield.HolesArea;

public class MokcupsTest
{
	public static final int THREE_STEPS = 3;
	public static final int TWO_STEPS = 2;
	public static final int ONE_STEP = 1;
	public static final int ZERO_STEP = 0;
	public static HolesArea holesAreaPlayer1 = mock(HolesArea.class);
	public static HolesArea holesAreaPlayer2 = mock(HolesArea.class);
	public static HolesArea holesAreaPlayer3 = mock(HolesArea.class);
	public static ColorHole oneStepHole = mock(ColorHole.class);
	public static ColorHole twoStepHole = mock(ColorHole.class);
	public static ColorHole threeStepHole = mock(ColorHole.class);
	public static ColorHole zeroStepHole = mock(ColorHole.class);
	
	public static void setUpMockups()
	{
		when(zeroStepHole.getSteps()).thenReturn(ZERO_STEP);
		when(oneStepHole.getSteps()).thenReturn(ONE_STEP);
		when(twoStepHole.getSteps()).thenReturn(TWO_STEPS);
		when(threeStepHole.getSteps()).thenReturn(THREE_STEPS);
	}
}
