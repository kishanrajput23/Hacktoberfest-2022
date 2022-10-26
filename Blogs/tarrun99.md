# Cyber Security ToolKit

3.1)	NETWORK SCANNING


3.1.1) Definition: -
Network scanning is a procedure for identifying active devices on a network by employing a feature or features in the network protocol to signal devices and await a response. During a network scan, all the active devices on the network send signals, and once the response is received, the scanner evaluates the results and checks to see if there are inconsistencies.


3.1.2) Purpose of Network Scanning: -
Network scanning is mainly used for security assessment, system maintenance, and also for performing attacks by hackers.
The purpose of network scanning is as follows:

•	Recognize available UDP and TCP network services running on the targeted hosts.
•	Recognize filtering systems between the user and the targeted hosts.
•	Determine the operating systems (OSs) in use by assessing IP responses.
•	Evaluate the target host's TCP sequence number predictability to determine sequence prediction attack and TCP spoofing.


3.1.3) Procedure


Step1: Create an ARP request directed to the broadcast MAC address asking for IP



The first step of our algorithm or the first simple problem that we need to solve is being able to create packets. So, the packet that we need to create has two main features:

a.) Use ARP to ask who has target IP.
b.) Set destination MAC to broadcast MAC
That is, to use ARP to ask who has a specific IP and then we need to direct this packet to the broadcast MAC address so that it gets delivered to all the clients on the same network.


a)	Use “pdst” whose default value is 0.0.0.0


Now set the destination mark to the broadcast MAC address to make sure that this will be sent to all the clients on the same network.
b)	Create an Ethernet frame that will be sent to the broadcast.


Combining the above two steps, i.e; a) and b)



 


Step2: - Send Packets and receive response
In this step we will try to send this packet into the network and wait for the response. So, the scapy function that we'll use to send this packet is called srp, this will send the packet that we give it and receive the response.



Step3 :- Parse the response
The next step is parsing the response that we got in step2. There is a lot of unwanted information, so to extract the useful information we will use the ‘list’ function.
And also to make it look readable and legible we modify the code




Step4:- Print result
Improving the print statement to print the results in a nice table format Trying to improve the code using a list of dictionaries 
So, the final Python program for Network scanning is

   3.1.4)  OUTPUT:

    3.1.5) Execution: 
    Command : >python3 network_scanner.py
     Output:

3.2)	VULNERABILITY SCANNER


 3.2.1) Definition:
 
Vulnerability scanner is an automated tool that identifies and creates an inventory of all IT      assets (including servers, desktops, laptops, virtual machines, containers, firewalls, switches,  and printers) connected to a network.
For each asset, it also attempts to identify operational details such as the operating system it runs and the software installed on it, along with other attributes such as open ports and user accounts. A vulnerability scanner enables organizations to monitor their networks, systems, and applications for security vulnerabilities.
3.2.2) Procedure:
Step 1:
Import all modules needed

Step 2:
Also create two arrays for storing the ports and banners and iterate through them.



Step 3:

We create the port scanner class which checks all the ports for vulnerabilities using the banner grabbing technique and will append the vulnerabilities found















Step 4:
Defining the banner class which receives banners from the target



Step 5:

We specify the target by taking input from the command line



Step 6:

We then specify the ports to be scanned by taking input from the command line



Step 7:

We finally create a function to open the vunerable_banners.txt and use it to compare banners This text file is to be updated based on the present vulnerabilities discovered.














Now our script is ready.
Step 8:
Create the vulnerable_banners.txt and append the present vulnerable Banners

Step 9:
put both the script and the vulnerability scanner script and the vulnerable_banners.txt in the same folder

Now the tool is ready for use


3.3.3) Execution and Usage :
In the directory of the tool, execute python3 portsc.py which is the vulnerability scanner



We give the target as input.


Also the number of ports to be scanned.


As there are no vulnerabilities we can see no issues.










