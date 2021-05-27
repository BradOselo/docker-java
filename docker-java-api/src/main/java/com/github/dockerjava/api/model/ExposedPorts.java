package com.github.dockerjava.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@EqualsAndHashCode(callSuper = true)
@ToString
public class ExposedPorts extends DockerObject implements Serializable {
    private static final long serialVersionUID = 1L;

    private ExposedPort[] exposedPorts;

    public ExposedPorts(ExposedPort... exposedPorts) {
        this.exposedPorts = exposedPorts;
    }

    public ExposedPorts(List<ExposedPort> exposedPorts) {
        this.exposedPorts = exposedPorts.toArray(new ExposedPort[exposedPorts.size()]);
    }

    public ExposedPort[] getExposedPorts() {
        return exposedPorts;
    }

    @JsonCreator
    public static ExposedPorts fromPrimitive(Map<String, Object> object) {
        return new ExposedPorts(
                object.keySet().stream().map(ExposedPort::parse).toArray(ExposedPort[]::new)
        );
    }

    @JsonValue
    public Map<String, Object> toPrimitive() {
        return Stream.of(exposedPorts).collect(Collectors.toMap(
            ExposedPort::toString,
            __ -> new Object(),
            (a, b) -> a
        ));
    }

}
