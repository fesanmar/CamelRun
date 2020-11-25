package com.felipesantacruz.camelrun.positions;

import java.util.Collection;

import com.felipesantacruz.camelrun.player.Camel;

public interface PositionsScore
{
	void config(Camel ...camels);
	
	void clearState();
	
	void updatePositions();
	
	int getPositonFor(Camel camel);
	
	Collection<String> getPositions();
	
	void setClassification(Classificator c);

	boolean isGameFinish();

	void setGameFinish();
}
