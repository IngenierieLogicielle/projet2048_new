package modele;

import java.util.Random;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Classe Grid,
 * Représente la grille de jeu.
 * 
 * @author Joan BELO, Matthieu BURTIN, Joseph DZIMBALKA
 */
public class Grid
{
	/**
	Hauteur de la grille (taille de la 1ere dimension du tableau).
	*/
	private int height;
	/**
	Largeur de la grille (taille de la 2nde dimension du tableau).
	*/
    private int width;
	/**
	Tableau de Case correspondant a la grille.
	*/
	private Case[][] cases;

	/**
	Constructeur vide initialise l'objet Grid avec une hauteur par defaut (4) et une largeur par defaut (4) (tableau vide).
	*/
    public Grid()
    {
        height = 4;
	width = 4;
	cases = new Case[height][width];
    }
	
	/**
	Constructeur initialise l'objet Grid avec une hauteur et une largeur (tableau vide).
	@param h Parametre qui correspond a la hauteur du tableau de cases.
	@param w Parametre qui correspond a la largeur du tableau de cases.
	*/
    public Grid(int h,int w)
    {
        height = h;
	width = w;
	cases = new Case[height][width];
    }
	
	/**
	Constructeur initialise l'objet Grid avec un tableau de Case.
	@param c Parametre qui correspond a tableau de cases.
	*/
    public Grid(Case[][] c)
    {
	height = c.length;
	width = c[c.length].length;
	cases = c;
    }
	
	/**
	Methode (setter) qui permet de specifier la hauteur de la grille.
	@param h Parametre qui correspond la hauteur de la grille.
	*/
    public void setHeight(int h)
    {
        height = h;
    }
	
	/**
	Methode (getter) qui permet d'obtenir la hauteur de la grille.
	@return Retourne la hauteur de la grille.
	*/
    public int getHeight()
    {
    	return height;
    }
	
	/**
	Methode (setter) qui permet de specifier la largeur de la grille.
	@param w Parametre qui correspond la largeur de la grille.
	*/
    public void setWidth(int w)
    {
        width = w;
    }
	
	/**
	Methode (getter) qui permet d'obtenir la largeur de la grille.
	@return Retourne la largeur de la grille.
	*/
    public int getWidth()
    {
        return width;
    }
	
	/**
	Methode (setter) qui permet de definir le tableau de cases qui correspond a la grille.
	@param c Parametre qui correspond au tableau de cases a fournir a l'objet.
	*/
    public void setCases(Case[][] c)
    {
	cases = c;
    }
	
	/**
	Methode (getter) qui permet d'obtenir le tableau de cases qui correspond a la grille.
	@return Retourne le tableau de cases (grille).
	*/
    public Case[][] getCases()
    {
	return cases;
    }
	
	/**
	Methode statique qui permet d'obtenir un nombre aleatoire compris entre 0 et une certaine valeur specifiee.
	@param range Parametre qui correspond a la valeur maximale que l'on souhaite.
	@return Retourne le nombre aleatoire.
	*/
    public static int customRandom(int range)
    {
    	int random = (int)(Math.random()*10)%range;
	
	return random;
    }
	
	/**
	Methode qui permet d'initialiser la grille et d'y positionner deux cases.
	*/
    public void initGrid()
    {
	for (int y=0; y<cases.length; y++)
    	{
            for (int x=0; x<cases[y].length; x++)
            {
		cases[y][x] = new Case();
            }
	}
	
	int pos_y_case1 = customRandom(4);
	int pos_x_case1 = customRandom(4);
	
	int pos_y_case2 = customRandom(4);
	int pos_x_case2 = customRandom(4);
		
	while ((pos_y_case1 == pos_y_case2) && (pos_x_case1 == pos_x_case2))
	{
            pos_y_case2 = customRandom(4);
            pos_x_case2 = customRandom(4);
	}
		
	cases[pos_y_case1][pos_x_case1] = new Case(true);
	cases[pos_y_case2][pos_x_case2] = new Case(true);
    }
	
	/**
	Methode qui permet d'ajouter une case avec la valeur 2 ou 4 a la grille.
	*/
    public void addCase()
    {
	int pos_y = customRandom(4);
	int pos_x = customRandom(4);
		
	while (cases[pos_y][pos_x].getValue() != 0)
	{
            pos_y = customRandom(4);
            pos_x = customRandom(4);
	}
		
	cases[pos_y][pos_x] = new Case(2);
    }
	
