package view;
import javafx.application.Application;
import javafx.stage.Stage;
import model.ModelManager;

public class TestGUI extends Application {

    public void start(Stage window) throws Exception
    {
        ModelManager modelManager = new ModelManager("upcoming.bin", "games.bin", "students.bin", "events.bin", "website/xml/eventsWebsite.xml", "website/xml/boardGamesWebsite.xml", "website/xml/upcomingBoardGamesWebsite.xml");
        ViewHandler viewHandler = new ViewHandler(window, modelManager);
        viewHandler.start();
    }
}
