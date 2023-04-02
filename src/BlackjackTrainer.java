import java.util.ArrayList;
public class BlackjackTrainer
{
    private String [][] hardHands;
    private String [][] softHands;
    private String [][] pairedHands;
    public BlackjackTrainer()
    {
        hardHands = new String[][] {
                //   2     3    4    5    6    7    8    9   10    A

                {"h", "h", "h", "h", "h", "h", "h", "h", "h", "h"},  // 5
                {"h", "h", "h", "h", "h", "h", "h", "h", "h", "h"},  // 6
                {"h", "h", "h", "h", "h", "h", "h", "h", "h", "h"},  // 7
                {"h", "h", "h", "h", "h", "h", "h", "h", "h", "h"},	 // 8
                {"h", " ", " ", " ", " ", "h", "h", "h", "h", "h"}, // 9
                {" ", " ", " ", " ", " ", " ", " ", " ", "h", "h"}, // 10
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", "h"}, // 11
                {"h", "h", "s", "s", "s", "h", "h", "h", "h", "h"},  // 12
                {"s", "s", "s", "s", "s", "h", "h", "h", "h", "h"},  // 13
                {"s", "s", "s", "s", "s", "h", "h", "h", "h", "h"},	 // 14
                {"s", "s", "s", "s", "s", "h", "h", "h", "h", "h"},  // 15
                {"s", "s", "s", "s", "s", "h", "h", "h", "h", "h"},  // 16
                {"s", "s", "s", "s", "s", "s", "s", "s", "s", "s"},  // 17
                {"s", "s", "s", "s", "s", "s", "s", "s", "s", "s"},  // 18
                {"s", "s", "s", "s", "s", "s", "s", "s", "s", "s"},  // 19
                {"s", "s", "s", "s", "s", "s", "s", "s", "s", "s"},  // 20
                {"s", "s", "s", "s", "s", "s", "s", "s", "s", "s"},  // 21
        };
        softHands = new String[][] {
                //   2     3    4    5    6    7    8    9   10    A

                {"h", "h", "h", " ", " ", "h", "h", "h", "h", "h"},  	// 13
                {"h", "h", "h", " ", " ", "h", "h", "h", "h", "h"},  	// 14
                {"h", "h", " ", " ", " ", "h", "h", "h", "h", "h"},	// 15
                {"h", "h", " ", " ", " ", "h", "h", "h", "h", "h"},	// 16
                {"h", " ", " ", " ", " ", "h", "h", "h", "h", "h"}, // 17
                {"s", " ", " ", " ", " ", "s", "s", "h", "h", "h"}, // 18
                {"s", "s", "s", "s", "s", "s", "s", "s", "s", "s"},    	// 19
                {"s", "s", "s", "s", "s", "s", "s", "s", "s", "s"},  	// 20
                {"s", "s", "s", "s", "s", "s", "s", "s", "s", "s"}, 	// 21
        };
        pairedHands = new String[][] {
                //   2     3    4    5    6    7    8    9   10    A

                {" ", " ", " ", " ", " ", " ", "h", "h", "h", "h"},  		// 2, 2
                {" ", " ", " ", " ", " ", " ", "h", "h", "h", "h"},  		// 3, 3
                {" ", " ", " ", " ", " ", "h", "h", "h", "h", "h"},			// 4, 4
                {" ", " ", " ", " ", " ", " ", " ", " ", "h", "h"},	// 5, 5
                {" ", " ", " ", " ", " ", "h", "h", "h", "h", "h"}, 		// 6, 6
                {" ", " ", " ", " ", " ", " ", "h", "h", "h", "h"}, 		// 7, 7
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},    		// 8, 8
                {" ", " ", " ", " ", " ", "s", " ", " ", "s", "s"},  		// 9, 9
                {"s", "s", "s", "s", "s", "s", "s", "s", "s", "s"}, 		// 10, 10
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},    		// A, A

        };
    }



    boolean isSoft(Hand hand)
    {
        ArrayList<Card> cards = hand.getCards();
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

        return (numofAces >= 1 && value <= 11);
    }

    boolean isPair(Hand hand)
    {
        ArrayList<Card> cards = hand.getCards();

        // [J Q] is considered a pair

        return (cards.get(0).getOriginalValue() == cards.get(1).getOriginalValue()) && cards.size() == 2;

    }

    public String getCorrectPlay(Hand playerHand, Card dealerCard)
    {
        int rowIndex, columnIndex;

        rowIndex = playerHand.getValue() - 5;
        columnIndex = dealerCard.getOriginalValue() - 2;

        if(dealerCard.getOriginalValue() > 10) // face card
        {
            columnIndex = 8;
        }
        else if(dealerCard.getOriginalValue() == 1) // ace
        {
            columnIndex = 9;
        }


        String play;

        if (isPair (playerHand))
        {
            rowIndex += 2;
            play = pairedHands[rowIndex][columnIndex];
        }
        else if (isSoft (playerHand))
        {
            rowIndex -= 8;
            play = softHands[rowIndex][columnIndex];
        }
        else
        {
            play = hardHands[rowIndex][columnIndex];
        }

        if(play.length() > 1)
        {
            if (playerHand.getNumCards() == 2)
            {
                return play.substring(0, 1);
            }
            else
            {
                return play.substring(1, 2);
            }
        }
        else
        {
            return play;
        }
    }
}
