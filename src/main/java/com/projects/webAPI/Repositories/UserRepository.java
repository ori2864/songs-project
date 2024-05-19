package com.projects.webAPI.Repositories;

import com.projects.webAPI.Beans.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDetails,Integer> {

}
