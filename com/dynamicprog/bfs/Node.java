package com.dynamicprog.bfs;

import java.util.ArrayList;
import java.util.Iterator;

public class Node {

	public Boolean isVisited = false;

	private ArrayList<Node> childNodes = new ArrayList<Node>();

	public String name = null;



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsVisited() {
		return isVisited;
	}

	public void setIsVisited(Boolean isVisited) {
		this.isVisited = isVisited;
	}

	public ArrayList<Node> getChildNodes() {

		Iterator it = childNodes.iterator();
		ArrayList<Node> nMster = new ArrayList<Node>();
		while(it.hasNext())
		{
			//System.out.println("Inside child nodes...");
			Node n = (Node)it.next();
			if(!n.isVisited)
				nMster.add(n);
		}
		return nMster;
	}

	public ArrayList<Node> getChildNodesNotTouched() {
		return childNodes;

	}

	public void setChildNodes(ArrayList<Node> childNodes) {
		this.childNodes = childNodes;
	}

	public Node(Boolean isVisited, ArrayList<Node> childNodes, String name) {
		super();
		this.isVisited = isVisited;
		this.name = name;
		//this.childNodes = childNodes;
	}





}
