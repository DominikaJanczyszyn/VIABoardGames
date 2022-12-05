package model;

import java.io.Serializable;

public class Student implements Serializable
    /**
     * A class creating model.Student objects
     *@author Simona-Luana Draghici
     *@version 1.0
     */
{
  private String firstName;
  private String lastName;
  private int VIAID;
  private boolean isAMember;

  /**
   * The 3-argument constructor initializing the model.Student object
   * @param firstName the first name to be set to the model.Student object
   * @param lastName the last name to be set to the model.Student object
   * @param id the VIA ID to be set to the model.Student object
   * isAMember sets model.Student object as a guest
   */
  public Student(String firstName, String lastName, int id) throws IllegalArgumentException
  {
    this.firstName = firstName;
    this.lastName = lastName;
    if(1000000 > id && id > 99999){
      this.VIAID = id;
    }
    else {
      throw new IllegalArgumentException();
    }

    this.VIAID = VIAID;
    isAMember = false;
  }

  /**
   * Sets the first name for the model.Student object
   * @param firstName the first name to be set to the model.Student object
   */
  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  /**
   * Sets the last name
   * @param lastName the last name to be set to the model.Student object
   */
  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  /**
   * Sets the VIA ID
   * @param id the VIA ID to be set to the model.Student object
   */
  public void setVIAID(int id)
  {
    if(1000000 > id && id > 99999){
      this.VIAID = id;
    }
    else {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Gets the first name of the model.Student object
   * @return returning the first name of the model.Student object
   */
  public String getFirstName()
  {
    return firstName;
  }

  /**
   * Gets the last name of the model.Student object
   * @return returning the last name of the model.Student object
   */
  public String getLastName()
  {
    return lastName;
  }

  /**
   * Gets the VIA ID of the model.Student object
   * @return returning the VIA ID of the model.Student object
   */

  public int getVIAID()
  {
    return VIAID;
  }

  /**
   * Sets a student as a member
   */
  public void setAMember()
  {
    isAMember = true;
  }

  /**
   * Verifies if a student is a member or a guest
   */
  public boolean isAMember()
  {
    return isAMember;
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

    Student other = (Student) obj;

    return firstName.equals(other.firstName) && lastName.equals(other.lastName) && VIAID == other.VIAID && isAMember == other.isAMember;
  }

  /**
   * Returns information about the student object
   * Verifying the type of a student (member/guest)
   * @return String that contains information about the model.Student object
   */
  public String toString()
  {
    return firstName +" "+lastName+ "("+VIAID+")";
  }

}
