CREATE DATABASE ImkerAG;
GO
USE ImkerAG
CREATE TABLE BEEHIVES
(
    ID            int PRIMARY KEY IDENTITY (1,1),
    WEIGHT        int,
    TEMPERATURE   int,
    POPULATION_NR int,
    ACTIVITY      nchar(50)
);
GO