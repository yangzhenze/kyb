package com.system.config;

import com.zzy.db.helper.JdbcHolder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * 后置处理
 * @author zzy
 * @Date 2020/5/7 3:51 下午
 */
@Component
public class PostProcessor extends JdbcHolder implements BeanPostProcessor{

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof JdbcTemplate){
            setDefaultJdbcTemplate((JdbcTemplate)bean);
        }
        return bean;
    }

    /**
     * 设置默认的jdbcTemplate
     * @param jdbcTemplate
     */
    @Override
    protected void setDefaultJdbcTemplate(JdbcTemplate jdbcTemplate) {
        JdbcHolder.defaultJdbcTemplate = jdbcTemplate;
    }
}
