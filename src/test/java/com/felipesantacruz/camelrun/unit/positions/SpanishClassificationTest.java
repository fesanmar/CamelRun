package com.felipesantacruz.camelrun.unit.positions;

import static com.felipesantacruz.camelrun.unit.MokcupsTest.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.felipesantacruz.camelrun.player.Camel;
import com.felipesantacruz.camelrun.positions.Classificator;
import com.felipesantacruz.camelrun.positions.SpanishClassification;

class SpanishClassificationTest
{

	private Camel camel1 = new Camel(1);
	private Camel camel2 = new Camel(2);
	private Camel camel3 = new Camel(3);
	private Classificator classificator = new SpanishClassification();
	
	@BeforeAll
	public static void setUp()
	{
		setUpMockups();
	}
	
	@Test
	void testCreateClassificationFrom()
	{
		camel2.move(twoStepHole);
		camel1.move(oneStepHole);
		camel3.move(zeroStepHole);
		Collection<Camel> positions = Lists.list(camel2, camel1, camel3);
		assertThat(classificator.createClassificationFrom(positions))
			.containsExactly(
					"1º Camello 2", 
					"2º Camello 1 a una posición",
					"3º Camello 3 a dos posiciones"
					);
	}
	
	@Test
	void createCalssificationFromEmptyCollectionReturnsAEmptyCollection()
	{
		Collection<Camel> emptyCollection = Lists.emptyList();
		assertThat(classificator.createClassificationFrom(emptyCollection))
			.isEmpty();
	}

}
