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
        allCards = new ArrayList<>();
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
        allCards.clear();
        allCards.addAll(hand);
        allCards.addAll(communityCards);
        // clears allCards of any extra cards and adds both hand and communityCards to it
        ArrayList<Integer> suits = findSuitFrequency();
        ArrayList<Integer> ranks = findRankingFrequency();
        boolean flush = suits.contains(5) || suits.contains(6) || suits.contains(7);
        boolean straight = false;
        boolean royal = false;
        int consecutiveNum = 0;

        sortCards();
        // sorts allCards

        for (int i = 0; i < ranks.size(); i++)
        {
            if (ranks.get(i) > 0)
            {
                consecutiveNum++;

                if (consecutiveNum >= 5)
                {
                    straight = true;
                    // checks for a straight
                    if (i == 12)
                    {
                        royal = true;
                        break;

                    // checks if the final card in a straight is a ace 
                    }
                }
            }
            else
            {
                consecutiveNum = 0;
                //resets counter
            }
        }

        if (royal && flush) 
            return "Royal Flush";
        if (flush && straight) 
            return "Straight Flush";
        if (ranks.contains(4)) 
            return "Four of a Kind";
        if (ranks.contains(3) && ranks.contains(2)) 
            return "Full House";
        if (ranks.contains(3)) 
            return "Three of a Kind";
        if (flush)
            return "Flush";
        if (straight)
            return "Straight";
        if (ranks.contains(3))
            return "Three of a Kind";
        if (Collections.frequency(ranks, 2) >= 2)
            return "Two Pair";
        if (ranks.contains(2))
            return "A Pair";
        if (communityCards.contains(allCards.get(allCards.size() - 1)))
            return "Nothing";
        return "High Card";
    }

    public void sortCards()
    {
        Collections.sort(allCards, new Comparator<Card>() 
        {
            @Override
            public int compare(Card one, Card two) 
            {
                List<String> transition = Arrays.asList(ranks);
                int result = Integer.compare(transition.indexOf(one.getRank()), transition.indexOf(two.getRank()));

                if (result == 0)
                {
                    return Integer.compare(transition.indexOf(one.getSuit()), transition.indexOf(two.getSuit()));
                }

                return result;
            }
        });
        //sorts allCards by rank and suit
        if (hand.get(0).getRank().compareTo(hand.get(1).getRank()) > 0)
        {
            Card swap = hand.set(0, hand.get(1));
            hand.set(1, swap);
        }
        //sorts hand by comparing rank
    } 

    public ArrayList<Integer> findRankingFrequency()
    {
        ArrayList<Integer> frequencyRank = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        for (int i = 0; i < allCards.size(); i++)
        {
            int index = Utility.getRankValue(allCards.get(i).getRank()) - 2;
            frequencyRank.set(index, frequencyRank.get(index) + 1);
        }
        return frequencyRank;
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
}
