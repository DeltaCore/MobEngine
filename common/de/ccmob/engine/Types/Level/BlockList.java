package de.ccmob.engine.Types.Level;

import de.ccmob.engine.Types.Blocks.Block;

/**
 * 
 * @author Marcel Benning
 * 
 */

public class BlockList {

	Block	blocks[][][]	= new Block[0][0][0];

	public BlockList(int width, int height, int depth) {
		blocks = new Block[width][height][depth];
	}

	public BlockList() {

	}

	public Block getBlock(int x, int y, int z) {
		return blocks[x][y][z];
	}

	public void setBlock(int x, int y, int z, Block b) {
		blocks[x][y][z] = b;
	}

	public Block[][][] getBlocks() {
		return blocks;
	}

}
