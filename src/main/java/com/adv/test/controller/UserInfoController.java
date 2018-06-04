package com.adv.test.controller;


import com.adv.test.controller.apidata.PageModel;
import com.adv.test.dao.UserRepository;
import com.adv.test.model.DeviceInfoPO;
import com.adv.test.model.UserInfoPO;
import com.adv.test.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("v1.0/user")
public class UserInfoController {

    @Autowired
    private IUserService userService;
    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private IUserDeviceDao udService;
    @RequestMapping(value="/info",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getUserInfo(
            @RequestParam(value = "length", defaultValue = "50") Integer length,
            @RequestParam(value = "offset", defaultValue = "0") Integer offset ) {
        System.out.println("getUserInfo");
        ResponseEntity<?> respEntity = null;
        List<UserInfoPO> userInfoList = userService.getUserInfo(length,offset);
        int total_count = userService.getUserInfocount();
        PageModel obj = new PageModel();
        obj.setData(userInfoList);
        obj.setTotalCount(total_count);
        respEntity = new ResponseEntity<>(obj, HttpStatus.OK);
        return respEntity;
    }

    @RequestMapping(value="/infoInput",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<?> getUserInfoByInput(@RequestBody(required=false)String str) {
        System.out.println("getUserInfo");
        ResponseEntity<?> respEntity = null;
        String input=str.split( ";" )[0];
        String selectType=str.split( ";" )[1];
        List<UserInfoPO> userInfoList = userService.getUserInfoByinput(input,selectType);
        int total_count = userService.getUserInfocount();
        PageModel obj = new PageModel();
        obj.setData(userInfoList);
        obj.setTotalCount(total_count);
        respEntity = new ResponseEntity<>(obj, HttpStatus.OK);
        return respEntity;
    }

    @RequestMapping(value="/info",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> createUserInfo(
            @RequestBody UserInfoPO userInfo
    ){
        System.out.println("createUserInfo");
        ResponseEntity<?> respEntity = null;

        Integer id = userService.createUserInfo(userInfo);
        respEntity = new ResponseEntity<>(new HashMap() {{put("id", id);}}, HttpStatus.OK);

        return respEntity;
    }

    @RequestMapping(value="/info",method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteUserInfo(
            @RequestParam int id
    ){
        try{
            userRepository.delete( id );
            return "success";
        }catch (Exception ex){
            throw ex;
        }
    }

    @RequestMapping(value="/batchRemove",method = RequestMethod.DELETE)
    @ResponseBody
    public Integer batchdeleteUserInfo(
            @RequestParam String ids
    ){
        try{
            int Count=0;

            String[] idarr=ids.split( ";" );

            if(idarr.length!=0) {
                for(int i=0;i<idarr.length;i++) {
                    userRepository.delete(Integer.parseInt( idarr[i]) ); //执行批量删除
                    Count++;
                }
            }
            return Count;
        }catch (Exception ex){
            throw ex;
        }
    }

    @RequestMapping(value="/info",method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> updateUserInfo(
            @RequestBody UserInfoPO userInfo
    ){
        System.out.println("updateUserInfo");
        ResponseEntity<?> respEntity = null;
        Boolean bool = userService.updateUserInfo(userInfo);
        respEntity = new ResponseEntity<>(new HashMap() {{put("result", bool);}}, HttpStatus.OK);

        return respEntity;
    }

    @RequestMapping(value="/login",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> getUserInfoByName(
            @RequestBody UserInfoPO userInfo
    ){
        System.out.println("getUserInfoByName");
        ResponseEntity<?> respEntity = null;
        Integer result = userService.getUserInfoByName(userInfo);
        respEntity = new ResponseEntity<>(new HashMap() {{put("result", result);}}, HttpStatus.OK);
        return respEntity;
    }

    @RequestMapping(value="/addcount",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> userNameIsExist(
            @RequestBody UserInfoPO userInfo
    ){
        System.out.println("userNameIsExist");
        ResponseEntity<?> respEntity = null;
        Integer result = userService.userNameIsExist(userInfo);
        respEntity = new ResponseEntity<>(new HashMap() {{put("result", result);}}, HttpStatus.OK);
        return respEntity;
    }
    @RequestMapping(value = "/allotUser",method =RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> allotUser(@RequestBody DeviceInfoPO deviceInfo){
        ResponseEntity<?> respEntity = null;
        List<UserInfoPO> userInfoList=userService.getUserInfoByDeviceID( deviceInfo);
        respEntity = new ResponseEntity<>( userInfoList, HttpStatus.OK);
        return respEntity;
    }

//    @RequestMapping(value="/allot",method = RequestMethod.POST)
//    @ResponseBody
//    public Integer allotDevice(@RequestBody String str) {
//        System.out.println("allotDevice");
//        try {
//            int count=0;
//            String[] strArry = str.split( ";" );
//            String deviceID = strArry[0];
//            String[] userIDArr = strArry[1].split( "&" );
//            for (int i = 0; i < userIDArr.length; i++) {
//                UDInfoPO[] udInfoPOS = new UDInfoPO[userIDArr.length];
//                udInfoPOS[i].setDeviceid( deviceID );
//                udInfoPOS[i].setUserid( Integer.parseInt( userIDArr[i] ) );
//                udService.save( udInfoPOS[i] );
//                System.out.print( udInfoPOS[i].getUserid() );
//                System.out.print( udInfoPOS[i].getDeviceid() );
//                count++;
//            }
//            return count;
//        }catch (Exception ex){
//            throw ex;
//        }
//    }

}
