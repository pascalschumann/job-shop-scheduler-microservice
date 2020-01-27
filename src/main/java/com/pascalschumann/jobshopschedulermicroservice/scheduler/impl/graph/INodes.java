package com.pascalschumann.jobshopscheduler.scheduler.impl.graph;

import com.pascalschumann.jobshopscheduler.scheduler.impl.datastructure.ICollectionWrapper;

import java.util.Stack;

/**
 * TODO: fill this
 *
 * @author Pascal Schumann
 */
public interface INodes extends ICollectionWrapper<INode> {

    Stack<INode> ToStack();
}
