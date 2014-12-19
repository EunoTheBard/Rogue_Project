package com.github.rdpgame.view;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.github.rdpgame.RDPGame;
import com.github.rdpgame.maps.RandomMap;
import com.github.rdpgame.model.Follower;
import com.github.rdpgame.model.Player;

public class World {
	
	RDPGame game;
	Player player;
	Follower follower;
	RandomMap map;
	
	Point cam; // Camera coordinates in pixels
	int zoom;
	
	public World (RDPGame game){
		this.game = game;
		zoom = 32;
		cam = new Point(0,0);
		player = new Player(new Vector2(3, 3), 1, 1, 0, false, false, new Vector2(0, 0), new Vector2(0, 0), 0.1f);
		follower = new Follower(5f, 0, 1, 1, false, new Vector2(10, 10), new Vector2(10, 10), new Vector2(10,10));
		map = new RandomMap(878, 50, 50, new Texture("data/dungeon_rock_floor.png"));
		
		Gdx.input.setInputProcessor(new InputHandler(this));
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public Follower getFollower(){
		return follower;
	}
	
	public RandomMap getMap(){
		return map;
	}
	
	public Point getCam(){
		return cam;
	}
	
	public void setCam(int x, int y){
		cam.x = x;
		cam.y = y;
	}
	
	public int getZoom(){
		return zoom;
	}
	
	public void update(){
		player.update();
		follower.update(player);
		
		if(player.getBounds().overlaps(follower.getBounds())){
			//Gdx.app.log(RDPGame.LOG, "GET FUCKED!");
		}
	}
	
	public void dispose(){
		
	}
}
