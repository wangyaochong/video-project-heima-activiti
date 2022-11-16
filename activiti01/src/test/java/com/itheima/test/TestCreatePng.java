package com.itheima.test;

import cn.hutool.core.io.FileUtil;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;

public class TestCreatePng {


    @Test
    public void testCreatePng() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        String processDefinitionId = "1";
        // 流程定义bpmn
        InputStream processBpmn = repositoryService.getResourceAsStream(processDefinitionId, "my-simple-process.bpmn");
//    FileUtils.copyInputStreamToFile(processBpmn , new File("D:/upload/my-simple-process.bpmn"));
        FileUtil.writeFromStream(processBpmn, new File("D:/upload/my-simple-process.bpmn"));
        // 流程图png
        InputStream processPng = repositoryService.getResourceAsStream(processDefinitionId, "my-simple-process.png");
        FileUtil.writeFromStream(processBpmn, new File("D:/upload/my-simple-process.bpmn"));
    }
}
