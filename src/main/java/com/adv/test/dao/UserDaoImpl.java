package com.adv.test.dao;

import com.adv.test.model.DeviceInfoPO;
import com.adv.test.model.UserInfoPO;
import com.adv.test.service.UserServiceImpl;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Repository("userDao")
@Transactional
public class UserDaoImpl extends AbstractDao<Integer, UserInfoPO> implements IUserDao {

    @Override

    public List<UserInfoPO> getUserInfo(Integer length, Integer offset) {
        try {
            String hql = String.format("from UserInfoPO where id <> %d",0);
            return queryForPage(hql,offset,length);
        }
        catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Integer getUserInfocount() {
        try {
            String hql = String.format("from UserInfoPO");
            return getCount(hql);
        }
        catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Integer createUserInfo(UserInfoPO userInfo) {
        persist(userInfo);
        return userInfo.getId();
    }

    @Override
    public Boolean deleteUser(UserInfoPO userInfo) {
        try {
            String hql = String.format("FROM UserInfoPO where id = '%d'",userInfo.getId());
            UserInfoPO userInfoPO = queryForPage(hql, -1, -1).get(0);
            delete(userInfoPO);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateUserInfo(UserInfoPO userInfo) {
        try {
            //获取密码
            String hql = String.format("FROM UserInfoPO where id = '%d'",userInfo.getId());
            UserInfoPO userInfoPO = queryForPage(hql, -1, -1).get(0);
            userInfo.setPassword(userInfoPO.getPassword());

            UserInfoPO userInfo0 = merge(userInfo);
            System.out.println(userInfo0);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    //登陆的时候进行校验，如果返回值1，登陆成功，返回值为0，输入的用户名跟密码错误
    @Override
    public Integer getUserInfoByName(UserInfoPO userInfo){
        UserServiceImpl us=new UserServiceImpl();
        try {
            String hql = String.format("from UserInfoPO where username='%s' and password='%s'",
                    userInfo.getUsername(),us.MD5(userInfo.getPassword()));
            System.out.print(getCount(hql));
            return getCount(hql);
        }
        catch (Exception e) {
            throw e;
        }
    }

    //添加账户的时候判断当前用户是否已经存在
    @Override
    public Integer userNameIsExist(UserInfoPO userInfo){
        try {
            String hql = String.format("from UserInfoPO where username='%s'",userInfo.getUsername());
            System.out.print(getCount(hql));
            return getCount(hql); //大于0表示已存在
        }
        catch (Exception e) {
            throw e;
        }
    }
    @Override
    public List<UserInfoPO> getUserInfoByinput(String input,String selectType){
        try{
            String hql=null;
            switch (selectType){
                case "1": hql=String.format( "from UserInfoPO where username like '%s'",'%' + input + '%' );
                    break;    //通过用户名进行模糊查询
                case "2": hql=  String.format( "from UserInfoPO where id='%s'",input );
                    break;   //通过ID查询
            }

            List<UserInfoPO> list=query(hql);
            return list;
        }catch (Exception ex){
            throw ex;
        }
    }
   @Override
    public List<UserInfoPO> getUserInfoByDeviceID(DeviceInfoPO deviceInfo){
        try{
            StringBuilder sbhql=new StringBuilder("select distinct u.id ,u.username,u.phone,u.email from UserInfoPO u ,UDInfoPO ud");
            String hql=String.format(" where u.id not in(select ud.userid from" +
                    " UDInfoPO ud where ud.deviceid=%s)",deviceInfo.getId() );
            sbhql.append(hql);
            sbhql.append( " and u.id<>0 order by u.id" );  //除去管理员
            List<UserInfoPO> list=query(sbhql.toString());
            return list;
        }catch (Exception ex){
            throw ex;
        }
    }
}

