package net.game;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main( String[] args ) {

        Scanner keyboard = new Scanner(System.in);
        Scanner keyboard2 = new Scanner(System.in);
        System.out.println("Snakes & ladders v1.0.0");

        // BoardA gameBoard = new BoardA();
        BoardB gameBoard = new BoardB();

        System.out.println("Enter The Number Of Players");
        int noOfPlayers = keyboard.nextInt();
        int pointer = 0;

        while (pointer < noOfPlayers){
            while (true){
                System.out.print("Enter Player  Name:\n>");
                String name = keyboard2.nextLine();
                if (name != ""){
                    if (gameBoard.accept(new Player(name),noOfPlayers)){
                        pointer++;
                    }else{
                        break;
                    }
                }
                if (pointer == noOfPlayers){
                    break;
                }
            }
        }
        System.out.println(pointer);
        gameBoard.start();
    }
}

class BoardA{

    ArrayList<Player> playerList = new ArrayList<Player>();
    boolean winner = false;

    boolean accept(Player p, int numberOfPlayers){
        if (this.playerList.size() < numberOfPlayers){
            this.playerList.add(p);
            return true;
        }
        return false;
    }

    void start(){
        while(!this.winner){
            for (int i=0; i<playerList.size();i++){
                if (!winner){
                    simulate(playerList.get(i));
                }
                if (i == playerList.size()){
                    i=0;
                }
            }
        }
    }

    void simulate(Player p){
        p.move(p.rollDice());
        checkPosition(p);
    }

    void checkPosition(Player p){
        System.out.println(p.name+" is now at "+p.getPosition());
        switch (p.getPosition()){
            case 1:
                ladder(p, 38);
                break;
            case 4:
                ladder(p,14);
                break;
            case 8:
                ladder(p, 30);
                break;
            case 21:
                ladder(p,42);
                break;
            case 28:
                ladder(p, 78);
                break;
            case 32:
                snake(p,10);
                break;
            case 48:
                snake(p,26);
                break;
            case 50:
                ladder(p,67);
                break;
            case 62:
                snake(p,18);
                break;
            case 71:
                ladder(p,92);
                break;
            case 80:
                ladder(p,99);
                break;
            case 88:
                snake(p,24);
                break;
            case 95:
                snake(p,56);
                break;
            case 97:
                snake(p,78);
                break;
            case 100:
                System.out.println("Board A has a winner... \nCongratulations "+p.name);
                this.winner = true;
                break;
            default:
                break;
        }
    }

    void ladder(Player p, int i){
        System.out.println(p.name+" found a ladder at position "+p.getPosition());
        p.setPosition(i);
        checkPosition(p);
    }

    void snake(Player p, int i){
        System.out.println(p.name+" landed on a snake at position "+p.getPosition());
        p.setPosition(i);
        checkPosition(p);
    }
}

class BoardB{

    ArrayList<Player> playerList = new ArrayList<Player>();
    boolean winner = false;

    boolean accept(Player p, int numberOfPlayers){
        if (this.playerList.size() < numberOfPlayers){
            this.playerList.add(p);
            return true;
        }
        return false;
    }

    void start(){
        while(!this.winner){
            for (int i=0; i<playerList.size();i++){
                if (!winner){
                    simulate(playerList.get(i));
                }
                if (i == playerList.size()){
                    i=0;
                }
            }
        }
    }

    void simulate(Player p){
        p.move(p.rollDice());
        checkPosition(p);
    }

    void checkPosition(Player p){
        System.out.println(p.name+" is now at "+p.getPosition());
        switch (p.getPosition()){
            case 9:
                ladder(p, 31);
                break;
            case 16:
                ladder(p,45);
                break;
            case 18:
                ladder(p, 64);
                break;
            case 21:
                ladder(p,42);
                break;
            case 32:
                snake(p,10);
                break;
            case 48:
                ladder(p, 66);
                break;
            case 50:
                ladder(p,93);
                break;
            case 63:
                ladder(p,81);
                break;
            case 74:
                snake(p,22);
                break;
            case 86:
                snake(p,51);
                break;
            case 99:
                snake(p,39);
                break;
            case 100:
                System.out.println("Board B has a winner... \nCongratulations "+p.name);
                this.winner = true;
                break;
            default:
                break;
        }
    }

    void ladder(Player p, int i){
        System.out.println(p.name+" found a ladder at position "+p.getPosition());
        p.setPosition(i);
        checkPosition(p);
    }

    void snake(Player p, int i){
        System.out.println(p.name+" landed on a snake at position "+p.getPosition());
        p.setPosition(i);
        checkPosition(p);
    }
}

class Player{
    String name;
    int position = -1;

    Player(String playerName){
       this.name = playerName;
    }

    int rollDice(){
        return Dice.roll();
    }

    void move(int diceRoll){
        System.out.println(this.name+" rolled a "+diceRoll);
        if (this.position < 0){
            if (diceRoll == 6){
                this.position = 0;
                System.out.println(this.name+" got on the Board A and is now at position 0");
            }
        } else if(this.position >= 0){
            int prev = this.position;
            if(prev+diceRoll <= 100){
                this.position+=diceRoll ;
                System.out.println(this.name+" moved from position "+prev+" to position "+this.position);
            }
            if (diceRoll == 6){
                move(rollDice());
            }
        }
    }

    void setPosition(int newPosition){
        this.position = newPosition;
    }

    int getPosition(){
        return this.position;
    }
}

class Dice{
    static int roll(){
        return (int)(Math.random()*6)+1;
    }
}
