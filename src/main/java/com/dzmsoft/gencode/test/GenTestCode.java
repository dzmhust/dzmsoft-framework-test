package com.dzmsoft.gencode.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dzmsoft.framework.base.util.CheckEmptyUtil;
import com.dzmsoft.gencode.test.common.TestBaseConstant;
import com.dzmsoft.gencode.test.dto.ControllerHttpBean;
import com.dzmsoft.gencode.test.parse.Parse;
import com.dzmsoft.plugin.gencode.GenCode;
import com.dzmsoft.plugin.gencode.dto.Bean;

/**
 * 生成单元测试用例
 * @author dzm
 */
public class GenTestCode extends GenCode{
    private static final Logger logger = LoggerFactory.getLogger(GenTestCode.class);
    
    
    protected void createHttpTest(List<String> beanNameList, Bean bean) throws Exception{
        StringBuilder path = new StringBuilder();
        path.append(System.getProperty("user.dir")).append("/src/test/java/")
                .append(bean.getBasePackage().replace(".", "/")).append("/http/");
        Parse parse = new Parse();
        if (!CheckEmptyUtil.isEmpty(beanNameList)){
            for (String beanName:beanNameList){
                List<ControllerHttpBean> controllerHttpBeans = parse.parseClass(beanName);
                Map<String,Object> params = new HashMap<String, Object>();
                params.put("controllerHttpBeans", controllerHttpBeans);
                createWebappFile(beanName, bean, TestBaseConstant.HTTP_TEST_SUFFIX, TestBaseConstant.HTTP_TEST_VM, path.toString(), params, true);
            }
        }
    }
}
