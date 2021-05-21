package com.webperside.smppclientexample.handler;

import com.cloudhopper.commons.charset.CharsetUtil;
import com.cloudhopper.commons.util.StringUtil;
import com.cloudhopper.smpp.SmppConstants;
import com.cloudhopper.smpp.impl.DefaultSmppSessionHandler;
import com.cloudhopper.smpp.pdu.DeliverSm;
import com.cloudhopper.smpp.pdu.PduRequest;
import com.cloudhopper.smpp.pdu.PduResponse;
import com.webperside.smppclientexample.enums.SmppClientInfo;
import com.webperside.smppclientexample.service.SmsService;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Setter
@Log4j2
public class ClientSmppSessionHandler extends DefaultSmppSessionHandler implements Runnable{

    private SmsService smsService;
    private SmppClientInfo client;

    public ClientSmppSessionHandler(SmsService smsService, SmppClientInfo client) {
        this.smsService = smsService;
        this.client = client;
    }

    @Override
    public void firePduRequestExpired(PduRequest pduRequest) {
        final String PDU_REQUEST_EXPIRED = "Developer Message : ### PDU request expired | PUT THE MESSAGE TO INTERRUPTED QUEUE ### | " +
                "Client Name : %s | " +
                "Pdu Request : %s" ;

        log.warn(String.format(PDU_REQUEST_EXPIRED, client.getName(), pduRequest));
    }

    @Override
    public PduResponse firePduRequestReceived(PduRequest pduRequest) {
        final String FULL_INFO_TEMPLATE = "##### RECEIVED from GW(%s) { Message : %s"
                + " | Source Address : %s"
                + " | Dest. Address : %s"
                + " | Data Encoding : %s"
                + " }";

        PduResponse pduResponse = pduRequest.createResponse();

        DeliverSm deliverSm = (DeliverSm) pduRequest;
        byte[] shortMessage = deliverSm.getShortMessage();
        byte dataCoding = deliverSm.getDataCoding();
        String msg = "";

        if(dataCoding == 0){
            msg = CharsetUtil.decode(shortMessage, CharsetUtil.CHARSET_GSM);
        } else if(dataCoding == 8){
            Charset utf16 = StandardCharsets.UTF_16;
            msg = new String(shortMessage, utf16);
        } else {
            msg = StringUtil.getAsciiString(shortMessage);
        }

        String sourceAddress = deliverSm.getSourceAddress().getAddress();
        String destAddress = deliverSm.getDestAddress().getAddress();

        String fullInfo = String.format(FULL_INFO_TEMPLATE,client, msg, sourceAddress, destAddress, dataCoding);

        String incomingSms = createIncomingSms(sourceAddress, destAddress, msg); // temporary

        if(smsService.saveDummy()){
            log.info(fullInfo);
        } else {
            log.error("#Could not be saved. " + fullInfo);
            pduResponse.setCommandStatus(SmppConstants.STATUS_SYSERR);
        }

        return pduResponse;

    }
    //todo
    private String createIncomingSms(String sourceAddress, String destAddress, String message){
        return sourceAddress + " " + destAddress + " " + message;
    }

    @Override
    public void run() {
        try {
            smsService.testThread(client.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
