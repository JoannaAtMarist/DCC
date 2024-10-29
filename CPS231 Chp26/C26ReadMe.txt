Assignment text below.
All files were provided. 
BinarySearchTreeWithDuplicates.java was modified.


Project 1:
This project initially indicated the following:
 Specify and implement a class of binary search trees in which duplicate entries are allowed. Place the duplicate of an entry in the entry’s right subtree, as suggested in Segment 26.4. Provide a method that searches the tree for a given entry and returns the first one it finds. Also, provide a similar method that returns a list of all entries that match the given one.  


 I have made it quite a bit simpler. Three methods in BinarySearchTreeWithDuplicates.java need to be completed:
• findEntry()
• findAllEntriesEqualTo()
• addEntry()
When they are all written properly, the output should match the output shown at the end of Driver.java.
 


Please provide the complete BinarySearchTreeWithDuplicates.java file with the appropriate additions.
There are several steps to this process:
1. Download all the code from the Assignment for Chapter 26 Project 1.
2. Add the BinarySearchTreeWithDuplicates.java included in this folder.
3. Run the program as is.
4. Add the BinaryTree.java from this folder and go through the debugging changes I made.
5. Run the program again and go through the output.
6. Now go through the implementation of addEntry().
7. Now go through the implementation of findEntry().
8. Now go through the implementation of findAllEntriesEqualTo().

The output should match:



/*
 The tree contains:
 Brent Brent Brent Donna Jerry Luke Luke Sue Tom Tom
 
 
 Test getEntry:
 
 Brent: Brent
 Donna: Donna
 Jerry: Jerry
 Luke:  Luke
 Sue:   Sue
 Tom:   Tom
 Missy: null
 
 Test getAllEntries:
 
 
 Luke's list contains 2 entries, as follows:
 Luke Luke
 
 
 Brent's list contains 3 entries, as follows:
 Brent Brent Brent
 
 
 Tom's list contains 2 entries, as follows:
 Tom Tom
 
 
 Sue's list contains 1 entries, as follows:
 Sue
 
 
 Missy is not in the tree
 
 
 Test contains:
 
 Is Brent in tree? true
 Is Donna in tree? true
 Is Jerry in tree? true
 Is Luke in tree?  true
 Is Sue in tree?   true
 Is Tom in tree?   true
 Is Missy in tree? false
 
 Removing (Brent); remove returns: Brent
 
 After removing Brent, the tree contains 9 items.
 
 
 Removing (Luke); remove returns: Luke
 
 After removing Luke, the tree contains 8 items.
 
 
 Removing (Tom); remove returns: Tom
 
 After removing Tom, the tree contains 7 items, as follows:
 Brent Brent Donna Jerry Luke Sue Tom
 
 Removing (Brent); remove returns: Brent
 
 After removing Brent, the tree contains 6 items.
 
 
 Removing (Luke); remove returns: Luke
 
 After removing Luke, the tree contains 5 items.
 
 
 Removing (Tom); remove returns: Tom
 
 After removing Tom, the tree contains 4 items, as follows:
 Brent Donna Jerry Sue
 
 Removing a missing item (Luke):    null
 
 Removing a missing item (Tom):     null
 
 The tree contains 4 items, as follows:
 Brent Donna Jerry Sue
 
 Brent's list contains 1 entries, as follows:
 Brent
 
 
 
 Test clear():
 
 
 The tree is empty after invoking clear().

 */

