package com.felipesantacruz.camelrun.positions;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.felipesantacruz.camelrun.player.Camel;

public class PositionsTable implements PositionsScore
{
	private static PositionsTable instance = new PositionsTable();
	private List<Camel> camelsClasification;
	private Classificator classificator = new SpanishClassification();;

	private PositionsTable() { }

	public static PositionsTable getInstance()
	{
		return instance;
	}

	public static void config(Camel... camels)
	{
		instance.camelsClasification = Arrays.asList(camels);
	}

	public static void clearState()
	{
		instance.camelsClasification = null;
		instance.classificator = new SpanishClassification();
	}

	@Override
	public void updatePositions()
	{
		if (instance.camelsClasification == null)
			throw new PositonTableUnConfigException();
		instance.camelsClasification.sort(this::compare);
		
	}
	
	private int compare(Camel camle1, Camel camel2)
	{
		return camel2.getTotalSteps() - camle1.getTotalSteps();
	}

	@Override
	public int getPositonFor(Camel camel)
	{
		return camelsClasification.indexOf(camel) + 1;
	}

	@Override
	public Collection<String> getPositions()
	{
		return classificator.createClassificationFrom(camelsClasification);
	}
	
	@Override
	public void setClassification(Classificator c)
	{
		classificator = c;
	}

}
