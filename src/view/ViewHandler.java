package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import model.ModelManager;

import java.io.IOException;

public class ViewHandler {
    private Stage stage;
    private ManageBorrowingController manageBorrowingController;
    private ManageBoardGameController manageBoardGameController;
    private ModelManager modelManager;
    private StartController startController;
    private ManageStudentsController manageStudentsController;
    private ManageEventsController manageEventsController;
    private ManageUpcomingBoardGamesController manageUpcomingBoardGamesController;

    public ViewHandler(Stage stage, ModelManager modelManager){
        this.stage = stage;
        this.modelManager = modelManager;
    }
    public void start()
    {
        loadViewMain();
        loadBoardGamesView();
        loadBorrowingView();
        loadStudentsView();
        loadEventsView();
        loadUpcomingBoardGamesView();
        openView("MainView");
    }

    public void openView(String id)
    {
        switch (id)
        {
            case "MainView":
                stage.setScene(startController.getScene());
                //reset
                break;
            case "ManageBoardGames":
                stage.setScene(manageBoardGameController.getScene());
                manageBorrowingController.reset();
                break;
            case "ManageBorrowing":
                stage.setScene(manageBorrowingController.getScene());
                manageBorrowingController.reset();
                break;
            case "ManageStudents":
                stage.setScene(manageStudentsController.getScene());
                manageStudentsController.restart();
                break;
            case "ManageEvents":
                stage.setScene(manageEventsController.getScene());
                manageEventsController.restart();
                break;
            case "ManageUpcomingBoardGames":
                stage.setScene(manageUpcomingBoardGamesController.getScene());
                manageUpcomingBoardGamesController.restart();
                break;
        }

        String title = "";

        if(stage.getScene().getRoot().getUserData() !=null)
        {
            title = stage.getScene().getRoot().getUserData().toString();
        }

        stage.setTitle(title);
        stage.show();
    }

    private void loadViewMain()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Start.fxml"));
            Region root = loader.load();
            startController = loader.getController();
            startController.init(this, new Scene(root), modelManager);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void loadBoardGamesView()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ManageBoardGames.fxml"));
            Region root = loader.load();
            manageBoardGameController = loader.getController();
            manageBoardGameController.init(this, new Scene(root), modelManager);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void loadBorrowingView()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ManageBorrowing.fxml"));
            Region root = loader.load();
            manageBorrowingController = loader.getController();
            manageBorrowingController.init(this, new Scene(root), modelManager);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void loadStudentsView(){
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ManageStudents.fxml"));
            Region root = loader.load();
            manageStudentsController = loader.getController();
            manageStudentsController.init(this, new Scene(root), modelManager);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void loadEventsView(){
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ManageEvents.fxml"));
            Region root = loader.load();
            manageEventsController = loader.getController();
            manageEventsController.init(this, new Scene(root), modelManager);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void loadUpcomingBoardGamesView(){
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ManageUpcomingBoardGames.fxml"));
            Region root = loader.load();
            manageUpcomingBoardGamesController = loader.getController();
            manageUpcomingBoardGamesController.init(this, new Scene(root), modelManager);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

