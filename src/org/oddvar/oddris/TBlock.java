package org.oddvar.oddris;

import org.newdawn.slick.Color;

public class TBlock extends AbstractBlock implements Block {

	public TBlock() {
		blocks = new boolean[][] { 
		{ false, false, true, false, },
		{ false, true,  true, false, },
		{ false, false, true, false, },
		{ false, false, false,false, } };
	}

	@Override
	public Color getColor() {
		return Config.TBLOCKCOLOUR;
	}
}
