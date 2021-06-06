---
title: "How To: Use"
type: description
---

1. Import ADL file into Bee-Up. The ADL file is present in the folder: "documentation/flowcharts".
2. Make sure you have installed the Server and Client properly! Click [here](./4_HowTo_Installation_Deployment.md) for
   the installation guide.
3. Run the Server: Open a command line or terminal in the 'imker-cloud-server' directory. Run the following
   command: `mvn spring-boot:run`.
4. Run the Client: Open a new command line or terminal in the 'imker-cloud-client' directory. Run the following
   command: `ng serve`. The GUI can be accessed from the following link: `http://localhost:4200/login`.
5. Run the "Initial Setup" flowchart.
6. Have fun!

**Please note!** The DMN Table is under "documentation/dmn" ! If you aim to change the file, please make sure to use the
Camunda Modeler which is available [here](https://camunda.com/download/modeler/). Also, after updating the DMN Table,
please make sure to put a copy into the following directory: "imker-cloud-server/src/main/resources".