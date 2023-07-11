package com.lcwd.todo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.lcwd.todo.entity.Todo;

@Component
public class TodoService {

	Logger logger = LoggerFactory.getLogger(TodoService.class);
	List<Todo> todos = new ArrayList<>();
	private List<Todo> collect;
	private List<Todo> newList;
	
	public Todo createTodo(Todo todo) {
		todos.add(todo);
		logger.info("Todos",this.todos);
		return todo;
	}
	
	public List<Todo> getAllTodo() {
		return todos;
	}
	
	public Todo getOnetodo(int id) {
		Todo todo = todos.stream().filter(t->id==t.getId()).findAny().get();
		logger.info("Todo : {}",todo);
		return todo;
	}	
	
	public Todo getUpdateTodo(Todo todo, int todoid) {
		collect = todos.stream().map(t->{
			if(t.getId() == todoid ) {
				t.setContent(todo.getContent());
				t.setStatus(todo.getStatus());
				t.setTitle(todo.getTitle());
				return t;
			}else {
				return t;
			}
			
		}).collect(Collectors.toList());
		
		todo.setId(todoid);
		return todo;
	}
	
	public void deleteTodo(int id) {
		
		logger.info("Deleting todo");
		newList = todos.stream().filter(t -> t.getId()!= id).collect(Collectors.toList());
		todos = newList;
	}
}








