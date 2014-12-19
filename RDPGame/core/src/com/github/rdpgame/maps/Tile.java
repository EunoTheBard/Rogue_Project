package com.github.rdpgame.maps;

import com.badlogic.gdx.graphics.Texture;

public class Tile {
	public static int TILE_WIDTH = 32;
	public static int TILE_HEIGHT = 32;
	
	int x, y;
	
	public int tileIndex;
	public Texture tileref;
	public String type;
	
	public boolean draw;
	public boolean autotile;
	public int bitmask;
	/**
	 * @param x x position in grid
	 * @param y y position in grid
	 * @param type collision type to check against ("wall", "air", "floor", "water", etc.)
	 * @param tileref object reference to tileset for internal use
	 */
	public Tile(int x, int y, String type, int tileIndex, Texture tileref){
		this.x = x;
		this.y = y;
		this.type = type;
		this.tileIndex = tileIndex;
		this.tileref = tileref;
		
		autotile = false;
		bitmask = 0;
	}
	public Tile(int x, int y, String type, int tileIndex, Texture tileref, Boolean autotile, int bitmask){
		this.x = x;
		this.y = y;
		this.type = type;
		this.tileIndex = tileIndex;
		this.tileref = tileref;
		
		this.autotile = autotile;
		this.bitmask = bitmask;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getTileTextureX(){
		int x = tileIndex;
		if(autotile){
			x *= 16;
			x += bitmask;
		}
		x = x % (tileref.getWidth() / TILE_WIDTH);
		return x * TILE_WIDTH;
	}
	
	public int getTileTextureY(){
		float y = tileIndex;
		if(autotile){
			x *= 16;
			x += bitmask;
		}
		y = y / (tileref.getWidth() / TILE_WIDTH);
		return (int)Math.floor(y) * TILE_HEIGHT;
	}
}
