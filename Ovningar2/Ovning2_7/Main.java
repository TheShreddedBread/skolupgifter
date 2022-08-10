class Main {
  public static void main(String[] args) {

    System.out.print("    ");
    for (int h = 0; h <= 12; h++) {
      // System.out.print(h + " ");
      System.out.format( "%4d", h);
    }
    System.out.println();

    System.out.println("   #----------------------------------------------------");

    for (int y = 0; y <= 12; y++) {
      System.out.print(y);
      for(int s = 0; s < "12".length() - String.valueOf(y).length() + 1; s++ ) {
        System.out.print(" ");
      }
      System.out.print("|");
      for (int x = 0; x <= 12; x++) {
        // System.out.print((x * y) + " ");
        System.out.format( "%4d" , (x * y) );
      } 
      System.out.println();
    }
  }
}