package old;

/**
 * Classe Case,
 * Représente une case de la grille de jeu.
 * 
 * @author Joan BELO, Matthieu BURTIN, Joseph DZIMBALKA
 */
public class Case
{
    private int value;
	
    public Case()
    {
    	value = 0;
    }
	
    public Case(int v)
    {
      	value = v;
    }
	
    public Case(boolean r)
    {
        if (r)
        {
            if (((int)(Math.random()*10) % 2) == 0)
		value = 2;
            else
		value = 4;
        }
	else
	{
            value = 0;
	}
    }
	
    public void setValue(int v)
    {
        value = v;
    }
	
    public int getValue()
    {
        return value;
    }
}
