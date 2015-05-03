package reseauRMI;

import java.rmi.*;

import java.net.*;
import java.io.*;
import java.sql.*;

public class Client
{
	static int resultat;
	
	public static void main(String[] args)
	{
		try
		{
			// recherche dans le registry de la reference à la méthode
			Actions reference = (Actions)Naming.lookup("MonServeur");
			
			// invocation de la méthode
			reference.enregistrerScore(args[0],Integer.parseInt(args[1]));
			String[][] tab = reference.afficherScores();
			
			for (int i = 0; i < tab.length; i++)
			{
				for (int j = 0; j < tab[i].length; j++)
				{
					System.out.print(tab[i][j]);
					System.out.print(" ");
				}
				System.out.println();
			}
		}
		catch (Exception e)
		{
			System.err.println("Erreur : "+e.getMessage());
			// System.out.println("--------------------");
			// System.err.println("Erreur : "+e.printStackTrace());
		}
	}
}
