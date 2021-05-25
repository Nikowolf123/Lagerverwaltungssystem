package org.openjfx;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class SecondaryController implements Initializable {


    @FXML
    private TableView<Products> table;
    @FXML
    private TableColumn<Products, String> col_id;
    @FXML
    private TableColumn<Products, String> col_name;
    @FXML
    private TableColumn<Products, String> col_abl;
    @FXML
    private TableColumn<Products, String> col_kat;

    @FXML
    private TableColumn<Products, String> col_pre;

    @FXML
    private Button btn_wechsel;
    @FXML
    private Button btn_verkauf;
@FXML
private TextField text_idloeschen;
    @FXML
    private void verkauf() throws IOException {
        App.setRoot("primary");
    }

    ObservableList<Products> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Connection con = new DaoImpl().establishConnection();
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from products");
            while (rs.next()) {
                oblist.add(new Products(rs.getInt("id"), rs.getString("name"), rs.getString("kategorie"), rs.getDate("ablaufdatum"),( rs.getDouble("preis"))));


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

    public void loeschen() {
        Products selectedItem = table.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            oblist.remove(selectedItem);


        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Welches Produkt wurde Verkauft");
            alert.setHeaderText("Wählen ein Produkt aus!");
            alert.setContentText("Es wurde kein Produkt gewählt!");
            alert.show();


        }
    }
    @FXML
        public void deleteproduct() {
            try {
                Connection con = new DaoImpl().establishConnection();
                PreparedStatement stmt = con.prepareStatement("DELETE FROM products WHERE name=?");
                stmt.setString(1, text_idloeschen.getText());
                stmt.execute();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Schreiben sie den Namen des Verkaufen Produktes");
                alert.setHeaderText("Bitte Kontrollieren Sie ihre eingabe. ");
                alert.setContentText("");
                alert.show();

        }
    }
}
