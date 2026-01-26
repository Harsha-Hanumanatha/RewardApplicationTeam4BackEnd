package com.tcs.rewardapplicationsys.repository;

import com.tcs.rewardapplicationsys.entity.CesUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CesUserRepo extends JpaRepository<CesUser,Integer> {
    public void deleteByUserName(String username);
    public CesUser findByUserName(String username);

}
