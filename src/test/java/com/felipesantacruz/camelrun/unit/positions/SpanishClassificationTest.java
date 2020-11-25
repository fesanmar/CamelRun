package com.felipesantacruz.camelrun.unit.positions;

import static com.felipesantacruz.camelrun.unit.MokcupsTest.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Collection;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.felipesantacruz.camelrun.player.Camel;
import com.felipesantacruz.camelrun.positions.Classificator;
import com.felipesantacruz.camelrun.positions.SpanishClassification;

class SpanishClassificationTest
{
	private static final int GOAL_TOW = 2;
	private Camel camel1 = new Camel(1, GOAL_TOW);
	private Camel camel2 = new Camel(2, GOAL_TOW);
	private Camel camel3 = new Camel(3, GOAL_TOW);
	private Classificator classificator = new SpanishClassification();
	private Collection<Camel> positions;
	
	@BeforeAll
	public static void setUp()
	{
		setUpMockups();
	}
	
	@BeforeEach
	public void setUpForEachTest()
	{
		camel2.move(twoStepHole);
		camel1.move(oneStepHole);
		camel3.move(zeroStepHole);
		positions = Lists.list(camel2, camel1, camel3);		
	}
	
	@Test
	void createCalssificationFromEmptyCollectionReturnsAEmptyCollection()
	{
		Collection<Camel> emptyCollection = Lists.emptyList();
		assertThat(classificator.createClassificationFrom(emptyCollection))
		.isEmpty();
	}
	
	@Test
	void testCreateClassificationFrom()
	{
		assertThat(classificator.createClassificationFrom(positions))
			.containsExactly(
					"1º Camello 2", 
					"2º Camello 1 a una posición",
					"3º Camello 3 a dos posiciones"
					);
	}
	
	@Test
	void getTailReportForFirstPositionCamel()
	{
		assertThat(classificator
				.getTailReportForCamelInCollection(camel2, positions))
		.isEqualTo("va en primera posición");
	}

	@Test
	void getTailReportForFirstSecondCamel()
	{
		assertThat(classificator
				.getTailReportForCamelInCollection(camel1, positions))
		.isEqualTo("a una posición del líder");
	}
	
	@Test
	void getTailReportForFirstThirdCamel()
	{
		assertThat(classificator
				.getTailReportForCamelInCollection(camel3, positions))
		.isEqualTo("a dos posiciones del líder");
	}
	
	@Test
	void getTailReportForACamelOutOfTheCollection()
	{
		Camel camel4 = new Camel(4);
		assertThatNullPointerException()
			.isThrownBy(() -> classificator
					.getTailReportForCamelInCollection(camel4, positions));
	}
}
