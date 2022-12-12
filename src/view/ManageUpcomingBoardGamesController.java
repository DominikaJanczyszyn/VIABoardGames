package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.*;

import java.util.Optional;

public class ManageUpcomingBoardGamesController {
    private ViewHandler viewHandler;
    private Scene scene;
    private  ModelManager manager;
    @FXML private TextField nameTextFieldAdd;
    @FXML private TextField numberTextFieldAdd;
    @FXML private TextArea descriptionTextFieldAdd;
    @FXML private Button addButton;
    /////// 2 tab////
    @FXML private TextField nameEdit;
    @FXML private TextField numberEdit;
    @FXML private TextField search;
    @FXML private TextArea descriptionEdit;
    @FXML private ListView<UpcomingBoardGame> searchList;
    @FXML private Button edit;
    @FXML private Button vote;
    @FXML private Button remove;
    @FXML private MenuBar menuBar;
    @FXML private Menu menuManage;
    @FXML private Menu exitMenu;
    @FXML private MenuItem exit;
    @FXML private MenuItem menuBoardGames;
    @FXML private MenuItem menuBorrowing;
    @FXML private MenuItem menuStudents;
    @FXML private MenuItem menuEvents;
    @FXML private MenuItem menuUpcomingGames;
    @FXML private TableView<UpcomingBoardGame> tab;
    @FXML private TableColumn<UpcomingBoardGame, String> titleTab;
    @FXML private TableColumn<UpcomingBoardGame, String > numberTab;
    @FXML private TableColumn<UpcomingBoardGame, String> descriptonTab;
    @FXML private TableColumn<UpcomingBoardGame, Integer> votesTab;
    private UpcomingBoardGame upcomingBoardGame;

    private UpcomingBoardGamesList upcomingBoardGamesList;
    public void init(ViewHandler viewHandler, Scene scene, ModelManager modelManager){
        this.viewHandler = viewHandler;
        this.scene = scene;
        this.manager = modelManager;
    }
    public void reset(){
        viewHandler.loadUpcomingBoardGamesView();
    }
    public void initialization() {
        this.manager = new ModelManager("upcoming.bin", "games.bin", "students.bin", "events.bin", "website/xml/eventsWebsite.xml", "website/xml/boardGamesWebsite.xml", "website/xml/upcomingBoardGamesWebsite.xml");
        this.upcomingBoardGamesList = manager.getAllUpcomingGames();
        if(searchList != null){
            updateGamesView();
        }
        if(tab != null){
            tab.getItems().clear();
            titleTab.setCellValueFactory(new PropertyValueFactory<>("name"));
            numberTab.setCellValueFactory(new PropertyValueFactory<>("numberOfPlayers"));
            descriptonTab.setCellValueFactory(new PropertyValueFactory<>("description"));
            votesTab.setCellValueFactory(new  PropertyValueFactory<>("numberOfVotes"));
            for(int i = 0; i < upcomingBoardGamesList.size(); i++){
                tab.getItems().add(upcomingBoardGamesList.getUpcomingBoardGameByIndex(i));
            }
        }

    }
    public Scene getScene()
    {
        return scene;
    }
    public void updateGamesView() {
        searchList.getItems().clear();
        for (int i = 0; i < upcomingBoardGamesList.size(); i++) {
            searchList.getItems().add(upcomingBoardGamesList.getUpcomingBoardGameByIndex(i));
        }
    }

