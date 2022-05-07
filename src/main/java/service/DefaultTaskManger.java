package service;

import config.Configs;
import domain.Process;


public class DefaultTaskManger implements TaskManager {

    public synchronized String addProcess(Process process) {
        if (Configs.processes.remainingCapacity() > 0) {
            process.start();
        }
        return process.getPid();
    }

}
