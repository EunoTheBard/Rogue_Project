package com.github.rdpgame.screens;

import com.badlogic.gdx.Screen;
import com.github.rdpgame.RDPGame;
import com.github.rdpgame.view.World;
import com.github.rdpgame.view.WorldRenderer;

public class GameScreen implements Screen{
	
	RDPGame game;
	World world;
	WorldRenderer worldrenderer;
	
	public GameScreen(RDPGame game){
		this.game = game;
		world = new World(game);
		worldrenderer = new WorldRenderer(world);
	}

	@Override
	public void render(float delta) {
		world.update();	
		worldrenderer.render();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		dispose();
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	

}
