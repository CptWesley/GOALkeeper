:- dynamic(on/2).
:- dynamic(clear/0).

% only blocks can be on top of another object.
block(X) :- on(X, _).
% a block is clear if nothing is on top of it.
clear(X) :- block(X), not( on(_, X) ).
% the table is always clear.
clear('table').

% the tower predicate holds for any stack of blocks that sits on the table.
tower([X]) :- on(X, 'table').
tower([X, Y| T]) :- on(X,Y), tower([Y| T]).

% The blocks of a tower are available when:
% There is no tower
available([]).
% All blocks are already in a tower
available(T) :- tower(T).
% A block in the tower is clear.
available([X|T]) :- clear(X), available(T). 
% Blocks are stacked in reverse order of the tower to build.
available([X,Y|T]) :- on(Y,X), available([Y|T]).

% define a subtower
subTower(_,[]).
subTower(T,T).
subTower([X|T],SubT) :- subTower(T,SubT).