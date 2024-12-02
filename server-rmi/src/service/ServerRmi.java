package service;

import impl.BingoServiceImpl;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ServerRmi {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            BingoServiceImpl bingo = new BingoServiceImpl();
            Naming.rebind("rmi://localhost:1099/bingo-rmi", bingo);
            System.out.println( bingo);
            System.out.println("RMI Server Is Ready.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
