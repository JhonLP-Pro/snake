import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Snake extends JPanel implements ActionListener, KeyListener {
    private static final int TAILLE_GRILLE = 20;
    private static final int TAILLE_CELLULE = 20;
    private static final int VITESSE = 200;

    private ArrayList<Point> serpent;
    private Point pomme;
    private int direction;
    private boolean enJeu;
    private int score;
    private Timer timer;

    public Snake() {
        setPreferredSize(new Dimension(TAILLE_GRILLE * TAILLE_CELLULE, TAILLE_GRILLE * TAILLE_CELLULE));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        initialiserJeu();
    }

    private void initialiserJeu() {
        serpent = new ArrayList<>();
        serpent.add(new Point(TAILLE_GRILLE/2, TAILLE_GRILLE/2));
        genererPomme();
        direction = KeyEvent.VK_RIGHT;
        enJeu = true;
        score = 0;
        
        // Arrêter l'ancien timer s'il existe
        if (timer != null) {
            timer.stop();
        }
        timer = new Timer(VITESSE, this);
        timer.start();
    }

    private void genererPomme() {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(TAILLE_GRILLE);
            y = rand.nextInt(TAILLE_GRILLE);
        } while (serpent.contains(new Point(x, y)));
        pomme = new Point(x, y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dessiner le serpent
        g.setColor(Color.GREEN);
        for (Point p : serpent) {
            g.fillRect(p.x * TAILLE_CELLULE, p.y * TAILLE_CELLULE, 
                      TAILLE_CELLULE - 1, TAILLE_CELLULE - 1);
        }

        // Dessiner la pomme
        g.setColor(Color.RED);
        g.fillRect(pomme.x * TAILLE_CELLULE, pomme.y * TAILLE_CELLULE, 
                  TAILLE_CELLULE - 1, TAILLE_CELLULE - 1);

        // Afficher le score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Score: " + score, getWidth() - 100, 20);

        if (!enJeu) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Game Over!", getWidth()/2 - 50, getHeight()/2);
            // Ajout du message pour rejouer
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString("Appuyez sur ENTER pour rejouer", getWidth()/2 - 100, getHeight()/2 + 30);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (enJeu) {
            deplacer();
            verifierCollision();
            repaint();
        }
    }

    private void deplacer() {
        Point tete = serpent.get(0);
        Point nouvelleTete = new Point(tete);

        switch (direction) {
            case KeyEvent.VK_LEFT:  nouvelleTete.x--; break;
            case KeyEvent.VK_RIGHT: nouvelleTete.x++; break;
            case KeyEvent.VK_UP:    nouvelleTete.y--; break;
            case KeyEvent.VK_DOWN:  nouvelleTete.y++; break;
        }

        serpent.add(0, nouvelleTete);

        if (nouvelleTete.equals(pomme)) {
            score += 10;
            genererPomme();
        } else {
            serpent.remove(serpent.size() - 1);
        }
    }

    private void verifierCollision() {
        Point tete = serpent.get(0);

        // Collision avec les murs
        if (tete.x < 0 || tete.x >= TAILLE_GRILLE || 
            tete.y < 0 || tete.y >= TAILLE_GRILLE) {
            gameOver();
        }

        // Collision avec le serpent
        for (int i = 1; i < serpent.size(); i++) {
            if (tete.equals(serpent.get(i))) {
                gameOver();
                break;
            }
        }
    }

    // Nouvelle méthode pour gérer le Game Over
    private void gameOver() {
        enJeu = false;
        requestFocusInWindow(); // Assure que le panneau garde le focus
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Gestion de la touche ENTER pour rejouer
        if (!enJeu && e.getKeyCode() == KeyEvent.VK_ENTER) {
            initialiserJeu();
            requestFocusInWindow(); // Assure que le panneau garde le focus après le redémarrage
            return;
        }

        int nouvelleDirection = e.getKeyCode();
        
        if ((direction == KeyEvent.VK_LEFT && nouvelleDirection != KeyEvent.VK_RIGHT) ||
            (direction == KeyEvent.VK_RIGHT && nouvelleDirection != KeyEvent.VK_LEFT) ||
            (direction == KeyEvent.VK_UP && nouvelleDirection != KeyEvent.VK_DOWN) ||
            (direction == KeyEvent.VK_DOWN && nouvelleDirection != KeyEvent.VK_UP)) {
            
            if (nouvelleDirection == KeyEvent.VK_LEFT || 
                nouvelleDirection == KeyEvent.VK_RIGHT ||
                nouvelleDirection == KeyEvent.VK_UP || 
                nouvelleDirection == KeyEvent.VK_DOWN) {
                direction = nouvelleDirection;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
} 