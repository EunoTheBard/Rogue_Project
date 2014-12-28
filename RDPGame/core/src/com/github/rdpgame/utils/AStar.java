package com.github.rdpgame.utils;
import java.awt.Point;
import java.util.*;

/*
 * Should probably add some bull shit explanation about Nick Raposo creating this.
 */
public class AStar{
	public Path calcedPath = new Path();
	public PriorityQueue<PointWeighted> open;
	public int[][] closed;
	public String[][] collision;
	public int mapsizeX;
	public int mapsizeY;
	public PointWeighted curPoint = new PointWeighted(), 
			destPoint = new PointWeighted(), 
			startPoint = new PointWeighted();
	
	public Point dest;
	
	public AStar()
	{
	}
	
	/*
	 * Takes the map Jon created.
	 */
	public AStar(String[][] map, int mapsizeX, int mapsizeY)
	{
		this.mapsizeX = mapsizeX;
		this.mapsizeY = mapsizeY;
		collision = map;
	}
	
	public Path calculatePath(Point from, Point to)
	{
		if(collision[to.y][to.x] == "wall" || from == to){
			return null;
		}
		
		closed = new int[mapsizeX][mapsizeY];
		dest = to;
		startPoint.x = from.x;
		startPoint.y = from.y;
		startPoint.weight = 5000;
		startPoint.parent = null;
		
		destPoint.x = to.x;
		destPoint.y = to.y;
		destPoint.weight = 0;
		
		curPoint = startPoint;

		open = new PriorityQueue<PointWeighted>(mapsizeX * mapsizeY);	
		
		for(int i = 0; i < mapsizeX; i++)
			for(int j = 0; j < mapsizeY; j++)
				closed[i][j] = 0;
		
		generateNodes(from, startPoint);
		closed[startPoint.x][startPoint.y] = 1;
		
		/*
		 * if you click 1 square away there is no point in doing the calculation
		 */
		if(from.distance(to) <= 1)
		{
			calcedPath.addPoint(to);
			return calcedPath;
		}
		/*
		 * nodes need to be generated before doing checks in case of only having a single
		 * element in the queue.
		 */
		for(;;)
		{
			curPoint = open.poll();
			closed[curPoint.x][curPoint.y] = 1;		
			generateNodes(new Point(curPoint.x, curPoint.y), curPoint);
			
			if((curPoint.x == to.x && curPoint.y == to.y) || (open.isEmpty()))
			{
				for(;;)
				{
					if(curPoint.parent == null)
						break;
					calcedPath.addPoint(new Point(curPoint.x, curPoint.y));
					curPoint = curPoint.parent;
				}
				break;
			}
		}
		return calcedPath;
	}
	
	public void generateNodes(Point p, PointWeighted pw)
	{
		/* 
		 * take point p and check the 4 nodes around it
		 * adding in the parent nodes weight for proper calculations
		 * could possibly add in extra values for diagonal movement
		 */
		if(p.x+1 != mapsizeX && closed[p.x+1][p.y] == 0)
			if(collision[p.y][p.x+1] != "wall")
				open.add(new PointWeighted(p.x+1, p.y, new Point(p.x+1, p.y).distance(dest) + pw.weight, pw));

		if(p.x-1 != -1 && closed[p.x-1][p.y] == 0)
			if(collision[p.y][p.x-1] != "wall")
				open.add(new PointWeighted(p.x-1, p.y, new Point(p.x-1, p.y).distance(dest) + pw.weight, pw));

		if(p.y+1 != mapsizeY && closed[p.x][p.y+1] == 0)
			if(collision[p.y+1][p.x] != "wall")
				open.add(new PointWeighted(p.x, p.y+1, new Point(p.x, p.y+1).distance(dest) + pw.weight, pw));
		
		if(p.y-1 != -1 && closed[p.x][p.y-1] == 0)
			if(collision[p.y-1][p.x] != "wall")
				open.add(new PointWeighted(p.x, p.y-1, new Point(p.x, p.y-1).distance(dest) + pw.weight, pw));
	}
}

