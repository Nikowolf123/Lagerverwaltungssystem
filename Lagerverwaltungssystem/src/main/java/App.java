package _20210325_Convert_To_DAO2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class App {    

    static String url = "jdbc:mysql://localhost/phpmyadmin";
    public static void main(String[] args) {
        
        createNewTable();

        newPerson("Stolz");
        newEmail("s.stolz@tsn.at", 1);
        newEmail("s.newslists@gmail.com", 1);
    }

    public static void newPerson(String name){
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        String sql = "INSERT INTO persons(name) VALUES(?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void newEmail(String address, int person_id){
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        String sql = "INSERT INTO emails(address, person_id) VALUES(?, ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, address);
            pstmt.setInt(2, person_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void createNewTable() {

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        String sql_persons = "CREATE TABLE IF NOT EXISTS persons (\n"
                + "	id integer not null PRIMARY KEY,\n"
                + "	name varchar(255)\n"
                + ");";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql_persons);
            System.out.println("Table persons is created!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql_emails = "CREATE TABLE IF NOT EXISTS emails (\n"
                + "	id integer not null PRIMARY KEY,\n"
                + "	address varchar(255),\n"
                + "	person_id int\n"
                + ");";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql_emails);
            System.out.println("Table emails is created!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

