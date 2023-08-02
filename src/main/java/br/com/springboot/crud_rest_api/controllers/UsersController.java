package br.com.springboot.crud_rest_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.crud_rest_api.model.Contacts;
import br.com.springboot.crud_rest_api.model.Users;
import br.com.springboot.crud_rest_api.repository.ContactsRepository;
import br.com.springboot.crud_rest_api.repository.UsersRepository;

@RestController
public class UsersController {

	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private ContactsRepository contactsRepository;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String homeText() {
		String msg = "UEX API";
		return "Welcome to the " + msg;
	}

	@PostMapping(value = "createUser")
	@ResponseBody
	public ResponseEntity<Users> createUser(@RequestBody Users new_user) {

		Users user = usersRepository.save(new_user);

		return new ResponseEntity<Users>(user, HttpStatus.CREATED);
	}

	@GetMapping(value = "listAll")
	@ResponseBody
	public ResponseEntity<List<Users>> usersList() {

		List<Users> user = usersRepository.findAll();

		return new ResponseEntity<List<Users>>(user, HttpStatus.OK);
	}

	@PutMapping(value = "updateUser")
	@ResponseBody
	public ResponseEntity<?> updateUser(@RequestBody Users user) {

		if (user.getId() == null) {
			return new ResponseEntity<String>("Please inform an id to save.", HttpStatus.OK);
		}

		Users user_updated = usersRepository.saveAndFlush(user);

		return new ResponseEntity<Users>(user_updated, HttpStatus.OK);
	}

	@DeleteMapping(value = "deleteUser")
	@ResponseBody
	public ResponseEntity<String> deleteUser(@RequestParam Long iduser) {

		usersRepository.deleteById(iduser);

		return new ResponseEntity<String>("<User deleted from database>", HttpStatus.OK);
	}

	@PostMapping(value = "/")
	@ResponseBody
	public String login(String name, String password){
		
		//Users user = usersRepository.validateLogin(name, password);
		
		return "redirect:login.html";
	}
	

	@PostMapping(value = "createContact")
	@ResponseBody
	public ResponseEntity<Contacts> createContact(@RequestBody Contacts new_contact){

		Contacts contact = contactsRepository.save(new_contact);

		return new ResponseEntity<Contacts>(contact, HttpStatus.CREATED);
	}

	@GetMapping(value = "listContacts")
	@ResponseBody
	public ResponseEntity<List<Contacts>>contactsList() {

		List<Contacts> contact = contactsRepository.findAll();

		return new ResponseEntity<List<Contacts>>(contact, HttpStatus.OK);
	}

	@PutMapping(value = "updateContacts")
	@ResponseBody
	public ResponseEntity<?> updateContacts(@RequestBody Contacts contact) {

		if (contact.getId() == null) {
			return new ResponseEntity<String>("Enter an id to continue.", HttpStatus.OK);
		}

		Contacts contact_updated = contactsRepository.saveAndFlush(contact);

		return new ResponseEntity<Contacts>(contact_updated, HttpStatus.OK);
	}

	@DeleteMapping(value = "deleteContact")
	@ResponseBody
	public ResponseEntity<String> deleteContact(@RequestParam Long idcontact) {

		contactsRepository.deleteById(idcontact);

		return new ResponseEntity<String>("<Contact deleted from database>", HttpStatus.OK);
	}

	
	@GetMapping(value = "seachContactByCpf")
	@ResponseBody
	public ResponseEntity<List<Contacts>> searchContactByCpf(@RequestParam(name = "c_cpf") String c_cpf) {

		List<Contacts> contact = contactsRepository.searchByCpf(c_cpf);

		return new ResponseEntity<List<Contacts>>(contact, HttpStatus.OK);
	}

	
	@GetMapping(value = "searchByName")
	@ResponseBody
	public ResponseEntity<List<Contacts>> searchByName(@RequestParam(name = "c_name") String c_name) {

		List<Contacts> contact = contactsRepository.searchByName(c_name);
		
		if(contact.isEmpty()) {
			return new ResponseEntity<List<Contacts>>(contact, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<List<Contacts>>(contact, HttpStatus.OK);

	}
	 

}
