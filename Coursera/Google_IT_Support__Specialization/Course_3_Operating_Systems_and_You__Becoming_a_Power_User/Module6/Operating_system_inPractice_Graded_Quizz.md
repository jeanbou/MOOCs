# Google IT Support 

## Operating Systems and You: Becoming a Power User | Week-6, Graded Quizz

### Question 1

Which of the following is a benefit of using SSH to connect from a Windows computer to a Linux computer? Mark all that apply.

--
               
**SSH is a secure protocol that encrypts all communication between the two computers. (CORRECT)**

**SSH is cross-platform, meaning that it can be used on both Windows and Linux computers. (CORRECT)**

**SSH allows you to remotely access the Linux computer as if you were sitting in front of it. (CORRECT)**

SSH can be used to install Windows software on the Linux computer.



### Question 2

What remote connection tool is built into the command line after PuTTY is installed on a Windows computer? 

--

PuTTYcmd

**Plink (CORRECT)**

PuTTYPwrShell

Premote

### Question 2

What happens when you type the following command in the Powershell command line: putty.exe -ssh alex-chi@104.131.122.215 22? 

--

You will establish a chat connection with someone called “auser”

**Your computer connects to an SSH located at IP address 104.131.122.215 (CORRECT)**

You will download a package called “putty.exe” from IP address 104.131.122.215

You will search for a user called “alex-chi” logged into your local area network



### Question 3

What does the PuTTY pscp.exe tool do? 

--

It creates a virtual machine on your Windows desktop.

It launches the Remote Desktop Protocol (RDP) client in Windows.

It creates a virtual private network.

**It lets you copy files to and from remote computers. (CORRECT)**

### Question 3

What does the PuTTY pscp.exe tool do?

--

It creates a virtual private network.

**It lets you copy files to and from remote computers. (CORRECT)**

It launches the Remote Desktop Protocol (RDP) client in Windows.

It creates a virtual machine on your Windows desktop.



### Question 4

What is a virtual instance?

--

A virtual machine that only runs in the CLI

**A single virtual machine (CORRECT)**

A virtual machine that has been copied

A program for creating virtual machines

### Question 4

What is one benefit of using a virtual machine vs a physical one?

--

$${\color{red}Multiple \space users \space can \space use \space one \space virtual \space machine \space (INCORRECT)}$$

Virtual machines are free to use on the cloud

It’s very simple to change virtual hardware resource allocation

Virtual machines don’t require passwords



### Question 5

Alex is trying to start an Ubuntu virtual machine for the first time. They’ve already installed Virtual Box on their Windows computer, which of the following is a good next step?

--

Restart the virtual instance

Download a Windows OS image

Download the Ubuntu OS image

**Add more physical RAM (CORRECT)** 

### Question 5

Which of the following is needed to virtualize an instance of Ubuntu the first time? (Choose all that apply)

--

A Windows host system

**An image of Ubuntu (CORRECT)**

**A physical host machine (CORRECT)**

**Software for installing an OS image (CORRECT)**



### Question 6

What event grouping can be used in the Windows Event Viewer to filter specific types of events?

--                    	

Custom Views

Sorted Logs

$${\color{red}Error \space View \space (INCORRECT)}$$

$${\color{red}Filter \space Views \space (INCORRECT)}$$



### Question 7

In Linux, which of the following log files usually contains the most comprehensive list of events?

--
    
The /var/log/dmesg file

**The /var/log/syslog file (CORRECT)**

The /var/log/uber.log file

The /var/log/kern.log file

### Question 7

In Linux, what type of messages are stored in the /var/log/dmesg file? 

--

Diagnostic messages

Direct messages

System startup messages

$${\color{red}System \space messages \space (INCORRECT)}$$



### Question 8

Which of the following commands can be used to observe your syslog in real time? 

--

rtview /var/log/syslog

**tail -f /var/log/syslog (CORRECT)**

view -rt /var/log/syslog

run -l /var/log/syslog

### Question 8

You’ve found a log file with a long list of errors in it. Where in the log would be a good place to start searching for the problem?

--

**The top (CORRECT)**

The bottom

The middle



### Question 9

What happens when you type the following command in the Linux command line: sudo dd if=/dev/sdd of=~/Desktop/USBcopy.imge bs=100M? Select all that apply. 

--
 
You will delete a file called “USBcopy.imge.”

**You will create an image file on your desktop (CORRECT)**

**You will make a copy of all data located on an sdd device. (CORRECT)**

You will create a file called “100M.”



### Question 10

What is the difference between using SCP and other methods of file transfer?

--

SCP encrypts a flash drive for transfer

**SCP uses SSH to transfer data (CORRECT)**

There is no difference

SCP is less resource intensive than an email