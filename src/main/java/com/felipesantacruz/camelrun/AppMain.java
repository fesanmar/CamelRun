package com.felipesantacruz.camelrun;

import java.util.Scanner;
import java.util.function.Predicate;

import com.felipesantacruz.camelrun.cli.CLIIntegerFetcher;
import com.felipesantacruz.camelrun.game.ClassicGame;
import com.felipesantacruz.camelrun.game.Game;

public class AppMain
{

	private static final String NUMBER_OF_PLAYERS_QUESTION = "Introduce el número de jugadores "
			+ "(al menos deben competir dos jugadores):";
	private static final String WRONG_PLAYERS_NUMBER_MESSAGE = "¡Error! Debes elegir al menos dos jugadores.";
	private static final Predicate<Integer> PLAYERS_PREDICATE = players -> players >= 2;
	private static final String GOAL_QUESTION = "Introduce la longitud del reccorido en posiciones (no puede ser menor a uno):";
	private static final String WRONG_GOAL_MESSAGE = "¡Error! La longitud del recorrido no puede ser menor a una posición.";
	private static final Predicate<Integer> GOAL_PREDICATE = goal -> goal > 0;
	
	static final String WELCOME_MESSAGE = "Bienvenido a CamelRun, la aplicaciónd de"
			+ " simulación de las carreras de camellos.\n"
			+ "Para abandonar la aplicación, introduce cualaqier carácter no numérico.\n";
	private static CLIIntegerFetcher playerFetcher;
	private static CLIIntegerFetcher goalFetcher;
	
	public static void main(String[] args) throws InterruptedException
	{
		askUserToEnterPlayersAndGoal();
		initializeGame();
	}
	
	private static void askUserToEnterPlayersAndGoal()
	{
		System.out.println(WELCOME_MESSAGE);
		Scanner scanner = new Scanner(System.in);
		buildFetchersWith(scanner);
		playerFetcher.askForInteger();
		goalFetcher.askForInteger();
		scanner.close();
	}

	private static void buildFetchersWith(Scanner scanner)
	{
		buildPlayersFetcher(scanner);
		buildGoalFetcher(scanner);
	}
	
	private static void buildPlayersFetcher(Scanner scanner)
	{
		playerFetcher = new CLIIntegerFetcher.Builder()
				.askingFor(NUMBER_OF_PLAYERS_QUESTION)
				.mustMeetCondition(PLAYERS_PREDICATE)
				.onErrorWillDisplayMessage(WRONG_PLAYERS_NUMBER_MESSAGE)
				.useReader(scanner)
				.build();
	}
	
	private static void buildGoalFetcher(Scanner scanner)
	{
		goalFetcher = new CLIIntegerFetcher.Builder()
				.askingFor(GOAL_QUESTION)
				.mustMeetCondition(GOAL_PREDICATE)
				.onErrorWillDisplayMessage(WRONG_GOAL_MESSAGE)
				.useReader(scanner)
				.build();
	}
	
	private static void initializeGame()
	{
		Game game = new ClassicGame(playerFetcher.getValue(), goalFetcher.getValue());
		System.out.println("¡Comienza la carrera!");
		game.startGame();
	}

}
