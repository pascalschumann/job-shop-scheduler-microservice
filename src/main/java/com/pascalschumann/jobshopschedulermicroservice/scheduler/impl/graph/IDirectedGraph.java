package com.pascalschumann.jobshopscheduler.scheduler.impl.graph;

import java.util.Collection;

import com.pascalschumann.jobshopscheduler.scheduler.impl.datastructure.IStackSet;
import com.pascalschumann.jobshopscheduler.scheduler.impl.datastructure.Id;
import com.pascalschumann.jobshopscheduler.scheduler.impl.graph.impl.IGraphNode;

/**
 * TODO: fill this
 *
 * @author Pascal Schumann
 */
public interface IDirectedGraph {

	/**
	 * one fromNode has many toNodes
	 * 
	 * @return: toNodes
	 */
	INodes GetSuccessorNodes(INode node);

	INodes GetSuccessorNodes(Id nodeId);

	INodes GetPredecessorNodes(INode node);

	INodes GetPredecessorNodes(Id nodeId);

	INodes GetPredecessorNodesRecursive(INode startNode);

	void AddEdges(Iterable<IEdge> edges);

	void AddEdges(INode fromNode, INodes nodes);

	void AddEdge(IEdge edge);

	int CountEdges();

	/**
	 * No duplicates should be contained
	 */
	IStackSet<INode> GetAllUniqueNodes();

	/// <summary>
	///
	/// </summary>
	/// <param name="node"></param>
	/// <param name="connectParentsWithChilds"> if true this removes the node,
	/// the parents will point to its childs afterwards</param>
	/// /// <param name="removeEdges">take false if all predecessors/successors
	/// are also removed</param>
	void RemoveNode(INode node, boolean connectParentsWithChilds, boolean removeEdges);// = true); TODO

	void RemoveNode(INode node, boolean connectParentsWithChilds);

	void RemoveNode(Id nodeId, boolean connectParentsWithChilds, boolean removeEdges);// = true); TODO

	void RemoveNode(Id nodeId, boolean connectParentsWithChilds);

	INodes GetLeafNodes();

	INodes GetRootNodes();

	void ReplaceNodeByDirectedGraph(INode node, IDirectedGraph graphToInsert);

	void ReplaceNodeByDirectedGraph(Id nodeId, IDirectedGraph graphToInsert);

	void ReplaceNodeByOtherNode(Id nodeId, INode otherNode);

	IStackSet<IEdge> GetEdges();

	void Clear();

	boolean IsEmpty();

	boolean Contains(INode node);

	boolean Contains(Id nodeId);

	INode GetNode(Id id);

	IStackSet<IGraphNode> GetNodes();

	void RemoveEdge(INode parent, INode child);
}
