package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.WeatherDistrict;
import com.ruoyi.system.service.IWeatherDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WeatherInfoMapper;
import com.ruoyi.system.domain.WeatherInfo;
import com.ruoyi.system.service.IWeatherInfoService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 * 百度天气信息Service业务层处理
 *
 * @author zgc
 * @date 2020-09-28
 */
@Service
public class WeatherInfoServiceImpl implements IWeatherInfoService {
    @Autowired
    private WeatherInfoMapper weatherInfoMapper;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    IWeatherDistrictService iWeatherDistrictService;
    @Autowired
    RedisTemplate redisTemplate;
    private static String url = "http://api.map.baidu.com/weather/v1/?data_type=all&ak=dBuj4EMh4isu5LwoaeWMGBCPSzN1NRBr";//百度查询天气接口

    /**
     * 查询百度天气信息
     *
     * @param id 百度天气信息ID
     * @return 百度天气信息
     */
    @Override
    public WeatherInfo selectWeatherInfoById(Long id) {
        return weatherInfoMapper.selectWeatherInfoById(id);
    }

    /**
     * 查询百度天气信息列表
     *
     * @param weatherInfo 百度天气信息
     * @return 百度天气信息
     */
    @Override

    public List<WeatherInfo> selectWeatherInfoList(WeatherInfo weatherInfo) {
        String city = weatherInfo.getCity();
        List<Long> ids = new ArrayList<>();
        WeatherDistrict weatherDistrict = new WeatherDistrict();//城市编码表
        weatherDistrict.setCity(city);
        String weatherUrl = url + "&district_id=";
        List<WeatherDistrict> weatherDistrictList = iWeatherDistrictService.selectWeatherDistrictList(weatherDistrict);//通过城市名查询城市编码
        if (StringUtils.isEmpty(weatherDistrictList) | weatherDistrictList == null) {
            return new ArrayList<WeatherInfo>();

        }
        List<WeatherInfo> redisResultList = new ArrayList<>();
        for (WeatherDistrict weatherDistrictResult : weatherDistrictList) {

            String district = weatherDistrictResult.getDistrict();
            String districtGeocode = weatherDistrictResult.getDistrictGeocode();


            String resultPost = restTemplate.postForObject(weatherUrl + districtGeocode, Object.class, String.class);
            JSONObject jsonObject = JSON.parseObject(resultPost);

            if (StringUtils.isEmpty(resultPost)) {
                return new ArrayList<WeatherInfo>();
            }
            JSONObject result = jsonObject.getJSONObject("result");//获取json结果

            JSONObject location = result.getJSONObject("location");
            String country = location.get("country").toString();
            String province = location.get("province").toString();


            JSONObject now = result.getJSONObject("now");
            String text = now.get("text").toString();
            Integer temp = Integer.valueOf(now.get("temp").toString());
            Integer feelsLike = Integer.valueOf(now.get("feels_like").toString());
            Integer rh = Integer.valueOf(now.get("rh").toString());
            String windClass = now.get("wind_class").toString();
            String windDir = now.get("wind_dir").toString();


            JSONArray forecasts = result.getJSONArray("forecasts");
            if (StringUtils.isEmpty(forecasts)) {
                return new ArrayList<WeatherInfo>();
            }

            for (int i = 0; i < forecasts.size(); i++) {

                JSONObject forecastsJSONObject = forecasts.getJSONObject(i);
                String textDay = forecastsJSONObject.get("text_day").toString();
                String textNight = forecastsJSONObject.get("text_night").toString();
                Integer high = Integer.valueOf(forecastsJSONObject.get("high").toString());
                Integer low = Integer.valueOf(forecastsJSONObject.get("low").toString());
                String wcDay = forecastsJSONObject.get("wc_day").toString();
                String wdDay = forecastsJSONObject.get("wd_day").toString();
                String wcNight = forecastsJSONObject.get("wc_night").toString();
                String wdNight = forecastsJSONObject.get("wd_night").toString();
                String date = forecastsJSONObject.get("date").toString();
                String week = forecastsJSONObject.get("week").toString();
                weatherInfo.setTextDay(textDay);
                weatherInfo.setTextNight(textNight);
                weatherInfo.setHigh(high);
                weatherInfo.setLow(low);
                weatherInfo.setWcDay(wcDay);
                weatherInfo.setWdDay(wdDay);
                weatherInfo.setWcNight(wcNight);
                weatherInfo.setWdNight(wdNight);
                weatherInfo.setWeek(week);
                weatherInfo.setDate(date);
                weatherInfo.setName(district);
                weatherInfo.setcId(districtGeocode);
                weatherInfo.setCountry(country);
                weatherInfo.setProvince(province);
                weatherInfo.setText(text);
                weatherInfo.setTemp(temp);
                weatherInfo.setFeelsLike(feelsLike);
                weatherInfo.setRh(rh);
                weatherInfo.setWindClass(windClass);
                weatherInfo.setWindDir(windDir);
                weatherInfo.setCity(city);
                weatherInfoMapper.insertWeatherInfo(weatherInfo);
                ids.add(weatherInfo.getId());
            }
            System.out.println(ids);

        }
        List<WeatherInfo> byIdsList = weatherInfoMapper.selectByIds(ids);
        if (redisTemplate.opsForValue().get("infoList" + city) == null) {
//            List<WeatherInfo> infoList = weatherInfoMapper.selectByCity(city);
            redisTemplate.opsForValue().set("infoList" + city, byIdsList, 60, TimeUnit.SECONDS);
            List<WeatherInfo> redisResult = (List<WeatherInfo>) redisTemplate.opsForValue().get("infoList" + city);
            return redisResult;
        } else {
            List<WeatherInfo> redisResult = (List<WeatherInfo>) redisTemplate.opsForValue().get("infoList" + city);
            return redisResult;
        }

    }

    /**
     * 新增百度天气信息
     *
     * @param weatherInfo 百度天气信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertWeatherInfo(WeatherInfo weatherInfo) {
        return weatherInfoMapper.insertWeatherInfo(weatherInfo);
    }

    /**
     * 修改百度天气信息
     *
     * @param weatherInfo 百度天气信息
     * @return 结果
     */
    @Override
    public int updateWeatherInfo(WeatherInfo weatherInfo) {
        return weatherInfoMapper.updateWeatherInfo(weatherInfo);
    }

    /**
     * 删除百度天气信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWeatherInfoByIds(String ids) {
        return weatherInfoMapper.deleteWeatherInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除百度天气信息信息
     *
     * @param id 百度天气信息ID
     * @return 结果
     */
    @Override
    public int deleteWeatherInfoById(Long id) {
        return weatherInfoMapper.deleteWeatherInfoById(id);
    }

    @Override
    public List<WeatherInfo> selectByCity(String city) {
        List<WeatherInfo> weatherInfoList = weatherInfoMapper.selectByCity(city);
        if (StringUtils.isEmpty(weatherInfoList)) {
            return new ArrayList<>();
        }
        return weatherInfoList;
    }
}
