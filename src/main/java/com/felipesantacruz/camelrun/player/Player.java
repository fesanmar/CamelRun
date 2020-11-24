package com.felipesantacruz.camelrun.player;

import com.felipesantacruz.camelrun.holesfield.HolesArea;
import com.felipesantacruz.camelrun.positions.PositionsTable;

public class Player
{

	private HolesArea holesArea;
	private Camel camel;
	private PositionsTable table;

	public Player(HolesArea holesArea, Camel camel)
	{
		this.holesArea = holesArea;
		this.camel = camel;
		table = PositionsTable.getInstance();
	}
	
	// Used for test
	public Player(HolesArea holesArea, Camel camel, PositionsTable table)
	{
		super();
		this.holesArea = holesArea;
		this.camel = camel;
		this.table = table;
	}

	public void shoot()
	{
		camel.move(holesArea.getColoredHole());
		table.updatePositions();
	}

}
