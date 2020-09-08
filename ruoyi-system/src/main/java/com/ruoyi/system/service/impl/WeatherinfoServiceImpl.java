package com.ruoyi.system.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WeatherinfoMapper;
import com.ruoyi.system.domain.Weatherinfo;
import com.ruoyi.system.service.IWeatherinfoService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 天气信息Service业务层处理
 *
 * @author ruoyi
 * @date 2020-09-07
 */
@Service
public class WeatherinfoServiceImpl implements IWeatherinfoService {
    @Autowired
    private WeatherinfoMapper weatherinfoMapper;

    /**
     * 查询天气信息
     *
     * @param id 天气信息ID
     * @return 天气信息
     */
    @Override
    public Weatherinfo selectWeatherinfoById(Long id) {
        return weatherinfoMapper.selectWeatherinfoById(id);
    }

    /**
     * 查询天气信息列表
     *
     * @param weatherinfo 天气信息
     * @return 天气信息
     */
    @Override
    public List<Weatherinfo> selectWeatherinfoList(Weatherinfo weatherinfo) {
        return weatherinfoMapper.selectWeatherinfoList(weatherinfo);
    }

    /**
     * 新增天气信息
     *
     * @param weatherinfo 天气信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertWeatherinfo(Weatherinfo weatherinfo) {
        return weatherinfoMapper.insertWeatherinfo(weatherinfo);
    }

    /**
     * 修改天气信息
     *
     * @param weatherinfo 天气信息
     * @return 结果
     */
    @Override
    public int updateWeatherinfo(Weatherinfo weatherinfo) {
        return weatherinfoMapper.updateWeatherinfo(weatherinfo);
    }

    /**
     * 删除天气信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWeatherinfoByIds(String ids) {
        return weatherinfoMapper.deleteWeatherinfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除天气信息信息
     *
     * @param id 天气信息ID
     * @return 结果
     */
    @Override
    public int deleteWeatherinfoById(Long id) {
        return weatherinfoMapper.deleteWeatherinfoById(id);
    }

    /**
     * 根据最新请求查询天气数据
     *
     * @return
     */
    @Override
    public Long selectWeatherinfoOne() {
        Long id = weatherinfoMapper.selectWeatherinfoOne();
        if (id == null) {
            return null;
        }

        return id;
    }

}
