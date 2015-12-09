package com.dynamicprog.bfs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSMain {

	public static void main(String[] s)
	{
		System.out.println("Start BFS...");
		Node rootNode = buildGraph();
		performBFS(rootNode);
	}



	public static void performBFS(Node rootNode)
	{
		List bfsList = new ArrayList();
		Queue q= new LinkedList();
		q.add(rootNode);
		rootNode.isVisited = true;
		System.out.print(rootNode.getName()+",");//Visited and printed
		while(!q.isEmpty())
		{
			Node nd = (Node) q.remove();

			Iterator it = nd.getChildNodes().iterator();
			while(it.hasNext())
			{
				Node nChild = (Node)it.next();
				nChild.isVisited = true;
				q.add(nChild);
				System.out.print(nChild.getName()+",");//Visited and printed

			}

		}

	}



	private static Node buildGraph()
	{
		Node d = new Node(false, null,"d");
		Node e = new Node(false, null,"e");
		Node c = new Node(false, null,"c");
		Node f = new Node(false, null,"f");
		Node g = new Node(false, null,"g");
		Node h = new Node(false, null,"h");
		Node s = new Node(false, null,"s");
		Node a = new Node(false, null,"a");
		Node b = new Node(false, null,"b");

		ArrayList lsE = e.getChildNodesNotTouched();
		lsE.add(c);
		lsE.add(h);



		ArrayList lsD = d.getChildNodesNotTouched();
		lsD.add(c);

		ArrayList lsC = c.getChildNodesNotTouched();
		lsC.add(d);
		lsC.add(e);
		lsC.add(f);
		lsC.add(s);

		ArrayList lsF = f.getChildNodesNotTouched();
		lsF.add(c);
		lsF.add(g);
		lsF.add(s);

		ArrayList lsG = g.getChildNodesNotTouched();
		lsG.add(f);
		lsG.add(s);
		lsG.add(h);

		ArrayList lsH = h.getChildNodesNotTouched();
		lsH.add(e);
		lsH.add(g);

		ArrayList lsS = s.getChildNodesNotTouched();
		lsS.add(c);
		lsS.add(g);
		lsS.add(a);

		ArrayList lsA = a.getChildNodesNotTouched();
		lsA.add(s);


		return a;
	}

}
