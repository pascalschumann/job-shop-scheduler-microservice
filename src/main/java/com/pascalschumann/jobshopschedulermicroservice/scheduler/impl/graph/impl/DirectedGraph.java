package com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.graph.impl;

import java.util.List;
import java.util.Stack;

import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.datastructure.IStackSet;
import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.datastructure.Id;
import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.datastructure.impl.StackSet;
import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.graph.IDirectedGraph;
import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.graph.IEdge;
import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.graph.INode;
import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.graph.INodes;

/**
 * An impl for a directed graph. It's important to always return null if aggregation is empty
 * (simplify error detecting, since no empty collections should pass through the program). A
 * directed graph is stored/read in by its edges, from that edges a list of nodes is built up where
 * every node has:<br>
 * - a list of successors<br>
 * - a list of predecessors<br>
 * --> so that O(1) can be realized for most operations<br>
 *
 * @author Pascal Schumann
 */
public class DirectedGraph implements IDirectedGraph {

    protected IStackSet<IGraphNode> _nodes = new StackSet<IGraphNode>();

    public DirectedGraph() {}

    public DirectedGraph(final List<IEdge> edges) {
        AddEdges(edges);
    }

    @Override
    public INodes GetSuccessorNodes(final Id nodeId) {
        final IGraphNode graphNode = _nodes.GetById(nodeId);
        if (graphNode == null) {
            // node doesn't exists
            throw new RuntimeException("Given node (" + graphNode + ") doesn't exists in graph.");
        }

        final GraphNodes successors = graphNode.GetSuccessors();

        if (successors.isEmpty()) {
            return null;
        }

        return new Nodes(successors);
    }

    @Override
    public INodes GetSuccessorNodes(final INode node) {
        return GetSuccessorNodes(node.getId());
    }

    @Override
    public INodes GetPredecessorNodes(final INode node) {
        return GetPredecessorNodes(node.getId());
    }

    @Override
    public INodes GetPredecessorNodesRecursive(final INode startNode) {
        final INodes allPredecessors = new Nodes();
        final Stack<INode> stack = new Stack<INode>();
        stack.push(startNode);

        while (stack.isEmpty() == false) {
            final INode poppedNode = stack.pop();
            if (poppedNode.equals(startNode) == false) {
                allPredecessors.Add(poppedNode);
            }

            final INodes predecessors = GetPredecessorNodes(poppedNode.getId());
            if (predecessors != null) {
                for (final INode predecessor : predecessors) {
                    stack.push(predecessor);
                }
            }

        }

        if (allPredecessors.any() == false) {
            return null;
        }
        return allPredecessors;
    }

    @Override
    public INodes GetPredecessorNodes(final Id nodeId) {
        final IGraphNode graphNode = _nodes.GetById(nodeId);
        if (graphNode == null) {
            // node doesn't exists
            throw new RuntimeException("Given node (" + graphNode + ") doesn't exists in graph.");
        }

        final GraphNodes predecessors = graphNode.GetPredecessors();

        if (predecessors.any() == false) {
            return null;
        }

        return new Nodes(predecessors);
    }

    @Override
    public void AddEdges(final Iterable<IEdge> edges) {
        for (final IEdge edge : edges) {
            AddEdge(edge);
        }
    }

    @Override
    public void AddEdges(final INode fromNode, final INodes nodes) {
        for (final INode toNode : nodes) {
            AddEdge(new Edge(fromNode, toNode));
        }
    }

    @Override
    public void AddEdge(final IEdge edge) {
        final INode edgeTail = edge.GetTailNode();
        final INode edgeHead = edge.GetHeadNode();
        final IGraphNode tail;
        final IGraphNode head;
        if (_nodes.Contains(edgeTail.getId()) == false) {
            tail = new GraphNode(edgeTail);
            _nodes.Push(tail);
        } else {
            tail = _nodes.GetById(edgeTail.getId());
        }

        if (_nodes.Contains(edgeHead.getId()) == false) {
            head = new GraphNode(edgeHead);
            _nodes.Push(head);
        } else {
            head = _nodes.GetById(edgeHead.getId());
        }

        head.AddPredecessor(tail);
        tail.AddSuccessor(head);
    }

    @Override
    public int CountEdges() {
        return _nodes.size();
    }

    @Override
    public IStackSet<INode> GetAllUniqueNodes() {
        final IStackSet<INode> uniqueNodes = new StackSet<INode>();
        uniqueNodes.PushAll(new Nodes(_nodes));

        if (uniqueNodes.Any() == false) {
            return null;
        }

        return uniqueNodes;
    }

    @Override
    public boolean Contains(final INode node) {
        return Contains(node.getId());
    }

    @Override
    public boolean Contains(final Id nodeId) {
        final IGraphNode graphNode = _nodes.GetById(nodeId);
        return graphNode != null;
    }

    @Override
    public void RemoveNode(final Id nodeId, final boolean connectParentsWithChilds) {
        RemoveNode(nodeId, connectParentsWithChilds, true);
    }

    @Override
    public void RemoveNode(final INode node, final boolean connectParentsWithChilds) {
        RemoveNode(node, connectParentsWithChilds, true);
    }

    @Override
    public void RemoveNode(final INode node, final boolean connectParentsWithChilds,
                    final boolean removeEdges) // TODO
                                               // =
                                               // true)
    {
        RemoveNode(node.getId(), connectParentsWithChilds, removeEdges);
    }

