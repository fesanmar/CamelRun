package com.felipesantacruz.camelrun.positions;

import java.util.Collection;

import com.felipesantacruz.camelrun.player.Camel;

public interface Clasiffication
{
	Collection<String> createClassificationFrom(Collection<Camel> positions);
}
