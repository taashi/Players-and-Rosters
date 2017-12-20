# Players-and-Rosters
There are two abstract data types, Player and Roster. 
These ADTs would be useful to the coaches of a team sport as they 
consider different rosters they might use for an upcoming game. 
In the NFL, for example, a team's roster can contain up to 53 players,
but some of those players may be unavailable because of suspension or injury. 
On game day, the head coach must designate a smaller roster with no more than 
46 players, and coaches routinely consider several of those smaller rosters 
before deciding which roster to use in the game.
To make it easier to consider several similar rosters, 
the Roster ADT is immutable. The Player ADT is mutable, 
however, because an injury, trade, or some other change to a player's 
status should be propagated immediately to all rosters that use the player.

