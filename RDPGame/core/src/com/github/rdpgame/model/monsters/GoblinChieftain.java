package com.github.rdpgame.model.monsters;

import com.badlogic.gdx.math.Vector2;
import com.github.rdpgame.model.Enemy;

public class GoblinChieftain extends Enemy
{

	public GoblinChieftain(float SPEED, 
			float rotation, 
			float width,
			float height, 
			boolean moving, 
			Vector2 destVec, 
			Vector2 moveVec,
			Vector2 position) {
		super(SPEED, 
				rotation, 
				width, 
				height, 
				moving, 
				destVec, 
				moveVec, 
				position);
	}

	public void Update()
	{
		
	}
}
