package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class creating model.BoardGamesList objects that contain model.BoardGame objects
 * @author Oliwier Wijas
 * @version 1.0
 */
public class BoardGamesList implements Serializable
{
  private ArrayList<BoardGame> boardGames;

  /**
   * No-argument constructor initializing ArrayList of model.BoardGame objects
   */
  public BoardGamesList()
  {
    boardGames = new ArrayList<>();
  }

  /**
   * Adds model.BoardGame object to the ArrayList
   * @param boardGame boardGame object to be added to the ArrayList
   */
  public void addBoardGame(BoardGame boardGame)
  {
    boardGames.add(boardGame);
  }

  /**
   * Removes model.BoardGame object from the ArrayList
   * @param boardGame boardGame object to be removed from the ArrayList
   */
  public void removeGame(BoardGame boardGame)
  {
    boardGames.remove(boardGame);
  }

  /**
   * Gets teh model.BoardGame object using String object name and model.Student object owner
   * @param name String object name of the model.BoardGame object to be got
   * @param owner model.Student object owner of the model.BoardGame object to be got
   * @return the model.BoardGame object found by the properties given
   */
  public BoardGame getBoardGame(String name, Student owner)
  {
    for (int i = 0; i < boardGames.size(); i++)
    {
      if (name.equals(boardGames.get(i).getName()) && owner.equals(boardGames.get(i).getOwner()))
      {
        return boardGames.get(i);
      }
    }
    return null;
  }
  public BoardGame getBoardGameByIndex(int index){
      return boardGames.get(index);

  }
  public boolean isABorrower(Student student)
  {
    for (int i = 0; i < boardGames.size(); i++)
    {
      if (boardGames.get(i).isLent() && boardGames.get(i).getBorrower().equals(student))
      {
        return true;
      }
    }
    return false;
  }
  public boolean isAnOwner(Student student)
  {
    for (int i = 0; i < boardGames.size(); i++)
    {
      if(boardGames.get(i).getOwner() != null){
        if (boardGames.get(i).getOwner().equals(student))
        {
          return true;
        }
      }
    }
    return false;
  }
  public boolean isAReservant(Student student)
  {
    for (int i = 0; i < boardGames.size(); i++)
    {
      if (boardGames.get(i).isAReservant(student))
      {
        return true;
      }
    }
    return false;
  }


  /**
   * Returns size of the ArrayList containing model.BoardGame objects
   * @return size of model.BoardGamesList
   */
  public int size()
  {
    return boardGames.size();
  }

  /**
   * Returns information about all the model.BoardGame objects contained in the model.BoardGamesList object
   * @return String that contains information about all the model.BoardGame objects contained in the BoardGameList object
   */
  public String toString()
  {
    String returnStr = "";
    for (int i = 0; i < boardGames.size(); i++)
    {
      returnStr += boardGames.get(i);
    }
    return returnStr;
  }

}
