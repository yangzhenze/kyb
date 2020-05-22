package com.system.common;

import com.zzy.db.helper.generate.DbConfig;
import com.zzy.db.helper.generate.Generate;

/**
 * @author zzy
 * @Date 2018/12/26 2:12 PM
 */
public class CreateTest {



    public static void main(String[] args) {
        String FILE_PATH = "system_admin/src/main/java/com/system/bean";
        String DATABASENAME = "rgyx_gx";
        String URL = "192.168.1.205";
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String USER_NAME = "rgyx_gx";
        String PASSWORD = "rgyx_gx";

        //多个表用'',分隔
        String IN_TABLES = "'t_business_duty_log'";
        //是否创建dao
        boolean IS_DAO = true;
        boolean IS_SERVICE = true;

        DbConfig config = new DbConfig();
        config.setFilePath(FILE_PATH);
        config.setDataBaseName(DATABASENAME);
        config.setUrl(URL);
        config.setPort("3316");
        config.setDriver(JDBC_DRIVER);
        config.setUser(USER_NAME);
        config.setPassword(PASSWORD);
        config.setInTables(IN_TABLES);
        config.setInTables(IN_TABLES);
        config.setCreateDao(true);
        config.setCreateService(true);



        Generate generate = new Generate();
        generate.CreateBean(config);

        //generate.CreateDao("system_admin/src/main/java/com/system/bean","BusinessDutyLog");
        //generate.CreateService("system_admin/src/main/java/com/system/bean","BusinessDutyLog");

    }
}
