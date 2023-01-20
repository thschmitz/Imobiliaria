package com.thschmitz.realstate.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thschmitz.realstate.domain.User;
import com.thschmitz.realstate.domain.services.exception.AuthenticationException;
import com.thschmitz.realstate.domain.services.exception.ObjectNotFoundException;
import com.thschmitz.realstate.domain.services.exception.ParametersNotPassedException;
import com.thschmitz.realstate.repository.UserRepository;
import com.thschmitz.realstate.resource.util.JWT;
import com.thschmitz.realstate.resource.util.Password;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(String id) {
		Optional<User> user = repository.findById(id);
		
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		
	}
	
	public String insert(User obj) {
		obj.setPassword(Password.encodePassword(obj));
		repository.insert(obj); // Encoding the password and sending the object
		return JWT.createJWT(obj);
	}
	
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public User update(User obj) {
		User newObj = findById(obj.getId());
		
		
		updateData(newObj, obj);
		
		return repository.save(newObj);
	}
	
	public void updateData(User newObj, User obj) {
		if(obj.getName() == null || obj.getEmail() == null || obj.getPassword() == null) {
			throw new ParametersNotPassedException("You need to inform all the parameters to update!");
		} else {
			newObj.setName(obj.getName());
			newObj.setEmail(obj.getEmail());
			newObj.setPassword(obj.getPassword());	
		}
		
	}
	
	public String login(User obj) {
		User newObj = repository.login(obj.getEmail());
		
		if(newObj == null) {
			throw new AuthenticationException(null);
		}
		
		boolean passwordIsValid = Password.matchPassword(obj, newObj);
		
		if(passwordIsValid == false) {
			throw new AuthenticationException(null);
		}

		return JWT.createJWT(newObj);
		
	}

	/*public User fromDTO(UserDTO objDto) {
		
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail(), null, objDto.getCreated_at());
	}*/
}
