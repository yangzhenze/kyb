package com.system;

import com.system.bean.Admin;
import com.system.dao.IDictionaryDao;
import com.system.service.IDictionaryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class SystemAdminApplicationTests {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	IDictionaryService dictionaryService;

	@Test
	public void contextLoads() {
		this.jdbcTemplateTest();
		//this.transactionalTest();
	}

	/**
	 * jdbcTemplate测试
	 */
	private void jdbcTemplateTest(){
		List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from sys_admin where id = ?",3);
		jdbcTemplate.queryForList("select * from sys_admin where id = ?",3);
		System.out.println("jdbcTemplate===list===Map:");
		list.forEach(x -> {
			System.out.println(x);
		});

		RowMapper<Admin> rowMapper = BeanPropertyRowMapper.newInstance(Admin.class);

		List<Admin> list1 = jdbcTemplate.query("select * from sys_admin where id = ?",rowMapper,3);

		System.out.println("jdbcTemplate===list===bean:");
		list1.forEach(x -> {
			System.out.println(x);
		});

		//返回结果必须为1条
		Map<String,Object> map = jdbcTemplate.queryForMap("select * from sys_admin where id = ?",3);
		System.out.println("jdbcTemplate===map:");
		System.out.println(map);

		//返回结果必须为1条
		Admin admin = jdbcTemplate.queryForObject("select * from sys_admin where id = ?",rowMapper,3);
		System.out.println("jdbcTemplate===bean:");
		System.out.println(admin);
	}



	/**
	 * 事务测试
	 */
	private void transactionalTest(){
		dictionaryService.delete(new Integer[]{15});
	}

}
