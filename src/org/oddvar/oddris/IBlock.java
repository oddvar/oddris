package org.oddvar.oddris;

import org.newdawn.slick.Color;

public class IBlock extends AbstractBlock implements Block {

	public IBlock() {
		blocks = new boolean[][] { 
		{ false, false, true, false, },
		{ false, false, true, false, },
		{ false, false, true, false, },
		{ false, false, true, false, } };
	}

	@Override
	public Color getColor() {
		return Config.IBLOCKCOLOUR;
	}
}
