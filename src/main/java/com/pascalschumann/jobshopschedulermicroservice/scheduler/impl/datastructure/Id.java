package com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.datastructure;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * If instantiated, contains an atomic id per jvm
 *
 * @author Pascal Schumann
 */
@Validated
public class Id {

    private static final AtomicLong idCounter = new AtomicLong();

    @JsonProperty("value")
    private final long id;

    public Id() {

        id = idCounter.getAndIncrement();
    }

    public Id(final long id) {
        this.id = id;
    }

    @ApiModelProperty(required = true, value = "")
    @NotNull
    public long getValue() {
        return id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Id id1 = (Id) o;
        return id == id1.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
