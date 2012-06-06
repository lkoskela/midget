package com.github.lkoskela.midget;

public class MidgetException extends RuntimeException {
	private static final long serialVersionUID = 6450188843439793592L;

	public MidgetException(String message) {
		super(message);
	}

	public MidgetException(String message, Throwable cause) {
		super(message, cause);
	}

	public MidgetException(Throwable cause) {
		super(cause);
	}
}