package com.felipesantacruz.camelrun.unit.player;

import static com.felipesantacruz.camelrun.unit.MokcupsTest.*;
import static com.felipesantacruz.camelrun.unit.MokcupsTest.holesAreaPlayer2;
import static com.felipesantacruz.camelrun.unit.MokcupsTest.oneStepHole;
import static com.felipesantacruz.camelrun.unit.MokcupsTest.twoStepHole;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.felipesantacruz.camelrun.player.Camel;
import com.felipesantacruz.camelrun.player.Player;
import com.felipesantacruz.camelrun.positions.PositionsScore;
import com.felipesantacruz.camelrun.positions.PositionsTable;
import com.felipesantacruz.camelrun.positions.UpdatePositionCallback;

class PlayerTest
{
	private Camel camel1 = mock(Camel.class);
	private Camel camel2 = mock(Camel.class);
	private PositionsScore table = spy(PositionsTable.getInstance());
	private Player player1 = new Player(holesAreaPlayer1, camel1, table);
	private Player player2 = new Player(holesAreaPlayer2, camel2, table);
	private UpdatePositionCallback callbak = mock(UpdatePositionCallback.class);
	
	@BeforeAll
	public static void setUp()
	{
		setUpMockups();
	}
	
	@BeforeEach
	public void setUpBeforeEveryTest()
	{
		PositionsScore table = spy(PositionsTable.getInstance());
		table.clearState();
		player1.setCallback(callbak);
		player2.setCallback(callbak);
		when(holesAreaPlayer1.getColoredHole()).thenReturn(oneStepHole);
		when(holesAreaPlayer1.getColoredHole()).thenReturn(oneStepHole);
		when(holesAreaPlayer2.getColoredHole()).thenReturn(twoStepHole);
	}
	
	@ParameterizedTest
	@ValueSource(ints = { 1, 2, 3 })
	void shootMovesPlayersCamel(int steps)
	{
		table.config(camel1);
		player1.shoot();
		verify(camel1).move(oneStepHole);
	}
	
	@Test
	void shootChangesCamelsPositionInPositionTable()
	{
		table.config(camel1, camel2);
		
		player1.shoot();
		player2.shoot();
		
		verify(table, times(2)).updatePositions(callbak);
	}

}
