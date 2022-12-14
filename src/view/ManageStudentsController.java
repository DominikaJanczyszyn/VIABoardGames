package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;

import java.util.Optional;

public class ManageStudentsController
{
  @FXML Tab addTab;
  @FXML Tab editTab;
  @FXML Tab removeTab;

  @FXML private TextField searchAdd;
  @FXML private TextField firstnameAdd;
  @FXML private TextField lastnameAdd;
  @FXML private TextField ViaIdAdd;
  @FXML private ListView<Student> guestsList;
  @FXML private Button changeStatus;
  @FXML private Button addMember;
  @FXML private MenuBar menuBar;
  @FXML private Menu menuManage;
  @FXML private MenuItem exit;
  @FXML private MenuItem menuBoardGames;
  @FXML private MenuItem menuBorrowing;
  @FXML private MenuItem menuStudents;
  @FXML private MenuItem menuEvents;
  @FXML private MenuItem menuUpcomingGames;

  @FXML private TextField searchEdit;
  @FXML private ListView<Student> studentsList;
  @FXML private TextField firstnameEdit;
  @FXML private TextField lastnameEdit;
  @FXML private TextField ViaIdEdit;
  @FXML private Button changeInformation;

  @FXML private TextField firstnameRemove;
  @FXML private TextField lastnameRemove;
  @FXML private TextField ViaIdRemove;
  @FXML private TextField searchRemove;
  @FXML private ListView<Student> membersList;
  @FXML private Button removeButton;

  private Student student, member, guest;
  private StudentList students;
  private ModelManager modelManager;

  private ViewHandler viewHandler;
  private Scene scene;

  public void init(ViewHandler viewHandler, Scene scene,ModelManager modelManager){
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.modelManager = modelManager;
    this.students = modelManager.getAllStudents();
  }
  public Scene getScene()
  {
    return scene;
  }
  public void reset(){
    updateGuestsList();
    updateStudentList();
    updateMembersList();
  }
  public void initialize()
  {
    this.modelManager = new ModelManager("upcoming.bin", "games.bin", "students.bin", "events.bin", "website/xml/eventsWebsite.xml", "website/xml/boardGamesWebsite.xml", "website/xml/upcomingBoardGamesWebsite.xml");
    if(guestsList != null){
      updateGuestsList();
    }
    else if(studentsList != null){
      updateStudentList();
    }
    else if(membersList != null){
      updateMembersList();
    }
  }

  private void updateGuestsList()
  {
    StudentList students = modelManager.getAllStudents();
    guestsList.getItems().clear();
    for (int i = 0; i < students.size(); i++)
    {
      if(students.getStudentByIndex(i).isAMember() == false){
        guestsList.getItems().add(students.getGuestById(i));
      }
    }
  }

  private void updateStudentList()
  {
   studentsList.getItems().clear();
    for (int i = 0; i < students.size(); i++)
    {
      studentsList.getItems().add(students.getStudentByIndex(i));
    }
  }

  private void updateMembersList()
  {
    membersList.getItems().clear();
    for (int i = 0; i < students.size(); i++)
    {
      if(students.getStudentByIndex(i).isAMember()){
        membersList.getItems().add(students.getStudentByIndex(i));
      }
    }
  }

  public void textChangeListener(KeyEvent keyEvent)
  {
    if (keyEvent.getSource() == searchAdd)
    {
      searchAdd.textProperty().addListener(new ChangeListener()
      {
        public void changed(ObservableValue observableValue, Object oldValue, Object newValue)
        {
          guestsList.getItems().clear();
          StudentList guests = modelManager.containGuest(searchAdd.getText());
          for (int i = 0; i < guests.size(); i++)
          {
            if (guests.getGuestById(i) != null) {
              guestsList.getItems().add(guests.getGuestById(i));
            }
          }
        }
      });
    }
    else if (keyEvent.getSource() == searchEdit)
    {
      searchEdit.textProperty().addListener(new ChangeListener()
      {
        public void changed(ObservableValue observableValue, Object oldValue, Object newValue)
        {
          studentsList.getItems().clear();
          StudentList students = modelManager.containStudents(searchEdit.getText());
          for (int i = 0; i < students.size(); i++)
          {
            studentsList.getItems().add(students.getStudentByIndex(i));
          }
        }
      });
    }
    else if (keyEvent.getSource() == searchRemove)
    {
      searchRemove.textProperty().addListener(new ChangeListener()
      {
        public void changed(ObservableValue observableValue, Object oldValue, Object newValue)
        {
          studentsList.getItems().clear();
          StudentList members = modelManager.containMember(searchRemove.getText());
          for (int i = 0; i < members.size(); i++)
          {
            studentsList.getItems().add(members.getStudentByIndex(i));
          }
        }
      });
    }
  }

