package modele;

import java.io.Serializable;

/**
 * Classe Case,
 * Represente une case de la grille de jeu.
 * 
 * @author Joan BELO, Matthieu BURTIN, Joseph DZIMBALKA
 */
public class Case implements Serializable
{
	/**
	Attribut 'value' qui correspond a la valeur de la case.
	*/
	private int value;

	/**
	Constructeur vide pour creer des objets de type Case.
	*/
    public Case()
    {
    	value = 0;
    }
	
	/**
	Constructeur pour creer des objets de type Case.
	@param v Entier qui correspond à la valeur de la case.
	*/
    public Case(int v)
    {
      	value = v;
    }
	
	/**
	Constructeur pour creer des objets de type Case.
	@param r Booleen qui, s'il est à vrai, permet de generer une valeur aleatoire (2 ou 4) pour la case.
	*/
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
	
	/**
	Methode (setter) qui permet d'attribuer une valeur à l'attribut 'value'.
	@param v Entier qui correspond à la valeur de la case.
	*/
    public void setValue(int v)
    {
        value = v;
    }
	
	/**
	Methode (getter) qui permet d'obtenir la valeur de l'attribut 'value'.
	@return Retourne la valeur de la case.
	*/
    public int getValue()
    {
        return value;
    }
}
