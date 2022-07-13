package it.polito.tdp.genes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.genes.model.Adiacenze;
import it.polito.tdp.genes.model.Genes;
import it.polito.tdp.genes.model.Interactions;


public class GenesDao {
	
	public List<Genes> getAllGenes(){
		String sql = "SELECT DISTINCT GeneID, Essential, Chromosome FROM Genes";
		List<Genes> result = new ArrayList<Genes>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Genes genes = new Genes(res.getString("GeneID"), 
						res.getString("Essential"), 
						res.getInt("Chromosome"));
				result.add(genes);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		}
	}
	
	public List<Integer> getChromosome(){
		String sql = "SELECT DISTINCT chromosome "
				+ "FROM genes "
				+ "WHERE chromosome<>0 ";
		List<Integer> result = new ArrayList<Integer>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				result.add(res.getInt("chromosome"));
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		}
	}
	
	
	public List<Adiacenze> getArchi(){
		String sql = "SELECT DISTINCT G1.chromosome as c1, G2.chromosome as c2, SUM(DISTINCT I.Expression_Corr) as peso "
				+ "FROM genes G1, genes G2 ,interactions I "
				+ "WHERE G1.chromosome<>0 "
				+ "AND G2.chromosome<>0 "
				+ "AND G1.chromosome<>G2.chromosome "
				+ "AND G1.GeneID = I.GeneID1 "
				+ "AND G2.GeneID = I.GeneID2 "
				+ "GROUP BY G1.chromosome, G2.chromosome "
				+ "Order BY G1.chromosome, G2.chromosome ";
		List<Adiacenze> result = new ArrayList<Adiacenze>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				int v1 = res.getInt("c1");
				int v2 = res.getInt("c2");
				double peso = res.getDouble("peso");
				Adiacenze a = new Adiacenze(v1,v2,peso);
				result.add(a);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		}
	}

	public double getMaxPeso(){
		String sql = "SELECT MAX(peso) as maxPeso "
				+ "FROM(SELECT DISTINCT G1.chromosome as c1, G2.chromosome as c2, SUM(DISTINCT I.Expression_Corr) as peso "
				+ "FROM genes G1, genes G2 ,interactions I "
				+ "WHERE G1.chromosome<>0  "
				+ "AND G2.chromosome<>0  "
				+ "AND G1.chromosome<>G2.chromosome "
				+ "AND G1.GeneID = I.GeneID1 "
				+ "AND G2.GeneID = I.GeneID2 "
				+ "GROUP BY G1.chromosome, G2.chromosome "
				+ "Order BY G1.chromosome, G2.chromosome) as Archi "
				+ "";
		
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			res.next();
			
			double maxPeso =res.getDouble("maxPeso");
				
			res.close();
			st.close();
			conn.close();
			return maxPeso;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		}
	}

	public double getMinPeso(){
		String sql = "SELECT MIN(peso) as minPeso "
				+ "FROM(SELECT DISTINCT G1.chromosome as c1, G2.chromosome as c2, SUM(DISTINCT I.Expression_Corr) as peso "
				+ "FROM genes G1, genes G2 ,interactions I "
				+ "WHERE G1.chromosome<>0  "
				+ "AND G2.chromosome<>0  "
				+ "AND G1.chromosome<>G2.chromosome "
				+ "AND G1.GeneID = I.GeneID1 "
				+ "AND G2.GeneID = I.GeneID2 "
				+ "GROUP BY G1.chromosome, G2.chromosome "
				+ "Order BY G1.chromosome, G2.chromosome) as Archi "
				+ "";
		
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			res.next();
			
			double minPeso =res.getDouble("minPeso");
				
			res.close();
			st.close();
			conn.close();
			return minPeso;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		}
	}

}
