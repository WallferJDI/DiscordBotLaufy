# Discord Bot Laufy
### Technical Stack
* Spring Boot 2.7.4
* MongoDB

## About
Bot for counting activity on your discord server. For reaching a certain level,
a certain role is issued. Fully customizable with admin commands. 
Bot counts messages and time spent in voice chat
 
## User Commands
* /profile 

Show your profile

> ![image](https://user-images.githubusercontent.com/72191421/206737186-231787cb-5451-4298-9065-5cf6c577e2ee.png)

* /leaderboard

Show users with highest exp
> ![image](https://user-images.githubusercontent.com/72191421/206738193-26b8ee2c-7f3f-419e-9380-d1f137345e3d.png)

# Admin Commands

/admin *
* ignore_role  -> add role to black list(wouldnt get an exp)
* delete_role_from_ignore
* set_exp
* set_lvl
* profile_user -> check user profile
* reset -> delete user profile
* set_role_level -> select which role will be given for certain lvl
* rank_role -> check table of rank roles 
* set_all_users_role -> set fot all users role that you select
