package com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.graph.impl;

import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.datastructure.IEntity;
import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.datastructure.Id;
import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.graph.INode;

public class Node implements INode {

    private final Id _id;
    private final IEntity _entity;

    /**
     * put one of the other classes inheriting INode in it like ProductionOrder...
     */
    public Node(final IEntity entity) {
        _entity = entity;
        _id = entity.getId();
    }

    @Override
    public Id getId() {
        return _id;
    }

    @Override
    public IEntity GetEntity() {
        return _entity;
    }

    @Override
    public boolean equals(final Object obj) {
        final Node otherObject = (Node) obj;
        return _id.getValue() == otherObject._id.getValue();
    }

    @Override
    public int hashCode() {
        // return HashCode.Combine(_id.GetHashCode(),
        // _entity.GetEntity().GetNodeType().GetHashCode());
        return _id.hashCode();
    }

    @Override
    public String toString() {
        return _entity.toString();
    }

}
