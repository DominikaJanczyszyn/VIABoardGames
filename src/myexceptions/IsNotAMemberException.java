package myexceptions;

public class IsNotAMemberException extends RuntimeException
{
  public IsNotAMemberException()
  {
    super("The person is not a member; cannot do the action");
  }
}
