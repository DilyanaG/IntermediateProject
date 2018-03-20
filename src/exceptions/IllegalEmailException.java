package exceptions;

public class IllegalEmailException extends Exception {

	private static final long serialVersionUID = -1404523310086202818L;

	public IllegalEmailException() {
		super();
	}

	public IllegalEmailException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public IllegalEmailException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalEmailException(String message) {
		super(message);
	}

	public IllegalEmailException(Throwable cause) {
		super(cause);
	}

	
}
