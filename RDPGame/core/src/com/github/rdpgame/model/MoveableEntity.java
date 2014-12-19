package com.github.rdpgame.model;

import java.awt.Point;

import com.badlogic.gdx.math.Vector2;
import com.github.rdpgame.maps.RandomMap;
import com.github.rdpgame.utils.AStar;
import com.github.rdpgame.utils.Path;

public abstract class MoveableEntity extends Entity {
	
	protected Vector2 velocity;
	protected float SPEED;
	protected float rotation; //if i decide to have the character rotate
	public boolean moving;
	protected Vector2 destVec;
	protected Vector2 moveVec;
	private boolean newSquare = false;
	public Path pPath;
	
	public MoveableEntity(float SPEED, 
			float rotation, 
			float width, 
			float height, 
			boolean moving, 
			Vector2 destVec, 
			Vector2 position,
			Vector2 moveVec)
	{
		super(position, width, height);
		this.SPEED = SPEED;
		this.rotation = rotation;
		velocity = new Vector2(0, 0);
		moveVec = new Vector2(0, 0);
	}

	/**
	 * @return the velocity
	 */
	public Vector2 getVelocity() {
		return velocity;
	}
	
	public boolean getMoving(){
		return moving;
	}
	
	public void setMoving(boolean moving){
		this.moving = moving;
	}

	/**
	 * @param velocity the velocity to set
	 */
	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}
	
	public float getRotation(){
		return rotation;
	}
	
	public void setRotation(float rotation){
		this.rotation = rotation;
	}
	
	public void update(Player player){
		bounds.x = position.x;
		bounds.y = position.y;
	}
	
	public void moveTo(Vector2 destVec, RandomMap map)
	{
		this.destVec = destVec;
		if(!(map.getCollision()[(int)destVec.y][(int)destVec.x] == "wall")){
			if(!(destVec.y == position.y && destVec.x == position.x)){
				AStar a = new AStar(map.getCollision(), 100);
				Point from = new Point((int)position.x, (int)position.y);
				Point to = new Point((int)destVec.x, (int)destVec.y);
				pPath = a.calculatePath(from, to);
				nextSquare();
				moving = true;
			}
		}
	}
	
	private void nextSquare()
	{
		moveVec = pPath.getNextPoint();
		if(moveVec == null)
		{
			moving = false;
			return;
		}
		if(moveVec.x > position.x)
			velocity.x = 1;
		if(moveVec.x < position.x)
			velocity.x = -1;
		if(moveVec.y > position.y)
			velocity.y = 1;
		if(moveVec.y < position.y)
			velocity.y = -1;
	}
	
	public void move()
	{
		if(moving)//seperate x and y to fix shit need to draw squares from center and have odd number of squares on the screen
		{	
			if(velocity.y > 0)
			{
				position.y += SPEED;
				
				if(position.y > moveVec.y)
				{
					position.y = moveVec.y;
					velocity.y = 0;
					newSquare = true;
				}
			}
			else
			{
				if(velocity.y != 0)
				{
					position.y -= SPEED;
					
					if(position.y < moveVec.y)
					{
						position.y = moveVec.y;
						velocity.y = 0;
						newSquare = true;
					}
				}
			}
			
			if(velocity.x > 0)
			{
				position.x += SPEED;
				
				if(position.x > moveVec.x)
				{
					position.x = moveVec.x;
					velocity.x = 0;
					newSquare = true;
				}
			}
			else
			{
				if(velocity.x != 0)
				{
					position.x -= SPEED;
					
					if(position.x < moveVec.x)
					{
						position.x = moveVec.x;
						velocity.x = 0;
						newSquare = true;
					}
				}
			}
			
			if(newSquare)
			{
				nextSquare();
				newSquare = false;
			}
		}
	}

}
