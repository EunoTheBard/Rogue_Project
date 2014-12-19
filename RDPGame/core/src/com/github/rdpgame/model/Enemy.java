package com.github.rdpgame.model;

import com.badlogic.gdx.math.Vector2;

public abstract class Enemy extends MoveableEntity {
	public Enemy(float SPEED, 
			float rotation, 
			float width, 
			float height, 
			boolean moving, 
			Vector2 destVec,
			Vector2 moveVec,
			Vector2 position){
		super(SPEED, rotation, width, height, moving, destVec, moveVec, position);
	}
	
	public void Update()
	{
		
	}
}
