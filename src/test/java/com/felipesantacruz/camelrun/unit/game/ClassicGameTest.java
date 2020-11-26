package com.felipesantacruz.camelrun.unit.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.function.Predicate;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.felipesantacruz.camelrun.game.ClassicGame;
import com.felipesantacruz.camelrun.game.Game;
import com.felipesantacruz.camelrun.player.Camel;
import com.felipesantacruz.camelrun.player.Player;
import com.felipesantacruz.camelrun.positions.PositionsScore;
import com.felipesantacruz.camelrun.positions.PositionsTable;

class ClassicGameTest
{

	private static int GOAL_10 = 10;
	private static Predicate<Player> notNullPlayer = player -> player != null;
	
	@ParameterizedTest
	@ValueSource(ints = { 1, 3, 10 })
	public void gameSetTheProperGoal(int goal)
	{
		Game classicGame = new ClassicGame(2, goal);
		assertThat(classicGame.getGoal()).isEqualTo(goal);
	}
	
	@ParameterizedTest
	@ValueSource(ints = { -1, 0 })
	public void gameGoalMustBeGreaterThanZero(int wrongGoal)
	{
		assertThatIllegalArgumentException()
			.isThrownBy(() -> new ClassicGame(2, wrongGoal));
	}
	
	@ParameterizedTest
	@ValueSource(ints = { -1, 0, 1 })
	public void theNumberOfPlayerMustBeGreaterThanOne(int wrongNumberOfPlayers)
	{
		assertThatIllegalArgumentException()
			.isThrownBy(() -> new ClassicGame(wrongNumberOfPlayers, 1));

	}
	
	@ParameterizedTest
	@ValueSource(ints = { 2, 4, 100 })
	public void gameCreatesThePropperNumberOfCamels(int numPlayers)
	{
		Game classicGame = new ClassicGame(numPlayers, GOAL_10);
		assertThat(classicGame.getPlayers())
			.hasSize(numPlayers)
			.allMatch(notNullPlayer);
	}
	
	@ParameterizedTest
	@ValueSource(ints = { 2, 4, 100 })
	public void gameConfigPositionTable(int numPlayers)
	{
		PositionsScore table = spy(PositionsTable.getInstance());
		table.clearState();
		new ClassicGame(numPlayers, GOAL_10, table);
		verify(table, times(2)).clearState();
		verify(table).config(any(Camel.class));
		assertThat(table.getPositions()).hasSize(numPlayers);
	}

}
