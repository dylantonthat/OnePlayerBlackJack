/**
 * The Blackjack class allows a single player to play a game of blackjack.
 * The class tracks the player's bankroll but makes no attempt to prevent
 * a negative bankroll.
 *
 */
public class BlackjackFunctions
{
    private static final int DECKS = 6, CARDS_PER_DECK = 52;
    private static final double SHOE_PENETRATION = 0.75;

    private Shoe shoe;

    public static double playersMoney;

    private Hand playersHand;
    private double playersBet;

    private Hand dealersHand;
    private boolean dealerHasPlayed;


    /**
     * Constructs a blackjack object that is ready to play.
     * @param pM the player's starting bankroll (all values, including 0 and negative values, are permitted)
     */
    public BlackjackFunctions(double pM)
    {
        shoe = new Shoe(DECKS);
        playersMoney = pM;
        playersHand = null;
        playersBet = -1;
        dealersHand = null;
        dealerHasPlayed = false;
    }

    /**
     * Resets for another round, including reseting shoe if necessary
     */
    private void reset()
    {
        playersHand = null;
        dealersHand = null;
        playersBet = -1;
        dealerHasPlayed = false;
        if (shoe.cardsLeft() < SHOE_PENETRATION * CARDS_PER_DECK * DECKS)
        {
            shoe.reset();
        }
    }

    /**
     * Returns the player's money 
     * @return the player's money
     */
    public double getPlayersMoney()
    {
        return playersMoney;
    }

    /**
     * Returns the player's bet
     * @return the player's bet for the hand
     */
    public double getPlayersBet()
    {
        return playersBet;
    }

    /**
     * Places a bet at the start of a round. Deals cards to the player and dealer.
     * @param amount the amount to bet
     */
    public void placeBetAndDealCards(double amount)
    {
        playersBet = amount;

        Card Pc1 = shoe.dealCard();
        Card Dc1 = shoe.dealCard();
        playersHand = new Hand(Pc1, shoe.dealCard());
        dealersHand = new Hand(Dc1, shoe.dealCard());
        playersMoney -= amount;
    }

    /**
     * Returns the player's hand
     * @return the player's hand
     */
    public Hand getPlayersHand()
    {
        return playersHand;
    }

    /**
     * Returns the dealer's hand
     * @return the dealer's hand
     */
    public Hand getDealersHand()
    {
        return dealersHand;
    }

    /**
     * Returns true if the player can hit, false otherwise
     * @return true if the player can hit, false otherwise
     */
    public boolean canHit()
    {
        return (playersHand.getValue() < 21 && dealersHand.getValue() != 21);
    }

    /**
     * Deals another card to the player's hand.
     *
     * Precondition: canHit()
     */
    public void hit()
    {
        playersHand.addCard(shoe.dealCard());
    }

    public boolean canDouble()
    {
        if (playersHand.getNumCards() == 2 && canHit())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void doubleBet()
    {
        if (canDouble() == true)
        {
            playersBet *= 2;
            playersHand.addCard(shoe.dealCard());
        }
    }

    /**
     * Plays the dealer's hand.
     */
    public void playDealersHand()
    {
        while (dealersHand.getValue() < 17)
        {
            System.out.println("dealer's hand: " + getDealersHand());
            dealersHand.addCard(shoe.dealCard());
        }
        dealerHasPlayed = true;
    }

    public boolean dealerHasPlayed()
    {
        return dealerHasPlayed;
    }

    /**
     * Returns true if the player's hand is a push, false otherwise
     * @return true if the player's hand is a push, false otherwise
     */
    public boolean isPush()
    {
        if (dealersHand.isBlackjack() && playersHand.isBlackjack())
        {
            return true;
        }

        if (dealersHand.isBlackjack() || playersHand.isBlackjack())
        {
            return false;
        }

        if (playersHand.getValue() >= 21)
        {
            return false;
        }

        if (dealersHand.getValue() >= 21)
        {
            return false;
        }
        if (dealersHand.getValue() == playersHand.getValue() && dealersHand.getValue() < 21 && playersHand.getValue() < 21)
        {
            return true;
        }
        if (dealersHand.isBlackjack() && playersHand.getNumCards() >= 3 && playersHand.getValue() == 21 )
        {
            return false;
        }
        return false;
    }

    /**
     * Returns true if the player's hand is a player win, false otherwise
     * @return true if the player's hand is a player win, false otherwise
     */
    public boolean isPlayerWin()
    {
        if(isPush())
            return false;

        if(playersHand.getValue() > 21)
            return false;

        if(playersHand.isBlackjack())
            return true;

        if(dealersHand.getValue() > 21)
            return true;

        if(playersHand.getValue() > dealersHand.getValue())
            return true;

        return false;
    }

    /**
     * Returns true if the player has blackjack, false otherwise
     * @return true if the player has blackjack, false otherwise
     *
     * Precondition: isPlayerWin()
     */
    public boolean isPlayerBlackjack()
    {
        return (playersHand.isBlackjack());
    }

    /**
     * Resolves the player's bets (updates player's money based on the
     * results of the round) and resets for another round
     */
    public void resolveBetsAndReset()
    {
        if (isPlayerWin() == true)
        {

            if (isPlayerBlackjack() == true)
            {
                playersMoney += (5.0/2.0 * playersBet);
            }
            else
                playersMoney += (2*playersBet);
        }
        else
        {
            if (isPush() == true)
            {
                playersMoney += playersBet;
            }
        }

        reset();
    }
}

