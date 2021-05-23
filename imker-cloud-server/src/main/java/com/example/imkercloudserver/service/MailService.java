package com.example.imkercloudserver.service;
import java.util.List;

import com.example.imkercloudserver.repository.entity.User;

import org.springframework.stereotype.Service;
@Service
public interface MailService {
    void SendMail(Integer weight,List <User> users);

}
