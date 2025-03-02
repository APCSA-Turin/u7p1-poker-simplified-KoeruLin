package com.example.project;
import java.util.ArrayList;
import java.util.Collections;


public class Game
{
    public static String determineWinner(Player p1, Player p2, String p1Hand, String p2Hand, ArrayList<Card> communityCards)
    {
        System.out.println(p1Hand);
        System.out.println(p2Hand);
        
        int p1Score = Utility.getHandRanking(p1Hand);
        int p2Score = Utility.getHandRanking(p2Hand);

        System.out.println(p1Score);
        System.out.println(p2Score);
    
        if (p1Score > p2Score) 
        {  
            return "Player 1 wins!";
        } 
        else if (p1Score < p2Score) 
        {
            return "Player 2 wins!";
        } 

        ArrayList<Card> p1Cards = p1.getHand();
        ArrayList<Card> p2Cards = p2.getHand();

        Card p1HighestCard = p1Cards.get(p1Cards.size() - 1);
        Card p2HighestCard = p2Cards.get(p2Cards.size() - 1);
        
        System.out.println(p1HighestCard.getRank());
        System.out.println(p2HighestCard.getRank());

        int p1Highest = Utility.getRankValue(p1HighestCard.getRank());
        int p2Highest = Utility.getRankValue(p2HighestCard.getRank());

        if (p1Highest > p2Highest)
        {
            return "Player 1 wins!";
        }
        else if (p1Highest < p2Highest)
        {
            return "Player 2 wins!";
        }

        return "Tie!";
    }

    public static void play()
    { //simulate card playing
    
    }

    public static void main(String[] args)
    {
        Player player1 = new Player();
        Player player2 = new Player();
        
        player1.addCard(new Card("7", "♠"));
        player1.addCard(new Card("10", "♠"));
  
        player2.addCard(new Card("A", "♠"));
        player2.addCard(new Card("3", "♠"));

        
        // Community cards that could help form the flush
        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("J", "♠")); // Player 1 completes the flush with this card
        communityCards.add(new Card("4", "♠"));
        communityCards.add(new Card("Q", "♠"));
        
        player1.playHand(communityCards);
        player2.playHand(communityCards);
        // Player results after playing the hand
        String p1Result = player1.playHand(communityCards);
        String p2Result = player2.playHand(communityCards);
        
        // Determine the winner
        String winner = Game.determineWinner(player1, player2, p1Result, p2Result, communityCards);
        System.out.println(winner);
    }
}