    public void handleActions(ActionEvent e) {///// ask allan
        if (e.getSource() == menuBoardGames) {
            viewHandler.openView("ManageBoardGames");
        }
        if (e.getSource() == menuBorrowing) {
            viewHandler.openView("ManageBorrowing");
        }
        if (e.getSource() == menuStudents) {
            viewHandler.openView("ManageStudents");
        }
        if (e.getSource() == menuEvents) {
            viewHandler.openView("ManageEvents");
        }
        if(e.getSource() == upcomingBoardGame){
            viewHandler.openView("ManageUpcomingBoardGames");
        }
        if(e.getSource() == menuUpcomingGames){
            viewHandler.openView("ManageUpcomingBoardGames");
        }
        if (e.getSource() == exit) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you really want to exit?", ButtonType.YES, ButtonType.CANCEL);
            alert.setHeaderText(null);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.YES) {
                System.exit(0);
            }
        }
        if(e.getSource() == addButton){
            if (!nameTextFieldAdd.getText().equals("") && !numberTextFieldAdd.getText().equals("") && !descriptionTextFieldAdd.getText().equals("")) {
                String title =  nameTextFieldAdd.getText();
                String numberOfPlayers = numberTextFieldAdd.getText();
                String description = descriptionTextFieldAdd.getText();
                UpcomingBoardGame upcomingBoardGame = new UpcomingBoardGame(title, numberOfPlayers, description);
                upcomingBoardGamesList.addUpcomingGame(upcomingBoardGame);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("The game was added to the system.");
                    alert.show();
                    nameTextFieldAdd.clear();
                    numberTextFieldAdd.clear();
                    descriptionTextFieldAdd.clear();
                    manager.saveAllUpcomingGames(upcomingBoardGamesList);
                    updateWebsite();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("No data entered.");
                alert.show();
            }
        }
        if(e.getSource() == edit){
            if (upcomingBoardGame != null && nameEdit.getText() != "" && numberEdit.getText() != "" && descriptionEdit.getText() != "") {
                String title = nameEdit.getText();
                String number = numberEdit.getText();
                String description = descriptionEdit.getText();
                upcomingBoardGame.setName(title);
                upcomingBoardGame.setNumberOfPlayers(number);
                upcomingBoardGame.setDescription(description);
                updateWebsite();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("The game has been edited.");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("No data entered.");
                alert.show();
            }
            manager.saveAllUpcomingGames(upcomingBoardGamesList);
            updateGamesView();
            updateWebsite();
            nameEdit.clear();
            numberEdit.clear();
            descriptionEdit.clear();
        }
        if(e.getSource() == vote){
            if(upcomingBoardGame != null) {
                upcomingBoardGame.voteForAGame();
                manager.saveAllUpcomingGames(upcomingBoardGamesList);
                updateGamesView();
                updateWebsite();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("You voted for the game : " + upcomingBoardGame.getName());
                alert.show();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("No game has been selected.");
                alert.show();
            }
        }
        if(e.getSource()== remove){
            if (upcomingBoardGame != null) {
                upcomingBoardGamesList.removeUpcomingGame(upcomingBoardGame);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("The game has been removed.");
                alert.show();
                manager.saveAllUpcomingGames(upcomingBoardGamesList);
                updateGamesView();
                updateWebsite();
                nameEdit.clear();
                numberEdit.clear();
                descriptionEdit.clear();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("No game has been selected.");
                alert.show();
            }
        }
    }


    public void select(MouseEvent mouseEvent) {
        if(mouseEvent.getSource() == searchList){
            this.upcomingBoardGame = searchList.getSelectionModel().getSelectedItem();
            nameEdit.setText(upcomingBoardGame.getName());
            numberEdit.setText(upcomingBoardGame.getNumberOfPlayers());
            descriptionEdit.setText(upcomingBoardGame.getDescription());
        }
    }

    public void search(KeyEvent keyEvent) {
        if(keyEvent.getSource() == search){
            search.textProperty().addListener(new ChangeListener() {
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    searchList.getItems().clear();

                    UpcomingBoardGamesList boardGames = manager.containUpcomingBoardGame(search.getText());
                    for (int i = 0; i < boardGames.size(); i++) {
                        searchList.getItems().add(upcomingBoardGamesList.getUpcomingBoardGameByIndex(i));
                    }
                }
            });
        }
    }
    private void updateWebsite()
    {
        manager.saveAllUpcomingBoardGamesXML(manager.getAllUpcomingGames());
    }

}
