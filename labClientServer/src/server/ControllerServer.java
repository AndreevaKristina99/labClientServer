package server;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerServer implements Initializable {

    public TextArea textAreaServer;
    public Button startServerButt;
    public Button updateButt;

    MasterServer server;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        server = new MasterServer();
    }

    public void startServer(ActionEvent actionEvent) {
        server.start();
    }

    public void update(ActionEvent actionEvent) {
        textAreaServer.setText(server.getMessages());
    }
}
