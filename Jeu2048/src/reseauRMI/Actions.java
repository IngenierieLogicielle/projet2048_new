package reseauRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

import java.net.*;
import java.io.*;
import java.sql.*;

public interface Actions extends Remote
{
	public void enregistrerScore(String joueur, int score) throws RemoteException;
	public String[][] afficherScores() throws RemoteException;
}
