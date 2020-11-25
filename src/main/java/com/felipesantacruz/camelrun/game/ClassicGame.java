package com.felipesantacruz.camelrun.game;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.felipesantacruz.camelrun.holesfield.HolesArea;
import com.felipesantacruz.camelrun.player.Camel;
import com.felipesantacruz.camelrun.player.Player;
import com.felipesantacruz.camelrun.player.PlayerSimulator;
import com.felipesantacruz.camelrun.player.RandomTimerShooter;
import com.felipesantacruz.camelrun.positions.PositionsScore;
import com.felipesantacruz.camelrun.positions.PositionsTable;

public class ClassicGame implements Game
{
	public static ThreadGroup CAMELS_RUNNING_GROUP = new ThreadGroup("Camels running");
	private int goal;
	private Player[] players;
	private PositionsScore table;
	private boolean sleeping = true;

	public ClassicGame(int numberPlayers, int goal)
	{
		this(numberPlayers, goal,PositionsTable.getInstance());
	}
	
	// Used for tests
	public ClassicGame(int numberPlayers, int goal, PositionsScore table)
	{
		checkIfArgumentsAreRight(numberPlayers, goal);
		this.table = table;
		this.goal = goal;
		createPlayers(numberPlayers);
		configPositionsScore();
	}

	private void checkIfArgumentsAreRight(int numberPlayers, int goal)
	{
		throwExceptionIfGoalIsLesserThanOne(goal);
		throwExceptionIfThereAreNotAtLeastTwoPlayers(numberPlayers);
	}
	
	private void throwExceptionIfGoalIsLesserThanOne(int goal)
	{
		if (goal <= 0)
			throw new IllegalArgumentException("Goal must be > 0");
	}
	
	private void throwExceptionIfThereAreNotAtLeastTwoPlayers(int numberPlayers)
	{
		if (numberPlayers < 2)
			throw new IllegalArgumentException("Players must be >= 2");
	}

	private void createPlayers(int numberPlayers)
	{
		players = new Player[numberPlayers];
		for (int i = 0; i < numberPlayers; i++)
			createPlayerNumber(i);
	}

	private void createPlayerNumber(int i)
	{
		HolesArea holesArea = HolesArea.createDefaultArea();
		Camel camel = new Camel(i + 1, goal);
		players[i] = new Player(holesArea, camel);
	}

	private void configPositionsScore()
	{
		this.table.clearState();
		this.table.config(getCamels());
	}
	
	private Camel[] getCamels()
	{
		return Arrays.stream(players)
				.map(player -> player.getCamel())
				.collect(Collectors.toList())
				.toArray(new Camel[players.length]);
	}
	
	public synchronized void setSleepingTo(boolean b)
	{
		sleeping = b;
	}
	
	@Override
	public Player[] getPlayers()
	{
		return players;
	}

	@Override
	public int getGoal()
	{
		return goal;
	}

	@Override
	public void startGame()
	{
		for (Player player : players)
			startSimulationFor(player);
	}

	private void startSimulationFor(Player player)
	{
		PlayerSimulator simulator = new RandomTimerShooter(player);
		((RandomTimerShooter) simulator).setSleepTo(sleeping);
		new Thread(CAMELS_RUNNING_GROUP, simulator).start();
	}

}
