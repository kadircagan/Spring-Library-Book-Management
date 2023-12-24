package com.bookRegister.bookRegister.response;


import com.bookRegister.bookRegister.exceptions.Status;


public class BaseResponse<T> {
    private BaseBody<T> body;
    private Status status;
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public BaseBody<T> getBody() {
		return body;
	}
	public void setBody(BaseBody<T> body) {
		this.body = body;
	}

}