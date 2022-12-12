package view;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.StudentList;
import model.ModelManager;
import model.BoardGamesList;
import model.*;
import myexceptions.*;
import javafx.scene.control.RadioButton;

import java.util.Optional;


public class ManageBorrowingController{
    @FXML private Tab borrowTab, editTab, returnTab, reserveTab, cancelTab, infoTab;
    @FXML private MenuBar menuBar;
    @FXML private Menu menuManage;
    @FXML private MenuItem exit;
    @FXML private MenuItem menuBoardGames;
    @FXML private MenuItem menuBorrowing;
    @FXML private MenuItem menuStudents;
    @FXML private MenuItem menuEvents;
    @FXML private MenuItem menuUpcomingGames;
    /// LEND TAB///
    @FXML private TextField gameTextField, studentTextField, nameTextField2, lastNameTextField2, idTextField2, lendingTextField;
    @FXML private ListView<BoardGame> gameListView;
    @FXML private ListView<Student> studentListView;
    @FXML private Button borrowButton;
    //// EDIT RESERVATION TAB ////
    @FXML private TextField gameTextFieldEdit, titleTextFieldEdit, borrowerTextFieldEdit, memberTextFieldEdit, nameTextFieldEdit, lastNameTextFieldEdit, idTextFieldEdit ;
    @FXML private ListView<BoardGame> gameListViewEdit;
    @FXML private ListView<Student> memberViewListEdit;
    @FXML private Button changeButton;
    @FXML private RadioButton radio1, radio2, radio3, radio4, radio5;


    /// RESERVE TAB ///
    @FXML private TextField gameTextFieldReserve, studentTextFieldReserve;
    @FXML private ListView<BoardGame>gameListViewReserve;
    @FXML private ListView<Student> studentListViewReserve;
    @FXML private Button reserveButton;
    ////CANCEL RESERVATION TAB/////
    @FXML private ListView<Student> studentListViewCancel;
    @FXML private TextField studentTextFieldCancel, gameTextFieldCancel;
    @FXML private ListView<BoardGame> reservationListView;
    @FXML private Button cancelButton;

    ///////RETURN TAB//////////////
    @FXML private TextField gameTextFieldReturn, titleTextFieldReturn, borrowerTextFieldReturn;
    @FXML private ListView<BoardGame> gameListViewReturn;
    @FXML private Button returnButton;
    //////INFO TAB//////////
    @FXML TableView<BoardGame> tab;
    @FXML
    TableColumn <BoardGame, String>titleColl;
    @FXML
    TableColumn<BoardGame, String> borrowerColl;
    @FXML
    TableColumn<BoardGame, String> reservantColl;
    @FXML TableColumn<BoardGame, String> dateColl;
    @FXML TableColumn<BoardGame, Double> ratingColl;
    private ModelManager manager;
    private BoardGamesList boardGamesList;
    private StudentList studentList;
    private BoardGame boardGame;
    private Student student;
    private ViewHandler viewHandler;
    private Scene scene;
    private EventList eventList;

