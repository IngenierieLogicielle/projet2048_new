package reseau;

/**
 * Classe Grid,
 * Représente le côté client (ordinateur du joueur).
 * 
 * @author Joan BELO, Matthieu BURTIN, Joseph DZIMBALKA
 */
import java.net.*;
import java.util.*;
import java.lang.*;
import java.io.*;

public class Client 
{
    public static void main(String[] args)
	{
		Socket s = null;
		BufferedReader is = null;
		BufferedReader us = null;
		PrintStream os = null;
		String str = null;
		int val = 0;
		
		try
		{
			s = new Socket((String) null, 99/*Integer.parseInt(args[1])*/);
			os = new PrintStream(s.getOutputStream());
			is = new BufferedReader(new InputStreamReader(s.getInputStream()));
			us = new BufferedReader(new InputStreamReader(System.in));
			
			// while (true)
			// {
				System.out.println("Quel est votre nom ?");
				str = us.readLine();
				System.out.println("Quel est votre score ?");
				val = Integer.parseInt(us.readLine());
				
				// if (str.equals(""))
					// break;
				
				os.println(str + "~" + val);
				
				System.out.println("Mon score : " + is.readLine());
			// }
		}
		catch (UnknownHostException uhe)
		{
			uhe.printStackTrace();
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		finally
		{
			try
			{
				if (s != null)
					s.close();
				if (is != null)
					is.close();
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
	}
}
