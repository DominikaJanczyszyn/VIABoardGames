package model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A class containing a model.Date object.
 * @author Iulia Ispas
 * @version 1.0
 */

public class Date implements Serializable
{
  private int day, month, year;

  /**
   * The constructor initializing the model.Date object
   * @param day the day to be set to the model.Date object
   * @param month the month to be set to the model.Date object
   * @param year the year to be set to the model.Date object
   */
  public Date(int day, int month, int year)
  {
    this.day = day;
    this.month = month;
    this.year = year;
  }

  /**
   * Sets the day of the model.Date object
   * @param day the day to be set to the model.Date object
   */
  public void setDay(int day)
  {
    this.day = day;
  }

  /**
   * Sets the month of the model.Date object
   * @param month the month to be set to the model.Date object
   */
  public void setMonth(int month)
  {
    this.month = month;
  }

  /**
   * Sets the year of the model.Date object
   * @param year the year to be set to the model.Date object
   */
  public void setYear(int year)
  {
    this.year = year;
  }

  /**
   * Gets the day of the model.Date object
   * @return day the day of the model.Date object
   */
  public int getDay()
  {
    return day;
  }

  /**
   * Gets the month of the model.Date object
   * @return month the month of the model.Date object
   */
  public int getMonth()
  {
    return month;
  }

  /**
   * Gets the year of the model.Date object
   * @return year the year of the model.Date object
   */
  public int getYear()
  {
    return year;
  }

  /**
   * Gets the current date
   * @return the current date
   */
  public static Date getTodaysDate()
  {
    LocalDate currentDate = LocalDate.now();
    int currentDay = currentDate.getDayOfMonth();
    int currentMonth = currentDate.getMonthValue();
    int currentYear = currentDate.getYear();
    Date today = new Date(currentDay, currentMonth, currentYear);
    return today;
  }

  /**
   * Verifies if the model.Date object given as a parameter is before the current date
   * @param date the model.Date object which needs to be checked
   * @return if the model.Date object is before the current date or not
   */
  public boolean isBefore(Date date)
  {
    Date today = getTodaysDate();
    if (year < date.getYear())
    {
      return true;
    }
    else if (year == date.getYear())
    {
      if (month < date.getMonth())
      {
        return true;
      }
      else if (month == date.getMonth())
      {
        if (day < date.getDay())
        {
          return true;
        }
        else
        {
          return false;
        }
      }
    }
    return false;
  }

  /**
   * Creates a copy for a model.Date object
   * @return a copy of a model.Date object
   */
  public Date copy()
  {
    return new Date(day, month, year);
  }

  /**
   *
   * @param obj the object given to compare with the current model.Date object
   * @return if the model.Date objects are equal or not
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass()!=obj.getClass())
      return false;
    Date other = (Date) obj;
    return day == other.day && month == other.month && year == other.year;
  }

  /**
   * Returns the model.Date object as a string
   * @return the model.Date object as a string
   */
  public String toString()
  {
    return day + "/" + month + "/" + year;
  }
}
