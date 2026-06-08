package com.houseproject.MyPak.exceptions;

public class AccountNotApprovedException extends RuntimeException {
    public AccountNotApprovedException (String message){
        super(message);
    }

}
