package com.felipesantacruz.camelrun.positions;

public class PositonTableUnConfigException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	public PositonTableUnConfigException()
	{
		super("PositionTable hasn't bein configured yed.");
	}

}
