package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class Controller {

    @FXML
    public TextField inputField;
    @FXML
    public TextField outputField;
    @FXML
    public RadioButton celsiusRad1;
    @FXML
    public RadioButton celsiusRad2;
    @FXML
    public RadioButton kelvinRad1;
    @FXML
    public RadioButton kelvinRad2;
    @FXML
    public RadioButton fahrenheitRad1;
    @FXML
    public RadioButton fahrenheitRad2;
    
    @FXML
    public ToggleGroup inputGroup = new ToggleGroup();
    @FXML
    public ToggleGroup outputGroup = new ToggleGroup();


    @FXML
    public void convert() {

        if (inputField.getText() != null && inputGroup.getSelectedToggle() != null && outputGroup.getSelectedToggle() != null) {

            if (celsiusRad1.isSelected() && celsiusRad2.isSelected()) {
                double temp = Double.parseDouble(inputField.getText());
                outputField.setText(Double.toString(temp));

            } else if (celsiusRad1.isSelected() && fahrenheitRad2.isSelected()) {
                double temp = Double.parseDouble(inputField.getText());
                temp = ((temp * 9 / 5) + 32);
                outputField.setText(Double.toString(temp));
            } else if (celsiusRad1.isSelected() && kelvinRad2.isSelected()) {
                double temp = Double.parseDouble(inputField.getText());
                temp = (temp + 273.15);
                outputField.setText(Double.toString(temp));
            } else if (fahrenheitRad1.isSelected() && fahrenheitRad2.isSelected()) {
                double temp = Double.parseDouble(inputField.getText());
                outputField.setText(Double.toString(temp));

            } else if (fahrenheitRad1.isSelected() && celsiusRad2.isSelected()) {
                double temp = Double.parseDouble(inputField.getText());
                temp = ((temp - 32) * 5 / 9);
                outputField.setText(Double.toString(temp));
            } else if (fahrenheitRad1.isSelected() && kelvinRad2.isSelected()) {
                double temp = Double.parseDouble(inputField.getText());
                temp = ((temp - 32) * 5 / 9 + 273.15);
                outputField.setText(Double.toString(temp));
            } else if (kelvinRad1.isSelected() && kelvinRad2.isSelected()) {
                double temp = Double.parseDouble(inputField.getText());
                outputField.setText(Double.toString(temp));

            } else if (kelvinRad1.isSelected() && celsiusRad2.isSelected()) {
                double temp = Double.parseDouble(inputField.getText());
                temp = (temp - 273.15);
                outputField.setText(Double.toString(temp));
            } else if (kelvinRad1.isSelected() && fahrenheitRad2.isSelected()) {
                double temp = Double.parseDouble(inputField.getText());
                temp = ((temp - 273.15) * 9 / 5 + 32);
                outputField.setText(Double.toString(temp));
            }


        }
    }


}



