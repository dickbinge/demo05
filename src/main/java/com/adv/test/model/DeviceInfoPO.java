package com.adv.test.model;


import javax.persistence.*;

@Entity
@Table(name="device_info")
public class DeviceInfoPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "devicename")
    private String devicename;
    @Column(name = "description")
    private String description;
    @Column(name = "type")
    private  Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Column(name = "number")
    private Integer number;
}
