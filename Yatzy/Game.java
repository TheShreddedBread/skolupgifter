import java.io.*;
import java.util.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicBorders.RolloverButtonBorder;

import java.util.Random;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Game extends JFrame implements ActionListener {

    boolean testingEndScreen = false;
    
    Random rand = new Random();

    JPanel bg = new JPanel();
    JLabel table = new JLabel();
    JLabel header = new JLabel();

    JButton rollDices = new JButton();
    JButton[] squares1 = new JButton[18];
    JButton[] squares2 = new JButton[18];
    String[] squaresText = {"Ettor","Tv\u00E5or","Treor","Fyror","Femmor","Sexor","Summa", "Bonus","1 Par","2 Par","Tretal","Fyrtal","Liten Straight","Stor Straight","K\u00E5k","Chans","Yatzy","Summa"};
    int[] squaresValues;
    JLabel crossImg = new JLabel();
    JLabel crossNo = new JLabel();
    JLabel crossYes = new JLabel();
    JButton toggleCross = new JButton();
    JLabel rollsLabel = new JLabel();

    JButton pairAlt1 = new JButton();
    JButton pairAlt2 = new JButton();
    boolean showPairAlts = false;

    JButton[] dices = new JButton[5];
    boolean[] dicesToRoll = {true, true, true, true, true};
    int[] diceSides = new int[5]; 

    int[] sideGroup = new int[6];

    JPanel restartMenu = new JPanel();
    JButton restartGameBtn = new JButton();
    JButton exitGameBtn = new JButton();
    JLabel restartMenuLabel = new JLabel();

    boolean rolled = false;
    int rollCount = 0;

    boolean cross = false;

    boolean showMenu = false;



    Game() {

        restartMenu.setBackground( new Color(0, 0, 0, 200) );
        restartMenu.setBounds(350, 175, 300, 350);
        restartMenu.setLayout(null);

        restartMenuLabel.setText("Menu");
        restartMenuLabel.setFont(new Font("Segoe", Font.BOLD, 70));
        restartMenuLabel.setForeground(Color.WHITE);
        restartMenuLabel.setHorizontalAlignment(JLabel.CENTER);
        restartMenuLabel.setVerticalAlignment(JLabel.CENTER);
        restartMenuLabel.setBounds(0,10, 300, 80);

        restartGameBtn.setText("K\u00F6r Igen");
        restartGameBtn.setFont(new Font("Segoe", Font.BOLD, 50));
        restartGameBtn.setForeground(Color.BLACK);
        restartGameBtn.setBackground(Color.WHITE);
        restartGameBtn.setHorizontalAlignment(JLabel.CENTER);
        restartGameBtn.setVerticalAlignment(JLabel.CENTER);
        restartGameBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        restartGameBtn.setFocusable(false);
        restartGameBtn.setBounds(30, 150, 235, 70);
        restartGameBtn.addActionListener(this);

        exitGameBtn.setText("St\u00E4ng");
        exitGameBtn.setFont(new Font("Segoe", Font.BOLD, 50));
        exitGameBtn.setForeground(Color.BLACK);
        exitGameBtn.setBackground(Color.WHITE);
        exitGameBtn.setHorizontalAlignment(JLabel.CENTER);
        exitGameBtn.setVerticalAlignment(JLabel.CENTER);
        exitGameBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        exitGameBtn.setFocusable(false);
        exitGameBtn.setBounds(30, 250, 235, 70);
        exitGameBtn.addActionListener(this);

        restartMenu.add(restartMenuLabel);
        restartMenu.add(restartGameBtn);
        restartMenu.add(exitGameBtn);
        // .setBounds(400, 500, 200, 50);
        
        
        // Skapar och placerar alla tärningarna på deras plats och lägger dit den grön "markerade" kanten
        for (int d = 0; d < dices.length; d++) {
            dices[d] = new JButton();
            // dices[d].setBorder(new LineBorder(Color.GREEN));
            dices[d].setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
            dices[d].setIcon(new ImageIcon("./1.png"));
            dices[d].setBounds(500, d * 60 + 120, 49, 49);
            dices[d].addActionListener(this);
            bg.add(dices[d]);
        }

        header.setIcon(new ImageIcon("./head.png"));
        header.setBounds(250, 10, 200, 50);
        bg.add(header);

        // Tabellrutorna
        squaresValues  = new int[18];
        for(int i = 0; i < squares1.length; i++) {
    

            squares2[i] =  new JButton();
            squares2[i].setFont(new Font("Segoe", Font.BOLD, 20));
            squares2[i].setForeground(Color.BLACK);
            squares2[i].setHorizontalAlignment(JLabel.CENTER);
            squares2[i].setVerticalAlignment(JLabel.CENTER);
            squares2[i].setBounds(5 + 136, 5 + (i * 31), 136, 26);
            
            // squares2[i].setText(" ");
            if(testingEndScreen) {
                squares2[i].setText("0");
            } else {
                squares2[i].setText(" ");
            }
                               

            // System.out.println(squaresText[i] + ": " + (5 + (i * 31)));
                       
            squares2[i].setContentAreaFilled(false);
            squares2[i].setOpaque(false);
            squares2[i].setBorderPainted(false);
            squares2[i].setFocusable(false);
            squares2[i].addActionListener(this);
                                             
            table.add(squares2[i]);
                               
            squares1[i] =  new JButton();    
            // squares1[i].setContentType("text/html; charset=UTF-8");
            squares1[i].setBounds(5, 5 + (i * 31), 136, 26);
            squares1[i].setBorder(null);
            squares1[i].setText(squaresText[i]);
            squares1[i].setFont(new Font("Segoe", Font.BOLD, 20));
            squares1[i].setForeground(Color.BLACK);
            squares1[i].setHorizontalAlignment(JLabel.CENTER);
            squares1[i].setVerticalAlignment(JLabel.CENTER);
                              
            squares1[i].setContentAreaFilled(false);
            squares1[i].setOpaque(false);
            squares1[i].setBorderPainted(false);
            squares1[i].setFocusable(false);
            squares1[i].addActionListener(this);
                              
            table.add(squares1[i]);
            
        }
               
        // Första "summan"
        squares2[6].setText("0");
                      
        if(testingEndScreen) {
            squares2[15].setText(" ");
        } 
                   

        // pairAlt1/2 är alternativen vid par så bara styling och positon anges här

        pairAlt1.setText("--");
        pairAlt1.setBounds(300, 240 - 5 + 75, 50, 30);
        //pairAlt1.setBackground(Color.WHITE);
        pairAlt1.setForeground(Color.BLACK);
        pairAlt1.setHorizontalAlignment(JLabel.CENTER);
        pairAlt1.setVerticalAlignment(JLabel.CENTER);
        pairAlt1.setContentAreaFilled(false);
        pairAlt1.setOpaque(false);
        pairAlt1.setFocusable(false);
        pairAlt1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        pairAlt1.addActionListener(this);
        

        pairAlt2.setText("--");
        pairAlt2.setBounds(300, 240 + 5 + 75 + 30, 50, 30);
        //pairAlt2.setBackground(Color.WHITE);
        pairAlt2.setForeground(Color.BLACK);
        pairAlt2.setHorizontalAlignment(JLabel.CENTER);
        pairAlt2.setVerticalAlignment(JLabel.CENTER);
        pairAlt2.setContentAreaFilled(false);
        pairAlt2.setOpaque(false);
        pairAlt2.setFocusable(false);
        pairAlt2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        pairAlt2.addActionListener(this);

        // System.setProperty("file.encoding","UTF-8");
        // Field charset = Charset.class.getDeclaredField("defaultCharset");
        // charset.setAccessible(true);
        // charset.set(null,null);
        // String rawString = "Tvåor";
        // byte[] bytes = rawString.getBytes(StandardCharsets.UTF_8);
        
        // String utf8EncodedString = new String(bytes, StandardCharsets.UTF_8);


        // String[] squaresText = {"Ettor",s,"Treor","Fyror","Femmor","Sexor","Summa", "Bonus","1 Par","2 Par","Tretal","Fyrtal","Liten Straight","Stor Straight","Kåk","Chans","Yatzy","Summa"};
        
        // Labelen/texten som berättar hur många kast användaren har kvar
        rollsLabel.setText("Kast kvar: 3");
        rollsLabel.setBounds(430, 475, 140, 15);
        rollsLabel.setBackground(Color.WHITE);
        rollsLabel.setForeground(Color.BLACK);
        rollsLabel.setHorizontalAlignment(JLabel.CENTER);
        rollsLabel.setVerticalAlignment(JLabel.CENTER);
        rollsLabel.setFont(new Font("Segoe", Font.BOLD, 20));
        bg.add(rollsLabel);
        
        // Knappen som används för att slå tärningarna
        rollDices.setText("Sl\u00E5 t\u00E4rningarna");
        rollDices.setFont(new Font("Segoe", Font.BOLD, 20));
        rollDices.setForeground(Color.BLACK);
        rollDices.setHorizontalAlignment(JLabel.CENTER);
        rollDices.setVerticalAlignment(JLabel.CENTER);
        rollDices.setBounds(400, 500, 200, 50);
        //rollDices.setContentAreaFilled(false);
        //rollDices.setOpaque(false);
        rollDices.setBackground(new Color(255, 255, 255));
        rollDices.setFocusable(false);
        rollDices.addActionListener(this);
        rollDices.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        bg.add(rollDices);

        // Skappar grunden för "switchen" där man kan byta ifall man ska strka eller ej
        crossImg.setIcon(new ImageIcon("./greenred.png"));
        crossImg.setBounds(400, 575, 200, 50);
        // crossImg.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        crossImg.setLayout(null);
        bg.add(crossImg);

        // Label som säger texten "Ja" (Till "switchen") 
        crossYes.setText("");
        crossYes.setFont(new Font("Segoe", Font.BOLD, 30));
        crossYes.setForeground(Color.BLACK);
        crossYes.setHorizontalAlignment(JLabel.CENTER);
        crossYes.setVerticalAlignment(JLabel.CENTER);
        // crossYes.setContentAreaFilled(false);
        crossYes.setBounds(0, 0, 100, 50);
        crossImg.add(crossYes);

        // Label som säger texten "Nej" (Till "switchen") 
        crossNo.setText("Nej");
        crossNo.setFont(new Font("Segoe", Font.BOLD, 30));
        crossNo.setForeground(Color.BLACK);
        crossNo.setHorizontalAlignment(JLabel.CENTER);
        crossNo.setVerticalAlignment(JLabel.CENTER);
        // crossYes.setContentAreaFilled(false);
        crossNo.setBounds(100, 0, 100, 50);
        crossImg.add(crossNo);

        // Skappar knappen som används för switchen
        toggleCross.setText("Stryk");
        toggleCross.setFont(new Font("Segoe", Font.BOLD, 20));
        toggleCross.setForeground(Color.BLACK);
        toggleCross.setHorizontalAlignment(JLabel.CENTER);
        toggleCross.setVerticalAlignment(JLabel.CENTER);
        toggleCross.setBounds(2, 2, 98, 46);
        toggleCross.setFocusable(false);
        toggleCross.addActionListener(this);
        toggleCross.setBackground(Color.WHITE);
        toggleCross.setBorder(null);
        crossImg.add(toggleCross);

        crossImg.setBounds(0,0,0,0);






        // Skappar grunden för tabellen
        table.setBounds(10, 75, 287, 563);
        table.setIcon(new ImageIcon("./yatzytables.png"));
        table.setPreferredSize(new Dimension(287, 563));
        
        // Backgrunden
        bg.setLayout(null);
        bg.setBorder(new EmptyBorder(0, 125, 25, 100));
        bg.setBackground(new Color(0, 130, 186));
        bg.setPreferredSize(new Dimension(1000, 1000));
        bg.add(table);

        // Sätter fönsterstorleken på applikationen, så att den kan stängs och att användaren inte kan ändra fönstersstorlek
        this.setTitle("Yatzy Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        // this.setLayout(new FlowLayout());
        this.setSize(1200, 1200);
        this.setPreferredSize(new Dimension(700, 700));
        this.getContentPane().setBackground(new Color(0, 125, 25));
        this.setIconImage(new ImageIcon("./5.png").getImage());
        
        
        this.add(bg);

        this.pack();
        this.setVisible(true);
    }

    void restartGame() {
        bg.remove(restartMenu);
        squaresValues = new int[18];
        for (int t = 0; t < squares2.length; t++) {
            squares2[t].setText(" ");
        }
        squares2[6].setText("0");

        for(int d = 0; d < dices.length; d++) {
            bg.add(dices[d]);
        }
        
        bg.add(rollsLabel);
        bg.add(rollDices);

        aterstallTarningar();

        bg.revalidate();
        bg.repaint();
    }

    // funktion för att återställa tärningarna
    void aterstallTarningar() {
        rolled = false;
        crossImg.setBounds(0,0,0,0);
        cross = false;

        // diceSides = null;
        for (int d = 0; d < dices.length; d++) {
            dicesToRoll[d] = true;
            dices[d].setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
            
        }
        rollCount = 0;
        rollsLabel.setText("Kast kvar: 3");

        table.revalidate();
        table.repaint();
    }

    // Funktionen för att beräkna hur många tärningarna som landade på de olika sidorna (Array)
    int[] getSidesValue() {
        int[] sideGroups = new int[6];

        for(int i = 0; i < 6; i++) {
            sideGroups[i] = 0;
        }


        // System.out.println("----");

        for(int d = 0; d < 5; d++) {
            
            sideGroups[diceSides[d] - 1] += 1;
            
        }

        // System.out.println("----");


        return sideGroups;
    }

    /* 
    Om någon sida upprepas (b) antal gånger. Används för exempelvis för att
    beräkna ifall användaren kan sätta tärningarna som ett tretal eller fyrtal
    */
    public boolean sideRepeats(int b, int[] collection) {
        boolean acc = false;
        for(int a = 0; a < collection.length; a++) {
            if (collection[a] >= b) {
                acc = true;
            }
        }

        return acc;
    }  

    /*
    Liknar funktionen ovan (används ofta tillsammans) men ifall en sida upprepas
    b antal gånger så beräknar denna hur mycket värdet av det skulle bli. Ett värde
    av exempelvis tre stycken femmor hade varit 15.
    */
    public int sideValue(int b, int[] collection) {
        int val = 0;
        for(int a = 0; a < collection.length; a++) {
            if (collection[a] >= b) {
                val += (a + 1)  * b; 
            }
        }

        return val;
    }  

    /*
    En av huvudfunkitonerna och checkar ifall man kan sätta sin tärningar som något (par, yatzy, kåk, tvåor etc.) och beräknar
    vad värdet av ens tärningar då hade blivit
    */
    public boolean setValue(int t) {

        // Om användaren inte har valt att stryka
        if (!cross) {
            int value = 0;
            if (t >= 0 && t < 6 && squares2[t].getText().equals(" ")) {
                for (int s = 0; s < diceSides.length; s++) {
                    if (diceSides[s] == t + 1) {
                        value += diceSides[s];
                    }
                }
    
                // System.out.println(value);
    
                if(value != 0) {
                    squares2[t].setText(Integer.toString(value));
    
                    checkSum1();
        
                    table.revalidate();
                    table.repaint();
                }
    
                // squares2[t].setText(value);
    
            }
    
            // System.out.println(Arrays.toString(getValue()));
            if(squares2[t].getText().equals(" ")) {
                int[] sidesGrouped = getSidesValue();
                int totalValue = 0;
                for (int v = 0; v < sidesGrouped.length; v++) {
                    totalValue += sidesGrouped[v] * (v + 1);
                }

                //par
                if (t == 8) {
                    int totValue = 0;
                    boolean fir = false;
                    int firInt = 0;
                    boolean sec = false;
                    int secInt = 0;
                    for(int a = 0; a < sidesGrouped.length; a++) {
                        if (sidesGrouped[a] >= 2 && !fir) {
                            firInt = a + 1;
                            fir = true;
                            totValue += (a + 1) * sidesGrouped[a]; 
                        } else if (sidesGrouped[a] >= 2 && fir) {
                            secInt = a + 1;
                            sec = true;
                            totValue += (a + 1) * sidesGrouped[a];
                        }
                    }
    
                    if (fir && sec) {
                        // System.out.println("1 --> " + firInt);
                        // System.out.println("2 --> " + secInt);
                        pairAlt1.setText(Integer.toString(firInt) + ":or");
                        pairAlt2.setText(Integer.toString(secInt) + ":or");
                        bg.add(pairAlt1);
                        bg.add(pairAlt2);
                        showPairAlts = true;

                    } else if (fir && !sec) {
                        value = totValue;
                        squaresValues[t] = totValue;
                        squares2[t].setText(Integer.toString(totValue));  
                    }
            
                }
    
                //2 par
                if (t == 9) {
                    int totValue = 0;
                    boolean fir = false;
                    boolean sec = false;
                    for(int a = 0; a < sidesGrouped.length; a++) {
                        if (sidesGrouped[a] >= 2 && !fir) {
                            fir = true;
                            totValue += (a + 1) * sidesGrouped[a]; 
                        } else if (sidesGrouped[a] >= 2 && fir) {
                            sec = true;
                            totValue += (a + 1) * sidesGrouped[a];
                        }
                    }
    
                    if (fir && sec) {
                        value = totValue;
                        squaresValues[t] = totValue;
                        squares2[t].setText(Integer.toString(totValue));  
                    }
            
                }
    
                //Tretal
                if(t == 10) {
                    if (sideRepeats(3, sidesGrouped)) {
                        int sv = sideValue(3, sidesGrouped);
                        value = sv;
                        squaresValues[t] = sv;
                        squares2[t].setText(Integer.toString(sv));  
                    }
                }
    
                //Fyrtal
                if(t == 11) {
                    if (sideRepeats(4, sidesGrouped)) {
                        int sv = sideValue(4, sidesGrouped);
                        value = sv;
                        squaresValues[t] = sv;
                        squares2[t].setText(Integer.toString(sv));  
                    }
                }
    
                //Liten straight
                if(t == 12) {
                    if (sidesGrouped[1] == 1 && sidesGrouped[2] == 1 && sidesGrouped[3] == 1 && sidesGrouped[4] == 1 && sidesGrouped[0] == 1) {
                        value = 15;
                        squaresValues[t] = 15;
                        squares2[t].setText("15");  
                    }
                } 
    
    
                //Stor straight
                if(t == 13) {
                    if (sidesGrouped[1] == 1 && sidesGrouped[2] == 1 && sidesGrouped[3] == 1 && sidesGrouped[4] == 1 && sidesGrouped[5] == 1) {
                        value = 20;
                        squaresValues[t] = 20;
                        squares2[t].setText("20");  
                    }
                } 
    
                //Kåk
                if(t == 14) {
                    boolean three = false;
                    boolean two = false; 
                    for(int a = 0; a < sidesGrouped.length; a++) {
                        if (sidesGrouped[a] == 3) {
                            three = true;
                        } else if (sidesGrouped[a] == 2) {
                            two = true;
                        }
                    }
    
                    if (two && three) {
                        value = totalValue;
                        squaresValues[t] = totalValue;
                        squares2[t].setText(Integer.toString(totalValue));
                    }
    
                    // if (sideRepeats(2, sidesGrouped) && sideRepeats(3, sidesGrouped))  {
                    //     value = totalValue;
                    //     squaresValues[t] = totalValue;
                    //     squares2[t].setText(Integer.toString(totalValue));                    
                    // }
                } 
    
    
                //Chans
                if (t == 15) {
                    value = totalValue;
                    squaresValues[t] = totalValue;
                    squares2[t].setText(Integer.toString(totalValue));
                }
    
                // Yatzy
                if (t == 16) {
                    if (diceSides[0] == diceSides[1] && diceSides[1]  == diceSides[2] &&  diceSides[2] == diceSides[3] && diceSides[3]  == diceSides[4]) {
                        value = 50;
                        squaresValues[t] = 50;
                        squares2[t].setText("50");
                    }
                }
            }
    
            // Om värdet är 0 så har inget kunnat uppdaters i tabellen användaren klickade på och retunerar därför false, annars true
            if (value == 0) {
                return false;
            } else {
                return true;
            }    

        // Denna delen stryker             
        } else if(squares2[t].getText().equals(" ") && !squares1[t].getText().equals("Bonus") && !squares1[t].getText().equals("Summa")) {
            squaresValues[t] = 0;
            squares2[t].setText("0");
            checkSum1();
            table.revalidate();
            table.repaint();

            return true;
        } else {
            return false;
        }
    }

    // Tar bort och lägger ditt element för att konstruera restart menyn
    void restartGameMenu() {
        for(int l = 0; l < dices.length; l++) {
            bg.remove(dices[l]);
        }
        bg.remove(rollDices);
        bg.remove(rollsLabel);

        bg.add(restartMenu);
        bg.revalidate();
        bg.repaint();
    }

    // Checka ifall alla rutor är fyllda dvs ifall "rundan" är klar.
    boolean checkIfDone() {
        boolean gotError = false;
        int total = 0;
        for(int d = 0; d < squares1.length; d++) {
            if(!squares1[d].getText().equals("Summa")) {
                try {
                    total += Integer.parseInt(squares2[d].getText());
                } catch (Exception err) {
                    gotError = true;
                }
                
            }
        }

        if (!gotError) {
            squares2[17].setText(Integer.toString(total));
            return true;
        } else {
            return false;
        }
        

        
    }


    // Checkar ifall det är någon tärning som är markerad. Används för att se till så att man inte kan slå 0 tärningar
    boolean selectedAnyDice() {
        boolean state = false;
        for(int d = 0; d < dicesToRoll.length; d++) {
            if(dicesToRoll[d]) {
                state = true;
            }
        }
        return state;
    }

    /* 
    Uppdatera och checka den första summan och därmed lägger till 50 poäng om 
    summan blir 63 eller mer
    */
    void checkSum1() {
        int newValue = 0;
        boolean allFilled = true;
        for (int b = 0; b < 6; b++) {
            if (!squares2[b].getText().equals(" ")) {
                newValue += Integer.parseInt(squares2[b].getText());
            }

            if (squares2[b].getText().equals(" ")) {
                allFilled = false;
            }
            
        }

        if (newValue >= 63 && squares2[7].getText().equals(" ")) {
            squares2[7].setText("50");
            squaresValues[7] = 50;
        } else if (allFilled) {
            squaresValues[7] = 0;
            squares2[7].setText("0");
        }

        if (newValue >= 63) {
            newValue += 50;
        }

        squares2[6].setText(Integer.toString(newValue));

    } 

    // När någon knapp trycks ner körs denna funktionen
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitGameBtn) {
            System.exit(0);
        }

        if (e.getSource() == restartGameBtn) {
            restartGame();
        }


        if (showPairAlts) {
            bg.remove(pairAlt1);
            bg.remove(pairAlt2);
            showPairAlts = false;
            if(e.getSource() == pairAlt1) {
                boolean workedAlt = true;
                // int[] laSides = getSidesValue();
                //String text = "0";
                int num = 0;
                try { 
                    num = Integer.parseInt(pairAlt1.getText().substring(0, 1)); 
                } catch(Exception err) {
                    workedAlt = false;
                }
                System.out.println(workedAlt);
                
                if (workedAlt) {
                    // int a = laSides[(num - 1)];
                    squares2[8].setText(Integer.toString(num * 2)) ;
                    // showPairAlts = false;
                    // bg.remove(pairAlt1);
                    // bg.remove(pairAlt2);
                    squaresValues[8] = num * 2;
                    aterstallTarningar();
                }
            }

            if(e.getSource() == pairAlt2) {
                boolean workedAlt = true;
                // int[] laSides = getSidesValue();
                // String text = "0";
                int num = 0;
                try { 
                    num = Integer.parseInt(pairAlt2.getText().substring(0, 1)); 
                } catch(Exception err) {
                    workedAlt = false;
                }
                // System.out.println(workedAlt);
                
                if (workedAlt) {
                    // int a = laSides[(num - 1)];
                    squares2[8].setText(Integer.toString(num * 2)) ;
                    // showPairAlts = false;
                    // bg.remove(pairAlt1);
                    // bg.remove(pairAlt2);
                    squaresValues[8] = num * 2;
                    aterstallTarningar();
                }
            }

            bg.revalidate();
            bg.repaint();
            

        } 
        if (rolled && !showPairAlts) {
            if(e.getSource() == toggleCross) {
                // System.out.println("crossar");
                if (cross) {
                    crossNo.setText("Nej");
                    crossYes.setText("");
                    toggleCross.setBounds(2, 2, 98, 46);
                    cross = false;
                } else {
                    crossNo.setText("");
                    crossYes.setText("Ja");
                    toggleCross.setBounds(100, 2, 98, 46);
                    cross = true;
                }
                
            }

            for(int i = 0; i < squares1.length; i++) {
                if(squares1[i] == e.getSource() || squares2[i] == e.getSource()) {
                    if(squares1[i].getText() != "Bonus" || squares1[i].getText() != "Summa") {
                        boolean uppdated = setValue(i);
                        // System.out.println("Hej");
                        if (uppdated) {
                            aterstallTarningar();
                        }

                    }
    
                }
            }

            for (int d = 0; d < dices.length; d++) {
                if(e.getSource() == dices[d]) {
                    dicesToRoll[d] = !dicesToRoll[d];
                    if (!dicesToRoll[d]) {
                        dices[d].setBorder(BorderFactory.createLineBorder(Color.GREEN, 0));
                    } else {
                        dices[d].setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                    }
                }
            }
            
        }

        if(e.getSource() == rollDices && rollCount < 3) {
            if (selectedAnyDice()) {
                rollsLabel.setText("Kast kvar: " + (2 - rollCount));
                // System.out.println("Count: " + rollCount);
                for (int d = 0; d < dices.length; d++) {
                    if (dicesToRoll[d]) {
                        int select = rand.nextInt(6) + 1;
                        // dices[d].setBorder(new LineBorder(Color.GREEN));
                        dices[d].setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                        dices[d].setIcon(new ImageIcon("./" + select + ".png"));
                        // dices[d].setBounds(500, d * 60 + 100, 49, 49);
                        rolled = true;
                        crossImg.setBounds(400, 575, 200, 50);
                        diceSides[d] = select;

                        crossNo.setText("Nej");
                        crossYes.setText("");
                        toggleCross.setBounds(2, 2, 98, 46);
                        cross = false;

                    }
                }
            rollCount++;
            }
        }

        // Om "runda" är klar, öppna restart menyn.
        boolean done = checkIfDone();
        if (done) {
            restartGameMenu();
        }

        bg.revalidate();
        bg.repaint();
    }
}