package com.pascalschumann.jobshopschedulermicroservice.api.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * JobToPlan
 */
@Validated
public class JobToPlan {

    @JsonProperty("id")
    private Long id = null;

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

    @JsonProperty("successorId")
    private Long successorId = null;

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

    public Long getSuccessorId() {
        return successorId;
    }

    public void setSuccessorId(final Long successorId) {
        this.successorId = successorId;
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
