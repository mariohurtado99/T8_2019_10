package model.data_structures;

public interface IStack<T extends Comparable<T>> extends Iterable<T>
{
	public class Node<T extends Comparable<T>>
	{
		Node<T> next;
		T element;
		
		public Node(T pElement)
		{
			next = null;
			element = pElement;
		}
		
		public Node<T> getNext()
		{
			return next;
		}
		
		public T getElement()
		{
			return element;
		}
		
		public void setNext(Node<T> pNext)
		{
			next = pNext;
		}
		
		public void setElement(T pElement)
		{
			element = pElement;
		}
	}
	public Node<T> getHead();
	/**
	 * Retorna true si la Pila esta vacia
	 * @return true si la Pila esta vacia, false de lo contrario
	 */
	public boolean isEmpty();
	
	/**
	 * Retorna el numero de elementos contenidos
	 * @return el numero de elemntos contenidos
	 */
	public int size();
	
	/**
	 * Inserta un nuevo elemento en la Pila
	 * @param t el nuevo elemento que se va ha agregar
	 */
	public void push(T t);
	
	/**
	 * Quita y retorna el elemento agregado más recientemente
	 * @return el elemento agregado más recientemente
	 */
	public T pop();	
	
}
