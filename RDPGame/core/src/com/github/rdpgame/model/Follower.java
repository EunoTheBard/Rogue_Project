package com.github.rdpgame.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Follower extends MoveableEntity {

	float ROTATION_SPEED = 500;
	
	public Follower(float SPEED, 
			float rotation, 
			float width, 
			float height, 
			boolean moving, 
			Vector2 destVec, 
			Vector2 moveVec,
			Vector2 position) {
		super(SPEED, rotation, width, height, moving, destVec, moveVec, position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Player player) {
		position.lerp(player.getPosition(), Gdx.graphics.getDeltaTime());
		
		rotation = (rotation + Gdx.graphics.getDeltaTime() * ROTATION_SPEED) % 360;
				
		super.update(player);
	}

}
