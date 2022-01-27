package com.josh.todo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josh.todo.model.Todo;
import com.josh.todo.repository.TodoRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TodoController {


	@Autowired
	TodoRepository todoRepo;

	@GetMapping("/todos")
	public List<Todo> getTodos() {
		Sort sortByCreatedAt = Sort.by(Sort.Direction.DESC, "createdAt");
		return todoRepo.findAll(sortByCreatedAt);
	}

	@GetMapping(value = "/todo/{id}")
	public ResponseEntity<Todo> getTodo(@PathVariable("id") String todoId) {
		return todoRepo.findById(todoId)
				.map(todo -> ResponseEntity.ok().body(todo))
				.orElse(ResponseEntity.notFound().build());
	}


	@PostMapping("/todo")
	public Todo saveTodo(@Valid @RequestBody Todo todo) {
		todo.setCompleted(false);
		return todoRepo.save(todo);
	}

	@PutMapping("/todo/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable("id") String todoId,
			@Valid @RequestBody Todo todo) {
		return todoRepo.findById(todoId)
				.map(todoData -> {
					todoData.setTitle(todo.getTitle());
					todoData.setCompleted(todo.getCompleted());
					Todo updatedTodo = todoRepo.save(todoData);
					return ResponseEntity.ok().body(updatedTodo);
				})
				.orElse(ResponseEntity.notFound().build());

	}


	@DeleteMapping(value = "/todo/{id}")
	public ResponseEntity<?> removeTodo(@PathVariable("id") String todoId) {
		return todoRepo.findById(todoId)
		.map(todoData -> {
			todoRepo.deleteById(todoId);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}

