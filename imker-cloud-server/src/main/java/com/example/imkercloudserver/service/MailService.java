package com.example.imkercloudserver.service;
import java.util.List;
import java.util.Optional;

import com.example.imkercloudserver.exception.BusinessException;
import com.example.imkercloudserver.repository.entity.User;
import com.example.imkercloudserver.service.impl.types.EMailSubjectType;

import org.springframework.stereotype.Service;
@Service
public interface MailService {
   void sendMailToMultipleUsers(EMailSubjectType type,Optional<Number> sufix, List <User> users) throws BusinessException;

}
