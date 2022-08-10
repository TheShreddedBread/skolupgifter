import java.util.Random;
import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    int wins = 0;
    int lost = 0;
    int rounds = 0;

    Random rand = new Random();
    Scanner read = new Scanner(System.in);


    while (true) {
      
      rounds++;


      int compCardValue = rand.nextInt(12);
      compCardValue++;

      int userCardValue = rand.nextInt(12);
      userCardValue++;

      boolean run = true;
      int runCount = 1;

      while (run) {
        runCount++;
        System.out.println("Datorn har kort med värde " + compCardValue + " och dina kort har ett värde på " + userCardValue + ".\nVill du dra ett till (J/N)?");

        String svar = read.nextLine();
        if (svar.toUpperCase().equals("J")) {
          userCardValue = userCardValue + 1 + rand.nextInt(12);
        }

        if (svar.toUpperCase().equals("N") || userCardValue > 21) {
          run = false;
        }
      }

      boolean blackjack = false;
      if (userCardValue == 21 && runCount == 2) {
        blackjack = true;
        System.out.println("Du fick blackjack vilket att du vann");

        wins++;
      }

      if (!blackjack) {

        while (compCardValue < 17) {
          compCardValue = compCardValue + 1 + rand.nextInt(12);
        }

        String resaultSvar = "";

        if (userCardValue > 21 && compCardValue > 21) {
          resaultSvar = "Ni båda bustade och ingen vann.";


        } else if (userCardValue > 21) {
          resaultSvar = "Du bustade och datorn vann.";

          lost++;

        } else if (compCardValue > 21) {
          resaultSvar = "Datorn bustade och du vann.";

          wins++;

        } else if (userCardValue > compCardValue) {
          resaultSvar = "Du vann.";

          wins++;

        } else if (userCardValue < compCardValue) {
          resaultSvar = "Datorn vann.";

          lost++;
        } else if (userCardValue == compCardValue) {
          resaultSvar = "Ingen vann.";

        }

        System.out.println("Datorn fick kort med värde " + compCardValue + " och dina kort fick ett värde på " + userCardValue + ". \nDetta innebär att: \n" + resaultSvar);

      }

      System.out.println("\n\nVill du köra en runda till? (J/N)");
      String conti = read.nextLine();
      if (conti.toUpperCase().equals("N")) {
        if (rounds >= 2) {
          System.out.println("Du van " + wins + " av " + rounds + " roundor");
        } else {
          System.out.println("Du van 1 runda men du körde dock bara en"); 
        }

        break;
      } else {
        System.out.println("\n\nDu kör vi!\n\n");
      }

    }
  }
}