package com.github.rdpgame.utils;
import java.awt.Point;
import java.util.*;

/*
 * Should probably add some bull shit explanation about Nick Raposo creating this.
 */
public class AStar{
	public Path calcedPath = new Path();
	public PriorityQueue<PointWeighted> open;
	public int[][] closed, checked;
	public String[][] collision;
	public int mapsizeX;
	public int mapsizeY;
	public PointWeighted curPoint = new PointWeighted(), 
			destPoint = new PointWeighted(), 
			startPoint = new PointWeighted();
	
	public Point dest;
	
	public PointWeighted[][] openArray;
	
	public AStar()
	{
	}
	
	/*
	 * Reads in a 2-D map array
	 */
	public AStar(String[][] map, int mapsizeX, int mapsizeY)
	{
		this.mapsizeX = mapsizeX;
		this.mapsizeY = mapsizeY;
		collision = map;
	}
	
	/*
	 * Main Astar method for pathfinding
	 *
	 * Returns the most efficient path
	 */
	public Path calculatePath(Point from, Point to)
	{
		if(collision[to.y][to.x] == "wall" || from == to){
			return null;
		}
		
		
		/*
		 * variable initilization for algorithm looping
		 */
		closed = new int[mapsizeX][mapsizeY];
		checked = new int[mapsizeX][mapsizeY];
		dest = to;
		startPoint.x = from.x;
		startPoint.y = from.y;
		startPoint.weight = 100;
		startPoint.weight = 0;
		startPoint.parent = null;
		
		destPoint.x = to.x;
		destPoint.y = to.y;
		destPoint.weight = 0;
		
		curPoint = startPoint;

		open = new PriorityQueue<PointWeighted>(mapsizeX * mapsizeY);	
		openArray = new PointWeighted[mapsizeX][mapsizeY];
		
		/*
		 * Initialize the closed and checked arrays values to 0
		 */
		for(int i = 0; i < mapsizeX; i++){
			for(int j = 0; j < mapsizeY; j++){
				closed[i][j] = 0;
				checked[i][j] = 0;
			}
		}
		
		openArray[startPoint.x][startPoint.y] = startPoint;
		generateNodes(from);
		checked[startPoint.x][startPoint.y] = 1;
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
			generateNodes(new Point(curPoint.x, curPoint.y));

			if((curPoint.x == to.x && curPoint.y == to.y) || (open.isEmpty()))
			{
				for(;;)
				{
					if(curPoint.parent == null)
						break;
					curPoint = openArray[curPoint.x][curPoint.y];
					calcedPath.addPoint(new Point(curPoint.x, curPoint.y));
					curPoint = curPoint.parent;
				}
				break;
			}
		}
		return calcedPath;
	}
	
	public void generateNodes(Point p)
	{
		int h = 0;
		/* 
		 * take point p and check the 4 nodes around it
		 * adding in the parent nodes weight for proper calculations
		 * could possibly add in extra values for diagonal movement
		 */
		if(p.x+1 != mapsizeX && closed[p.x+1][p.y] == 0)
			if(collision[p.y][p.x+1] != "wall")
			{			
				if(checked[p.x+1][p.y] == 0)
				{
					h = Math.abs(destPoint.x - (p.x+1)) + Math.abs(destPoint.y - p.y);
					openArray[p.x+1][p.y] = new PointWeighted(p.x+1, p.y, openArray[p.x][p.y].weight + h, openArray[p.x][p.y]);
					open.add(openArray[p.x+1][p.y]);
					checked[p.x+1][p.y] = 1;
				}
			}
		
		if(p.x-1 != -1 && closed[p.x-1][p.y] == 0)
			if(collision[p.y][p.x-1] != "wall")
			{
				if(checked[p.x-1][p.y] == 0)
				{
					h = Math.abs(destPoint.x - (p.x-1)) + Math.abs(destPoint.y - p.y);
					openArray[p.x-1][p.y] = new PointWeighted(p.x-1, p.y, openArray[p.x][p.y].weight + h, openArray[p.x][p.y]);
					open.add(openArray[p.x-1][p.y]);
					checked[p.x-1][p.y] = 1;
				}
			}

		if(p.y+1 != mapsizeY && closed[p.x][p.y+1] == 0)
			if(collision[p.y+1][p.x] != "wall")
			{
				if(checked[p.x][p.y+1] == 0)
				{
					h = Math.abs(destPoint.x - p.x) + Math.abs(destPoint.y - (p.y+1));
					openArray[p.x][p.y+1] = new PointWeighted(p.x, p.y+1, openArray[p.x][p.y].weight + h, openArray[p.x][p.y]);
					open.add(openArray[p.x][p.y+1]);
					checked[p.x][p.y+1] = 1;
				}
			}
		
		if(p.y-1 != -1 && closed[p.x][p.y-1] == 0)
			if(collision[p.y-1][p.x] != "wall")
			{
				if(checked[p.x][p.y-1] == 0)
				{
					h = Math.abs(destPoint.x - p.x) + Math.abs(destPoint.y - (p.y-1));
					openArray[p.x][p.y-1] = new PointWeighted(p.x, p.y-1, openArray[p.x][p.y].weight + h, openArray[p.x][p.y]);
					open.add(openArray[p.x][p.y-1]);
					checked[p.x][p.y-1] = 1;
				}
			}
	}
}

