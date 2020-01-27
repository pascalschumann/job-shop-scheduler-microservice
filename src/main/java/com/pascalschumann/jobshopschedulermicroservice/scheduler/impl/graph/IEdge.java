package com.pascalschumann.jobshopscheduler.scheduler.impl.graph;

import com.pascalschumann.jobshopscheduler.scheduler.impl.datastructure.IEntity;

/**
 * TODO: fill this
 *
 * @author Pascal Schumann
 */
public interface IEdge extends IEntity {
    INode GetTailNode();

    INode GetHeadNode();
}
