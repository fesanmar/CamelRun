package com.felipesantacruz.camelrun.game;

import com.felipesantacruz.camelrun.player.Player;

public interface Game
{
	void startGame();
	
	Player[] getPlayers();
	
	int getGoal();
}
