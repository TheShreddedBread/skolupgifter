import java.util.Scanner; 

class Main {
  public static void main(String[] args) {
    Scanner Read = new Scanner(System.in);
    ///
    System.out.print("Tal 1: ");
    int tal1 = Read.nextInt();
    ///
    System.out.print("Tal 2: ");
    int tal2 = Read.nextInt();
    /////
    int summa = tal1 + tal2;
    System.out.println(summa + " = " + tal1 + " + " + tal2);
  }
}