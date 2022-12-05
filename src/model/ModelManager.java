package model;

import parser.ParserException;
import utils.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A class creating model.Game objects
 * @author Dominika Janczyszyn
 * @version 1.0
 */
public class ModelManager
{
  private String upcomingBoardGameFile;
  private String boardGamesFile;
  private String studentFile;//chanage this in diagram
  private String eventFile;
  private String eventsWebsiteFile;
  private String boardGamesWebsiteFile;
  private String upcomingBoardGamesWebsiteFile;
  /**
   * Constructor creating model.ModelManager object
   //* @param upcomingBoardGameFile name of UpcomingBoardGameList binary file
   * @param boardGamesFile name of BoardGameList birnary file
   * @param studentFile name of StudentsList binary file
   //* @param eventFile name of EventsList binary file
   */
  public ModelManager(String upcomingBoardGameFile, String boardGamesFile, String studentFile, String eventFile, String eventsWebsiteFile, String boardGamesWebsiteFile, String upcomingBoardGamesWebsiteFile){
    this.upcomingBoardGameFile = upcomingBoardGameFile;
    this.boardGamesFile = boardGamesFile;
    this.studentFile = studentFile;
    this.eventFile = eventFile;
    this.eventsWebsiteFile = eventsWebsiteFile;
    this.boardGamesWebsiteFile = boardGamesWebsiteFile;
    this.upcomingBoardGamesWebsiteFile = upcomingBoardGamesWebsiteFile;
  }

  /**
   * Gets all model.UpcomingBoardGame objects from binary file and creates model.UpcomingBoardGamesList object
   * @return model.UpcomingBoardGamesList object
   */
  public UpcomingBoardGamesList getAllUpcomingGames(){
    UpcomingBoardGamesList upcomingBoardGames = new UpcomingBoardGamesList();

    try
    {
      upcomingBoardGames = (UpcomingBoardGamesList) MyFileHandler.readFromBinaryFile(upcomingBoardGameFile);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }
    return upcomingBoardGames;
  }

