package com.company.eRPC;

import java.rmi.Remote;
import java.rmi.RemoteException;
public interface Scheduling_Interface extends Remote{
//    public int[] findWaitingTime(String[] proc, int n, int[] bt, int[] wt, int[] at);
//    public void findTurnAroundTime(String proc[], int n, int bt[], int wt[], int tat[]);
    public float fcfs(String proc[], int n, int bt[], int at[]) throws RemoteException;
    public float sjf(String pid[], int n, int bt[], int art[]) throws RemoteException;
    public float roundRobin(String pid[], int num, int b[], int a[], int n) throws RemoteException;
}
