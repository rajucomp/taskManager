package config;

import io.quarkus.arc.config.ConfigProperties;

@ConfigProperties(prefix = "taskmanager")
public class QueueConfigs {

    private Integer capacity;

    public Integer getCapacity() {
        return capacity;
    }
}
