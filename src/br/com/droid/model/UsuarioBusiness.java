package br.com.droid.model;

import java.util.ArrayList;
import br.com.droid.dao.UsuarioDAO;

public class UsuarioBusiness {

	public ArrayList<Usuario> buscarTodos() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.buscarTodos();
	}
	
	public String inserir(Usuario usuario) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if(usuarioDAO.inserir(usuario) > 0){
			return "Usuario inserido no banco com sucesso!";
		} else {
			return "Falha ao inserir o usuario no banco!";
		}
	}
	
	public String alterar(Usuario usuario){
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if(usuarioDAO.alterar(usuario) > 0){
			return "Usuario atualizado no banco com sucesso!";
		} else {
			return "Falha ao atualizar o usuario no banco!";
		}
	}
	
	public String deletar(int id) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if(usuarioDAO.deletar(id) > 0){
			return "Usuario removido no banco com sucesso!";
		} else {
			return "Usuario nao existe!";
		}
	}
	
	public Usuario buscar(int id) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.buscar(id);
	}
	
	public String inserirLista(ArrayList<Usuario> listaUsuarios) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		String retorno = "";
		for (int i = 0; i < listaUsuarios.size(); i++) {
			if(usuarioDAO.inserir(listaUsuarios.get(i)) < 1){
				retorno += "Erro ao inserir o usuario de EMAIL: "+ listaUsuarios.get(i).getEmail() +"\n";
			}
		}
		if(retorno.length() == 0){
			retorno = "Lista de usuarios inserida no banco com sucesso!";
		}
		return retorno;
	}
}
