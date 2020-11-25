package com.felipesantacruz.camelrun.unit.positions;

import static com.felipesantacruz.camelrun.unit.MokcupsTest.oneStepHole;
import static com.felipesantacruz.camelrun.unit.MokcupsTest.setUpMockups;
import static com.felipesantacruz.camelrun.unit.MokcupsTest.twoStepHole;
import static com.felipesantacruz.camelrun.unit.MokcupsTest.zeroStepHole;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.util.Lists.list;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.felipesantacruz.camelrun.player.Camel;
import com.felipesantacruz.camelrun.positions.Classificator;
import com.felipesantacruz.camelrun.positions.PositionsScore;
import com.felipesantacruz.camelrun.positions.PositionsTable;
import com.felipesantacruz.camelrun.positions.PositonTableUnConfigException;

class PositionsTableTest
{

	private static final int SECOND_POSITION = 2;
	private static final int FIRST_POSITON = 1;
	private static final int GOAL_2 = 2;
	private Camel camel1 = new Camel(1, GOAL_2);
	private Camel camel2 = new Camel(2, GOAL_2);
	private Camel camel3 = new Camel(3, GOAL_2);
	private PositionsScore table = PositionsTable.getInstance();

	@BeforeAll
	public static void setUp()
	{
		setUpMockups();
	}
	
	@AfterEach
	public void tearDown()
	{
		table.clearState();
	}
	
	@Test
	void callingUpdatePositionsBeforeConfigWillThrowAnException()
	{
		PositionsScore table = PositionsTable.getInstance();
		assertThatExceptionOfType(PositonTableUnConfigException.class)
			.isThrownBy(table::updatePositions);
	}

	@Test
	void getInstanceReturnsAInstanceOfPositonsTable()
	{
		assertThat(PositionsTable.getInstance()).isInstanceOf(PositionsTable.class);
	}
	
	@Test
	void callingUpdatePositionsAfterConfigDoesNotThrowException()
	{
		table.config(camel1, camel2);
		PositionsTable.getInstance().updatePositions();
	}
	
	@Test
	void finishTheGame()
	{
		assertThat(table.isGameFinish()).isFalse();
		table.setGameFinish();
		assertThat(table.isGameFinish()).isTrue();
	}

	@Test
	void positionsShouldBeRefresedAfterOnePlayerShoot()
	{
		table.config(camel1);
		
		camel1.move(oneStepHole);
		table.updatePositions();
		
		assertThat(table.getPositonFor(camel1)).isEqualTo(1);
	}
	
	@Test
	void positionsShouldUpdatedAfterTwoPlayerShoot()
	{
		table.config(camel1, camel2);
		
		camel1.move(oneStepHole);
		table.updatePositions();
		
		assertThat(table.getPositonFor(camel1)).isEqualTo(FIRST_POSITON);
		assertThat(table.getPositonFor(camel2)).isEqualTo(SECOND_POSITION);
		
		camel2.move(twoStepHole);
		table.updatePositions();
		
		assertThat(table.getPositonFor(camel2)).isEqualTo(FIRST_POSITON);
		assertThat(table.getPositonFor(camel1)).isEqualTo(SECOND_POSITION);
	}
	
	@Test 
	public void getPositionsReturnsTheRightNumberOfCamels()
	{
		table.config(camel1, camel2);
		assertThat(table.getPositions()).hasSize(2);
	}
	
	@Test
	public void positionsMessageAreAsSpected()
	{
		table.config(camel1, camel2, camel3);
		Classificator classificator = mock(Classificator.class);
		table.setClassification(classificator);
		when(classificator
				.createClassificationFrom(eq(list(camel2, camel1, camel3))))
		.thenReturn(
				list("1º Camello 2", 
						"2º Camello 1 a una posición",
						"3º Camello 3 a dos posiciones"));
		
		camel2.move(twoStepHole);
		camel1.move(oneStepHole);
		camel3.move(zeroStepHole);
		
		table.updatePositions();
		
		assertThat(table.getPositions())
		.containsExactly(
				"1º Camello 2", 
				"2º Camello 1 a una posición",
				"3º Camello 3 a dos posiciones"
				);
		
	}

}
