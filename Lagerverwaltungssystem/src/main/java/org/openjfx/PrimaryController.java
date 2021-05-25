package org.openjfx;

import java.io.IOException;
import java.net.URL;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrimaryController implements Initializable {


    @FXML
    private TableView<org.openjfx.Products> table;
    @FXML
    private TableColumn<org.openjfx.Products, String> col_id;
    @FXML
    private TableColumn<org.openjfx.Products, String> col_name;
    @FXML
    private TableColumn<org.openjfx.Products, String> col_abl;
    @FXML
    private TableColumn<org.openjfx.Products, String> col_kat;
    @FXML
    private TableColumn<org.openjfx.Products, String> col_pre;

    @FXML
    private Button btn_wechsel;
    @FXML
    private Button btn_load;
    @FXML
    private Button btn_einkauf;

    @FXML
    private TextField text_suche;
    public PrimaryController() {
    }

    @FXML
    private void einkauf() throws IOException {
        org.openjfx.App.setRoot("third");
    }
    @FXML
    private void verkauf() throws IOException {
        org.openjfx.App.setRoot("secondary");
    }

    ObservableList<org.openjfx.Products> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Connection con = new org.openjfx.DaoImpl().establishConnection();
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from products");
            while (rs.next()) {
                oblist.add(new org.openjfx.Products(rs.getInt("id"), rs.getString("name"), rs.getString("kategorie"), rs.getDate("ablaufdatum"), rs.getDouble("preis")));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_kat.setCellValueFactory(new PropertyValueFactory<>("kategorie"));
        col_abl.setCellValueFactory(new PropertyValueFactory<>("ablaufdatum"));
        col_pre.setCellValueFactory(new PropertyValueFactory<>("preis"));

        table.setItems(oblist);

    }

    public void loadEintraege() {
        table.getItems().setAll(FXCollections.observableArrayList(oblist));
    }

    public void Suche() {
        String searchname = text_suche.getText();
        table.getItems().stream().filter(item -> item.getName()==searchname).findAny().ifPresent(item -> {
            table.getSelectionModel().select(item);
            table.scrollTo(item);
            }); }
}