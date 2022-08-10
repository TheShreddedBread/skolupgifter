class Main {
  public static void main(String[] args) {
    int i = 0;
    while (i < 5) {
      //Sätter du ++ innan veriablen så ökar du den och sen använder du den och sätter du ++ efter så använder den veriablens nuvarande värde och sedan ökar den med 1.
      System.out.println(++i);
    }
    System.out.println("loopen avslutad");
  }
}