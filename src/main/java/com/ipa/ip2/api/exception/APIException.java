package com.ipa.ip2.api.exception;

/**
 * Created by amit on 13/02/17.
 */
public class APIException extends Exception{

    public APIException(){
        super();
    }

    public APIException(String message){
        super(message);
    }

}
