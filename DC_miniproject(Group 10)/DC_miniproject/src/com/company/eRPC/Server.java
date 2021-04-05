package com.company.eRPC;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.io.*;
import java.net.*;
public class Server {
    public static void main(String[] args) throws Exception{
        try{
            scheduling obj_FindAT,obj_FindRWT,obj_FindSWT;
            Registry reg = LocateRegistry.createRegistry(3000);
            //RoundRobin obj_FindRWT;
            //SJF obj_FindSWT;
            obj_FindAT = new scheduling();
            reg.rebind("FindAT", obj_FindAT);
            obj_FindRWT = new scheduling();
            reg.rebind("FindRWT", obj_FindRWT);
            obj_FindSWT = new scheduling();
            reg.rebind("FindSWT", obj_FindSWT);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
