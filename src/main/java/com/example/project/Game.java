package com.example.project;
import java.util.ArrayList;
import java.util.Collections;


public class Game
{
    public static String determineWinner(Player p1, Player p2, String p1Hand, String p2Hand, ArrayList<Card> communityCards)
    {   
        int p1Score = Utility.getHandRanking(p1Hand);
        int p2Score = Utility.getHandRanking(p2Hand);

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
}