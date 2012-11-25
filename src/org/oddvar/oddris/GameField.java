package org.oddvar.oddris;

import org.newdawn.slick.Color;

public class GameField {
	private final int GAMEFIELDHEIGHT = 22;
	private final int GAMEFIELDWIDTH = 10;
	private Color[][] blocks;

	public GameField() {
		blocks = new Color[GAMEFIELDWIDTH][GAMEFIELDHEIGHT];
		for(int i=0; i<GAMEFIELDWIDTH; i++) {
			for(int j=0; j<GAMEFIELDHEIGHT; j++) {
				blocks[i][j] = Config.NOBLOCKCOLOUR;;
			}
		}
	}

	public Color getBlock(int width, int height) {
		return blocks[width][height];
	}

	public Color[][] getBlocks() {
		return blocks;
	}
	
	public int getHeight() {
		return GAMEFIELDHEIGHT;
	}

	public int getWidth() {
		return GAMEFIELDWIDTH;
	}

	public void setBlock(int i, int j, Color col) {
		blocks[i][j] = col;
		
	}
	
	public Block addBlock() {
		return new IBlock();
	}
	
	public boolean checkMoveBlock(Block b, int offsetX, int offsetY) {
		boolean [][] newBlock = b.getBlocks();
		
		for(int i=0; i<newBlock.length; i++) {
			for(int j=0; j<newBlock.length; j++) {
				if(newBlock[i][j]) {
					if(i+offsetX < 0 || i+offsetX >= GAMEFIELDWIDTH ||
							j+offsetY < 0 || j+offsetY >= GAMEFIELDHEIGHT) {
						return false;
					}
							
					if(blocks[i+offsetX][j+offsetY] != Config.NOBLOCKCOLOUR) {
						return false;
					}
					//blocks[i+offsetX][j+offsetY] = b.getColor(); 
				}
			}
		}
		return true;
	}
	
	// this sucks. need to think harder how to deal with moving block VS gameField. Separate them, draw block separately until it merges.
	
	public void moveBlock(Block b, int offsetX, int offsetY) {
		boolean [][] newBlock = b.getBlocks();

		for(int i=0; i<newBlock.length; i++) {
			for(int j=0; j<newBlock.length; j++) {
				if(newBlock[i][j]) {
					
					if(j+offsetY-1 >= 0) {
						blocks[i+offsetX][j+offsetY-1] = Config.NOBLOCKCOLOUR;
					}
					//blocks[i+offsetX][j+offsetY] = b.getColor(); 
				}
			}
		}
	}

	public boolean glueBlock(Block b, int offsetX, int offsetY) {
		boolean [][] newBlock = b.getBlocks();
		
		for(int i=0; i<newBlock.length; i++) {
			for(int j=0; j<newBlock.length; j++) {
				if(newBlock[i][j]) {
					if(i+offsetX < 0 || i+offsetX >= GAMEFIELDWIDTH ||
							j+offsetY < 0 || j+offsetY >= GAMEFIELDHEIGHT) {
						return false;
					}
							
					if(blocks[i+offsetX][j+offsetY] != Config.NOBLOCKCOLOUR) {
						return false;
					}
					
					blocks[i+offsetX][j+offsetY] = b.getColor(); 
				}
			}
		}
		return true;
	}


	/*
	 * The field dimension of Tetris is perhaps the least deviated among releases: 
	 * almost always 10 cells wide by 20 high. 
	 * 
	 * Some releases on handheld platforms with small screens have smaller fields; 
	 * for example, the Tetris Jr. keychain game has 8 by 12, and Tetris for Game Boy has 10 by 18.
	 * 
	 * Traditionally, blocks spawn within the four most central columns and the two highest rows. 
	 * The I tetromino occupies columns 4, 5, 6 and 7, the O tetromino occupies columns 5 and 6, 
	 * and the remaining 5 tetrominoes occupy columns 4, 5 and 6 (or in some, especially older, 
	 * versions, 5, 6 and 7). In some more recent games, pieces spawn above the visible playfield.
	 * 
	 * source: http://en.wikipedia.org/wiki/Tetris
	 */
	
	/*
	 * Scoring
	 * The scoring formula for the majority of Tetris products is built on the idea that more 
	 * difficult line clears should be awarded more points. For example, a single line clear in
	 *  Tetris Zone is worth 100 points, clearing four lines at once (known as a Tetris) is worth 800, 
	 *  while a back-to-back Tetris is worth 1,200.[18]
	 */
	
	/* In traditional games, a level-up would occur once every ten lines are cleared. After a level-up, 
	 * the blocks fall slightly faster, and typically more points are given. In some newer games such as 
	 * Tetris Worlds, the number of lines required vary upon each new level. For example, NES Tetris operates
	 * at 60 frames per second. At level 0, a piece falls one step every 48 frames, and at level 19, a piece 
	 * falls one step every 2 frames. Level increments either terminate at a certain point (Game Boy Tetris 
	 * tops off at level 20) or increase forever yet not in speed after a certain point. NES Tetris will level 
	 * up in speed until level 29 (due to limitations of the game's engine, pieces are not capable of dropping 
	 * faster than this), but tool-assisted emulation will show that the level indicator increases indefinitely—
	 * eventually leading to a glitch where the meter displays non-numeric characters
	 * 
	 * 
	 */
	
	/* Single direction rotation is an older restriction that has since been ruled out in nearly every new official release
	 * by the favor of separate buttons for clockwise and one for counterclockwise rotation. In traditional games, 
	 * the unsymmetrical vertical orientation I-, Z-, and S-pieces will fill the same columns for each clockwise and 
	 * counter clockwise rotation. Some games vary this by allowing two possible column orientations: one for counter 
	 * clockwise and one for clockwise rotations. Double rotation, only seen in progressive clones such as Quadra and DTET, 
	 * rotates the piece 180 degrees.
	 * 
	 * One of the features most appreciated by skilled players is wall kick, or the ability of rotating the pieces even if 
	 * these touch the left or right walls. In the NES version, for example, if a Z piece is "vertically" aligned and falling 
	 * touching the left wall, the player cannot rotate the piece, giving the impression that the rotate buttons are locked. 
	 * In this situation, the player has to move the piece one position to the right before rotating it, losing precious time. 
	 * Proper implementations of wall kick first appeared in the arcade version of Tetris by Atari Games.
	 * 
	 * Piece preview allows a look at the next piece to enter the field. This feature has been implemented since the earliest 
	 * games, though in those early games, having the preview turned on made the score increase more slowly.
	 */

	
	/* "Atari Tetris and Famicom Tetris were also CCW only. I used to only rotate CCW on my Gameboy so it wouldn't 
	 * throw me off when I played Atari Tetris at the arcade." /*
	 */
}
