package com.example.demo.exception;

public class EmployeeException extends RuntimeException{
	private static final long serialVersionUID=-5219910739102703298L;
	public EmployeeException(String message)
	{
		super(message);
	}
@Override
public String toString()
{
	return "EmployeeID is not found";
}
}
