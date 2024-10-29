package Chp26Assignment;

import java.util.Iterator;
import java.util.List;

/** 
   A driver that demonstrates the class BinarySearchTreeWithDuplicates.

   @author Frank M. Carrano
   @version 5.0
 */
public class Driver 
{
	public static void main(String[] args) 
	{
		String brent = "Brent";
		String donna = "Donna";
		String jerry = "Jerry";
		String luke  = "Luke";
		String sue   = "Sue";
		String tom   = "Tom";
		String missy = "Missy";

		SearchTreeWithDuplicatesInterface<String> myBST = new BinarySearchTreeWithDuplicates<>();
		myBST.add(luke);
		myBST.add(brent);
		myBST.add(donna);
		myBST.add(tom);
		myBST.add(sue);
		myBST.add(jerry);
		myBST.add(brent);
		myBST.add(brent);
		myBST.add(tom);
		myBST.add(luke);

		System.out.println("The tree contains:");
		displayTree(myBST);
		System.out.println();

		System.out.println("\nTest getEntry:\n");
		System.out.println("Brent: " + myBST.getEntry(brent));
		System.out.println("Donna: " + myBST.getEntry(donna));
		System.out.println("Jerry: " + myBST.getEntry(jerry));
		System.out.println("Luke:  " + myBST.getEntry(luke));
		System.out.println("Sue:   " + myBST.getEntry(sue));
		System.out.println("Tom:   " + myBST.getEntry(tom));
		System.out.println("Missy: " + myBST.getEntry(missy));

		System.out.println("\nTest getAllEntries:\n");

		List<String> entryList = myBST.getAllEntriesEqualTo(luke);      
		displayList(luke, entryList);
		entryList = myBST.getAllEntriesEqualTo(brent);      
		displayList(brent, entryList);
		entryList = myBST.getAllEntriesEqualTo(tom);      
		displayList(tom, entryList);
		entryList = myBST.getAllEntriesEqualTo(sue);      
		displayList(sue, entryList);
		entryList = myBST.getAllEntriesEqualTo(missy); 
		displayList(missy, entryList);
		
		System.out.println("\n\nTest contains:\n");		
		System.out.println("Is Brent in tree? " + myBST.contains(brent));
		System.out.println("Is Donna in tree? " + myBST.contains(donna));
		System.out.println("Is Jerry in tree? " + myBST.contains(jerry));
		System.out.println("Is Luke in tree?  " + myBST.contains(luke));
		System.out.println("Is Sue in tree?   " + myBST.contains(sue));
		System.out.println("Is Tom in tree?   " + myBST.contains(tom));
		System.out.println("Is Missy in tree? " + myBST.contains(missy));

		// test remove
		System.out.println("\nRemoving (Brent); remove returns: " + myBST.remove(brent));
		System.out.println("\nAfter removing Brent, the tree contains "
				+ myBST.getNumberOfNodes() + " items.");

		System.out.println("\n\nRemoving (Luke); remove returns: " + myBST.remove(luke));
		System.out.println("\nAfter removing Luke, the tree contains "
				+ myBST.getNumberOfNodes() + " items.");

		System.out.println("\n\nRemoving (Tom); remove returns: " + myBST.remove(tom));
		System.out.println("\nAfter removing Tom, the tree contains "
				+  myBST.getNumberOfNodes() + " items, as follows:");
		displayTree(myBST);

		System.out.println("\nRemoving (Brent); remove returns: " + myBST.remove(brent));
		System.out.println("\nAfter removing Brent, the tree contains "
				+ myBST.getNumberOfNodes() + " items.");

		System.out.println("\n\nRemoving (Luke); remove returns: " + myBST.remove(luke));
		System.out.println("\nAfter removing Luke, the tree contains "
				+ myBST.getNumberOfNodes() + " items.");

		System.out.println("\n\nRemoving (Tom); remove returns: " + myBST.remove(tom));
		System.out.println("\nAfter removing Tom, the tree contains "
				+  myBST.getNumberOfNodes() + " items, as follows:");
		displayTree(myBST);

		System.out.println("\nRemoving a missing item (Luke):    " + myBST.remove(luke));
		System.out.println("\nRemoving a missing item (Tom):     " + myBST.remove(tom));

		System.out.println("\nThe tree contains " + myBST.getNumberOfNodes() +
				" items, as follows:");
		displayTree(myBST);

		entryList = myBST.getAllEntriesEqualTo(brent);      
		displayList(brent, entryList);

		// test clear()
		System.out.println("\n\nTest clear():\n");
		myBST.clear();

		if (myBST.isEmpty())
			System.out.println("\nThe tree is empty after invoking clear().");
		else
			System.out.println("\nclear() error");		


	} // end main

	// Tests getInorderIterator.
	public static void displayTree(SearchTreeWithDuplicatesInterface<String> bst)
	{
		Iterator<String> traverser = bst.getInorderIterator();
		while (traverser.hasNext())
			System.out.print(traverser.next() + " ");

		System.out.println();
	} // end displayTree

	public static void displayList(String name, List<String> list)
	{
		if (list != null)
		{
			int numberOfEntries = list.size();
			System.out.println("\n" + name + "'s list contains " + numberOfEntries
					+ " entries, as follows:");
			for (int i = 0; i < numberOfEntries; i++)
				System.out.print(list.get(i) + " ");
			System.out.println("\n");
		}
		else
			System.out.println("\n" + name + " is not in the tree");

	} // end displayList
}  // end Driver

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