package com.tyss.frozenbottleapi.responsestructure;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseStructure<T> {
	
	private int statusCode;
	private String message;
	private T data;
}
