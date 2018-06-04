package com.adv.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="user_device")
public class UDInfoPO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "userid")
    private Integer userid;

    @Id
    @Column(name = "deviceid")
    private String deviceid;
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

}
