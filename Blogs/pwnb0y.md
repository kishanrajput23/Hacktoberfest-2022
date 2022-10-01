# What is DOS Attack and how to perform it

![](https://miro.medium.com/max/720/1*W4kN_FekqMvYH3FKk2LuOw.jpeg)

## What is DOS/DDOS Attack

**A** denial-of-service attack (DoS attack) is a cyber-attack where the attacker looks to make a machine or network resource unavailable to its deliberated users by temporarily or indefinitely services of disturbing a host connected to the Internet. Denial of service is usually accomplished by flooding the targeted machine or resource with excessive requests in an attempt to overload systems and prevent some or all legitimate requests from being fulfilled.

In a distributed denial-of-service attack (DDoS attack), the incoming traffic flooding the victim originates from many different sources. A DoS or DDoS attack is analogous to a group of people crowding the entry door or gate to a shop or business, and not letting legitimate parties enter into the shop or business, disrupting normal operations.

Basically, the attacker machine either sends infinite request packets to the target machine without waiting for a reply packet form the target network or uses bots (host machines) to send request packet on the target machine. Let study more above it using given below image, here you can observe 3 Phases where Attacker machine is placed at the Top while Middle part holds Host machine which is controlled by the attacker machine and at Bottom, you can see Target machine.

From the given below image, you can observe that the attacker machine want to send an ICMP echo request packet on the target machine with help of bots so this will increase the number of attackers and the number of request packet on the target network and cause traffic Flood. Now at that time, the targeted network gets overloaded and hence leads some service down then prevents some or all legitimate requests from being fulfilled.
DDOS Attack

## DOS/DDOS Categories

- Volume Based Attack: The attack’s objective is to flood the bandwidth of the target networks by sending ICMP or UDP or TCP traffic in per bits per second.
- Protocol-Based Attack: This kind of attack focus actual target server resources by sending packets such TCP SYN flood, Ping of death or Fragmented packets            attack per second to demolish the target and make it unresponsive to other legitimate requests.
- Application Layer Attack: Rather than attempt to demolish the whole server, an attacker will focus their attack on running applications by sending request per     second, for example, attacking WordPress, Joomla web server by infinite request on apache to make it unresponsive to other legitimate requests.

### What will the Effect of Dos Attack?

As we had described that any kind of Dos attack will affect the server services to their users and clients in establishing a connection with it. Here also when we had sent infinite request packet on port 80 of target’s network then it should make HTTP service unable for legitimate users.
So now if I will explore target IP on your browser for accessing their web site as a legitimate user then you can observe that the browser is unable to connect with the server for HTTP services as shown in given below image.

![](https://miro.medium.com/max/1400/1*lo4uSJ2gpWehUzTz4h89iQ.png)

### How to Perform a DOS Attack?

  Weapons we use:-
  > OS: Kali Linux </br>
  > Tool for attack: hping3

Now suppose port 80 is open in the target’s network (192.168.1.x) for accessing its HTTP services so that you can open their website through your browser and get the information available in those web pages. So basically attacker plan to slow down the HTTP service for another user who wants to interact with the target machine through port 80 as a result server will not able to reply the other legitimate requests and this will consider a Protocol Dos attack.

An attacker can use any tool for a DOS attack but we are using Hping3 for attacking to generate traffic flood for the target’s network to slow down its HTTP service for other users.</br>
```hping3 -F --flood -p 80 192.168.1.x``` 

The above command will send endless request packets per second on port 80 of the target’s network.

We are using Hping3 for attacking to generate traffic flood for the target’s network to slow down its UDP service for other users it is considered as Volume Based Dos Attack as described above.<br>

```hping3 --udp --flood -p 80 192.168.1.x```

The above command will send endless bits packets per second on port 80 of the target’s network.

Again we are using Hping3 for attacking to generate traffic flood for the target’s network to slow down network services for other users.<br>
```hping3 -SF --flood -p 80 192.168.1.x```

The above command will send endless bits packets per second on port 80 of the target’s network.

**_And this is it. 🥂 Enjoy Hacking!_**

This article is Written by: [Vicky Aryan](https://github.com/pwnb0y)

