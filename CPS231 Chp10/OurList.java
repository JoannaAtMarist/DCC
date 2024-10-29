package Chp10Assignment;

//source code for use with c10p16
// from Brightspace

import java.util.ArrayList;
/**
   A class that implements the ADT list by using an instance of Vector.
 
   @author Frank M. Carrano, Charles Hoot
   @version 5.0
*/
public class OurList<T> implements ListInterface<T>, Cloneable
{
	private ArrayList<T> list; // Entries in list

	public OurList()
	{
		list = new ArrayList<T>();  // When necessary, vector doubles in size
	} // end default constructor

	public OurList(int initialSize)
	{
		list = new ArrayList<T>(initialSize);
	} // end constructor

	public void add(T newEntry)
	{
		// Place new entry after last current entry
		list.add(newEntry);
	} // end add

	public void add(int newPosition, T newEntry)
	{
		if ((newPosition >= 1) && (newPosition <= list.size() + 1))
			list.add(newPosition - 1, newEntry);	 
		else
			throw new IndexOutOfBoundsException("Attempt to add to a list at an illegal position.");
	} // end add

	public T remove(int givenPosition)
	{
      if ((givenPosition >= 1) && (givenPosition <= list.size()))
		{	
         // Assertion: The list is not empty
			return list.remove(givenPosition - 1);
		}
      else
         throw new IndexOutOfBoundsException("Attempt to remove an entry from a list at an illegal position.");
	} // end remove

   public void clear()
   {
      list.clear();
   } // end clear
   
	public T replace(int givenPosition, T newEntry)
	{
 		if ((givenPosition >= 1) && (givenPosition <= list.size()))
		{
			// Assertion: The list is not empty
			return list.set(givenPosition - 1, newEntry);
		}
		else
         throw new IndexOutOfBoundsException("Attempt to replace an entry at an illegal position.");
      // NOTE: The method set throws an IndexOutOfBoundsException, if givenPosition is out of bounds.
      // See the next method.
	} // end replace
// NOTE: Two versions of getEntry give different error messages:
/*	public T getEntry(int givenPosition)
	{
      return list.get(givenPosition - 1); // Throws IndexOutOfBoundsException if position is illegal
      /*
       This is the output when givenPosition is illegal"
       
       Exception in thread "main" java.lang.IndexOutOfBoundsException: Index 11 out-of-bounds for length 9
       at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
       at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
       at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:248)
       at java.base/java.util.Objects.checkIndex(Objects.java:372)
       at java.base/java.util.ArrayList.get(ArrayList.java:439)
       at OurList.getEntry(OurList.java:67)
       at Driver2.testStringList(Driver2.java:44)
       at Driver2.main(Driver2.java:12)
	} // end getEntry
*/
   
   public T getEntry(int givenPosition)
   {
      try
      {
         return list.get(givenPosition - 1); // Throws IndexOutOfBoundsException if position is illegal
      }
      catch (IndexOutOfBoundsException e) // Catch exception to give our own message
      {
         throw new IndexOutOfBoundsException("Attempt to retrieve an entry from an illegal position.");
      }
      /*
       This is the output when givenPosition is illegal"
       Exception in thread "main" java.lang.IndexOutOfBoundsException: Attempt to retrieve an entry from an illegal position.
      at OurList.getEntry(OurList.java:92)
      at Driver2.testStringList(Driver2.java:44)
      at Driver2.main(Driver2.java:12)
      */
   } // end getEntry

   public T[] toArray()
   {
      @SuppressWarnings("unchecked")
      T[] result = (T[])list.toArray();
      
      return result;
   } // end toArray

   public boolean contains(T anEntry)
	{
		return list.contains(anEntry);
	} // end contains

	public int getLength()
	{
		return list.size();
	} // end getLength

	public boolean isEmpty()
	{
		return list.isEmpty();
	} // end isEmpty
} // end OurList

