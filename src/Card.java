/**
 * Represents a playing card
 */
public class Card
{
    private String suit;
    private int value;

    /**
     * Constructs a playing card with the specified suit and value
     * @param suit one of "D", "H", "S", "C"
     * @param value 1 - 13 corresponding to Ace, 2 - 10, Jack, Queen, King
     * @throws IllegalArgumentException if argument formats don't match
     */
    public Card(String suit, int value)
    {
        if(!suit.equals("D") && !suit.equals("H") &&
                !suit.equals("S") && !suit.equals("C"))
            throw new IllegalArgumentException("suit must be one of \"D\", \"H\", \"S\", \"C\"");

        if(! (1 <= value && value <= 13) )
            throw new IllegalArgumentException("value must be 1 - 13 (inclusive)");

        this.suit = suit;
        this.value = value;
    }

    /**
     * Returns this card's suit ("D", "H", "S" or "C")
     * @return this card's suit
     */
    public String getSuit()
    {
        return suit;
    }

    /**
     * Return this card's value (1 - 13 corresponding to Ace, 2 - 10, Jack, Queen, King)
     * @return this card's value
     */
    public int getOriginalValue()
    {
        return value;
    }

    /**
     * Returns this card with the 1 or 2 character value (A, 2-10, J, Q, K)
     * followed by the 1 character suit (D, H, S, C)
     * Examples: JD, 10H, AS, 9C
     */
    public String toString()
    {
        if (value == 1)
        {
            return "A" + suit;
        }

        else if (value == 11)
        {
            return "J" + suit;
        }

        else if (value == 12)
        {
            return "Q" + suit;
        }
        else if (value == 13)
        {
            return "K" + suit;
        }

        return value + suit;
    }
}
