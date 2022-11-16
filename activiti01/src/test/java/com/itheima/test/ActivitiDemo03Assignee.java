package com.itheima.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

import java.util.HashMap;

public class ActivitiDemo03Assignee {
    @Test
    public void test() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/eviction_uel.bpmn")
                .addClasspathResource("bpmn/eviction.svg")
                .name("出差申请流程").deploy();

        System.out.println(deployment.getId());
        System.out.println(deployment.getName());
    }
    @Test
    public void start(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("assignee0","张三");
        hashMap.put("assignee1","李经理");
        hashMap.put("assignee2","王总经理");
        hashMap.put("assignee3","赵财务");
        processEngine.getRuntimeService().startProcessInstanceByKey("myEviction-uel", hashMap);

    }
}
