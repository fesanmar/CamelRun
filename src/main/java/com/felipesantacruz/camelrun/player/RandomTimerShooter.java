package com.felipesantacruz.camelrun.player;

import java.util.Random;

import com.felipesantacruz.camelrun.positions.PositionsScore;
import com.felipesantacruz.camelrun.positions.PositionsTable;

public class RandomTimerShooter implements PlayerSimulator
{
	private Player player;
	private Camel camel;
	private PositionsScore table;
	private Random random = new Random();
	private boolean goalNotReached = true;
	private int upperBound = 3;
	private boolean sleep = true;

	public RandomTimerShooter(Player player)
	{
		this(player, PositionsTable.getInstance());
	}
	
	public RandomTimerShooter(Player player, PositionsScore table)
	{
		this.player = player;
		camel = player.getCamel();
		camel.setGoalObserver(this);
		this.table = table;
	}
	
	public void setMaxSecondsToSleep(int include)
	{
		upperBound = include;
	}
	
	public void setSleepTo(boolean b)
	{
		sleep = b;
	}
	
	@Override
	public void run()
	{
		while (gameIsOn())
			reloadGunAndShoot();
	}

	private boolean gameIsOn()
	{
		return goalNotReached && !table.isGameFinish();
	}

	private void reloadGunAndShoot()
	{
		try
		{
			Thread.sleep(getMillis());
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		player.shoot();
	}

	private int getMillis()
	{
		if (sleep)
			return (random.nextInt(upperBound) + 1) * 1000;
		else
			return 0;
	}

	@Override
	public void update()
	{
		goalNotReached = false;
		table.setGameFinish();
	}

}
