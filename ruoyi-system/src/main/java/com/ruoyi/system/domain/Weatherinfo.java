package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 天气信息对象 weatherinfo
 *
 * @author ruoyi
 * @date 2020-09-07
 */
public class Weatherinfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 省份名
     */
    @Excel(name = "省份名 ")
    private String province;

    /**
     * 城市名
     */
    @Excel(name = "城市名")
    private String city;

    /**
     * 区域编码
     */
    @Excel(name = " 区域编码")
    private String adcode;

    /**
     * 天气现象（汉字描述）
     */
    @Excel(name = "天气现象", readConverterExp = "汉=字描述")
    private String weather;

    /**
     * 实时气温，单位：摄氏度
     */
    @Excel(name = "实时气温，单位：摄氏度")
    private String temperature;

    /**
     * 风向描述
     */
    @Excel(name = "风向描述")
    private String winddirection;

    /**
     * 风力级别，单位：级
     */
    @Excel(name = " 风力级别，单位：级")
    private String windpower;

    /**
     * 空气湿度
     */
    @Excel(name = "空气湿度")
    private String humidity;

    /**
     * 数据发布的时间
     */
    @Excel(name = " 数据发布的时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date reporttime;

    @Excel(name = " 用户请求时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWeather() {
        return weather;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setWinddirection(String winddirection) {
        this.winddirection = winddirection;
    }

    public String getWinddirection() {
        return winddirection;
    }

    public void setWindpower(String windpower) {
        this.windpower = windpower;
    }

    public String getWindpower() {
        return windpower;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setReporttime(Date reporttime) {
        this.reporttime = reporttime;
    }

    public Date getReporttime() {
        return reporttime;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("province", getProvince())
                .append("city", getCity())
                .append("adcode", getAdcode())
                .append("weather", getWeather())
                .append("temperature", getTemperature())
                .append("winddirection", getWinddirection())
                .append("windpower", getWindpower())
                .append("humidity", getHumidity())
                .append("reporttime", getReporttime())
                .append("createTime", getCreateTime())
                .toString();
    }
}
