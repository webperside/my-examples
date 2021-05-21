package com.webperside.smppclientexample.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {
    @Override
    public synchronized boolean saveDummy() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Saved");
        return true;
    }

    @Override
    public synchronized boolean testThread(String name) throws Exception {
        System.out.println(name + " is here");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " is finished");
        return false;
    }
}
