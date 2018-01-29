package mancala;

@SuppressWarnings("serial")
public class EmptyPitException extends Exception
{
    public EmptyPitException() {}
    public EmptyPitException(String reason) {super(reason);}
}