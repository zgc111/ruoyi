package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Weatherinfo;

import java.util.Date;
import java.util.List;

/**
 * 天气信息Mapper接口
 * 
 * @author ruoyi
 * @date 2020-09-07
 */
public interface WeatherinfoMapper 
{
    /**
     * 查询天气信息
     * 
     * @param id 天气信息ID
     * @return 天气信息
     */
    public Weatherinfo selectWeatherinfoById(Long id);

    /**
     * 查询天气信息列表
     * 
     * @param weatherinfo 天气信息
     * @return 天气信息集合
     */
    public List<Weatherinfo> selectWeatherinfoList(Weatherinfo weatherinfo);

    /**
     * 新增天气信息
     * 
     * @param weatherinfo 天气信息
     * @return 结果
     */
    public int insertWeatherinfo(Weatherinfo weatherinfo);

    /**
     * 修改天气信息
     * 
     * @param weatherinfo 天气信息
     * @return 结果
     */
    public int updateWeatherinfo(Weatherinfo weatherinfo);

    /**
     * 删除天气信息
     * 
     * @param id 天气信息ID
     * @return 结果
     */
    public int deleteWeatherinfoById(Long id);

    /**
     * 批量删除天气信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWeatherinfoByIds(String[] ids);

    /**
     * 根据最新请求时间查询天气数据
     * @return
     */
    public Long selectWeatherinfoOne();

}
