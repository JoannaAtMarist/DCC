package Chp10Assignment;


/*
 * Joanna Picciano
 * Chapter 10
 * Project 16: Some online stores offer their customers a gift registry, where they can enter items that they would like to receive as gifts for a special event. Let's let the customer rank the gifts in order of preference in the registry. You need to: 
 * 1. Design an ADT to represent the gift registry.
 * 2. Implement your ADT in pseudocode using the operations of the ADT list.
 * Implement your ADT using the class OurList. OurList comes from Project 2 in Chapter 10, which states: using the standard class ArrayList, define a class OurList of lists that implement the interface ListInterface, as defined in Listing 10-1 of this chapter. 
 */

public class GiftRegistry<T> extends OurList<T> implements ListInterface<T> {

	
	/**
	 * Changes the rank of the gift
	 * @param currentRank
	 * @param toRank
	 */
	public void changeRank (int currentRank, int toRank)
	{
		//remove from current position
		T temp = remove(currentRank);
		//add to new position
		add(toRank, temp);
	}
	
	/**
	 * Changes the rank of the gift
	 * @param gift
	 * @param toRank
	 */
	public void changeRank (T gift, int toRank)
	{
		int currentRank = findIndex(gift);
		T temp = remove(currentRank);
		add(toRank, temp);
	}
	
	/**
	 * Finds the rank of the specified gift
	 * @param gift
	 * @return i
	 */
	private int findIndex(T gift)
	{
		for (int i = 0; i < getLength(); i++) {
			if (getEntry(i + 1).equals(gift)) {
				return i+1;
			}
		}
		return -1; //  not found
	}
	
} //end GiftRegistry class
