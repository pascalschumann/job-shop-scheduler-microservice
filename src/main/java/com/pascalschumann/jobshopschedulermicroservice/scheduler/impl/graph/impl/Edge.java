package com.pascalschumann.jobshopscheduler.scheduler.impl.graph.impl;

import com.pascalschumann.jobshopscheduler.scheduler.impl.datastructure.Id;
import com.pascalschumann.jobshopscheduler.scheduler.impl.graph.IEdge;
import com.pascalschumann.jobshopscheduler.scheduler.impl.graph.INode;

public class Edge
implements IEdge {

	private final INode TailNode;
	private final INode HeadNode;
	private final Id _id = new Id();

	public Edge(final INode tailNode, final INode toNode) {
		TailNode = tailNode;
		HeadNode = toNode;
	}

	@Override
    public INode GetTailNode() {
		return TailNode;
	}

	@Override
    public INode GetHeadNode() {
		return HeadNode;
	}

	@Override
	public String toString() {
		return TailNode + " --> " + HeadNode;
	}

	@Override
	public boolean equals(final Object obj) {
		final Edge other = (Edge) obj;
		final boolean headAndTailAreEqual = HeadNode.equals(other.HeadNode) && TailNode.equals(other.TailNode);
		return headAndTailAreEqual;
	}

	@Override
	public int hashCode() {
		return HeadNode.hashCode() + TailNode.hashCode();
	}

	@Override
    public Id getId() {
		return _id;
	}
}
