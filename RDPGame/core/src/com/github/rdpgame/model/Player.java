package com.github.rdpgame.model;

import java.awt.Point;

import com.badlogic.gdx.math.Vector2;
import com.github.rdpgame.maps.RandomMap;
import com.github.rdpgame.utils.AStar;
import com.github.rdpgame.utils.Path;

public class Player extends MoveableEntity
{

	public boolean keyDown;
	public Path pPath;
	
	public Player(Vector2 position, 
			float width, 
			float height, 
			float rotation, 
			boolean moving, 
			boolean keyDown, 
			Vector2 destVec,
			Vector2 moveVec,
			float SPEED) 
	{
		super(SPEED, 
				rotation, 
				width, 
				height, 
				moving, 
				destVec, 
				position, 
				moveVec);
	}

	public void update() 
	{
		
		move();
		//position.add(velocity.cpy().scl(SPEED));
		//if(position.x )
		//position.add(velocity.cpy().scl(Gdx.graphics.getDeltaTime() * SPEED));
		
		//Gdx.app.log(RDPGame.LOG, "Player Position: " + position.x + "," + position.y);
		/*if(velocity.x != 0 || velocity.y != 0)
			rotation = velocity.angle() - 90;*/
		
		bounds.x = position.x;
		bounds.y = position.y;
	}
}
