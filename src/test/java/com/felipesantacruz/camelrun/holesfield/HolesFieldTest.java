package com.felipesantacruz.camelrun.holesfield;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HolesFieldTest
{

	private static final ColorHole RED_COLOR_HOLE = new ColorHole("red", 3);
	private static final ColorHole BLUE_COLOR_HOLE = new ColorHole("blue", 2);
	private static final ColorHole YELLOW_COLOR_HOLE = new ColorHole("yellow", 1);

	@Test
	void testFetchingAPosition()
	{
		HolesField field = new HolesField.Builder(10)
				.withNotScoringHoles(3)
				.withHolesColor(YELLOW_COLOR_HOLE, 4)
				.withHolesColor(BLUE_COLOR_HOLE, 2)
				.withHolesColor(RED_COLOR_HOLE, 1)
				.build();
		assertThat(field.getStepsToMove()).isBetween(0, 3);
	}
	
	@Test
	void testCreatingMoreHolesThanTotalFieldThrowsException()
	{
		assertThatExceptionOfType(WrongHoleFieldConfigException.class).isThrownBy(() ->
		{
			new HolesField.Builder(5)
					.withNotScoringHoles(3)
					.withHolesColor(YELLOW_COLOR_HOLE, 4)
					.withHolesColor(BLUE_COLOR_HOLE, 2)
					.withHolesColor(RED_COLOR_HOLE, 1)
					.build();
			
		});
	}

}
