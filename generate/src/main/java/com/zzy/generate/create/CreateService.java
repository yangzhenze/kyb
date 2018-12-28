package com.zzy.generate.create;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Date;

/**
 * @author zzy
 * @Date 2018/12/22 4:39 PM
 */
public class CreateService {
    protected static final Logger logger = LoggerFactory.getLogger(CreateService.class);

    private String beanFilePath;
    private String systemPath = System.getProperty("user.dir");
    private String beanPackage;
    private String daoPackage;
    private String servicePackage;
    private String inDao;
    public CreateService(){

    }

    public CreateService(String beanFilePath, String inDao){
        this.beanFilePath = beanFilePath;
        this.inDao = inDao;
        beanPackage = this.beanFilePath.substring(this.beanFilePath.indexOf("java")+5,this.beanFilePath.length()).replace("/",".");
        daoPackage = beanPackage.substring(0,beanPackage.lastIndexOf("."))+".dao";
        servicePackage = beanPackage.substring(0,beanPackage.lastIndexOf("."))+".service";
    }
    public CreateService(String beanFilePath){
        this.beanFilePath = beanFilePath;
        beanPackage = this.beanFilePath.substring(this.beanFilePath.indexOf("java")+5,this.beanFilePath.length()).replace("/",".");
        daoPackage = beanPackage.substring(0,beanPackage.lastIndexOf("."))+".dao";
        servicePackage = beanPackage.substring(0,beanPackage.lastIndexOf("."))+".service";
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

                    String id = findId(f);
                    String serviceInterfaceName = "I"+beanName+"Service";
                    String serviceImlName = beanName+"ServiceImpl";
                    this.createFile(systemPath+"/"+beanFilePath.substring(0,beanFilePath.lastIndexOf("/"))+"/service",serviceInterfaceName,this.CreateServiceInterface(serviceInterfaceName,beanName,id));
                    this.createFile(systemPath+"/"+beanFilePath.substring(0,beanFilePath.lastIndexOf("/"))+"/service/impl",serviceImlName,this.CreateDaoImpl(serviceImlName,serviceInterfaceName,"I"+beanName+"Dao",beanName,id));
                }
            }
            logger.info("service生成生功!");
        }catch (IOException e){
            logger.error("service生成失败");
            e.printStackTrace();
        }
    }

    private void createFile(String filePath,String serviceName,String serviceString) throws IOException {
        File file = new File(filePath);

        if(!file.exists()){
            file.mkdirs();
        }

        File daoFile = new File(file.getAbsolutePath()+ "/"+serviceName+".java");

        if(daoFile.exists()){
            daoFile.delete();
        };

        // 写入文件
        BufferedWriter out = new BufferedWriter(new FileWriter(
                daoFile, false));
        out.write(serviceString);
        out.close();
    }


    private String CreateServiceInterface(String serviceName,String bean,String id){
        StringBuffer sb = new StringBuffer("package " + servicePackage + ";\n\n");
        sb.append("import "+beanPackage+"."+bean+";\n");
        sb.append("import com.zzy.generate.util.Page;\n");
        sb.append("/**\n");
        sb.append("* @author zzy\n");
        sb.append("* @Date "+ DateFormatUtils.format(new Date(),"yyyy/mm/dd HH:mm:ss")+"\n");
        sb.append("*/\n");
        sb.append("public interface "+serviceName+" {\n");
        sb.append("    /**\n");
        sb.append("    * 保存\n");
        sb.append("    * @param "+this.toParamsName(bean)+"\n");
        sb.append("    * @return\n");
        sb.append("    */\n");
        sb.append("    public boolean save("+bean+" "+this.toParamsName(bean)+");\n\n");
        if(!StringUtils.isAllEmpty(id)){

            sb.append("    /**\n");
            sb.append("    * 根据id查询\n");
            sb.append("    * @param id\n");
            sb.append("    * @return\n");
            sb.append("    */\n");
            sb.append("    public "+bean+" getById("+id+" id);\n\n");
            sb.append("    /**\n");
            sb.append("    * 根据id删除\n");
            sb.append("    * @return\n");
            sb.append("    */\n");
            sb.append("    public boolean deleteByIds("+id+" [] ids);\n\n");
        }
        sb.append("    /**\n");
        sb.append("    * 更新\n");
        sb.append("    * @param "+this.toParamsName(bean)+"\n");
        sb.append("    * @return\n");
        sb.append("    */\n");
        sb.append("    public boolean update("+bean+" "+this.toParamsName(bean)+");\n\n");
        sb.append("    /**\n");
        sb.append("    * 删除\n");
        sb.append("    * @param "+this.toParamsName(bean)+"\n");
        sb.append("    * @return\n");
        sb.append("    */\n");
        sb.append("    public boolean delete("+bean+" "+this.toParamsName(bean)+");\n\n");
        sb.append("    /**\n");
        sb.append("    * 分页\n");
        sb.append("    * @param page\n");
        sb.append("    * @param pageSize\n");
        sb.append("    * @return\n");
        sb.append("    */\n");
        sb.append("    public Page<"+bean+"> getPage(int page, int pageSize);\n\n");
        sb.append("}");
        return sb.toString();
    }

    private String CreateDaoImpl(String serviceName,String serviceInterface,String daoInterface,String bean,String id){
        StringBuffer sb = new StringBuffer("package " + servicePackage + ".impl;\n\n");
        sb.append("import com.zzy.generate.util.Page;\n");
        sb.append("import "+beanPackage+"."+bean+";\n");
        sb.append("import "+daoPackage+"."+daoInterface+";\n");
        sb.append("import "+servicePackage+"."+serviceInterface+";\n");
        sb.append("import org.springframework.beans.factory.annotation.Autowired;\n");
        sb.append("import org.springframework.stereotype.Service;\n");
        sb.append("/**\n");
        sb.append("* @author zzy\n");
        sb.append("* @Date "+ DateFormatUtils.format(new Date(),"yyyy/mm/dd HH:mm:ss")+"\n");
        sb.append("*/\n");
        sb.append("@Service\n");
        sb.append("public class "+serviceName+" implements "+serviceInterface+" {\n");
        sb.append("    @Autowired\n");
        sb.append("   ").append(daoInterface).append(" "+this.toVariableName(daoInterface)+";\n");
        sb.append("    /**\n");
        sb.append("    * 保存\n");
        sb.append("    */\n");
        sb.append("    @Override\n");
        sb.append("    public boolean save("+bean+" "+this.toParamsName(bean)+") {\n");
        sb.append("        return ").append(this.toVariableName(daoInterface)).append(".save(").append(this.toParamsName(bean)).append(");\n");
        sb.append("    }\n\n");
        if(!StringUtils.isAllEmpty(id)){
            sb.append("    /**\n");
            sb.append("    * 根据id查询\n");
            sb.append("    */\n");
            sb.append("    @Override\n");
            sb.append("    public "+bean+" getById("+id+" id){\n");
            sb.append("        return ").append(this.toVariableName(daoInterface)).append(".findById(id);\n");
            sb.append("    }\n\n");
            sb.append("    /**\n");
            sb.append("    * 根据id删除\n");
            sb.append("    */\n");
            sb.append("    @Override\n");
            sb.append("    public boolean deleteByIds("+id+" [] ids){\n");
            sb.append("        return ").append(this.toVariableName(daoInterface)).append(".delById(ids);\n");
            sb.append("    }\n\n");
        }
        sb.append("    /**\n");
        sb.append("    * 更新\n");
        sb.append("    */\n");
        sb.append("    @Override\n");
        sb.append("    public boolean update("+bean+" "+this.toParamsName(bean)+") {\n");
        sb.append("        return ").append(this.toVariableName(daoInterface)).append(".update(").append(this.toParamsName(bean)).append(");\n");
        sb.append("    }\n\n");
        sb.append("    /**\n");
        sb.append("    * 删除\n");
        sb.append("    */\n");
        sb.append("    @Override\n");
        sb.append("    public boolean delete("+bean+" "+this.toParamsName(bean)+") {\n");
        sb.append("        return ").append(this.toVariableName(daoInterface)).append(".delete(").append(this.toParamsName(bean)).append(");\n");
        sb.append("    }\n\n");
        sb.append("    /**\n");
        sb.append("    * 分页\n");
        sb.append("    */\n");
        sb.append("    @Override\n");
        sb.append("    public Page<"+bean+"> getPage(int page, int pageSize) {\n");
        sb.append("        return ").append(this.toVariableName(daoInterface)).append(".findPage(page, pageSize);\n");
        sb.append("    }\n\n");
        sb.append("}");
        return sb.toString();
    }

    private String findId(File file){
        String id = "";
        try (FileReader reader = new FileReader(file);
             BufferedReader br = new BufferedReader(reader) // 建立一个对象，它把文件内容转成计算机能读懂的语言
        ) {
            String line;
            //网友推荐更加简洁的写法
            while ((line = br.readLine()) != null) {
                // 一次读入一行数据
                if(line.contains("isPK = true")){
                    String idLien = br.readLine();
                    id = idLien.substring(idLien.indexOf("java"),idLien.length());
                    id = id.substring(0,id.lastIndexOf(" "));
                    break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return id;
    }

    private String toParamsName(String bean){
        String first = bean.substring(0,1);
        return first.toLowerCase()+bean.substring(1,bean.length());
    }

    private String toVariableName(String bean){
        bean = bean.substring(1,bean.length());
        String first = bean.substring(0,1);
        return first.toLowerCase()+bean.substring(1,bean.length());
    }


    public void generation(){
        this.readBean();
    }

    public static void main(String[] args) {
        CreateService service = new CreateService("/main/java/com/system");
        /*dao.generation();
        String ss = "/main/java/com/system";*/
        System.out.println(service.toParamsName("FuckName"));
    }


}
