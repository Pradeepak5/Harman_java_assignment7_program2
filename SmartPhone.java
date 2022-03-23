package com.harman.Smartphone_Management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class SmartPhone {

	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		int option;
		while(true) {
			System.out.println("1. Add the smartphones ");
			System.out.println("2. View All smartphones ");
			System.out.println("3. Search phones based on brand");
			System.out.println("4. Edit the smart phone data using imei number ");
			System.out.println("5. Delete the smart phone data from db using imei number");
			System.out.println("6. EXIT");
			System.out.println("Enter Your Choice from the above Menu :");
			option=in.nextInt();
			switch(option) {
			case 1:
				try {
					Connection c= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/smartphonedb?autoReconnect=true&useSSL=false","root","");
					Scanner s=new Scanner(System.in);
					String imei,brand,model,price;
					System.out.println("imei :");
					imei=s.nextLine();
					System.out.println("Brand :");
					brand=s.nextLine();
					System.out.println("Model :");
					model=s.nextLine();
					System.out.println("Price :");
					price=s.nextLine();
					
					Statement stmt=c.createStatement();
					stmt.executeUpdate("INSERT INTO `phones`(`imei`, `brand`, `model`, `price`) VALUES("+imei+",'"+brand+"','"+model+"',"+price+")");
					System.out.println("Inserted Successfully");
				}
				catch(Exception e) {
					System.out.println(e);
				}
				break;
			case 2:
				try {
					Connection c= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/smartphonedb?autoReconnect=true&useSSL=false","root","");
					Statement stmt=c.createStatement();
					ResultSet rs=stmt.executeQuery("select * from phones");
					while(rs.next()) {
						System.out.println("ID :"+rs.getInt("ID"));
						System.out.println("IMEI :"+rs.getBigDecimal("imei"));
						System.out.println("Brand :"+rs.getString("brand"));
						System.out.println("Model :"+rs.getString("model"));
						System.out.println("Price :"+rs.getInt("price"));
						
						
					}
				}
				catch(Exception e) {
					System.out.println(e);
				}
				break;
			case 3:
				try {
					Connection c= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/smartphonedb?autoReconnect=true&useSSL=false","root","");
					
					Scanner s=new Scanner(System.in);
					System.out.println("Search name by Brand name :");
					String bd=s.nextLine();
					Statement stmt=c.createStatement();
					ResultSet rs=stmt.executeQuery("select * from phones where brand like '%"+bd+"%'");
					while(rs.next()) {
						System.out.println("ID :"+rs.getInt("ID"));
						System.out.println("IMEI :"+rs.getBigDecimal("imei"));
						System.out.println("Brand :"+rs.getString("brand"));
						System.out.println("Model :"+rs.getString("model"));
						System.out.println("Price :"+rs.getInt("price"));
					}
					
				}
				catch(Exception e){
					System.out.println(e);
				}
				break;
			case 4:
				try {
					Connection c= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/smartphonedb?autoReconnect=true&useSSL=false","root","");
					Scanner a=new Scanner(System.in);
					String brand,model,price,Imei;
					System.out.println("Enter imei to update phone data :");
				    Imei=a.nextLine();
					System.out.println("Brand :");
					brand=a.nextLine();
					System.out.println("Model :");
					model=a.nextLine();
					System.out.println("Price :");
					price=a.nextLine();
					Statement stmt=c.createStatement();
					stmt.executeUpdate("update phones set brand='"+brand+"',model='"+model+"',price="+price+" WHERE imei="+Imei+"");
					System.out.println("Updated Successfully");
					}					
					
				catch(Exception e) {
					System.out.println(e);
				}
				break;
			case 5:
				try {
					Connection c= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/smartphonedb?autoReconnect=true&useSSL=false","root","");
					Scanner b=new Scanner(System.in);
					String Imei;
					System.out.println("Delete Phone data using imei :");
					Imei=b.nextLine();
					Statement stmt=c.createStatement();
					stmt.executeUpdate("delete from phones where imei="+Imei+"");
					System.out.println("Phone Data deleted successfully.");
					
				}
				catch(Exception e) {
					System.out.println(e);
				}
				break;
			case 6:
				System.exit(0);
			default:
				System.out.println("You choosed Ivalid option .....try again by choosing valid choice!");
				break;
		}
	}

}
}
