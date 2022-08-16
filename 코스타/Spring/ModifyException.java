package com.example.demo.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ModifyException extends Exception {
	private static final long serialVersionUID = 1L;

	public ModifyException(String msg) {
		super(msg);
	}
}
