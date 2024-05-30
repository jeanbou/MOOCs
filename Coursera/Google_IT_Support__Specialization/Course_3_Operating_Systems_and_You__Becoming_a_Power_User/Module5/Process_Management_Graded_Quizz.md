# Google IT Support 

## Operating Systems and You: Becoming a Power User | Week-5, Graded Quizz

### Question 1

Which of the following statements are true about child processes in Windows? Select all that apply.

--
               
**A child process inherits environment variables and settings from its parent. (CORRECT)**

**A child process can be terminated by running the taskkill /pidcommand in the CLI. (CORRECT)**

**A child process can be terminated by clicking on the X button in the top right corner of the application. (CORRECT)**

$${\color{red}A \space child \space process \space is \space dependent \space on \space its \space parent \space process \space until \space the \space child \space process \space is \space terminated. \space (INCORRECT)}$$

### Question 2

When Windows boots up, what is the first non-kernel process that starts?

--

The csrss.exe process.

Powershell.

The winlogon process.

**The smss.exe process (CORRECT)**



### Question 2

In Linux, what process has the PID of 1?

--

**init (CORRECT)**

grep

less

kernel

### Question 2

When a process completes its task, what happens? Select all that apply.

--

**The process releases all the resources it was using back to the kernel. (CORRECT)**

The process goes into a suspended state.

The process releases all the resources it was using to its parent.

**The process terminates automatically (CORRECT)**



### Question 3

How do you find a process PID number in Windows Task Manager?

--

**Click the Details tab. (CORRECT)**

Select a process from the process list.

$${\color{red}Task \space Manager \space will \space not \space show \space PID \space numbers \space (you \space have \space to \space use \space the \space tasklist \space command \space or \space the \space Get-Process \space commandlet). \space (INCORRECT)}$$

Click on the Processes tab.

### Question 3

Which of the following methods can be used to get information on processes that are running in Windows? Select all that apply.

--

**Use the Windows Task Manager. (CORRECT)**

**From the PowerShell prompt, use the Get-Process commandlet. (CORRECT)**

**From the CLI, use the tasklist command. (CORRECT)**

From the CLI, use the ps command.



### Question 4

Which of the following methods will provide information on processes that are running in Linux? Select all that apply.

--

List the contents of the /etc directory.

**Use the ps -x command. (CORRECT)**

**List the contents of the /proc directory. (CORRECT)**

Use the ls command



### Question 5

What is the default action of the SIGINT signal?

--

To restart the process that is signaled

To put the process that is signaled into a sleep state

To suspend the process that is signaled

**To terminate the process that is signaled (CORRECT)**

### Question 5

Which of the following represents a signal in Linux?

--

PING

**SIGPROF (CORRECT)**

SUDO

TOUCH

### Question 5

What is the default action of the SIGINT signal?

--

To put the process that is signaled into a sleep state

To restart the process that is signaled

**To terminate the process that is signaled (CORRECT)**

To suspend the process that is signaled



### Question 6

You launch notepad.exe from a PowerShell command prompt. Later, you use Process Explorer to restart the notepad.exe process? After restart, what is the parent process of notepad.exe?

--                    	

$${\color{red}PowerShell \space (powershell.exe) \space (INCORRECT)}$$

notepad.exe

smss.exe

Process Explorer (procexp.exe)

### Question 6

Which of the following options in Process Explorer will terminate a selected process? Select all that apply.

--

**Kill Process Tree (CORRECT)**

**Kill Process (CORRECT)**

Suspend

Restart

### Question 6

Which of the following options are available in Process Explorer after right-clicking a running process in the top window pane? Select all that apply.

--

Suspend

$${\color{red}Remove \space Process \space Tree \space (INCORRECT)}$$

**Kill Process (CORRECT)**

Restart



### Question 7

In Linux, what signal puts a process into a suspended state? 

--
    
SIGTERM 

SIGKILL 

**SIGTSTP (CORRECT)**

SIGINT

### Question 7

What are the two most common ways to terminate a process in Linux at the CLI? Select two options

--

Use the end process command.

**Use the kill pidcommand. (CORRECT)**

**Use the kill -KILL pidcommand. (CORRECT)**

Use the terminate process command

### Question 7

In Linux, what signal is sent when you enter the kill pidcommand?

--

SIGINT 

SIGTSTP 

SIGKILL 

**SIGTERM (CORRECT)**



### Question 8

What happens to background apps while a foreground app is in use on iOS and Android?

--

The background apps will take turns running in the background to use less processing power.

**The OS will suspend background mobile apps. (CORRECT)**

The OS will terminate the background apps.

The background apps continue to run normally

### Question 8

In iOS and Android, what’s the first thing you should try when troubleshooting a misbehaving app?

--

Check for an app that is using a lot of battery.

Uninstall apps, one at a time, starting with the least recently used app.

$${\color{red}Close \space apps, \space one \space at \space a \space time, \space starting \space with \space the \space least \space recently \space used \space app. \space (INCORRECT)}$$

Close apps, one at a time, starting with the foreground app



### Question 9

Which of the following Powershell commands will sort processes by amount of non-paged memory, in descending order?

--
 
**Get-Process| Sort NPM -descending | Select -property ID, ProcessName (CORRECT)**

Get-Process| Sort -property ID, ProcessName

Get-Process| Sort WS -ascending | Select -property ID, ProcessName

Get-Process| Sort CPU -descending | Select -property ID, ProcessName

### Question 9

System hardware properties 

**Process information along with data about the resources that the process is consuming (CORRECT)**

**Information about particular resources on the system (like CPU, Memory, and Network usage) (CORRECT)**

Information about current time and how long your system’s been running



### Question 10

What does the top command do in Linux? Select all that apply

--

**It provides information on process CPU and memory usage. (CORRECT)**

**It provides a snapshot of total tasks currently running or idling. (CORRECT)**

**It lists the top processes that are using the most resources on a machine. (CORRECT)**

It lists the top ten largest files on the system.

### Question 10

Which of the following Linux commands lists open files and what processes are using them?

--

ps -ef

uptime

top

**lsof (CORRECT)**

### Question 10

Which of the following Linux commands shows information about the current time, how long your system's been running, how many users are logged on, and what the load average of your machine is?

--

ps -ef

lsof

**uptime (CORRECT)**

top