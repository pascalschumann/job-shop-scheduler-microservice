package com.pascalschumann.jobshopscheduler.api.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Machine
 */
@Validated
public class Machine {
	@JsonProperty("id")
	private Long id = null;

	@JsonProperty("machineGroupId")
	private Long machineGroupId = null;

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
	 * Get machineGroupId
	 * 
	 * @return machineGroupId
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public Long getMachineGroupId() {
		return machineGroupId;
	}

	public void setMachineGroupId(final Long machineGroupId) {
		this.machineGroupId = machineGroupId;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final Machine machine = (Machine) o;
		return Objects.equals(this.id, machine.id) && Objects.equals(this.machineGroupId, machine.machineGroupId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, machineGroupId);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class Machine {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    machineGroupId: ").append(toIndentedString(machineGroupId)).append("\n");
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
