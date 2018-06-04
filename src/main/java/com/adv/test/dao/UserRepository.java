package com.adv.test.dao;

import com.adv.test.model.UDInfoPO;
import com.adv.test.model.UserInfoPO;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<UserInfoPO,Integer> {
    void deleteById(int id);

}
