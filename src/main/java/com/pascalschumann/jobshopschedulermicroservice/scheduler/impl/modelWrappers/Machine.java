package com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.modelWrappers;

import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.datastructure.Id;

/**
 * Wraps Machine from api model
 *
 * @author Pascal Schumann
 */
public class Machine {

    // wrapped value
    private final com.pascalschumann.jobshopschedulermicroservice.api.model.Machine machineFromApi;
    // more members
    private Long idleStart = 0L;

    public Machine(final com.pascalschumann.jobshopschedulermicroservice.api.model.Machine machineFromApi) {
        this.machineFromApi = machineFromApi;
    }

    public Id getId() {
        return new Id(machineFromApi.getId());
    }

    public Id getMachineGroupId() {
        return new Id(machineFromApi.getMachineGroupId());
    }

    public Long getIdleStart() {
        return idleStart;
    }

    public void setIdleStart(final Long idleStart) {
        this.idleStart = idleStart;
    }
}
