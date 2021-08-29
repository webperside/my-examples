package com.webperside;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class SecretWay implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String textfield1 = (String) delegateExecution.getVariable("textfield1");

        if(textfield1.equalsIgnoreCase("hamid")){
            throw new BpmnError("TEST","are you fucking idiot ?");
        }

        System.out.println("this is fucking variable you typed : " + textfield1);

        textfield1 = textfield1 + " from Java class. Marvelous";

        delegateExecution.setVariable("textfield1",textfield1);
    }
}
