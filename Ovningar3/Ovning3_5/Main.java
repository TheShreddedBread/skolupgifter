import java.util.Scanner;

class Main {
  static void printAlternativ() {
    System.out.println("\n\n--- Ange siffran på den handling du vill utföra ---");
    System.out.println("1. Kolla Saldo");
    System.out.println("2. Ta Ut Pengar");
    System.out.println("3. Ta Ut Kortet");
  }

  public static void main(String[] args) {
    int saldo = 1000;
    String pin = "6454";

    int fails = 0;
    boolean loop = true;
    boolean pinLogin = true;
    boolean showAlternativ = false;

    Scanner Read = new Scanner(System.in);

    while (loop) {

      while (pinLogin) {

        System.out.print("Pin: ");
        String givenPin = "";
        while (givenPin.equals("")) {
          givenPin = Read.nextLine();
        }
          

        if (givenPin.equals(pin)) {

          pinLogin = false;
          showAlternativ = true;

        } else {

          fails++;

          if (fails >= 3) {

            System.out.println("Vi har spärrat ditt kort, vänligen kontakta din bank");
            pinLogin = false;
            break;

          } else {

            System.out.println("Du angav fel kod. " + (3 - fails) + " försök kvar\n");

          }
        }
      }

      if (fails >= 3) {
        break;
      }

      while (showAlternativ) {

        printAlternativ();

        int action = Read.nextInt();


        if (action == 1) {
          System.out.println("\nDitt saldo är: " + saldo + " SEK");

        }


        if (action == 2) {
          boolean takeOut = true;
          while (takeOut) {
            System.out.println("\nVänligen ange den summa pengar du hade velat ta ut eller skriv \"avbryt\" för att avbryta");
            String takeOutAmount = "";
            while (takeOutAmount.equals("")) {
              takeOutAmount = Read.nextLine();
            }

            if ((takeOutAmount.toLowerCase()).equals("avbryt")) {
              takeOut = false;
              showAlternativ = true;
            
            } else {
              boolean isInt = true;
              int takeOutValue = 0;
              try {
                takeOutValue = Integer.parseInt(takeOutAmount);
                
              } catch(Exception error) {
                isInt = false;
              } 

              if (isInt) {
                if (takeOutValue < 1) {
                  System.out.println("\nDu måste minst ta ut 1 kr");
                } else if (takeOutValue <= saldo) {
                  saldo = saldo - takeOutValue;
                  System.out.println("\nDu tog ut " + takeOutValue + " kr och har nu " + saldo + " kr kvar på kortet");

                  // Gör så raden under inte är en kommentar ifall man ska bli tillbaka skickad till alternativen efter man har tagit ut pengar
                  
                  // takeOut = false;

                } else {
                  System.out.println("\nSumman du vill ta ut måste vara ett heltal");
                }

              }

            }
          }

        }

            
        if (action == 3) {
          showAlternativ = false;
          loop = false;

        }
      }
    }
  }
}