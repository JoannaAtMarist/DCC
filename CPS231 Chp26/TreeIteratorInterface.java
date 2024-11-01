package Chp26Assignment;

import java.util.Iterator;
/**
   An interface of iterators for the ADT tree.
   
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public interface TreeIteratorInterface<T>
{
   public Iterator<T> getPreorderIterator();
   public Iterator<T> getPostorderIterator();
   public Iterator<T> getInorderIterator();
   public Iterator<T> getLevelOrderIterator();
} // end TreeIteratorInterface