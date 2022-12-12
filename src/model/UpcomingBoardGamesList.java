package model; /**
 * A class creating model.Game objects
 * @author Dominika Janczyszyn
 * @version 1.0
 */
import java.io.Serializable;
import java.util.ArrayList;

public class UpcomingBoardGamesList implements Serializable
{

    private ArrayList<UpcomingBoardGame> upcomingBoardGames;

  /**
   * Constructor creating model.UpcomingBoardGamesList object
   * and initializing upcomingBoardGame ArrayList
   */
  public UpcomingBoardGamesList(){
    upcomingBoardGames = new ArrayList<>();
  }

  /**
   * Adding model.UpcomingBoardGame Object to ArrayList
   * @param upcomingBoardGame model.UpcomingBoardGame Object to be added to upcomingBoardGames arryaList
   */
  public void addUpcomingGame(UpcomingBoardGame upcomingBoardGame){
    upcomingBoardGames.add(upcomingBoardGame);
  }


  /**
   *Remove model.UpcomingBoardGame Object from upcomingBoardGames arraylist
   * @param name String value to compare with model.UpcomingBoardGame Object name varable;
   */
  public void removeUpcomingGame(UpcomingBoardGame upcomingBoardGame){
    for(int i = 0; i < upcomingBoardGames.size(); i++){
      if(upcomingBoardGames.get(i).equals(upcomingBoardGame)){
        upcomingBoardGames.remove(i);
      }
    }
  }

  public UpcomingBoardGame getUpcomingBoardGameByIndex(int index){

    return upcomingBoardGames.get(index);

  }

  /**
   * Get the size of upcominBoardGames arrayList
   * @return size of upcominBoardGames arrayList
   */
  public int size(){
    return upcomingBoardGames.size();
  }

  /**
   *Returns information about the model.UpcomingBoardGamesList object
   * @return String that contains information about the model.UpcomingBoardGamesList object
   */
  public String toString(){
    return upcomingBoardGames.toString();
  }
}
