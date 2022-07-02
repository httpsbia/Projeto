//Pessoa Consulta

package br.senac.application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import br.senac.model.Pessoa;
import br.senac.dao.PessoaDao;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;


public class PessoaConsulta {

	private JFrame frame;
	private JTextField txtNome;
	private JTable tblPessoa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PessoaConsulta window = new PessoaConsulta();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PessoaConsulta() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(10, 27, 32, 14);
		panel.add(lblNewLabel);
		
		txtNome = new JTextField();
		txtNome.setBounds(50, 24, 254, 20);
		panel.add(txtNome);
		txtNome.setColumns(10);
		
		JButton bntConsulta = new JButton("Ok");
		bntConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PessoaDao pessoaDao = new PessoaDao();
				List<Pessoa> pessoas = new ArrayList<Pessoa>();
				String nome = txtNome.getText();
				
				try {
					pessoas = pessoaDao.getPessoaByName(nome);
					
					
					DefaultTableModel modelo = (DefaultTableModel)tblPessoa.getModel();
					modelo.setRowCount(0);
					modelo.addColumn("Código");
					modelo.addColumn("Nome");
					modelo.addColumn("Ano de Nascimento");
					
					for (Pessoa p : pessoas) {
						modelo.addRow(new Object[] {
							p.getId(),
							p.getNome(),
							p.getAnoNascimento()
							
						});
					}
	
				}
				
				catch(SQLException ex) {
					JOptionPane.showMessageDialog(null, "Erro:" + ex.getMessage());
				}
			}
		});
		bntConsulta.setBounds(314, 23, 89, 23);
		panel.add(bntConsulta);
		
		tblPessoa = new JTable();
		tblPessoa.setBounds(10, 73, 414, 177);
		panel.add(tblPessoa);
	}
}


//Pessoa Cadastro

package br.senac.application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.senac.dao.PessoaDao;
import br.senac.model.Pessoa;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class PessoaCadastro {

	private JFrame frame;
	private JTextField txtNome;
	private JLabel lblAnoDeNascimento;
	private JTextField txtAnoNascimento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PessoaCadastro window = new PessoaCadastro();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PessoaCadastro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setBounds(58, 34, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		txtNome = new JTextField();
		txtNome.setBounds(58, 51, 310, 20);
		frame.getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		lblAnoDeNascimento = new JLabel("Ano de Nascimento:");
		lblAnoDeNascimento.setBounds(58, 82, 96, 14);
		frame.getContentPane().add(lblAnoDeNascimento);
		
		txtAnoNascimento = new JTextField();
		txtAnoNascimento.setColumns(10);
		txtAnoNascimento.setBounds(58, 103, 310, 20);
		frame.getContentPane().add(txtAnoNascimento);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Pessoa pessoa = new Pessoa();
					pessoa.setNome(txtNome.getText());
					pessoa.setAnoNascimento(Integer.valueOf(txtAnoNascimento.getText()));
					
					PessoaDao pessoaDao = new PessoaDao();
					pessoaDao.create(pessoa);
					
					JOptionPane.showMessageDialog(null, "Cadastrado Mermão!!");
				}
				catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Erro:" + ex.getMessage(), "Pessoa", JOptionPane.ERROR_MESSAGE);
				
				}
			}
		});
		btnCadastrar.setBounds(279, 227, 89, 23);
		frame.getContentPane().add(btnCadastrar);
	}
}


//Connection Factory

package br.senac.dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConnectionFactory {
	private static final String DRIVER = "com.mysql.jdbc.driver";
	private static final String URL = "jdbc:mysql://localhost:3306/bank";
	private static final String	USER = "usuario_bank";
	private static final String	PASS = "";
	
	public static Connection getConnection() {
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USER, PASS);
	} catch (ClassNotFoundException | SQLException ex) {		
		
		throw new RuntimeException(ex);
		
		}
	}
	
	public static void closeConnection(Connection connection) {
		try {
			if(connection != null) {
				connection.close();
			}
			
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
}

//Pessoa Dao

package br.senac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.senac.model.Pessoa;
import java.sql.ResultSet;


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
		
		public List<Pessoa> getPessoaByName(String nome) throws SQLException{
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement preparedStatement = null;
			
			try {
				String query = "SELECT id, nome, anoNascimento FROM Pessoa WHERE nome LIKE ?;";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1,"%" + nome + "%");
				
				ResultSet rs = preparedStatement.executeQuery();
				
				List<Pessoa> pessoa = new ArrayList<Pessoa>();
				
				while(rs.next()) {
					Pessoa Pessoa = new Pessoa();
					
					Pessoa.setId(rs.getInt("id"));
					Pessoa.setNome(rs.getString("nome"));
					Pessoa.setAnoNascimento(rs.getInt("anoNascimento"));
					
					Pessoa.add(pessoa);
					}
			return pessoa;
		}
			catch (SQLException ex) {
				throw new SQLException (ex);
			}
}
}

//Pessoa

package br.senac.model;

import java.util.List;

public class Pessoa {

		private int id;
		private String nome;
		private int anoNascimento;
	
		
		public Pessoa() {
			
		}
		
		public Pessoa(int id, String nome, int anoNascimento) {
			this.id = id;
			this.nome = nome;
			this.anoNascimento = anoNascimento;
			
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public int getAnoNascimento() {
			return anoNascimento;
		}

		public void setAnoNascimento(int anoNascimento) {
			this.anoNascimento = anoNascimento;
		}

		public void add(List<Pessoa> pessoa) {
			// TODO Auto-generated method stub
			
		}
		
		
		
		
}

// tela teste

package br.edu.senacsp.presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaTeste {

	private JFrame frame;
	private JTextField txtTemperatura;
	private JButton btnConverte;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaTeste window = new TelaTeste();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaTeste() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Temperatura");
		lblNewLabel.setBounds(10, 11, 69, 14);
		frame.getContentPane().add(lblNewLabel);
		
		txtTemperatura = new JTextField();
		txtTemperatura.setBounds(86, 8, 86, 20);
		frame.getContentPane().add(txtTemperatura);
		txtTemperatura.setColumns(10);
		
		btnConverte = new JButton("Converter");
		btnConverte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = txtTemperatura.getText();
				
				JOptionPane.showMessageDialog(null, nome, "Primeira Tela", JOptionPane.ERROR_MESSAGE);
				
			}
		});
		btnConverte.setBounds(119, 39, 97, 23);
		frame.getContentPane().add(btnConverte);
	}
}
