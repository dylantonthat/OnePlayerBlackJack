import java.text.NumberFormat;
import java.util.Scanner;


/**
 * A text based user interface that allows the user to
 * play a game of blackjack.
 */
public class BlackjackUI
{
    private BlackjackFunctions bj;
    private BlackjackTrainer bjtrainer;
    private Scanner fromKeyboard;
    private NumberFormat nf;

    /**
     * Constructs a blackjack game with $1,000 in player bankroll
     */
    public BlackjackUI(double f)
    {
        bj = new BlackjackFunctions (f);
        bjtrainer = new BlackjackTrainer();
        fromKeyboard = new Scanner(System.in);
    }

    /**
     * Returns a valid numerical bet obtained from the player
     * @return a valid numerical bet obtained from the player
     */

    private double getValidBet()
    {
        double playersBet;

        while(true)
        {
            System.out.println("Enter a valid bet:");

            try
            {
                playersBet = Double.parseDouble(fromKeyboard.next());
                if (playersBet <= 0.0 || bj.getPlayersMoney() - playersBet < 0.0)
                {
                    return (getValidBet());

                }
                break;
            }

            catch(NumberFormatException ignore)
            {
                System.out.println("Invalid bet, try again.");
            }


        }
        return playersBet;
    }
    /**
     * Plays a single hand of blackjack
     */
    public void playHand()
    {
        double bet = getValidBet();

        bj.placeBetAndDealCards(bet);
        System.out.println("bet: $" + bj.getPlayersBet() + "0\n");
        System.out.println("dealer's card: " + bj.getDealersHand().getCards().get(0));
        System.out.println("player's hand: " + bj.getPlayersHand());


        boolean canHitAgain = bj.canHit();
        while (canHitAgain)
        {
            String recommend = bjtrainer.getCorrectPlay(bj.getPlayersHand(), bj.getDealersHand().getCards().get(0));
            
            if (!recommend.equals(" "))
                System.out.println("Recommended move: " + recommend + "\n");

            System.out.println("Do you want to hit, double, or stand? \n");

            String answer = fromKeyboard.next();



            if (answer.equals("hit") || answer.equals("h"))
            {
                bj.hit();
                System.out.println(bj.getPlayersHand());
                if (bj.getPlayersHand().getValue() >= 21)
                {
                    System.out.println("You busted your hand.");
                    canHitAgain = false;
                }
            }
            else if (answer.equals("double") || answer.equals("d"))
            {
                if (bj.canDouble() == true)
                {
                    bj.doubleBet();
                    System.out.println(bj.getPlayersHand());
                    canHitAgain = false;
                }
            }
            else if (answer.equals("stand") || answer.equals("s"))
            {
                canHitAgain = false;
            }
        }
        bj.playDealersHand();
        System.out.println("dealer's hand: " + bj.getDealersHand());

        displayResult();
        bj.resolveBetsAndReset();

        System.out.println("player's money: $" + bj.getPlayersMoney());
    }

    /**
     * Displays the result of the hand (push, player win, player blackjack or loss)
     */
    private void displayResult()
    {
        if (bj.isPush() == true)
        {
            System.out.println("push, so your bet is returned. \n");
        }
        else if (bj.isPlayerWin() == true)
        {
            if (bj.isPlayerBlackjack() == true)
            {
                System.out.println("You got blackjack! \n");
            }
            else
            {
                System.out.println("You won! \n");
            }
        }
        else
        {
            System.out.println("You lost. \n");
        }
    }

    /**
     * Plays blackjack hands until the user chooses to quit
     */
    public void playHandsUntilQuit()
    {
        boolean playAgain = true;
        while (playAgain)
        {
            System.out.println("Do you want to play a hand ?");
            String yesorno = fromKeyboard.next();

            if (yesorno.equals("yes") || yesorno.equals("y"))
            {
                if (bj.getPlayersMoney() <= 0.0)
                    System.out.println("Sorry, you do not have the funds to play again. Have a good day.");
                else
                {
                    playHand();
                }
            }

            if (yesorno.equals("no") || yesorno.equals("n"))
            {
                playAgain = false;

                if (bj.getPlayersMoney() > 1000.0)
                    System.out.println("Congratulations on your winnings, play again soon!");
                else
                {
                    System.out.println("Better luck next time!");
                }
            }

        }
        fromKeyboard.close();
    }

    /**
     * Returns the numeric representation of input or -1 if input is not numeric
     * @param input the value to be converted to a number
     * @return numeric representation or -1
     */
    private double stringToNumber(String input)
    {
        try
        {
            return Double.parseDouble(input);
        }
        catch(NumberFormatException e)
        {
            return -1;
        }
    }
}