  /**
   * Save all UpcomingBoardGames Objects from model.UpcomingBoardGamesList to the binary file
   * @param upcomingBoardGames is an object that contains UpcomingBoardGames objects, that will be saved to the binary file
   */
  public void saveAllUpcomingGames(UpcomingBoardGamesList upcomingBoardGames)
  {
    try
    {
      MyFileHandler.writeToBinaryFile(upcomingBoardGameFile, upcomingBoardGames);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file");
    }
  }
  /////////////////////////////////////////////////////////////////////////////////////////
  /**
   * Gets all model.BoardGame objects from binary file and creates model.BoardGamesList object
   * @return model.BoardGamesList object
   */
  public BoardGamesList getAllBoardGames(){
    BoardGamesList boardGames = new BoardGamesList();

    try
    {
      boardGames = (BoardGamesList)MyFileHandler.readFromBinaryFile(boardGamesFile);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }
    return boardGames;
  }
  /**
   * Save all BoardGames Objects from model.BoardGamesList to the binary file
   * @param boardGames is an object that contains BoardGames objects, that will be saved to the binary file
   */
  public void saveAllGames(BoardGamesList boardGames)
  {
    try
    {
      MyFileHandler.writeToBinaryFile(boardGamesFile, boardGames);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file");
    }
  }
  ///////////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * Gets all model.Student objects from binary file and creates model.StudentList object
   * @return model.StudentList object
   */
  public StudentList getAllStudent(){
    StudentList studentList = new StudentList();

    try
    {
      studentList = (StudentList) MyFileHandler.readFromBinaryFile(studentFile);

    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }
    return studentList;
  }
  /**
   * Save all model.Student Objects from StundetList to the binary file
   * @param studentList is an object that contains model.Student objects, that will be saved to the binary file
   */
  public void saveAllStudents(StudentList studentList)
  {
    try
    {
      MyFileHandler.writeToBinaryFile(studentFile, studentList);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file");
    }
  }
//////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * Gets all model.Event objects from binary file and creates EventsList object
   * @return EventsList object
   */
public EventList getAllEvents(){
  EventList eventsList = new EventList();

  try
  {
    eventsList = (EventList) MyFileHandler.readFromBinaryFile(eventFile);

  }
  catch (FileNotFoundException e)
  {
    System.out.println("File not found");
  }
  catch (IOException e)
  {
    System.out.println("IO Error reading file");
  }
  catch (ClassNotFoundException e)
  {
    System.out.println("Class Not Found");
  }
  return eventsList;
}
  /**
   * Save all model.Event Objects from EventsList to the binary file
   * @param eventsList is an object that contains model.Event objects, that will be saved to the binary file
   */
  public void saveAllEvents(EventList eventsList)
  {
    try
    {
      MyFileHandler.writeToBinaryFile(eventFile, eventsList);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file");
    }
  }
  public StudentList containStudents(String text){
    StudentList allStudents = new StudentList();
    StudentList containStudent = new StudentList();

    try
    {
      allStudents = (StudentList)MyFileHandler.readFromBinaryFile(studentFile);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");

    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }

    for(int i = 0 ; i < allStudents.size(); i++){
      String student = allStudents.getStudentByIndex(i).toString();
      if(student.contains(text)){
        containStudent.addMember(allStudents.getStudentByIndex(i));
      }
    }



    return containStudent;
  }
  public BoardGamesList containGame(String text){

    BoardGamesList allGames = new BoardGamesList();
    BoardGamesList containGame = new BoardGamesList();

    try
    {
      allGames = (BoardGamesList) MyFileHandler.readFromBinaryFile(boardGamesFile);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");

    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }


    for(int i = 0 ; i < allGames.size(); i++){
      String games = allGames.getBoardGameByIndex(i).toString();
      if(games.contains(text)){
        containGame.addBoardGame(allGames.getBoardGameByIndex(i));
      }
    }
    return containGame;
  }
  public StudentList containStudent (String text)
  {
    StudentList allStudents = new StudentList();
    StudentList containStudent = new StudentList();

    allStudents = getAllStudent();
    for (int i = 0; i < allStudents.size(); i++)
    {
      String student = allStudents.getStudentByIndex(i).toString();
      if (student.contains(text))
      {
        containStudent.addStudent(getAllStudent().getStudentByIndex(i));
      }
    }
    return containStudent;
  }
  public UpcomingBoardGamesList containUpcomingBoardGame (String text)
  {
    UpcomingBoardGamesList allBoardGames = new UpcomingBoardGamesList();
    UpcomingBoardGamesList containBoardGame = new UpcomingBoardGamesList();

    allBoardGames = getAllUpcomingGames();
    for (int i = 0; i < allBoardGames.size(); i++)
    {
      String boardGame = allBoardGames.getUpcomingBoardGameByIndex(i).toString();
      if (boardGame.contains(text))
      {
        containBoardGame.addUpcomingGame(getAllUpcomingGames().getUpcomingBoardGameByIndex(i));
      }
    }
    return containBoardGame;
  }
  public BoardGamesList containLending(String text){
    BoardGamesList allGames = new BoardGamesList();
    BoardGamesList containGame = new BoardGamesList();

    try
    {
      allGames = (BoardGamesList) MyFileHandler.readFromBinaryFile(boardGamesFile);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }

    for(int i = 0 ; i < allGames.size(); i++){
      String game= allGames.getBoardGameByIndex(i).toString();
      if(game.contains(text) && allGames.getBoardGameByIndex(i).isLent()){
        containGame.addBoardGame(allGames.getBoardGameByIndex(i));
      }
    }
    return containGame;
  }
  public void addEvent(Event event)
  {
    EventList allEvents = getAllEvents();

    allEvents.addEvent(event);

    saveAllEvents(allEvents);
  }
  public void removeEvent(Event event)
  {
    EventList allEvents = getAllEvents();

    allEvents.removeEvent(event);

    saveAllEvents(allEvents);
  }
  public boolean hasBorrowedABoardGame(Student student)
  {
    BoardGamesList allGames = getAllBoardGames();

    return allGames.isABorrower(student);
  }
  public boolean hasLentABoardGame(Student student)
  {
    BoardGamesList allGames = getAllBoardGames();

    return allGames.isABorrower(student);
  }
  public void removeStudent(Student student)
  {
    StudentList allStudents = getAllStudent();

    allStudents.removeGuest(student);

    saveAllStudents(allStudents);
  }
  public StudentList getAllParticipants(Event event)
  {
    return event.getParticipants();
  }
  public void registerForAnEvent(Student student, Event event)
  {
    EventList allEvents = getAllEvents();

    for (int i = 0; i < allEvents.size(); i++)
    {
      if (allEvents.getEventByIndex(i).equals(event))
      {
        allEvents.getEventByIndex(i).addParticipant(student);
        break;
      }
    }

    saveAllEvents(allEvents);
  }
  public void addStudent(Student student)
  {
    StudentList allStudents = getAllStudent();

    allStudents.addGuest(student);

    saveAllStudents(allStudents);
  }
  public EventList containEvent(String text)
  {
    EventList allEvents = new EventList();
    EventList containEvent = new EventList();

    allEvents = getAllEvents();

    for (int i = 0; i < allEvents.size(); i++)
    {
      String event = allEvents.getEventByIndex(i).toString();
      if (event.contains(text))
      {
        containEvent.addEvent(allEvents.getEventByIndex(i));
      }
    }
    return containEvent;
  }
  public void saveAllEventsXML(EventList eventsList)
  {
    try
    {
      MyFileHandler.writeToXMLFile(eventsWebsiteFile, eventsList);
    }
    catch (ParserException e)
    {
      System.out.println("Parser Error");
    }
  }
  public void addBoardGame(BoardGame boardGame)
  {
    BoardGamesList allBoardGames = getAllBoardGames();

    allBoardGames.addBoardGame(boardGame);

    saveAllGames(allBoardGames);
  }
  public EventList getAllEvent(){
    EventList eventsList = new EventList();

    try
    {
      eventsList = (EventList) MyFileHandler.readFromBinaryFile(eventFile);

    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }
    return eventsList;
  }
  public StudentList containGuest (String text)
  {
    StudentList allGuests = new StudentList();
    StudentList containGuest = new StudentList();
    Student[] guests = getAllStudent().getAllGuests();
    for (int i = 0; i < guests.length; i++)
    {
      allGuests.addGuest(guests[i]);
    }
    for (int i = 0; i < allGuests.size(); i++)
    {
      String guest = allGuests.getGuestById(i).toString();
      if (guest.contains(text))
      {
        containGuest.addGuest(allGuests.getGuestById(i));
      }
    }
    return containGuest;
  }
  public void addMember(Student member)
  {
    StudentList students = getAllStudent();
    students.addMember(member);
    saveAllStudents(students);
  }
  public StudentList containMember (String text)
  {
    StudentList allMembers = new StudentList();
    StudentList containGuest = new StudentList();
    Student[] guests = getAllStudent().getAllGuests();
    for (int i = 0; i < guests.length; i++)
    {
      allMembers.addGuest(guests[i]);
    }
    for (int i = 0; i < allMembers.size(); i++)
    {
      String guest = allMembers.getGuestById(i).toString();
      if (guest.contains(text))
      {
        containGuest.addGuest(allMembers.getGuestById(i));
      }
    }
    return containGuest;
  }
  public void saveAllBoardGamesXML(BoardGamesList boardGamesList)
  {
    try
    {
      MyFileHandler.writeToXMLFile(boardGamesWebsiteFile, boardGamesList);
    }
    catch (ParserException e)
    {
      System.out.println("Parser Error");
    }
  }

  public void saveAllUpcomingBoardGamesXML(UpcomingBoardGamesList upcomingBoardGamesList)
  {
    try
    {
      MyFileHandler.writeToXMLFile(upcomingBoardGamesWebsiteFile, upcomingBoardGamesList);
    }
    catch (ParserException e)
    {
      System.out.println("Parser Error");
    }
  }
}
