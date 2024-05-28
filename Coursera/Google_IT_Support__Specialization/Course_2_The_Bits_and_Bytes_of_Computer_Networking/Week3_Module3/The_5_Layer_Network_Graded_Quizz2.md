#  Google IT Support 

## The Bits and Bytes of Computer Networking | Week-3, Graded quizz 2

### Question 1

Computer 1 on network A, with the IP address of 10.1.1.8, wants to send a packet to Computer 2, with the IP address of 10.1.1.10. On which network is computer 2?

--
               
Not present

**Network A (CORRECT)**
                  
Network C

Network B



### Question 2

For what purpose would computer 1 send a FF:FF:FF:FF:FF broadcast ARP message to all nodes on network A?

--

To verify the internet connection

To obtain Computer 2 MAC address

**To obtain Router Y’s MAC address (CORRECT)**

To calculate the TTL

### Question 2

Computer 1 wants to send a packet to Computer 2. Since computer 2 is not on the local network, Computer 1 checks the ARP table for the corresponding ______ that matches the gateway IP.

--

Destination MAC address

TTL value

**MAC address (CORRECT)**

Port number



### Question 3

Which layer constructs the IP datagram?

--


Application layer
 
Data layer
 
Physical Layer
 
**Network layer (CORRECT)**



### Question 4

What information is in the payload section of the TCP segments?

--

**The application layer data (CORRECT)**

Handshake

ART Table

The MAC address of Computer 1

### Question 4

What information is in the data payload of the Ethernet frame?

--

**IP datagram (CORRECT)**

ART message

Handshake

network interface



### Question 5

When constructing the Ethernet datagram to send the packet from Router Y to Router Z, what information needs to be in the destination MAC address?

--

Computer 1’s MAC address

Router Y’s MAC address

Computer 2’s MAC address

**Router Z’s MAC address (CORRECT)**

### Question 5

When constructing the Ethernet datagram to send the packet from computer 1 to its gateway (Router Y), what information needs to be in the destination MAC address?

--

$${\color{red}Computer \space 2’s \space MAC \space address \space (INCORRECT)}$$

Router Y’s MAC address

Router Z’s MAC address

Computer 1’s MAC address



### Question 6

Computer 1 on Network A sends a packet to Computer 2 on Network C. What's the last step that Router Z does after receiving the Ethernet frame?

--

$${\color{red}Strips \space away \space the \space Ethernet \space frame, \space leaving \space the \space IP \space datagram. \space Performs \space a \space checksum \space calculation \space against \space the \space entire \space datagram \space (INCORRECT)}$$ 

Decrements the TTL by 1, calculates a new checksum, and makes a new IP datagram. This new IP datagram is again encapsulated on a new Ethernet frame.

Sends back the packages to router Y for confirmation

Calculates a checksum and compares this checksum with the one in the Ethernet frame header

### Question 6

Computer 1 on Network A sends a packet to Computer 2 on Network C. What's the first step that Router Z does after receiving the Ethernet frame?

--

$${\color{red}Checks \space the \space destination \space IP \space address \space and \space changes \space it \space to \space its \space own \space (INCORRECT)}$$ 

Decrements the TTL by 1, calculates a new checksum, and makes a new IP datagram. This new IP datagram is again encapsulated on a new Ethernet frame.

Sends back the packages to router Y for confirmation

Calculates a checksum and compares this checksum with the one in the Ethernet frame header



### Question 7

Computer 1 on network C, with IP address of 172.16.1.57, wants to send a packet to Computer 2, with IP address of 172.16.1.133. If the TTL value was set to 64 at the beginning, what is the value of the TTL once it reaches its destination?

--
     
0

61

65

**64 (CORRECT)**



### Question 8

Computer 1 on network B, with IP address of 192.168.1.121, wants to send a packet to Computer 2, with IP address of 10.1.1.8. Taking in consideration that computer 1 is sending a request to a web server on computer 2, listening on port 80, and the source port on computer 1 is 5000, which of the following contains the correct information for the first TCP segment of data?

--
 
Source Port: 80
Destination Port: 5000
Sequence Number: 1
Acknowledgment Number: 1

Source Port: 80
Destination Port: 5000
Sequence Number: 1
Acknowledgment Number: 2

Source Port: 8081
Destination Port: 50
Sequence Number: 4
Acknowledgment Number: 1

**Source Port: 5000 Destination Port: 80 Sequence Number: 1 Acknowledgment Number: 2 (CORRECT)**

### Question 8

Computer 1 on network A, with IP address of 10.1.1.8, wants to send a packet to Computer 2, with IP address of 10.1.1.205. Taking in consideration that computer 1 is sending a FTP request to computer 2, and the source port on computer 1 is 21086, which of the following contains the correct information for the first TCP segment of data?

--
 
Source Port: 21
Destination Port: 21
Sequence Number: 4
Acknowledgment Number: 1

Source Port: 21086
Destination Port: 21
Sequence Number: 1
Acknowledgment Number: 2

Source Port: 5000
Destination Port: 80
Sequence Number: 1
Acknowledgment Number: 2

$${\color{red}Source \space Port: \space 80 \space Destination \space Port: \space 5000 \space Sequence \space Number: \space 1 \space Acknowledgment \space Number: \space 1 \space (INCORRECT)}$$



### Question 9

Computer 1 on network A, with IP address of 10.1.1.10, wants to send a packet to Computer 2, with IP address of 172.16.1.64. Which of the following has the correct IP datagram information for the fields: Version, minimum Header Length, Source IP, and Destination IP?

--
 
Version: 5
Header Length: 16
Source IP Address: 171.1.1.1.
Destination IP address: 172.16.1.0/24.

Version: 4
Header Length: 32
Source IP Address: 10.1.1.1
Destination IP address: 172.16.1.1

Version: 6
Header Length: 20
Source IP Address: 8a:1a:2b:3c:4d:5f
Destination IP address: 2a:2b:3c:4d:8f

**Version: 4 Header Length: 20 Source IP Address: 10.1.1.10 Destination IP address: 172.16.1.64 (CORRECT)**

### Question 9

Computer 1 on network B, with IP address of 192.168.1.233, wants to send a packet to Computer 2, with IP address of 172.16.1.133. Which of the following has the correct IP datagram information for the fields: Version, minimum Header Length, Source IP, and Destination IP?

--

Version: 6
Header Length: 20
Source IP Address: 8a:1a:2b:3c:4d:5f
Destination IP address: 2a:2b:3c:4d:8f

Version: 4
Header Length: 32
Source IP Address: 10.1.1.1
Destination IP address:172.16.1.1

Version: 4
Header Length: 20
Source IP Address: 192.168.1.233
Destination IP address: 172.16.1.133

$${\color{red}Version: \space 5 \space Header \space Length: \space 16 \space Source \space IP \space Address: \space 10.1.1.0/24. \space Destination \space IP \space address: \space 172.16.1.0/24 \space (INCORRECT)}$$



### Question 10

The Cat6 cable is part of the ______ layer. 

--

Network

**Physical (CORRECT)**

Application

Transport

### Question 10

When referring to RJ45, we are referring to the ________.

--

**cable plug (CORRECT)**

ethernet port

network identification

router velocity