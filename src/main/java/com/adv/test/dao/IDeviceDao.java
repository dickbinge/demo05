package com.adv.test.dao;


import com.adv.test.model.DeviceInfoPO;
import com.adv.test.model.UserInfoPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IDeviceDao {
    List<DeviceInfoPO> getDeviceInfo(Integer length, Integer offset);  //查出要展示的设备
    Integer getDeviceInfocount();
    List<DeviceInfoPO> getDeviceInfoByUserID(UserInfoPO userInfo);    //根据用户查出其被分配的设备
    List<DeviceInfoPO> getDeviceInfoByinput(String input,String selectType);
    List<DeviceInfoPO> getDataByUserName(UserInfoPO userInfo);   //普通用户页面进行设备的加载
}
