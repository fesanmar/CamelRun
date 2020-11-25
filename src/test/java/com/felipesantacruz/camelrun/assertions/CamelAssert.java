package com.felipesantacruz.camelrun.assertions;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import com.felipesantacruz.camelrun.player.Camel;

public class CamelAssert extends AbstractAssert<CamelAssert, Camel>
{

	public CamelAssert(Camel actual)
	{
		super(actual, CamelAssert.class);
	}
	
	public static CamelAssert assertThat(Camel actual)
	{
		return new CamelAssert(actual);
	}
	
	public CamelAssert hasName(String name)
	{
		isNotNull();
		Assertions.assertThat(actual.getName())
			.as("Name")
			.isEqualTo(name);
		return this;
	}
	
	public CamelAssert justMoved(int steps)
	{
		isNotNull();
		Assertions.assertThat(actual.getLastStepsMoved())
			.as("Camel last movement")
			.isEqualTo(steps);
		return this;
	}
	
	public CamelAssert hasMovedATotalOfStepsOf(int steps)
	{
		isNotNull();
		Assertions.assertThat(actual.getTotalSteps())
			.as("Camel total movements")
			.isEqualTo(steps);
		return this;
	}
	
	public CamelAssert hasMovedLessStepsThan(int steps)
	{
		isNotNull();
		Assertions.assertThat(actual.getTotalSteps())
			.as("Camel total movements y lesser than")
			.isLessThan(steps);
		return this;
	}
	
	public CamelAssert actualReportIs(String report)
	{
		isNotNull();
		Assertions.assertThat(actual.getReport())
			.as("Camel report")
			.isEqualTo(report);
		return this;
	}
	
	public CamelAssert hasReachedGoal()
	{
		Assertions.assertThat(actual.goalReached())
			.as("Camel reached goal")
			.isEqualTo(true);
		return this;
	}
	
	public CamelAssert hasNotReachedGoal()
	{
		Assertions.assertThat(actual.goalReached())
			.as("Camel hast NOT reached goal")
			.isEqualTo(false);
		return this;
	}
}
