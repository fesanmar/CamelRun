package com.felipesantacruz.camelrun.utils;

public class StringUtils
{
	public static String getStringNumber(final int number)
	{
		switch (number)
		{
		case 0:
			return "cero";
		case 1:
			return "uno";
		case 2:
			return "dos";
		case 3:
			return "tres";
		case 4:
			return "cuatro";
		case 5:
			return "cinco";
		case 6:
			return "seis";
		case 7:
			return "siete";
		case 8:
			return "ocho";
		case 9:
			return "neuve";
		case 10:
			return "diez";
		default:
			return String.valueOf(number);
		}
	}
}
