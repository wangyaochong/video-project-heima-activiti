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

public class ActivitiDemo05CandidateUser {
    @Test
    public void testDeploy() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/eviction_candidate_user.bpmn")
                .addClasspathResource("bpmn/eviction.svg")
                .name("出差申请流程").deploy();

        System.out.println(deployment.getId());
        System.out.println(deployment.getName());
    }

    String key = "myEviction-candidate-user";

    @Test
    public void testStartProcess() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("assignee0", "张三");
        hashMap.put("assignee1", "李经理");
        hashMap.put("assignee2", "王总经理");
        hashMap.put("assignee3", "赵财务");
        processEngine.getRuntimeService().startProcessInstanceByKey(key, hashMap);

    }

    @Test
    public void testComplete() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery().processDefinitionKey(key).taskAssignee("张三").list();
        taskService.complete(list.get(0).getId());

    }

    @Test
    public void testFindTaskByCandidate() {
        String candidateUser = "a";
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery().processDefinitionKey(key).taskCandidateUser("a").list();
        System.out.println(list);
    }

    @Test
    public void testClaimTask() {
        String candidateUser = "a";
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery().processDefinitionKey(key).taskCandidateUser(candidateUser).list();
        taskService.claim(list.get(0).getId(),candidateUser);
        System.out.println(list);
        List<Task> list2 = taskService.createTaskQuery().processDefinitionKey(key).taskAssignee(candidateUser).list();
        System.out.println(list2);


    }
}
