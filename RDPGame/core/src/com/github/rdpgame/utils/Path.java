package com.github.rdpgame.utils;

import java.awt.Point;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import com.badlogic.gdx.math.Vector2;

public class Path {
	LinkedList<Point> path;
	
	public Path()
	{
		path = new LinkedList<Point>();
	}
	
	public void addPoint(Point p)
	{
		path.addLast(p);
	}
	
	public Vector2 getNextPoint()
	{
		if(path.isEmpty())
			return null;
		Vector2 point = new Vector2(path.getLast().x, path.getLast().y);
		path.removeLast();
		return point;
	}
}
