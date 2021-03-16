package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PersonRepository;
import com.example.demo.entity.Person;

@Service("personService")
public class PersonService {

	@Autowired
	@Qualifier("personRepository")
	private PersonRepository prersonRepository;
	
	public Person createPerson(Person person) {
		Person person1 = new Person();
		person1.setFirstName(person.getFirstName());
		person1.setSurname(person.getSurname());
		Person savedPerson = prersonRepository.save(person1);
		return savedPerson;
	}
	
	public Person changePerson(Person person) throws PersonException {
		
		Optional<Person> personById  = getPersonById(person.getId());
		Person person2 = personById.get();
		person2.setFirstName(person.getFirstName());
		person2.setSurname(person.getSurname());
		Person savedPerson = prersonRepository.save(person2);
		
		return savedPerson;
	}
	
	public boolean removePerson(Long id) throws PersonException {
		Optional<Person> personById = getPersonById(id);
		if(personById!=null) {
			
			prersonRepository.delete(personById.get());
			return true;
		}else {
			return false;
		}
		
		
	}
	
	public List<Person> ListAllPersons() {
		List<Person> list = prersonRepository.findAll();
		
		
		if(!list.isEmpty()) {
			return list;
		}else {
			return new ArrayList<Person>();
		}
		
	}
	
	
	public Optional<Person> getPersonById(Long id) {
		
		//Optional<Person> one = Optional.ofNullable(prersonRepository.getOne(id));
		Optional<Person> findById = prersonRepository.findById(id);
		if(findById.isPresent())
			return findById;
		else return null;
	}
	
}
