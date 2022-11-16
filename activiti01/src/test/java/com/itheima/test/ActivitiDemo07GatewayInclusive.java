package com.itheima.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class ActivitiDemo07GatewayInclusive {
    @Test
    public void testDeploy() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/gateway/gateway-inclusive.bpmn")
                .addClasspathResource("bpmn/eviction.svg")
                .name("出差申请流程").deploy();

        System.out.println(deployment.getId());
        System.out.println(deployment.getName());

//        repositoryService.deleteDeployment("190001", true);
    }

    String key = "gateway_inclusive";

    @Test
    public void testStartProcess() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("num", "2");
        hashMap.put("days", "1");

        processEngine.getRuntimeService().startProcessInstanceByKey(key, hashMap);
    }

    @Test
    public void testComplete() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery().processDefinitionKey(key).list();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("num", "2");
//        hashMap.put("days", "1");
        taskService.complete(list.get(0).getId(), hashMap);

    }
}
