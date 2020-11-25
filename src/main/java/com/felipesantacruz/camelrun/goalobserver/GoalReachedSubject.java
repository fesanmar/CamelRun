package com.felipesantacruz.camelrun.goalobserver;

public interface GoalReachedSubject
{
	void setGoalObserver(GoalReachedObserver o);
	
	void notifyObserver();
}
