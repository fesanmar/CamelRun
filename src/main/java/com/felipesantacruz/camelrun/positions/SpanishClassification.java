package com.felipesantacruz.camelrun.positions;

import static java.lang.String.format;
import static com.felipesantacruz.camelrun.utils.StringUtils.getStringNumber;

import java.util.ArrayList;
import java.util.Collection;

import com.felipesantacruz.camelrun.player.Camel;

public class SpanishClassification implements Classificator
{
	private int firstCamelSteps = 0;
	private int i = 0;
	private StringBuilder builder = new StringBuilder();
	private Collection<String> clasiffication;

	@Override
	public Collection<String> createClassificationFrom(Collection<Camel> positions)
	{
		clasiffication = new ArrayList<>();
		i = 0;
		for (Camel camel : positions)
			addToClassification(camel);
		return clasiffication;
	}

	private void addToClassification(Camel camel)
	{
		buildMessageFor(camel);
		clasiffication.add(builder.toString());
		nextIndex();
	}

	private void buildMessageFor(Camel camel)
	{
		setUpStringBuilder();
		buildHeadMessage(camel);
		buildMessageTailIfMustHave(camel);
	}

	private void setUpStringBuilder()
	{
		builder = new StringBuilder();
	}

	private void buildHeadMessage(Camel camel)
	{
		builder.append(i + 1 + "º " + camel.getName());
	}

	private void buildMessageTailIfMustHave(Camel camel)
	{
		if (isFirstCamel())
			setFirstCamelTotalSteps(camel.getTotalSteps());
		else
			buildTailMessage(camel);
	}

	private boolean isFirstCamel()
	{
		return i == 0;
	}
	
	private void setFirstCamelTotalSteps(int totalStpets)
	{
		firstCamelSteps = totalStpets;
	}
	
	private void buildTailMessage(Camel camel)
	{
		int differenceWithFirstCamel = firstCamelSteps - camel.getTotalSteps();
		if (isSingular(differenceWithFirstCamel))
			builder.append(" a una posición");
		else 
			builder.append(format(" a %s posiciones", getStringNumber(differenceWithFirstCamel)));
	}

	private boolean isSingular(int differenceWithFirstCamel)
	{
		return differenceWithFirstCamel == 1;
	}

	private void nextIndex()
	{
		i++;
	}

	@Override
	public String getTailReportForCamelInCollection(Camel camel, Collection<Camel> positions)
	{
		createClassificationFrom(positions);
		String tail = getTailReportForCamel(camel);
		if (tail.isEmpty())
			return "va en primera posición";
		return tail.concat(" del líder").trim();
	}

	private String getTailReportForCamel(Camel camel)
	{
		String actualCamelReport = getReportFor(camel);
		String tail = getTailFor(camel, actualCamelReport);
		return tail;
	}

	private String getReportFor(Camel camel)
	{
		String actualCamelReport = null; 
		for (String report : clasiffication)
		{
			if (report.contains(camel.getName()))
			{
				actualCamelReport = report;
				break;
			}
			
		}
		return actualCamelReport;
	}
	
	private String getTailFor(Camel camel, String actualCamelReport)
	{
		int headEnds = getIndexWhereCamelNameEnds(camel, actualCamelReport);
		return actualCamelReport.substring(headEnds);
	}

	private int getIndexWhereCamelNameEnds(Camel camel, String actualCamelReport)
	{
		return actualCamelReport.indexOf(camel.getName()) + camel.getName().length();
	}
}
