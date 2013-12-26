package de.ccmob.engine.Types.Level;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import de.ccmob.engine.Types.Blocks.Block;

/**
 * 
 * @author Marcel Benning
 * 
 */

public class Level {

	BlockList	blocks	= new BlockList(2, 1, 2);

	public Level() {
		for (int x = 0; x < blocks.getBlocks().length; x++) {
			for (int y = 0; y < blocks.getBlocks()[x].length; y++) {
				for (int z = 0; z < blocks.getBlocks()[x][y].length; z++) {
					blocks.setBlock(x, y, z, new Block("", x, y, z));
				}
			}
		}
	}

	public BlockList getBlocks() {
		return blocks;
	}

	public void setBlocks(BlockList blocks) {
		this.blocks = blocks;
	}

	public void render() {
		glBegin(GL_TRIANGLES);
		for (int x = 0; x < blocks.getBlocks().length; x++) {
			for (int y = 0; y < blocks.getBlocks()[x].length; y++) {
				for (int z = 0; z < blocks.getBlocks()[x][y].length; z++) {
					blocks.getBlock(x, y, z).render();
				}
			}
		}
		glEnd();
	}

}
