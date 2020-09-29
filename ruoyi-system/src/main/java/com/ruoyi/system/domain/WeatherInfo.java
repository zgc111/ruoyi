package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 百度天气信息对象 weather_info
 *
 * @author zgc
 * @date 2020-09-28
 */
public class WeatherInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 国家名称
     */
    @Excel(name = "国家名称")
    private String country;

    /**
     * 省份名称
     */
    @Excel(name = "省份名称")
    private String province;

    /**
     * 城市名称
     */
    @Excel(name = "城市名称")
    private String city;

    /**
     * 区县名称
     */
    @Excel(name = "区县名称")
    private String name;

    /**
     * 区县id
     */
    @Excel(name = "区县id")
    private String cId;

    /**
     * 温度（℃）
     */
    @Excel(name = "温度", readConverterExp = "℃=")
    private Integer temp;

    /**
     * 体感温度(℃)
     */
    @Excel(name = "体感温度(℃)")
    private Integer feelsLike;

    /**
     * 相对湿度(%)
     */
    @Excel(name = "相对湿度(%)")
    private Integer rh;

    /**
     * 风力等级
     */
    @Excel(name = "风力等级")
    private String windClass;

    /**
     * 风向描述
     */
    @Excel(name = "风向描述")
    private String windDir;

    /**
     * 天气现象
     */
    @Excel(name = "天气现象")
    private String text;

    /**
     * 日期
     */
    @Excel(name = "日期")
    private String date;

    /**
     * 星期
     */
    @Excel(name = "星期")
    private String week;

    /**
     * 最高温度(℃)
     */
    @Excel(name = "最高温度(℃)")
    private Integer high;

    /**
     * 最低温度(℃)
     */
    @Excel(name = "最低温度(℃)")
    private Integer low;

    /**
     * 白天风力
     */
    @Excel(name = "白天风力")
    private String wcDay;

    /**
     * 晚上风力
     */
    @Excel(name = "晚上风力")
    private String wcNight;

    /**
     * 白天风向
     */
    @Excel(name = "白天风向")
    private String wdDay;

    /**
     * 晚上风向
     */
    @Excel(name = "晚上风向")
    private String wdNight;

    /**
     * 白天天气现象
     */
    @Excel(name = "白天天气现象")
    private String textDay;

    /**
     * 晚上天气现象
     */
    @Excel(name = "晚上天气现象")
    private String textNight;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getcId() {
        return cId;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setFeelsLike(Integer feelsLike) {
        this.feelsLike = feelsLike;
    }

    public Integer getFeelsLike() {
        return feelsLike;
    }

    public void setRh(Integer rh) {
        this.rh = rh;
    }

    public Integer getRh() {
        return rh;
    }

    public void setWindClass(String windClass) {
        this.windClass = windClass;
    }

    public String getWindClass() {
        return windClass;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getWeek() {
        return week;
    }

    public void setHigh(Integer high) {
        this.high = high;
    }

    public Integer getHigh() {
        return high;
    }

    public void setLow(Integer low) {
        this.low = low;
    }

    public Integer getLow() {
        return low;
    }

    public void setWcDay(String wcDay) {
        this.wcDay = wcDay;
    }

    public String getWcDay() {
        return wcDay;
    }

    public void setWcNight(String wcNight) {
        this.wcNight = wcNight;
    }

    public String getWcNight() {
        return wcNight;
    }

    public void setWdDay(String wdDay) {
        this.wdDay = wdDay;
    }

    public String getWdDay() {
        return wdDay;
    }

    public void setWdNight(String wdNight) {
        this.wdNight = wdNight;
    }

    public String getWdNight() {
        return wdNight;
    }

    public void setTextDay(String textDay) {
        this.textDay = textDay;
    }

    public String getTextDay() {
        return textDay;
    }

    public void setTextNight(String textNight) {
        this.textNight = textNight;
    }

    public String getTextNight() {
        return textNight;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("country", getCountry())
                .append("province", getProvince())
                .append("city", getCity())
                .append("name", getName())
                .append("cId", getcId())
                .append("temp", getTemp())
                .append("feelsLike", getFeelsLike())
                .append("rh", getRh())
                .append("windClass", getWindClass())
                .append("windDir", getWindDir())
                .append("text", getText())
                .append("date", getDate())
                .append("week", getWeek())
                .append("high", getHigh())
                .append("low", getLow())
                .append("wcDay", getWcDay())
                .append("wcNight", getWcNight())
                .append("wdDay", getWdDay())
                .append("wdNight", getWdNight())
                .append("textDay", getTextDay())
                .append("textNight", getTextNight())
                .toString();
    }
}
