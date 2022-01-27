package com.josh.todo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.josh.todo.model.Todo;

@Repository
public interface TodoRepository extends MongoRepository<Todo, String> {
	

}
