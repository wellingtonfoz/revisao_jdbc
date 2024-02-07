package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Celular;

public class CelularDAO {
	private  Connection conexao;
	private  PreparedStatement statement;
	
	public void salvar(Celular celular) {
		try{
			ConexaoBD conexaoDB = new ConexaoBD();
			conexao = conexaoDB.getCon();
			conexao.setAutoCommit(false);

			//Preparando a consulta SQL
			String sql = "INSERT INTO celular values(0,?,?,?)";
			statement = conexao.prepareStatement(sql);
			//Mapeamento Relacional
			statement.setString(1, celular.getMarca());
			statement.setString(2, celular.getModelo());
			statement.setString(3, celular.getMemoria());
			//Execu��o do SQL
			statement.executeUpdate();

		}
		catch(Exception ex){
			try {
				conexao.rollback();
			} catch (SQLException e) {
				ex.printStackTrace();
			}
			ex.printStackTrace();
		}
		finally{
			try{
				conexao.setAutoCommit(true);
				statement.close();
				conexao.close();
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	
	
	public void alterar(Celular celular) {
		try{
			ConexaoBD conexaoDB = new ConexaoBD();
			conexao = conexaoDB.getCon();
			conexao.setAutoCommit(false);

			//Preparando a consulta SQL
			String sql = "UPDATE celular SET marca = ?, modelo = ?, memoria = ? WHERE id = ?";
			statement = conexao.prepareStatement(sql);
			//Mapeamento Relacional
			statement.setString(1, celular.getMarca());
			statement.setString(2, celular.getModelo());
			statement.setString(3, celular.getMemoria());
			statement.setInt(4, celular.getId());
			//Execu��o do SQL
			statement.executeUpdate();

		}
		catch(Exception ex){
			try {
				conexao.rollback();
			} catch (SQLException e) {
				ex.printStackTrace();
			}
			ex.printStackTrace();
		}
		finally{
			try{
				conexao.setAutoCommit(true);
				statement.close();
				conexao.close();
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	
	public void deletar(int id) {
		try{
			ConexaoBD conexaoDB = new ConexaoBD();
			conexao = conexaoDB.getCon();
			conexao.setAutoCommit(false);

			//Preparando a consulta SQL
			String sql = "DELETE FROM celular WHERE id = ?";
			statement = conexao.prepareStatement(sql);
			//Mapeamento Relacional
			statement.setInt(1, id);
			//Execu��o do SQL
			statement.executeUpdate();

		}
		catch(Exception ex){
			try {
				conexao.rollback();
			} catch (SQLException e) {
				ex.printStackTrace();
			}
			ex.printStackTrace();
		}
		finally{
			try{
				conexao.setAutoCommit(true);
				statement.close();
				conexao.close();
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	
	public List<Celular> listar(){
		try{
			//Inst�ncia de conex�o com o BD
			ConexaoBD conexaoDB = new ConexaoBD();
			conexao = conexaoDB.getCon();
			conexao.setAutoCommit(false);

			//Prepara��o da Consulta SQL
			String sql = "SELECT id,marca, modelo, memoria from celular";
			statement = conexao.prepareStatement(sql);
			//Execu��o da Consulta SQL
			ResultSet result = statement.executeQuery();

			List<Celular> listaCelulares = new ArrayList<Celular>();
			while(result.next()){
				Celular celular = new Celular();
				celular.setId(result.getInt(1));
				celular.setMarca(result.getString(2));
				celular.setModelo(result.getString(3));
				celular.setMemoria(result.getString(4));
				listaCelulares.add(celular);
			}
			return listaCelulares;
		}
		catch(Exception ex){
			try {
				conexao.rollback();
			} catch (SQLException e) {
				ex.printStackTrace();
			}
			ex.printStackTrace();
			return null;
		}
		finally{
			try{
				conexao.setAutoCommit(true);
				statement.close();
				conexao.close();
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	

}
