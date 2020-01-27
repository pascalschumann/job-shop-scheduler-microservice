package com.pascalschumann.jobshopschedulermicroservice.scheduler;

import java.util.List;

import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.IPriorityRule;
import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.graph.IDirectedGraph;
import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.modelWrappers.Machine;

/**
 * TODO: fill this
 *
 * @author Pascal Schumann
 */
public interface IJobShopScheduler {
    void ScheduleWithGifflerThompsonAsZaepfel(IPriorityRule priorityRule,
                    IDirectedGraph operationGraph, List<Machine> machines);
}
