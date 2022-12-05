package model;

import java.io.Serializable;

/**
 * A class containing an model.Event object.
 * @author Iulia Ispas
 * @version 1.0
 */

public class Event implements Serializable
{

  private String name, description;
  private Date date;
  private StudentList participants;


  /**
   * The constructor initializing the model.Event object
   * @param name the name of the model.Event object
   * @param description the description of the model.Event object
   */
  public Event (String name,Date date, String description)
  {
    this.name = name;
    this.description = description;
    this.date = date.copy();
    participants = new StudentList();
  }


  /**
   * Sets the name of the model.Event object
   * @param name the name of the model.Event object
   */
  public void setName(String name)
  {
    this.name = name;
  }


  /**
   * Sets the description of the model.Event object
   * @param description the description of the model.Event object
   */
  public void setDescription(String description)
  {
    this.description = description;
  }
  public void setDate(Date date)
  {
    this.date = date.copy();
  }

  /**
   * Gets the name of the model.Event object
   * @return the name of the model.Event object
   */
  public String getName()
  {
    return name;
  }


  /**
   * Gets the description of the model.Event object
   * @return the description of the model.Event object
   */
  public String getDescription()
  {
    return description;
  }


  /**
   * Gets the date of the model.Event object
   * @return the date of the model.Event object
   */
  public Date getDate()
  {
    return date;
  }


  /**
   * Adds a new student to the participants list
   * @param student the student to add to the participants list
   */
  public void addParticipant(Student student)
  {
    if (student.isAMember())
    {
      participants.addMember(student);
    }
    else
    {
      participants.addGuest(student);
    }
  }


  /**
   * Removes a student from the participants list
   * @param student the student to remove from the participants list
   */
  public void removeParticipant(Student student)
  {
    if (student.isAMember())
    {
      participants.removeMember(student);
    }
    else
    {
      participants.removeGuest(student);
    }
  }

  /**
   * Returns if the model.Event object's date has passed or not
   * @return if the model.Event object's date has passed or not
   */
  public boolean pastEvent()
  {
    if (!date.isBefore(date))
    {
      return true;
    }
    return false;
  }

  /**
   * Returns the list with all the participants
   * @return the list with all the participants
   */
  public StudentList getParticipants()
  {
    return participants;
  }
  public boolean isAParticipant(Student student)
  {
    for (int i = 0; i < participants.size(); i++)
    {
      if (participants.getStudentByIndex(i).equals(student))
      {
        return true;
      }
    }
    return false;
  }


  /**
   * Returns all the information about the model.Event object as a string
   * @return all the information about the model.Event object
   */
  public String toString()
  {
    return "Name: " + name + "\n" + "model.Date: " + date + "\n" + "Description: " + description + "The list of participants: " + participants.toString();
  }
  public boolean equals(Object obj)
  {
    if (obj == null || obj.getClass() != getClass())
    {
      return false;
    }

    Event other = (Event) obj;
    return this.name.equals(other.name) && this.date.equals(other.date) && this.description.equals(other.description) && this.participants.equals(other.participants);
  }
}
