package com.example.imkercloudserver.service;

import com.example.imkercloudserver.exception.BusinessException;
import com.example.imkercloudserver.repository.entity.User;
import com.example.imkercloudserver.service.impl.types.EMailSubjectType;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public interface MailService {
    void sendMailToMultipleUsers(EMailSubjectType type, Optional<Number> sufix, Set<User> users) throws BusinessException;

    void sendMailToUser(String userEmail, Long beehiveId, EMailSubjectType type) throws BusinessException;

    //void sendMailToUser(final Mail mail, final String email) throws BusinessException;
}
