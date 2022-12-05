package myexceptions;

public class IsLentException extends RuntimeException
{
  public IsLentException()
  {
    super("model.Game is already lent; cannot borrow");
  }
}