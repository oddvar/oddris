package org.oddvar.oddris;

import java.util.Random;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.AppGameContainer;

public class TestRun extends BasicGame {
	
	GameField gameField;
	Random rand;
	private final static int ONE_FRAME = 16; // 1000/60 ~= 16
	private final static int FRAMES_PER_STEP = 48;
	
	private final static int RES_WIDTH = 600;
	private final static int RES_HEIGHT = 800;
	private final int BLOCKSIZE = 30;
	private final int BLOCK_START_X = 150;
	private final int BLOCK_START_Y = 40;
	
	private Block block;
	private Block nextBlock;
	private int offsetX;
	private int offsetY;
	
	private int deltaCounter;
    private int inputDelta;
	
	private int frameCount;
	
	private boolean updateBlocks;
	
	public TestRun() {
		super("SimpleTest");
	}
	
	@Override
	public void init(GameContainer container) throws SlickException {
		gameField = new GameField();
		rand = new Random(1);
		updateBlocks = true;
		nextBlock = gameField.addBlock();
		frameCount = 0;
		deltaCounter = 500;
		inputDelta = 0;
	}

	@SuppressWarnings("static-access")
	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {

		deltaCounter -= delta;
        inputDelta -= delta;
        
		if(null == block) { //if new block needed
			block = nextBlock;
			nextBlock = gameField.addBlock();
			offsetX = 3;
			offsetY = 0;
			
			if(gameField.checkMoveBlock(block, offsetX, offsetY) == false) {
				//gameOver();
				System.out.println("Game over");
			}

		}
		
		// only allow moves every so often
		if(inputDelta < 0) {
			Input input = container.getInput();

			if(input.isKeyDown(Input.KEY_LEFT)) {
				if(gameField.checkMoveBlock(block, offsetX-1, offsetY)) {				
					gameField.moveBlock(block, offsetX--, offsetY);
					inputDelta = 100;
				}

			}
			else if(input.isKeyDown(Input.KEY_RIGHT)) {
				if(gameField.checkMoveBlock(block, offsetX+1, offsetY)) {				
					gameField.moveBlock(block, offsetX++, offsetY);
					inputDelta = 100;
				}
			}
			else if(input.isKeyDown(Input.KEY_DOWN)) {
				if(gameField.checkMoveBlock(block, offsetX, offsetY+1)) {				
					gameField.moveBlock(block, offsetX, offsetY++);
					inputDelta = 100;
				}
			}

		}
		
		
		// update as per speed (FRAMES_PER_STEP)
		if(frameCount == 0) {
			if(gameField.checkMoveBlock(block, offsetX, offsetY+1)) {				
				gameField.moveBlock(block, offsetX, offsetY++);
			}
			else {
				gameField.glueBlock(block, offsetX, offsetY);
				block = null;
			}
		}
		
		// wait one frame
		try {
			Thread.currentThread().sleep(ONE_FRAME);
			frameCount = (frameCount+1) % FRAMES_PER_STEP;
			// For example, NES Tetris operates at 60 frames per second. At level 0, a piece falls one step every 48 frames, and at level 19, a piece falls one step every 2 frames. Level increments either terminate at a certain point (Game Boy Tetris tops off at level 20)
		}
		catch(InterruptedException e) {}

	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {

		if(updateBlocks) {
			for(int i=0; i<gameField.getWidth(); i++) {			
				for(int j=0; j<gameField.getHeight(); j++) {
					g.setColor(Color.gray);
					g.drawRect(BLOCK_START_X + i*BLOCKSIZE, BLOCK_START_Y + j*BLOCKSIZE, BLOCKSIZE, BLOCKSIZE);
					g.setColor(gameField.getBlock(i, j));
					if(j<2) { g.setColor(Color.yellow); }
					g.fillRect(BLOCK_START_X + 1 + i*BLOCKSIZE, BLOCK_START_Y + 1 + j*BLOCKSIZE, BLOCKSIZE -2, BLOCKSIZE -2);
				}
			}
		}

		if(null != block) {
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					if(block.getBlock(i, j)) {					
						//blocks[i+offsetX][j+offsetY] = b.getColor();
						g.setColor(block.getColor());
						g.fillRect(BLOCK_START_X + 1 + (i+offsetX)*BLOCKSIZE, BLOCK_START_Y + 1 + (j+offsetY)*BLOCKSIZE, BLOCKSIZE -2, BLOCKSIZE -2);

					}
				}
			}
		}
		
		// draw upcoming block:
		if(null != nextBlock) {
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					if(nextBlock.getBlock(i, j)) {					
						g.setColor(nextBlock.getColor());
						g.fillRect(BLOCK_START_X + 1 + i*BLOCKSIZE + 310, BLOCK_START_Y + 1 + j*BLOCKSIZE, BLOCKSIZE -2, BLOCKSIZE -2);

					}
					else {
						g.setColor(Color.gray);
						g.drawRect(BLOCK_START_X + i*BLOCKSIZE + 310, BLOCK_START_Y + j*BLOCKSIZE, BLOCKSIZE, BLOCKSIZE);
						g.setColor(Color.white);
						g.fillRect(BLOCK_START_X + 1 + i*BLOCKSIZE + 310, BLOCK_START_Y + 1 + j*BLOCKSIZE, BLOCKSIZE -2, BLOCKSIZE -2);
					}
				}
			}
		}

	}

	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new TestRun());
			app.setDisplayMode(RES_WIDTH, RES_HEIGHT, false);
			app.start();
		} 
		catch (SlickException e) {
			e.printStackTrace();
		}

	}
}	
