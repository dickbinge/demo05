package com.adv.test.controller;

import com.adv.test.controller.apidata.PageModel;
import com.adv.test.model.DeviceInfoPO;
import com.adv.test.model.UserInfoPO;
import com.adv.test.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("v1.0/device")
public class DeviceInfoController {
    @Autowired
    private IDeviceService deviceService;

    /**
     * 获取当前所有设备信息列表
     * @param length
     * @param offset
     * @return
     */
    @RequestMapping(value="/info",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getDeviceInfo(
            @RequestParam(value = "length", defaultValue = "50") Integer length,
            @RequestParam(value = "offset", defaultValue = "0") Integer offset ) {
        System.out.println("getDeviceInfo");
        ResponseEntity<?> respEntity = null;
        List<DeviceInfoPO> deviceInfoList = deviceService.getDeviceInfo(length,offset);
        int total_count = deviceService.getDeviceInfocount();
        PageModel obj = new PageModel();
        obj.setData(deviceInfoList);
        obj.setTotalCount(total_count);
        respEntity = new ResponseEntity<>(obj, HttpStatus.OK);
        return respEntity;
    }

    /**
     * 通过用户ID来获取其对应分配的设备
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/infoByUserId",method =RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> getDeviceInfoByUserId(@RequestBody UserInfoPO userInfo){
        ResponseEntity<?> respEntity = null;
        List<DeviceInfoPO> deviceInfoList=deviceService.getDeviceInfoByUserID( userInfo);
        respEntity = new ResponseEntity<>(deviceInfoList, HttpStatus.OK);
        return respEntity;
    }

    /**
     * 模糊搜索设备
     * @param str
     * @return
     */
    @RequestMapping(value="/infoInput",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<?> getDeviceInfoByInput(@RequestBody(required=false)String str) {
        System.out.println("getDevice");
        ResponseEntity<?> respEntity = null;
        String input=str.split( ";" )[0];
        String selectType=str.split( ";" )[1];
        List<DeviceInfoPO> userInfoList = deviceService.getDeviceInfoByinput(input,selectType);
        int total_count = deviceService.getDeviceInfocount();
        PageModel obj = new PageModel();
        obj.setData(userInfoList);
        obj.setTotalCount(total_count);
        respEntity = new ResponseEntity<>(obj, HttpStatus.OK);
        return respEntity;
    }

    /**
     * 通过用户ID来获取其对应分配的设备
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/infoByUserName",method =RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> getDataByUserName(@RequestBody UserInfoPO userInfo){
        ResponseEntity<?> respEntity = null;
        List<DeviceInfoPO> deviceInfoList=deviceService.getDataByUserName( userInfo);
        respEntity = new ResponseEntity<>(deviceInfoList, HttpStatus.OK);
        return respEntity;
    }
}
