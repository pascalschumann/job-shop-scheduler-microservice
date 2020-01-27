package com.pascalschumann.jobshopscheduler.api.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * PlannedJob
 */
@Validated
public class PlannedJob {
	@JsonProperty("id")
	private Long id = null;

	@JsonProperty("machineId")
	private Long machineId = null;

	@JsonProperty("start")
	private Long start = null;

	@JsonProperty("end")
	private Long end = null;

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
	 * Get machineId
	 * 
	 * @return machineId
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public Long getMachineId() {
		return machineId;
	}

	public void setMachineId(final Long machineId) {
		this.machineId = machineId;
	}

	/**
	 * Get start
	 * 
	 * @return start
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

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
	@ApiModelProperty(required = true, value = "")
	@NotNull

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
		final PlannedJob plannedJob = (PlannedJob) o;
		return Objects.equals(this.id, plannedJob.id) && Objects.equals(this.machineId, plannedJob.machineId) &&
			Objects.equals(this.start, plannedJob.start) && Objects.equals(this.end, plannedJob.end);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, machineId, start, end);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class PlannedJob {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    machineId: ").append(toIndentedString(machineId)).append("\n");
		sb.append("    start: ").append(toIndentedString(start)).append("\n");
		sb.append("    end: ").append(toIndentedString(end)).append("\n");
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
