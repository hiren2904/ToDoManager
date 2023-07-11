package com.lcwd.todo.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Todo {

	private int id;
	private String title ;
	private String content;
	private String status;
	private Date createddate;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date tododate;
}
