package com.adv.test.service;

import com.adv.test.dao.IDeviceDao;
import com.adv.test.model.DeviceInfoPO;
import com.adv.test.model.UserInfoPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("deviceService")
public class IDeviceServiceImpl implements IDeviceService{
    @Autowired
    private IDeviceDao deviceDao;
    @Override
    public List<DeviceInfoPO> getDeviceInfo(Integer length, Integer offset){
        return deviceDao.getDeviceInfo(length, offset);
    }

    @Override
    public Integer getDeviceInfocount() {
        return deviceDao.getDeviceInfocount();
    }
    @Override
    public List<DeviceInfoPO> getDeviceInfoByUserID(UserInfoPO userInfo){
        return deviceDao.getDeviceInfoByUserID( userInfo );
    }
    @Override
    public List<DeviceInfoPO> getDeviceInfoByinput(String input,String selectType){
        return deviceDao.getDeviceInfoByinput( input,selectType );
    }
    @Override
    public List<DeviceInfoPO> getDataByUserName(UserInfoPO userInfo){
        return deviceDao.getDataByUserName( userInfo );
    }
}
