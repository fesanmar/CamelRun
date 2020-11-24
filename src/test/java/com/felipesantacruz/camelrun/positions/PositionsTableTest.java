package com.felipesantacruz.camelrun.positions;

import static com.felipesantacruz.camelrun.unit.MokcupsTest.holesAreaPlayer1;
import static com.felipesantacruz.camelrun.unit.MokcupsTest.holesAreaPlayer2;
import static com.felipesantacruz.camelrun.unit.MokcupsTest.oneStepHole;
import static com.felipesantacruz.camelrun.unit.MokcupsTest.setUpMockups;
import static com.felipesantacruz.camelrun.unit.MokcupsTest.twoStepHole;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.felipesantacruz.camelrun.player.Camel;
import com.felipesantacruz.camelrun.player.Player;

class PositionsTableTest
{

	private static final int SECOND_POSITION = 2;
	private static final int FIRST_POSITON = 1;

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
		Camel camel1 = mock(Camel.class);
		Camel camel2 = mock(Camel.class);
		PositionsTable.config(camel1, camel2);
		assertThat(PositionsTable.getInstance()).isInstanceOf(PositionsTable.class);
	}

	@Test
	void positionsShouldBeRefresedAfterOnePlayerShoot()
	{
		Camel camel1 = mock(Camel.class);
		PositionsTable.config(camel1);
		PositionsTable table = PositionsTable.getInstance();
		Player player1 = new Player(holesAreaPlayer1, camel1, table);
		
		player1.shoot();
		
		assertThat(table.getPositonFor(camel1)).isEqualTo(1);
	}
	
	@Test
	void positionsShouldUpdatedAfterTwoPlayerShoot()
	{
		Camel camel1 = new Camel(1);
		Camel camel2 = new Camel(2);
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
	
	

}
