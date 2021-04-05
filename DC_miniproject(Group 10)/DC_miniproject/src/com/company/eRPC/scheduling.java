package com.company.eRPC;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
public class scheduling extends UnicastRemoteObject implements Scheduling_Interface {
    scheduling() throws RemoteException {
        super();
    }
    public float fcfs(String proc[], int n, int bt[], int at[]) {
        int wt[] = new int[n], tat[] = new int[n];
        int service_time[] = new int[n];
        service_time[0] = 0;
        wt[0] = 0;

        for (int i = 1; i < n; i++) {
            service_time[i] = service_time[i - 1] + bt[i - 1];
            wt[i] = service_time[i] - at[i];
            if (wt[i] < 0)
                wt[i] = 0;
        }

        for (int i = 0; i < n; i++)
            tat[i] = bt[i] + wt[i];
//        findWaitingTime(proc, n, bt, wt, at);
//        findTurnAroundTime(proc, n, bt, wt, tat);

        System.out.print("Processes " + " Arrival Time " + " Burst Time " + " Waiting Time " + " Turn Around Time \n");
        int total_wt = 0, total_tat = 0;
        for (int i = 0; i < n; i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            System.out.println(proc[i] + "\t\t\t" + at[i] + "\t\t\t\t" + bt[i] + "\t\t\t\t" + wt[i] + "\t\t\t\t " + tat[i]);
        }
        System.out.println("Average waiting time = " + (float) total_wt / n);
        System.out.println("Average turn around time = " + (float) total_tat / n);
        return (float) total_wt / n;
    }

    public float roundRobin(String pid[], int num, int b[], int a[], int n) {
        int res = 0;
        int resc = 0;
        int res_x[] = new int[num];
        int res_y[] = new int[num];

        for (int i = 0; i < num; i++) {
            res_y[i] = b[i];
            res_x[i] = a[i];
        }

        int t = 0;
        int w[] = new int[num];
        int ttime[] = new int[num];

        while (true) {
            boolean flag = true;
            for (int i = 0; i < num; i++) {
                if (res_x[i] <= t) {
                    if (res_x[i] <= n && res_y[i] > 0) {
                        flag = false;
                        if (res_y[i] > n) {
                            t = t + n;
                            res_y[i] = res_y[i] - n;
                            res_x[i] = res_x[i] + n;
                        }
                        else {
                            t = t + res_y[i];
                            ttime[i] = t - a[i];

                            w[i] = t - b[i] - a[i];
                            res_y[i] = 0;
                        }
                    }
                    else if (res_x[i] > n) {
                        for (int j = 0; j < num; j++) {
                            if (res_x[j] < res_x[i] && res_y[j] > 0) {
                                flag = false;
                                if (res_y[j] > n) {
                                    t = t + n;
                                    res_y[j] = res_y[j] - n;
                                    res_x[j] = res_x[j] + n;
                                } else {
                                    t = t + res_y[j];
                                    ttime[j] = t - a[j];
                                    w[j] = t - b[j] - a[j];
                                    res_y[j] = 0;
                                }
                            }
                        }

                        if (res_y[i] > 0) {
                            flag = false;
                            if (res_y[i] > n) {
                                t = t + n;
                                res_y[i] = res_y[i] - n;
                                res_x[i] = res_x[i] + n;
                            }
                            else {
                                t = t + res_y[i];
                                ttime[i] = t - a[i];
                                w[i] = t - b[i] - a[i];
                                res_y[i] = 0;
                            }
                        }
                    }
                } else if (res_x[i] > t) {
                    t++;
                    i--;
                }
            }

            if (flag)
                break;
        }

        System.out.println("Processes " + " Arrival Time " + " Burst Time " + " Waiting Time " + " Turn Around Time");
        for (int i = 0; i < num; i++) {
            System.out.println(pid[i] + "\t\t\t" + a[i] + "\t\t\t\t" + b[i] + "\t\t\t\t" + w[i] + "\t\t\t\t" + ttime[i]);

            res = res + w[i];
            resc = resc + ttime[i];
        }

        System.out.println("Average waiting time = " + (float) res / num);
        System.out.println("Average turn around time = " + (float) resc / num);
        return (float) res / num;
    }
    public float sjf(String pid[], int n, int bt[], int art[]) {
        int wt[] = new int[n], tat[] = new int[n];
        int total_wt = 0, total_tat = 0;
        int rt[] = new int[n];

        for (int i = 0; i < n; i++)
            rt[i] = bt[i];

        int done = 0, t = 0, minm = Integer.MAX_VALUE;
        int min = 0, time;
        boolean check = false;

        while (done != n) {
            for (int j = 0; j < n; j++) {
                if ((art[j] <= t) && (rt[j] < minm) && rt[j] > 0) {
                    minm = rt[j];
                    min = j;
                    check = true;
                }
            }

            if (check == false) {
                t++;
                continue;
            }

            rt[min]--;
            minm = rt[min];
            if (minm == 0)
                minm = Integer.MAX_VALUE;

            if (rt[min] == 0) {
                done++;
                check = false;
                time = t + 1;
                wt[min] = time - bt[min] - art[min];
                if (wt[min] < 0)
                    wt[min] = 0;
            }
            t++;
        }
        for (int i = 0; i < n; i++)
            tat[i] = bt[i] + wt[i];
//		findWaitingTime(pid, bt, art, n, wt);
//		findTurnAroundTime(pid, bt, art, n, wt, tat);

        System.out.println("Processes " + "Arrival Time " + " Burst Time " + " Waiting Time " + " Turn Around Time");
        for (int i = 0; i < n; i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            System.out.println(pid[i] + "\t\t\t" + art[i] + "\t\t\t\t " + bt[i] + "\t\t\t\t" + wt[i] + "\t\t\t\t" + tat[i]);
        }
        System.out.println("Average waiting time = " + (float) total_wt / n);
        System.out.println("Average turn around time = " + (float) total_tat / n);
        return (float) total_wt / n;
    }
}
