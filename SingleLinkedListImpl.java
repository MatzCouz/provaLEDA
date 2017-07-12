   package adt.linkedList;

import java.util.Comparator;

public class SingleLinkedListImpl<T> implements LinkedList<T> {
   
   	protected SingleLinkedListNode<T> head;
   
   	public SingleLinkedListImpl() {
   		this.head = new SingleLinkedListNode<T>();
   	}

	@Override
  	public boolean isEmpty() {
  		return this.head.isNIL();
  	}
  
  	@Override
  	public int size() {
  		int sum = 0;
  		if (isEmpty()) {
  			return sum;
  		} else {
  			SingleLinkedListNode<T> aux = this.getHead();
  
  			while (!aux.isNIL() && aux.getNext() != null) {
  				sum++;
  				aux = aux.getNext();
  			}
  			return sum;
  		}
  	}
  
  	@Override
  	public T search(T element) {
  		T result = null;
  		if (element != null) {
  			SingleLinkedListNode<T> node = this.getHead();
  
  			while (!node.isNIL() && node.getNext() != null && !node.getData().equals(element)) {
  				node = node.getNext();
  			}
  
  			if (!node.isNIL() && node.getData().equals(element)) {
  				result = node.getData();
  			}
  		}
  		return result;
  	}
  
  	@Override
  	public void insert(T element) {
  		if (element != null) {
  			SingleLinkedListNode<T> node = this.getHead();
  
  			while (!node.isNIL() && node.getNext() != null) {
  				node = node.getNext();
  			}
  
  			node.setData(element);
  			node.setNext(new SingleLinkedListNode<T>());
  		}
  
  	}
  
  	@Override
  	public void remove(T element) {
  		if (!isEmpty() && element != null) {
  			if (this.getHead().getData().equals(element)) {
  				if (this.getHead().getNext() == null) {
  					this.setHead(new SingleLinkedListNode<T>());
  				} else {
  					this.setHead(this.getHead().getNext());
  				}
  			} else {
  				SingleLinkedListNode<T> node = this.getHead();
  				SingleLinkedListNode<T> previous = new SingleLinkedListNode<>(null, node);
  
  				while (!node.isNIL() && node.getNext() != null && !node.getData().equals(element)) {
  					previous = node;
  					node = node.getNext();
  				}
  
  				if (!node.isNIL() && node.getData().equals(element)) {
  					previous.setNext(node.getNext());
  				}
  			}
  		}
  	}
  
  	@SuppressWarnings("unchecked")
  	@Override
  	public T[] toArray() {
  		int size = this.size();
  		T[] array = (T[]) new Object[size];
  		int index = 0;
  		SingleLinkedListNode<T> node = this.getHead();
  
  		while (!node.isNIL() && node.getNext() != null) {
  			array[index] = node.getData();
  			node = node.getNext();
 			index++;
 		}
 
 		return array;
 	}
 
 	public SingleLinkedListNode<T> getHead() {
 		return head;
 	}
 
 	public void setHead(SingleLinkedListNode<T> head) {
 		this.head = head;
 	}
 }