package com.ruoyi.system.service;

import com.ruoyi.system.domain.WeatherDistrict;

import java.util.List;

/**
 * 全国城市代码Service接口
 *
 * @author ruoyi
 * @date 2020-09-28
 */
public interface IWeatherDistrictService {
    /**
     * 查询全国城市代码
     *
     * @param id 全国城市代码ID
     * @return 全国城市代码
     */
    public WeatherDistrict selectWeatherDistrictById(Long id);

    /**
     * 查询全国城市代码列表
     *
     * @param weatherDistrict 全国城市代码
     * @return 全国城市代码集合
     */
    public List<WeatherDistrict> selectWeatherDistrictList(WeatherDistrict weatherDistrict);

    /**
     * 新增全国城市代码
     *
     * @param weatherDistrict 全国城市代码
     * @return 结果
     */
    public int insertWeatherDistrict(WeatherDistrict weatherDistrict);

    /**
     * 修改全国城市代码
     *
     * @param weatherDistrict 全国城市代码
     * @return 结果
     */
    public int updateWeatherDistrict(WeatherDistrict weatherDistrict);

    /**
     * 批量删除全国城市代码
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWeatherDistrictByIds(String ids);

    /**
     * 删除全国城市代码信息
     *
     * @param id 全国城市代码ID
     * @return 结果
     */
    public int deleteWeatherDistrictById(Long id);

    /**
     * 导入全国城市数据
     */
    public String importWeatherDistrict(List<WeatherDistrict> weatherDistrictList);
}
