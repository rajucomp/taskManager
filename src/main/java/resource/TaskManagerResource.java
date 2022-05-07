package resource;

import config.Configs;
import config.QueueConfigs;
import domain.Priority;
import domain.Process;
import domain.TaskManagerType;
import resource.dto.ProcessDTO;
import service.TaskManager;
import service.TaskManagerFactory;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/taskmanager")
public class TaskManagerResource {


    private final TaskManagerFactory taskManagerFactory;
    private final QueueConfigs queueConfigs;
    private TaskManager taskManager;

    public TaskManagerResource(TaskManagerFactory taskManagerFactory, QueueConfigs queueConfigs) {
        this.taskManagerFactory = taskManagerFactory;
        this.queueConfigs = queueConfigs;
        new Configs(queueConfigs.getCapacity());
    }

    @POST
    @Path("/init/{type}")
    public Response createTaskManager(@PathParam("type") TaskManagerType type) {
        this.taskManager = taskManagerFactory.createTaskManager(type);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Process> list() {
        return this.taskManager.list();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProcess(ProcessDTO dto) {
        final String pid = this.taskManager.addProcess(dto.toProcess());
        return Response.ok(pid).build();
    }

    @DELETE
    @Path("/{pid}")
    public void kill(@PathParam("pid") String pid) {
        this.taskManager.kill(pid);
    }

    @DELETE
    @Path("/all/{priority}")
    public void killAll(@PathParam("priority") Priority priority) {
        if (priority != null) {
            this.taskManager.killAll(priority);
            return;
        }
        this.taskManager.killAll();
    }
}