import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.*;

public class NonEmptyRosterWithStreams implements RosterWithStream 
{
	//SetofPlayers is a set of players
	private Set <Player> SetofPlayers = new HashSet <Player>();
	// If r1 and r2 are RosterWithStream objects, then r1.equals(r2)
    // if and only if
    // every player on roster r1 is also on roster r2, and
    // every player on roster r2 is also on roster r1.
	//EXAMPLE: If r1 and r2 have same players objects
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
	 if(obj instanceof NonEmptyRosterWithStreams)
		{
			NonEmptyRosterWithStreams er = (NonEmptyRosterWithStreams)obj;
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
	//EXAMPLE: r1 is a roster with 3 players
	//r1.hashcode()->sum of hashcode of 3 players
	public int hashCode()
	{
		int c = 0;
		for(Player pl : SetofPlayers)
		{
			c = c + Objects.hashCode(pl);
			
		}
		return c;
	}
	// RETURNS: If r1 and r2 are rosters of different sizes, then
    // r1.toString() is not the same string as r2.toString().
	//EXAMPLE: r1 is a roster qith 2 players
	//r1.toString() -> "2"
	public String toString()
	{
		return ((Integer)SetofPlayers.size()).toString();
	}
    //CONSTRUCTOR TEMPLATE
	//// A RosterWithStream object represents a set of players.
	//GIVEN : A player
	//RETURNS: A player added to the setofplayers
	//EXAMPLE: r1 is an empty roster
	//r1.with(p).size()->1
	public NonEmptyRosterWithStreams(Player pl)
	{
		SetofPlayers.add(pl);
	}
	//GIVEN : A player , a playerset and a string-with or without
	//RETURNS: A player added to the setofplayers or removed from the setof 
	//players as per the string with or without given in the fn
	//EXAMPLE: r1 is an  roster having 1 player
	//r1.with(p).size()->2
	//r1.without(p).size()->1
	public NonEmptyRosterWithStreams
	(Player pl, Set<Player>PlayersinSet, String choice) 
	{
		switch(choice) 
		{
		case("without") :
			SetofPlayers=PlayersinSet.stream().collect(Collectors.toSet());
		    SetofPlayers.remove(pl);
		break;
		case("with") :
			SetofPlayers=PlayersinSet.stream().collect(Collectors.toSet());
			SetofPlayers.add(pl);
		}	
	}
		
	@Override
	//RETURNS: a roster consisting of the given player together
    // with all players on this roster.
    // EXAMPLE: r is a roster with 2 players
	//r.with(p)  
	//r.with(p).size()->3
	public RosterWithStream with(Player p) 
	{
		return new NonEmptyRosterWithStreams(p,SetofPlayers,"with");
	}

	@Override
	//RETURNS: a roster consisting of all players on this roster
    // except for the given player.
	//EXAMPLE: RosterWithStreams.empty().without(p)  =>  
	//RosterWithStreams.empty()
	public RosterWithStream without(Player p) 
	{
		if(SetofPlayers.size()==1 && SetofPlayers.contains(p))
			return new EmptyRosterWithStreams();
		return new NonEmptyRosterWithStreams(p,SetofPlayers,"without");
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
		return SetofPlayers.stream().
				filter(player -> player.equals(p)).count() != 0;
	}

	@Override
	//RETURNS: the number of players on this roster.
    // EXAMPLES :RosterWithStreams.empty().size()  =>  0
   // If r is a roster with r.size() == n, and r.has(p) is false, then
    //     r.without(p).size()          =>  n
    //     r.with(p).size()             =>  n+1
    //     r.with(p).with(p).size()     =>  n+1
    //     r.with(p).without(p).size()  =>  n
	public int size() 
	{
		return (int) SetofPlayers.stream().count();
	}

	@Override
	// RETURNS: the number of players on this roster whose current
    // status indicates they are available.
	//EXAMPLE: r is a roster with 3 players available out of 4 players
	//r.readyCount()->3
	public int readyCount() 
	{
		 SetofPlayers.stream().collect(Collectors.toSet());
		 SetofPlayers.removeIf(player->!player.available());
		 return SetofPlayers.size();
	}

	@Override
	 // RETURNS: a roster consisting of all players on this roster
    // whose current status indicates they are available.
	//EXAMPLE: r is a roster with 3 players available out of 4 players
	// r.readyRoster().size()->3
	public RosterWithStream readyRoster() 
	{
		Set <Player> avlblPlayers = new HashSet <Player>();
		Set <Player> rstrPlayers = new HashSet <Player>();
		RosterWithStream rstr0 = new EmptyRosterWithStreams();
		RosterWithStream rstr1 = new EmptyRosterWithStreams();
		avlblPlayers = SetofPlayers.stream().
				filter(pl->pl.available()==true).collect(Collectors.toSet());
		if(! avlblPlayers.equals(null))
		{
			for(Player p: avlblPlayers)
			{
				if(rstr0.stream().equals(null))
				{
					rstr0 = rstr0.with(p);
					rstrPlayers.add(p);
				}
				else 
				{
					rstr0 = new NonEmptyRosterWithStreams(p,rstrPlayers,"with");
					rstrPlayers.add(p);
				}
			}
				return rstr0;		
		}
		else
			return rstr1;	
	}

	@Override
    //RETURNS: an iterator that generates each player on this
    // roster exactly once, in alphabetical order by name.
	//EXAMPLE: r1 is a roster of size 3
	//r1.iterator().hasnext()->true
	public Iterator<Player> iterator() 
	{
		List <Player> PlayerList = new ArrayList <Player>();
		PlayerList=SetofPlayers.stream().collect(Collectors.toList());
		PlayerList.sort((pl1,pl2)-> (pl1.name().compareTo(pl2.name())));
		return PlayerList.iterator();
	}

	@Override
    // RETURNS: a sequential Stream with this RosterWithStream
    // as its source.
    // The result of this method generates each player on this
    // roster exactly once, in alphabetical order by name.
    // EXAMPLES:
    //RosterWithStreams.empty().stream().count()  =>  0
    //RosterWithStreams.empty().stream().findFirst().isPresent()
    //=>  false
    //RosterWithStreams.empty().with(p).stream().findFirst().get()
    // =>  p
    //this.stream().distinct()  =>  this.stream()
    //this.stream().filter((Player p) -> p.available()).count()
    // =>  this.readyCount()
	public Stream<Player> stream() 
	{
		List <Player> ListofPlayers = new ArrayList <Player>();
		ListofPlayers=SetofPlayers.stream().collect(Collectors.toList());
		ListofPlayers.sort((pl1,pl2)-> (pl1.name().compareTo(pl2.name())));
		return ListofPlayers.stream();
	}

}
