package com.pascalschumann.jobshopschedulermicroservice.api.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * ScheduleRequest
 */
@Validated
public class ScheduleRequest {
    @JsonProperty("id")
    private Long id = null;

    @JsonProperty("jobsToPlan")
    private JobToPlan[] jobsToPlan = null;

    @JsonProperty("machines")
    private Machine[] machines = null;

    public ScheduleRequest(final Long id, final JobToPlan[] jobsToPlan, final Machine[] machines) {
        this.id = id;
        this.jobsToPlan = jobsToPlan;
        this.machines = machines;
    }

    /**
     * Get id
     * 
     * @return id
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Get jobsToPlan
     * 
     * @return jobsToPlan
     **/
    @ApiModelProperty(value = "")

    @Valid
    public JobToPlan[] getJobsToPlan() {
        return jobsToPlan;
    }

    public void setJobsToPlan(final JobToPlan[] jobsToPlan) {
        this.jobsToPlan = jobsToPlan;
    }

    /**
     * Get machines
     * 
     * @return machines
     **/
    @ApiModelProperty(value = "")

    @Valid
    public Machine[] getMachines() {
        return machines;
    }

    public void setMachines(final Machine[] machines) {
        this.machines = machines;
    }

    @Override
    public boolean equals(final java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ScheduleRequest scheduleRequest = (ScheduleRequest) o;
        return Objects.equals(this.id, scheduleRequest.id)
                        && Objects.equals(this.jobsToPlan, scheduleRequest.jobsToPlan)
                        && Objects.equals(this.machines, scheduleRequest.machines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, jobsToPlan, machines);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class ScheduleRequest {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    jobsToPlan: ").append(toIndentedString(jobsToPlan)).append("\n");
        sb.append("    machines: ").append(toIndentedString(machines)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces (except the first
     * line).
     */
    private String toIndentedString(final java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