  public void handleAction(ActionEvent e)
  {
    if(e.getSource() == menuBoardGames){
      viewHandler.openView("ManageBoardGames");
    }
    if(e.getSource() == menuBorrowing){
      viewHandler.openView("ManageBorrowing");
    }
    if(e.getSource() == menuStudents){
      viewHandler.openView("ManageStudents");
    }
    if(e.getSource() == menuEvents){
      viewHandler.openView("ManageEvents");
    }
    if(e.getSource() == menuUpcomingGames){
      viewHandler.openView("ManageUpcomingBoardGames");
    }
    if (e.getSource() == exit)
    {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you really want to exit?", ButtonType.YES, ButtonType.CANCEL);
      alert.setHeaderText(null);
      Optional<ButtonType> result = alert.showAndWait();
      if(result.get() == ButtonType.YES)
      {
        System.exit(0);
      }
    }
    if (e.getSource() == changeStatus)
    {
      Student guest = guestsList.getSelectionModel().getSelectedItem();
      BoardGamesList boardGamesList = modelManager.getAllBoardGames();
      if (this.guest != null)
      {

        for (int i = 0; i < students.size(); i++)
        {
          if (students.getStudentByIndex(i)!=null && students.getStudentByIndex(i).equals(guest))
          {
            if(boardGamesList.isABorrower(guest) || boardGamesList.isAnOwner(guest)){
            for (int j = 0; j < boardGamesList.size(); j++) {
              if (boardGamesList.getBoardGameByIndex(j).isLent() && boardGamesList.getBoardGameByIndex(j).getBorrower().equals(guest)) {
                boardGamesList.getBoardGameByIndex(j).getBorrower().setAMember();
              }
              if (boardGamesList.getBoardGameByIndex(j).getOwner() != null && boardGamesList.getBoardGameByIndex(j).getOwner().equals(guest)) {
                boardGamesList.getBoardGameByIndex(j).getOwner().setAMember();
              }
            }
          }
            students.getStudentByIndex(i).setAMember();
          }
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("You change guest : "+guest+" to member.");
        alert.show();
        modelManager.saveAllGames(boardGamesList);
        modelManager.saveAllStudents(students);
        updateGuestsList();
      }
      else
      {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("You did not select any guest from the list. Try again.");
        alert.show();
      }
    }
    else if (e.getSource() == addMember)
    {
      if (!firstnameAdd.getText().equals("") && !lastnameAdd.getText().equals("") && !ViaIdAdd.getText().equals(""))
      {
        String firstName = firstnameAdd.getText();
        String lastName = lastnameAdd.getText();
        int ViaId = 0;
        try
        {
          ViaId = Integer.parseInt(ViaIdAdd.getText());
        }
        catch (IllegalArgumentException exception)
        {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setHeaderText(null);
          alert.setContentText("VIA ID can only contain digits.");
          alert.show();
          return;
        }

        this.member = null;

        try
        {
          this.member = new Student(firstName, lastName, ViaId);
          this.member.setAMember();
        }
        catch (IllegalArgumentException exception)
        {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setHeaderText(null);
          alert.setContentText("Entered VIA ID has to have 6 digits.");
          alert.show();
          return;
        }

          if (students.memberExists(member))
          {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Same data.");
            alert.show();
          }
          if(students.sameID(member)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Same id.");
            alert.show();
          }else{
            modelManager.addMember(member);
          }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("The member has been added.");
        alert.show();
      }
      else
      {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Error, make sure you entered the information.");
        alert.show();
      }
      modelManager.saveAllStudents(students);
      updateMembersList();
    }
    else if (e.getSource() == changeInformation)
    {
      System.out.println(student);
      if (this.student != null)
      {
        if (!firstnameEdit.getText().equals("") && !lastnameEdit.getText().equals("") && !ViaIdEdit.getText().equals(""))
        {
          String firstName = firstnameEdit.getText();
          String lastName = lastnameEdit.getText();
          int ViaId;
          try
          {
            ViaId = Integer.parseInt(ViaIdEdit.getText());
          }
          catch (IllegalArgumentException exception)
          {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("VIA ID can only contain digits.");
            alert.show();
            return;
          }
          try
          {
            this.student.setFirstName(firstName);
            this.student.setLastName(lastName);
            this.student.setVIAID(ViaId);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Updated.");
            alert.show();
          }
          catch (IllegalArgumentException exception)
          {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Entered VIA ID has to have 6 digits.");
            alert.show();
            return;
          }
          modelManager.saveAllStudents(students);
          updateStudentList();
        }
        else {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setHeaderText(null);
          alert.setContentText("Error, make sure you entered the information.");
          alert.show();
        }
      }
    }
    else if (e.getSource() == removeButton)
    {
      BoardGamesList boardGamesList = modelManager.getAllBoardGames();
      EventList eventList = modelManager.getAllEvents();
      if (member!=null && !boardGamesList.isABorrower(member) && !boardGamesList.isAnOwner(member) && !boardGamesList.isAReservant(member) && !eventList.isAParticipant(member))
      {
        try
        {
            students.removeMember(member);
        }
        catch (IllegalArgumentException exception)
        {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setHeaderText(null);
          alert.setContentText("The members is a current borrower, owner or a participant to an event.");
          alert.show();
          return;
        }

        modelManager.saveAllStudents(students);
        updateMembersList();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Member has been removed.");
        alert.showAndWait();
      }
      else
      {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("The members is a current borrower, owner or a participant to an event.");
        alert.show();
      }
      firstnameRemove.clear();
      lastnameRemove.clear();
      ViaIdRemove.clear();
      modelManager.saveAllStudents(students);
      updateMembersList();
    }
  }

  public void listChangeListener(MouseEvent e)
  {
    if(e.getSource() == guestsList){
      if(guestsList.getSelectionModel().getSelectedItem() != null){
        this.guest = guestsList.getSelectionModel().getSelectedItem();
      }

    }
    if (e.getSource() == studentsList)
    {
      this.student = (Student) studentsList.getSelectionModel().getSelectedItem();
      if (student!=null)
      {
        firstnameEdit.setText(student.getFirstName());
        lastnameEdit.setText(student.getLastName());
        ViaIdEdit.setText(String.valueOf(student.getVIAID()));
      }
    }
    else if (e.getSource() == membersList)
    {
      this.member = (Student) membersList.getSelectionModel().getSelectedItem();
      if (member!=null)
      {
        firstnameRemove.setText(member.getFirstName());
        lastnameRemove.setText(member.getLastName());
        ViaIdRemove.setText(String.valueOf(member.getVIAID()));
      }
    }
  }
}
