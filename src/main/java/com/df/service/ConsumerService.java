package com.df.service;

public interface ConsumerService {
    void retrieveMessage(String message);
    void retrieveMessageWithCallback(String message);
}