    public void init(ViewHandler viewHandler, Scene scene, ModelManager modelManager){
        this.viewHandler = viewHandler;
        this.scene = scene;
        this.manager = modelManager;

    }
    public void reset()
    {
        viewHandler.loadBorrowingView();
    }
    public Scene getScene()
    {
        return scene;
    }
    public void tabChanger(Event event) {
        this.manager = new ModelManager("upcoming.bin", "games.bin", "students.bin", "events.bin", "website/xml/eventsWebsite.xml", "website/xml/boardGamesWebsite.xml", "website/xml/upcomingBoardGamesWebsite.xml");
        this.boardGamesList = manager.getAllBoardGames();
        this.studentList = manager.getAllStudents();
        this.eventList = manager.getAllEvents();
        if(borrowTab.isSelected()){
            updateStudentArea(studentList, studentListView);
            updateNotLendGamesArea(boardGamesList, gameListView);
        }
        else if(editTab.isSelected()){
            updateLentBoardGames(boardGamesList, gameListViewEdit);
            updateStudentArea(studentList, memberViewListEdit);
        }
        else if(reserveTab.isSelected()){
            updateStudentArea(studentList, studentListViewReserve);
            updateLentBoardGames(boardGamesList, gameListViewReserve);
        }
        else if(cancelTab.isSelected()){
            updateStudentArea(studentList, studentListViewCancel);
        }
        else if(returnTab.isSelected()){
            updateLentBoardGames(boardGamesList, gameListViewReturn);
        }
        else if(infoTab.isSelected()){
            tab.getItems().clear();
            titleColl.setCellValueFactory(new PropertyValueFactory<>("name"));
            borrowerColl.setCellValueFactory(new PropertyValueFactory<>("borrower"));
            reservantColl.setCellValueFactory(new PropertyValueFactory<>("reservants"));
            dateColl.setCellValueFactory(new  PropertyValueFactory<>("dateOfLoan"));
            ratingColl.setCellValueFactory(new PropertyValueFactory<>("rating"));
            for(int i = 0; i < boardGamesList.size(); i++){
                tab.getItems().add(boardGamesList.getBoardGameByIndex(i));
            }
        }

    }
    private void updateStudentArea(StudentList students, ListView listView) {
        listView.getItems().clear();
        for (int i = 0; i < students.size(); i++) {
            if(students.getStudentByIndex(i).isAMember()){
                listView.getItems().add(students.getStudentByIndex(i));
            }

        }
    }
    private void updateNotLendGamesArea(BoardGamesList games, ListView listView){
        listView.getItems().clear();
        for(int i = 0; i < games.size(); i++){
            if(!games.getBoardGameByIndex(i).isLent()){
                listView.getItems().add(games.getBoardGameByIndex(i));
            }
        }
    }
    private void updateLentBoardGames(BoardGamesList games, ListView listView){
        listView.getItems().clear();
        for(int i = 0; i < games.size(); i++){
            if(games.getBoardGameByIndex(i).isLent()){
                listView.getItems().add(games.getBoardGameByIndex(i));
            }
        }
    }
    private void updateReservedBardGamesArea(BoardGamesList games, ListView listView, Student student){
        listView.getItems().clear();
        for (int i = 0; i < games.size(); i++){
            Student[] reservants = games.getBoardGameByIndex(i).getAllReservants();
            for(int j = 0; j < reservants.length; j++){
                Student student1 = reservants[j];
                if(student1.equals(student)){
                    listView.getItems().add(games.getBoardGameByIndex(i));
                }
            }
        }
    }
    public void buttonListener(ActionEvent e) throws IsLentException{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (e.getSource() == borrowButton) {
            if(student != null && boardGame!=null){
                String person = student.toString();
                String game = boardGame.getName();
                alert.setHeaderText("You lent:\n " + game);
                alert.setContentText("borrower:\n" + person);
                boardGame.lentBoardGame(student);
            }
            else if(boardGame!=null && nameTextField2.getText()!="" && lastNameTextField2.getText()!="" &&idTextField2.getText()!="") {
                String name = nameTextField2.getText();
                String lastName = lastNameTextField2.getText();
                try{
                    int Id = Integer.parseInt(idTextField2.getText());
                    Student student1 = new Student(name, lastName, Id);
                    studentList.addStudent(student1);
                    String game = boardGame.getName();
                    alert.setHeaderText(null);
                    if(student1.isAMember() == false && !manager.getAllBoardGames().isABorrower(student1)){
                        boardGame.lentBoardGame(student1);
                        alert.setHeaderText("You lent:\n " + game);
                        alert.setContentText("borrower:\n" + student1);
                    }else{
                        alert.setContentText("This student is not allowed to borrow game!\n Because this student borrowed one game already.");
                    }
                }catch (IllegalArgumentException ex){
                    alert.setHeaderText("ERROR!");
                    alert.setContentText("VIA ID has to contain 6 digits");
                }
            }else{
                alert.setHeaderText("ERROR!");
                alert.setContentText("Please, fill in all fields");
            }
            boardGame = null;
            student = null;
            studentTextField.clear();
            gameTextField.clear();
            nameTextField2.clear();
            lastNameTextField2.clear();
            idTextField2.clear();
            alert.showAndWait();
            manager.saveAllGames(boardGamesList);
            manager.saveAllStudents(studentList);
            updateStudentArea(studentList, studentListView);
            updateNotLendGamesArea(boardGamesList, gameListView);
            updateWebsite(boardGamesList);
        }
        else if(e.getSource() == changeButton){
            if(student != null && boardGame !=null){
                System.out.println(student);
                boardGame.lentBoardGame(student);
                alert.setHeaderText("YOU CHANGED BORROWER");
                alert.setContentText("You changed borrower to: "+student);

            }
            else if(nameTextFieldEdit.getText()!="" && lastNameTextFieldEdit.getText()!="" &&idTextFieldEdit.getText()!="" && boardGame!=null ){
                String name = nameTextFieldEdit.getText();
                String lastName = lastNameTextFieldEdit.getText();
                Student student1 = boardGame.getBorrower();
                try{
                    int Id = Integer.parseInt(idTextFieldEdit.getText());
                    student1.setVIAID(Id);
                    student1.setFirstName(name);
                    student1.setLastName(lastName);
                }catch (IllegalArgumentException er){
                    alert.setHeaderText("ERROR!");
                    alert.setContentText("VIA ID has to contain 6 digits");
                }
            }
            else{
                alert.setHeaderText("ERROR!");
                alert.setContentText("Please, fill in all fields");
            }
            boardGame = null;
            student = null;
            titleTextFieldEdit.clear();
            borrowerTextFieldEdit.clear();
            gameTextFieldEdit.clear();
            memberTextFieldEdit.clear();
            nameTextFieldEdit.clear();
            lastNameTextFieldEdit.clear();
            idTextFieldEdit.clear();
            alert.showAndWait();
            manager.saveAllGames(boardGamesList);
            manager.saveAllStudents(studentList);
            updateStudentArea(studentList, memberViewListEdit);
            updateLentBoardGames(boardGamesList, gameListViewEdit);
            updateWebsite(boardGamesList);

        }
        else if(e.getSource() == reserveButton){
            if(boardGame!=null && student !=null){
                boardGame.reserve(student);
                alert.setHeaderText("You made a reservation");
                alert.setContentText(student+" reserved a game "+boardGame);
            }

            else{
                alert.setHeaderText("ERROR!");
                alert.setContentText("You must choose student and a game!");
            }
            alert.showAndWait();
            boardGame = null;
            student = null;
            manager.saveAllGames(boardGamesList);
            manager.saveAllStudents(studentList);
            gameTextFieldReserve.clear();
            studentTextFieldReserve.clear();
            updateWebsite(boardGamesList);
        }
        else if(e.getSource() == returnButton){
            if(boardGame != null){
                alert.setHeaderText("The game has been returned");
                if(boardGame.getBorrower().isAMember()==false  && !boardGamesList.isAnOwner(boardGame.getBorrower())  && !eventList.isAParticipant(boardGame.getBorrower())){
                    studentList.removeGuest(boardGame.getBorrower());
                }
                if(radio1.isSelected()) boardGame.rate(1);
                if(radio2.isSelected()) boardGame.rate(2);
                if(radio3.isSelected()) boardGame.rate(3);
                if(radio4.isSelected()) boardGame.rate(4);
                if(radio5.isSelected()) boardGame.rate(5);
                boardGame.returnBoardGame();
            }else{
                alert.setHeaderText("ERROR!");
                alert.setContentText("You must choose a game!");
            }
            boardGame = null;
            student = null;
            titleTextFieldReturn.clear();
            borrowerTextFieldReturn.clear();
            gameTextFieldReturn.clear();
            gameListViewReturn.getItems().clear();
            alert.showAndWait();
            manager.saveAllGames(boardGamesList);
            manager.saveAllStudents(studentList);
            updateWebsite(boardGamesList);
            boardGamesList = manager.getAllBoardGames();
            updateLentBoardGames(boardGamesList, gameListViewReturn);
        }
        else if(e.getSource() == cancelButton){
            if(student != null && boardGame != null){
                System.out.println("tutaj");
                boardGame.cancelReservation(student);
                alert.setHeaderText("The reservation has been canceled.");
            }
            else {
                alert.setHeaderText("ERROR!");
                alert.setContentText("You must choose student and a game!");
            }
            boardGame = null;
            student = null;
            studentTextFieldCancel.clear();
            gameTextFieldCancel.clear();
            alert.showAndWait();
            manager.saveAllGames(boardGamesList);
            manager.saveAllStudents(studentList);
            updateStudentArea(studentList, studentListViewCancel);
            reservationListView.getItems().clear();
            updateWebsite(boardGamesList);
        }


    }
    public void MouseClickedList(MouseEvent e) {
        if(e.getSource() == gameListView){
            if(gameListView.getSelectionModel().getSelectedItem() != null){
                boardGame = (BoardGame) gameListView.getSelectionModel().getSelectedItem();
                gameTextField.setText(gameListView.getSelectionModel().getSelectedItem().toString());
            }
        }
        else if(e.getSource() == studentListView){
            if(studentListView.getSelectionModel().getSelectedItem() !=null){
                student = (Student) studentListView.getSelectionModel().getSelectedItem();
                studentTextField.setText(studentListView.getSelectionModel().getSelectedItem().toString());
            }
        }
        else if(e.getSource() == gameListViewEdit){
            if(gameListViewEdit.getSelectionModel().getSelectedItem() != null){
                boardGame = (BoardGame) gameListViewEdit.getSelectionModel().getSelectedItem();
                gameTextFieldEdit.setText(gameListViewEdit.getSelectionModel().getSelectedItem().toString());
                titleTextFieldEdit.setText(boardGame.getName());
                borrowerTextFieldEdit.setText(boardGame.getBorrower().toString());
            }
        }
        else if(e.getSource() == memberViewListEdit){
            if(memberViewListEdit.getSelectionModel().getSelectedItem() != null){
                student = (Student) memberViewListEdit.getSelectionModel().getSelectedItem();
                memberTextFieldEdit.setText(memberViewListEdit.getSelectionModel().getSelectedItem().toString());
            }
        }
        else if(e.getSource() == gameListViewReserve){
            if(gameListViewReserve.getSelectionModel().getSelectedItem() != null){
                boardGame = (BoardGame) gameListViewReserve.getSelectionModel().getSelectedItem();
                gameTextFieldReserve.setText(gameListViewReserve.getSelectionModel().getSelectedItem().toString());
            }
        }
        else if(e.getSource() == studentListViewReserve){
            if(studentListViewReserve.getSelectionModel().getSelectedItem() != null){
                student = (Student) studentListViewReserve.getSelectionModel().getSelectedItem();
                studentTextFieldReserve.setText(studentListViewReserve.getSelectionModel().getSelectedItem().toString());
            }
        }
        else if(e.getSource() == studentListViewCancel){
            if( studentListViewCancel.getSelectionModel().getSelectedItem()!=null){
                student = (Student) studentListViewCancel.getSelectionModel().getSelectedItem();
                studentTextFieldCancel.setText(studentListViewCancel.getSelectionModel().getSelectedItem().toString());
                updateReservedBardGamesArea(boardGamesList, reservationListView, student);
            }
        }
        else if(e.getSource() == reservationListView){
            if( reservationListView.getSelectionModel().getSelectedItem() !=null){
                boardGame = (BoardGame) reservationListView.getSelectionModel().getSelectedItem();
                gameTextFieldCancel.setText(reservationListView.getSelectionModel().getSelectedItem().toString());
            }
        }
        else if(e.getSource() == gameListViewReturn){
            if(gameListViewReturn.getSelectionModel().getSelectedItem() != null){
                boardGame = (BoardGame) gameListViewReturn.getSelectionModel().getSelectedItem();
                gameTextFieldReturn.setText(gameListViewReturn.getSelectionModel().getSelectedItem().toString());
                titleTextFieldReturn.setText(boardGame.getName());
                borrowerTextFieldReturn.setText(boardGame.getBorrower().getFirstName() +" "+ boardGame.getBorrower().getLastName());
            }
        }


    }
    public void textChangeListener(KeyEvent e) {
        if(e.getSource() == studentTextField){
            studentTextField.textProperty().addListener(new ChangeListener() {
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    studentListView.getItems().clear();

                    StudentList students1 = manager.containStudents(studentTextField.getText());
                    for (int i = 0; i < students1.size(); i++) {
                        studentListView.getItems().add(students1.getStudentByIndex(i));
                    }

                }
            });
        }
        else if(e.getSource() == gameTextField){
            gameTextField.textProperty().addListener(new ChangeListener() {
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    gameListView.getItems().clear();
                    BoardGamesList games1 = manager.containGame(gameTextField.getText());
                    for (int i = 0; i < games1.size(); i++) {
                        if(!games1.getBoardGameByIndex(i).isLent()){
                            gameListView.getItems().add(games1.getBoardGameByIndex(i));
                        }

                    }

                }
            });
        }
        else if(e.getSource() == gameTextFieldEdit){
            gameTextFieldEdit.textProperty().addListener(new ChangeListener() {
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    gameListViewEdit.getItems().clear();
                    BoardGamesList games1 = manager.containGame(gameTextField.getText());
                    for (int i = 0; i < games1.size(); i++) {
                        if(games1.getBoardGameByIndex(i).isLent()){
                            gameListViewReturn.getItems().add(games1.getBoardGameByIndex(i));
                        }
                    }
                }
            });
        }
        else if(e.getSource() == studentTextFieldReserve){
            studentTextFieldReserve.textProperty().addListener(new ChangeListener() {
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    studentListViewReserve.getItems().clear();
                    StudentList students1 = manager.containStudents(studentTextFieldReserve.getText());
                    for (int i = 0; i < students1.size(); i++) {
                        studentListViewReserve.getItems().add(students1.getStudentByIndex(i));
                    }
                }
            });
        }
        else if(e.getSource() == memberTextFieldEdit){
            memberTextFieldEdit.textProperty().addListener(new ChangeListener() {
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    memberViewListEdit.getItems().clear();
                    StudentList students1 = manager.containStudents(memberTextFieldEdit.getText());
                    for (int i = 0; i < students1.size(); i++) {
                        memberViewListEdit.getItems().add(students1.getStudentByIndex(i));
                    }
                }
            });
        }
        else if(e.getSource() == gameTextFieldReserve){
            gameTextFieldReserve.textProperty().addListener(new ChangeListener() {
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    gameListViewReserve.getItems().clear();
                    BoardGamesList games1 = manager.containGame(gameTextFieldReserve.getText());
                    for (int i = 0; i < games1.size(); i++) {
                        if(games1.getBoardGameByIndex(i).isLent()){
                            gameListViewReserve.getItems().add(games1.getBoardGameByIndex(i));
                        }
                    }
                }
            });
        }
        else if(e.getSource() == studentTextFieldCancel){
            studentTextFieldCancel.textProperty().addListener(new ChangeListener() {
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    studentListViewCancel.getItems().clear();
                    StudentList student1 = manager.containStudents(studentTextFieldCancel.getText());
                    for (int i = 0; i < student1.size(); i++) {
                        studentListViewCancel.getItems().add(student1.getStudentByIndex(i));
                    }
                }
            });
        }
        else if(e.getSource() == gameTextFieldReturn){
            gameTextFieldReturn.textProperty().addListener(new ChangeListener() {
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    gameListViewReturn.getItems().clear();
                    BoardGamesList games1 = manager.containGame(gameTextFieldReturn.getText());
                    for (int i = 0; i < games1.size(); i++) {
                        if(games1.getBoardGameByIndex(i).isLent()){
                            gameListViewReturn.getItems().add(games1.getBoardGameByIndex(i));
                        }
                    }
                }
            });
        }
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
    private void updateWebsite(BoardGamesList boardGamesList)
    {
        manager.saveAllBoardGamesXML(boardGamesList);
    }
}