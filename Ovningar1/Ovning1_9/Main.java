import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    System.out.print("Farenheit: ");
    Scanner Read = new Scanner(System.in);
    
    double farenheit = Read.nextDouble();

    double celsius = (farenheit - 32)/1.8;
    System.out.print(celsius + " Celsius");
  }
}