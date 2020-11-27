package com.felipesantacruz.camelrun;

import static com.felipesantacruz.camelrun.cli.CLIProperty.GOAL;
import static com.felipesantacruz.camelrun.cli.CLIProperty.NUMBER_OF_PLAYERS;

import java.util.Scanner;

import com.felipesantacruz.camelrun.cli.CLIIntegerFetcher;
import com.felipesantacruz.camelrun.cli.CLIProperty;
import com.felipesantacruz.camelrun.game.ClassicGame;
import com.felipesantacruz.camelrun.game.Game;

public class AppMain
{
	
	static final String WELCOME_MESSAGE = "Bienvenido a CamelRun, la aplicación de"
			+ " simulación de carreras de camellos.\n"
			+ "Para abandonar la aplicación, introduce cualaquier carácter no numérico. Sólo se permiten números enteros.\n";
	private static int numberOfPlayers;
	private static int goal;
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) throws InterruptedException
	{
		askUserToEnterPlayersAndGoal();
		initializeGame();
	}
	
	private static void askUserToEnterPlayersAndGoal()
	{
		System.out.println(WELCOME_MESSAGE);
		numberOfPlayers = fetchProperty(NUMBER_OF_PLAYERS);
		goal = fetchProperty(GOAL);
		scanner.close();
	}
	
	private static int fetchProperty(CLIProperty property)
	{
		CLIIntegerFetcher fetcher = new CLIIntegerFetcher(property, scanner);
		fetcher.askForInteger();
		return fetcher.getValue();
	}

	private static void initializeGame()
	{
		Game game = new ClassicGame(numberOfPlayers, goal);
		System.out.println("¡Comienza la carrera!");
		game.startGame();
	}

}
