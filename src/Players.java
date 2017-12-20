import java.util.*;
// A Player is an object of any class that implements the Player interface.
public class Players implements Player 
{
	//INTERPRETATION : playerName is represented as String
	//suspended is represented as boolean
	//hasContract is represented as boolean
	//injured is represented as boolean
	private String playerName;
	private boolean suspended;
	private boolean hasContract;
	private boolean injured;
    //Constructor template for Players
	// A Player object represents a member of a team.
	private Players (String name)
	{
		this.playerName = name;
		hasContract = true;
		suspended = false;
		injured = false;
	}
	
	// Players.make(String name) is a static factory method that returns
    // a player with the given name who is (initially) available.
	//GIVEN: A String
	//RETURNS: the name of this player.
    //EXAMPLE: Players.make("Gordon Wayhard").name()  =>  "Gordon Wayhard"
	public static Player make (String playerName)
	{
		return new Players(playerName);
	}
	
	// If p1 and p2 are players with distinct names, then
    // p1.toString() is not the same string as p2.toString().
    // p3.toString().equals(p4.toString()) -> false 
	public String toString()
	{
		return name();
	}
	
    //RETURNS: the name of this player.
    //EXAMPLE: Players.make("Gordon Wayhard").name()  =>  "Gordon Wayhard"
	@Override
	public String name() 
     {
		return this.playerName;
	}

	
	//RETURNS: true iff this player is under contract, and not injured, 
	//and not suspended
    //EXAMPLE: Player gw = Players.make ("Gordon Wayhard");
    //System.out.println (gw.available());  // prints true
    //gw.changeInjuryStatus (true);
    //System.out.println (gw.available());  // prints false
     @Override
	public boolean available() 
	{
		return this.underContract() && !this.isInjured() && !this.isSuspended();
	}

	
	//RETURNS: true iff this player is under contract (employed).
    //EXAMPLE: Player ih = Players.make ("Isaac Homas");
    //System.out.println (ih.underContract());  // prints true
    //ih.changeContractStatus (false);
    //System.out.println (ih.underContract());  // prints false
    //ih.changeContractStatus (true);
    //System.out.println (ih.underContract());  // prints true
	@Override
	public boolean underContract() 
	{
		return this.hasContract;
	}
	
	//RETURNS: true iff this player is injured.
	//EXAMPLE: Player ih = Players.make ("Isaac Homas");
    //System.out.println (ih.underContract());  // prints true
    //ih.changeInjuryStatus (true);
    //System.out.println (ih.isInjured());  // prints true
	@Override
	public boolean isInjured() 
	{
		return this.injured;
	}
	
	//RETURNS: true iff this player is suspended.
	//EXAMPLE: Player ih = Players.make ("Isaac Homas");
    //System.out.println (ih.underContract());  // prints true
    //ih.changeSuspendedStatus (true);
    //System.out.println (ih.isSuspended());  // prints true
	@Override
	public boolean isSuspended() 
	{
		return this.suspended;
	}
	
	//RETURNS: true iff this player is under contract (employed).
    //EXAMPLE: Player ih = Players.make ("Isaac Homas");
    //System.out.println (ih.underContract());  // prints true
    //ih.changeContractStatus (false);
    //System.out.println (ih.underContract());  // prints false
    //ih.changeContractStatus (true);
    //System.out.println (ih.underContract());  // prints true
	@Override
	public void changeContractStatus(boolean newStatus)
	{
		this.hasContract = newStatus;
	}
	
	//RETURNS: true iff this player is injured.
	//EXAMPLE: Player ih = Players.make ("Isaac Homas");
    //System.out.println (ih.underContract());  // prints true
    //ih.changeInjuryStatus (true);
    //System.out.println (ih.isInjured());  // prints true
    @Override
	public void changeInjuryStatus(boolean newStatus)
	{
		this.injured = newStatus;
	}
    
	//RETURNS: true iff this player is suspended.
	//EXAMPLE: Player ih = Players.make ("Isaac Homas");
    //System.out.println (ih.underContract());  // prints true
    //ih.changeSuspendedStatus (true);
    //System.out.println (ih.isSuspended());  // prints true
    @Override
	public void changeSuspendedStatus(boolean newStatus)
	{
		this.suspended = newStatus;
	}
}
