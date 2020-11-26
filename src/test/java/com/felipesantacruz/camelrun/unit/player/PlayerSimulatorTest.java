package com.felipesantacruz.camelrun.unit.player;

import static com.felipesantacruz.camelrun.assertions.CamelRunAssertions.assertThat;
import static com.felipesantacruz.camelrun.unit.MokcupsTest.oneStepHole;
import static com.felipesantacruz.camelrun.unit.MokcupsTest.setUpMockups;
import static com.felipesantacruz.camelrun.unit.MokcupsTest.threeStepHole;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.felipesantacruz.camelrun.holesfield.HolesArea;
import com.felipesantacruz.camelrun.player.Camel;
import com.felipesantacruz.camelrun.player.Player;
import com.felipesantacruz.camelrun.player.PlayerSimulator;
import com.felipesantacruz.camelrun.player.RandomTimerShooter;
import com.felipesantacruz.camelrun.positions.PositionsScore;
import com.felipesantacruz.camelrun.positions.PositionsTable;
import com.felipesantacruz.camelrun.positions.UpdatePositionCallback;

class PlayerSimulatorTest
{

	public static final int GOAL_10 = 10;
	private UpdatePositionCallback mockCallback = mock(UpdatePositionCallback.class);
	private static HolesArea oneStepHolesArea = mock(HolesArea.class);
	private static HolesArea threeStepHolesArea = mock(HolesArea.class);
	private PositionsScore table = mock(PositionsTable.class);
	private Camel camel1 = spy(new Camel(1, GOAL_10));
	private Camel camel2 = spy(new Camel(2, GOAL_10));
	private Player slowerPlayer = spy(new Player(oneStepHolesArea, camel1, table));
	private Player fasterPlayer = spy(new Player(threeStepHolesArea, camel2, table));
	private PlayerSimulator slowerSimulator = new RandomTimerShooter(slowerPlayer, table);
	private PlayerSimulator fasterSimulator = new RandomTimerShooter(fasterPlayer, table);

	@BeforeAll
	static void setUpOnce()
	{
		setUpMockups();
		when(oneStepHolesArea.getColoredHole()).thenReturn(oneStepHole);
		when(threeStepHolesArea.getColoredHole()).thenReturn(threeStepHole);
	}

	@BeforeEach
	void setUpWithEveryTest()
	{
		table.clearState();
		slowerPlayer.setCallback(mockCallback);
		fasterPlayer.setCallback(mockCallback);
		((RandomTimerShooter) slowerSimulator).setSleepTo(false);
		((RandomTimerShooter) fasterSimulator).setSleepTo(false);
		when(table.isGameFinish()).thenReturn(false);
		doAnswer(invocation -> when(table.isGameFinish()).thenReturn(true))
			.when(table).setGameFinish();
	}

	@Test
	void playerShootsUntilGoalIsReached()
	{
		table.config(camel1);
		slowerSimulator.run();
		verify(slowerPlayer, times(10)).shoot();
		assertThat(camel1).hasMovedATotalOfStepsOf(GOAL_10);
	}

	@Test
	void gameFinishWhenCamelReachTheGoal()
	{
		table.config(camel1, camel2);
		slowerSimulator.run();
		verify(table).setGameFinish();
		assertThat(table.isGameFinish()).isTrue();
	}
	
	@Test
	void allPlayersStopWhenOneCamelReachTheGoal() throws InterruptedException
	{
		table.config(camel1, camel2);
		Thread t1 = new Thread(slowerSimulator);
		Thread t2 = new Thread(fasterSimulator);
		t2.start();
		Thread.sleep(1); // Makes sure fasterSimulator wins
		t1.start();
		t1.join();
		t2.join();
		assertThat(table.isGameFinish()).isTrue();
		assertThat(fasterPlayer.getCamel())
			.hasReachedGoal()
			.hasMovedATotalOfStepsOf(GOAL_10);
		assertThat(slowerPlayer.getCamel())
			.hasNotReachedGoal()
			.hasMovedLessStepsThan(GOAL_10);
	}

}
