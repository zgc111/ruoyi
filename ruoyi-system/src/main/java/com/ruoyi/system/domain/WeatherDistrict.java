package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 全国城市代码对象 weather_district
 *
 * @author ruoyi
 * @date 2020-09-28
 */
public class WeatherDistrict extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 行政区域编码
     */
    @Excel(name = "行政区域编码")
    private String districtId;

    /**
     * 省份名
     */
    @Excel(name = "省份名")
    private String province;

    /**
     * 城市名
     */
    @Excel(name = "城市名")
    private String city;

    /**
     * 城市编码
     */
    @Excel(name = "城市编码")
    private String cityGeocode;

    /**
     * 行政区域名
     */
    @Excel(name = "行政区域名")
    private String district;

    /**
     * 区域编码
     */
    @Excel(name = "区域编码")
    private String districtGeocode;

    /**
     * 经纬度x
     */
    @Excel(name = "经纬度x")
    private String lon;

    /**
     * 经纬度y
     */
    @Excel(name = "经纬度y")
    private String lat;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvince() {
        return province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCityGeocode(String cityGeocode) {
        this.cityGeocode = cityGeocode;
    }

    public String getCityGeocode() {
        return cityGeocode;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrictGeocode(String districtGeocode) {
        this.districtGeocode = districtGeocode;
    }

    public String getDistrictGeocode() {
        return districtGeocode;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLon() {
        return lon;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLat() {
        return lat;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("districtId", getDistrictId())
                .append("province", getProvince())
                .append("city", getCity())
                .append("cityGeocode", getCityGeocode())
                .append("district", getDistrict())
                .append("districtGeocode", getDistrictGeocode())
                .append("lon", getLon())
                .append("lat", getLat())
                .toString();
    }
}
