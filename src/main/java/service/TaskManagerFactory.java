package service;


import config.Configs;
import config.QueueConfigs;
import domain.TaskManagerType;

import javax.enterprise.context.ApplicationScoped;

import static domain.TaskManagerType.*;

@ApplicationScoped
public class TaskManagerFactory {

    public TaskManager createTaskManager(TaskManagerType type) {

         if (type.equals(PRIORITY)){
             return new PriorityBasedTaskManager();
         } else if (type.equals(FIFO)) {
             return new FIFOTaskManager();
         }
         return new DefaultTaskManger();

    }

}
