package com.boot.demo.exception;

public class EntityNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -2504357656421954162L;

	public EntityNotFoundException() {
		super("Entity not found");
	}

}
