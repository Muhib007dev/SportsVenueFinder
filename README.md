# SportsVenueFinder
Problem Statement:- ABC company has started a new service in which they rent two sports places that is futsal court and badminton court. Both the places are on rent on particular days and some new clients wants to rent the place. You have to find which client can rent the place without interfearing already booked place and which client cannot book the place. There will be two array of objects inputs, first array of client, which is already rented with start date, end date, and which place booked, and second array of object is the clients who wants to rent the place.

Examle:- 
Input:
Object1[]:- [0]Name: "e", start:7, end:8, place:"futsal court";
            [1]Name: "b", start:3, end:4, place:"badminton court";
            [2]Name: "a", start:1, end:2, place:"futsal court";
Object2[]:- [0]Name: "c", start:3, end:4, place:"futsal court";
            [1]Name: "d", start:1, end:2, place:"badminton court";
            [2]Name: "f", start:6, end:7, place:"futsal court";
            
Output:-
Possible booking list:-
d
c
Not Possible booking list:-
f
