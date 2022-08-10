//High and Low Game

import java.util.Scanner;
import java.util.Random;

class Main {
  public static void main(String[] args) {
    Scanner Read = new Scanner(System.in);
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

        guess = rand.nextInt();
      } else if (randomInt < guess && contiRun) {
          System.out.println("Talet är lägre än vad du gissade på");

      }
      if (contiRun) {
        System.out.print("Ny gissning: ");
        guess = Read.nextInt();
        }
      }
      
    
  }
}