package model.data_structures;

public class Queue<T extends Comparable<T>> implements IQueue<T> 
{
	
	public class Node<T extends Comparable<T>>
	{
		private Node<T> next;
		
		private T element;
		
		public Node(T pElement)
		{
			next = null;
			element = pElement;
		}
		
		public Node<T> getNext()
		{
			return next;
		}
		
		public void setNext(Node<T> pNode)
		{
			next = pNode;
		}
		
		public T getElement()
		{
			return element;
		}
		
		public void setElement(T pElement)
		{
			element = pElement;
		}
	}

	///////////////////////////////////////////
	//Attributes
	///////////////////////////////////////////
	private Node<T> head;
	
	private Node<T> tail;
	
	private int size;
	
	///////////////////////////////////////////
	//Constructors
	///////////////////////////////////////////
	public Queue()
	{
		head = tail = null;
		size = 0;
	}
	
	///////////////////////////////////////////
	//Methods
	///////////////////////////////////////////
	
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
	public void enqueue(T t)
	{
		Node<T> nuevo = new Node<T>(t);
		if(tail == null)
		{
			head = tail = nuevo;
			size++;
		}
		else
		{
			tail.setNext(nuevo);
			tail = nuevo;
			size++;
		}
	}

	@Override
	public T dequeue()
	{
		Node<T> eliminado = head;
		head = head.getNext();
		if(head == null)
		{
			tail = null;
		}
		size--;
		
		return eliminado.getElement();
	}
}
