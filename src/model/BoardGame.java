package model;

import myexceptions.IsLentException;
import myexceptions.IsNotAMemberException;
import myexceptions.IsNotLentException;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class creating model.BoardGame objects being an extension of the model.Game class
 * @author Oliwier Wijas
 * @version 1.0
 */

public class BoardGame extends Game implements Serializable
{
  private Student borrower;
  private Student owner;
  private Date dateOfLoan;
  private StudentList reservants;
  private int numberOfRatings;
  private ArrayList<Integer> ratings;
  private double averageRating;

  /**
   * Constructor creating model.BoardGame object
   * @param name the name to be set to the model.BoardGame object
   * @param numberOfPlayers number of players to be set to the model.BoardGame object
   * @param description the description to be set to the model.BoardGame object
   */
  public BoardGame(String name, String numberOfPlayers, String description)
  {
    super(name, numberOfPlayers, description);
    borrower = null;
    owner = null;
    dateOfLoan = null;
    reservants = new StudentList();
    numberOfRatings = 0;
    ratings = new ArrayList<Integer>();
  }

  /**
   * Sets the owner of the model.BoardGame object
   * @param owner owner to be set to the model.BoardGame object
   */
  public void setOwner(Student owner)
  {
    this.owner = owner;
  }

  /**
   * Sets the borrower of the model.BoardGame object
   * @param borrower borrower to be set to the model.BoardGame object
   */
  public void setBorrower(Student borrower)
  {
    this.borrower = borrower;
  }

  /**
   * Gets the owner of the model.BoardGame object
   * @return the borrower of the model.BoardGame object
   */
  public Student getOwner()
  {
    return owner;
  }

  /**
   * Gets the borrower of the model.BoardGame object
   * @return the borrower of the model.BoardGame object
   */
  public Student getBorrower()
  {
    return borrower;
  }

  /**
   * Gets the date of loan of the model.BoardGame object
   * @return the date of loan of the model.BoardGame object
   */
  public Date getDateOfLoan()
  {
    return dateOfLoan;
  };

  /**
   * Lents the model.BoardGame object to the borrower and changes the date of loan to the current date
   * @param member the borrower of the model.BoardGame object
   * @throws IsLentException if the model.BoardGame object is already lent, process cannot be performed
   */
  public void lentBoardGame(Student member)
  {
    if (this.borrower == null)
    {
      this.borrower = member;
      dateOfLoan = Date.getTodaysDate();
    }
    if(this.borrower != null){
      this.borrower = member;
    }
  }
  public boolean isAReservant(Student student)
  {
    for (int i = 0; i < reservants.size(); i++)
    {
      if (reservants.getStudentByIndex(i).equals(student))
        return true;
    }
    return false;
  }

  /**
   * Sets borrower and date of loan to null
   */
  public void returnBoardGame()
  {
    if (isReserved())
    {
      borrower = reservants.getStudentByIndex(0);
      reservants.removeByIndex(0);
      dateOfLoan = Date.getTodaysDate();
    }
    else
    {
      borrower = null;
      dateOfLoan = null;
    }
  }

  /**
   * Returns true if the model.BoardGame object is lent; false if not
   * @return boolean value whether the model.BoardGame is lent or not
   */
  public boolean isLent()
  {
    if (borrower == null)
    {
      return false;
    }
    return true;
  }

  /**
   * Adds the member to the reservants list
   * @param member the reservant of the model.BoardGame object
   * @throws IsNotAMemberException if the entered model.Student object is not a member, process cannot be performed
   * @throws IsNotLentException if model.BoardGame is not lent to anyone, reservation process cannot be performed
   */
  public void reserve(Student member) throws IsNotLentException, IsNotAMemberException
  {
      reservants.addMember(member);
  }

  /**
   * Returns true if the model.BoardGame object is reserved by at least one person; false if not
   * @return boolean value whether the model.BoardGame is reserved by someone or not
   */
  public boolean isReserved()
  {
    if (reservants.size() == 0)
    {
      return false;
    }
    return true;
  }

  /**
   * Cancels reservation of the entered model.Student
   * @param member the student that wants to cancel the reservation
   */
  public void cancelReservation(Student member)
  {
    reservants.removeMember(member);
  }

  /**
   * Adds a rating to the model.BoardGame object
   * @param rating the rating of the model.BoardGame object
   */
  public void rate(int rating)
  {
    numberOfRatings++;
    ratings.add(rating);
    getRating();
  }

  /**
   * Gets average rating of the model.BoardGame object
   * @return the average of the ratings of the model.BoardGame object
   */
  public double getRating()
  {
    double sum = 0;
    for (int i = 0; i < ratings.size(); i++)
    {
      sum += ratings.get(i);
    }
    this.averageRating = sum / (double)ratings.size();
    return averageRating;

  }

  /**
   * Returns list of model.Student objects with all reservants of the model.BoardGame object
   * @return list of model.Student objects that contains all reservants
   */
  public Student[] getAllReservants()
  {
    Student[] reservants1 = reservants.getAllMembers();
    return reservants1;
  }
  public String getReservants(){
    String temp = "";
    Student[] reservants1 = reservants.getAllMembers();
    for(int i = 0; i < reservants1.length; i++){
      temp+= reservants1[i]+"\n";
    }
    return temp;
  }

  /**
   * Returns boolean value when a given object has the same parameters as current model.BoardGame object
   * @param obj the object given to compare with the current model.BoardGame object
   * @return boolean value of the comparison
   */
  public boolean equals(Object obj)
  {
    if (obj == null || obj.getClass() != getClass())
    {
      return false;
    }

    BoardGame other = (BoardGame) obj;
    return super.equals(other) && this.owner == other.owner && this.borrower == other.borrower && this.dateOfLoan == other.dateOfLoan && this.reservants.equals(other.reservants) && this.numberOfRatings == other.numberOfRatings && this.ratings.equals(other.ratings);
  }

  public String toString(){
    return getName();
  }
}
