package com.pascalschumann.jobshopschedulermicroservice.scheduler.impl;

import java.util.List;

import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.modelWrappers.Operation;

/**
 * TODO: fill this
 *
 * @author Pascal Schumann
 */
public interface IPriorityRule {

    Operation GetHighestPriorityOperation(Long now, List<Operation> operations);
}
