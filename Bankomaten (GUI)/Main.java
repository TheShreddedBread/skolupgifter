// import javax.swing.JFrame;
// import javax.swing.JLabel;
// import javax.swing.JButton;
// import javax.swing.JColorChooser;
// import javax.swing.ImageIcon;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


class Main {

    public static void main(String[] args) {

        // // JTextField textField = new JTextField();
        // // textField.setPreferredSize(new Dimension(10, 10));
        // // textField.setBounds(50,50,50,50);
        // // textField.setSize(50,50);

        // JTextField t1 = new JTextField("Welcome to Javatpoint.");  
        // // t1.setBounds(50,100, 200,30);  
        // t1.setPreferredSize(new Dimension(400, 400));
        

        // //Background och annat grundläggande

        // ImageIcon image = new ImageIcon("bg.jpg");

        // JLabel bg = new JLabel();
        // bg.setIcon(image);
        // bg.setHorizontalAlignment(JLabel.CENTER);
        // bg.setVerticalAlignment(JLabel.CENTER);

        // JFrame frame = new JFrame(); //Skapar en ruta
        // frame.setTitle("Bank");
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setResizable(false);
        // // frame.setSize(415, 435);
        // frame.setSize(800, 800);
        // frame.getContentPane().setBackground(new Color(100,100,255));
    

        // // frame.add(bg);

        // frame.setVisible(true);

        // // frame.add(t1);


        // // ImageIcon image = new ImageIcon("icon.png") 
        // // frame.setIconImage(image.getImage());

        // // frame.getContentPane().setBackground(Color.YELLOW);


        Bank screen = new Bank();

    }
}