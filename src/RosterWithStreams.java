
public class RosterWithStreams 
{

	// RosterWithStreams.empty() is a static factory method that returns a
    // RosterWithStream with no players.
	//RETURNS: A new empty rosterwithstream object 
	public static RosterWithStream empty()
	{
		return new EmptyRosterWithStreams();
	}
	public static void main(String args[])
	{
		TestRosterWithStream.main(args);
	}	
}
