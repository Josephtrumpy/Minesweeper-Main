import java.io.*;
import java.util.*;
public class PlayMinesweeper
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        Minesweeper game = new Minesweeper();

        game.initializeBoard(8, 8, 10);

        System.out.println("Welcome to Minesweeper!");
        printBoard(game);

        while (true)
        {
            System.out.println("Enter command: r row col  (reveal) OR f row col (flag)");
            String cmd = input.next();

            int r = input.nextInt();
            int c = input.nextInt();

            if (cmd.equalsIgnoreCase("r"))
            {
                String result = game.revealCell(r, c);
                System.out.println("Reveal result: " + result);

                if (game.checkLoss())
                {
                    System.out.println("BOOM! You hit a mine. Game over.");
                    printBoard(game, true);
                    break;
                }
            }
            else if (cmd.equalsIgnoreCase("f"))
            {
                game.flagCell(r, c);
                System.out.println("Cell flagged/unflagged.");
            }

            printBoard(game);

            if (game.checkWin())
            {
                System.out.println("You win! All safe cells revealed.");
                printBoard(game, true);
                break;
            }
        }

        input.close();
    }

    
    public static void printBoard(Minesweeper game)
    {
        printBoard(game, false);
    }

    
    public static void printBoard(Minesweeper game, boolean showMines)
    {
        System.out.println("\nCurrent Board:");

        for (int r = 0; r < game.getRows(); r++)
        {
            for (int c = 0; c < game.getCols(); c++)
            {
                if (game.isRevealed(r, c))
                {
                    int count = game.countAdjacentMines(r, c);
                    System.out.print(count + " ");
                }
                else if (game.isFlagged(r, c))
                {
                    System.out.print("F ");
                }
                else if (showMines && game.checkLoss())
                {
                    
                    System.out.print("* ");
                }
                else
                {
                    System.out.print("# ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}