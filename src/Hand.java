import java.util.ArrayList;

/**
 * Represents all or part player's or dealer's blackjack hand
 */
public class Hand
{
    private ArrayList<Card> cards;

    /**
     * Constructs a hand containing the specified 2 cards
     * @param card1 the first card
     * @param card2 the second card
     */
    public Hand(Card card1, Card card2)
    {
        cards = new ArrayList<Card>();
        cards.add(card1);
        cards.add(card2);
    }

    /**
     * Returns the numerical value of this hand according to the rules of blackjack
     * @return the numerical value of this hand
     */
    public int getValue()
    {
        int value = 0;
        int numofAces = 0;
        for (int count = 0; count < cards.size(); count ++)
        {
            if (cards.get(count).getOriginalValue() > 10)
            {
                value += 10;
            }
            else if (cards.get(count).getOriginalValue() == 1)
            {
                numofAces += 1;
                value += 1;
            }
            else
            {
                value += cards.get(count).getOriginalValue();
            }

        }

        if (numofAces >= 1 && value <= 11)
        {
            value += 10;
        }

        return value;
    }

    /**
     * Returns true if this hand is a blackjack, false otherwise
     * @return true if this hand is a blackjack, false otherwise
     */
    public boolean isBlackjack()
    {
        return cards.size() == 2 && getValue() == 21;
    }

    /**
     * Returns the cards in this hand
     * @return the cards in this hand
     */
    public ArrayList<Card> getCards()
    {
        return cards;
    }

    /**
     * Returns the cards in this hand followed by their numerical value
     * Ex: JS AH (21)
     */
    public String toString()
    {
        String result = "";

        for (int count = 0; count < cards.size(); count ++)
        {
            result += cards.get(count).toString() + " ";
        }

        result += ("(" + getValue() + ")");
        return result;

    }

    /**
     * Adds the specified card to this hand
     * @param card the card to add
     */
    public void addCard(Card card)
    {
        cards.add(card);
    }

    /**
     * Returns the number of cards in this hand
     * @return the number of cards in this hand
     */
    public int getNumCards()
    {
        return cards.size();
    }
}