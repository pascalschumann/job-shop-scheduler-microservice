package com.pascalschumann.jobshopscheduler.api.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pascalschumann.jobshopscheduler.api.model.ScheduleRequest;
import com.pascalschumann.jobshopscheduler.api.model.ScheduleResponse;
import com.pascalschumann.jobshopscheduler.api.store.ScheduleStore;
import com.pascalschumann.jobshopscheduler.scheduler.impl.JobShopScheduler;
import com.pascalschumann.jobshopscheduler.scheduler.impl.OperationGraph;
import com.pascalschumann.jobshopscheduler.scheduler.impl.PriorityRule;
import com.pascalschumann.jobshopscheduler.scheduler.impl.datastructure.Id;
import com.pascalschumann.jobshopscheduler.scheduler.impl.graph.IDirectedGraph;
import com.pascalschumann.jobshopscheduler.scheduler.impl.modelWrappers.Machine;

/**
 * The service around creating/getting/deleting a schedule
 *
 * @author Pascal Schumann
 */
@Component
public class ScheduleService {

	private final ScheduleStore scheduleStore;
	private final JobShopScheduler jobShopScheduler;

	@Autowired
	public ScheduleService(final ScheduleStore scheduleStore, final JobShopScheduler jobShopScheduler) {
		this.scheduleStore = scheduleStore;
		this.jobShopScheduler = jobShopScheduler;
	}

	public void createSchedule(final ScheduleRequest scheduleRequest) {

		final List<Machine> machines = new LinkedList<>();
		for (final com.pascalschumann.jobshopscheduler.api.model.Machine machine : scheduleRequest.getMachines()) {
			machines.add(new Machine(machine));
		}

		final IDirectedGraph operationGraph = new OperationGraph(scheduleRequest.getJobsToPlan());
		jobShopScheduler.ScheduleWithGifflerThompsonAsZaepfel(new PriorityRule(operationGraph), operationGraph,
			machines);
	}

	public ScheduleResponse getSchedule(final Id id) {
		if (scheduleStore.containsKey(id) == false) {
			return null;
		}
		return scheduleStore.get(id);
	}

	public ScheduleResponse[] getSchedules() {

		if (scheduleStore.size() == 0) {
			return new ScheduleResponse[0];
		}
		return scheduleStore.values().toArray(new ScheduleResponse[scheduleStore.size()]);
	}

	public void deleteSchedule(final Id id) {
		if (scheduleStore.containsKey(id) == false) {
			return;
		}
		scheduleStore.remove(id);
	}
}
