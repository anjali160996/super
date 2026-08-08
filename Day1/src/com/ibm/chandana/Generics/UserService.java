package com.ibm.chandana.Generics;

import java.util.Optional;
import java.util.function.Predicate;

public class UserService {
	private Repository<User> repository;

	public UserService(Repository<User> repository) {
		this.repository = repository;
	}
	public Optional<User> findUser(Long id) {
		return repository.findById(id);
	}
	public Optional<String> findEmail(Long id) {
		return repository.findById(id).flatMap(user -> Optional.ofNullable(user.getEmailId()));
	}
	public String findNameOr(Long id, String defaultName) {
		return repository.findById(id).map(User::getName).orElse(defaultName);
	}
	public Optional<String> findVerifiedEmail(Long id, Predicate<String> isValid) {
		return repository.findById(id).flatMap(user -> Optional.ofNullable(user.getEmailId())).filter(isValid);
	}
	

}
