package com.pascalschumann.jobshopscheduler.api.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * ScheduleResponse
 */
@Validated
public class ScheduleResponse {
	@JsonProperty("id")
	private Long id = null;

	@JsonProperty("plannedJobs")
	private PlannedJob[] plannedJobs = null;

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
	 * Get plannedJobs
	 * 
	 * @return plannedJobs
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public PlannedJob[] getPlannedJobs() {
		return plannedJobs;
	}

	public void setPlannedJobs(final PlannedJob[] plannedJobs) {
		this.plannedJobs = plannedJobs;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final ScheduleResponse scheduleResponse = (ScheduleResponse) o;
		return Objects.equals(this.id, scheduleResponse.id) &&
			Objects.equals(this.plannedJobs, scheduleResponse.plannedJobs);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, plannedJobs);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ScheduleResponse {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    plannedJobs: ").append(toIndentedString(plannedJobs)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces (except the first line).
	 */
	private String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
