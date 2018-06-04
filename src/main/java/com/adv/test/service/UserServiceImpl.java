package com.adv.test.service;


import com.adv.test.dao.IUserDao;
import com.adv.test.model.DeviceInfoPO;
import com.adv.test.model.UserInfoPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("userService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    public String MD5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(s.getBytes("utf-8"));
            return toHex(bytes);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static String toHex(byte[] bytes) {

        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i=0; i<bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }

    @Override
    public List<UserInfoPO> getUserInfo(Integer length, Integer offset) {
        return userDao.getUserInfo(length, offset);
    }

    @Override
    public Integer getUserInfocount() {
        return userDao.getUserInfocount();
    }

    @Override
    public Integer createUserInfo(UserInfoPO userInfo) {
        userInfo.setPassword(MD5(userInfo.getPassword()));
        Integer id = userDao.createUserInfo(userInfo);
        return id;
    }
    @Override
    public List<UserInfoPO> getUserInfoByinput(String input,String selectType){
        return userDao.getUserInfoByinput( input,selectType );
    }

    @Override
    public Boolean updateUserInfo(UserInfoPO userInfo) {
        return userDao.updateUserInfo(userInfo);
    }

    @Override
    public Integer getUserInfoByName(UserInfoPO userInfo){
        return userDao.getUserInfoByName(userInfo);
    }

    @Override
    public Integer userNameIsExist(UserInfoPO userInfo){
        return userDao.userNameIsExist(userInfo);
    }

    @Override
    public List<UserInfoPO> getUserInfoByDeviceID(DeviceInfoPO deviceInfo){
        return userDao.getUserInfoByDeviceID( deviceInfo );
    }
}