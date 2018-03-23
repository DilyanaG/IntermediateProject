package exceptions;

public class IllegalVideoDescriptionException extends Exception {

	private static final long serialVersionUID = 1628100694865362905L;

	public IllegalVideoDescriptionException() {
		super();
	}

	public IllegalVideoDescriptionException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public IllegalVideoDescriptionException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalVideoDescriptionException(String message) {
		super(message);
	}

	public IllegalVideoDescriptionException(Throwable cause) {
		super(cause);
	}

	
}
