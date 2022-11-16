package com.itheima.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.example.pojo.Eviction;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class ActivitiDemo04ConditionFlow {
    @Test
    public void test() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/eviction-conditionflow.bpmn")
                .addClasspathResource("bpmn/eviction.svg")
                .name("出差申请流程").deploy();

        System.out.println(deployment.getId());
        System.out.println(deployment.getName());
    }
    @Test
    public void start(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        HashMap<String, Object> hashMap = new HashMap<>();
        Eviction value = new Eviction();
        value.setNum(4d);
        hashMap.put("eviction", value);
//        ProcessInstance conditionflow = processEngine.getRuntimeService().startProcessInstanceByKey("conditionflow", hashMap);
        List<Task> taskList = processEngine.getTaskService().createTaskQuery().processDefinitionKey("conditionflow").list();
        for (Task task : taskList) {
            processEngine.getTaskService().complete(task.getId());
        }

    }
}
