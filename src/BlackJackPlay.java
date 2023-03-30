import java.util.Scanner;
public class BlackJackPlay
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        double fund;
        while(true)
        {
            System.out.println("Enter the amount of money you would like to deposit at the table: (USD)");

            fund = Double.parseDouble(input.next());
            while (fund <= 0.0)
            {
                System.out.println("Invalid, try again:");
                fund = Double.parseDouble(input.next());
            }

            break;
        }

        BlackjackUI bjui = new BlackjackUI(fund);
        bjui.playHandsUntilQuit();

        input.close();
    }
}



