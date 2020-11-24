package com.felipesantacruz.camelrun.positions;

import static com.felipesantacruz.camelrun.unit.MokcupsTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.felipesantacruz.camelrun.player.Camel;
import com.felipesantacruz.camelrun.player.Player;

class PositionsTableTest
{

	private static final int SECOND_POSITION = 2;
	private static final int FIRST_POSITON = 1;
	private Camel camel1 = new Camel(1);
	private Camel camel2 = new Camel(2);
	private Camel camel3 = new Camel(3);

	@BeforeAll
	public static void setUp()
	{
		setUpMockups();
	}
	
	@AfterEach
	public void tearDown()
	{
		PositionsTable.clearState();
	}
	
	@Test
	void callingGetInstanceBeforeConfigWillThrowAnException()
	{
		assertThatExceptionOfType(PositonTableUnConfigException.class)
			.isThrownBy(PositionsTable::getInstance);
	}

	@Test
	void callingGetInstanceAfterConfigWillReturnTheInstance()
	{
		PositionsTable.config(camel1, camel2);
		assertThat(PositionsTable.getInstance()).isInstanceOf(PositionsTable.class);
	}

	@Test
	void positionsShouldBeRefresedAfterOnePlayerShoot()
	{
		PositionsTable.config(camel1);
		PositionsTable table = PositionsTable.getInstance();
		Player player1 = new Player(holesAreaPlayer1, camel1, table);
		
		player1.shoot();
		
		assertThat(table.getPositonFor(camel1)).isEqualTo(1);
	}
	
	@Test
	void positionsShouldUpdatedAfterTwoPlayerShoot()
	{
		PositionsTable.config(camel1, camel2);
		PositionsTable table = PositionsTable.getInstance();
		Player player1 = new Player(holesAreaPlayer1, camel1, table);
		Player player2 = new Player(holesAreaPlayer2, camel2, table);
		when(holesAreaPlayer1.getColoredHole()).thenReturn(oneStepHole);
		when(holesAreaPlayer2.getColoredHole()).thenReturn(twoStepHole);
		
		player1.shoot();
		assertThat(table.getPositonFor(camel1)).isEqualTo(FIRST_POSITON);
		assertThat(table.getPositonFor(camel2)).isEqualTo(SECOND_POSITION);
		player2.shoot();
		assertThat(table.getPositonFor(camel2)).isEqualTo(FIRST_POSITON);
		assertThat(table.getPositonFor(camel1)).isEqualTo(SECOND_POSITION);
	}
	
	@Test 
	public void getPositionsReturnsTheRightNumberOfCamels()
	{
		PositionsTable.config(camel1, camel2);
		PositionsTable table = PositionsTable.getInstance();
		assertThat(table.getPositions()).hasSize(2);
	}
	
	@Test
	public void positionsMessageAreAsSpected()
	{
		PositionsTable.config(camel1, camel2, camel3);
		PositionsTable table = PositionsTable.getInstance();
		Player player1 = new Player(holesAreaPlayer1, camel1, table);
		Player player2 = new Player(holesAreaPlayer2, camel2, table);
		Player player3 = new Player(holesAreaPlayer3, camel3, table);
		when(holesAreaPlayer1.getColoredHole()).thenReturn(oneStepHole);
		when(holesAreaPlayer2.getColoredHole()).thenReturn(twoStepHole);
		when(holesAreaPlayer3.getColoredHole()).thenReturn(zeroStepHole);
		player1.shoot(); // Player 1 -> 1 step
		player2.shoot(); // Player 2 -> 2 steps
		player3.shoot(); // Player 3 -> 0 step
		assertThat(table.getPositions())
		.containsExactly(
				"1º Camello 2", 
				"2º Camello 1 a una posición",
				"3º Camello 3 a dos posiciones"
				);
		
	}

}
