package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		this.head = new LLNode<E>(null);
		this.tail = new LLNode<E>(null);
		this.head.next = this.tail;
		this.tail.prev = this.head;


	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		if(element == null){
			throw new NullPointerException("Null Pointer Exception- Null Element aren't allowed");
		}
		else{
			LLNode<E> new_node = new LLNode<E>(element);
			tail.prev.next = new_node;
			new_node.prev = tail.prev;
			new_node.next = tail;
			tail.prev = new_node;
			size++;
			return true;
		}
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		if(index < 0 || index >= this.size){
			throw new IndexOutOfBoundsException("Index out of bound: " + index);
		}
		LLNode<E> curr_node = this.head.next;
		for(int i = 0; i < index; i++){
			curr_node = curr_node.next;
		}
		return curr_node.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		if(element == null){
			throw new NullPointerException("Null Pointer Exception- Null Element aren't allowed");
		}
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException("Out of range. Invalid index");
		}

		LLNode<E> new_node = new LLNode<E>(element);
		LLNode<E> curr_node = head;

		for(int i = 0; i < index; i++){
			curr_node = curr_node.next;
		}

		LLNode<E> succ = curr_node.next;
		curr_node.next = new_node;
		new_node.prev = curr_node;
		new_node.next = succ;
		succ.prev = new_node;
		size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException("Out of range. Invalid index");
		}

		LLNode<E> curr_node = head.next;
		for(int i = 0; i < index; i++){
			curr_node = curr_node.next;
		}

		LLNode<E> pred = curr_node.prev;
		LLNode<E> succ = curr_node.next;
		pred.next = succ;
		succ.prev = pred;
		size--;
		return curr_node.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if(element == null){
			throw new NullPointerException("Null Pointer Exception- Null Element aren't allowed");
		}
		else if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException("Out of range. Invalid index");
		}
		LLNode<E> curr_node = head.next;
		for(int i = 0; i < index; i++){
			curr_node = curr_node.next;
		}

		E prev_data = curr_node.data;
		curr_node.data = element;
		return prev_data;
	}
	
}


class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
