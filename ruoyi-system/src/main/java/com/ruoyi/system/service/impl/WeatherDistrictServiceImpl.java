package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WeatherDistrictMapper;
import com.ruoyi.system.domain.WeatherDistrict;
import com.ruoyi.system.service.IWeatherDistrictService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 全国城市代码Service业务层处理
 *
 * @author ruoyi
 * @date 2020-09-28
 */
@Service
public class WeatherDistrictServiceImpl implements IWeatherDistrictService {
    @Autowired
    private WeatherDistrictMapper weatherDistrictMapper;

    /**
     * 查询全国城市代码
     *
     * @param id 全国城市代码ID
     * @return 全国城市代码
     */
    @Override
    public WeatherDistrict selectWeatherDistrictById(Long id) {
        return weatherDistrictMapper.selectWeatherDistrictById(id);
    }

    /**
     * 查询全国城市代码列表
     *
     * @param weatherDistrict 全国城市代码
     * @return 全国城市代码
     */
    @Override
    public List<WeatherDistrict> selectWeatherDistrictList(WeatherDistrict weatherDistrict) {
        return weatherDistrictMapper.selectWeatherDistrictList(weatherDistrict);
    }

    /**
     * 新增全国城市代码
     *
     * @param weatherDistrict 全国城市代码
     * @return 结果
     */
    @Override
    @Transactional
    public int insertWeatherDistrict(WeatherDistrict weatherDistrict) {
        return weatherDistrictMapper.insertWeatherDistrict(weatherDistrict);
    }

    /**
     * 修改全国城市代码
     *
     * @param weatherDistrict 全国城市代码
     * @return 结果
     */
    @Override
    public int updateWeatherDistrict(WeatherDistrict weatherDistrict) {
        return weatherDistrictMapper.updateWeatherDistrict(weatherDistrict);
    }

    /**
     * 删除全国城市代码对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWeatherDistrictByIds(String ids) {
        return weatherDistrictMapper.deleteWeatherDistrictByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除全国城市代码信息
     *
     * @param id 全国城市代码ID
     * @return 结果
     */
    @Override
    public int deleteWeatherDistrictById(Long id) {
        return weatherDistrictMapper.deleteWeatherDistrictById(id);
    }

    /**
     * 导入全国城市数据
     * @param weatherDistrictList
     * @return
     */
    @Override
    public String importWeatherDistrict(List<WeatherDistrict> weatherDistrictList) {
        if (StringUtils.isEmpty(weatherDistrictList) || weatherDistrictList.size() == 0) {
            return "导入的数据不能为空！";
        }
        for (WeatherDistrict weatherDistrict : weatherDistrictList) {
            weatherDistrictMapper.insertWeatherDistrict(weatherDistrict);
        }
        return String.valueOf(weatherDistrictList.size());
    }
}
