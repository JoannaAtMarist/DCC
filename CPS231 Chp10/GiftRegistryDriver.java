package Chp10Assignment;

/*
 * Joanna Picciano
 * Chapter 10
 * Project 16: Some online stores offer their customers a gift registry, where they can enter items that they would like to receive as gifts for a special event. Let's let the customer rank the gifts in order of preference in the registry. You need to: 
 * 1. Design an ADT to represent the gift registry.
 * 2. Implement your ADT in pseudocode using the operations of the ADT list.
 * Implement your ADT using the class OurList. OurList comes from Project 2 in Chapter 10, which states: using the standard class ArrayList, define a class OurList of lists that implement the interface ListInterface, as defined in Listing 10-1 of this chapter. 
 */

public class GiftRegistryDriver {

	public static void main(String[] args) {

		//Create a list
		ListInterface<String> list = new GiftRegistry<>();
		
		//Create some gifts
		String crib = "Crib";
		String bedding = "Jungle themed baby bedding";
		String cTable = "Jungle themed baby changing table";
		String bottle = "Bottle";
		String diapers = "Pack of Diapers";
		String stroller = "Stroller";
		String onesieD = "Pack of baby onesies, Dinosaur theme";
		String onesieP = "Pack of baby onesies, Pokemon theme";
		String onesieS = "Pack of baby onesies, Shark theme";
		
		//Test add
		list.add(crib);
		list.add(bottle);
		list.add(stroller);
		System.out.println("Testing add, Current list:");
		printList(list);
		
		
		//Test add with rank
		list.add(1, diapers);
		list.add(3, onesieS);
		list.add(4, onesieP);
		System.out.println("Testing add with rank, Current list:");
		printList(list);
		
		//test change rank
		// should change crib at position 2 to position 6,
		// shifting other entries appropriately
		((GiftRegistry<String>) list).changeRank(2, 6);
		System.out.println("Testing change rank,\nIt should change crib at position 2 to position 6\nCurrent list:");	
		printList(list);
		
		//test change rank
		// should change bottle at position 4 to position 2,
		// shifting other entries appropriately
		((GiftRegistry<String>) list).changeRank(bottle, 2);
		System.out.println("Testing change rank again,\nIt should change bottle at position 4 to position 2\nCurrent list:");	
		printList(list);
		
		//clear the list
		list.clear();
		
		//start over?
		list.add(onesieD);
		list.add(bedding);
		list.add(2, cTable);
		System.out.println("Testing clear and add, Current list:");
		printList(list);
		
	} //end of main

	public static void printList(ListInterface<String> list)
	{
		Object[] ourArray = list.toArray();
		for (int index = 0; index < ourArray.length; index++)
		{
			int rank = index+1;
			System.out.print("Rank " + rank + ": " + ourArray[index] + " \n");
		} // end for
		System.out.println();
		
	} //end of printList

} //end of driver class
