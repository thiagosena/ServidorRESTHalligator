package br.com.droid.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.droid.factory.ConnectionFactory;
import br.com.droid.model.Usuario;

public class UsuarioDAO extends ConnectionFactory {

	public int verificaCadastrado(String email) {

		int id = 0;
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement stmt = null;
		conn = getConnection();
		try {
			stmt = conn
					.prepareStatement("SELECT ID FROM USUARIO WHERE EMAIL = ?");
			stmt.setString(1, email);
			resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn, stmt, resultSet);
		}
		return id;
	}

	public int inserir(Usuario usuario) {

		Connection conn = null;
		conn = getConnection();
		int sucesso = 0;
		int prodsCadastrados = verificaCadastrado(usuario.getEmail());

		if (prodsCadastrados == 0) {
			PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement("INSERT INTO "
						+ "USUARIO (NOME, SENHA, EMAIL) VALUES(?,?,?)");

				stmt.setString(1, usuario.getNome());
				stmt.setString(2, usuario.getSenha());
				stmt.setString(3, usuario.getEmail());
				sucesso = stmt.executeUpdate();

				if (sucesso > 0) {
					System.out.println("USUARIO INSERIDO!");
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("ERRO AO INSERIR USUARIO!");
			} finally {
				closeConnection(conn, stmt);
			}
		} else {
			System.out.println("ERRO: USUARIO JA CADASTRADO");
			closeConnection(conn);
		}
		return sucesso;
	}

	public int alterar(Usuario usuario) {

		Connection conn = null;
		conn = getConnection();
		PreparedStatement stmt = null;
		int sucesso = 0;
		try {
			stmt = conn
					.prepareStatement("UPDATE usuario SET nome = ?, email = ?, senha = ?, pontuacao = ? WHERE id = ?");

			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getSenha());
			stmt.setString(4, usuario.getPontuacao());
			stmt.setInt(5, usuario.getId());
			sucesso = stmt.executeUpdate();
			
			if (sucesso > 0) {
				System.out.println("USUARIO ALTERADO!");
			} else {
				System.out.println("USUARIO NAO EXISTE!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERRO AO ALTERAR USUARIO!");
		} finally {
			closeConnection(conn, stmt);
		}
		return sucesso;
	}

	public int deletar(int id) {

		Connection conn = null;
		conn = getConnection();
		int excluidos = 0;
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement("DELETE FROM USUARIO WHERE ID = ?");
			stmt.setInt(1, id);
			excluidos = stmt.executeUpdate();
			
			if (excluidos > 0) {
				System.out.println("USUARIO REMOVIDO!");
			} else {
				System.out.println("USUARIO NAO EXISTE!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERRO AO DELETAR USUARIO!");
		} finally {
			closeConnection(conn, stmt);
		}
		return excluidos;
	}

	public Usuario buscar(int id) {

		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement stmt = null;
		conn = getConnection();
		Usuario usuario = null;
		try {
			stmt = conn.prepareStatement("SELECT * FROM USUARIO WHERE ID = ?");
			stmt.setInt(1, id);
			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				usuario = new Usuario();
				usuario.setId(resultSet.getInt("id"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setSenha(resultSet.getString("senha"));
				usuario.setEmail(resultSet.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn, stmt, resultSet);
		}
		return usuario;
	}

	public ArrayList<Usuario> buscarTodos() {

		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement stmt = null;
		conn = getConnection();
		ArrayList<Usuario> listaUsuarios = null;

		try {

			stmt = conn.prepareStatement("SELECT * FROM USUARIO ORDER BY ID");
			resultSet = stmt.executeQuery();
			listaUsuarios = new ArrayList<Usuario>();

			while (resultSet.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(resultSet.getInt("id"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setSenha(resultSet.getString("senha"));
				usuario.setEmail(resultSet.getString("email"));
				usuario.setPontuacao(resultSet.getString("pontuacao"));
				listaUsuarios.add(usuario);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			listaUsuarios = null;
		} finally {
			closeConnection(conn, stmt, resultSet);
		}
		return listaUsuarios;
	}
}
