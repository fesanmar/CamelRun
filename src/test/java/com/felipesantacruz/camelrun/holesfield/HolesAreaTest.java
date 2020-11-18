package com.felipesantacruz.camelrun.holesfield;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class HolesAreaTest
{

	
	private static final int ZERO_STEPS = 0;
	private static final int ONE_STEP = 1;
	private static final int TWO_STEPS = 2;
	private static final int THREE_STEPS = 3;
	private static final ColorHole RED_COLOR_HOLE = new ColorHole("red", THREE_STEPS);
	private static final ColorHole BLUE_COLOR_HOLE = new ColorHole("blue", TWO_STEPS);
	private static final ColorHole YELLOW_COLOR_HOLE = new ColorHole("yellow", ONE_STEP);
	private static final int TOTAL_HOLES = 10;

	@Test
	void testFetchingAPosition()
	{
		HolesArea field = new HolesArea.Builder(TOTAL_HOLES)
				.withNotScoringHoles(3)
				.withHolesColor(YELLOW_COLOR_HOLE, 4)
				.withHolesColor(BLUE_COLOR_HOLE, 2)
				.withHolesColor(RED_COLOR_HOLE, 1)
				.build();
		assertThat(field.getStepsToMove()).isBetween(ZERO_STEPS, THREE_STEPS);
	}
	
	@ParameterizedTest
	@ValueSource(ints = { 9, 5, 0 })
	void testCreatingMoreHolesThanTotalFieldThrowsException(int incorrectTotalHolesNumber)
	{
		assertThatExceptionOfType(WrongHoleFieldConfigException.class).isThrownBy(() ->
		{
			new HolesArea.Builder(incorrectTotalHolesNumber)
					.withNotScoringHoles(3)
					.withHolesColor(YELLOW_COLOR_HOLE, 4)
					.withHolesColor(BLUE_COLOR_HOLE, 2)
					.withHolesColor(RED_COLOR_HOLE, 1)
					.build();
			
		});
	}

}
