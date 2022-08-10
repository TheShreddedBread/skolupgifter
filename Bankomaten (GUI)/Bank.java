import java.awt.event.*;
import java.lang.reflect.Array;
import java.awt.*;
import javax.swing.*;

public class Bank extends JFrame implements ActionListener {

    int saldo = 1000;
    String pin = "6454";
    String givenPin = "";
    int givenPinLimit = 4;
    boolean LoggedIn = false;
    int fails = 0;
    boolean Bypass = false;
    boolean blocked = false;

    JTextField pinField;
    JButton pinCheckBtn;
    JLabel bg;
    JLabel saldoLabel;

    // Labels
    JLabel pinLabel = new JLabel();


    // Mata in/ut kortet
    JButton insertCard = new JButton();
    JButton returnCard = new JButton();
    
    // Andra bilder
    ImageIcon image = new ImageIcon("bg.jpg");
    ImageIcon confirmImg = new ImageIcon("conf.jpg");

    //Knappsatts Display
    JLabel numDisplay = new JLabel();

    //Knappsatts (Stora knapparna)
    JButton numDone = new JButton();
    JButton numWrong = new JButton();
    JButton numCancel = new JButton();

    
    //Knappsatts (Nummerna)
    JButton num0 = new JButton();

    JButton num1 = new JButton();
    JButton num2 = new JButton();
    JButton num3 = new JButton();

    JButton num4 = new JButton();
    JButton num5 = new JButton();
    JButton num6 = new JButton();

    JButton num7 = new JButton();
    JButton num8 = new JButton();
    JButton num9 = new JButton();

    JButton[] numPadBtn = {num0, num1, num2, num3, num4, num5, num6, num7, num8, num9};

    //Knappsatts (Nummerna)
    JLabel num0Label = new JLabel();

    JLabel num1Label = new JLabel();
    JLabel num2Label = new JLabel();
    JLabel num3Label = new JLabel();

    JLabel num4Label = new JLabel();
    JLabel num5Label = new JLabel();
    JLabel num6Label = new JLabel();

    JLabel num7Label = new JLabel();
    JLabel num8Label = new JLabel();
    JLabel num9Label = new JLabel();

    JLabel[] numPadLabels = {num0Label, num1Label, num2Label, num3Label, num4Label, num5Label, num6Label, num7Label, num8Label, num9Label};

    //Knappsatts (Position)
    int numSizeX = 39;
    int numSizeY = 39;
    int[] numPosX = {122, 78, 122, 166, 78, 122, 166, 78, 122, 166};
    int[] numPosY = {314, 181, 181, 181, 225, 225, 225, 269, 269, 269};



    // Deklarera knapparna (Höger och vänster sida)
    JButton menuButton1 = new JButton();
    JButton menuButton2 = new JButton();
    JButton menuButton3 = new JButton();
    JButton menuButton4 = new JButton();

    JButton menuButton5 = new JButton();
    JButton menuButton6 = new JButton();
    JButton menuButton7 = new JButton();
    JButton menuButton8 = new JButton();

    // Deklarera alternativen
    JLabel altLabel1 = new JLabel();
    JLabel altLabel2 = new JLabel();
    JLabel altLabel3 = new JLabel();
    JLabel altLabel4 = new JLabel();

    JLabel altLabel5 = new JLabel();
    JLabel altLabel6 = new JLabel();
    JLabel altLabel7 = new JLabel();
    JLabel altLabel8 = new JLabel();


    Bank() {
        for(int n = 0; n < 10; n++) {
            numPadBtn[n].addActionListener(this);
        }
        
        numCancel.addActionListener(this);
        numWrong.addActionListener(this);
        numDone.addActionListener(this);

        insertCard.addActionListener(this);
        returnCard.addActionListener(this);

        menuButton1.addActionListener(this);
        menuButton2.addActionListener(this);
        menuButton3.addActionListener(this);
        menuButton4.addActionListener(this);

        menuButton5.addActionListener(this);
        menuButton6.addActionListener(this);
        menuButton7.addActionListener(this);
        menuButton8.addActionListener(this);


        bg = new JLabel();
        bg.setIcon(image);
        bg.setHorizontalAlignment(JLabel.CENTER);
        bg.setVerticalAlignment(JLabel.CENTER);
        // bg.setBounds(0, 0, 410, 410);


        insertCard.setText("Mata in kortet");
        insertCard.setHorizontalAlignment(JLabel.CENTER);
        insertCard.setVerticalAlignment(JLabel.CENTER);
        insertCard.setBounds(100, 300, 200, 50);


        this.setTitle("Bank");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new FlowLayout());
        this.setSize(410, 410);
        this.add(bg);

