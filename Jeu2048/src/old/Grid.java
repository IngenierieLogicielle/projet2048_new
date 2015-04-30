package old;

import java.util.Random;

/**
 * Classe Grid,
 * Représente la grille de jeu.
 * 
 * @author Joan BELO, Matthieu BURTIN, Joseph DZIMBALKA
 */
public class Grid
{
    private int height;
    private int width;
    private Case[][] cases;

    public Grid()
    {
        height = 4;
	width = 4;
	cases = new Case[height][width];
    }
	
    public Grid(int h,int w)
    {
        height = h;
	width = w;
	cases = new Case[height][width];
    }
	
    public Grid(Case[][] c)
    {
	height = c.length;
	width = c[c.length].length;
	cases = c;
    }
	
    public void setHeight(int h)
    {
        height = h;
    }
	
    public int getHeight()
    {
    	return height;
    }
	
    public void setWidth(int w)
    {
        width = w;
    }
	
    public int getWidth()
    {
        return width;
    }
	
    public void setCases(Case[][] c)
    {
	cases = c;
    }
	
    public Case[][] getCases()
    {
	return cases;
    }
	
    public static int customRandom(int range)
    {
    	int random = (int)(Math.random()*10)%range;
	
	return random;
    }
	
    public void initGrid()
    {
	for (int y=0; y<cases.length; y++)
    	{
            for (int x=0; x<cases[y].length; x++)
            {
		cases[y][x] = new Case();
            }
	}
		
	int pos_y_case1 = customRandom(4); // System.out.println("H1 _"+pos_y_case1);
	int pos_x_case1 = customRandom(4); // System.out.println("W1 _"+pos_x_case1);
	
	int pos_y_case2 = customRandom(4); // System.out.println("H2 _"+pos_y_case2);
	int pos_x_case2 = customRandom(4); // System.out.println("W2 _"+pos_x_case2);
		
	// System.out.println((pos_y_case1 == pos_y_case2) && (pos_x_case1 == pos_x_case2));
		
	while ((pos_y_case1 == pos_y_case2) && (pos_x_case1 == pos_x_case2))
	{
            pos_y_case2 = customRandom(4); // System.out.println("H2 "+pos_y_case2);
            pos_x_case2 = customRandom(4); // System.out.println("W2 "+pos_x_case2);
	}
		
	cases[pos_y_case1][pos_x_case1] = new Case(true);
	cases[pos_y_case2][pos_x_case2] = new Case(true);
    }
	
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
	
    public void displayGrid()
    {
	//System.out.println(cases.length);
	//System.out.println(cases[cases.length-1].length);
		
	for (int y=0; y<cases.length; y++)
	{
            for (int x=0; x<cases[y].length; x++)
            {
		System.out.print("|"+cases[y][x].getValue());
            }
            System.out.println("|");
	}
    }
	
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
					
                    // merge(cases[y-1][x],cases[y][x]);
		}
            }
        } while (update);
        
        return action;
    }
	
    public boolean moveLeft()
    {
	boolean update;
	boolean action = false;
		
	do
	{
            /*
            for (int y=0; y<cases.length; y++)
            {
				
            }
            */
			
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
					
                    // merge(cases[y-1][x],cases[y][x]);
                }
            }
        } while (update);
        
        return action;
    }
	
    public boolean moveRight()
    {
        boolean update;
	boolean action = false;
		
	do
	{
            /*
            for (int y=0; y<cases.length; y++)
            {
				
            }
            */
			
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
					
                    // while (loop)
                    // {
                    // loop = merge(cases[y-1][x],cases[y][x]);
                    // }
		}
            }
	} while (update);
        
        return action;
    }
    
    /**
     * L'IA joue le prochain coup sur la grille.
     * 
     * @return Retourne si l'opération a pu être effectuée
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
		
		// *** DEBUG ***
        System.out.println("A");
        
		// Tant qu'aucune modification n'a été faite, faire {...}
        while (!update && !all)
        {
			// *** DEBUG ***
			System.out.println("B | (!)UPDATE : "+update+" | (!)ALL : "+all);
            
			// Définition d'une valeur aléatoire pour la direction de l'IA
            //int rand = (int)( Math.random()*( 4 - 1 + 1 ) ) + 1; 
            do
            {
				// *** DEBUG ***
				// System.out.println("C");
                
				// Génération d'un entier aléatoire compris dans [0,4[ puis incrémentation pour obtenir [1,5[ (soit [1,4])
                rand = r.nextInt(4) + 1;
				
                // Booléen à vrai si toutes les valeurs sont utilisées
                all = (val1 && val2 && val3 && val4);

                // Booléen à vrai s'il existe un conflit (exemple : aléatoire tiré à 1 mais valeur 1 déjà utilisée) et qu'il reste des nombres non utilisés
                retry = !((rand == 1 && !val1) | (rand == 2 && !val2) | (rand == 3 && !val3) | (rand == 4 && !val4) | all);
				
				// *** DEBUG ***
				System.out.println("C | rand : "+rand+" | val : "+val1+" _ "+val2+" _ "+val3+" _ "+val4+" | all : "+all+" | RETRY : "+retry);
            } while (retry);

			// Actualisation de la liste des valeurs utiliséesS
			//if (rand == 1) val1 = true;
			//if (rand == 2) val2 = true;
			//if (rand == 3) val3 = true;
			//if (rand == 4) val4 = true;
			
			// *** DEBUG ***
			System.out.println("D | rand : "+rand);
			
            // Choix aléatoire de la direction de l'IA et mouvement dans cette direction
            switch (rand)
            {
                case 1:
					// *** DEBUG ***
                    System.out.println("E"+rand+" | val"+rand+" : "+val1);
					val1 = true;
                    update = this.moveUp();
					// *** DEBUG ***
					System.out.println("F"+rand+" | val"+rand+" : "+val1);
                    break;
                case 2:
					// *** DEBUG ***
					System.out.println("E"+rand+" | val"+rand+" : "+val2);
                    val2 = true;
                    update = this.moveDown();
					// *** DEBUG ***
					System.out.println("F"+rand+" | val"+rand+" : "+val2);
                    break;
                case 3:
					// *** DEBUG ***
					System.out.println("E"+rand+" | val"+rand+" : "+val3);
                    val3 = true;
                    update = this.moveLeft();
					// *** DEBUG ***
					System.out.println("F"+rand+" | val"+rand+" : "+val3);
                    break;
                case 4:
					// *** DEBUG ***
					System.out.println("E"+rand+" | val"+rand+" : "+val4);
                    val4 = true;
                    update = this.moveRight();
					// *** DEBUG ***
					System.out.println("F"+rand+" | val"+rand+" : "+val4);
                    break;
                default:
					// *** DEBUG ***
					System.out.println("E"+rand+" | val"+rand+" *** BUG ***");
					// *** DEBUG ***
					System.out.println("F"+rand+" | val"+rand+" *** BUG ***");
                    break;
            }
			
			// *** DEBUG ***
			System.out.println("G | (!)UPDATE : "+update+" | (!)ALL : "+all);
        }
		
		// *** DEBUG ***
		System.out.println("H");
        
		// Ajout d'une case à la grille
        this.addCase();
        
        // Notification de la (non-)modification de la grille
        return update;
    }
    
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
}
