package com.github.rdpgame.view;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.github.rdpgame.maps.RandomMap;
import com.github.rdpgame.maps.Tile;
import com.github.rdpgame.model.Follower;
import com.github.rdpgame.model.Player;


public class WorldRenderer {

	World world;
	SpriteBatch batch;
	Player player;
	OrthographicCamera cam;
	Texture playerTexture, followerTexture, terrainTexture;
	float width, height;
	ShapeRenderer sr;
	Follower follow;
	Point worldCamera;
	
	Texture currentTileset;
	
	RandomMap map;
	Tile[][] tileref;
	
	public WorldRenderer(World world){
		this.world = world;

		player = world.getPlayer();
		follow = world.getFollower();
		map = world.getMap();
		tileref = map.getTiles();
		worldCamera = world.getCam();
		
		
		width = Gdx.graphics.getWidth() / world.getZoom();
		height = Gdx.graphics.getHeight() / world.getZoom();
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, width, height);
		cam.update();
		
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);
		
		
		playerTexture = new Texture("data/player.png");
		playerTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		followerTexture = new Texture("data/follower.png");
		followerTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		terrainTexture = new Texture("data/terrain.png");
		terrainTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		
		sr = new ShapeRenderer();
		
		//Gdx.app.log(RDPGame.LOG, "Cam initial: " + cam.position.toString());
	}
	
	public void render(){
		tileref = map.getTiles();
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//cam.position.set(player.getPosition().x, player.getPosition().y, 0);
		cam.position.set((float)worldCamera.x / world.getZoom(), (float)worldCamera.y / world.getZoom(), 0);
		cam.update();
		
		//Gdx.app.log("PLoc", "Player Location: " + player.getPosition().toString());
		
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		
		
		for (int h = 0; h < map.getHeight(); h++){
			for (int w = 0; w < map.getWidth(); w++){
				if(tileref[h][w].type != "empty"){
					if(tileref[h][w].type != "floor"){
						batch.draw(map.getDefaultTile(),
								w, 	//x
								h, 	//y
								0,					//originX
								0,					//originY
								1,					//width
								1,					//height
								1,					//scaleX
								1,					//scaleY
								0,					//rotation
								0,
								0,
								Tile.TILE_WIDTH,
								Tile.TILE_HEIGHT,
								false,
								false);
					}
					batch.draw(tileref[h][w].tileref,
							w, 	//x
							h, 	//y
							0,					//originX
							0,					//originY
							1,					//width
							1,					//height
							1,					//scaleX
							1,					//scaleY
							0,					//rotation
							tileref[h][w].getTileTextureX(),
							tileref[h][w].getTileTextureY(),
							Tile.TILE_WIDTH,
							Tile.TILE_HEIGHT,
							false,
							false);
				}
			}
		}
		
		batch.draw(playerTexture, 
				player.getPosition().x, 
				player.getPosition().y, 
				player.getHeight() / 2, 
				player.getWidth() / 2, 
				player.getWidth(), 
				player.getHeight(), 
				1, //scalex
				1, //scaley
				player.getRotation(), 
				0, 
				0, 
				playerTexture.getWidth(), 
				playerTexture.getHeight(), 
				false, 
				false);
		
		batch.draw(followerTexture, 
				follow.getPosition().x, 
				follow.getPosition().y, 
				follow.getHeight() / 2, 
				follow.getWidth() / 2, 
				follow.getWidth(), 
				follow.getHeight(), 
				1, 
				1, 
				follow.getRotation(),
				0,
				0,
				followerTexture.getWidth(),
				followerTexture.getHeight(),
				false,
				false);
		//batch.draw(playerTexture, player.getPosition().x, player.getPosition().y);
		batch.end();
		
		sr.setProjectionMatrix(cam.combined);
		sr.begin(ShapeType.Line);
		sr.setColor(Color.CYAN);
		sr.rect(player.getBounds().x, player.getBounds().y, player.getBounds().width, player.getBounds().height);
		sr.setColor(Color.RED);
		sr.rect(follow.getBounds().x, follow.getBounds().y, follow.getBounds().width, follow.getBounds().height);
		sr.end();
		
	}
	
	public void dispose(){
		batch.dispose();
		playerTexture.dispose();
		followerTexture.dispose();
		terrainTexture.dispose();
	}
}
