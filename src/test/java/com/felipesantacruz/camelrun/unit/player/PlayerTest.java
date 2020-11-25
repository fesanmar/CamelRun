package com.felipesantacruz.camelrun.unit.player;

import static com.felipesantacruz.camelrun.unit.MokcupsTest.holesAreaPlayer1;
import static com.felipesantacruz.camelrun.unit.MokcupsTest.holesAreaPlayer2;
import static com.felipesantacruz.camelrun.unit.MokcupsTest.oneStepHole;
import static com.felipesantacruz.camelrun.unit.MokcupsTest.twoStepHole;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.felipesantacruz.camelrun.player.Camel;
import com.felipesantacruz.camelrun.player.Player;
import com.felipesantacruz.camelrun.positions.PositionsScore;
import com.felipesantacruz.camelrun.positions.PositionsTable;

class PlayerTest
{
	private Camel camel1 = mock(Camel.class);
	private Camel camel2 = mock(Camel.class);
	
	@ParameterizedTest
	@ValueSource(ints = { 1, 2, 3 })
	void shootMovesPlayersCamel(int steps)
	{
		when(holesAreaPlayer1.getColoredHole()).thenReturn(oneStepHole);
		Player player = new Player(holesAreaPlayer1, camel1);
		
		player.shoot();
		
		verify(camel1).move(oneStepHole);
	}
	
	@Test
	void shootChangesCamelsPositionInPositionTable()
	{
		PositionsScore table = spy(PositionsTable.getInstance());
		table.config(camel1, camel2);
		Player player1 = new Player(holesAreaPlayer1, camel1, table);
		Player player2 = new Player(holesAreaPlayer2, camel2, table);
		when(holesAreaPlayer1.getColoredHole()).thenReturn(oneStepHole);
		when(holesAreaPlayer2.getColoredHole()).thenReturn(twoStepHole);
		
		player1.shoot();
		player2.shoot();
		
		verify(table, times(2)).updatePositions();
	}

}
