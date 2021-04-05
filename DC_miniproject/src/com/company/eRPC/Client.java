package com.company.eRPC;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Client {
    public static void main(String []args) throws RemoteException, NotBoundException {
        int g = 0;
        float wt[] = new float[4];
        Scanner s = new Scanner(System.in);
        int f = 0;
        System.out.println("Enter the Number of Processes:");
        int n = s.nextInt();
        String process[] = new String[n];
        int burst_time[] = new int[n];
        int arrival_time[] = new int[n];

        System.out.println("Enter the Burst Time of the Processes :");
        for(int i = 0; i < n; i++) {
            burst_time[i] = s.nextInt();
        }

        System.out.println("Enter the Arrival Time of the Processes :");
        for (int i = 0; i < n; i++) {
            int c = i + 1;
            arrival_time[i] = s.nextInt();
            process[i] = "P" + c;
        }

        while (f < 4) {
            System.out.println("Enter the algorithm you want to implement:\n1.FCFS\n2.Round Robin\n3.SJF");
            int c = s.nextInt();
            Registry reg = LocateRegistry.getRegistry("localhost",3000);
            switch (c) {
                case 1:
                    Scheduling_Interface obj_FindAT = (Scheduling_Interface) reg.lookup("FindAT");
//                obj_FindAT.findWaitingTime(process, n, burst_time, arrival_time);
//                System.out.println("your file has been created");
                    wt[c] = obj_FindAT.fcfs(process, n, burst_time, arrival_time);
                    g++;
                    System.out.println("Waiting time of FCFS: "+wt[c]);
                    break;

            case 2:
                Scheduling_Interface obj_FindRWT = (Scheduling_Interface) reg.lookup("FindRWT");
                System.out.println("Enter the Quantum time:");
                int q = s.nextInt();
                wt[c] = obj_FindRWT.roundRobin(process, n, burst_time, arrival_time, q);
                g++;
                System.out.println("Waiting time of RoundRobin: "+wt[c]);
                break;

            case 3:
                Scheduling_Interface obj_FindSWT = (Scheduling_Interface) reg.lookup("FindSWT");
                wt[c] = obj_FindSWT.sjf(process, n, burst_time, arrival_time);
                g++;
                System.out.println("Waiting time of SJF: "+wt[c]);
                break;

                default:
                    System.out.println("Program terminated!");
                    f = 4;
            }
        }

		if (g == 3) {
        System.out.println("\nWaiting time of:");
        System.out.println("FCFS: " + wt[1]);
        System.out.println("Round Robin: " + wt[2]);
        System.out.println("SJF: " + wt[3]);

        float min = Float.MAX_VALUE;
        int fa = 5;
        for (int i = 1; i <= 3; i++) {
            if (wt[i] < min) {
                min = wt[i];
                fa = i;
            }
        }

        String ip = "";
        for (int i = 1; i <= 3; i++)
            if (wt[fa] == wt[i])
                ip += i;

        if (ip.length() == 1) {
            System.out.print("\nThe best technique is: ");
            Printing((int) ip.charAt(0) - 48, wt);
            System.out.println();
        }

        else {
            System.out.print("\nThe best techniques are: ");
            for (int i = 0; i < ip.length() - 1; i++) {
                Printing((int) ip.charAt(i) - 48, wt);
                System.out.print(", ");
            }
            System.out.print("and ");
            Printing((int) ip.charAt(ip.length() - 1) - 48, wt);
        }
    }
		else {
        System.out.println("All three methods are not executed. Execute all three to show the best technique.");
    }
}
    static void Printing(int n, float wt[]) {
        switch (n) {
            case 1:
                System.out.print("FCFS ( Average waiting time: " + wt[n] + " )");
                break;
            case 2:
                System.out.print("Round Robin ( Average waiting time: " + wt[n] + " )");
                break;
            case 3:
                System.out.print("SJF ( Average waiting time: " + wt[n] + " )");
                break;
        }
    }
    }


