package view;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import model.ModelManager;
import javafx.event.ActionEvent;


import java.awt.*;
import java.util.Optional;

public class StartController {
@FXML private MenuBar menuBar;
@FXML private Menu menuManage;
@FXML private MenuItem exit;
@FXML private MenuItem menuBoardGames;
@FXML private MenuItem menuBorrowing;
@FXML private MenuItem menuEvents;
@FXML private MenuItem menuStudents;
@FXML private MenuItem menuUpcomingGames;
private Scene scene;
private ModelManager modelManager;
private ViewHandler viewHandler;

public void init(ViewHandler viewHandler, Scene scene, ModelManager modelManager){
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.modelManager = modelManager;
}
    public Scene getScene()
    {
        return scene;
    }


    public void handleActions(Event event) {
            if(event.getSource() == menuBoardGames){
                viewHandler.openView("ManageBoardGames");
            }
            if(event.getSource() == menuBorrowing){
                viewHandler.openView("ManageBorrowing");
            }
            if(event.getSource() == menuStudents){
                viewHandler.openView("ManageStudents");
            }
            if(event.getSource() == menuEvents){
                viewHandler.openView("ManageEvents");
            }
            if(event.getSource() == menuUpcomingGames){
                viewHandler.openView("ManageUpcomingBoardGames");
            }

            if (event.getSource() == exit)
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you really want to exit?", ButtonType.YES, ButtonType.CANCEL);
                alert.setHeaderText(null);
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.YES)
                {
                    System.exit(0);
                }
            }

    }
}
