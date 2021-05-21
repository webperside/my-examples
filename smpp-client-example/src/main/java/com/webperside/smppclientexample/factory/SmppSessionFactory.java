package com.webperside.smppclientexample.factory;

import com.cloudhopper.smpp.SmppSession;
import com.cloudhopper.smpp.SmppSessionConfiguration;
import com.cloudhopper.smpp.impl.DefaultSmppClient;
import com.cloudhopper.smpp.pdu.EnquireLink;
import com.cloudhopper.smpp.type.RecoverablePduException;
import com.cloudhopper.smpp.type.SmppChannelException;
import com.cloudhopper.smpp.type.SmppTimeoutException;
import com.cloudhopper.smpp.type.UnrecoverablePduException;
import com.cloudhopper.smpp.util.SmppSessionUtil;
import com.webperside.smppclientexample.handler.ClientSmppSessionHandler;
import com.webperside.smppclientexample.config.AppConfig;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Setter
@Log4j2
public class SmppSessionFactory {

    @Getter
    private ClientSmppSessionHandler clientSmppSessionHandler;
    private DefaultSmppClient defaultSmppClient;
    private SmppSession session = null;
    private SmppSessionConfiguration smppSessionConfiguration;

    public SmppSessionFactory(ClientSmppSessionHandler clientSmppSessionHandler, SmppSessionConfiguration smppSessionConfiguration) {
        this.clientSmppSessionHandler = clientSmppSessionHandler;
        this.smppSessionConfiguration = smppSessionConfiguration;
    }

//    @PostConstruct //todo
    public SmppSession getSession() throws SmppTimeoutException, UnrecoverablePduException, SmppChannelException, InterruptedException {
        if(session == null || session.isClosed() || !session.isBound()){
            if(defaultSmppClient == null) defaultSmppClient = AppConfig.defaultSmppClientBean();
            session = defaultSmppClient.bind(smppSessionConfiguration, clientSmppSessionHandler);
        }

        return session;
    }

    public void destroySession(){
        SmppSessionUtil.close(session);
        session = null;
    }

    public void unbindAndClose(){
        session.unbind(2000);
        SmppSessionUtil.close(session);
        session = null;
    }

    public void enquireLink(){
        final String START_TEMPLATE = "%s - enquireLink.start ==> session : %s";
        final String SMPP_TIME_OUT = "### Smpp Time Out Exception occurred. Destroying and Unbinding old session";
        final String SMPP_CHANNEL = "### Smpp Channel Exception occurred. Destroying old session";
        final long timeoutInMillis = 15 * 1000;

        try{
            log.info(String.format(START_TEMPLATE,
                    smppSessionConfiguration.getSystemId(),
                    session)
            );

            getSession().enquireLink(new EnquireLink(), timeoutInMillis);
        } catch (SmppTimeoutException e) {
            log.error(SMPP_TIME_OUT, e);
            destroySession();
        } catch (SmppChannelException e) {
            log.error(SMPP_CHANNEL,e);
            destroySession();
        } catch (RecoverablePduException | UnrecoverablePduException | InterruptedException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

    }


}
