---
title: "Lösungsansatz"
type: description
---

# 2. Lösungsansatz

The Beehives, their states, population number, weight and temperature are monitored by a server. The server offers
different methods which can manipulate the attributes of the beehives.

Through the use of flowcharts, we create the beehives, either manually (with every attribute being picked by the user),
or randomized by the server. Besides that, there is a flowchart which serves as a "Chaos"-button, which completely
randomizes all the beehives' attributes. Finally, the last flowchart aims as the "trigger" of the smart-sensors within
the smart beehives. These smart sensors will check attributes such as temperature. Based on how high or how low the
temperature is within the beehives, it will either be aired out or heated. Aside from that, a second sensor is a weight
sensor. If the beehive reaches a certain weight, the server will be notified. When the server receives this information
it will send out emails to the users which are responsible for the beehive, that it is ready for harvest. Lastly, a
Decision Making Table comes into play when dishing out notifications and emails. Emails will be sent, based on the
DMN-Table.