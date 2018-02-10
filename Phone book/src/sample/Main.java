package sample;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.*;

public class Main extends Application {

    private TableView<Person> table = new TableView<Person>();
    private final ObservableList<Person> data =
            FXCollections.observableArrayList();
    final HBox hb = new HBox();

    private BufferedReader reader = null;
    private FileWriter writer = null;
    private String filePath = "Persons.txt";
    private String[] stab;

    public static void main(String[] args) throws IOException {
        launch(args);
        File file = new File("Persons.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public void czytaj() throws IOException {
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            if (line != "") {
                while (line != null) {
                    stab = line.split("\\s");
                    data.add(new Person(stab[0], stab[1], stab[2]));
                    line = reader.readLine();
                } };
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    public void zapisz() throws IOException {

        try {
            writer = new FileWriter(filePath);
            String s1= "";
            String s2 = "";
            String s3 = "";

            for (Person p : data) {
                writer.write(p.getFirstName() + " " + p.getLastName() + " " + p.getPhone() + "\r\n");
            }
        }
        finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    @Override
    public void start(Stage stage) {
        try {
            czytaj();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Scene scene = new Scene(new Group());
        stage.setTitle("");
        stage.setWidth(600);
        stage.setHeight(600);

        final Label label = new Label("Phone List");
        label.setFont(new Font("Arial", 25));
        table.setEditable(true);

        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setMinWidth(175);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("firstName"));

        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setMinWidth(175);

        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("lastName"));

        TableColumn PhoneCol = new TableColumn("Phone");
        PhoneCol.setMinWidth(200);
        PhoneCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("Phone"));

        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol, PhoneCol);

        final TextField addFirstName = new TextField();
        addFirstName.setPromptText("First Name");
        addFirstName.setMaxWidth(firstNameCol.getPrefWidth());
        final TextField addLastName = new TextField();
        addLastName.setMaxWidth(lastNameCol.getPrefWidth());
        addLastName.setPromptText("Last Name");
        final TextField addPhone = new TextField();
        addPhone.setMaxWidth(PhoneCol.getPrefWidth());
        addPhone.setPromptText("Phone");

        final Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                data.add(new Person(
                        addFirstName.getText(),
                        addLastName.getText(),
                        addPhone.getText()));
                addFirstName.clear();
                addLastName.clear();
                addPhone.clear();
                try {
                    zapisz();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        final Button updateButton = new Button("Update");
        updateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                data.set(table.getSelectionModel().getSelectedIndex(), new Person(
                        addFirstName.getText(),
                        addLastName.getText(),
                        addPhone.getText())
                );
                addFirstName.clear();
                addLastName.clear();
                addPhone.clear();
                try {
                    zapisz();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        final Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                data.remove(table.getSelectionModel().getSelectedItem());
                try {
                    zapisz();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });


        hb.getChildren().addAll(addFirstName, addLastName, addPhone, addButton, updateButton, deleteButton);
        hb.setSpacing(3);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }

    public static class Person {

        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty Phone;

        private Person(String fName, String lName, String Phone) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.Phone = new SimpleStringProperty(Phone);
        }

        public String getFirstName() {
            String ss1 = "...";
            if(firstName.get().equals("")) {
                return ss1;
            }
            else{
                return firstName.get();}
        }

        public void setFirstName(String fName) {
            firstName.set(fName);
        }

        public String getLastName() {
            String ss2 = "...";
            if(lastName.get().equals("")) {
                return ss2;
            }
            else{
                return lastName.get();}
        }

        public void setLastName(String fName) {
            lastName.set(fName);
        }

        public String getPhone() {
            String ss3 = "...";
            if(Phone.get().equals("")) {
                return ss3;
            }
            else{
                return Phone.get();}
        }

        public void setPhone(String fName) {
            Phone.set(fName);
        }
    }
}