	/**
	Methode qui permet d'afficher l'etat de la grille.
	*/
    public void displayGrid()
    {
		
	for (int y=0; y<cases.length; y++)
	{
            for (int x=0; x<cases[y].length; x++)
            {
		System.out.print("|"+cases[y][x].getValue());
            }
            System.out.println("|");
	}
    }
	
	
	/**
	Methode qui deplace une case vers un emplacement vide.
	@param from Parametre qui correspond a la case d'origine.
	@param to Parametre qui correspond a la case de destination.
	@return Retourne un booleen a vrai si le deplacement a ete effectue.
	*/
    public boolean move(Case from, Case to)
    {
        if (to.getValue() == 0 && from.getValue() != 0)
        {
            to.setValue(to.getValue()+from.getValue());
            from.setValue(0);
            return true;
        }
        return false;
    }
	
	/**
	Methode qui fusionne une case avec une autre en additionnant les valeurs.
	@param from Parametre qui correspond a la case d'origine.
	@param to Parametre qui correspond a la case de destination.
	@return Retourne un booleen a vrai si la fusion a ete effectuee.
	*/
    public boolean merge(Case from, Case to)
    {
        if (from.getValue() == to.getValue() && from.getValue() != 0)
        {
            to.setValue(to.getValue()+from.getValue());
            from.setValue(0);
            return true;
        }
        return false;
    }
	
	
	/**
	Methode qui permet d'effectuer un mouvement vers le bas.
	@return Retourne un booleen a vrai si le deplacement a pu etre efectuee.
	*/
    public boolean moveDown()
    {
	// Une mise à jour de l'état d'une case C a été effectuée (Oui/Non)
        boolean update;
	// Au moins une action a pu être effectuée (Oui/Non)
        boolean action = false;
        
        // Faire {...} tant que la grille a subit une modification lors du tour précédent
	do
	{
            // La grille n'a pas subit de modification lors de ce tour
            update = false;
		
            // Passage en revue des ordonnées (de bas en haut pour un movement vers le bas)
            for (int y=cases.length-1; y>0; y--)
            {
                // Passage en revue des abscisses (ordre indifférent pour un mouvement vertical)
                for (int x=0; x<cases[y].length; x++)
		{
                    // Une fusion a pu être effectuée
                    boolean merge;
                    // Un mouvement a pu être effectué
                    boolean move;
                    
                    // Faire {...} tant que la case a subit une modification lors du tour précédent
                    do
                    {
                        // Appel de la méthode permettant de déplacer la case précédente vers la case actuelle (fusion)
                        merge = merge(cases[y-1][x],cases[y][x]);
			// Appel de la méthode permettant de déplacer la case précédente vers la case actuelle (vide)
                        move = move(cases[y-1][x],cases[y][x]); 
			// Mise à jour de l'état du booléen de modification (soit il était déjà à vrai et le reste, soit il passe à vrai car on a pu effectuer une modification)
                        update = (update || merge || move);
                        
                        // Si on a pu effectuer une modification, alors on précise via le booléen d'action
                        if(!action && update)
                        {
                          action = update;  
                        }
                    //} while (update);
                    } while (merge || move);
		}
            }
	} while (update);
        
        return action;
        
    }
	
	
	/**
	Methode qui permet d'effectuer un mouvement vers le haut.
	@return Retourne un booleen a vrai si le deplacement a pu etre efectuee.
	*/
    public boolean moveUp()
    {
	boolean update;
	boolean action = false;
	
	do
	{
            update = false;
			
            for (int y=0; y<cases.length-1; y++)
            {
		for (int x=0; x<cases[y].length; x++)
                {
                    boolean merge;
                    boolean move;
					
                    do
                    {
			merge = merge(cases[y+1][x],cases[y][x]);
			move = move(cases[y+1][x],cases[y][x]);
			update = (update || merge || move);
                        if(!action && update)
                        {
                          action = update;  
                        }
                    } while (merge || move);
		}
            }
        } while (update);
        
        return action;
    }
	
	/**
	Methode qui permet d'effectuer un mouvement sur la gauche.
	@return Retourne un booleen a vrai si le deplacement a pu etre efectuee.
	*/
    public boolean moveLeft()
    {
	boolean update;
	boolean action = false;
		
	do
	{
            update = false;
			
            for (int y=0; y<cases.length-1; y++)
            {
                for (int x=0; x<cases[y].length; x++)
		{
                    boolean merge;
                    boolean move;
					
                    do
                    {
                        merge = merge(cases[x][y+1],cases[x][y]);
                        move = move(cases[x][y+1],cases[x][y]);
			update = (update || merge || move);
                        if(!action && update)
                        {
                          action = update;  
                        }
                    } while (merge || move);
                }
            }
        } while (update);
        
        return action;
    }
	
