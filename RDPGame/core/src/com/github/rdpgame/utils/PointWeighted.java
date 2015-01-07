package com.github.rdpgame.utils;

public class PointWeighted implements Comparable<PointWeighted>{
	public int x;
	public int y;
	public int weight;
	public PointWeighted parent;
	public int totalCost;
	
	PointWeighted()
	{
		x = 0;
		y = 0;
		weight = 0;
		parent = null;
		totalCost = 0;
	}
	
	PointWeighted(PointWeighted p)
	{
		x= p.x;
		y = p.y;
		weight = p.weight;
		parent = null;
		totalCost = 0;
	}
	
	PointWeighted(int x, int y, int weight, int totalCost, PointWeighted parent)
	{
		this.x = x;
		this.y = y;
		this.weight = weight;
		this.parent = parent;
		this.totalCost = totalCost;
	}

	@Override
	public int compareTo(PointWeighted o) {
		PointWeighted p = (PointWeighted) o;
		return (int) (weight - p.weight);
	}
}
