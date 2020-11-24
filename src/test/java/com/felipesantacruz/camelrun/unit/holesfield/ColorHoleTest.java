package com.felipesantacruz.camelrun.unit.holesfield;

import static com.felipesantacruz.camelrun.assertions.CamelRunAssertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.felipesantacruz.camelrun.holesfield.ColorHole;

class ColorHoleTest
{

	@ParameterizedTest
	@ValueSource(ints = { 0, 1, 2, 3 })
	void colorHoleReturnRightNumberOfSteps(int numberOfSteps)
	{
		ColorHole redHole = new ColorHole.Builder("red")
				.lettingMove(numberOfSteps, "")
				.build();
		assertThat(redHole.getSteps()).isEqualTo(numberOfSteps);
	}
	
	@Test
	void colorHoleReturnRightStringSteps()
	{
		ColorHole redHole = new ColorHole.Builder("red")
				.lettingMove(3, "tres posiciones")
				.build();
		assertThat(redHole.getStringPositions()).isEqualTo("tres posiciones");
	}

}
