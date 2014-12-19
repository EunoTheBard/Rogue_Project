package com.github.rdpgame.view;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.github.rdpgame.RDPGame;
import com.github.rdpgame.maps.Map;
import com.github.rdpgame.maps.RandomMap;
import com.github.rdpgame.model.Player;

public class InputHandler implements InputProcessor{

	World world;
	Player player;
	RandomMap map;
	
	public InputHandler(World world)
	{
		this.world = world;
	}

	@Override
	public boolean keyDown(int keycode) {
		player = world.getPlayer();
		map = world.getMap();
		switch(keycode){
			case Keys.W:
				if(!player.getMoving()){
					//player.getVelocity().y = 1;
					player.moveTo(player.getPosition().cpy().add(player.getVelocity()), map);
					player.keyDown = true;
				}
				//player.getPosition().y += 1;
				break;
			case Keys.S:
				if(!player.getMoving()){
					//player.getVelocity().y = -1;
					player.moveTo(player.getPosition().cpy().add(player.getVelocity()), map);
					player.keyDown = true;
				}
				//player.getPosition().y -= 1;
				break;
			case Keys.D:
				if(!player.getMoving()){
					//player.getVelocity().x = 1;
					player.moveTo(player.getPosition().cpy().add(player.getVelocity()), map);
					player.keyDown = true;
				}
				//player.getPosition().x += 1;
				break;
			case Keys.A:
				if(!player.getMoving()){
					//player.getVelocity().x = -1;
					player.moveTo(player.getPosition().cpy().add(player.getVelocity()), map);
					player.keyDown = true;
				}
				//player.getPosition().x -= 1;
				break;
			default:
				break;
		}
		return true;
	}

	@Override 
	public boolean keyUp(int keycode) {
		player = world.getPlayer();
		switch(keycode){
			case Keys.W:
				if(player.getVelocity().y >= 1)
					player.keyDown = false;
					//player.setMoving(false);
					//player.getVelocity().y = 0;
				break;
			case Keys.S:
				if(player.getVelocity().y <= -1)
					player.keyDown = false;
					//player.setMoving(false);
					//player.getVelocity().y = 0;
				break;
			case Keys.D:
				if(player.getVelocity().x >= 1)
					player.keyDown = false;
					//player.setMoving(false);
					//player.getVelocity().x = 0;
				break;
			case Keys.A:
				if(player.getVelocity().x <= -1)
					player.keyDown = false;
					//player.setMoving(false);
					//player.getVelocity().x = 0;
				break;
			default:
				break;
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		map = world.getMap();
		screenX -= Gdx.graphics.getWidth() / 2;
		screenY -= Gdx.graphics.getHeight() / 2;
		screenY *= -1;
		
		screenX += world.getCam().x;
		screenY += world.getCam().y; 
		// screenX and screenY are now the pixel in world that was clicked on
		// as opposed to the pixel on the screen
		
		if(button == Buttons.RIGHT) world.setCam(screenX, screenY); 
		// if RMB center screen on click
		
		player = world.getPlayer();

		Gdx.app.log(RDPGame.LOG, "pointer: " + screenX + ":" + screenY);
		
		if(screenX < 0 ||
				screenY < 0) Gdx.app.log("touchUp()","Clicked outside of level");
		
		else{
	screenX /= world.getZoom();
	screenY /= world.getZoom();
			
			switch(button){
			case Buttons.LEFT:
				if(!player.getMoving()){
					player.keyDown = true;
					/*if(player.getPosition().x > screenX){
						player.getVelocity().x = -1;
					}
					if(player.getPosition().x < screenX){
						player.getVelocity().x = 1;
					}
					if(player.getPosition().y < screenY){
						player.getVelocity().y = 1;
					}
					if(player.getPosition().y > screenY){
						player.getVelocity().y = -1;
					}*/
					
					player.moveTo(new Vector2(screenX, screenY), map);
				}
				Gdx.app.log(RDPGame.LOG, "pointer: " + screenX + ":" + screenY);
			default:
				break;
			}
		}
		return true;
	}
	
	public Point getTilePoint(int x, int y){
		return new Point(x / world.getZoom(), y / world.getZoom());
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
