package com.harry.web.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

/**
 * Created by chenhaibo on 2017/11/9.
 */
public class ApiResult<T> implements Serializable {
    private static final long serialVersionUID = -4696898674758059398L;

    private static final ObjectMapper MAPPER=new ObjectMapper();

    private int code;
    private String info;

    private T data;

    private boolean ok = true;




    public ApiResult(int code, String info, T data) {
        this.code = code;
        this.ok = code == 200;
        this.info = info;
        this.data = data;
    }



    public ApiResult(int code, T data) {
        this.code = code;
        this.ok = code == 200;
        this.data = data;
    }

    public ApiResult(T data) {
        this.data = data;
    }

    public ApiResult() {

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
        this.ok = code == 200;
    }

    public boolean getOk(){
        return ok;
    }
    public void setOk(boolean ok){
        this.ok = ok;
    }
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }





}