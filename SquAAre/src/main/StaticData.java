
package main;

import java.io.*;
import java.util.*;

public class StaticData
{

	/*
	 * final private String IDLOGIN = null; final private String IDSONG = null;
	 * public Boolean SSongServer = false; public Boolean SLoginServer = false;
	 */
	public ArrayList<User> savedUser = null;
	public ArrayList<Creator> savedCreator = null;
	public Boolean SLoginUser = false;
	public Boolean SLoginCreator = false;
	public Boolean SSong = false;
	final public static String LOCALMUSIC = "./import/music/";
	final public static String LOCALINFORM = "";
	final public static String LOCALUSER = "./keys/data/acc/users.ser";
	final public static String LOCALUSERINFO = "./keys/data/acc/users.ser";
	final public static String LOCALCREATOS = "./keys/data/acc/creator.ser";
	final public static String DIRECTORY_INFORM = "./keys/data/inform/";
	final public static String LOCALUSERBK = "./keys/backup/acc/users.ser";
	final public static String LOCALCREATOSBK = "./keys/backup/acc/creator.ser";
	final public static String BARRA = "-------------------------";
	public static Integer ecn = 3;

	public StaticData()
	{

	}

	@SuppressWarnings("unchecked")
	protected void getUsers()
	{
		File file = new File(LOCALUSER);

		if (file.exists())
		{

			try
			{
				FileInputStream fileIn = new FileInputStream(LOCALUSER);

				try
				{
					ObjectInputStream objectIn = new ObjectInputStream(fileIn);
					savedUser = (ArrayList<User>) objectIn.readObject();
					objectIn.close();
				} catch (Exception e)
				{
					savedUser = new ArrayList<User>();
				}

			} catch (Exception ex)
			{
				ex.printStackTrace();
			}

		}
		else
		{

			try
			{
				file.createNewFile();
			} catch (IOException e)
			{
				e.printStackTrace();
			}

		}

		if (savedUser != null)
		{
			Collections.sort(savedUser);
		}

	}

	@SuppressWarnings("unchecked")
	protected void getCreator()
	{
		File file = new File(LOCALCREATOS);

		if (file.exists())
		{

			try
			{
				FileInputStream fileIn = new FileInputStream(LOCALUSER);

				try
				{
					ObjectInputStream objectIn = new ObjectInputStream(fileIn);
					savedCreator = (ArrayList<Creator>) objectIn.readObject();
					objectIn.close();
				} catch (Exception e)
				{
					savedCreator = new ArrayList<Creator>();
				}

			} catch (Exception ex)
			{
				ex.printStackTrace();
			}

		}
		else
		{

			try
			{
				file.createNewFile();
			} catch (IOException e)
			{
				e.printStackTrace();
			}

		}

		if (savedCreator != null)
		{
			Collections.sort(savedUser);
		}

	}

	public void pause(int seg) throws InterruptedException
	{
		Thread.sleep(seg * 1000);
	}

	public static void cls()
	{
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

}
