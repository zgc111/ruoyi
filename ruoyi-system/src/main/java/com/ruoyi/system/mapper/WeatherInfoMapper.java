package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.WeatherInfo;

import java.util.List;

/**
 * 百度天气信息Mapper接口
 *
 * @author zgc
 * @date 2020-09-28
 */
public interface WeatherInfoMapper {
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
     * 删除百度天气信息
     *
     * @param id 百度天气信息ID
     * @return 结果
     */
    public int deleteWeatherInfoById(Long id);

    /**
     * 批量删除百度天气信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWeatherInfoByIds(String[] ids);

    /**
     * 根据城市查询数据
     * @param city
     * @return
     */
    List<WeatherInfo> selectByCity(String city);
}
