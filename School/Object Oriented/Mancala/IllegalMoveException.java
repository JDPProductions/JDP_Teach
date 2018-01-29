package mancala;

@SuppressWarnings("serial")
public class IllegalMoveException extends Exception
{
    public IllegalMoveException() {}
    public IllegalMoveException(String reason) {super(reason);}
}
