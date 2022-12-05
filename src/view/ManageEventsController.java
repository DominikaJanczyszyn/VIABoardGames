package view;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.*;

import java.time.LocalDate;
import java.util.Optional;

public class ManageEventsController
{
  private ModelManager modelManager;
  @FXML private TextField nameTab1;
  @FXML private DatePicker dateTab1;
  @FXML private TextArea descriptionTab1;
  @FXML private Button addButtonTab1;
  @FXML private MenuBar menuBar;
  @FXML private Menu menuManage;
  @FXML private MenuItem exit;
  @FXML private MenuItem menuBoardGames;
  @FXML private MenuItem menuBorrowing;
  @FXML private MenuItem menuStudents;
  @FXML private MenuItem menuEvents;
  @FXML private MenuItem menuUpcomingGames;

  @FXML private TextField nameTab2;
  @FXML private DatePicker dateTab2;
  @FXML private TextArea descriptionTab2;
  @FXML private TextField searchTab2;
  @FXML private ListView<Event> listViewTab2;
  @FXML private Button editButtonTab2;

  @FXML private TextField nameTab3;
  @FXML private DatePicker dateTab3;
  @FXML private TextArea descriptionTab3;
  @FXML private TextField searchTab3;
  @FXML private ListView<Event> listViewTab3;
  @FXML private Button removeButtonTab3;


  @FXML private TextField firstNameTab4;
  @FXML private TextField lastNameTab4;
  @FXML private TextField VIAIDTab4;
  @FXML private TextField searchStudentsTab4;
  @FXML private TextField searchEventsTab4;
  @FXML private ListView<Student> listViewMembersTab4;
  @FXML private ListView<Event> listViewEventsTab4;
  @FXML private Button registerButtonTab4;

  @FXML private TableView<Event> tab;
  @FXML private TableColumn<Event, String> nameTab;
  @FXML private TableColumn<Event, Date> dateTab;
  @FXML private TableColumn<Event, String> descriptonTab;
  @FXML private TableColumn<Event, String> participantsTab;
  private ViewHandler viewHandler;
  private Scene scene;

  public void init(ViewHandler viewHandler, Scene scene, ModelManager modelManager){
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.modelManager = modelManager;
  }
  public Scene getScene()
  {
    return scene;
  }
  public void restart(){
    viewHandler.loadEventsView();
  }
  public void initialize()
  {
    modelManager = new ModelManager("upcoming.bin", "games.bin", "students.bin", "events.bin", "eventsWebsite.xml", "boardGamesWebsite.xml", "upcomingBoardGamesWebsite.xml");
    if (listViewTab2 != null && listViewTab3 != null && listViewEventsTab4 != null && listViewMembersTab4 != null)
    {
      updateEventList();
      updateStudentsList();
      updateWebsite();
    }
    if (nameTab1 != null && dateTab1 != null && descriptionTab1 != null && nameTab2 != null && dateTab2 != null && descriptionTab2 != null && nameTab3 != null && dateTab3 != null && descriptionTab3 != null && firstNameTab4 != null && lastNameTab4 != null && VIAIDTab4 != null)
    {
      nameTab1.clear();
      dateTab1.setValue(null);
      descriptionTab1.clear();
      nameTab2.clear();
      dateTab2.setValue(null);
      descriptionTab2.clear();
      nameTab3.clear();
      dateTab3.setValue(null);
      descriptionTab3.clear();
      firstNameTab4.clear();
      lastNameTab4.clear();
      VIAIDTab4.clear();
    }
    if(tab != null){
      tab.getItems().clear();
      EventList eventList = modelManager.getAllEvent();
      nameTab.setCellValueFactory(new PropertyValueFactory<>("name"));
      dateTab.setCellValueFactory(new PropertyValueFactory<>("date"));
      descriptonTab.setCellValueFactory(new PropertyValueFactory<>("description"));
      participantsTab.setCellValueFactory(new  PropertyValueFactory<>("participants"));
      for(int i = 0; i < eventList.size(); i++){
        tab.getItems().add(eventList.getEventByIndex(i));
      }
    }

  }

  public void handleActions(ActionEvent e)
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
    if (e.getSource() == addButtonTab1)
    {
      if (!nameTab1.getText().equals("") && dateTab1.getValue() != null && !descriptionTab1.getText().equals(""))
      {
        String firstName = nameTab1.getText();
        Date date = new Date(dateTab1.getValue().getDayOfMonth(), dateTab1.getValue().getMonthValue(), dateTab1.getValue().getYear());
        String description = descriptionTab1.getText();

        if (date.isBefore(Date.getTodaysDate()))
        {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setHeaderText(null);
          alert.setContentText("Unable to create an event in the past.");
          alert.show();
          return;
        }

        Event event = new Event(firstName, date, description);
        modelManager.addEvent(event);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Event has been added.");
        alert.show();
        updateEventList();
        updateWebsite();

        nameTab1.setText("");
        dateTab1.setValue(null);
        descriptionTab1.setText("");
      }
      else
      {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Data is missing.");
        alert.show();
      }
    }

