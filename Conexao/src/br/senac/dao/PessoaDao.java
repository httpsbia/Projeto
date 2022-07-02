package br.senac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.senac.model.Pessoa;

public class PessoaDao {
	
		public void create(Pessoa pessoa) throws SQLException {
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement preparedStatement = null;
			
			try {
				String query = "INSERT INTO Pessoas (nome, anoNascimento) VALUES (?,?);";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, pessoa.getNome());
				preparedStatement.setInt(2, pessoa.getAnoNascimento());
				preparedStatement.executeUpdate();
			} catch (SQLException ex) {
				throw new SQLException(ex);
			}
		}
	
}