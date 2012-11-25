package org.oddvar.oddris;

import org.newdawn.slick.Color;

public class LBlock extends AbstractBlock implements Block {

	public LBlock() {
		blocks = new boolean[][] { 
				{ false, true, false, false, },
				{ false, true, false, false, },
				{ false, true, true,  false, },
				{ false, false,false, false, } };
	}

	@Override
	public Color getColor() {
		return Config.LBLOCKCOLOUR;
	}
}
