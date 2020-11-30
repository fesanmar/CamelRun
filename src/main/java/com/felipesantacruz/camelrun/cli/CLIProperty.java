package com.felipesantacruz.camelrun.cli;

import java.util.function.Predicate;

public enum CLIProperty
{
	NUMBER_OF_PLAYERS
	{
		@Override
		public String question()
		{
			return "Introduce el n�mero de jugadores " + "(al menos deben competir dos jugadores):";
		}

		@Override
		public String erroMessage()
		{
			return "�Error! Debes elegir al menos dos jugadores.";
		}

		@Override
		public Predicate<Integer> conditionToMeet()
		{
			return players -> players >= 2;
		}
	},
	GOAL
	{
		@Override
		public String question()
		{
			return "Introduce la longitud del recorrido en posiciones (no puede ser menor a uno):";
		}

		@Override
		public String erroMessage()
		{
			return "�Error! La longitud del recorrido no puede ser menor a una posici�n.";
		}

		@Override
		public Predicate<Integer> conditionToMeet()
		{
			return goal -> goal > 0;
		}
	};

	public abstract String question();

	public abstract String erroMessage();

	public abstract Predicate<Integer> conditionToMeet();
}
