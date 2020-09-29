package com.ruoyi.system.service;

import com.ruoyi.system.domain.WeatherInfo;

import java.util.List;

/**
 * 百度天气信息Service接口
 *
 * @author zgc
 * @date 2020-09-28
 */
public interface IWeatherInfoService {
    /**
     * 查询百度天气信息
     *
     * @param id 百度天气信息ID
     * @return 百度天气信息
     */
    public WeatherInfo selectWeatherInfoById(Long id);

    /**
     * 查询百度天气信息列表
     *
     * @param weatherInfo 百度天气信息
     * @return 百度天气信息集合
     */
    public List<WeatherInfo> selectWeatherInfoList(WeatherInfo weatherInfo);

    /**
     * 新增百度天气信息
     *
     * @param weatherInfo 百度天气信息
     * @return 结果
     */
    public int insertWeatherInfo(WeatherInfo weatherInfo);

    /**
     * 修改百度天气信息
     *
     * @param weatherInfo 百度天气信息
     * @return 结果
     */
    public int updateWeatherInfo(WeatherInfo weatherInfo);

    /**
     * 批量删除百度天气信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWeatherInfoByIds(String ids);

    /**
     * 删除百度天气信息信息
     *
     * @param id 百度天气信息ID
     * @return 结果
     */
    public int deleteWeatherInfoById(Long id);

    /**
     * 根据城市查询数据
     * @param city
     * @return
     */
    List<WeatherInfo> selectByCity(String city);
}
