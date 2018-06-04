package com.adv.test.service;

import com.adv.test.dao.IDeviceDao;
import com.adv.test.model.DeviceInfoPO;
import com.adv.test.model.UserInfoPO;

import java.util.List;

public interface IDeviceService {
    List<DeviceInfoPO> getDeviceInfo(Integer length, Integer offset);  //查出要展示的设备
    Integer getDeviceInfocount();
    List<DeviceInfoPO> getDeviceInfoByUserID(UserInfoPO userInfo);    //根据用户查出其被分配的设备
    List<DeviceInfoPO> getDeviceInfoByinput(String input,String selectType);
    List<DeviceInfoPO> getDataByUserName(UserInfoPO userInfo);
}
