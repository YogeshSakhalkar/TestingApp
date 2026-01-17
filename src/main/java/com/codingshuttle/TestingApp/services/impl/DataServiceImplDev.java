package com.codingshuttle.TestingApp.services.impl;

import com.codingshuttle.TestingApp.services.DataService;

import javax.xml.crypto.Data;

public class DataServiceImplDev implements DataService {
    @Override
    public String getData() {
        return "Dev Data";
    }
}
