package com.pascalschumann.jobshopscheduler.scheduler;

import java.util.List;

import com.pascalschumann.jobshopscheduler.scheduler.impl.IPriorityRule;
import com.pascalschumann.jobshopscheduler.scheduler.impl.graph.IDirectedGraph;
import com.pascalschumann.jobshopscheduler.scheduler.impl.modelWrappers.Machine;

/**
 * TODO: fill this
 *
 * @author Pascal Schumann
 */
public interface IJobShopScheduler {
	void ScheduleWithGifflerThompsonAsZaepfel(IPriorityRule priorityRule, IDirectedGraph operationGraph,
		List<Machine> machines);
}
