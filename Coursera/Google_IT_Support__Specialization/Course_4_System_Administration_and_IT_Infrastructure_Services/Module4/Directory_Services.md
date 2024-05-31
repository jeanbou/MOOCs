# Google IT Support 

## System Administration and IT Infrastructure Services | Week-4, Graded Quizz

### Question 1

Directory services store information in a hierarchical structure. Which statements about Organizational Units (OUs) of a directory service hierarchy are true? (Choose all that apply)

--

**Changes can be made to one sub-OU without affecting other sub-OUs within the same parent. (CORRECT)**

Parent OUs inherit characteristics of their sub-members.

**Sub-member OUs inherit the characteristics of their parent OU. (CORRECT)**

Specific files within an OU, or container, are called "objects".

$${\color{red}You \space didn’t \space select \space all  \space the \space correct \space answers}$$

### Question 1

How are things organized in a directory server?

--

By a relational database structure

By a flat text file

By a series of nested groups

**By a hierarchical model of objects and containers (CORRECT)**



### Question 2

Which directory service software would be used exclusively on a Windows network?

--

DISP

DSP

**Active Directory (CORRECT)**

OpenLDAP

### Question 2

Which of the following are examples of Lightweight Directory Access Protocol (LDAP)-based directory server software? (Choose all that apply)

--

**OpenLDAP (CORRECT)**

ADUC

RDP

**Microsoft's Active Directory (CORRECT)**


### Question 3

Instead of assigning access for each user account individually, ________ is a more efficient and easier-to-manage approach.

--

active directory

centralized authentication

**centralized management (CORRECT)**

LDAP



### Question 4

In LDAP, what does dn stand for at the beginning of the entry?

--

Distinguished number

Domain name

**Distinguished name (CORRECT)**

Distinct name



### Question 5

Which of these is a common protocol and a type of SASL authentication?

--

SSH

SSL

**Kerberos (CORRECT)**

SFTP

### Question 5

Which of the following are ways to authenticate to an LDAP server? (Choose all that apply)

--

Simple bind

**Anonymous bind (CORRECT)**

PGP

**SASL (CORRECT)**

$${\color{red}You \space didn’t \space select \space all  \space the \space correct \space answers}$$



### Question 6

In active directory, what is the top most level in the hierarchy?

--

Top

ADAC

**Forest (CORRECT)**

Domain

### Question 6

Which of these statements about Active Directory (AD) are true? (Choose all that apply)

--

AD includes a tool called the Active Directory Authentication Center, or ADAC.

**AD is used as a central repository of group policy objects, or GPOs. (CORRECT)**

AD is incompatible with Linux, OS X, and other non-Windows hosts.

**AD can "speak" LDAP. (CORRECT)**



### Question 7

By default, Active Directory adds new computers to what group?

--
    
New Computers

Added Computers

All Computers

**Domain Computers (CORRECT)**

### Question 7

Which of these statements are true about Domain Controllers (DCs)? (Choose all that apply)

--

**Delegation can be used in Active Directory. (CORRECT)**

$${\color{red}Changes \space that \space are \space safe \space to \space be \space made \space by \space multiple \space Domain \space Controllers \space at \space once \space are \space tasked \space by \space granting \space them \space Flexible \space Single-Master \space Operations. \space (INCORRECT)}$$

The default Organizational Unit (OU), called Domain Controllers, contains all Domain Controllers in the domain.

$${\color{red}Always \space use \space the \space Domain \space Admin \space or \space Enterprise \space Admin \space for \space day-to-day \space use. \space (INCORRECT)}$$



### Question 8

Juan, a network user, sends an email to you, the IT admin of the network, stating that his account is locked because he has lost his password. Select all appropriate steps in helping Juan resolve his situation. (Choose all that apply)

--
 
Issue a temporary password.

Ask Juan questions to help him remember his password.

**Check the "User must change password at next logon" box so a new password must be created at the next logon. (CORRECT)**

**Make sure the password reset is authorized by verifying that Juan is who he says he is. (CORRECT)**

$${\color{red}You \space didn’t \space select \space all  \space the \space correct \space answers}$$ 

### Question 8

Which of these statements are true about managing through Active Directory? (Choose all that apply)

--

Domain Local, Global, and Universal are examples of group scopes.

$${\color{red}Distribution \space groups \space can \space be \space used \space to \space assign \space permission \space to \space resources. \space (INCORRECT)}$$

**The default group's Domain Users and Domain Admins are security groups. (CORRECT)**

ADAC uses PowerShell.



### Question 9

A particular computer on your network is a member of several GPOs. GPO-A has precedence set to 1. GPO-B has precedence set to 2, and GPO-C has precedence set to 3. According to the given levels of precedence, what will be the resultant set of policy (RSOP) for this machine?

--
 
GPO-A will take precedence and overwrite any conflicting settings.

$${\color{red}GPO-B \space will \space take \space precedence \space and \space overwrite \space any \space conflicting \space settings. \space (INCORRECT)}$$

GPO-C will take precedence and overwrite any conflicting settings.

The computer will default to local policy due to the confusion.

### Question 9

What is the difference between a group policy and a group policy preference?

--

Policies are reapplied every 90 minutes, and preferences are a settings template that the user can change on the computer.

$${\color{red}Preferences \space are \space reapplied \space every \space 90 \space minutes, \space and \space policies \space are \space more \space of \space a \space settings \space template. \space (INCORRECT)}$$

A policy is editable only by admins, but anyone can edit a group policy preference.

A preference is editable only by admins, but anyone can edit a policy.



### Question 10

You'd like to change the minimum password length policy in the Default Domain Policy group policy preference (GPO). What's the best way to go about doing this?

--

Manually edit config files in SYSVOL

Open ADAC and edit policy settings there

**Open the Group Policy Management Console by running gpmc.msc from the CLI (CORRECT)**

Edit the Windows Registry to change group policy settings

### Question 10

Which of the following are common reasons a group policy doesn't take effect correctly? (Choose all that apply)

--

**Replication failure may occur. (CORRECT)**

$${\color{red}The \space GPO \space may \space be \space linked \space to \space the \space OU \space that \space contains \space the \space computer. \space (INCORRECT)}$$

Fast Logon Optimization may delay GPO changes from taking effect.

**Kerberos may have issues with the UTC time on the clock. (CORRECT)**