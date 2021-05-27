package com.example.imkercloudserver.service.impl.types;

public enum EMailSubjectType{
    WEIGHT_TOO_HIGH("Warnung! Gewicht zu hoch!","Achtung!Gewicht zu hoch!. Das Anfangsgewicht war :"),
    TEMPERATURE_TOO_HIGH("Warnung! Temperature zu hoch!","Achtung!Temperature zu hoch!. Das Anfangstemperature war :"),
    POPULATION_TOO_HIGH("Warnung! Population zu groß!","Achtung!Population zu groß!. Das Anfangspopulation war :"),
    HYPERACTIVE("Warnung, die Bienen sind hyperaktiv!","Warnung, die Bienen sind hyperaktiv!");

    public String subject;
    public String message;

    EMailSubjectType(String subject,String message){
        this.message = message;
        this.subject = subject;
        
    }
}