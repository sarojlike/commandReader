package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Person;

@Component
public class CommandReader implements CommandLineRunner{
	
	@Autowired
	@Qualifier("personService")
	private PersonService personService;

	@SuppressWarnings("resource")
	@Override
	public void run(String... args) throws Exception {
		

		
		boolean inputIsValid = true;
       
	outer:	while (inputIsValid) {
				System.out.println("\n\nNote: please press \"A\" to Add Person,press \"E\" to Edit Person,\"D\" to delete a person,\"L\" to list all persons and \"Q\" to quit the console");
				Scanner scanner = new Scanner(System.in);
				String input = scanner.nextLine().toUpperCase();
        	
		        	switch (input) {
					case "A":{
								
								System.out.println("Enter your firtst name");
					    		String name = scanner.nextLine();
					    		name=(name==null?"":name.trim());
			    		
				    			System.out.println("Enter Your Surname:");
				    			String surname = scanner.nextLine();
				    			surname=(surname==null?"":surname.trim());
			    			
			    		
				    			if((!name.trim().equals("")) && (!surname.trim().equals(""))&&(name.length()>4) && (surname.length()>4)) {
			    			
					    				Person person = new Person();
					        			person.setFirstName(name);
					        			person.setSurname(surname);
					        			Person createdPerson = personService.createPerson(person);
					        			System.out.println("\n");
					        			System.out.println(createdPerson+" added sucessfully");
					        			System.out.println("\n");
					        			
				    			}else {
				    				System.out.println("Please Enter a valid value for name and Surname");
				    				System.out.println("\n");
				    			}
						
						break;
						
					}
					case "E" : {
						
									System.out.println("Enter id of the person to edit");
									long nextLong=0;;
									try {
										nextLong = scanner.nextLong();
									} catch (Exception e) {
										
										System.out.println("Please enter a valid input");
									}
									
									if(nextLong!=0) {
								
										Optional<Person> personById = personService.getPersonById(nextLong);
								
								
											if(personById!=null) {
												Person person = personById.get();
												System.out.println("Person with given id is "+person+"\n");
												
												Scanner scanner2 = new Scanner(System.in);
												System.out.println("Enter new Value for first name if any");
												String newName = scanner2.nextLine();
												newName=(newName==null?"":newName.trim());
												
									    		
									    		System.out.println("Enter new Value for Surname if any");
									    		String newSurname = scanner2.nextLine();
									    		newSurname=(newSurname==null?"":newSurname.trim());
									    		
								    			if(!newName.equals("") && newName.length()>4)
								    				person.setFirstName(newName);
								    			
								    			if(!newSurname.equals("") && newSurname.length()>4)
								    				person.setSurname(newSurname);
								    			
								    			Person changedPerson = personService.changePerson(person);
								    			System.out.println(" Changed sucessfully new data is "+changedPerson+"\n");
												
											}else {
												System.out.println("There is no data with provided id");
											}
									}
						break ;
					}
					case "D" : {
						
									System.out.println("Enter id of the person to delete");
									long nextLong=0;;
									try {
										nextLong = scanner.nextLong();
									} catch (Exception e) {
										
										System.out.println("Please enter a valid input");
									}
									
									if(nextLong!=0) {
										
										Optional<Person> personById = personService.getPersonById(nextLong);
										
											if(personById!=null) {
												Person person = personById.get();
												boolean isremoved = personService.removePerson(person.getId());
												if(isremoved)
													System.out.println("Person deleted sucessfully \n");
											}else {
												System.out.println("there is no data with provided id");
											}
									}
								
								break;
							}
					case "L" : {
						
								List<Person> list = personService.ListAllPersons();
								if(!list.isEmpty()) {
									list.forEach(obj->{
										System.out.println(obj);
									});
								}else {
									System.out.println("No data is available");
								}
							break ;
						}
					case "Q" : {
						
							inputIsValid=false;
							System.out.println("Console will no longer process any input,BYE");
							break outer;
					}
						
						
		
					default:{
						break;
					}
		        	
        		
        }
	}

	}
}
