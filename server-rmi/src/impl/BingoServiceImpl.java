package impl;

import service.BingoService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.stream.Collectors;

public class BingoServiceImpl extends UnicastRemoteObject implements BingoService {

    Map<String, Integer> scores = new HashMap<>();
    Map<String,List<Integer>> appearanceOrderAllClients = new HashMap<>();

    public BingoServiceImpl() throws RemoteException {
        super();
    }
    private List<Integer> shuffleAppearanceOrder(String username){
        Integer[] numbers = new Integer[10];
        for (int i = 0; i < 10; i++) {
            numbers[i] = i;
        }
        List<Integer> numbersList = Arrays.asList(numbers);
        Collections.shuffle(numbersList);
        return numbersList;

    }

    @Override
    public boolean saveUsername(String username) throws RemoteException {
        if(appearanceOrderAllClients.containsKey(username)){
            return false;
        }
        else{
            appearanceOrderAllClients.put(username,shuffleAppearanceOrder(username));
            return true;
        }
    }

    @Override
    public int playBingo(String username, Integer value) throws RemoteException {

        Integer ballNumber = appearanceOrderAllClients.get(username).get(0);
        appearanceOrderAllClients.get(username).remove(0);
        return (ballNumber ==  value)?1:0;
    }

    @Override
    public void saveScore(String username, Integer score) throws RemoteException {
        scores.put(username, score);
    }

    @Override
    public List<Map.Entry<String, Integer>> topThreeScores() throws RemoteException {

        return scores.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(3)
                .toList();
    }
}
