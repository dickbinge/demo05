package com.adv.test.dao;

import com.adv.test.model.DeviceInfoPO;
import com.adv.test.model.UserInfoPO;

import java.util.List;
import java.util.Map;

/**
 * Created by xiao.bo on 2018/2/8.
 */
public interface IUserDao {

    List<UserInfoPO> getUserInfo(Integer length, Integer offset);
    Integer getUserInfocount();
    Integer getUserInfoByName(UserInfoPO userInfo);
    Integer createUserInfo(UserInfoPO userInfo);

    Boolean deleteUser(UserInfoPO userInfo);

    Boolean updateUserInfo(UserInfoPO userInfo);
    Integer userNameIsExist(UserInfoPO userInfo);
    List<UserInfoPO> getUserInfoByDeviceID(DeviceInfoPO deviceInfo); //根据设备信息展示当前其上分配的用户
    List<UserInfoPO> getUserInfoByinput(String input,String selectType);
}
