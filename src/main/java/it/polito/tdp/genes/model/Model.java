package it.polito.tdp.genes.model;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {

	private Graph<Integer,DefaultWeightedEdge> grafo;
	private GenesDao dao;

	public Model() {
		this.grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		dao = new GenesDao();
	}

	public void creaGrafo()
	{
		Graphs.addAllVertices(this.grafo, this.dao.getChromosome());
		
		for(Adiacenze a: this.dao.getArchi())
		{
			Graphs.addEdgeWithVertices(this.grafo, a.getV1(), a.getV2(), a.getPeso());
		}
	}
	
	public int getNVertici()
	{
		return this.grafo.vertexSet().size();
	}
	public int getNArchi()
	{
		return this.grafo.edgeSet().size();
	}
	public double getMinPeso(){
		return this.dao.getMinPeso();
	}
	public double getMaxPeso(){
		return this.dao.getMaxPeso();
	}
	
	public int GetArchiMaggiori(double s)
	{
		int i = 0;
		int j = 0;
		for(Integer it :  this.dao.getChromosome())
		{
			for(DefaultWeightedEdge dwe: this.grafo.incomingEdgesOf(it))
			{
				if(this.grafo.getEdgeWeight(dwe)>s)
				{
					i++;
				}
				if(this.grafo.getEdgeWeight(dwe)>s)
				{
					i++;
				}
			}
		}
		return i/2;
	}
	
	public int GetArchiMinori(double s)
	{
		int j = 0;
		for(Integer it :  this.dao.getChromosome())
		{
			for(DefaultWeightedEdge dwe: this.grafo.incomingEdgesOf(it))
			{
				if(this.grafo.getEdgeWeight(dwe)<s)
				{
					j++;
				}
				if(this.grafo.getEdgeWeight(dwe)<s)
				{
					j++;
				}
			}
		}
		return j/2;
	}
	
}