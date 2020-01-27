package com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.modelWrappers;

import com.pascalschumann.jobshopschedulermicroservice.api.model.JobToPlan;
import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.datastructure.IEntity;
import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.datastructure.Id;

/**
 * Wraps JobToPlan from api model
 *
 * @author Pascal Schumann
 */
public class Operation implements IEntity {

    // wrapped entity
    private final JobToPlan jobToPlan;
    // additional members
    private Id plannedMachine = null;

    public Operation(final JobToPlan jobToPlan) {
        this.jobToPlan = jobToPlan;
    }

    @Override
    public Id getId() {
        return null;
    }

    public Long getStart() {
        return jobToPlan.getStart();
    }

    public void setStart(final Long start) {
        jobToPlan.setStart(start);
    }

    public Long getEnd() {
        return jobToPlan.getEnd();
    }

    public void setEnd(final Long end) {
        jobToPlan.setEnd(end);
    }

    public Long getDuration() {
        return jobToPlan.getDuration();
    }

    public void setDuration(final Long duration) {
        jobToPlan.setDuration(duration);
    }

    public Id getMachineGroupId() {
        return new Id(jobToPlan.getMachineGroupId());
    }

    public void setMachineGroupId(final Id machineGroupId) {
        jobToPlan.setMachineGroupId(machineGroupId.getValue());
    }

    public Id getPlannedMachine() {
        return plannedMachine;
    }

    public void setPlannedMachine(final Id plannedMachine) {
        this.plannedMachine = plannedMachine;
    }

    public Long getMaterialAvailability() {
        return jobToPlan.getMaterialAvailability();
    }

    public Id getPredecessorId() {
        return new Id(jobToPlan.getPredecessorId());
    }

    public Id[] getSuccessorId() {
        final Id[] successorIds = new Id[jobToPlan.getSuccessorIds().length];
        for (int i = 0; i < successorIds.length; i++) {
            successorIds[i] = new Id(jobToPlan.getSuccessorIds()[i]);
        }
        return successorIds;
    }

}
