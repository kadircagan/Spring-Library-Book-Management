package com.bookRegister.bookRegister.response;



public class BaseBody<T> {

    private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}