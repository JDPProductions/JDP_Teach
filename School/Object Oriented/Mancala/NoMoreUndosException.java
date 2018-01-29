package mancala;

@SuppressWarnings("serial")
public class NoMoreUndosException extends Exception
{
    public NoMoreUndosException() {}
    public NoMoreUndosException(String reason) {super(reason);}
}
