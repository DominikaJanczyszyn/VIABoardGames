package view;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ModelManager;

public class TestDominikaGUI extends Application {

    public void start(Stage window) throws Exception
    {
        ModelManager modelManager = new ModelManager("upcoming.bin", "games.bin", "students.bin", "events.bin", "eventsWebsite.xml", "boardGamesWebsite.xml", "upcomingBoardGamesWebsite.xml");
        ViewHandler viewHandler = new ViewHandler(window, modelManager);
        viewHandler.start();
    }
}
