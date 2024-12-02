package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface BingoService extends Remote {

     boolean saveUsername(String username) throws  RemoteException;
     int playBingo(String username, Integer value) throws RemoteException;
     void saveScore(String username, Integer score) throws  RemoteException;
     String topThreeScores() throws RemoteException;
}
