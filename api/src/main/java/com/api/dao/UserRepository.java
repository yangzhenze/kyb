package com.api.dao;

import com.api.bean.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User,String>  {

}
