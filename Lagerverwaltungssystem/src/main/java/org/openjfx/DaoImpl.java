package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;


public class DaoImpl implements DaoInter {

    Connection connection;
    ObservableList<Products> oblist = FXCollections.observableArrayList();

    public DaoImpl() {
        establishConnection();

    }

    public Connection establishConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/products", "root", "");
        } catch (ClassNotFoundException e) {
            System.err.println("Can't find MYSQL Class!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error with SQL");
            e.printStackTrace();
        }
        connection = con;
        return con;

    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println("Error with closing the DB Connection");
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Products> getAllInvoices() {
        return null;
    }








}