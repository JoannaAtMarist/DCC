package Chp26Assignment;

/*
 * Joanna Picciano
 * Chapter 26 Project 1
 */

import java.util.ArrayList;
import java.util.List;
/**
   A class that implements the ADT binary search tree by extending BinaryTree.
   Duplicate entries are allowed. A duplicate of an entry, if one occurs, is 
   placed in the entry's right subtree. Recursive version.

   @author Frank M. Carrano
   @author Timothy M. Henry
   @author Joseph Erickson
   @version 5.0
 */
public class BinarySearchTreeWithDuplicates<T extends Comparable<? super T>>
				extends BinaryTree<T> 
				implements SearchTreeWithDuplicatesInterface<T>
{
	public BinarySearchTreeWithDuplicates()
	{
		super();
	} // end default constructor

	public BinarySearchTreeWithDuplicates(T rootEntry)
	{
		super();
		setRootNode(new BinaryNode<>(rootEntry));
	} // end constructor

	public void setTree(T rootData) // Disable setTree (see Segment 25.6)
	{
		throw new UnsupportedOperationException();
	} // end setTree

	public void setTree(T rootData, BinaryTreeInterface<T> leftTree, 
			BinaryTreeInterface<T> rightTree)
	{
		throw new UnsupportedOperationException();
	} // end setTree

	public T getEntry(T entry)
	{
		return findEntry(getRootNode(), entry);
	} // end getEntry

	// Recursively finds the given entry in the binary tree rooted at the given node.
	private T findEntry(BinaryNode<T> rootNode, T entry)
	{	
		T result = null;
		//TODO
		//Did I do?
		// ***********************
		// filled in the missing statements
		// ***********************

		if (rootNode != null)
		{
			T rootData = rootNode.getData();
			int comparison = entry.compareTo(rootData);

			if (rootNode != null)
			{
				T rootEntry = rootNode.getData();

				if (entry.equals(rootEntry))
					result = rootEntry;
				else if (entry.compareTo(rootEntry) < 0)
					result = findEntry(rootNode.getLeftChild(), entry);
				else
					result = findEntry(rootNode.getRightChild(), entry);
			} // end if
		}
		
		return result;
	} // end findEntry
	
	public List<T> getAllEntriesEqualTo(T entry)	// *
	{
		return findAllEntriesEqualTo(getRootNode(), entry);
	} // end getAllEntriesEqualTo
	
	// Recursively finds all copies of the given entry in the binary tree
	// rooted at the given node.
	private List<T> findAllEntriesEqualTo(BinaryNode<T> rootNode, T entry)	// *
	{
		List<T> result = new ArrayList<>();
		
		//TODO
		//Did I Do?
		// ***********************
		// filled in the missing statements
		// ***********************

		if (rootNode != null)
		{
			T rootData = rootNode.getData();
			int comparison = entry.compareTo(rootData);
			
			if (comparison == 0)
			{
				result.add(rootData);
				result.addAll(findAllEntriesEqualTo(rootNode.getRightChild(), entry));
			}
			else if (comparison < 0)
			{
				result.addAll(findAllEntriesEqualTo(rootNode.getLeftChild(), entry));
			}
			else
			{
				result.addAll(findAllEntriesEqualTo(rootNode.getRightChild(), entry));
			}
		}
		return result;
	} // end findAllEntriesEqualTo

	public boolean contains(T entry)
	{
		return getEntry(entry) != null;
	} // end contains

	public T add(T newEntry)
	{
		T result = null;

		if (isEmpty())
			setRootNode(new BinaryNode<>(newEntry));
		else
			result = addEntry(getRootNode(), newEntry);

		return result;
	} // end add

	// Adds newEntry to the nonempty subtree rooted at rootNode.
	private T addEntry(BinaryNode<T> rootNode, T newEntry)	// *
	{
		assert rootNode != null;
		T result = null;
		int comparison = newEntry.compareTo(rootNode.getData());
		
		//TODO
		//Did I do?
		// ***********************
		// filled in the missing statements?
		// ***********************

		if (comparison == 0)
		{
			if (rootNode.hasRightChild())
			{
				result = addEntry (rootNode.getRightChild(), newEntry);
			}
			else
			{
				rootNode.setRightChild(new BinaryNode<>(newEntry));
				result = newEntry;
			}
		}
		else if (comparison < 0 )
		{
			if (rootNode.hasLeftChild())
			{
				result = addEntry(rootNode.getLeftChild(), newEntry);
			}
			else
			{
				rootNode.setLeftChild(new BinaryNode<>(newEntry));
				result = newEntry;
			}
		}
		else
		{
			if (rootNode.hasRightChild())
			{
				result = addEntry(rootNode.getRightChild(), newEntry);
			}
			else
			{
				rootNode.setRightChild(new BinaryNode<>(newEntry));
				result = newEntry;
			}
		}
		return result;
	} // end addEntry

	public T remove(T entry)
	{
		ReturnObject oldEntry = new ReturnObject(null);
		BinaryNode<T> newRoot = removeEntry(getRootNode(), entry, oldEntry);
		setRootNode(newRoot);

		return oldEntry.get();
	} // end remove

	// Removes an entry from the tree rooted at a given node.
	// Parameters:
	//    rootNode  A reference to the root of a tree.
	//    entry  The object to be removed.
	//    oldEntry  An object whose data field is null.
	// Returns:  The root node of the resulting tree; if entry matches
	//           an entry in the tree, oldEntry's data field is the entry
	//           that was removed from the tree; otherwise it is null.
	private BinaryNode<T> removeEntry(BinaryNode<T> rootNode, T entry,
			ReturnObject oldEntry)
	{
		if (rootNode != null)
		{
			T rootData = rootNode.getData();
			int comparison = entry.compareTo(rootData);

			if (comparison == 0)       // entry == root entry
			{
				oldEntry.set(rootData);
				rootNode = removeFromRoot(rootNode);
			}
			else if (comparison < 0)   // entry < root entry
			{
				BinaryNode<T> leftChild = rootNode.getLeftChild();
				BinaryNode<T> subtreeRoot = removeEntry(leftChild, entry, oldEntry);
				rootNode.setLeftChild(subtreeRoot);
			}
			else                       // entry > root entry
			{
				BinaryNode<T> rightChild = rootNode.getRightChild();
				rootNode.setRightChild(removeEntry(rightChild, entry, oldEntry));
			} // end if
		} // end if

		return rootNode;
	} // end removeEntry

	// Removes the entry in a given root node of a subtree.
	// Parameter:
	//    rootNode  A reference to the root of the subtree.
	// Returns:  The root node of the revised subtree.
	private BinaryNode<T> removeFromRoot(BinaryNode<T> rootNode)
	{
		// Case 1: rootNode has two children 
		if (rootNode.hasLeftChild() && rootNode.hasRightChild())
		{
			// Find node with largest entry in left subtree
			BinaryNode<T> leftSubtreeRoot = rootNode.getLeftChild();
			BinaryNode<T> largestNode = findLargest(leftSubtreeRoot);

			// Replace entry in root
			rootNode.setData(largestNode.getData());

			// Remove node with largest entry in left subtree
			rootNode.setLeftChild(removeLargest(leftSubtreeRoot));
		} // end if 

		// Case 2: rootNode has at most one child
		else if (rootNode.hasRightChild())
			rootNode = rootNode.getRightChild();
		else
			rootNode = rootNode.getLeftChild();

		// Assertion: If rootNode was a leaf, it is now null

		return rootNode; 
	} // end removeEntry

	// Finds the node containing the largest entry in a given tree.
	// Parameter:
	//    rootNode  A reference to the root node of the tree.
	// Returns:  The node containing the largest entry in the tree.
	private BinaryNode<T> findLargest(BinaryNode<T> rootNode)
	{
		if (rootNode.hasRightChild())
			rootNode = findLargest(rootNode.getRightChild());

		return rootNode;
	} // end findLargest

	// Removes the node containing the largest entry in a given tree.
	// Parameter:
	//    rootNode  A reference to the root node of the tree.
	// Returns:  The root node of the revised tree.
	private BinaryNode<T> removeLargest(BinaryNode<T> rootNode)
	{
		if (rootNode.hasRightChild())
		{
			BinaryNode<T> rightChild = rootNode.getRightChild();
			rightChild = removeLargest(rightChild);
			rootNode.setRightChild(rightChild);
		}
		else 
			rootNode = rootNode.getLeftChild();

		return rootNode;
	} // end removeLargest

	private class ReturnObject
	{
		private T item;

		private ReturnObject(T entry)
		{
			item = entry;
		} // end constructor

		public T get()
		{
			return item;
		} // end get

		public void set(T entry)
		{
			item = entry;
		} // end set
	} // end ReturnObject
} // end BinarySearchTreeWithDuplicates