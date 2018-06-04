package com.adv.test.dao;


import com.adv.test.model.DeviceInfoPO;
import com.adv.test.model.UserInfoPO;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository("deviceDao")
@Transactional
public  class DeviceDaoImpl extends AbstractDao<Integer, DeviceInfoPO> implements IDeviceDao {

    @Override
    public List<DeviceInfoPO> getDeviceInfo(Integer length, Integer offset){
        try {
            String hql = String.format("from DeviceInfoPO");
            return queryForPage(hql,offset,length);
        }
        catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Integer getDeviceInfocount(){
        try {
            String hql = String.format("from DeviceInfoPO");
            return getCount(hql);
        }
        catch (Exception e) {
            throw e;
        }
    }
    @Override
    public  List<DeviceInfoPO> getDeviceInfoByUserID(UserInfoPO userInfo){
        try {
            String hql = String.format("select d.id,d.devicename,d.description,d.type,d.number from DeviceInfoPO d,UDInfoPO ud where d.id=ud.deviceid and ud.userid='%s' ",userInfo.getId());
            List<DeviceInfoPO> list=query(hql);
            return list;
        }
        catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<DeviceInfoPO> getDeviceInfoByinput(String input,String selectType){
        try{
            String hql=null;
            switch (selectType){
                case "1": hql=String.format( "from DeviceInfoPO where devicename like '%s'",'%' + input + '%' );
                    break;    //通过用户名进行模糊查询
                case "2": hql=  String.format( "from DeviceInfoPO where id='%s'",input );
                    break;   //通过ID查询
            }

            List<DeviceInfoPO> list=query(hql);
            return list;
        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public List<DeviceInfoPO> getDataByUserName(UserInfoPO userInfo){
        try {
            String hql = String.format("select d.id,d.devicename,d.description,d.type,d.number from DeviceInfoPO d,UDInfoPO ud " +
                    "where d.id=ud.deviceid and ud.userid in(select id from UserInfoPO where username='%s') ",userInfo.getUsername());
            List<DeviceInfoPO> list=query(hql);
            return list;
        }
        catch (Exception e) {
            throw e;
        }
    }
}