        bg.add(insertCard);

        this.pack();
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton[] MoneyButtons = {menuButton1, menuButton2, menuButton3, menuButton4, menuButton5, menuButton6, menuButton7, menuButton8};
        JLabel[] MoneyAlt = {altLabel1, altLabel2, altLabel3, altLabel4, altLabel5, altLabel6, altLabel7, altLabel8};

        for (int i = 0; i < 7; i++) {
            if (e.getSource() == MoneyButtons[i]) {
                int takeOut = Integer.parseInt(MoneyAlt[i].getText());
                
                if (saldo >= takeOut) {
                    
                    saldo -= takeOut;
                    saldoLabel.setText("Saldo: " + saldo);

                }
                
            }
        }

        if(e.getSource() == MoneyButtons[7]) {
            DestroyTakeoutScreen();

            BuildNumpadScreen(5, "Ange summan");

            Bypass = true;

            bg.revalidate();
            bg.repaint();
        }

        if(e.getSource() == returnCard) {
            DestroyTakeoutScreen();

            bg.add(insertCard);
            LoggedIn = false;
            givenPin = "";
        }

        if(e.getSource() == insertCard) {

            bg.remove(insertCard);
            
            if (blocked) {
                BuildNumpadScreen(4, "Access Declined");
            } else {
                BuildNumpadScreen(4, "Pin");
            }
                

            bg.revalidate();
            bg.repaint();
        }

        for (int n = 0; n < 10; n++) {
            if (e.getSource() == numPadBtn[n]) { 
                if (Bypass) {
                    if (pinLabel.getText().length() != 0) {
                        pinLabel.setText("");
                    } 
                }
                
                if (givenPin.length() < givenPinLimit) {
                    givenPin = givenPin + numPadLabels[n].getText();
                    numDisplay.setText(givenPin);
                }
            }
        }

        if (e.getSource() == numCancel) {
            givenPin = "";

            if (Bypass) {
                BuildTakeoutScreen();
                Bypass = false;
                
            } else {
                bg.add(insertCard);

                numDisplay.setText("");
                givenPin = "";
                LoggedIn = false;
                bg.setIcon(new ImageIcon("bg.jpg"));
    
            }

            for (int n = 0; n < 10; n++) {
                bg.remove(numPadLabels[n]);
                bg.remove(numPadBtn[n]);
            }

            bg.remove(numDone);
            bg.remove(numWrong);
            bg.remove(numCancel);
            bg.remove(numDisplay);
            bg.remove(pinLabel);
        }

        if (e.getSource() == numWrong) {
            if (givenPin.length() > 0) {
                givenPin = givenPin.substring(0, givenPin.length() - 1);
                numDisplay.setText(givenPin);
            }
        }

