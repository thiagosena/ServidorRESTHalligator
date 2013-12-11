package br.com.droid;

import java.util.ArrayList;

import br.com.droid.model.Usuario;

public class Banco {

	private static Banco instance;
	private ArrayList<Usuario> listaUsuarios;
	private int idUsuario;
	
	private Banco() {
		idUsuario = 1;
		listaUsuarios = new ArrayList<Usuario>();
		Usuario usuario = new Usuario();
		usuario.setEmail("teste@teste.com");
		usuario.setSenha("123456");
		inserir(usuario);
	}
	
	public static Banco getBancoInstance(){
		if(instance==null)
			instance = new Banco();
		return instance;
	}
	
	public ArrayList<Usuario> getListaUsuarios(){
		return listaUsuarios;
	}
	
	public String inserir(Usuario usuario){
		usuario.setId(idUsuario++);
		listaUsuarios.add(usuario);
		return "Usuario inserido no banco com sucesso!";
	}
	
	public Usuario buscar(int id){
		Usuario usuario = null;
		for (int i = 0; i < listaUsuarios.size(); i++) {
			if (listaUsuarios.get(i).getId() == id){
				usuario = new Usuario();
				usuario = listaUsuarios.get(i);
				break;
			}
		}
		return usuario;
	}
	
	private int getPosition(int id) {
		int pos = -1;
		for (int i = 0; i < listaUsuarios.size(); i++) {
			if (listaUsuarios.get(i).getId() == id){
				pos = i;
			}
		}
		return pos;
	}
	
	public String delete(int id){
		Usuario usuario = buscar(id);
		int pos = getPosition(id);
		if(usuario == null || pos < 0){
			return "Usuario nao encontrado!";
		} else {
			listaUsuarios.remove(pos);
			return "Usuario removido com sucesso!";
		}
	}
	
	public String inserirLista(ArrayList<Usuario> listaUsuario) {
		for (int i = 0; i < listaUsuario.size(); i++) {
			inserir(listaUsuario.get(i));
		}
		return "Lista de usuarios inserida no banco com sucesso!";
	}
}
