#  Google IT Support 

## Operating Systems and You: Becoming a Power User | Week-2, Graded Quizz

### Question 1

Generally, what type of user has access to, but limited control over a computer? 

--
               
Administrator

**Standard (CORRECT)**
                  
General

Limited

### Question 1

Does an administrator user account on a computer have complete control over a machine? 

--
               
Sometimes

No

**Yes (CORRECT)**

### Question 1

As a Windows Administrator for a large company, you are asked to grant temporary software installation permissions to the Sales department. Which of the following would be the most efficient method for accomplishing this task? 

--
               
**Add the User account for each employee in the Sales department into a special Group, then grant temporary software installation permissions to the Group. (CORRECT)**

Grant temporary Administrator permissions to each employee in the Sales department.

Grant each employee in the Sales department temporary Local Administrator permissions on their individual computers.

Grant each employee in the Sales department temporary software installation permissions on their individual User accounts.



### Question 2

Which of the following Windows tools can be used to manage group information?

--

Registry Editor

System Configuration

**Computer Management (CORRECT)**

Task Manager

### Question 2

In Windows, what does UAC stand for?

--

User Access Console

User Administration Control

User Availability Checklist

**User Account Control (CORRECT)**



### Question 3

Which Windows PowerShell CLI command can be used to list the Users on a given computer?

--

**Get-LocalUser (CORRECT)**

Get-LocalGroupMember

Get-LocalGroup

Get-GPOReport



### Question 4

On a Linux system, what is the first user that gets automatically created?

--

admin user

privileged user

user 1

**root user (CORRECT)**

### Question 4

On a Linux system, which file contains information about group memberships?

--

/etc/users

/etc/sudoers

**/etc/group (CORRECT)**

/etc/passwd



### Question 5

What parameter can be used in the Windows CLI to force a user to change their password on the next logon? 

--

/newpassword:logon

/passwordchg:yes

**/logonpasswordchg:yes (CORRECT)**

/passwordkeep:no

### Question 5

What Windows CLI command can be used to change a local user password? 

--

user command

password command

**net command (CORRECT)**

new command



### Question 6

What flag can be used in Linux with the passwd command to force a user to change their password on the next logon? 

--

**-e flag (CORRECT)**

-next flag

-new flag

-chg flag

### Question 6

What is the name of the privileged file on Linux that stores scramble passwords? 

--

**/etc/shadow (CORRECT)**

/etc/passwords

/passwords

/etc/shade



### Question 7

Which of the following methods can Administrators use to add a user in Windows? (Choose all that apply)

--

**In the GUI, under Local Users and Groups in the Computer Management tool, right click Users and select New User. (CORRECT)**

$${\color{red}At \space the \space CLI, \space using \space the \space DOS \space style \space net \space computer \space computername/new \space command. \space (INCORRECT)}$$

**At the CLI, use the DOS style net user username * /add command. (CORRECT)**

$${\color{red}With \space Powershell, \space use \space the \space Create-LocalUser \space usernamecommand. \space (INCORRECT)}$$



### Question 8

In Windows, which of the following are directory and file permissions that can be assigned to groups and/or users? (Choose all that apply)

--
 
Change

**Read & Execute (CORRECT)**

**List folder contents (CORRECT)**

**Write (CORRECT)**

### Question 8

Is it possible to have write access to a file in Windows without having read access to that same file?

-- 

Maybe

Yes

$${\color{red}No \space (INCORRECT)}$$



### Question 9

Imagine you are an IT administrator and you need to check that proper permissions are configured on a file in Linux. The file�s owner should have full permissions. The file�s associated group members should be able to read and write to the file, but not execute the file. Other users can only read the file. How should these file permissions look when you check them in the CLI environment?

--
 
-r- -rw-rwx

-rwxrw-r- -

$${\color{red}Drwxrw-r- - \space (INCORRECT)}$$

-rwerw-r- -

### Question 9

When examining the permissions on a file in Linux you find the the first four bits are -rwx. What does this mean?

--
 

It is a directory file and the owner has read, write, and exchange permissions.

It is a regular file and the owner has read, write, but no execute permissions.

$${\color{red}It \space is \space a \space directory \space file \space and \space the \space owner \space has \space read, \space write, \space and \space execute \space permissions. \space (INCORRECT)}$$

It is a regular file and the owner has read, write, and execute permissions.



### Question 10

In Windows, when setting the basic permission �Read� which of the following special permissions are enabled? (Choose all that apply)

--

**Read Attributes (CORRECT)**

**Read Permissions (CORRECT)**

Read Activity

**Read Data (CORRECT)**

### Question 10

In Windows, a simple permission is actually a larger set of ___

--

special permissions.

$${\color{red}admin \space permissions. \space (INCORRECT)}$$

user permissions.

partial permissions.

### Question 10

When using ICACL in the Windows CLI, what flag shows that a given user can create files?

--

write

S

WD

$${\color{red}CF \space (INCORRECT)}$$