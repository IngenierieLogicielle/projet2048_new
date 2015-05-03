package reseauRMI;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

import java.net.*;
import java.io.*;
import java.sql.*;

public class Serveur extends UnicastRemoteObject implements Actions
{
	public Serveur() throws RemoteException {}
	
	public void enregistrerScore(String joueur, int score) throws RemoteException
	{
		Connection c = null;
		PreparedStatement prst = null;
		
		try
		{
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/test_java";
			String login = "root";
			String password = "root";
			Class.forName(driver);
			c = DriverManager.getConnection(url,login,password);
			
			String req = "INSERT INTO partie (joueur_partie,score_partie) VALUES (?,?)";
			prst = c.prepareStatement(req);
			prst.setString(1,joueur);
			prst.setInt(2,score);
			prst.executeUpdate();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (prst != null)
				{
					prst.close();
					c.close();
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public String[][] afficherScores() throws RemoteException
	{
		Connection c = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		
		String[][] tab = null;
		
		try
		{
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/test_java";
			String login = "root";
			String password = "root";
			Class.forName(driver);
			c = DriverManager.getConnection(url,login,password);
			
			String req = "SELECT joueur_partie,score_partie FROM partie";
			prst = c.prepareStatement(req);
			rs = prst.executeQuery();
			
			rs.last();
			int taille = rs.getRow();
			rs.beforeFirst();
			
			System.out.println(taille);
			
			tab = new String[taille][2];
			int i = 0;
			
			while (rs.next())
			{
				String joueur = rs.getString(1);
				int score = rs.getInt(2);
				
				tab[i][0] = joueur;
				tab[i][1] = ""+score;
					
				System.out.println(tab[i][0] + " : " + tab[i][1]);
				
				i++;
			}
			
			tab.toString();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
					prst.close();
					c.close();
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return tab;
	}
	
	public static void main(String[] args)
	{
		try
		{
			Serveur objet = new Serveur();
			
			// enregistrement du proxy dans le registry
			Naming.rebind("MonServeur",objet);
			System.out.println("Ready...");
		}
		catch (Exception e)
		{
			System.err.println("Erreur : "+e.getMessage());
			// System.out.println("--------------------");
			// System.err.println("Erreur : "+e.printStackTrace());
		}
	}
}
