package com.example.imkercloudserver.service.impl;

import com.example.imkercloudserver.repository.entity.Beehive;
import com.example.imkercloudserver.service.DecisionMakingService;
import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.DmnEngineConfiguration;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class DecisionMakingServiceImpl implements DecisionMakingService {
    private final DmnEngine dmnEngine;
    private static final String SEND_EMAIL_DMN = "/sendEmail.dmn";

    public DecisionMakingServiceImpl() {
        this.dmnEngine = DmnEngineConfiguration.createDefaultDmnEngineConfiguration().buildEngine();
    }

    @Override
    public boolean shouldEmailBeSent(final Beehive beehive) {
        final InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(SEND_EMAIL_DMN);
        if (inputStream != null) {
            try {
                final DmnDecision decision = this.dmnEngine.parseDecision("sendEmail", inputStream);
                final DmnDecisionTableResult result = this.dmnEngine.evaluateDecisionTable(decision, this.createVariableMap(beehive));
                System.out.println("DMN has result: " + result);
                if (result != null) {
                    if (result.getSingleResult() != null) {
                        final Boolean shouldSendEmail = result.getSingleResult().getSingleEntry();
                        System.out.println("Decision: " + shouldSendEmail);
                        return shouldSendEmail;
                    }
                }
            } finally {
                try {
                    inputStream.close();
                } catch (final IOException e) {
                    System.err.println("Could not close stream! " + e.getMessage());
                }
            }
        }
        return false;
    }

    private VariableMap createVariableMap(final Beehive beehive) {
        return Variables
                .putValue("populationNr", beehive.getPopulationNr())
                .putValue("temperature", beehive.getTemperature())
                .putValue("activity", beehive.getActivityType().toString());
    }
}
