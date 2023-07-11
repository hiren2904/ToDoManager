package com.lcwd.todo.controller;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.todo.entity.Todo;
import com.lcwd.todo.service.TodoService;

@RestController
@RequestMapping("/todos")
public class ToDoController {

	Logger logger = LoggerFactory.getLogger(ToDoController.class);
	@Autowired
	private TodoService service;
	
	Random random = new Random();
	private Todo updateTodo;
	
	@PostMapping
	public ResponseEntity<Todo> createTodoHandler(@RequestBody Todo todo) {
		
		int id = random.nextInt(9999999);
		todo.setId(id);
		Date currentdate = new Date();
		logger.info("currentdate",currentdate);
		todo.setCreateddate(currentdate);
		
		logger.info("create todo");
		Todo todo1= service.createTodo(todo);
		return new ResponseEntity<>(todo1,HttpStatus.CREATED);
		
	}
	
	@GetMapping()
	public ResponseEntity<List<Todo>> getAllTodos(){
		List<Todo> listtodo = service.getAllTodo();
		return new ResponseEntity<>(listtodo,HttpStatus.OK);
	}
	
	@GetMapping("/{todoid}")
	public ResponseEntity<Todo> getoneTodo(@PathVariable String todoid) {
		Todo todo = service.getOnetodo(Integer.parseInt(todoid));
		//return new ResponseEntity<>(todo,HttpStatus.OK);
		return ResponseEntity.ok(todo);
	}
	
	
	@PutMapping("/{todoid}")
	public ResponseEntity<Todo> updateTodo(@RequestBody Todo todo,@PathVariable String todoid){
		updateTodo = service.getUpdateTodo(todo,Integer.parseInt(todoid));
		return ResponseEntity.ok(updateTodo);
		
	}
	
	@DeleteMapping("/{todoId}")
	public ResponseEntity<String> deleteTodo(@PathVariable String todoId ){
		service.deleteTodo(Integer.parseInt(todoId));
		return ResponseEntity.ok("Delete Todo Successfully");
	}
}


















