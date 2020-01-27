package com.pascalschumann.jobshopscheduler.scheduler.impl;

import java.util.List;

import com.pascalschumann.jobshopscheduler.scheduler.impl.modelWrappers.Operation;

/**
 * TODO: fill this
 *
 * @author Pascal Schumann
 */
public interface IPriorityRule {

	Operation GetHighestPriorityOperation(Long now, List<Operation> operations);
}
