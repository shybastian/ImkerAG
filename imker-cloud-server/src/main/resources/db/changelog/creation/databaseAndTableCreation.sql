CREATE TABLE BEEHIVES
(
    ID            int PRIMARY KEY IDENTITY (1,1),
    WEIGHT        int,
    TEMPERATURE   int,
    POPULATION_NR int,
    ACTIVITY      nchar(50)
);

CREATE TABLE USERS
(
    ID         int PRIMARY KEY IDENTITY (1,1),
    USERNAME   nvarchar(50),
    PASSWORD   nvarchar(200),
    FIRST_NAME nvarchar(100),
    LAST_NAME  nvarchar(100),
    EMAIL      nvarchar(50),
);

CREATE TABLE USERS_BEEHIVES
(
    FK_USER    int FOREIGN KEY REFERENCES USERS (ID),
    FK_BEEHIVE int FOREIGN KEY REFERENCES BEEHIVES (ID),
    PRIMARY KEY (FK_USER, FK_BEEHIVE)
);

CREATE TABLE NOTIFICATIONS
(
    ID                            int PRIMARY KEY IDENTITY (1,1),
    NOTIFICATION_TYPE             nvarchar(50),
    NOTIFICATION_READ_STATUS_TYPE nvarchar(50),
    MESSAGE                       nvarchar(200),
    NOTIFICATION_DATE             datetime null,
    FK_USER                       int FOREIGN KEY REFERENCES USERS (ID),
    FK_BEEHIVE                    int FOREIGN KEY REFERENCES BEEHIVES (ID)
);