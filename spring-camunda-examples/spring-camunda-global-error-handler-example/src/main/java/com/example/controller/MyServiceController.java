package com.example.controller;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyServiceController {

    private final RuntimeService runtimeService;

    public MyServiceController(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @GetMapping
    public Integer startProcess(){
        final ProcessInstanceWithVariables processInstanceWithVariables = runtimeService
                .createProcessInstanceByKey("my-project-process")
                .executeWithVariablesInReturn();

        System.out.println(processInstanceWithVariables.getVariables());

        return (Integer) processInstanceWithVariables.getVariables().getOrDefault("myValue2",-1);
    }

}
