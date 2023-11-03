# Interop for t18

Interoperability testing allows us to verify correct operation when connected to another team's client or server.
Each team member must verify interoperability with the client and server from another team.
Each of the different aspects of the protocol must be verified.
We will discuss these issues with the other team and create defects in GitHub for any problems found in our system.
 
### Other Teams

This table lists each student in the team, the team they verified interoperability with, and the time to complete the testing.

| Name | Team | Time |
| ---- | ---- | ---- |
| Greyson Sequino | #6 | 45 min |
| Justin Cao | #24 | 50 min |
| Billy Ginna | #27 | 30 min |
| Jeydn Byrd | #11 | 40 min |

### Client Problems found

We found these problems when connecting our client to another team's server.

| team | problem | github# |
| :--- |  :--- | --- |
| 6 | Sending random distance requests | #278 |
| 6 | Client schema lists distances as "distance" | #281 |


### Server Problems found

We found these problems when connecting the other team's client to our server.

| team |  problem | github# |
| :--- |  :--- | --- |
| 6 | distances not listed in config request | #254 |
| 27 | after verifying feature, list of featurs disappears | #284 |
| 11 | Does not recognize any other servers | #287 |
