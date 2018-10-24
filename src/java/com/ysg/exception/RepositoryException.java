/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ysg.exception;

/**
 *
 * @author tochukwu
 */
public class RepositoryException extends RuntimeException {
     public RepositoryException(Throwable ex){
        super(ex);
    }

    public RepositoryException(String msg){
        super(msg);
    }

    public RepositoryException(String msg, Throwable ex){
        super(msg, ex);
    }
}
