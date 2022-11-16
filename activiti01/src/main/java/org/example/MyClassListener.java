package org.example;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class MyClassListener implements TaskListener {

    @Override public void notify(DelegateTask delegateTask) {
        if(delegateTask.getName().equals("创建出差申请")){
            delegateTask.setAssignee("张三-listener");
        }
        if(delegateTask.getName().equals("部门经理审批")){
            delegateTask.setAssignee("李经理-listener");
        }
        if(delegateTask.getName().equals("总经理审批")){
            delegateTask.setAssignee("王总经理-listener");
        }
        if(delegateTask.getName().equals("财务审批")){
            delegateTask.setAssignee("赵财务-listener");
        }
    }
}
