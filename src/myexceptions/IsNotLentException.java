package myexceptions;

public class IsNotLentException extends RuntimeException
{
  public IsNotLentException()
  {
    super("model.Game is not lent; cannot make a reservation");
  }
}
