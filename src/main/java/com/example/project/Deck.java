package com.example.project;
import java.util.ArrayList;
import java.util.Collections;

public class Deck
{
    private ArrayList<Card> cards;

    public Deck()
    {
        cards = new ArrayList<>();
        initializeDeck();
        shuffleDeck();
    }

    public ArrayList<Card> getCards()
    {
        return cards;
    }

    public  void initializeDeck()
    {
        for (String suit : Utility.getSuits())
        {
            for (String rank : Utility.getRanks())
            {
                cards.add(new Card(rank, suit));
            }
        }
    }

    public void shuffleDeck()
    {
        Collections.shuffle(cards); 
    }

    public Card drawCard()
    {
        return (isEmpty()) ? null : cards.removeFirst();
    }

    public  boolean isEmpty()
    {
        return cards.isEmpty();
    }
}