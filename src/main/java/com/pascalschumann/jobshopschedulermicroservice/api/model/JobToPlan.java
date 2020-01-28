package com.pascalschumann.jobshopschedulermicroservice.api.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.datastructure.IEntity;
import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.datastructure.Id;

import io.swagger.annotations.ApiModelProperty;

/**
 * JobToPlan
 */
@Validated
public class JobToPlan implements IEntity {

    @JsonProperty("id")
    private Id id = null;

    @JsonProperty("duration")
    private Long duration = null;

    @JsonProperty("start")
    private Long start = null;

    @JsonProperty("end")
    private Long end = null;

    @JsonProperty("machineGroupId")
    private Long machineGroupId = null;

    @JsonProperty("materialAvailability")
    private Long materialAvailability = null;

    @JsonProperty("predecessorId")
    private Long predecessorId = null;

    @JsonProperty("successorIds")
    private Long[] successorIds = null;

    public JobToPlan() {}

    public JobToPlan(final Id id, final Long duration, final Long machineGroupId) {
        this.id = id;
        this.duration = duration;
        this.machineGroupId = machineGroupId;
    }

    /**
     * Get id
     *
     * @return id
     **/
    @Override
    @ApiModelProperty(required = true, value = "")
    @NotNull
    public Id getId() {
        return id;
    }

    public void setId(final Id id) {
        this.id = id;
    }

    public Long[] getSuccessorIds() {
        return successorIds;
    }

    public void setSuccessorIds(final Long[] successorIds) {
        this.successorIds = successorIds;
    }

    public Long getPredecessorId() {
        return predecessorId;
    }

    public void setPredecessorId(final Long predecessorId) {
        this.predecessorId = predecessorId;
    }

    public Long getMaterialAvailability() {
        return materialAvailability;
    }

    public void setMaterialAvailability(final Long materialAvailability) {
        this.materialAvailability = materialAvailability;
    }

    @ApiModelProperty(required = true, value = "")
    @NotNull
    public Long getMachineGroupId() {
        return machineGroupId;
    }

    public void setMachineGroupId(final Long machineGroupId) {
        this.machineGroupId = machineGroupId;
    }

    @ApiModelProperty(required = true, value = "")
    @NotNull
    public Long getDuration() {
        return duration;
    }

    public void setDuration(final Long duration) {
        this.duration = duration;
    }

    /**
     * Get start
     *
     * @return start
     **/
    public Long getStart() {
        return start;
    }

    public void setStart(final Long start) {
        this.start = start;
    }

    /**
     * Get end
     *
     * @return end
     **/
    public Long getEnd() {
        return end;
    }

    public void setEnd(final Long end) {
        this.end = end;
    }

    @Override
    public boolean equals(final java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final JobToPlan jobToPlan = (JobToPlan) o;
        return Objects.equals(this.id, jobToPlan.id) && Objects.equals(this.start, jobToPlan.start)
                        && Objects.equals(this.end, jobToPlan.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, start, end);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class JobToPlan {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    start: ").append(toIndentedString(start)).append("\n");
        sb.append("    end: ").append(toIndentedString(end)).append("\n");
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
