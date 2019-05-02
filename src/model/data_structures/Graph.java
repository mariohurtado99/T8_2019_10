package model.data_structures;

import java.util.Iterator;

public class Graph<K extends Comparable<K>, V, W>
{
	/**
	 * K idVertex
	 * V infoVertex
	 * W infoArk
	 */
	
	/**
	 * Number of edges of the graph
	 */
	private int e;
	
	/**
	 * Tabla de hash con los vertices del grafo
	 */
	private HashTableLinearProbing<K, Node<K, V, W>> nodes;
	
	private Queue<K> preOrderDfs;
	
	private Queue<K> postOrderDfs;
	
	private Stack<K> reversePostOrderDfs;
	
	//---------------------------
	
	/**
	 * Inicializa un nuevo grafo vacio
	 */
	public Graph()
	{
		e = 0;
		nodes = new HashTableLinearProbing<K, Node<K,V,W>>();
	}

	/**
	 * @return Número de vértices
	 */
	public int V()
	{
		return nodes.size();
	}
	
	public boolean isEmpty()
	{
		return nodes.isEmpty();
	}

	/**
	 * @return Número de arcos.
	 * Cada arco No dirigido debe contarse una única vez.
	 */
	public int E()
	{
		return e;
	}

	/**
	 * Adiciona un vértice con un Id único. El vértice tiene la información InfoVertex
	 * @param idVertex
	 * @param infoVertex
	 */
	public void addVertex( K idVertex, V infoVertex)
	{
		Node<K, V, W> nuevo = new Node<K, V, W>(idVertex, infoVertex);
		nodes.put(idVertex, nuevo);
	}

	/**
	 * Adiciona el arco No dirigido entre el vertice IdVertexIni y el vertice
	 * IdVertexFin. El arco tiene la información infoArc.
	 * @param idVertexIni
	 * @param idVertexFin
	 * @param infoArc
	 */
	public void addEdge(K idVertexIni, K idVertexFin, W infoArc)
	{
		try
		{
			nodes.get(idVertexIni).addEdge(idVertexFin, infoArc);
			nodes.get(idVertexFin).addEdge(idVertexIni, infoArc);
			e++;
		}
		catch(Exception e)
		{
			return;
		}
	}
	
	/**
	 * 
	 * @param pKey
	 * @return
	 */
	public Node<K, V, W> getVertex(K pKey)
	{
		return nodes.get(pKey);
	}

	/**
	 * Obtener la información de un vértice
	 * @param idVertex
	 * @return la información de un vértice
	 */
	public V getInfoVertex(K idVertex)
	{
		return nodes.get(idVertex).value();
	}

	/**
	 * Modificar la información del vértice idVertex
	 * @param idVertex
	 * @param infoVertex
	 */
	public void setInfoVertex(K idVertex, V infoVertex)
	{
		nodes.get(idVertex).setValue(infoVertex);
	}

	/**
	 * Obtener la información de un arco.
	 * @param idVertexIni
	 * @param idVertexFin
	 * @return
	 */
	public W getInfoArc(K idVertexIni, K idVertexFin)
	{
		try
		{
			return nodes.get(idVertexIni).getEdge(idVertexFin);
		}
		catch(Exception e)
		{
			return null;
		}
	}

	/**
	 * Modificar la información del arco entre los vértices idVertexIni e idVertexFin
	 * @param idVertexIni
	 * @param idVertexFin
	 * @param infoArc
	 */
	public void setInfoArc(K idVertexIni, K idVertexFin, W infoArc)
	{
		try
		{
			nodes.get(idVertexIni).setEdge(idVertexFin, infoArc);
			nodes.get(idVertexFin).setEdge(idVertexIni, infoArc);
		}
		catch(Exception e)
		{
			return;
		}
	}
	
	/**
	 * Retorna los vertices del grafo
	 * @return una tabla de hash con los vertices del grafo
	 */
	public HashTableLinearProbing<K, Node<K, V, W>> getAdj()
	{
		return nodes;
	}

	/**
	 * Retorna los identificadores de los vértices adyacentes a idVertex
	 * @param idVertex
	 * @return
	 */
	public Iterable<K> adj(K idVertex)
	{
		Iterator<K> iter = nodes.get(idVertex).ajd();
		Stack<K> pila = new Stack<K>();
		while(iter.hasNext())
		{
			pila.push(iter.next());
		}
		return pila;
	}
	
	private void initializeOrders()
	{
		postOrderDfs = new Queue<K>();
		preOrderDfs = new Queue<K>();
		reversePostOrderDfs = new Stack<K>();
	}
	
	public int vertexInDegree(K pKey)
	{
		int degree = 0;
		for(K k : nodes)
		{
			Node<K, V, W> vertex = nodes.get(k);
			
			W arc = vertex.getEdge(pKey);
			if(arc != null)
			{
				degree++;
			}
		}
		return degree;
	}
	
	public int vertexOutDegree(K pKey)
	{
		if(nodes.get(pKey) != null)
		{
			return nodes.get(pKey).getAdj().size();
		}
		else
		{
			return 0;
		}
	}
	
	public double vertexDensity(K pKey)
	{
		return (vertexInDegree(pKey) + vertexOutDegree(pKey))/V();
	}
	
	public void dfs(K pKey)
	{
		initializeOrders();
		HashTableLinearProbing<K, Boolean> marked = new HashTableLinearProbing<K, Boolean>();
		marking(marked);
		try
		{
			for(K k : nodes)
			{
				if(!marked.get(k))
				{
					dfs(marked, k);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void dfs(HashTableLinearProbing<K, Boolean> marked, K pKey)throws Exception
	{
		marked.put(pKey, true);
		preOrderDfs.enqueue(pKey);
		
		for(K k : nodes.get(pKey).getAdj())
		{
			if(!marked.get(k))
			{
				dfs(marked, k);
			}
		}
		postOrderDfs.enqueue(pKey);
		reversePostOrderDfs.push(pKey);
	}
	
	/**
	 * marca todos los nodos del grafo como no visitados antes de empezar el recorrido
	 * @param marked tabla con los nodos no marcados.
	 */
	public void marking(HashTableLinearProbing<K, Boolean> marked)
	{
		for(K k : nodes)
		{
			try
			{
				marked.put(k, false);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Verifica que la llave que entra por par�metro exista en el grafo
	 * @param pKey llave a verficar
	 * @return true or false si la llave existe.
	 */
	public boolean contains(K pKey)
	{
		return nodes.contains(pKey);
	}
	
	/**
	 * 
	 * @return
	 */
	public Iterator<K> keys()
	{
		return nodes.iterator();
	}
}
