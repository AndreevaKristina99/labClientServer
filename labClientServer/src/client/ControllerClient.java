package client;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ControllerClient implements Initializable {

    public TextArea sendedMessagesTextArea;
    public TextField sendMessageTextField;
    public Button sendButt;

    Client client;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        client = new Client(sendedMessagesTextArea);
        client.start();
        sendedMessagesTextArea.appendText("Номер Вашего подключения: "+String.valueOf(client.getNumber()));
    }
}
