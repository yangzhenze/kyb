package com.zzy.db.helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author zzy
 * @Date 2020/4/24 2:28 下午
 */
public abstract class JdbcHolder {
    private static Logger log = LoggerFactory.getLogger(JdbcTemplateHelper.class);

    protected static JdbcTemplate defaultJdbcTemplate;


    /**
     * 记录每条线程引用的jdbcTemplate
     */
    protected static final ThreadLocal<JdbcTemplate> jdbcHolder = new ThreadLocal<>();

    /**
     * 设置当前线程所引用的数据源
     * @param dataSource
     */
    public static void setDataSource(DataSource dataSource){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        setJdbcHolder(jdbcTemplate);
        log.info("set【{}】DataSource!",Thread.currentThread().getName());
    }

    /**
     * 删除当前线程记录的jdbcTemplate
     * 防止内存溢出
     */
    public static void destroy(){
        jdbcHolder.remove();
        log.info("destroy【{}】DataSource!",Thread.currentThread().getName());
    }

    private static void setJdbcHolder(JdbcTemplate jdbcTemplate){
        jdbcHolder.set(jdbcTemplate);
    }

    /**
     * 设置默认数据源
     * @param jdbcTemplate
     */
    protected abstract void setDefaultJdbcTemplate(JdbcTemplate jdbcTemplate);

    /**
     * 获取JdbcTemplate对象
     * @return
     */
    public static JdbcTemplate getJdbcTemplate(){
        if(null == jdbcHolder.get()){
            return defaultJdbcTemplate;
        }
        return jdbcHolder.get();
    }


}
