package com.felipesantacruz.camelrun.positions;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.felipesantacruz.camelrun.player.Camel;

public class PositionsTable implements PositionsScore
{
	private static PositionsTable instance = new PositionsTable();
	private List<Camel> camelsClasification;
	private Classificator classificator = new SpanishClassification();
	private boolean gameIsFinish = false;

	private PositionsTable() { }

	public static PositionsTable getInstance()
	{
		return instance;
	}

	public void config(Camel... camels)
	{
		camelsClasification = Arrays.asList(camels);
	}

	public void clearState()
	{
		camelsClasification = null;
		classificator = new SpanishClassification();
		gameIsFinish = false;
	}

	@Override
	public synchronized void updatePositions()
	{
		if (camelsClasification == null)
			throw new PositonTableUnConfigException();
		camelsClasification.sort(this::compare);
	}
	
	private int compare(Camel camle1, Camel camel2)
	{
		return camel2.getTotalSteps() - camle1.getTotalSteps();
	}

	@Override
	public synchronized int getPositonFor(Camel camel)
	{
		return camelsClasification.indexOf(camel) + 1;
	}
	
	@Override
	public String getTailReportFor(Camel camel)
	{
		return classificator.getTailReportForCamelInCollection(camel, camelsClasification);
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

	@Override
	public boolean isGameFinish()
	{
		return gameIsFinish;
	}

	@Override
	public void setGameFinish()
	{
		gameIsFinish = true;
	}

}
