package reseau;

/**
 * Classe Serveur,
 * Représente le côté serveur (serveur application et base de données).
 * 
 * @author Joan BELO, Matthieu BURTIN, Joseph DZIMBALKA
 */
import java.net.*;
import java.util.*;
import java.lang.*;
import java.io.*;
import java.sql.*;

public class Serveur 
{
    public static void main(String[] args)
	{
		ServerSocket ss = null;
		
		try
		{
			ss = new ServerSocket(99/*Integer.parseInt(args[0])*/);
			
			// while (true)
			// {
				Socket cs = ss.accept();
				// System.out.println(cs.getInetAddress());
				doService(cs);
				// System.out.println("Before close...");
				cs.close();
			// }
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (ss != null)
					ss.close();
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
	}
	
	public static void doService(Socket cs) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(cs.getInputStream()));
		PrintStream ps = new PrintStream(cs.getOutputStream());
		
		// while (true)
		// {
			// System.out.println("Do service...");
			String str = br.readLine();
			
			String[] param = str.split("~");
			String joueur = param[0];
			// System.out.println("|" + param[1]+"|");
			int score = Integer.parseInt(param[1]);
			
			try
			{
				String driver = "com.mysql.jdbc.Driver";
				String url = "jdbc:mysql://localhost:3306/test_java";
				String login = "root";
				String password = "root";
				Class.forName(driver);
				Connection c = DriverManager.getConnection(url,login,password);
				
				String req = "INSERT INTO partie (joueur_partie,score_partie) VALUES (?,?)";
				PreparedStatement prst = c.prepareStatement(req);
				prst.setString(1,joueur);
				prst.setInt(2,score);
				prst.executeUpdate();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally {}
			
			ps.println("(Affichage Serveur sur Client) " + str);
			System.out.println("(Afichage Serveur sur Serveur) " + str);
			// System.out.println("Service done!");
		// }
	}
}
