package com.demo.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.model.User;

public interface UserRepository extends JpaRepository<User,Long>{

}
