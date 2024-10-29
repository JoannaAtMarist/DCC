package Chp12Assignment;


/*
 * Joanna Picciano
 * Chapter 12
 * Project 1: Write a program that thoroughly tests the class LListWithTail (this begins with Listing 12-1 in the textbook and evolves through the discussion of tail references in section 12-24). 
 * Include methods that answer Exercises 1 and 2 in Chapter 10. 
 * Include interface definition, class implementing the interface, and the test program in your submitted work
 */

public class LListWithTailDriver {

	public static void main(String[] args) {

		LListWithTail<String> myList = new LListWithTail<>();

		//If myList is an empty list of strings, 
		//what does it contain after the following statements execute?
		myList.add("A");
		myList.add("B");
		myList.add("C");
		myList.add("D");
		myList.add(1, "one");
		myList.add(1, "two");
		myList.add(1, "three");
		myList.add(1, "four");
		System.out.println("Chapter 10 Exercise 1:");
		printList(myList);

		//If myList is an empty list of strings, 
		//what does it contain after the following statements execute?
		myList = new LListWithTail<>();
		myList.add("alpha");
		myList.add(1, "beta");
		myList.add("gamma");
		myList.add(2, "delta");
		myList.add(4, "alpha");
		myList.remove(2);
		myList.remove(2);
		myList.replace(3, "delta");
		System.out.println("\nChapter 10 Exercise 2:");
		printList(myList);

	} //end of main


	public static void printList(LListWithTail<String> list)
	{
		Object[] ourArray = list.toArray();
		for (int index = 0; index < ourArray.length; index++)
		{
			System.out.print(ourArray[index] + " \n");
		} // end for
		System.out.println();

	} //end of printList

}
