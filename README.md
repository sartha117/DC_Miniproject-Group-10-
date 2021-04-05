# DC_Miniproject-Group-10-
Use of Embedded RPC based service communication for Microkernel RTOS  Architecture

We have implemented its application i.e CPU scheduling algorithms using RPC. Here, the server will act as a microkernel and it will schedule the processes and send back the waiting time to the client. 

RMI is a pure java solution to Remote Procedure Calls (RPC) and is used to create 
distributed application in java. Stub and Skeleton objects are used for communication 
between client and server side.

Remote Procedure Call (RPC) is a set of methods to communicate with two processes which may be in the same computer or different computer.
In client.java we have imported the registry which invokes the connection so that the objects in the cases are looked up and then check in the server which object rebind will bind and connection established and then function calls are executed.


The client here serves as the CPU Processes that are waiting in the job queue to be scheduled. Scheduling will be done by the server. The user will enter the processes and their respective burst and arrival time. These values will be passed to the server and the average waiting time will be calculated on the server side. We have implemented three algorithms FCFS, RoundRobin and SJF. We will get the output on the client of which method is the best out of three in terms of least average waiting time.
