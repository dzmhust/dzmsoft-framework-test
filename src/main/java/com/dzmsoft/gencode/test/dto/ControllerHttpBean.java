package com.dzmsoft.gencode.test.dto;

public class ControllerHttpBean {
    /**
     * 请求方式，GET、POST
     */
    private String reqMethod;
    /**
     * 接口路径
     */
    private String iface;
    /**
     * 方法名
     */
    private String methodName;
    public String getReqMethod() {
        return reqMethod;
    }
    public void setReqMethod(String reqMethod) {
        this.reqMethod = reqMethod;
    }
    public String getIface() {
        return iface;
    }
    public void setIface(String iface) {
        this.iface = iface;
    }
    public String getMethodName() {
        return methodName;
    }
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
