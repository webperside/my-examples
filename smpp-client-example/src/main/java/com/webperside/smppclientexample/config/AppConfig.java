package com.webperside.smppclientexample.config;

import com.cloudhopper.smpp.SmppBindType;
import com.cloudhopper.smpp.SmppSessionConfiguration;
import com.cloudhopper.smpp.impl.DefaultSmppClient;
import com.webperside.smppclientexample.factory.CustomThreadFactory;
import com.webperside.smppclientexample.factory.SmppSessionFactory;
import com.webperside.smppclientexample.enums.SmppClientInfo;
import com.webperside.smppclientexample.handler.ClientSmppSessionHandler;
import com.webperside.smppclientexample.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final Environment environment;
    private final SmsService smsService;

    // keywords
    private final String prefix = "smpp.client.";

    @Bean(name = "session-factory-1111")
    public SmppSessionFactory smppSessionFactory5555Bean(){
        final String key = prefix + "1111.";

        SmppSessionConfiguration smppSessionConfiguration = createSmppSessionConfigurationByKey(key);
        ClientSmppSessionHandler clientSmppSessionHandler = createSpecificHandler(SmppClientInfo.CLIENT_5555);

        return new SmppSessionFactory(clientSmppSessionHandler, smppSessionConfiguration);
    }

    @Bean(name = "session-factory-2222")
    public SmppSessionFactory smppSessionFactory6070Bean(){
        final String key = prefix + "2222.";

        SmppSessionConfiguration smppSessionConfiguration = createSmppSessionConfigurationByKey(key);
        ClientSmppSessionHandler clientSmppSessionHandler = createSpecificHandler(SmppClientInfo.CLIENT_6070);

        return new SmppSessionFactory(clientSmppSessionHandler, smppSessionConfiguration);
    }

    private SmppSessionConfiguration createSmppSessionConfigurationByKey(final String key){
        final String name = "Tester.Session.TRANSCEIVER";
        final String bindType = "bindType";
        final String host = "host";
        final String port = "port";
        final String systemId = "systemId";
        final String password = "password";
        final String systemType = "systemType";

        SmppSessionConfiguration smppSessionConfiguration = new SmppSessionConfiguration();
        smppSessionConfiguration.setWindowSize(1);
        smppSessionConfiguration.setName(name);
        smppSessionConfiguration.setType(getBindType(environment.getProperty(key + bindType)));
        smppSessionConfiguration.setHost(environment.getProperty(key + host));
        smppSessionConfiguration.setPort(Integer.parseInt(Objects.requireNonNull(environment.getProperty(key + port))));
        smppSessionConfiguration.setConnectTimeout(10000); // for testing 10000
        smppSessionConfiguration.setSystemId(environment.getProperty(key + systemId));
        smppSessionConfiguration.setPassword(environment.getProperty(key + password));
        smppSessionConfiguration.setSystemType(environment.getProperty(key + systemType));
        smppSessionConfiguration.getLoggingOptions().setLogBytes(false);
        smppSessionConfiguration.setRequestExpiryTimeout(30000);
        smppSessionConfiguration.setWindowMonitorInterval(15000);
        smppSessionConfiguration.setCountersEnabled(true);

        return smppSessionConfiguration;
    }

    private ClientSmppSessionHandler createSpecificHandler(SmppClientInfo client){
        return new ClientSmppSessionHandler(smsService, client);
    }

    private SmppBindType getBindType(String value){
        for(SmppBindType type : SmppBindType.values()){
            if(type.name().equals(value)){
                return type;
            }
        }
        return null;
    }

    public static DefaultSmppClient defaultSmppClientBean(){
        CustomThreadFactory customThreadFactory = new CustomThreadFactory();

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1,customThreadFactory);

        DefaultSmppClient client = new DefaultSmppClient(
                cachedThreadPool,
                1,
                scheduledThreadPool
        );

        cachedThreadPool.shutdown();
        scheduledThreadPool.shutdown();

        return client;
    }

}
