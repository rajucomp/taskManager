package service;

import config.Configs;
import domain.Process;


public class FIFOTaskManager implements TaskManager {


    @Override
    public synchronized String addProcess(Process process) {
        if (Configs.processes.remainingCapacity() == 0) {
            Configs.processes.poll().kill();
        }
        process.start();
        return process.getPid();
    }


}