    if (e.getSource() == editButtonTab2)
    {
      Event event = (Event)listViewTab2.getSelectionModel().getSelectedItem();
      if (event != null)
      {
        modelManager.removeEvent((Event)listViewTab2.getSelectionModel().getSelectedItem());
        if (!nameTab2.getText().equals("") && dateTab2.getValue() != null && !descriptionTab2.getText().equals(""))
        {
          String name = nameTab2.getText();
          Date date = new Date(dateTab2.getValue().getDayOfMonth(),
              dateTab2.getValue().getMonthValue(), dateTab2.getValue().getYear());
          String description = descriptionTab2.getText();

          if (date.isBefore(Date.getTodaysDate()))
          {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Unable to create an event in the past.");
            alert.show();
            return;
          }

          event.setName(name);
          event.setDate(date);
          event.setDescription(description);

          listViewTab2.getSelectionModel().select(-1);
          modelManager.addEvent(event);
          updateEventList();
          updateWebsite();

          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setHeaderText(null);
          alert.setContentText("Event has been edited.");
          alert.show();
        }
        else
        {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setHeaderText(null);
          alert.setContentText("No event has been selected.");
          alert.show();
        }
      }
    }
    if (e.getSource() == removeButtonTab3)
    {
      Event event = (Event) listViewTab3.getSelectionModel().getSelectedItem();
      if (event != null)
      {
        for (int i = 0; i < event.getParticipants().size(); i++)
        {
          if (!modelManager.hasBorrowedABoardGame(event.getParticipants().getStudentByIndex(i)) && !modelManager.hasLentABoardGame(event.getParticipants().getStudentByIndex(i)) && !event.getParticipants().getStudentByIndex(i).isAMember())
          {
            modelManager.removeStudent(event.getParticipants().getStudentByIndex(i));
          }
        }
        modelManager.removeEvent((Event) listViewTab3.getSelectionModel().getSelectedItem());
        listViewTab3.getSelectionModel().select(-1);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Event has been deleted.");
        alert.show();
        updateEventList();
        updateStudentsList();
        updateWebsite();
      }
      else
      {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("No event has been selected.");
        alert.show();
      }
    }
    if (e.getSource() == registerButtonTab4)
    {
      Event event = (Event) listViewEventsTab4.getSelectionModel().getSelectedItem();
      Student student = (Student) listViewMembersTab4.getSelectionModel().getSelectedItem();
      if (event != null)
      {
        if (student != null)
        {
          StudentList allParticipants = modelManager.getAllParticipants(event);

          for (int i = 0; i < allParticipants.size(); i++)
          {
            if (allParticipants.getStudentByIndex(i).equals(student))
            {
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setHeaderText(null);
              alert.setContentText("Student is already registered for that event.");
              alert.show();

              listViewEventsTab4.getSelectionModel().select(-1);
              listViewMembersTab4.getSelectionModel().select(-1);

              return;
            }
          }

          modelManager.registerForAnEvent(student, event);
          updateStudentsList();
          updateEventList();

          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setHeaderText(null);
          alert.setContentText("Student has been registered for the event.");
          alert.show();

          listViewEventsTab4.getSelectionModel().select(-1);
          listViewMembersTab4.getSelectionModel().select(-1);

          firstNameTab4.clear();
          lastNameTab4.clear();
          VIAIDTab4.clear();
        }
        else if (!firstNameTab4.getText().equals("") && !lastNameTab4.getText().equals("") && !VIAIDTab4.getText().equals(""))
        {
          String firstName = firstNameTab4.getText();
          String lastName = lastNameTab4.getText();
          int VIAID = 0;

          try
          {
            VIAID = Integer.parseInt(VIAIDTab4.getText());
          }
          catch (IllegalArgumentException exception)
          {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Entered VIA ID should contain only digits");
            alert.show();
            return;
          }

          Student guest = null;

          try
          {
            guest = new Student(firstName, lastName, VIAID);
          }
          catch (IllegalArgumentException exception)
          {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Entered VIA ID should contain 6 digits.");
            alert.show();
            return;
          }

          StudentList allStudents = modelManager.getAllStudent();

          for (int i = 0; i < allStudents.size(); i++)
          {
            if (allStudents.getStudentByIndex(i).equals(guest))
            {
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setHeaderText(null);
              alert.setContentText("User is already in the system");
              alert.show();
              return;
            }
          }

          modelManager.registerForAnEvent(guest, event);
          modelManager.addStudent(guest);
          updateStudentsList();
          updateEventList();

          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setHeaderText(null);
          alert.setContentText("New guest has been registered for the event.");
          alert.show();

          listViewEventsTab4.getSelectionModel().select(-1);
          listViewMembersTab4.getSelectionModel().select(-1);

          firstNameTab4.clear();
          lastNameTab4.clear();
          VIAIDTab4.clear();
        }
        else
        {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setHeaderText(null);
          alert.setContentText("Student data is missing.");
          alert.show();
        }
      }
      else
      {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("No data entered.");
        alert.show();
      }
    }
  }

  public void textChangeListener(KeyEvent keyEvent)
  {
    if (keyEvent.getSource() == searchTab2)
    {
      searchTab2.textProperty().addListener(new ChangeListener()
      {
        public void changed(ObservableValue observable, Object oldValue, Object newValue)
        {
          listViewTab2.getItems().clear();
          EventList events = modelManager.containEvent(searchTab2.getText());
          for (int i = 0; i < events.size(); i++)
          {
            listViewTab2.getItems().add(events.getEventByIndex(i));
          }
        }
      });
    }
    if (keyEvent.getSource() == searchTab3)
    {
      searchTab3.textProperty().addListener(new ChangeListener()
      {
        public void changed(ObservableValue observable, Object oldValue, Object newValue)
        {
          listViewTab3.getItems().clear();
          EventList events = modelManager.containEvent(searchTab3.getText());
          for (int i = 0; i < events.size(); i++)
          {
            listViewTab3.getItems().add(events.getEventByIndex(i));
          }
        }
      });
    }
    if (keyEvent.getSource() == searchStudentsTab4)
    {
      searchStudentsTab4.textProperty().addListener(new ChangeListener()
      {
        public void changed(ObservableValue observable, Object oldValue, Object newValue)
        {
          listViewMembersTab4.getItems().clear();
          StudentList students = modelManager.containStudents(searchStudentsTab4.getText());
          for (int i = 0; i < students.size(); i++)
          {
            listViewMembersTab4.getItems().add(students.getStudentByIndex(i));
          }
        }
      });
    }
    if (keyEvent.getSource() == searchEventsTab4)
    {
      searchEventsTab4.textProperty().addListener(new ChangeListener()
      {
        public void changed(ObservableValue observable, Object oldValue, Object newValue)
        {
          listViewEventsTab4.getItems().clear();
          EventList events = modelManager.containEvent(searchEventsTab4.getText());
          for (int i = 0; i < events.size(); i++)
          {
            listViewEventsTab4.getItems().add(events.getEventByIndex(i));
          }
        }
      });
    }
  }

  private void updateEventList()
  {
    EventList events = modelManager.getAllEvents();
    listViewTab2.getItems().clear();
    listViewTab3.getItems().clear();
    listViewEventsTab4.getItems().clear();
    for (int i = 0; i < events.size(); i++)
    {
      listViewTab2.getItems().add(events.getEventByIndex(i));
      listViewTab3.getItems().add(events.getEventByIndex(i));
      listViewEventsTab4.getItems().add(events.getEventByIndex(i));
    }
  }
  private void updateStudentsList()
  {
    StudentList students = modelManager.getAllStudent();
    listViewMembersTab4.getItems().clear();
    for (int i = 0; i < students.size(); i++)
    {
      listViewMembersTab4.getItems().add(students.getStudentByIndex(i));
    }
  }

  private void updateWebsite()
  {
    modelManager.saveAllEventsXML(modelManager.getAllEvents());
  }

  public void listChangeListener(MouseEvent e)
  {
    if(e.getSource() == listViewTab2)
    {
      Event event = (Event)listViewTab2.getSelectionModel().getSelectedItem();
      if (event != null)
      {
        nameTab2.setText(event.getName());
        dateTab2.setValue(LocalDate.of(event.getDate().getYear(), event.getDate().getMonth(), event.getDate().getDay()));
        descriptionTab2.setText(event.getDescription());
      }
    }
    if(e.getSource() == listViewTab3)
    {
      Event event = (Event)listViewTab3.getSelectionModel().getSelectedItem();
      if (event != null)
      {
        nameTab3.setText(event.getName());
        dateTab3.setValue(LocalDate.of(event.getDate().getYear(), event.getDate().getMonth(), event.getDate().getDay()));
        descriptionTab3.setText(event.getDescription());
      }
    }
  }
}
