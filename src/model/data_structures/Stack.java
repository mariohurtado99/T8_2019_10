package model.data_structures;

import java.util.Iterator;

public class Stack<T extends Comparable<T>> implements IStack<T>{
	
	private Node<T> head;
	
	private int size;
	
	public Stack()
	{
		head = null;
		size = 0;
	}
	
	@Override
	public Node<T> getHead()
	{
		return head;
	}
	
	@Override
	public Iterator<T> iterator()
	{
		return new Iterator<T>()
		{
			Node<T> actual = head;

			@Override
			public boolean hasNext()
			{
				return actual != null;
			}

			@Override
			public T next() 
			{
				if(hasNext())
				{
					T data = actual.getElement();
					actual = actual.getNext();
					return data;
				}
				return null;
			}
		};
	}
	
	@Override
	public boolean isEmpty() 
	{
		return size == 0;
	}

	@Override
	public int size() 
	{
		return size;
	}

	@Override
	public void push(T t) 
	{
		try
		{
			Node<T> nuevo = new Node<T>(t);
			if(head != null)
			{
				nuevo.setNext(head);
			}
			head = nuevo;
			size++;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	@Override
	public T pop() 
	{
		if(head != null)
		{
			T actual = head.getElement();
			head = head.getNext();
			size--;
			return actual;
		}
		return null;
	}
}
