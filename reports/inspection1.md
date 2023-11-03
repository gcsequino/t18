# Inspection - Team *T18* 

The goal of an Inspection is to find defects.
We first identify the code we wish to inspect, determine the time we wish to meet, and determine the checklist we will use to find faults in our code during the preparation before the meeting.

|  | Details |
| ----- | ----- |
| Subject | *Database Package* |
| Meeting | *10/12, 10:00am, CS120* |
| Checklist | *checklist from product repo* |

### Roles

We note the amount of time each person spent reviewing the code in preparation for the meeting.

| Name | Preparation Time |
| Jeydn Byrd | 20 minutes |
| Billy | 15 Minutes|
| Greyson Sequino | 25 minutes |
| Justin Cao | 20 minutes |


### Problems found

We list each potential defect we found in the code during our preparation so we can discuss them in the meeting.
We add a GitHub issue for each defect that requires a modification to the system.

| file:line | problem | hi/med/low | who found | github#  |
| --- | --- | :---: | :---: | --- |
| Find.java:20 |  Code duplication. Could be fixed restructuring if statements in match() | low | Billy | bginna |
| Find.java:21 | Code duplication. Could be fixed writing the chunk of SQL in another variable | low | Billy | bginna |
