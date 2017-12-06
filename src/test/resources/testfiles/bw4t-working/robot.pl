:- dynamic in/1,			% matches the in/1 percept
	state/1,			% matches the state/1 percept
	zone/5,				% matches the zone/5 percept
	color/2,
	sequence/1,
	atBlock/1,
	holding/1,
	sequenceIndex/1,
	at/1,
	posistion/3,
	location/3,
	visited/1,
	block/3,
	place/1,
	goToBlock/1,
	drop/1,
	deliver/1,
	pickUp/1,
	seeing/1,
	return/1,
	getting/1,
	putDown/0.
	
% A room is a place with exactly one neighbour, i.e., there is only one way to get to and from that place.
room(PlaceID) :- zone(_,PlaceID,_,_,Neighbours), length(Neighbours,1).


% Exercise 2.2: insert a definition of the predicate "nextColorInSeq(Color)".
nextColorInSeq(Color) :- sequence(X), sequenceIndex(I), nth0(I,X,Color).
nthNextColorInSeq(Color, Int) :- sequence(X), sequenceIndex(I), Tmp is I + Int, nth0(Tmp, X, Color).  
% Check if the index is 1 larger than the index of the last color, if true then the agent is finished.
finished :- sequenceIndex(I1), sequence(X), length(X,I2), I1 =:= I2.