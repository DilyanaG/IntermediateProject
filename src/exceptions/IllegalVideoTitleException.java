package exceptions;

public class IllegalVideoTitleException extends Exception {

	private static final long serialVersionUID = -3125393625189888720L;

	public IllegalVideoTitleException() {
		super();
	}

	public IllegalVideoTitleException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public IllegalVideoTitleException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalVideoTitleException(String message) {
		super(message);
	}

	public IllegalVideoTitleException(Throwable cause) {
		super(cause);
	}

	
}
