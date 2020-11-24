package com.felipesantacruz.camelrun.player;

import com.felipesantacruz.camelrun.holesfield.HolesArea;

public class Player
{

	private HolesArea holesArea;
	private Camel camel;

	public Player(HolesArea holesArea, Camel camel)
	{
		this.holesArea = holesArea;
		this.camel = camel;
	}

	public void shoot()
	{
		camel.move(holesArea.getColoredHole());
	}

}
