package com.example.project;
import java.util.*;


public class Player
{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player()
    {
        hand = new ArrayList<>();
    }

    public ArrayList<Card> getHand()
    {
        return hand;
    }
    public ArrayList<Card> getAllCards()
    {
        return allCards;
    }

    public void addCard(Card c)
    {
        hand.add(c);
    }

    public String playHand(ArrayList<Card> communityCards)
    {      
        return "Nothing";
    }

    public void sortCards()
    {
        Collections.sort(allCards, new Comparator<Card>() 
        {
            @Override
            public int compare(Card one, Card two) 
            {
                List<String> transition = Arrays.asList(ranks);
                return Integer.compare(transition.indexOf(one.getRank()), transition.indexOf(two.getRank()));
            }
        });
    } 

    public ArrayList<Integer> findRankingFrequency()
    {
        return new ArrayList<>(); 
        // use find lastindexOf
    }

    public ArrayList<Integer> findSuitFrequency()
    {
        ArrayList<Integer> frequency = new ArrayList<>(Arrays.asList(0, 0, 0, 0));
        for (Card card : allCards)
        {
            switch(card.getSuit())
            {
                case "♠": 
                    frequency.set(0, frequency.get(0) + 1);
                    break;
                case "♥":
                    frequency.set(1, frequency.get(1) + 1);
                    break;
                case "♣":
                    frequency.set(2, frequency.get(2) + 1);
                    break;
                default:
                    frequency.set(3, frequency.get(3) + 1);
            }
        }

        return frequency; 
    }

    @Override
    public String toString()
    {
        return hand.toString();
    }

    public static void main(String[] args)
    {

    }
}
