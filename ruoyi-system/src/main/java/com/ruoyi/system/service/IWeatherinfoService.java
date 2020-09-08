package com.ruoyi.system.service;

import com.ruoyi.system.domain.Weatherinfo;

import java.util.Date;
import java.util.List;

/**
 * 天气信息Service接口
 * 
 * @author ruoyi
 * @date 2020-09-07
 */
public interface IWeatherinfoService 
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
     * 批量删除天气信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWeatherinfoByIds(String ids);

    /**
     * 删除天气信息信息
     * 
     * @param id 天气信息ID
     * @return 结果
     */
    public int deleteWeatherinfoById(Long id);

    public Long selectWeatherinfoOne();

}
