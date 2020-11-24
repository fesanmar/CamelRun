package com.felipesantacruz.camelrun.positions;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.felipesantacruz.camelrun.player.Camel;

public class PositionsTable
{
	private static PositionsTable instance = new PositionsTable();
	private List<Camel> camelsClasification;

	private PositionsTable() { }

	public static PositionsTable getInstance()
	{
		if (instance.camelsClasification == null)
			throw new PositonTableUnConfigException();
		return instance;
	}

	public static void config(Camel... camels)
	{
		instance.camelsClasification = Arrays.asList(camels);
	}

	public static void clearState()
	{
		instance.camelsClasification = null;
	}

	public void updatePositions()
	{
		instance.camelsClasification.sort(this::compare);
		
	}
	
	public int compare(Camel camle1, Camel camel2)
	{
		return camel2.getTotalSteps() - camle1.getTotalSteps();
	}

	public int getPositonFor(Camel camel)
	{
		return camelsClasification.indexOf(camel) + 1;
	}

	public Collection<String> getPositions()
	{
		return createClassification();
	}
	
	private Collection<String> createClassification()
	{
		Clasiffication classification = new SpanishClassification();
		return classification.createClassificationFrom(camelsClasification);
	}

}
