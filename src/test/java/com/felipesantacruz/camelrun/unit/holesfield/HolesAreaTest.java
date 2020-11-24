package com.felipesantacruz.camelrun.unit.holesfield;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.felipesantacruz.camelrun.holesfield.ColorHole;
import com.felipesantacruz.camelrun.holesfield.HolesArea;
import com.felipesantacruz.camelrun.holesfield.WrongHoleFieldConfigException;

class HolesAreaTest
{
	private static final int ONE_STEP = 1;
	private static final int TWO_STEPS = 2;
	private static final int THREE_STEPS = 3;
	private static final ColorHole RED_COLOR_HOLE = new ColorHole.Builder("red").lettingMove(THREE_STEPS, "").build();
	private static final ColorHole BLUE_COLOR_HOLE = new ColorHole.Builder("blue").lettingMove(TWO_STEPS, "").build();
	private static final ColorHole YELLOW_COLOR_HOLE = new ColorHole.Builder("yellow").lettingMove(ONE_STEP, "").build();
	private static final int TOTAL_HOLES = 10;
	
	private static final ColorHole[] holesInGame = { RED_COLOR_HOLE, BLUE_COLOR_HOLE, YELLOW_COLOR_HOLE, ColorHole.NO_STEPS_HOLE };

	@Test
	void testFetchingAColorHole()
	{
		HolesArea field = new HolesArea
				.Builder(TOTAL_HOLES)
				.withNotScoringHoles(3)
				.withHolesColor(YELLOW_COLOR_HOLE, 4)
				.withHolesColor(BLUE_COLOR_HOLE, 2)
				.withHolesColor(RED_COLOR_HOLE, 1)
				.build();
		assertThat(field.getColoredHole()).isIn(Arrays.asList(holesInGame));
	}

	@ParameterizedTest
	@ValueSource(ints = { 9, 5, 0 })
	void testCreatingMoreHolesThanTotalFieldThrowsException(int incorrectTotalHolesNumber)
	{
		assertThatExceptionOfType(WrongHoleFieldConfigException.class).isThrownBy(() ->
		{
			new HolesArea
				.Builder(incorrectTotalHolesNumber)
				.withNotScoringHoles(3)
				.withHolesColor(YELLOW_COLOR_HOLE, 4)
				.withHolesColor(BLUE_COLOR_HOLE, 2)
				.withHolesColor(RED_COLOR_HOLE, 1)
				.build();

		});
	}

}
