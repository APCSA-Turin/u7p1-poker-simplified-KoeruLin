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
        ArrayList<Integer> suits = findSuitFrequency();
        ArrayList<Integer> ranks = findRankingFrequency();
        allCards.addAll(hand);
        allCards.addAll(communityCards);
        boolean flush = suits.contains(5) || suits.contains(6) || suits.contains(7);
        boolean straight = false;
        boolean royal = false;
        int consecutiveNum = 0;

        sortCards();

        for (int i = 0; i < ranks.size(); i++)
        {
            if (ranks.get(i) > 0)
            {
                consecutiveNum++;

                if (consecutiveNum >= 5)
                {
                    straight = true;

                    if (i == 12)
                    {
                        royal = true;
                        break;
                    }
                }
            }
            else
            {
                consecutiveNum = 0;
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
    } 

    public ArrayList<Integer> findRankingFrequency()
    {
        ArrayList<Integer> frequency = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        System.out.println(allCards.size());
        for (Card card : allCards)
        {
            int index = Utility.getRankValue(card.getRank()) - 2;
            frequency.set(index, frequency.get(index) + 1);
        }

        return frequency;
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
        Player player = new Player();
        player.addCard(new Card("10", "♠"));
        player.addCard(new Card("J", "♦"));

        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("9", "♣"));
        communityCards.add(new Card("Q", "♥"));
        communityCards.add(new Card("8", "♠"));
        
        String handResult = player.playHand(communityCards);

        System.out.println(handResult);
    }
}
