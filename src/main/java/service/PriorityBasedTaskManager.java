package service;

import config.Configs;
import domain.Process;


public class PriorityBasedTaskManager implements TaskManager {

    public synchronized String addProcess(Process process) {
        if (Configs.processes.remainingCapacity() == 0) {
            Configs.processes.stream().sorted().findFirst().get().kill();
        }
        process.start();
        return process.getPid();
    }
}
