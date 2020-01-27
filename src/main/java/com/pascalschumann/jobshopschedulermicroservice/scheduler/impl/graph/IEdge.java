package com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.graph;

import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.datastructure.IEntity;

/**
 * TODO: fill this
 *
 * @author Pascal Schumann
 */
public interface IEdge extends IEntity {
    INode GetTailNode();

    INode GetHeadNode();
}
