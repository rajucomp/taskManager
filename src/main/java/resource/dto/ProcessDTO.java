package resource.dto;

import domain.Priority;
import domain.Process;

import java.util.UUID;

public class ProcessDTO {

    private Priority priority;

    public Priority getPriority() {
        return priority;
    }

    public Process toProcess() {
        return new Process(UUID.randomUUID().toString(), this.priority);
    }
}
