package com.pascalschumann.jobshopscheduler.scheduler.impl;

import java.util.List;

import com.pascalschumann.jobshopscheduler.scheduler.impl.graph.IDirectedGraph;
import com.pascalschumann.jobshopscheduler.scheduler.impl.modelWrappers.Operation;

/**
 * TODO: This is a simplification, lowest duration get highest prio --> replace by least-slack-time-rule
 *
 * @author Pascal Schumann
 */
public class PriorityRule
implements IPriorityRule {
	private final IDirectedGraph _operationGraph;

	public PriorityRule(final IDirectedGraph operationGraph) {
		_operationGraph = operationGraph;
	}

	@Override
    public Operation GetHighestPriorityOperation(final Long now, final List<Operation> operations) {
		long minDuration = Long.MAX_VALUE;
		Operation minOperation = null;

		for (final Operation operation : operations) {
			if (operation.getDuration() < minDuration) {
				minDuration = operation.getDuration();
				minOperation = operation;
			}
		}
		return minOperation;
	}

}
