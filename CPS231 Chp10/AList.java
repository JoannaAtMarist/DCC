package Chp10Assignment;
import java.util.Arrays;


//source code
// not the altered version for Chp 11

public class AList<T> implements ListInterface<T>
{
	private T[] list;   // Array of list entries; ignore list[0]
	private int numberOfEntries;
	private boolean integrityOK;
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;

	public AList()
	{
		this(DEFAULT_CAPACITY);
	} // end default constructor

	public AList(int initialCapacity)
	{
		integrityOK = false;

		// Is initialCapacity too small?
		if (initialCapacity < DEFAULT_CAPACITY)
			initialCapacity = DEFAULT_CAPACITY;
		else // Is initialCapacity too big?
			checkCapacity(initialCapacity);

		// The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] tempList = (T[])new Object[initialCapacity + 1];
		list = tempList;
		numberOfEntries = 0;
		integrityOK = true;
	} // end constructor

	/** Adds a new entry to the end of this list.
	   Entries currently in the list are unaffected.
	   The list's size is increased by 1.
	   @param newEntry  The object to be added as a new entry. */
	public void add(T newEntry)
	{
		checkIntegrity();
		list[numberOfEntries + 1] = newEntry;
		numberOfEntries++;
		ensureCapacity();
		//     add(numberOfEntries + 1, newEntry);  // ALTERNATE CODE
	} // end add

	/** Adds a new entry at a specified position within this list.
	   Entries originally at and above the specified position
	   are at the next higher position within the list.
	   The list's size is increased by 1.
	   @param newPosition  An integer that specifies the desired
	                       position of the new entry.
	   @param newEntry     The object to be added as a new entry.
	   @throws  IndexOutOfBoundsException if either
	            newPosition < 1 or newPosition > getLength() + 1. */
	public void add(int newPosition, T newEntry)
	{
		checkIntegrity();
		// Assertion: The array list has room for another entry.
		if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1))
		{
			if (newPosition <= numberOfEntries)
				makeRoom(newPosition);
			list[newPosition] = newEntry;
			numberOfEntries++;
			ensureCapacity(); // Ensure enough room for next add
		}
		else
			throw new IndexOutOfBoundsException(
					"Given position of add's new entry is out of bounds.");
	} // end add

	private void makeRoom(int givenPosition)
	{
		int newIndex = givenPosition;
		int lastIndex = numberOfEntries;
		for (int index = lastIndex; index >= newIndex; index--)
			list[index + 1] = list[index];
	}  // end makeRoom

	/** Removes the entry at a given position from this list.
	   Entries originally at positions higher than the given
	   position are at the next lower position within the list,
	   and the list's size is decreased by 1.
	   @param givenPosition  An integer that indicates the position of
	                         the entry to be removed.
	   @return  A reference to the removed entry.
	   @throws  IndexOutOfBoundsException if either 
	            givenPosition < 1 or givenPosition > getLength(). */
	public T remove(int givenPosition)
	{
		checkIntegrity();
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
		{
			// Assertion: The list is not empty
			T result = list[givenPosition]; // Get entry to be removed
			// Move subsequent entries towards entry to be removed,
			// unless it is last in list
			if (givenPosition < numberOfEntries)
				removeGap(givenPosition);
			list[numberOfEntries] = null;
			numberOfEntries--;
			return result; // Return reference to removed entry
		}
		else
			throw new IndexOutOfBoundsException(
					"Illegal position given to remove operation.");
	} // end remove

	/** Removes all entries from this list. */
	public void clear()
	{ /* < Implementation deferred > */
		//questionable?
		numberOfEntries = 0;
	} // end clear

	/** Replaces the entry at a given position in this list.
	   @param givenPosition  An integer that indicates the position of
	                         the entry to be replaced.
	   @param newEntry  The object that will replace the entry at the
	                    position givenPosition.
	   @return  The original entry that was replaced.
	   @throws  IndexOutOfBoundsException if either
	            givenPosition < 1 or givenPosition > getLength(). */
	public T replace(int givenPosition, T newEntry)
	{ /* < Implementation deferred > */
		//questionable?
		checkIntegrity();
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
		{
			T originalEntry = list[givenPosition];
			list[givenPosition] = newEntry;
			return originalEntry;
		}
		else
			throw new IndexOutOfBoundsException(
					"Illegal position given to replace operation.");		
	} // end replace

	/** Retrieves the entry at a given position in this list.
	   @param givenPosition  An integer that indicates the position of
	                         the desired entry.
	   @return  A reference to the indicated entry.
	   @throws  IndexOutOfBoundsException if either
	            givenPosition < 1 or givenPosition > getLength(). */
	public T getEntry(int givenPosition) 
	{ /* < Implementation deferred > */
		//questionable?
		checkIntegrity();
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
		{
			return list[givenPosition];
		}
		else
			throw new IndexOutOfBoundsException(
					"Illegal position given to getEntry operation.");
	} // end getEntry

	/** Retrieves all entries that are in this list in the order in which
	   they occur in the list.
	   @return  A newly allocated array of all the entries in the list.
	            If the list is empty, the returned array is empty. */
	public T[] toArray()
	{
		checkIntegrity();

		// The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] result = (T[])new Object[numberOfEntries]; // Unchecked cast
		for (int index = 0; index < numberOfEntries; index++)
		{
			result[index] = list[index + 1];
		} // end for

		return result;
	} // end toArray

	/** Sees whether this list contains a given entry.
	   @param anEntry  The object that is the desired entry.
	   @return  True if the list contains anEntry, or false if not. */
	public boolean contains(T anEntry)
	{
		checkIntegrity();
		boolean found = false;
		int index = 1;
		while (!found && (index <= numberOfEntries))
		{
			if (anEntry.equals(list[index]))
				found = true;
			index++;
		} // end while
		return found;
	} // end contains

	/** Gets the length of this list.
	   @return  The integer number of entries currently in the list. */
	public int getLength()
	{
		return numberOfEntries;
	} // end getLength

	/** Sees whether this list is empty.
	   @return  True if the list is empty, or false if not. */
	public boolean isEmpty()
	{
		return numberOfEntries == 0; // Or getLength() == 0
	} // end isEmpty

	// Doubles the capacity of the array list if it is full.
	// Precondition: checkIntegrity has been called.
	private void ensureCapacity()
	{
		int capacity = list.length - 1;
		if (numberOfEntries >= capacity)
		{
			int newCapacity = 2 * capacity;
			checkCapacity(newCapacity); // Is capacity too big?
			list = Arrays.copyOf(list, newCapacity + 1);
		} // end if
	} // end ensureCapacity

	// Throws an exception if this object is not initialized.
	private void checkIntegrity()
	{
		if (!integrityOK)
			throw new SecurityException("Object is corrupt.");
	} // end checkIntegrity

	// Throws an exception if the client requests a capacity that is too large.
	private void checkCapacity(int capacity)
	{
		if (capacity > MAX_CAPACITY)
			throw new IllegalStateException("Attempt to create an object whose capacity exceeds " +
					"allowed maximum of " + MAX_CAPACITY);
	} // end checkCapacity

	// Shifts entries that are beyond the entry to be removed to the
	// next lower position.
	// Precondition: 1 <= givenPosition < numberOfEntries;
	//               numberOfEntries is list's length before removal;
	//               checkIntegrity has been called.
	// @author Frank M. Carrano, Timothy M. Henry
	// @version 5.0
	private void removeGap(int givenPosition)
	{
		int removedIndex = givenPosition;
		for (int index = removedIndex; index < numberOfEntries; index++)
			list[index] = list[index + 1];
	} // end removeGap

} // end AList