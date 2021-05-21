package com.webperside.smppclientexample.tasks;

import com.cloudhopper.smpp.type.SmppChannelException;
import com.cloudhopper.smpp.type.SmppTimeoutException;
import com.cloudhopper.smpp.type.UnrecoverablePduException;
import com.webperside.smppclientexample.factory.SmppSessionFactory;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    @Qualifier(value = "session-factory-1111") @NonNull
    private final SmppSessionFactory smppSessionFactory5555;

    @Qualifier(value = "session-factory-6070") @NonNull
    private final SmppSessionFactory smppSessionFactory6070;

    @Scheduled(fixedDelay = 25000)
    public void jobCheckConnection() throws SmppTimeoutException, UnrecoverablePduException, SmppChannelException, InterruptedException {
//        smppSessionFactory5555.getSession();
//        smppSessionFactory6070.getSession();
    }
}
