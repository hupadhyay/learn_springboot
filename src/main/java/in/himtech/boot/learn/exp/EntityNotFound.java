package in.himtech.boot.learn.exp;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFound extends RuntimeException {

	public EntityNotFound() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EntityNotFound(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
