package com.zzy.db.helper;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * 后置处理器设置
 * @author zzy
 * @Date 2020/5/7 3:51 下午
 */
@Component
public class SetDefaultJdbcTemplate extends JdbcHolder implements BeanPostProcessor{

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof JdbcTemplate){
            setDefaultJdbcTemplate((JdbcTemplate)bean);
        }
        return bean;
    }

    /**
     * 后置处理器设置JdbcTemplateHelper默认的jdbcTemplate实例
     * @param jdbcTemplate
     */
    @Override
    protected void setDefaultJdbcTemplate(JdbcTemplate jdbcTemplate) {
        JdbcHolder.defaultJdbcTemplate = jdbcTemplate;
    }
}
