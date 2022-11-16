package com.itheima.test;

import cn.hutool.core.io.IoUtil;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipInputStream;


@Slf4j
public class ActivitiDemo01 {
    @Test
    public void test() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/eviction.bpmn")
                .addClasspathResource("bpmn/eviction.svg")
                .name("出差申请流程").deploy();

        System.out.println(deployment.getId());
        System.out.println(deployment.getName());
    }

    @Test
    public void testDeployByZip() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ZipInputStream zipInputStream = new ZipInputStream(this.getClass().getClassLoader().getResourceAsStream("bpmn/eviction.zip"));
        Deployment deployment = repositoryService.createDeployment().addZipInputStream(zipInputStream).deploy();

        System.out.println("流程部署id=" + deployment.getId());
        System.out.println("流程部署名称=" + deployment.getName());
    }

    @Test
    public void testFindPersonalTaskList() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery().processDefinitionKey("myEviction").taskAssignee("zhangsan").list();
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            System.out.println("---------task--" + i + "------------");
            System.out.println(task.getProcessInstanceId());
            System.out.println("taskId=" + task.getId());
            System.out.println(task.getAssignee());
            System.out.println(task.getName());
        }
        log.debug("test");
    }

    @Test
    public void testCompleteTask() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        taskService.complete("82505");
    }

    @Test
    public void testCompleteTaskWithQuery() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery().processDefinitionKey("myEviction").taskAssignee("rose").list().get(0);
        System.out.println(task);
        taskService.complete(task.getId());
    }

    @Test
    public void testQueryProcessDefinition() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().processDefinitionKey("myEviction").orderByProcessDefinitionVersion().desc().list();
        System.out.println(list);
    }

    @Test
    public void testDeleteDeployment() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.deleteDeployment("1");
    }

    @Test
    public void testGetDeploymentResource() throws IOException {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        List<ProcessDefinition> myEviction = repositoryService.createProcessDefinitionQuery().processDefinitionKey("myEviction").list();
        ProcessDefinition processDefinition = myEviction.get(0);
        String resourceName = processDefinition.getResourceName();
        String diagramResourceName = processDefinition.getDiagramResourceName();
        InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
        InputStream diaInputStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), diagramResourceName);
        IoUtil.copy(resourceAsStream, Files.newOutputStream(Paths.get("c:/tmp/eviction.bpmn")));
        IoUtil.copy(diaInputStream, Files.newOutputStream(Paths.get("c:/tmp/eviction.svg")));
        resourceAsStream.close();
        diaInputStream.close();
    }

    @Test
    public void testFindHistoryTask() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .processDefinitionKey("myEviction").orderByHistoricActivityInstanceId().asc().list();
        System.out.println(list);
    }

    @Test
    public void testFindHistoryAct() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery().processDefinitionId("myEviction:1:80004").orderByHistoricActivityInstanceId().asc().list();
        System.out.println(list);
    }
}
