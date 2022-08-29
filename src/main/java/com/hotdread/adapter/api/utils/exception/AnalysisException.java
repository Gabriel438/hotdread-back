package com.hotdread.adapter.api.utils.exception;

public class AnalysisException extends Exception{

    public AnalysisException(Exception e) {
        super(e);
    }

    public AnalysisException(String message){
        super("Error on Analysis: " + message);
    }

}
