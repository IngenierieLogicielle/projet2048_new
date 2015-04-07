package old;

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
	
    public void moveDown()
    {
	/*for (int y=cases.length-1; y>1; y--)
	{
            for (int x=0; x<cases[y].length; x++)
            {
                merge(cases[y-1][x],cases[y][x]);
            }
	}*/
		
	boolean update;
		
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
                        merge = merge(cases[y-1][x],cases[y][x]);
			move = move(cases[y-1][x],cases[y][x]);
			update = (update || merge || move);
                    } while (merge || move);
					
                    // while (loop)
                    // {
                    // loop = merge(cases[y-1][x],cases[y][x]);
                    // }
		}
            }
	} while (update);
    }
	
    public void moveUp()
    {
	boolean update;
	
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
                    } while (merge || move);
					
                    // merge(cases[y-1][x],cases[y][x]);
		}
            }
        } while (update);
    }
	
    public void moveLeft()
    {
	boolean update;
		
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
                    } while (merge || move);
					
                    // merge(cases[y-1][x],cases[y][x]);
                }
            }
        } while (update);
    }
	
    public void moveRight()
    {
        boolean update;
		
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
                    } while (merge || move);
					
                    // while (loop)
                    // {
                    // loop = merge(cases[y-1][x],cases[y][x]);
                    // }
		}
            }
	} while (update);
    }
    
    public boolean helpForOne()
    {
        return false;
    }
    
    public boolean helpForAll()
    {
        return false;
    }
}
