package com.dzmsoft.gencode.test;

import java.util.List;

import com.dzmsoft.plugin.gencode.dto.Bean;

public class GenTestUtil {
    /**
     * 产生http请求的单元测试用例
     * @param beanNameList
     * @param basePackage
     */
    public static void genHttpTest(List<String> beanNameList, String basePackage){
        Bean bean = new Bean(basePackage);
        GenTestCode genTestCode = new GenTestCode();
        try {
            genTestCode.createHttpTest(beanNameList, bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
