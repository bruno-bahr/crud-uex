package br.com.springboot.crud_rest_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.crud_rest_api.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{	 
	
	@Query(value = "select u from Users u where email = :email and password = :password")
	public Users validateLogin(String name, String password);	
}
