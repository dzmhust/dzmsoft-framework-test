package com.dzmsoft.gencode.test.parse;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dzmsoft.framework.base.web.mvc.view.GenericResponse;
import com.dzmsoft.gencode.test.dto.ControllerHttpBean;

public class Parse {
    
    public List<ControllerHttpBean> parseClass(String className){
        List<ControllerHttpBean> controllerHttpBeans = null;
        try {
            Class<?> objectClass = Class.forName(className);
            controllerHttpBeans = parseMethod(objectClass.getMethods());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return controllerHttpBeans;
    }
    
    /**
     * 解析方法
     * @param methods
     */
    private List<ControllerHttpBean> parseMethod(Method[] methods){
        List<ControllerHttpBean> controllerHttpBeans = new ArrayList<ControllerHttpBean>();
        if (methods!=null && methods.length>0){
            ControllerHttpBean controllerHttpBean = null;
            for(Method method:methods){
                if (method.getReturnType().getName().equals(GenericResponse.class.getName()) ){
                    controllerHttpBean = new ControllerHttpBean();
                    controllerHttpBean.setMethodName(method.getName());
                    // 注解
                    parseAnnotation(method, controllerHttpBean);
                    // 参数
                    controllerHttpBeans.add(controllerHttpBean);
                }
            }
        }
        return controllerHttpBeans;
    } 
    
    private void parseAnnotation(Method method,ControllerHttpBean controllerHttpBean){
        Annotation[] annotations =method.getAnnotations();
        if (annotations!=null && annotations.length>0){
            for (Annotation annotation:annotations){
                String str = annotation.toString();
                if (str.indexOf("@org.springframework.web.bind.annotation.RequestMapping") >= 0){
                    String reqMethod = parseText(str, "method=\\[(.*)\\], name");
                    controllerHttpBean.setReqMethod(reqMethod);
                    String iface = parseText(str, "value=\\[(.*)\\], consumes");
                    controllerHttpBean.setIface(iface);
                    break;
                }
            }
        }
    }
    
    private void parseParamter(Parameter[] parameters){
        if (parameters!=null && parameters.length>0){
            for (Parameter parameter:parameters){
                parameter.getName();
            }
        }
    }
    
    private String parseText(String text, String regx){
        Pattern p = Pattern.compile(regx);
        Matcher m = p.matcher(text );//  
        if (m.find()) {  
            return m.group(1);
        }  
        return null;
    }
}
