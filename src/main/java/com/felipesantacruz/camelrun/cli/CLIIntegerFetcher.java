package com.felipesantacruz.camelrun.cli;

import java.util.Scanner;

public class CLIIntegerFetcher
{
	private CLIProperty property;
	private int integerValue = -1;
	private Scanner scanner;

	public CLIIntegerFetcher(CLIProperty property, Scanner scanner)
	{
		this.property = property;
		this.scanner = scanner;
	}

	public void askForInteger()
	{
		requestValue();
		while (conditionIsNotMet())
			displayErrorAndrequestValue();
	}

	private void requestValue()
	{
		System.out.println(property.question());
		integerValue = askForIntAndExitIfOthreCharIsEntered();
	}

	private int askForIntAndExitIfOthreCharIsEntered()
	{
		try
		{
			return Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e)
		{
			System.out.println("Has seleccionado [SALIR]. ¡Hasta otra!");
			System.exit(0);
		}
		return -1;
	}

	private boolean conditionIsNotMet()
	{
		return property.conditionToMeet().negate().test(integerValue);
	}

	private void displayErrorAndrequestValue()
	{
		System.out.println(property.erroMessage());
		requestValue();
	}

	public int getValue()
	{
		return integerValue;
	}
}
