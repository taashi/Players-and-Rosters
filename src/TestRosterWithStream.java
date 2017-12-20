import java.util.*;
import java.util.stream.Collectors;
public class TestRosterWithStream 
{
	public static void main(String args[])
	{
		//Creating players for testing
		Player p0 = Players.make("Mike");
		Player p1 = Players.make("Dave");
		Player p2 = Players.make("MArio");
		Player p3 = Players.make("Pablo");
		Player p4 = Players.make("Pablo1");
		Player p5 = Players.make("Escobar");
		Player p6 = Players.make("Jave");
		Player p7 = Players.make("Jacob");
		Player p8 = Players.make("Yuko");
		Player p9 = Players.make("Bry");
		Player p10 = Players.make("Zayne");
		
		//Changing the status of the players
		p8.changeInjuryStatus(true);
		p6.changeSuspendedStatus(true);
		p5.changeContractStatus(false);
		
        //Creating roster
		RosterWithStream r01 = RosterWithStreams.empty();
		RosterWithStream r04 = RosterWithStreams.empty();
		RosterWithStream r0 = RosterWithStreams.empty();
		RosterWithStream r7 = RosterWithStreams.empty();
		RosterWithStream r8 = RosterWithStreams.empty();
		RosterWithStream r13 = RosterWithStreams.empty();
		RosterWithStream r25 = RosterWithStreams.empty();
		RosterWithStream r28 = RosterWithStreams.empty();
		
		//Adding players to the roster
		RosterWithStream r26 = r25.with(p7);
		RosterWithStream r27 = r26.without(p7);
		RosterWithStream r02 = r01.with(p10).with(p8).with(p9);
		RosterWithStream r03 = r04.with(p10).with(p9);
		RosterWithStream r05 = r8.with(p10);
		RosterWithStream r06 = r8.with(p10);
		
		//Checking if the player is available or not
		assert p5.available()==false:"incorrect output";
		assert p6.available()==false:"incorrect output";
		
		//Checking for name of the player
		assert p10.toString()=="Zayne"==true:"incorrect output";
		
		//Checking for iterator on roster
		assert r03.iterator().hasNext()==true:"incorrect output";
		
		//Checking for readyRoster() on roster
		assert r02.readyRoster().equals(r02.without(p8))==true:"incorrect output";
		
		assert r05.readyRoster().equals(r06)==true:"incorrect output";
		
		//Checking for readyCount() on roster
		assert r02.readyCount()==2==true:"incorrect output";
		
		//Checking for without on roster with only 1 player
		assert r27.equals(r28)==true:"incorrect output";
		assert r7.without(p6).equals(r8)==true:"incorrect output";
		
		//Checking for hashcode() of an empty roster
		assert r7.hashCode()==0==true:"incorrect output";
		
		//Checking for has for an empty roster
		assert r7.has(p6)==false:"incorrect output";
		
		//Checking for size for an empty roster
		assert r7.size()==0==true:"incorrect output";
		
		//Checking for readycount for an empty roster
		assert r7.readyCount()==0==true:"incorrect output";
		
		//Checking for readyroster for an empty roster
		assert r7.readyRoster().equals(r8)==true:"incorrect output";
		
		//Checking for iterator for an empty roster
		assert r7.iterator().hasNext()==false:"incorrect output";
		
		//Checking for toString() on an empty roster
		assert r7.toString().equals("0")==true:"incorrect output";
		
		//Creating roster
		RosterWithStream r1 = r0.with(p0).with(p1).with(p2).with(p3).with(p4);
		RosterWithStream r14 = r0.with(p0).with(p1).with(p2).with(p3).with(p4);
		RosterWithStream r20 = r0.with(p0).with(p2).with(p3).with(p4);
		RosterWithStream r9 = r0.with(p0).with(p1).with(p2).with(p3).with(p4);
		RosterWithStream r11 = r0.with(p0);
		RosterWithStream r12 = r0.with(p0);
		RosterWithStream r15 = r13.with(p6).with(p7);
		p7.changeInjuryStatus(true);
		p6.changeInjuryStatus(true);
		
		//Checking for Readyroster for a non empty roster
		assert r15.readyRoster().equals(r25)==true:"incorrect output";
		
		//Checking for Has() for non empty roster
		assert r9.has(p1)==true:"incorrect output";
		
		//Checking for Size() of roster
		assert r15.size()==2==true:"incorrect output";
		
		//Checking for Inequality of two roster
		assert r0.equals(r9)==false:"incorrect output";
		
		//Checking for EQuality of two roster having players
		assert r1.equals(r9)==true:"incorrect output";
		
		//Checking for with() for a roster
		assert r11.equals(r12)==true:"incorrect output";
		
		//Checking for without() for a roster
		assert r14.without(p1).equals(r20)==true:"incorrect output";
		
		//Checking for toString() of a roster
		assert r1.toString().equals("5")==true:"incorrect output";
		
		//Checking for Count of an empty roster
		assert(RosterWithStreams.empty().stream().count())==0==true:
			"incorrect output";
		
		//Checking for HashCode of two rosters are equal since they have 
		//same players
		assert r1.hashCode()==r9.hashCode()==true:"incorrect output";

		//TEST 1:boolean allMatch(Predicate<? super T> predicate)
		p2.changeInjuryStatus(true);
		p1.changeInjuryStatus(true);
		RosterWithStream r3 = r0.with(p1).with(p2).with(p3);
		assert r3.stream().allMatch((Player p)->p.isInjured())==false:
			"incorrect output";

		//TEST 2: boolean anyMatch(Predicate<? super T> predicate)
		assert r3.stream().anyMatch((Player p)->p.isInjured())==true:
			"incorrect output";

		//TEST 3:long count()
		assert r1.stream().count()==5==true:"incorrect output";

		//TEST 4:Stream <T>     distinct()
		assert r1.stream().distinct().count()==r1.stream().count()==
				true:"incorrect output";

		//TEST 5: Stream<T>     filter(Predicate<? super T> predicate)
		assert r1.stream().filter((Player p) -> p.available()).count()==3
				==true:"incorrect output";

		//TEST 6: Optional<T>   findAny()
		assert r3.stream().findAny().get().name()!=""==true:"incorrect output";

		//TEST 7: Optional<T>   findFirst()
		assert r1.stream().findFirst().isPresent()==true:"incorrect output";
		
		//TEST 8: <R> Stream<R> map(Function<? super T,? extends R> mapper)
		Set<String> r4 = new HashSet <String>();
		r4.add("Pablo");
		r4.add("MArio");
		r4.add("Dave");
		assert r4.equals((r3.stream().map(pl->pl.name()).
				collect(Collectors.toSet())))==true:"incorrect output";

		//TEST 9:T reduce(T identity, BinaryOperator<T> accumulator)
		Boolean bol = r15.stream().map(Player->Player.isInjured()).
				reduce(true,(a,b)->a && b);
		assert bol==true:"incorrect output";	
		
		//TEST 10:Stream<T>     skip(long n)
		assert r3.stream().skip(1).count()==2==true:"incorrect output";
		
		//TEST 11: Object[]      toArray()
		assert r4.stream().toArray().length==3==true:"incorrect output";
		
		//TEST 12:void   forEach(Consumer<? super T> action)
		Set<Player> r6 = new HashSet <Player>();
		Set<Player> r17 = new HashSet <Player>();
		r6.add(p0);
		r6.add(p1);
		r6.add(p7);
		r6.forEach((Player pl) ->{if (pl.available()==true)
			r17.add(pl);});
		Set<Player> r18 = new HashSet <Player>();
		r18.add(p0);
		assert r18.equals(r17)==true:"incorrect output";

		r4 = r3.stream().map(pl->pl.name()).collect(Collectors.toSet());
		assert r1.stream().mapToInt(pl->{if (pl.available()==true)
			return 1;
		else return 0;
		}).sum()==3==true:"incorrect output";	
	}

}
