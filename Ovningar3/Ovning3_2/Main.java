import java.util.Scanner;
import java.util.Random;

class Main {
  public static void main(String[] args) {
    Scanner Read = new Scanner(System.in);
    int times = 0;

    String runAwn = "j";

    while (runAwn.toLowerCase().equals("j")) {

      times++;
      Random rand = new Random(); 
      int randomInt = rand.nextInt(100);
      randomInt++;
      int fails = 1;
      boolean contiRun = true;

      System.out.print("\nGissa på en sifra mellan 1-100: ");
      int guess = Read.nextInt();

      while (contiRun) {

        if (guess == randomInt) {
          if (fails == 1) {
            System.out.println("Bra jobbat. Svaret var faktiskt " + randomInt);
            
          } else {
            System.out.println("Bra jobbat. Svaret är " + randomInt + " vilket tog dig " + fails + " försök att knäcka");

          }
          contiRun = false;

        } else if(randomInt > guess && contiRun) {
          System.out.println("Talet är högre än vad du gissade på");

          fails++;

          guess = rand.nextInt();
        } else if (randomInt < guess && contiRun) {
          System.out.println("Talet är lägre än vad du gissade på");

          fails++;

        }
        if (contiRun) {
          System.out.print("Ny gissning: ");
          guess = Read.nextInt();
        }
      }

      System.out.println("\n\nVill du köra en runda till? (J/N)");
      String awn = Read.nextLine();
      if (awn.length() == 0) {
        awn = Read.nextLine();
      }

      runAwn = awn.toLowerCase();
      if (awn.toLowerCase().equals("j")) {
        System.out.println("\nVad roligt att du ville köra en runda till.\n");
      } else {
        if (times <= 1) {
          System.out.println("\nOkej. Roligt att du ville köra " + times + " runda");
        } else {
          System.out.println("\nOkej. Roligt att du ville köra " + times + " rundor");
        }
        break;
      }
      
    }
  }
}