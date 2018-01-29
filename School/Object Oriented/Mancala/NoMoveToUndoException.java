package mancala;

@SuppressWarnings("serial")
public class NoMoveToUndoException extends Exception
{
    public NoMoveToUndoException() {}
    public NoMoveToUndoException(String reason) {super(reason);}
}
