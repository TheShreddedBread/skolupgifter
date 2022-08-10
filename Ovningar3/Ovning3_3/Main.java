import java.util.Random;
import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Random rand = new Random();
    Scanner read = new Scanner(System.in);

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

      } else if (compCardValue > 21) {
        resaultSvar = "Datorn bustade och du vann.";

      } else if (userCardValue > compCardValue) {
        resaultSvar = "Du vann.";

      } else if (userCardValue < compCardValue) {
        resaultSvar = "Datorn vann.";

      } else if (userCardValue == compCardValue) {
        resaultSvar = "Ingen vann.";

      }

      System.out.println("Datorn fick kort med värde " + compCardValue + " och dina kort fick ett värde på " + userCardValue + ". \nDetta innebär att: \n" + resaultSvar);

    }
  }
}