package com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.graph.impl;

import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.datastructure.impl.CollectionWrapperWithStackSet;

/**
 * TODO: fill this
 *
 * @author Pascal Schumann
 */
public class GraphNodes extends CollectionWrapperWithStackSet<IGraphNode> {

    GraphNodes(final Iterable<IGraphNode> list) {
        AddAll(list);
    }

    GraphNodes() {}
}
