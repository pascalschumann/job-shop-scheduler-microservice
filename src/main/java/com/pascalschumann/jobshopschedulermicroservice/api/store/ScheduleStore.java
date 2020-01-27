package com.pascalschumann.jobshopscheduler.api.store;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.pascalschumann.jobshopscheduler.api.model.ScheduleResponse;
import com.pascalschumann.jobshopscheduler.scheduler.impl.datastructure.Id;

/**
 * A in-memory storage for the created schedules
 *
 * @author Pascal Schumann
 */
@Component
public class ScheduleStore
extends HashMap<Id, ScheduleResponse> {
}
