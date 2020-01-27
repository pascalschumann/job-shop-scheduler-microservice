package com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.graph.impl;

import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.datastructure.IStackSet;
import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.datastructure.Id;
import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.datastructure.impl.StackSet;
import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.graph.INode;

public class GraphNode implements IGraphNode {

    private final INode _node;

    private final IStackSet<IGraphNode> _successors = new StackSet<IGraphNode>();
    private final IStackSet<IGraphNode> _predecessors = new StackSet<IGraphNode>();

    public GraphNode(final INode node) {
        _node = node;
    }

    @Override
    public boolean equals(final Object obj) {
        final GraphNode otherObject = (GraphNode) obj;
        // performance optimized
        return _node.getId().getValue() == otherObject._node.getId().getValue();
    }

    @Override
    public int hashCode() {
        // return HashCode.Combine(_id.GetHashCode(),
        // _entity.GetEntity().GetNodeType().GetHashCode());
        return _node.getId().hashCode();
    }

    @Override
    public String toString() {
        return _node.toString();
    }

    @Override
    public Id getId() {
        return _node.getId();
    }

    @Override
    public void AddSuccessor(final IGraphNode node) {
        _successors.Push(node);
    }

    @Override
    public void AddSuccessors(final GraphNodes nodes) {
        for (final IGraphNode node : nodes) {
            AddSuccessor(node);
        }
    }

    @Override
    public GraphNodes GetSuccessors() {
        final GraphNodes nodes = new GraphNodes();
        nodes.AddAll(_successors);
        return nodes;
    }

    @Override
    public void AddPredecessor(final IGraphNode node) {
        _predecessors.Push(node);
    }

    @Override
    public void AddPredecessors(final GraphNodes nodes) {
        for (final IGraphNode node : nodes) {
            AddPredecessor(node);
        }
    }

    @Override
    public GraphNodes GetPredecessors() {
        final GraphNodes nodes = new GraphNodes(_predecessors);
        return nodes;
    }

    @Override
    public void RemoveSuccessor(final IGraphNode node) {
        _successors.Remove(node);
    }

    @Override
    public void RemovePredecessor(final IGraphNode node) {
        _predecessors.Remove(node);
    }

    @Override
    public void RemoveAllSuccessors() {
        _successors.Clear();
    }

    @Override
    public void RemoveAllPredecessors() {
        _predecessors.Clear();
    }

    @Override
    public INode GetNode() {
        return _node;
    }
}
