package org.oddvar.oddris;

import org.newdawn.slick.Color;

public class ZBlock extends AbstractBlock implements Block {

	public ZBlock() {
		blocks = new boolean[][] { 
		{ false, false, true,  false, },
		{ false, true,  true,  false, },
		{ false, true,  false, false, },
		{ false, false, false, false, } };
	}

	@Override
	public Color getColor() {
		return Config.ZBLOCKCOLOUR;
	}
}
