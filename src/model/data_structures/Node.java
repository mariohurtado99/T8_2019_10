package model.data_structures;

import java.util.Iterator;

public class Node<K extends Comparable<K>, V, W> 
{
	/**
	 * K idVertex
	 * V infoVertex
	 * W infoArk
	 */
	
	private K key;
	
	private V value;
	
	private HashTableLinearProbing<K, W> adj;
	
	public Node(K pKey, V pValue)
	{
		key = pKey;
		value = pValue;
		adj = new HashTableLinearProbing<K, W>();
	}
	
	public K key()
	{
		return key;
	}
	
	public V value()
	{
		return value;
	}
	
	public HashTableLinearProbing<K, W> getAdj()
	{
		return adj;
	}
	
	public void addEdge(K pKey, W pWeight)
	{
		adj.put(pKey, pWeight);
	}
	
	public void setEdge(K pKey, W pWeight)
	{
		adj.put(pKey, pWeight);
	}
	
	public void setValue(V pValue)
	{
		value = pValue;
	}
	
	public void setKey(K pKey)
	{
		key = pKey;
	}
	
	public W getEdge(K pId)
	{
		try
		{
			return adj.get(pId);
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	public Iterator<K> ajd()
	{
		return adj.iterator();
	}
	
	public void setAdj(HashTableLinearProbing<K, W> pAdj)
	{
		adj = pAdj;
	}
	
	public boolean contains(K pKey)
	{
		return adj.contains(pKey);
	}
	
	public boolean noAdj()
	{
		return adj.isEmpty();
	}
}
