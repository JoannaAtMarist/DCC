package Chp26Assignment;

import java.util.List;
/**
   An interface for a search tree that allows duplicate entries.

   @author Frank M. Carrano
   @version 5.0
 */
public interface SearchTreeWithDuplicatesInterface<T extends Comparable<? super T>> 
       extends SearchTreeInterface<T>
{
	/** Retrieves all entries in this tree that match a given one.
		@param entry  An object to be found.
		@return  Either a list of the found objects or
				   null if no such object exists. */
	public List<T> getAllEntriesEqualTo(T entry);

	/** Adds a new entry to this tree, allowing duplicate entries.
		 @param newEntry  An object to be added to the tree.
		 @return  newEntry if a duplicate exists already, or returns null
                if newEntry is added as a unique entry. */
	public T add(T newEntry); // Inherited method, but specifications are different
} // end SearchTreeWithDuplicatesInterface