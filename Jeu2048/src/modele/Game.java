package modele;

import static java.lang.Integer.parseInt;
import java.util.Scanner;

/**
* Classe Game,
* Lance le jeu 2048 pour pouvoir jouer à une partie.
* 
* @author Joan BELO, Matthieu BURTIN, Joseph DZIMBALKA
*/
public class Game
{
    /**
    * Méthode main,
    * Démarre l'exécution du jeu.
    * @param args Arguments a passer au programme. 
    */
    public static void main(String args[])
    {
    // --------------------------------
	// Déclaration des variables utiles
    // --------------------------------
        
	boolean end = false;
	String saisie;
	Scanner s = new Scanner(System.in);
	Grid grid = new Grid(4,4);
	
	grid.initGrid();
	grid.displayGrid();
		
    // ----------------
    // Lancement du jeu : 
    // répétition de la demande d'une touche et de l'execution de l'action tant qu'on ne souhaite pas quitter
    // ----------------
    System.out.println("+++");
	
	while (!end)
	{
            // -----------------
			// Affichage du menu
			// -----------------
			System.out.println("Prochain deplacement : ");
            System.out.println("2 - bas");
            System.out.println("8 - haut");
            System.out.println("4 - gauche");
            System.out.println("6 - droite");
            System.out.println("7 - 1 coup de l'IA");
            System.out.println("9 - Fin de la partie par l'IA");
            System.out.println("Autres commandes :");
            System.out.println("1 - sauvegarder");
            System.out.println("3 - charger");
            System.out.println("5 - nouvelle grille");
            System.out.println("0 - fin");

			// -----------------------------------
			// Attente d'un choix de l'utilisateur
			// -----------------------------------
            System.out.println(">> ");
            saisie = s.nextLine();
            
			// ------------------------------------
			// Traitement du choix de l'utilisateur
			// ------------------------------------
            switch(saisie)
            {
				// -----
				// Effectue un deplacement vers le bas
				// -----
            	case "2":
                    grid.moveDown();
                    grid.addCase();
                    System.out.println("BAS");
                    grid.displayGrid();
                    break;
				// -----
				// Effectue un deplacement vers le haut
				// -----
                case "8":
                    grid.moveUp();
                    grid.addCase();
                    System.out.println("HAUT");
                    grid.displayGrid();
                    break;
				// -----
				// Effectue un deplacement sur la gauche
				// -----
				case "4":
                    grid.moveLeft();
                    grid.addCase();
                    System.out.println("GAUCHE");
                    grid.displayGrid();
                    break;
				// -----
				// Effectue un deplacement sur la droite
				// -----
				case "6":
                    grid.moveRight();
                    grid.addCase();
                    System.out.println("DROITE");
                    grid.displayGrid();
                    break;
				// -----
				// Recommence la partie
				// -----
				case "5":
                    grid.initGrid();
                    grid.displayGrid();
                    break;
				// ------
				// Termine le jeu
				// ------
				case "0":
                    end = true;
                    break;
				// -----
				// Fait appel a l'IA pour effectuer une action
				// -----
				case "7":
                    grid.helpForOne();
                    grid.displayGrid();
                    break;
				// -----
				// Fait appel a l'IA pour terminer la partie
				// -----
				case "9":
                    grid.helpForAll();
                    grid.displayGrid();
                    end = true;
                    break;
				// -----
				// Sauvegarde la partie actuelle
				// -----
				case "1":
                    grid.saveGame();
                    grid.displayGrid();
                    break;
				// -----
				// Charge la derniere partie enregistree
				// -----
                case "3":
                    grid.loadGame();
                    grid.displayGrid();
                    break;
				// -----
				// A l'appui sur une autre touche que celle proposee le jeu s'arrete
				// -----
				default:
                    end = true;
                    break;
            }
	}
        System.out.println("+++");
    }
}
