package com.example.imkercloudserver.service.impl.types;

public enum EMailSubjectType {
    WEIGHT_TOO_HIGH("Info! Beehive ready for harvest!", "The following Beehive is ready for harvest! ID: "),
    TEMPERATURE_TOO_HIGH("Warning! Temperature too high!", "Warning! The temperature is too high in the following Beehive! ID: "),
    TEMPERATURE_TOO_LOW("Warning! Temperature too low!", "Warning! The temperature is too low in the following Beehive! ID: "),
    //POPULATION_TOO_HIGH("Warnung! Population zu groß!", "Achtung! Population zu groß!. Das Anfangspopulation war :"),
    HYPERACTIVE("Warning! The Bees are hyperactive!", "Warning! The beehives are hyperactive! ID: "),
    SLOW("Warning! The Bees are slow!", "Warning! The beehives are slow! ID!"),

    GENERAL("HiveMind: Warning!", "The following Beehive needs your attention: ");

    public String subject;
    public String message;

    EMailSubjectType(final String subject, final String message) {
        this.message = message;
        this.subject = subject;

    }
}