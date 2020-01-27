package com.pascalschumann.jobshopschedulermicroservice.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pascalschumann.jobshopschedulermicroservice.api.model.ScheduleRequest;
import com.pascalschumann.jobshopschedulermicroservice.api.model.ScheduleResponse;
import com.pascalschumann.jobshopschedulermicroservice.api.service.ScheduleService;
import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.datastructure.Id;

/**
 * TODO
 */
public class ScheduleController {

    private static final Logger log = LoggerFactory.getLogger(ScheduleController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(final ObjectMapper objectMapper, final HttpServletRequest request,
                    final ScheduleService scheduleService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.scheduleService = scheduleService;
    }


    public void createSchedule(final ScheduleRequest body) {

        scheduleService.createSchedule(body);
    }


    public ScheduleResponse[] listSchedules() {

        return scheduleService.getSchedules();
    }


    public ScheduleResponse showScheduleById(final Long scheduleId) {
        return scheduleService.getSchedule(new Id(scheduleId));
    }
}
