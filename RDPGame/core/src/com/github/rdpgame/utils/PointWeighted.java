package com.github.rdpgame.utils;

public class PointWeighted implements Comparable<PointWeighted>{
	public int x;
	public int y;
	public double weight;
	public PointWeighted parent;
	
	PointWeighted()
	{
		x = 0;
		y = 0;
		weight = 0;
		parent = null;
	}
	
	PointWeighted(PointWeighted p)
	{
		x= p.x;
		y = p.y;
		weight = p.weight;
		parent = null;
	}
	
	PointWeighted(int x, int y, double weight, PointWeighted parent)
	{
		this.x = x;
		this.y = y;
		this.weight = weight;
		this.parent = parent;
	}

	@Override
	public int compareTo(PointWeighted o) {
		PointWeighted p = (PointWeighted) o;
		return (int) (weight - p.weight);
	}
}
