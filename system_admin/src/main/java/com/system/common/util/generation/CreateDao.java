package com.system.common.util.generation;

import com.alibaba.druid.util.StringUtils;
import com.system.common.util.DateUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * @author zzy
 * @Date 2018/12/22 4:39 PM
 */
public class CreateDao {

    private String beanFilePath;
    private String systemPath = System.getProperty("user.dir");
    private String beanPackage;
    private String daoPackage;
    private String inDao;

    public CreateDao(){

    }

    public CreateDao(String beanFilePath,String inDao){
        this.beanFilePath = beanFilePath;
        this.inDao = inDao;
        beanPackage = this.beanFilePath.substring(this.beanFilePath.indexOf("java")+5,this.beanFilePath.length()).replace("/",".");
        daoPackage = beanPackage.substring(0,beanPackage.lastIndexOf("."))+".dao";
    }
    public CreateDao(String beanFilePath){
        this.beanFilePath = beanFilePath;
        beanPackage = this.beanFilePath.substring(this.beanFilePath.indexOf("java")+5,this.beanFilePath.length()).replace("/",".");
        daoPackage = beanPackage.substring(0,beanPackage.lastIndexOf("."))+".dao";
    }

    private void readBean(){
        File file = new File(systemPath+"/"+beanFilePath+"/");
        File [] files = file.listFiles();

        try{
            for(File f:files){
                if(f.isFile()){
                    String beanName = f.getName().replaceAll("[.][^.]+$", "");
                    if(!StringUtils.isEmpty(inDao)){
                        if(!inDao.contains(beanName)){
                            continue;
                        }
                    }

                    String daoInterfaceName = "I"+beanName+"Dao";
                    String daoImlName = beanName+"DaoImpl";
                    this.createFile(systemPath+"/"+beanFilePath.substring(0,beanFilePath.lastIndexOf("/"))+"/dao",daoInterfaceName,this.CreateDaoInterface(daoInterfaceName,beanName));
                    this.createFile(systemPath+"/"+beanFilePath.substring(0,beanFilePath.lastIndexOf("/"))+"/dao/impl",daoImlName,this.CreateDaoImpl(daoImlName,daoInterfaceName,beanName));
                }
            }
            System.out.println("dao生成生功!");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void createFile(String filePath,String daoName,String daoString) throws IOException {
        File file = new File(filePath);

        if(!file.exists()){
            file.mkdirs();
        }

        File daoFile = new File(file.getAbsolutePath()+ "/"+daoName+".java");

        if(daoFile.exists()){
            daoFile.delete();
        };

        // 写入文件
        BufferedWriter out = new BufferedWriter(new FileWriter(
                daoFile, false));
        out.write(daoString);
        out.close();
    }


    private String CreateDaoInterface(String daoName,String bean){
        StringBuffer sb = new StringBuffer("package " + daoPackage + ";\n\n");
        sb.append("import "+beanPackage+"."+bean+";\n");
        sb.append("/**\n");
        sb.append("* @Date "+ DateUtil.formatDate(new Date(),"yyyy/mm/dd HH:mm:ss")+"\n");
        sb.append("*/\n");
        sb.append("public interface "+daoName+" extends IBaseDao<"+bean+"> {\n");
        sb.append("}");
        return sb.toString();
    }

    private String CreateDaoImpl(String daoName,String daoInterface,String bean){
        StringBuffer sb = new StringBuffer("package " + daoPackage + ".impl;\n\n");
        sb.append("import "+daoPackage.substring(0,daoPackage.lastIndexOf("."))+".common.util.Page;\n");
        sb.append("import "+daoPackage+".BaseDao;\n");
        sb.append("import "+beanPackage+"."+bean+";\n");
        sb.append("import java.io.Serializable;\n");
        sb.append("import "+daoPackage+"."+daoInterface+";\n");
        sb.append("import org.springframework.stereotype.Repository;\n");
        sb.append("/**\n");
        sb.append("* @Date "+ DateUtil.formatDate(new Date(),"yyyy/mm/dd HH:mm:ss")+"\n");
        sb.append("*/\n");
        sb.append("public class "+daoName+" extends BaseDao<"+bean+"> implements "+daoInterface+" {\n");
        sb.append("    @Override\n");
        sb.append("    public boolean save("+bean+" entity) {\n");
        sb.append("        return super.insertEntity(entity);\n");
        sb.append("    }\n\n");
        sb.append("    @Override\n");
        sb.append("    public boolean delete("+bean+" entity) {\n");
        sb.append("        return super.deleteEntity(entity);\n");
        sb.append("    }\n\n");
        sb.append("    @Override\n");
        sb.append("    public boolean delById(Serializable [] ids) {\n");
        sb.append("        return super.deleteById(ids);\n");
        sb.append("    }\n\n");
        sb.append("    @Override\n");
        sb.append("    public boolean update("+bean+" entity) {\n");
        sb.append("        return super.updateEntity(entity);\n");
        sb.append("    }\n\n");
        sb.append("    @Override\n");
        sb.append("    public "+bean+" findById(Serializable id) {\n");
        sb.append("        return super.findEntityById(id);\n");
        sb.append("    }\n\n");
        sb.append("    @Override\n");
        sb.append("    public Page<"+bean+"> findPage(int page, int pageSize) {\n");
        sb.append("        return super.paginate(page,pageSize,null);\n");
        sb.append("    }\n\n");
        sb.append("}");
        return sb.toString();
    }


    public void generation(){
        this.readBean();
    }

    public static void main(String[] args) {
        /*CreateDao dao = new CreateDao("/main/java/com/system");
        dao.generation();*/
        String ss = "/main/java/com/system";
        System.out.println(ss.substring(0,ss.lastIndexOf("/")));
    }


}
