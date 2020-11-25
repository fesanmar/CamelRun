package com.felipesantacruz.camelrun.player;

import com.felipesantacruz.camelrun.holesfield.HolesArea;
import com.felipesantacruz.camelrun.positions.PositionsScore;
import com.felipesantacruz.camelrun.positions.PositionsTable;

public class Player
{

	private HolesArea holesArea;
	private Camel camel;
	private PositionsScore table;

	public Player(HolesArea holesArea, Camel camel)
	{
		this.holesArea = holesArea;
		this.camel = camel;
		table = PositionsTable.getInstance();
	}
	
	// Used for test
	public Player(HolesArea holesArea, Camel camel, PositionsScore table)
	{
		this.holesArea = holesArea;
		this.camel = camel;
		this.table = table;
	}

	public Camel getCamel()
	{
		return camel;
	}

	public void shoot()
	{
		camel.move(holesArea.getColoredHole());
		table.updatePositions();
	}

}
