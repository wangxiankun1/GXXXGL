package com.util;

/**
 * 接口返回参数
 *
 * @param <T>
 */
public class Response<T> {
    private int code;
    private String msg;
    private T resdata;
    private int count;
    private int totalPage;
    private T jcpeizhi;
    private T jcdaohangslist;
    private T jcbiaotis;
    private String role;
    private T tongjiMap;
    private T tongjiJSON;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResdata() {
        return resdata;
    }

    public void setResdata(T resdata) {
        this.resdata = resdata;
    }

    public void setJcpeizhi(T jcpeizhi) {
        this.jcpeizhi = jcpeizhi;
    }

    public T getJcpeizhi() {
        return jcpeizhi;
    }

    public void setJcdaohangslist(T jcdaohangslist) {
        this.jcdaohangslist = jcdaohangslist;
    }

    public T getJcdaohangslist() {
        return jcdaohangslist;
    }

    public void setJcbiaotis(T jcbiaotis) {
        this.jcbiaotis = jcbiaotis;
    }

    public T getJcbiaotis() {
        return jcbiaotis;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setTongjiMap(T tongjiMap) {
        this.tongjiMap = tongjiMap;
    }

    public T getTongjiMap() {
        return tongjiMap;
    }

    public void setTongjiJSON(T tongjiJSON) {
        this.tongjiJSON = tongjiJSON;
    }

    public T getTongjiJSON() {
        return tongjiJSON;
    }

    public Response() {
    }

    public Response(T resdata) {
        this.resdata = resdata;
    }

    public Response(T resdata,T jcpeizhi,T jcbiaotis,T jcdaohangslist) {
        this.resdata = resdata;
        this.jcpeizhi = jcpeizhi;
        this.jcbiaotis = jcbiaotis;
        this.jcdaohangslist = jcdaohangslist;
    }

    public Response(T tongjiMap,T tongjiJSON) {
        this.tongjiMap = tongjiMap;
        this.tongjiJSON = tongjiJSON;
    }

    public static Response success() {
        Response response = new Response();
        response.setCode(200);
        response.setMsg("操作成功");
        return response;
    }

    public static <T> Response<T> success(T resdata, int count, int totalPage) {
        Response<T> response = new Response<T>(resdata);
        response.setCode(200);
        response.setMsg("操作成功");
        response.setCount(count);
        response.setTotalPage(totalPage);
        return response;
    }

    public static <T> Response<T> success(T resdata) {
        Response<T> response = new Response<T>(resdata);
        response.setCode(200);
        response.setMsg("操作成功");
        return response;
    }

    public static Response error(int code, String msg) {
        Response response = new Response();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }

    public static Response error() {
        Response response = new Response();
        response.setCode(500);
        response.setMsg("服务器错误");
        return response;
    }

    public static <T> Response<T> success(T resdata,String role,T jcpeizhi,T jcbiaotis,T jcdaohangslist) {
        Response<T> response = new Response<T>(resdata,jcpeizhi,jcbiaotis,jcdaohangslist);
        response.setRole(role);
        response.setCode(200);
        response.setMsg("登陆成功");
        return response;
    }

    public static <T> Response<T> tongjiSuccess(T tongjiMap,T tongjiJSON) {
        Response<T> response = new Response<T>(tongjiMap,tongjiJSON);
        response.setCode(200);
        response.setMsg("操作成功");
        return response;
    }

}
