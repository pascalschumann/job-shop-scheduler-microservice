package com.pascalschumann.jobshopscheduler.scheduler.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.pascalschumann.jobshopscheduler.api.model.JobToPlan;
import com.pascalschumann.jobshopscheduler.scheduler.impl.datastructure.Id;
import com.pascalschumann.jobshopscheduler.scheduler.impl.graph.IEdge;
import com.pascalschumann.jobshopscheduler.scheduler.impl.graph.INode;
import com.pascalschumann.jobshopscheduler.scheduler.impl.graph.impl.DirectedGraph;
import com.pascalschumann.jobshopscheduler.scheduler.impl.graph.impl.Edge;
import com.pascalschumann.jobshopscheduler.scheduler.impl.graph.impl.Node;
import com.pascalschumann.jobshopscheduler.scheduler.impl.modelWrappers.Operation;

/**
 * A
 *
 * @author Pascal Schumann
 */
public class OperationGraph
extends DirectedGraph {

	public OperationGraph(final JobToPlan[] jobsToPlan) {

		CreateGraph(jobsToPlan);
	}

	private void CreateGraph(final JobToPlan[] jobsToPlan) {

		final List<Operation> roots = new LinkedList<>();
		final Map<Id, Operation> operations = new HashMap<>();

		// determine roots and index given jobs
		for (final JobToPlan jobToPlan : jobsToPlan) {

			final Operation operation = new Operation(jobToPlan);
			if (operations.containsKey(operation.getId())) {
				throw new RuntimeException("Every job must have a unique id.");
			} else {
				operations.put(new Id(jobToPlan.getId()), operation);
			}

			if (jobToPlan.getPredecessorId() == null) {
				roots.add(operation);
			}
		}

		// traverse
		for (final Operation root : roots) {
			traverseOperations(root, operations);

		}
	}

	private void traverseOperations(final Operation currentOperation, final Map<Id, Operation> allOperations) {

		// break condition
		if (currentOperation.getSuccessorId() == null) {
			return;
		}

		final INode fromNode = new Node(currentOperation);
		final INode toNode = new Node(allOperations.get(currentOperation.getSuccessorId()));

		final IEdge edge = new Edge(fromNode, toNode);
		AddEdge(edge);
	}
}
