package com.dce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * desc 定位信息
 * @author Administrator
 */
@Entity
public class Location {

	@Id
    @GeneratedValue
    private long id;
	 @Column(nullable = false)
	private String province;
	 @Column(nullable = false)
	private String city;
	 @Column(nullable = false)
	private String city_code;
	 @Column(nullable = false)
	private String point_x;
	 @Column(nullable = false)
	private String point_y;
	 @Column(nullable = false)
	private String addr;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCity_code() {
		return city_code;
	}
	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}
	public String getPoint_x() {
		return point_x;
	}
	public void setPoint_x(String point_x) {
		this.point_x = point_x;
	}
	public String getPoint_y() {
		return point_y;
	}
	public void setPoint_y(String point_y) {
		this.point_y = point_y;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	 
	 @Override
	public String toString() {
		return this.getId()+" "+this.getAddr()+" "+this.getProvince()+" "+this.getCity()+" "+this.getCity_code()+" "+this.getPoint_x()+" "+this.getPoint_y()+"".toString();
	}
	
}
