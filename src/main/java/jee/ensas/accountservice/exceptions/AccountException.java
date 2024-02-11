package jee.ensas.accountservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountException extends RuntimeException{

    private final int status;

    public AccountException(int status , String message){
        super(message);
        this.status = status;
    }
}