package com.felipesantacruz.camelrun.unit.player;

import static com.felipesantacruz.camelrun.assertions.CamelRunAssertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.felipesantacruz.camelrun.holesfield.ColorHole;
import com.felipesantacruz.camelrun.player.Camel;

class CamelTest
{

	private static ColorHole threeStepsHole = mock(ColorHole.class);
	private static ColorHole twoStepsHole = mock(ColorHole.class);
	private static ColorHole oneStepHole = mock(ColorHole.class);
	private static ColorHole zeroStepsHole = mock(ColorHole.class);

	private Camel camel = new Camel(1);
	
	@BeforeAll
	public static void setUp()
	{
		when(threeStepsHole.getSteps()).thenReturn(3);
		when(twoStepsHole.getSteps()).thenReturn(2);
		when(oneStepHole.getSteps()).thenReturn(1);
		when(zeroStepsHole.getSteps()).thenReturn(0);
		
		when(threeStepsHole.getStringPositions()).thenReturn("tres posiciones");
		when(twoStepsHole.getStringPositions()).thenReturn("dos posiiciones");
		when(oneStepHole.getStringPositions()).thenReturn("una posición");
		when(zeroStepsHole.getStringPositions()).thenReturn("cero posiciones");
	}
	
	@ParameterizedTest
	@ValueSource(ints = { 1, 2, 3 })
	void camelReturnCorrectName(int number)
	{
		Camel camel = new Camel(number);
		assertThat(camel).hasName("Camello " + number);
	}

	@ParameterizedTest
	@ValueSource(ints = { -1, 0 })
	void camelNumberMusBeGreaterThanZero(int lowerThanZeroNumber)
	{
		assertThatIllegalArgumentException()
			.isThrownBy(() -> new Camel(lowerThanZeroNumber));
	}

	@ParameterizedTest
	@MethodSource("colorHoleProvider")
	void camelMovesRightNumberOfSteps(ColorHole colorHole)
	{
		camel.move(colorHole);
		assertThat(camel).justMoved(colorHole.getSteps());
	}
	
	public static Stream<ColorHole> colorHoleProvider()
	{
		return Stream.of(threeStepsHole, twoStepsHole, oneStepHole, zeroStepsHole);
	}

	@ParameterizedTest
	@MethodSource("twoHolesAndTotalStepsProvider")
	void camelReturnRightNumberOfTotalSteps(ColorHole firstHole, ColorHole secondHole, int totalSteps)
	{
		camel.move(firstHole);
		camel.move(secondHole);
		assertThat(camel).hasMovedATotalOfStepsOf(totalSteps);
	}
	
	public static Stream<org.junit.jupiter.params.provider.Arguments> twoHolesAndTotalStepsProvider()
	{
		return Stream.of(
				arguments(threeStepsHole, twoStepsHole, threeStepsHole.getSteps() + twoStepsHole.getSteps()),
				arguments(zeroStepsHole, oneStepHole, zeroStepsHole.getSteps() + oneStepHole.getSteps()),
				arguments(zeroStepsHole, zeroStepsHole, zeroStepsHole.getSteps())
				);
				
	}
	
	@Test
	void camelReturnRightStateReportWithOneMove()
	{
		camel.move(oneStepHole);
		assertThat(camel)
			.actualReportIs("Camello 1 avanza una posición y lleva 1 posiciones");
	}
	
	@Test
	void camelReturnRightStateReportWithTwoMoves()
	{
		camel.move(oneStepHole);
		camel.move(threeStepsHole);
		assertThat(camel)
			.actualReportIs("Camello 1 avanza tres posiciones y lleva 4 posiciones");
	}
	
	@Test
	void camelReturnRightStateReportWithTwoZeroStepsMoves()
	{
		camel.move(zeroStepsHole);
		camel.move(zeroStepsHole);
		assertThat(camel)
			.actualReportIs("Camello 1 avanza cero posiciones y lleva 0 posiciones");
	}

}
