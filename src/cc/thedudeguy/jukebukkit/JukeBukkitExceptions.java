package cc.thedudeguy.jukebukkit;

public class JukeBukkitExceptions {

}

class URLMinLengthException extends Exception
{
	private static final long serialVersionUID = 1L;
	public URLMinLengthException()
	{
		super("Url requires atleast 5 characters.");
	}
}

class URLInvalidExtension extends Exception
{
	private static final long serialVersionUID = 1L;
	public URLInvalidExtension()
	{
		super("A url must end with .ogg or .wav");
	}
}

class URLConnectionError extends Exception
{
	private static final long serialVersionUID = 1L;
	public URLConnectionError()
	{
		super("Was not able to connect to the given URL. Maybe you entered it wrong?");
	}
}