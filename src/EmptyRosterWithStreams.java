import java.util.stream.Stream;
import java.util.*;

public class EmptyRosterWithStreams implements RosterWithStream 
{
	//SetofPlayers is a set of players
	private Set <Player> SetofPlayers = new HashSet <Player>();
	// If r1 and r2 are RosterWithStream objects, then r1.equals(r2)
    // if and only if
    // every player on roster r1 is also on roster r2, and
    // every player on roster r2 is also on roster r1.
	//EXAMPLE: If r1 and r2 are empty rosterwithstream objects
	//r1.equals(r2)->true
	public boolean equals(Object obj)
	{
		if(obj==this)
		{
			return true;
		}
		else if(obj==null)
		{
			return false;
		}
	 if(obj instanceof EmptyRosterWithStreams)
		{
			EmptyRosterWithStreams er = (EmptyRosterWithStreams)obj;
			if(SetofPlayers.size()==er.SetofPlayers.size())
			{
			return isSameSet(SetofPlayers,er.SetofPlayers);
		    }
		return false;
	    }
	 return false;
	}
	//GIVEN: Two sets of Players
	//RETURNS: True if the players are same in both the sets
	//EXAMPLE: If the set contains same players
	// if s1 and s2 are empty sets then
	//s1.isSameSet(s2)->true
	private boolean isSameSet(Set<Player> s1, Set<Player> s2)
	{
			HashSet < Player> plSet = new HashSet <Player> (s2);
		for(Iterator<Player> iter = s1.iterator();iter.hasNext();)
		{
			Player pl = (Player) iter.next();
			if(!plSet.contains(pl))
			{
				return false;
			}
		}
		return true;
	}
	
	//RETURNS:If r is a roster, then r.hashCode() always returns the same
    // value, even if r has some players whose status changes.
	//EXAMPLE: r1 is an empty roster
	//r1.hashcode()->0
	public int hashCode()
	{
	   return 0;
	}
	// RETURNS: If r1 and r2 are rosters of different sizes, then
    // r1.toString() is not the same string as r2.toString().
	//EXAMPLE: r1 is an empty roster
	//r1.toString() -> 0
	public String toString()
	{
		return "0";
	}
	
	//RETURNS: a roster consisting of the given player together
    // with all players on this roster.
    // EXAMPLE: r is an empty roster
	//r.with(p)  
	//r.with(p).size()->1
	@Override
	public RosterWithStream with(Player p) 
	{
		return new  NonEmptyRosterWithStreams(p);
	}

	@Override
	//RETURNS: a roster consisting of all players on this roster
    // except for the given player.
	//EXAMPLE: RosterWithStreams.empty().without(p)  =>  
	//RosterWithStreams.empty()
	public RosterWithStream without(Player p) 
	{
		return new EmptyRosterWithStreams();
	}

	@Override
	// RETURNS: true iff the given player is on this roster.
    // EXAMPLES:
    // RosterWithStreams.empty().has(p)  =>  false
    // If r is any roster, then
    // r.with(p).has(p)     =>  true
    //r.without(p).has(p)  =>  false
	public boolean has(Player p) 
	{
		return false;
	}
	//RETURNS: the number of players on this roster.
    // EXAMPLES :RosterWithStreams.empty().size()  =>  0
   // If r is a roster with r.size() == n, and r.has(p) is false, then
    //     r.without(p).size()          =>  n
    //     r.with(p).size()             =>  n+1
    //     r.with(p).with(p).size()     =>  n+1
    //     r.with(p).without(p).size()  =>  n
	@Override
	public int size() 
	{
		return 0;
	}

	@Override
	// RETURNS: the number of players on this roster whose current
    // status indicates they are available.
	//EXAMPLE: r is an empty roster
	//r.readyCount()->0
	public int readyCount() 
	{
		return 0;
	}

	@Override
	 // RETURNS: a roster consisting of all players on this roster
    // whose current status indicates they are available.
	//EXAMPLE: r is an empty roster
	// r.readyRoster().size()->0
	public RosterWithStream readyRoster() 
	{
		 return new EmptyRosterWithStreams();
	}

	@Override
    //RETURNS: an iterator that generates each player on this
    // roster exactly once, in alphabetical order by name.
	//EXAMPLE: r1 is an empty roster
	//r1.iterator().hasnext()->false
	public Iterator<Player> iterator() 
	{
		Iterator <Player> it = SetofPlayers.iterator(); 
		return it;
	}

	@Override
    // RETURNS: a sequential Stream with this RosterWithStream
    // as its source.
    // The result of this method generates each player on this
    // roster exactly once, in alphabetical order by name.
    // EXAMPLES:
    //     RosterWithStreams.empty().stream().count()  =>  0
    //     RosterWithStreams.empty().stream().findFirst().isPresent()
    //         =>  false
    //     RosterWithStreams.empty().with(p).stream().findFirst().get()
    //         =>  p
    //     this.stream().distinct()  =>  this.stream()
    //     this.stream().filter((Player p) -> p.available()).count()
    //         =>  this.readyCount()
	public Stream<Player> stream() 
	{
		return SetofPlayers.stream();
	}
	

}
