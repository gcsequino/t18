# Interop for t18

Interoperability testing allows us to verify correct operation when connected to another team's client or server.
Each team member must verify interoperability with the client and server from another team.
Each of the different aspects of the protocol must be verified.
We will discuss these issues with the other team and create defects in GitHub for any problems found in our system.
 
### Other Teams

This table lists each student in the team, the team they verified interoperability with, and the time to complete the testing.

| Name | Team | Time |
| ---- | ---- | ---- |
| Maxwell Daley | 8 | 30 min |


### Client Problems found

We found these problems when connecting our client to another team's server.

| team | problem | github# |
| :--- |  :--- | --- |
| 8 | On connection, "Request to server failed : TypeError: Failed to fetch" and "Server config response json is invalid." |  |


### Server Problems found

We found these problems when connecting the other team's client to our server.

| team |  problem | github# |
| :--- |  :--- | --- |
| 8 | On search attempt, "Uncaught TypeError: Cannot read properties of undefined (reading 'serverUrl')" |  |
