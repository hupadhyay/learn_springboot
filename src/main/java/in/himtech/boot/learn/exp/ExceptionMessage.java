package in.himtech.boot.learn.exp;

import java.sql.Timestamp;

public class ExceptionMessage {
	
	private Timestamp timeStamp;
	
	private String message;
	
	private String requestedResourceUri;
	
	private String errorDetail;
	

	public ExceptionMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExceptionMessage(Timestamp timeStamp, String message, String requestedResourceUri, String errorDetail) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.requestedResourceUri = requestedResourceUri;
		this.errorDetail = errorDetail;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRequestedResourceUri() {
		return requestedResourceUri;
	}

	public void setRequestedResourceUri(String requestedResourceUri) {
		this.requestedResourceUri = requestedResourceUri;
	}

	public String getErrorDetail() {
		return errorDetail;
	}

	public void setErrorDetail(String errorDetail) {
		this.errorDetail = errorDetail;
	}

}
