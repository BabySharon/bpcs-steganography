package BPCS;
import java.util.ArrayList;

public class Plane {
	int index;
	int height,width;
	int[][] rgb;
	ArrayList<Block> blocks;
	ArrayList<Integer> complexBlocks = new ArrayList<Integer>();
	
	Plane(int i,int height, int width) {
		index = i;
		this.height = height;
		this.width = width;
		rgb = new int[height][width];
		
	}
	void setBit(int x,int y, int bit) {
		rgb[x][y] = bit;
	}
	ArrayList<Block> getBlocks() {
		return this.blocks;
	}
	void setupBlocks() {
		ArrayList<Block> blocks = new ArrayList<Block>();
		for (int r = 0; r < rgb.length; r+=8) for (int c=0; c< rgb[0].length; c+=8 ) {
			Block block = new Block(r,c,rgb);
			blocks.add(block);
		}
		this.blocks = blocks;
		this.calculateComplexBlocks();
	}
	int maxComplexity() {
		int max = -1;
		for (Block block: blocks) {
			int kvalue = block.getKValue();
			if( kvalue > max) {
				max = kvalue;
			}
		}
		return max;
	}
	void calculateComplexBlocks() {
		int maxComplexity = this.maxComplexity();
		System.out.println("aa"+maxComplexity);
		
		for(int i = 0; i< blocks.size() ; i++) {
			if(maxComplexity != 0 && blocks.get(i).getKValue()/ (float)(maxComplexity) >= 0.3) {
				complexBlocks.add(i);
			}
		}
	}
	int[][] mergeBlocks(){
		int[][] plane = new int[height][width];
		int r = 0;
		int c = 0;
		for (Block block : blocks) {
			for(int i = r; i < r+8 ;i++) for(int j = c; j< c+8;j++) {
				plane[i][j] = block.getBlockBit(i-r, j-c);	
			}
			if(c + 8 >= width) {
				r += 8;
				c = 0;
				continue;
			}
			c+=8;
		}
		return plane;
	}
	ArrayList<Integer> getComplexRegions() {
		return this.complexBlocks;
	}
	
}
