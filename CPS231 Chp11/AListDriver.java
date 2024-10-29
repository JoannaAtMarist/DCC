package Chp11Assignment;



/*
 * Joanna Picciano
 * Chapter 11
 * Project 1: Write a program that thoroughly tests the class AList 
 * (developed in the textbook starting with Listing 11-1). Include:
 * 1. A constructor that creates a list from a given array of objects. 
 * 2. The method – public int getPosition(T anObject) – 
 * that returns the position of a given object in the list, 
 * where T is the generic type of the objects in the list.
 */

public class AListDriver {

	public static void main(String[] args) {


		
		//create an array of objects
		String anArray[] = new String[]{"Alvin", "Simon", "Theodore"};
		//create the list from the array
		AList<String> list = new AList<>(anArray);
		
		System.out.println("Testing new constructor:");
		printList(list);
		
		//Test add
		list.add("Garfield");
		
		//Test add with position
		list.add(1, "Snoopy");
		System.out.println("Testing adds:");
		printList(list);
		
		//Test new getPosition method
		System.out.println("Testing getPosition of Alvin: " + list.getPosition("Alvin"));
		
		//Test clear
		list.clear();
		
		//test add after clear
		list.add("Charlie Brown");
		System.out.println("\nNew List:");
		printList(list);

	} //end of main

	
	public static void printList(AList<String> list)
	{
		Object[] ourArray = list.toArray();
		for (int index = 0; index < ourArray.length; index++)
		{
			System.out.print(ourArray[index] + " \n");
		} // end for
		System.out.println();
		
	} //end of printList
	
} //end of class
