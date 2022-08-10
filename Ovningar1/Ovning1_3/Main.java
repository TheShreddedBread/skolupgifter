import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Scanner Read = new Scanner(System.in);
    System.out.print("Vänligen ange ditt namn: ");
    String namn = Read.nextLine();
    System.out.println("Hejsan " + namn + "!");
  }
}