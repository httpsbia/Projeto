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