3.3)	NIPE
3.3.1) Definition:
Nipe is a program that uses the Tor network as the user’s default gateway, routing all traffic on the Tor network, which is often used to provide privacy and anonymity. It should be emphasized that hiding an IP address alone will not provide anonymity when using a tool for privacy and anonymity, as DNS information may still be exposed. Both IP and DNS information must be hidden.
Staying anonymous is a great way to protect yourself from all kinds of surveillance. However, we only have a few options because VPNs, especially free ones, are quite ineffective. We can be tracked because a free VPN maintains logs. We can just use the TOR network instead of the browser. Tor is quite difficult to track (close to practically impossible). This Perl script allows us to quickly route all of our traffic from our computers to the Tor network, allowing us to access the Internet without fear or intimidation.

3.3.2) Procedure:
Step 1: Open a terminal and navigate to the Desktop directory (or directory of your choice). Then, using the following command, we must clone this repository from GitHub:


Step 2:  Then, using the following command, we must clone this repository from GitHub:
git clone https://github.com/htrgouvea/nipe 
Enter the following command to go to the Nipe directory:

cd nipe
  
    Step 3: Now we have to run the following command to install the libraries and dependencies:
    When prompted to perform an automatic installation, press Enter:
    sudo cpan install Try::Tiny Config::Simple JSON







   Step 4: After that, we can use the following command to install Nipe dependencies or a Perl script:
    Nipe must be run as root.
    sudo perl nipe.pl install







3.3.3) Commands:

3.3.4) Output:

To check the status of nipe, type the following command. And you will see that the current status is disabled.
sudo perl nipe.pl status













3.4)	ARP SPOOFING


3.4.1) Definition: -
What is ARP Spoofing
It is a method of gaining a man-in-the-middle situation It is a technique by which an attacker sends spoofed ARP packets (false packets) onto the network enabling the attacker to intercept, change or modify network traffic on the fly.
ARP spoofing may allow an attacker to intercept data frames on a network, modify the traffic, or stop all traffic. Often the attack is used as an opening for other attacks, such as denial of service, man in the middle, or session hijacking attacks.

3.4.2) Writing the Python Script:



1.	We need to import the necessary modules:


2.	We need to have IP forwarding Enabled and For Windows users, once you copy service.py in your current directory, you can copy-paste this function:




3.	The function below handles enabling IP routing in all platforms:




4.	We need a utility function that allows us to get the MAC address of any machine in the network:

5.	We use Scapy's srp() function that sends requests as packets and keeps listening for responses; in this case, we're sending ARP requests and listening for any ARP responses.

Second, given a target IP address and a host IP address, it changes the ARP cache of the target IP address, saying that we have the host's IP address

6.	 Once we want to stop the attack, we need to re-assign the real addresses to the target device if we don't do that, the victim will lose internet connection, and it will be evident that something happened, we will send seven legitimate ARP reply packets sequentially

7.	This was similar to the spoof () function, and the only difference is that it is sending few legitimate packets. it is sending true information.




3.4.3) OUTPUT:
 
3.5)	KEYLOGGER


3.5.1) Definition:
A keylogger, sometimes called a keystroke logger or keyboard capture, is a type of surveillance technology used to monitor and record each keystroke on a specific computer. Keylogger software is also available for use on smartphones, such as the Apple iPhone and Android devices.
Keyloggers are often used as a spyware tool by cybercriminals to steal personally identifiable information (PII), login credentials and sensitive enterprise data.

3.5.2) Procedure:
Step1: Python Libraries:
Install required Python libraries
1)	Pynput
2)	Threading
3)	Smtplib
Step -2: Script the Keylogger
1.	Import all the Libraries to the script with filename keylogger.py:
 
2.	Initialize the class and methods:




    
          key-strokes and Reporting Them by Email

3.	Logging Special Keys 







4.	Importing keylogger module to the file zlogger.py



5.	User Inputs:
Enter the duration to send the keystrokes along with email_id and password




3.5.3) Execution:
Command:	> python keylogger.py

Command:	> python zlogger.py




3.5.4) Output:
















