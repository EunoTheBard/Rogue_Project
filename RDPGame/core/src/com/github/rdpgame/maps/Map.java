package com.github.rdpgame.maps;

import com.badlogic.gdx.graphics.Texture;


public abstract class Map {
	protected int width, height;
	
	protected Tile[][] tiles;
	protected String[][] col;

	private Texture defaultTexture; // Texture to draw under tiles
	
	public Map(int height, int width, Texture defTexture){
		tiles = new Tile[height][width];
		col = new String[height][width];
		
		this.width = width;
		this.height = height;
		this.defaultTexture = defTexture;
	}
	
	/**
	 * 
	 * @return Array of tile objects
	 */
	public Tile[][] getTiles(){
		return tiles;
	}
	/**
	 * @return String[][] array contains collision descriptors like "wall" "floor" "air"
	 */
	public String[][] getCollision(){
		for(int y = 0;y < height; y++){
			for(int x = 0;x < width; x++){
				col[y][x] = tiles[y][x].type;
			}
		}
		return col;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public Texture getDefaultTile(){
		return defaultTexture;
	}
}