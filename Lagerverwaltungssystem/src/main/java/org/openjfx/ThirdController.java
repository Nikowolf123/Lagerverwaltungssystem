package org.openjfx;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ThirdController implements Initializable {

    public ObservableList<String> kategorieList;
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
    private ComboBox<String> dropbox;
    @FXML
    private Button btn_wechsel;
    @FXML
    private Button btn_einkauf;
    @FXML TextField text_name;
    @FXML TextField text_abl;
    @FXML DatePicker date_picker;
    @FXML Button btn_load;
    @FXML
    private void verkauf() throws IOException {
        App.setRoot("primary");
    }
    private int i = 0;
    ObservableList<Products> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        Connection con = new DaoImpl().establishConnection();
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from products");
            while (rs.next()) {
                oblist.add(new Products(rs.getInt("id"), rs.getString("name"), rs.getString("kategorie"), rs.getDate("ablaufdatum"), rs.getDouble("preis")));


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


        kategorieList = FXCollections.observableArrayList(
                "Lebensmittel",
                "Kleidung",
                "Getränke",
                "Werkzeug",
                "Baumaterial"
        );
        dropbox.getItems().addAll(kategorieList);

}



    public  void addProduct() {


        Statement stmt = null;

        Connection con = new DaoImpl().establishConnection();
        try {
            Class.forName("com.mysql.jdbc.Driver");

            stmt = con.createStatement();
            LocalDate date = date_picker.getValue();
            String sql =  ("INSERT products (id, name, kategorie, ablaufdatum, preis) VALUES (?, ?, ?, ?)");

            LocalDate date1 = date_picker.getValue();

            for (i = 0; i<1; i++) {
                try {

                    PreparedStatement st = con.prepareStatement(sql);
                    st.setInt(1, i);

                    st.setString(2, text_name.getText());
                    st.setString(3, dropbox.getValue());
                    st.setDate(4, java.sql.Date.valueOf(date1)) ;


                    // execute the preparedstatement insert
                    st.executeUpdate();
                    st.close();
                } catch (SQLException se) {
                    // log exception
                    throw se;
                }
            }



        } catch (SQLException | ClassNotFoundException | NullPointerException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sie haben nicht alle daten Ausgewählt");
            alert.setHeaderText("Bitte Kontrolieren Sie ob Name, Kategorie und das Ablaufdatum eingegeben wurde.");
            alert.setContentText("");
            alert.show();
        }


    }
    @FXML
    public void loadEintraege() {
        table.refresh();
    }






}