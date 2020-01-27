package com.pascalschumann.jobshopschedulermicroservice.api.store;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.pascalschumann.jobshopschedulermicroservice.api.model.ScheduleResponse;
import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.datastructure.Id;

/**
 * A in-memory storage for the created schedules
 *
 * @author Pascal Schumann
 */
@Component
public class ScheduleStore extends HashMap<Id, ScheduleResponse> {
}
