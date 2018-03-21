package exceptions;

public class IllegalUserArgumentException extends Exception {

	private static final long serialVersionUID = 518285349703046462L;

	public IllegalUserArgumentException() {
		super();
	}

	public IllegalUserArgumentException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public IllegalUserArgumentException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalUserArgumentException(String message) {
		super(message);
	}

	public IllegalUserArgumentException(Throwable cause) {
		super(cause);
	}
	
	

}
