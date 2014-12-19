package com.github.rdpgame.maps;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

public class RandomMap extends Map{
	private static int TILE_WIDTH = 32;
	private static int TILE_HEIGHT = 32;
	
	private Texture walls;
	private Texture floors;
	
	private Random gen;
	
	protected boolean[][] cells;
	
	public RandomMap(long seed, int height, int width, Texture defTexture){
		super(height, width, defTexture);

		cells = new boolean[height][width];
		
		defTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		walls = new Texture("data/dungeon_rock_wall.png");
		walls.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		floors = new Texture("data/dungeon_rock_floor.png");
		walls.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		gen = new Random(seed);

		setEdge(cells, false);
		randomPopulate(cells, 0.62f);

		conwayAutomata(cells,4,8,5,100,0.1f);
		conwayAutomata(cells,4,8,5,2,0f);
		
		mapFromCells();
	}
	
	protected void mapFromCells(){
		
		for(int y = 0; y < width; y++){
			for(int x = 0; x < width; x++){
				if(cells[y][x]) tiles[y][x] = new Tile(x, y, "floor", 0, floors);
				else tiles[y][x] = new Tile(x, y, "wall", 0, walls, true, 0);
			}
		}
		
		setBitmask(tiles);
	}
	
	protected void setEdge(boolean[][] cells, boolean b){
		for(int h = 0; h < height; h++){
			for(int w = 0; w < width; w++){
					if(h == 0) cells[h][w] = b;
					if(w == 0) cells[h][w] = b;
					if(h == height - 1) cells[h][w] = b;
					if(w == width - 1) cells[h][w] = b;
			}
		}
	}
	
	/**
	 * 
	 * @param cells array of cells to change
	 * @param die1 cell dies if less than die1 neighbors
	 * @param die2 cell dies if more than die2 neighbors
	 * @param create cell is reborn if more than create neighbors
	 * @param iterations number of times to repeat
	 * @param radical amount of noise to add in percent (0.0-1.0)
	 */
	protected void conwayAutomata(boolean[][] cells, int die1, int die2, int create, int iterations, float radical){
		while(iterations > 0){
			boolean[][] help = new boolean[width][height];
			randomPopulate(help, radical);

			// populate help[][] with cells[][]
			for(int h = 0; h < width; h++){
				for(int w = 0; w < width; w++){
					help[h][w] = cells[h][w];
				}
			}
			// create n
			byte n = 0;
			// for each cell
			for(int h = 1; h < height - 1; h++){
				for(int w = 1; w < width - 1; w++){
					// reset n to 0
					n = 0;
					
					for(int y = -1; y < 2; y++){
						for(int x = -1; x < 2; x++){
							if(!(x == 0 && y == 0))
								if(cells[h + y][w + x]) n++;
						}
					}

					if(n < die1) help[h][w] = false;
					else if(n > create) help[h][w] = true;
					else if(n > die2) help[h][w] = false;
				}
			}
	
			for(int h = 0; h < width; h++){
				for(int w = 0; w < width; w++){
					cells[h][w] = help[h][w];
				}
			}
		iterations--;
		}
	}
	
	protected void randomPopulate(boolean[][] cells, float chance){
		for(int h = 1; h < height - 1; h++){
			for(int w = 1; w < width - 1; w++){
				if(gen.nextFloat() <= chance) cells[h][w] = !cells[h][w];
			}
		}
	}
	
	protected int signum(int i){ // Why doesn't java have a signum(int) ? The world will never know
		return Math.abs(i) / i;
	}

	protected void setBitmask(Tile[][] tiles){
		int bm = 0;
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				if(tiles[y][x].autotile){
					bm = 0;
					
					if(y - 1 >= 0){
						if(tiles[y - 1][x].type == tiles[y][x].type) bm += 4;
					}
					if(x - 1 >= 0){
						if(tiles[y][x - 1].type == tiles[y][x].type) bm += 8;
					}
					if(y + 1 <= height - 1){
						if(tiles[y + 1][x].type == tiles[y][x].type) bm += 1;
					}
					if(x + 1 <= width - 1){
						if(tiles[y][x + 1].type == tiles[y][x].type) bm += 2;
					}
					if(y == 0) bm += 4;
					if(y == height - 1) bm += 1;
					if(x == width - 1) bm += 2;
					if(x == 0) bm += 8;
					
					tiles[y][x].bitmask = bm;
					
				}
			}
		}
	}
	
	public int getTileWidth(){
		return TILE_WIDTH;
	}
	
	public int getTileHeight(){
		return TILE_HEIGHT;
	}
}
