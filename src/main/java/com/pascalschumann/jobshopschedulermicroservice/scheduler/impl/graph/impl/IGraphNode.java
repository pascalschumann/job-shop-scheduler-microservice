package com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.graph.impl;

import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.datastructure.IEntity;
import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.graph.INode;

/**
 * TODO: fill this
 *
 * @author Pascal Schumann
 */
public interface IGraphNode extends IEntity {
    void AddSuccessor(IGraphNode node);

    void AddSuccessors(GraphNodes nodes);

    void AddPredecessor(IGraphNode node);

    void AddPredecessors(GraphNodes nodes);

    GraphNodes GetPredecessors();

    GraphNodes GetSuccessors();

    void RemoveSuccessor(IGraphNode node);

    void RemovePredecessor(IGraphNode node);

    void RemoveAllSuccessors();

    void RemoveAllPredecessors();

    INode GetNode();
}
