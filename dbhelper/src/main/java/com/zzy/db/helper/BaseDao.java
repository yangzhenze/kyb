package com.zzy.db.helper;
import com.zzy.db.helper.anotation.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * @author zzy
 */
public class BaseDao<T,ID extends Serializable> {
    private static Logger log = LoggerFactory.getLogger(BaseDao.class);

    public String tableName;
    private String pkName;
    public RowMapper<T> rowMapper;


    private Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public BaseDao() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        entityClass = (Class<T>) type.getActualTypeArguments()[0];
        rowMapper = BeanPropertyRowMapper.newInstance(entityClass);
        //表名
        this.tableName = entityClass.getAnnotation(Table.class).name();
        this.pkName = ReflexUtil.getTablePk(entityClass);

        if(log.isDebugEnabled()){
            StringBuffer logStr = new StringBuffer();
            logStr.append("\n================================================================\n");
            logStr.append("Table Name：" + this.tableName+"\n");
            logStr.append("Dao Implement：" + entityClass.getName()+"\n");
            logStr.append("Table ID：" + this.pkName+"\n");
            logStr.append("================================================================\n");
            log.debug(logStr.toString());
        }
    }

    /**
     * 获取jdbcTemplate对象
     * @return
     */
    protected JdbcTemplate jdbcTemplate(){
        return JdbcTemplateHelper.getJdbcTemplate();
    }

    /**
     * 插入
     * @param entity
     * @return
     */
    public boolean insertEntity(T entity) {
        return JdbcTemplateHelper.insertEntity(entity);
    }


    /**
     * 插入返回实体
     * @param entity
     * @return
     */
    public T insertAndGetEntity(T entity){
        return JdbcTemplateHelper.insertAndGetEntity(entity);
    }

    /**
     * 更新
     * @param entity
     * @return
     */
    public boolean updateEntity(T entity) {
        return JdbcTemplateHelper.updateEntity(entity);
    }


    /**
     * 插入返回实体
     * @param entity
     * @return
     */
    public T updateAndGetEntity(T entity){
        return JdbcTemplateHelper.updateAndGetEntity(entity);
    }

    /**
     * 删除
     * @param entity
     * @return
     */
    public boolean deleteEntity(T entity) {
        return JdbcTemplateHelper.deleteEntity(entity);
    }

    /**
     * 添加或更新--批量
     * @param entityList
     */
    public void insertOrUpdate(List<T> entityList){
        JdbcTemplateHelper.insertOrUpdate(entityList);
    }

    /**
     * 添加或更新--批量 ==异常回滚
     * @param entityList
     * @return
     */
    public boolean batchInsertOrUpdate(List<T> entityList){
        return JdbcTemplateHelper.batchInsertOrUpdate(entityList);

    }


    /**
     * 添加或更新
     * @param entity
     * @return
     */
    public boolean insertOrUpdate(T entity){
        return JdbcTemplateHelper.insertOrUpdate(entity);
    }

    /**
     * 根据id删除
     * @param ids
     * @return
     */
    public boolean deleteById(ID ... ids){
        return JdbcTemplateHelper.deleteById(entityClass,ids);
    }

    /**
     * 清空该表数据
     * @return
     */
    public boolean truncateTable() {
        String sql = " TRUNCATE TABLE " + this.tableName;
        ReflexUtil.forDebuggingAndPrintingSQLLogs(sql,null);
        return JdbcTemplateHelper.update(sql) > 0?true:false;
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public T findEntityById(ID id) {
        return JdbcTemplateHelper.findEntityById(entityClass,id);
    }


    /**
     * 查询返回实体(单条)
     * @param sql
     * @param args
     * @return
     */
    public T findFirstEntity(String sql,Object ... args){
        return JdbcTemplateHelper.findFirstEntity(entityClass,sql, args);
    }

    /**
     * 查询全部实体
     * @return
     */
    public List<T> findAllEntity() {
        return JdbcTemplateHelper.findAllEntity(entityClass);
    }

    /**
     * 查询返回实体(多条)
     * @param sql
     * @param args
     * @return
     */
    public List<T> findEntity(String sql,Object ... args){
        return JdbcTemplateHelper.findEntity(entityClass,sql,args);
    }

    /**
     * 查询返回实体(多条)
     * @param entity 不为空的属性为查询条件
     * @return
     */
    public List<T> findEntity(T entity){
        return JdbcTemplateHelper.findEntity(entity);
    }

    /**
     * 根据sql查询返回为Map类型(多条)
     * @param sql
     * @param args
     * @return
     */
    public List<Map<String,Object>> select(String sql,Object ... args){
        return JdbcTemplateHelper.select(sql,args);
    }

    /**
     * 根据sql查询返回为Map类型(单条)
     * @param sql
     * @param args
     * @return
     */
    public Map<String,Object> selectFirst(String sql,Object ... args){
        return JdbcTemplateHelper.selectFirst(sql,args);
    }

    /**
     * 获取实体分页 sql为where
     * @param whichPage
     * @param pageSize
     * @param whereSql
     * @param args
     * @return
     */
    public Page<T> paginate(int whichPage, int pageSize, String whereSql, Object ... args){
        return JdbcTemplateHelper.paginate(entityClass,whichPage,pageSize,whereSql,args);

    }

    public Page<Map<String,Object>> paginate(int whichPage,int pageSize,String select,String sql,Object ... args){
        return JdbcTemplateHelper.paginate(whichPage,pageSize,select,sql,args);
    }





}



