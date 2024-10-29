import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame fenetre = new JFrame("Snake");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setResizable(false);
        fenetre.add(new Snake());
        fenetre.pack();
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);
    }
} 