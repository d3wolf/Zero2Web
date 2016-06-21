package com.z2w.common.exception;

public class Z2WException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6636987474817402737L;
	
	public Z2WException() {
		super();
	}

	public Z2WException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public Z2WException(String arg0) {
		super(arg0);
	}

	public Z2WException(Throwable arg0) {
		super(arg0);
	}
}
