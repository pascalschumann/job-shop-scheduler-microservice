package com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.graph;

import java.util.Stack;

import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.datastructure.ICollectionWrapper;

/**
 * TODO: fill this
 *
 * @author Pascal Schumann
 */
public interface INodes extends ICollectionWrapper<INode> {

    Stack<INode> ToStack();
}
