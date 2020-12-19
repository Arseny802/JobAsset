package com.example.job_asset.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class ConnectorSQLite {
	public Connection connection;
	public static Statement statmt;
	public static ResultSet resSet;
	
	public ConnectorSQLite() {
	
	}
	
	public void Conn() throws ClassNotFoundException, SQLException
	{
		connection = null;
		Class.forName("org.sqlite.JDBC");
		connection = DriverManager.getConnection("jdbc:sqlite:TEST1.s3db");
		
		System.out.println("База Подключена!");
	}
	
	// --------Создание таблицы--------
	public void CreateDB() throws ClassNotFoundException, SQLException
	{
		statmt = connection.createStatement();
		statmt.execute("full_db.sql");
		statmt.execute("CREATE TABLE if not exists 'users' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'phone' INT);");
		
		System.out.println("Таблица создана или уже существует.");
	}
	
	public void AddSingleJob(String title, String description) throws SQLException
	{
		statmt.execute("INSERT INTO 'users' ('name', 'phone') VALUES ('Petya', 125453); ");
		
		System.out.println("Таблица заполнена");
	}
	
	// -------- Вывод таблицы--------
	public void ReadDB() throws ClassNotFoundException, SQLException
	{
		resSet = statmt.executeQuery("SELECT * FROM users");
		
		while(resSet.next())
		{
			int id = resSet.getInt("id");
			String  name = resSet.getString("name");
			String  phone = resSet.getString("phone");
			System.out.println( "ID = " + id );
			System.out.println( "name = " + name );
			System.out.println( "phone = " + phone );
			System.out.println();
		}
		
		System.out.println("Таблица выведена");
	}
	
	// --------Закрытие--------
	public void CloseDB() throws ClassNotFoundException, SQLException
	{
		connection.close();
		statmt.close();
		resSet.close();
		
		System.out.println("Соединения закрыты");
	}
	
}
