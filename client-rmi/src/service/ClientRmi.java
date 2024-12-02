package service;

import java.rmi.Naming;
import java.util.Map;
import java.util.Scanner;

public class ClientRmi {
    public static void main(String[] args) {
        try {
            BingoService stub=(BingoService) Naming.lookup("rmi://localhost:1099/bingo-rmi");
            Scanner scanner = new Scanner(System.in);
            System.out.println("*************************");
            System.out.println("*** Welcome To Bingo  ***");
            System.out.println("*** Choose an option  ***");
            System.out.println("*** 1-Play Bingo      ***");
            System.out.println("*** 2-Display scores  ***");
            System.out.println("*** 3-Quit            ***");
            System.out.println("*************************");

            int choice = scanner.nextInt();
            while(choice ==2 || choice ==1){
                if(choice==1){
                    System.out.println("Please enter your username");
                    String username = scanner.next();
                    boolean right = stub.saveUsername(username);
                    while(right == false){
                        System.out.println("Please choose another username");
                        username = scanner.next();
                        right = stub.saveUsername(username);
                    }
                    int score = 0;
                    for(int i=0;i<10;i++){
                        System.out.println("Guess a Number");
                        int value = scanner.nextInt();
                        int res = stub.playBingo(username,value);
                        if(res>0)
                            System.out.println("-----Good Guess :)-----");
                        else
                            System.out.println("-----Bad  Guess :c-----");
                        score+=res;
                    }
                    System.out.println("*************************");
                    System.out.println("Your final score is "+ score);
                    stub.saveScore(username,score);

                }
                else if(choice==2){
                    System.out.println("***    Top 3 Scores   ***");
                    System.out.println(stub.topThreeScores());
                }
                System.out.println("*************************");
                System.out.println("*** Welcome To Bingo  ***");
                System.out.println("*** Choose an option  ***");
                System.out.println("*** 1-Play Bingo      ***");
                System.out.println("*** 2-Display scores  ***");
                System.out.println("*** 3-Quit            ***");
                System.out.println("*************************");
                choice = scanner.nextInt();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