	/**
	Methode qui permet d'effectuer un mouvement sur la droite.
	@return Retourne un booleen a vrai si le deplacement a pu etre efectuee.
	*/
    public boolean moveRight()
    {
        boolean update;
	boolean action = false;
		
	do
	{
            update = false;
			
            for (int y=cases.length-1; y>0; y--)
            {
                for (int x=0; x<cases[y].length; x++)
                {
                    boolean merge;
                    boolean move;
					
                    do
                    {
                        merge = merge(cases[x][y-1],cases[x][y]);
                        move = move(cases[x][y-1],cases[x][y]);
                        update = (update || merge || move);
                        if(!action && update)
                        {
                          action = update;  
                        }
                    } while (merge || move);
		}
            }
	} while (update);
        
        return action;
    }
    
    /**
    Methode qui demande a l'IA d'effectuer une action.
    @return Retourne un booleen a vrai si l'action a pu etre effectuee.
     */
    public boolean helpForOne()
    {
        // Booléen de modification de la grille
        boolean update = false;
        
        // Valeurs déjà utilisées
        boolean val1 = false;
        boolean val2 = false;
        boolean val3 = false;
        boolean val4 = false;
        
        boolean retry;
        boolean all = (val1 && val2 && val3 && val4);
        
        Random r = new Random();
        int rand;
        
		// Tant qu'aucune modification n'a été faite, faire {...}
        while (!update && !all)
        {
            do
            {
				// Génération d'un entier aléatoire compris dans [0,4[ puis incrémentation pour obtenir [1,5[ (soit [1,4])
                rand = r.nextInt(4) + 1;
				
                // Booléen à vrai si toutes les valeurs sont utilisées
                all = (val1 && val2 && val3 && val4);

                // Booléen à vrai s'il existe un conflit (exemple : aléatoire tiré à 1 mais valeur 1 déjà utilisée) et qu'il reste des nombres non utilisés
                retry = !((rand == 1 && !val1) | (rand == 2 && !val2) | (rand == 3 && !val3) | (rand == 4 && !val4) | all);
            } while (retry);
			
            // Choix aléatoire de la direction de l'IA et mouvement dans cette direction
            switch (rand)
            {
                case 1:
					val1 = true;
                    update = this.moveUp();
                    break;
                case 2:
                    val2 = true;
                    update = this.moveDown();
                    break;
                case 3:
                    val3 = true;
                    update = this.moveLeft();
                    break;
                case 4:
                    val4 = true;
                    update = this.moveRight();
                    break;
                default:
                    break;
            }
        }
        
		// Ajout d'une case à la grille
        this.addCase();
        
        // Notification de la (non-)modification de la grille
        return update;
    }
    
	/**
	Methode qui demande a l'IA de terminer la partie.
	@return Retourne un booleen a vrai si l'IA a pu effectuer au moins une action.
	*/
    public boolean helpForAll()
    {
        boolean action = false;
        //boolean value2048 = false;
        
        int i = 0;
        
        // Faire {...} tant qu'on a pu effectuer une action le coup précédent //et tant qu'on a pas rencontré la valeur 2048 
        do
        {
            action = helpForOne();
            //System.out.println(i++);
            //System.out.println("Action : "+action);
            this.displayGrid();
        } while (action);
        //} while (action && !value2048);
        
        return action;
    }
	
	/**
	Methode qui sauvegarde le tableau de cases correspondant à la grille depuis l'attribut de type Case[][] vers le fichier de sauvegarde.
	@return Retourne un booleen a vrai si la sauvegarde a pu etre effectuee.
	*/
	public boolean saveGame()
    {
        boolean saved = false;
        
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        
        try
        {
            fos = new FileOutputStream("2048.save");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(getCases());
            oos.flush();
            saved = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (oos != null)
                {
                    oos.flush();
                    oos.close();
                }
                if (fos != null)
                {
                    fos.flush();
                    fos.close();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        
        return saved;
    }
    
	/**
	Methode qui charge le tableau de cases correspondant à la grille depuis le fichier de sauvegarde vers l'attribut de type Cas[][].
	@return Retourne un booleen a vrai si le chargement a pu etre effectue.
	*/
    public boolean loadGame()
    {
        boolean loaded = false;
        
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        
        try
        {
            fis = new FileInputStream("2048.save");
            ois = new ObjectInputStream(fis);
            setCases((Case[][])ois.readObject());
            loaded = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (ois != null)
                {
                    ois.close();
                }
                if (fis != null)
                {
                    fis.close();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        
        return loaded;
    }
}
