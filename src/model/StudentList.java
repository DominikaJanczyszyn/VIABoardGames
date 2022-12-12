package model;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * A class containing model.Student objects
 * @author Simona-Luana Draghici
 * @version 1.0
 */

public class StudentList implements Serializable
{
  private ArrayList<Student> students;

  /**
   * No-argument constructor initializing ArrayList of model.Student objects
   */
  public StudentList()
  {
    students = new ArrayList<Student>();
  }

  /**
   * Adds a model.Student object of type guest to the model.StudentList
   * @param guest the guest to add in the model.StudentList
   */
  public void addGuest(Student guest)
  {
    students.add(guest);
  }

  /**
   * Adds a model.Student object of type member to the model.StudentList
   * @param member the member to add in the model.StudentList
   */
  public void addMember(Student member)
  {
    if(member != null){
      member.setAMember();
      students.add(member);
    }
  }
  public void addStudent(Student student)
  {
    students.add(student);
  }
  public Student getGuestById(int index)
  {
    if (!students.get(index).isAMember())
    {
      return students.get(index);
    }
    return null;
  }

  public Student getMemberById(int index)
  {
    if (students.get(index).isAMember())
    {
      return students.get(index);
    }
    return null;
  }
  public boolean sameID(Student member){
    boolean equals = false;
    for(int i = 0; i < students.size(); i++){
      if(students.get(i).getVIAID() == member.getVIAID())
      {
        equals = true;
      }
    }
    return equals;
  }
  public boolean memberExists(Student member){
    boolean equals = false;
    for(int i = 0; i < students.size() ; i++){
      if(students.get(i).equals(member)){
        equals = true;
      }
    }
    return equals;
  }



  /**
   * Gets an model.Student object of type member from the model.StudentList using the index given as an argument
   * @param index the index of the model.Student object of type member
   * @return the model.Student object of type member with the index given
   */
  public Student getStudentByIndex(int index)
  {
    return students.get(index);
  }


  /**
   *Gets all the model.Student objects of type guest from the model.StudentList as a model.Student array
   * @return all the model.Student objects of type guest from the model.StudentList as a model.Student array
   */
  public Student[] getAllGuests()
  {
    Student[] guest = new Student[students.size()];
    for (int i = 0; i < students.size(); i++)
    {
      if (students.get(i).isAMember() == false)
      {
        guest[i] = students.get(i);
      }
    }
    return guest;
  }

  /**
   *Gets all the model.Student objects of type member from the model.StudentList as a model.Student array
   * @return all the model.Student objects of type member from the model.StudentList as a model.Student array
   */
  public Student[] getAllMembers()
  {
    Student[] member = new Student[students.size()];
    for (int i = 0; i < students.size(); i++)
    {
      if (students.get(i).isAMember() == true)
      {
        member[i] = students.get(i);
      }
    }
    return member;
  }

  /**
   * Removes a model.Student object of type guest given as an argument from the model.StudentList
   * @param guest the model.Student object of type guest to remove from the model.StudentList
   */
  public void removeGuest(Student guest)
  {
    students.remove(guest);
  }

  /**
   * Removes a model.Student object of type member given as an argument from the model.StudentList
   * @param member the model.Student object of type member to remove from the model.StudentList
   */
  public void removeMember(Student member)
  {
    students.remove(member);
  }

  /**
   * Removes a model.Student object with the index given as an argument from the model.StudentList
   * @param index the model.Student object to remove from the model.StudentList
   */
  public void removeByIndex(int index)
  {
    students.remove(index);
  }
  /**
   * Returns size of the ArrayList containing model.Student objects
   * @return size of model.StudentList
   */
  public int size()
  {
    return students.size();
  }

  /**
   * Returns information about all the model.Student objects contained in the model.StudentList object
   * @return String that contains information about all the model.Student objects contained in the model.StudentList object
   */
  public String toString()
  {
    String returnStr = "";

    for(int i = 0; i<students.size(); i++)
    {
      Student temp = students.get(i);
      returnStr += temp.toString() +"\n";
    }
    return returnStr;
  }

  /**
   * Returns boolean value when a given object has the same parameters as the current model.Student object
   * @param obj the object given to compare with the given model.Student object
   * @return boolean value of the comparison
   */
  public boolean equals(Object obj)
    {
      if(obj == null || getClass() != obj.getClass())
      {
        return false;
      }

      StudentList other = (StudentList) obj;

      for (int i = 0; i < students.size(); i++)
      {
        if (!students.get(i).equals(other.students.get(i)))
        {
          return false;
        }
      }

      return true;
    }
}
