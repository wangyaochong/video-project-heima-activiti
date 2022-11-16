package com.itheima.test;

import org.activiti.engine.*;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

import java.util.List;

public class ActivitiDemo02AddBusinessKey {
    @Test
    public void testAddBusinessKey() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance myEviction = runtimeService.startProcessInstanceByKey("myEviction", "1001");
        System.out.println(myEviction.getBusinessKey());
        List<Execution> list = runtimeService.createExecutionQuery().processInstanceBusinessKey("1001").list();
        System.out.println(list);
    }

    @Test
    public void suspendAllProcessInstance() {//流程定义全部挂起
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        List<ProcessDefinition> myEviction = repositoryService.createProcessDefinitionQuery().processDefinitionKey("myEviction").list();
        for (ProcessDefinition processDefinition : myEviction) {
            System.out.println(processDefinition.isSuspended());
            if (processDefinition.isSuspended()) {
                repositoryService.activateProcessDefinitionById(processDefinition.getId(), true, null);
                System.out.println("流程定义id="+processDefinition.getId()+"激活");
            } else {
                repositoryService.suspendProcessDefinitionById(processDefinition.getId(), true, null);
                System.out.println("流程定义id="+processDefinition.getId()+"挂起");
            }
        }
    }
    @Test
    public void testSuspendOneInstance() {//挂起或恢复单个
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().processInstanceId("97501").list();
        for (ProcessInstance processInstance : list) {
            if (processInstance.isSuspended()) {
                runtimeService.activateProcessInstanceById(processInstance.getId());
                System.out.println("流程实例id=" + processInstance.getId() + "激活");
            }else{
                runtimeService.suspendProcessInstanceById(processInstance.getId());
                System.out.println("流程实例id=" + processInstance.getId() + "挂起");
            }
        }
    }
}
