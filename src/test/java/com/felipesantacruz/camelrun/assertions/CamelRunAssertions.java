package com.felipesantacruz.camelrun.assertions;

import org.assertj.core.api.Assertions;

import com.felipesantacruz.camelrun.player.Camel;

public class CamelRunAssertions extends Assertions
{
	public static CamelAssert assertThat(Camel actual)
	{
		return new CamelAssert(actual);
	}
}
