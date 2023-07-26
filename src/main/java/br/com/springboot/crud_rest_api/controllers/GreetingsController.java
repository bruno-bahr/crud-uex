package br.com.springboot.crud_rest_api.controllers;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.crud_rest_api.model.Usuario;
import br.com.springboot.crud_rest_api.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
    @RequestMapping(value = "/welcome/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
    	
		
		  Usuario usuario = new Usuario(); usuario.setNome(name);
		  
		  usuarioRepository.save(usuario);
		 
    	
        return "Hello " + name + "!";
    }
    
    @RequestMapping(value = "/home/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String homeText( ) {
    	String msg = "Project Home";
    	return "Welcome to the " + msg;
    }
    
    @GetMapping(value = "listatodos")
    @ResponseBody 
    public ResponseEntity<List<Usuario>> listaUsuario(){
    	
    	List<Usuario> usuarios = usuarioRepository.findAll();
    	
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); 
    }
    
    @PostMapping(value = "salvar")
    @ResponseBody
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
    	
    	Usuario user =  usuarioRepository.save(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    	
    }
    
    @PutMapping(value = "atualizar")
    @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){
    	
    	if(usuario.getId() == null ) {
    		return new ResponseEntity<String>("Favor informar um id antes de salvar.", HttpStatus.OK);
    	}
    	
    	Usuario user =  usuarioRepository.saveAndFlush(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    	
    }
    
    @DeleteMapping(value = "delete")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long iduser){
    	
    	usuarioRepository.deleteById(iduser);;
    	
    	return new ResponseEntity<String>("<Usuário deletado com sucesso>", HttpStatus.OK);
    	
    }
    
    @GetMapping(value = "userbyid")
    @ResponseBody
    public ResponseEntity<?> userbyid(@RequestParam(name = "iduser") Long iduser){
    	
    	List<Usuario> user = usuarioRepository.findAll();
    	
    	for (Usuario usuario : user) {
    		if(usuario.getId() == iduser) {
    			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    		}
    	}
    	
    	return new ResponseEntity<String>("Invalid id!", HttpStatus.OK);
    	
    }
    
    @GetMapping(value = "buscarPorNome")
    @ResponseBody
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "name") String name){
    	
    	List<Usuario> user = usuarioRepository.buscarPorNome(name.trim());
    	
    	return new ResponseEntity<List<Usuario>>(user, HttpStatus.OK);
 
    }
}
