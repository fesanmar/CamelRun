package com.felipesantacruz.camelrun.unit.player;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.felipesantacruz.camelrun.holesfield.ColorHole;
import com.felipesantacruz.camelrun.holesfield.HolesArea;
import com.felipesantacruz.camelrun.player.Camel;
import com.felipesantacruz.camelrun.player.Player;

class PlayerTest
{

	@ParameterizedTest
	@ValueSource(ints = { 1, 2, 3 })
	void shootMovesPlayersCamel(int steps)
	{
		HolesArea holesArea = mock(HolesArea.class);
		ColorHole colorHole = mock(ColorHole.class);
		when(holesArea.getColoredHole()).thenReturn(colorHole);
		Camel camel = mock(Camel.class);
		Player player = new Player(holesArea, camel);
		
		player.shoot();
		
		verify(camel).move(colorHole);
	}

}
