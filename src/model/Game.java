package model;

import java.io.Serializable;

/**
 * A class creating model.Game objects
 * @author Oliwier Wijas
 * @version 1.0
 */
public abstract class Game implements Serializable
{
  private String name;
  private String numberOfPlayers;
  private String description;

  /**
   * Constructor creating model.Game object
   * @param name the name to be set to the model.Game object
   * @param numberOfPlayers number of players to be set to the model.Game object
   * @param description the description to be set to the model.Game object
   */
  public Game(String name, String numberOfPlayers, String description)
  {
    this.name = name;
    this.numberOfPlayers = numberOfPlayers;
    this.description = description;
  }

  /**
   * Sets the name of the model.Game object
   * @param name name to be set to the model.Game object
   */
  public void setName(String name)
  {
    this.name = name;
  };

  /**
   * Sets number of players of the model.Game object
   * @param numberOfPlayers number of players to be set to the model.Game object
   */
  public void setNumberOfPlayers(String numberOfPlayers)
  {
      this.numberOfPlayers = numberOfPlayers;
  }

  /**
   * Sets the description of the model.Game object
   * @param description the description to be set to the model.Game object
   */
  public void setDescription(String description)
  {
    this.description = description;
  }

  /**
   * Gets the name of the model.Game object
   * @return the name of the model.Game object
   */
  public String getName()
  {
    return name;
  }

  /**
   * Gets number of players of the model.Game object
   * @return number of players of the model.Game object
   */
  public String getNumberOfPlayers()
  {
    return numberOfPlayers;
  }

  /**
   * Gets the description of the model.Game object
   * @return the description of the model.Game object
   */
  public String getDescription()
  {
    return description;
  }

  /**
   * Returns boolean value when a given object has the same parameters as current model.Game object
   * @param obj the object given to compare with the current model.Game object
   * @return boolean value of the comparison
   */
  public boolean equals(Object obj)
  {
    if (obj == null || obj.getClass() != getClass())
    {
      return false;
    }

    Game other = (Game) obj;
    return this.name.equals(other.name) && this.numberOfPlayers.equals(other.numberOfPlayers) && this.description.equals(other.description);
  }

  /**
   * Returns information about the model.Game object
   * @return String that contains information about the model.Game object
   */
  public String toString()
  {
    return "\nName: " + name +
        "\nNumber of players: " + numberOfPlayers +
        "\nDescription: " + description;
  }
}
