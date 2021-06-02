IF NOT EXISTS(SELECT *
              FROM sys.databases
              WHERE name = 'ImkerAG')
    BEGIN
        CREATE DATABASE [ImkerAG]
    END
GO