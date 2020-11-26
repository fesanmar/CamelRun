package com.felipesantacruz.camelrun.cli;

import java.util.Scanner;
import java.util.function.Predicate;

public class CLIIntegerFetcher
{
	private String question;
	private String errorMessage;
	private Predicate<Integer> predicate;
	private int integerValue = -1;
	private Scanner scanner;
	
	public static class Builder
	{
		private String question;
		private String errorMessage;
		private Predicate<Integer> predicate;
		private Scanner scanner;
		
		public Builder askingFor(String question)
		{
			this.question = question;
			return this;
		}
		
		public Builder onErrorWillDisplayMessage(String errorMessage)
		{
			this.errorMessage = errorMessage;
			return this;
		}
		
		public Builder mustMeetCondition(Predicate<Integer> predicate)
		{
			this.predicate = predicate;
			return this;
		}
		
		public Builder useReader(Scanner sc)
		{
			scanner = sc;
			return this;
		}
		
		public CLIIntegerFetcher build()
		{
			return new CLIIntegerFetcher(question, errorMessage, predicate, scanner); 
		}
	}
	
	private CLIIntegerFetcher(String question, String errorMessage, Predicate<Integer> predicate, Scanner sc)
	{
		this.question = question;
		this.errorMessage = errorMessage;
		this.predicate = predicate;
		scanner = sc;
	}

	public void askForInteger()
	{
		System.out.println(question);
		integerValue = askForIntAndExitIfOthreCharIsEntered();
		while (predicate.negate().test(integerValue))
		{
			System.out.println(errorMessage);
			System.out.println(question);
			integerValue = askForIntAndExitIfOthreCharIsEntered();
		}
	}
	
	private int askForIntAndExitIfOthreCharIsEntered()
	{
		try
		{
			return Integer.parseInt(scanner.nextLine());			
		} catch (NumberFormatException e) {
			System.out.println("Has seleccionado [SALIR]. ¡Hasta otra!");
			System.exit(0);
		}
		return -1;
	}
	
	public int getValue()
	{
		return integerValue;
	}
}