        if (e.getSource() == numDone && !blocked) {
            if (givenPin.equals( pin ) || Bypass) {
                if (Bypass) {
                    if (Integer.parseInt(givenPin) <= saldo) {
                        if (givenPin.equals("0")) {
                            pinLabel.setText(Integer.parseInt(givenPin) + " KR kan ej tas ut");

                        } else {
                            saldo -= Integer.parseInt(givenPin);

                            numDisplay.setText("");
                            givenPin = "";
                            LoggedIn = true;
                            bg.setIcon(new ImageIcon("bgin.jpg"));
            
                            for (int n = 0; n < 10; n++) {
                                bg.remove(numPadLabels[n]);
                                bg.remove(numPadBtn[n]);
                            }
            
                            bg.remove(numDone);
                            bg.remove(numWrong);
                            bg.remove(numCancel);
                            bg.remove(numDisplay);
                            bg.remove(pinLabel);

                            Bypass = false;
            
                            BuildTakeoutScreen();
                        }

                    } else {
                        pinLabel.setText("Du har ej " + Integer.parseInt(givenPin) + " KR");

                    }

                } else {
                    fails = 0;

                    numDisplay.setText("");
                    givenPin = "";
                    LoggedIn = true;
                    bg.setIcon(new ImageIcon("bgin.jpg"));
    
                    for (int n = 0; n < 10; n++) {
                        bg.remove(numPadLabels[n]);
                        bg.remove(numPadBtn[n]);
                    }
    
                    bg.remove(numDone);
                    bg.remove(numWrong);
                    bg.remove(numCancel);
                    bg.remove(numDisplay);
                    bg.remove(pinLabel);
    
                    BuildTakeoutScreen();
                }

                
            } else if (!blocked) {
                fails += 1;
                numDisplay.setText("");
                givenPin = "";
                if ((3 - fails)  == 1) {
                    pinLabel.setText((3 - fails) + " Attempt Left");
                } else {
                    pinLabel.setText((3 - fails) + " Attempts Left");
                }
            }

            if (fails >= 3) {
                fails = 3;
                for (int n = 0; n < 10; n++) {
                    numPadBtn[n].removeActionListener(this);
                }

                // bg.remove(numDone);
                numDisplay.setText("Access Declined");
                pinLabel.setText("");
                blocked = true;
                // bg.remove(numDisplay);


                bg.revalidate();
                bg.repaint();
            }


        }

    }

    public void DestroyTakeoutScreen() {
        JButton[] MoneyButtons = {menuButton1, menuButton2, menuButton3, menuButton4, menuButton5, menuButton6, menuButton7, menuButton8};
        JLabel[] MoneyAlt = {altLabel1, altLabel2, altLabel3, altLabel4, altLabel5, altLabel6, altLabel7, altLabel8};

        for (int i = 0; i < 8; i++) {
            bg.remove(MoneyButtons[i]);
            bg.remove(MoneyAlt[i]);
            bg.remove(saldoLabel);
            bg.remove(returnCard);

            bg.setIcon(new ImageIcon("bg.jpg"));
            
        }

        bg.revalidate();
        bg.repaint();
    }


    public void BuildNumpadScreen(int setMax, String numDisplayText) {
        givenPinLimit = setMax;
        bg.setIcon(new ImageIcon("bgin4.jpg"));

        pinLabel.setText("");
        pinLabel.setFont(new Font("Arial", Font.BOLD, 20));
        pinLabel.setForeground(Color.BLACK);
        pinLabel.setBounds(78, 75, 243, 23);
        pinLabel.setHorizontalAlignment(JLabel.CENTER);
        pinLabel.setVerticalAlignment(JLabel.CENTER);

        bg.add(pinLabel);


        // --- Display för knappsattsen --- //
        numDisplay.setText(numDisplayText);
        numDisplay.setFont(new Font("Arial", Font.BOLD, 30));
        numDisplay.setBounds(78, 121, 243, 54);
        numDisplay.setHorizontalAlignment(JLabel.CENTER);
        numDisplay.setVerticalAlignment(JLabel.CENTER);
        numDisplay.setForeground(Color.WHITE);
        // numDisplay.setText("hej");

        bg.add(numDisplay);


        // --- De "stora" knapparna --- //


        numDone.setBounds(215, 181, 106, 39);
        numDone.setBorderPainted(false);
        numDone.setIcon(new ImageIcon("done.png"));

        bg.add(numDone);


        numWrong.setBounds(215, 225, 106, 39);
        numWrong.setBorderPainted(false);
        numWrong.setIcon(new ImageIcon("wrong.png"));

        bg.add(numWrong);

        
        numCancel.setBounds(215, 269, 106, 39);
        numCancel.setBorderPainted(false);
        numCancel.setIcon(new ImageIcon("cancel.png"));

        bg.add(numCancel);

        // --- Knappsatsens knappar --- //

        for(int b = 0; b < 10; b++) {
            numPadLabels[b].setFont(new Font("Arial", Font.PLAIN, 20));
            numPadLabels[b].setText(String.valueOf(b));
            numPadLabels[b].setHorizontalAlignment(JLabel.CENTER);
            numPadLabels[b].setVerticalAlignment(JLabel.CENTER);
            numPadLabels[b].setBounds(numPosX[b], numPosY[b], numSizeX, numSizeY);

            bg.add(numPadLabels[b]);

            numPadBtn[b].setHorizontalAlignment(JButton.CENTER);
            numPadBtn[b].setVerticalAlignment(JButton.CENTER);
            numPadBtn[b].setOpaque(false);
            numPadBtn[b].setContentAreaFilled(false);
            numPadBtn[b].setBorderPainted(false);
            numPadBtn[b].setBounds(numPosX[b], numPosY[b], numSizeX, numSizeY);

            bg.add(numPadBtn[b]);
        }
    }




    public void BuildTakeoutScreen() {
        bg.setIcon(new ImageIcon("bgin.jpg"));

        saldoLabel = new JLabel();
        saldoLabel.setText("Saldo: " + saldo);
        saldoLabel.setBounds(151, 107, 100, 20);
        saldoLabel.setHorizontalAlignment(JLabel.CENTER);
        

        bg.add(saldoLabel);


        //Knapparna (Vänster)
        menuButton1.setIcon(new ImageIcon("inv.png")); 
        menuButton1.setBounds(72, 147, 24, 24);

        menuButton2.setIcon(new ImageIcon("inv.png")); 
        menuButton2.setBounds(72, 195, 24, 24);

        menuButton3.setIcon(new ImageIcon("inv.png")); 
        menuButton3.setBounds(72, 243, 24, 24);

        menuButton4.setIcon(new ImageIcon("inv.png")); 
        menuButton4.setBounds(72, 291, 24, 24);

        bg.add(menuButton1);
        bg.add(menuButton2);
        bg.add(menuButton3);
        bg.add(menuButton4);

        //Knapparna (Höger)
        menuButton5.setIcon(new ImageIcon("inv.png")); 
        menuButton5.setBounds(304, 147, 24, 24);

        menuButton6.setIcon(new ImageIcon("inv.png")); 
        menuButton6.setBounds(304, 195, 24, 24);

        menuButton7.setIcon(new ImageIcon("inv.png")); 
        menuButton7.setBounds(304, 243, 24, 24);

        menuButton8.setIcon(new ImageIcon("inv.png")); 
        menuButton8.setBounds(304, 291, 24, 24);

        bg.add(menuButton5);
        bg.add(menuButton6);
        bg.add(menuButton7);
        bg.add(menuButton8);



        //Alternativen (Vänster)
        altLabel1 = new JLabel();
        altLabel1.setBounds(105, 149, 50, 20);
        altLabel1.setText("50");
        altLabel1.setHorizontalAlignment(JLabel.CENTER);
        altLabel1.setVerticalAlignment(JLabel.CENTER);


        altLabel2.setBounds(105, 197, 50, 20);
        altLabel2.setText("100");
        altLabel2.setHorizontalAlignment(JLabel.CENTER);
        altLabel2.setVerticalAlignment(JLabel.CENTER);


        altLabel3.setBounds(105, 245, 50, 20);
        altLabel3.setText("200");
        altLabel3.setHorizontalAlignment(JLabel.CENTER);
        altLabel3.setVerticalAlignment(JLabel.CENTER);


        altLabel4.setBounds(105, 293, 50, 20);
        altLabel4.setText("500");
        altLabel4.setHorizontalAlignment(JLabel.CENTER);
        altLabel4.setVerticalAlignment(JLabel.CENTER);


        bg.add(altLabel1);
        bg.add(altLabel2);
        bg.add(altLabel3);
        bg.add(altLabel4);

        
        //Alternativen (Höger)
        altLabel5.setBounds(244, 149, 50, 20);
        altLabel5.setText("1000");
        altLabel5.setHorizontalAlignment(JLabel.CENTER);
        altLabel5.setVerticalAlignment(JLabel.CENTER);


        altLabel6.setBounds(244, 197, 50, 20);
        altLabel6.setText("1500");
        altLabel6.setHorizontalAlignment(JLabel.CENTER);
        altLabel6.setVerticalAlignment(JLabel.CENTER);


        altLabel7.setBounds(244, 245, 50, 20);
        altLabel7.setText("2000");
        altLabel7.setHorizontalAlignment(JLabel.CENTER);
        altLabel7.setVerticalAlignment(JLabel.CENTER);


        altLabel8.setBounds(220, 293, 75, 20);
        altLabel8.setText("Egen summa");
        altLabel8.setHorizontalAlignment(JLabel.CENTER);
        altLabel8.setVerticalAlignment(JLabel.CENTER);

        
        bg.add(altLabel5);
        bg.add(altLabel6);
        bg.add(altLabel7);
        bg.add(altLabel8);
        

        returnCard.setText("Ta ut kortet");
        returnCard.setHorizontalAlignment(JLabel.CENTER);
        returnCard.setVerticalAlignment(JLabel.CENTER);
        returnCard.setBounds(150, 368, 100, 28);

        bg.add(returnCard);

        bg.revalidate();
        bg.repaint();
    }
    
}