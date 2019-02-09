package edu.uprm.cse.datastructures.cardealer.util;

import java.util.Comparator;
import java.util.Iterator;


public class CircularSortedDoublyLinkedList<E>  implements SortedList<E> {
	//instance variables
	private DNode<E> header;
	private int size;
	private Comparator<E> cmp;

	public CircularSortedDoublyLinkedList( Comparator<E> comparator) {
		header = new DNode<E>(null,header,header);
		size=0;
		this.cmp=comparator;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * add new object to the sorted list
	 * @return   0 if object already on the list 1 if not
	 */
	@Override
	public boolean add(E obj) {
		// TODO Auto-generated method stub
		DNode<E> newnode = new DNode<E>(obj);
		if(isEmpty()) {
			header.setNext(newnode);
			header.setPrev(newnode);

			newnode.setNext(header);
			newnode.setPrev(header);
			size++;
			return true;
		}

		else {

			DNode<E>curr= header.getNext();
					
			for(int i =0; i<size; i++){
				if(cmp.compare(obj, curr.getElement())>0)
					curr=curr.getNext();
				else break;
			}
			newnode.setNext(curr);
			newnode.setPrev(curr.getPrev());
			(newnode.getPrev()).setNext(newnode);
			(curr).setPrev(newnode);
			size++;

			return true;	
		}


	}


	/**
	 * @return size of the current list
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	/**
	 * remove the instance of the object passed in the parameter
	 * @return 0 if object is not fount in the list 1 if object its found and deleted
	 */
	@Override
	public boolean remove(E obj) {
		// TODO Auto-generated method stub
		DNode<E> currfoward = header.getNext();
		DNode<E> currbackwards= header.getPrev();
		DNode<E> ntr;

		int backwardbound= size/2;	
		int fowardbound= size-backwardbound;
		for(int i=0; i<fowardbound;i++) {
			if(currfoward.getElement().equals(obj)) {
				ntr=currfoward;
				(ntr.getPrev()).setNext(ntr.getNext());
				(ntr.getNext()).setPrev(ntr.getPrev());
				ntr.cleanLinks();
				size--;	
				return true;
			}
			else if(currbackwards.getElement().equals(obj)) {
				ntr=currbackwards;
				(ntr.getPrev()).setNext(ntr.getNext());
				(ntr.getNext()).setNext(ntr.getPrev());
				ntr.cleanLinks();
				size--;	
				return true;
			}
			currfoward=currfoward.getNext();
			currbackwards=currbackwards.getPrev();
		}
		return false;
	}


	/**
	 * remove any instance located at the index given in the parameter
	 * @return 0 if index does not exist, 1 if index is valid and instance of object is deleted
	 */
	@Override
	public boolean remove(int index) {
	
		if( index<0 || index>=size) return false;
		DNode<E> curr =header.getNext();
		for(int i=0;i<index;i++) 
			curr=curr.getNext();

          (curr.getPrev()).setNext(curr.getNext());
          (curr.getNext()).setPrev(curr.getPrev());
          size--;
		return true;

	}
	
	//the return was assumed since the specifications where not clear, if there where specifications at all
	/**
	 * removes all instances of the object given as parameter in the current list
	 * @return the number of instances deleted
	 */
	@Override
	public int removeAll(E obj) {
		// TODO Auto-generated method stub
		if(isEmpty()) return 0;

		DNode<E> currfoward = header.getNext();
		DNode<E> currbackwards= header.getPrev();
		DNode<E> ntr;
		int counter =size;

		int backwardbound= size/2;	
		int fowardbound= size-backwardbound;// > than backwardbound for 1 if size is odd

		for(int i=0; i<fowardbound;i++) {
			
			if(cmp.compare(currfoward.getElement(), obj)==0) {
				ntr=currfoward;
				currfoward=currfoward.getNext();
				if(fowardbound!=i+1)
					currbackwards=currbackwards.getPrev();

				(ntr.getPrev()).setNext(ntr.getNext());
				(ntr.getNext()).setNext(ntr.getPrev());
				ntr.cleanLinks();
				size--;	


			}
			
			else if(cmp.compare(currbackwards.getElement(), obj)==0) {
				ntr=currbackwards;
				currfoward=currfoward.getNext();
				if(fowardbound!=i+1)
					currbackwards=currbackwards.getPrev();

				(ntr.getPrev()).setNext(ntr.getNext());
				(ntr.getNext()).setPrev(ntr.getPrev());
				ntr.cleanLinks();
				size--;	


			}
			else {


				currfoward=currfoward.getNext();
				if(fowardbound!=i+1)
					currbackwards=currbackwards.getPrev();
			}
		}
		//if odd size then check for the last object backwards


		return counter-=size;
	}
	/**
	 * @return the first object instance in the current list
	 */
	@Override
	public E first() {
		// TODO Auto-generated method stub
		if(isEmpty()) return null;
		return header.getNext().getElement();
	}
	/**
	 * @return the last object instance in the current list
	 */
	@Override
	public E last() {
		// TODO Auto-generated method stub
		if(isEmpty()) return null;

		return header.getPrev().getElement();
	}
	/**
	 * @return the element at index
	 */
	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		if( index<0 || index>size) return null;
		DNode<E> curr =header.getNext();
		for(int i=0;i<index;i++) 
			curr=curr.getNext();

		return curr.getElement();
	}
   /**
    * clear(reset) the current list
    */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		header.setNext(null);
		header.setPrev(null);
		size=0;

	}
    /**
     * return true if the list contains elements e, otherwise return false
     */
	@Override
	public boolean contains(E e) {
		//needs to compare e with all the elements on the list using comparator 
		for(int i=0; i<size;i++) {
			if(this.get(i).equals(e)) {
				return true;
			}
		}
		return false;
	}
  /**
   * return true if the list is empty, false otherwise
   */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size==0;
	}
	/**
	 * @return the index of the first object instance in the current list
	 */
	@Override
	public int firstIndex(E e) {
		if(isEmpty()) return -1;

		DNode<E> currfoward = header.getNext();

		int counter;

		for(counter=0; counter<size;counter++) {

			if(cmp.compare(currfoward.getElement(), e)==0) {
				return counter;
			}

			currfoward=currfoward.getNext();

		}
		return -1;
	}
	/**
	 * @return the index of the last object instance in the current list
	 * if the list does not contain e return -1
	 */
	@Override
	public int lastIndex(E e) {
		// TODO Auto-generated method stub
		if(isEmpty()) return -1;

		DNode<E> currbackward = header.getPrev();

		int counter;

		for(counter=0; counter<size;counter++) {
			//currbackward.getElement().equals(e)
			if(cmp.compare(currbackward.getElement(), e)==0) {
				return size-counter;
			}

			currbackward=currbackward.getPrev();

		}
		return -1;
	}

	/**
	 * Class to represent a node of the type used in doubly linked lists.
	 * @param <E>
	 */
	private static class DNode<E> implements Node<E> {
		private E element; 
		private DNode<E> prev, next; 

		// Constructors
		public DNode() {
			element=null;
		}

		public DNode(E e) { 
			element = e; 
		}

		public DNode(E e, DNode<E> p, DNode<E> n) { 
			prev = p; 
			next = n; 
		}

		// Methods
		public DNode<E> getPrev() {
			return prev;
		}
		public void setPrev(DNode<E> prev) {
			this.prev = prev;
		}
		public DNode<E> getNext() {
			return next;
		}
		public void setNext(DNode<E> next) {
			this.next = next;
		}
		public E getElement() {
			return element; 
		}

		public void setElement(E data) {
			element = data; 
		} 

		/**
		 * Just set references prev and next to null. Disconnect the node
		 * from the linked list.... 
		 */
		public void cleanLinks() { 
			prev = next = null; 
		}

	}

	
    /**
     * 
     * @param array object reference where the list  values are going to be stored
     * @return the full array 
     */
	public E[] toArray(E[] array) {
		// TODO Auto-generated method stub
		DNode<E> curr= header.getNext();
		int i=0;
		while(curr!=header) {
			array[i]= curr.getElement();
			curr=curr.getNext();
			i++;
		}
		return  array;
	}
}
