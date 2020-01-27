package com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.graph.impl;

import java.util.Stack;

import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.datastructure.IStackSet;
import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.datastructure.impl.CollectionWrapperWithStackSet;
import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.graph.INode;
import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.graph.INodes;

/**
 * TODO: fill this
 *
 * @author Pascal Schumann
 */
public class Nodes extends CollectionWrapperWithStackSet<INode> implements INodes {
    public Nodes(final Iterable<INode> list) {
        AddAll(list);
    }

    public Nodes(final INode item) {
        Add(item);
    }

    public Nodes() {}

    public Nodes(IStackSet<IGraphNode> graphNodes) {
        this(new GraphNodes(graphNodes));
    }

    public Nodes(GraphNodes graphNodes) {
        for (IGraphNode successor : graphNodes) {
            Add(successor.GetNode());
        }
    }

    public Stack<INode> ToStack() {
        final Stack<INode> stack = new Stack<INode>();
        for (final INode item : StackSet) {
            stack.push(item);
        }

        return stack;
    }

}