    @Override
    public void RemoveNode(final Id nodeId, final boolean connectParentsWithChilds,
                    final boolean removeEdges) // TODO =
                                               // true)
    {
        // e.g. A -> B --> C, B is removed

        final IGraphNode graphNode = _nodes.GetById(nodeId);
        if (graphNode == null) {
            // node doesn't exists
            throw new RuntimeException("Given node (" + graphNode + ") doesn't exists in graph.");
        }

        // holds A
        final GraphNodes predecessors = graphNode.GetPredecessors();
        // holds C
        final GraphNodes successors = graphNode.GetSuccessors();

        if (connectParentsWithChilds) {
            for (final IGraphNode predecessor : predecessors) {
                // predecessor is A

                // remove edge A -> B
                predecessor.RemoveSuccessor(graphNode);
                // add edge A -> C
                predecessor.AddSuccessors(successors);
            }

            for (final IGraphNode successor : successors) {
                // successor is C

                // remove edge B -> C
                successor.RemovePredecessor(graphNode);
                // add edge A -> C
                successor.AddPredecessors(predecessors);
            }
        } else if (removeEdges) {
            for (final IGraphNode predecessor : predecessors) {
                // predecessor is A

                // remove edge A -> B
                predecessor.RemoveSuccessor(graphNode);
            }

            for (final IGraphNode successor : successors) {
                // successor is C

                // remove edge B -> C
                successor.RemovePredecessor(graphNode);
            }
        }

        // remove node
        _nodes.Remove(graphNode);
    }

    @Override
    public INodes GetLeafNodes() {
        final INodes leafs = new Nodes();

        for (final IGraphNode node : _nodes) {
            final INodes successors = GetSuccessorNodes(node.GetNode());
            if (successors == null || successors.any() == false) {
                leafs.Add(node.GetNode());
            }
        }

        if (leafs.any() == false) {
            return null;
        }

        return leafs;
    }

    @Override
    public boolean IsEmpty() {
        return _nodes == null || _nodes.Any() == false;
    }

    @Override
    public INodes GetRootNodes() {
        final INodes roots = new Nodes();

        for (final IGraphNode node : _nodes) {
            final INodes predecessors = GetPredecessorNodes(node.GetNode());
            if (predecessors == null) {
                roots.Add(node.GetNode());
            }
        }

        if (roots.any() == false) {
            return null;
        }

        return roots;
    }

    @Override
    public void ReplaceNodeByDirectedGraph(final INode node, final IDirectedGraph graphToInsert) {
        ReplaceNodeByDirectedGraph(node.getId(), graphToInsert);
    }

    @Override
    public void ReplaceNodeByOtherNode(final Id nodeId, INode otherNode) {
        if (Contains(otherNode)) {
            otherNode = GetNode(otherNode.getId());
        }
        final INodes predecessors = GetPredecessorNodes(nodeId);
        final INodes successors = GetSuccessorNodes(nodeId);
        RemoveNode(nodeId, false);
        // predecessors --> roots
        if (predecessors != null) {
            for (final INode predecessor : predecessors) {

                AddEdge(new Edge(predecessor, otherNode));

            }
        }

        // leafs --> successors
        if (successors != null) {

            for (final INode successor : successors) {
                AddEdge(new Edge(otherNode, successor));
            }
        }

    }

    @Override
    public void ReplaceNodeByDirectedGraph(final Id nodeId, final IDirectedGraph graphToInsert) {
        final INodes predecessors = GetPredecessorNodes(nodeId);
        final INodes successors = GetSuccessorNodes(nodeId);
        RemoveNode(nodeId, false);
        // predecessors --> roots
        if (predecessors != null) {
            for (final INode predecessor : predecessors) {
                for (final INode rootNode : graphToInsert.GetRootNodes()) {
                    AddEdge(new Edge(predecessor, rootNode));
                }
            }
        }

        // leafs --> successors
        if (successors != null) {
            for (final INode leaf : graphToInsert.GetLeafNodes()) {
                for (final INode successor : successors) {
                    AddEdge(new Edge(leaf, successor));
                }
            }
        }

        // add all edges from graphToInsert
        AddEdges(graphToInsert.GetEdges());
    }

    @Override
    public INode GetNode(final Id id) {
        final IGraphNode graphNode = _nodes.GetById(id);
        if (graphNode == null) {
            throw new RuntimeException("A with id " + id + " doesn't exist.");
        }
        return graphNode.GetNode();
    }

    @Override
    public IStackSet<IEdge> GetEdges() {
        final IStackSet<IEdge> edges = new StackSet<IEdge>();

        // one is enough either all successors or all predecessors
        for (final IGraphNode node : _nodes) {
            for (final IGraphNode successor : node.GetSuccessors()) {
                edges.Push(new Edge(node.GetNode(), successor.GetNode()));
            }
        }

        if (edges.Any() == false) {
            return null;
        }

        return edges;
    }

    @Override
    public void Clear() {
        _nodes.Clear();
    }

    @Override
    public IStackSet<IGraphNode> GetNodes() {
        return _nodes;
    }

    @Override
    public void RemoveEdge(final INode parent, final INode child) {
        final IGraphNode parentGraphNode = _nodes.GetById(parent.getId());
        final IGraphNode childGraphNode = _nodes.GetById(child.getId());
        parentGraphNode.RemoveSuccessor(childGraphNode);
        childGraphNode.RemovePredecessor(parentGraphNode);
    }
}
