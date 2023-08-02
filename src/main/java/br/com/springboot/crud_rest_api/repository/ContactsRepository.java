package br.com.springboot.crud_rest_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.springboot.crud_rest_api.model.Contacts;

@Repository
public interface ContactsRepository extends JpaRepository<Contacts, Long>{
	
	@Query(value = "select c from Contacts c where c.name like %?1%")
	List<Contacts> searchByName(@Param(value = "c_name") String c_name);
	
	@Query(value = "select c from Contacts c where c.cpf = ?1")
	List<Contacts> searchByCpf(String c_cpf);
	
	
	
}
