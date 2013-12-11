package br.com.droid.resources;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.droid.exception.NoContentException;
import br.com.droid.model.Usuario;
import br.com.droid.model.UsuarioBusiness;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

@Path("/usuario")
public class UsuarioResource {
	
	@GET
	@Path("/buscarTodos")
	@Produces("application/json")
	public ArrayList<Usuario> selTodos(){
		return new UsuarioBusiness().buscarTodos();
	}

	@GET
	@Path("/buscarTodosGSON")
	@Produces("application/json")
	public String selTodosGSON(){
		return new Gson().toJson(new UsuarioBusiness().buscarTodos());
	}
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Usuario getUsuario(@PathParam("id") int id){
		Usuario usuario = new UsuarioBusiness().buscar(id);
		
		if(usuario == null)
			throw new NoContentException("Usuario nao encontrado!");
		
		return usuario;
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Produces("application/json")
	public String deleteUsuario(@PathParam("id") int id){
		return new UsuarioBusiness().deletar(id);
	}
	
	@POST
	@Path("/inserir")
	@Produces("application/json")
	@Consumes("application/json")
	public String inserirUsuario(Usuario usuario) {
		return new UsuarioBusiness().inserir(usuario);
	}
	
	@POST
	@Path("/alterar")
	@Produces("application/json")
	@Consumes("application/json")
	public String alterarUsuario(Usuario usuario){
		return new UsuarioBusiness().alterar(usuario);
	}
	
	@POST
	@Path("/inserirLista")
	@Produces("application/json")
	@Consumes("application/json")
	public String inserirLista(String listaUsuariosJson) {
		
		Gson gson = new Gson();
		ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
		JsonParser parser = new JsonParser();
	    JsonArray array = parser.parse(listaUsuariosJson).getAsJsonArray();
	    
	    for (int i = 0; i < array.size(); i++) {
	    	listaUsuarios.add(gson.fromJson(array.get(i), Usuario.class));
		}
	    
		return new UsuarioBusiness().inserirLista(listaUsuarios);

	}
}
