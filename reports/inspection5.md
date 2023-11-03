# Inspection - Team *T18* 

The goal of an Inspection is to find defects.
We first identify the code we wish to inspect, determine the time we wish to meet, and determine the checklist we will use to find faults in our code during the preparation before the meeting.

|  | Details |
| ----- | ----- |
| Subject | *Tour.java, CalculateDistance.java* |
| Meeting | *12/1 3:30pm Teams* |
| Checklist | *checklist from product repo* |

### Roles

We note the amount of time each person spent reviewing the code in preparation for the meeting.

| Name | Preparation Time |
| ---- | ---- |
| Greyson Sequino | 15 minutes |
| Justin Cao | 30 min |
| Maxwell Daley | 30 min |
| Billy Ginna | 20 min |


### Problems found

We list each potential defect we found in the code during our preparation so we can discuss them in the meeting.
We add a GitHub issue for each defect that requires a modification to the system.

| file:line | problem | hi/med/low | who found | github#  |
| --- | --- | :---: | :---: | --- |
| Tour.java:173 | nearestNeighbor has high complexity | low | greyson | #516 |
| Tour.java:all | method names unclear | low | greyson | #517 |
| CalculateDistances.java:12 | CalculateDistance uses Arraylists, much slower than arrays | mid | billy | #521 |
