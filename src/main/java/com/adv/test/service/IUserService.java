package com.adv.test.service;


import com.adv.test.model.DeviceInfoPO;
import com.adv.test.model.UserInfoPO;

import java.util.List;

public interface  IUserService {

    List<UserInfoPO> getUserInfo(Integer length, Integer offset);
    Integer getUserInfocount();
    Integer createUserInfo(UserInfoPO userInfo);
    Boolean updateUserInfo(UserInfoPO userInfo);
    Integer getUserInfoByName(UserInfoPO userInfo);
    Integer userNameIsExist(UserInfoPO userInfo);
    List<UserInfoPO> getUserInfoByinput(String input,String selectType);
    List<UserInfoPO> getUserInfoByDeviceID(DeviceInfoPO deviceInfo);
}
