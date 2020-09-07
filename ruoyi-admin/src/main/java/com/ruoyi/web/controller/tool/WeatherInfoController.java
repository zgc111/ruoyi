package com.ruoyi.web.controller.tool;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 天气信息
 */
@Controller
@RequestMapping("/tool/weather")
public class WeatherInfoController {

    @Autowired
    private RestTemplate restTemplate;
    /**
     * 天气接口
     */
    private String weatherURL = "https://restapi.amap.com/v3/weather/weatherInfo?key=7a9247c712ee77b73fa81ec9bd9de896";
    /**
     * 行政区域接口
     */
    private String districtURL = "https://restapi.amap.com/v3/config/district?key=7a9247c712ee77b73fa81ec9bd9de896";

    /**
     * ip接口
     */
    private String ipURL = "https://restapi.amap.com/v3/ip?key=7a9247c712ee77b73fa81ec9bd9de896";


    /**
     * 通过行政区域接口获取城市的adcode，通过adcode得到天气信息
     *
     * @return
     */
    @RequestMapping("/info")
    @RequiresPermissions("tool:weather:info")
    @ResponseBody
    public JSONObject info(String keywords) {
        String DUrl = districtURL + "&keywords=" + "合肥";
        JSONObject templateForObject = restTemplate.getForObject(DUrl, JSONObject.class, JSONObject.class);
        JSONArray districts = templateForObject.getJSONArray("districts");//通过关键字districts获取城市的adcode
        String adcode = districts.getJSONObject(0).get("adcode").toString();
        String Iurl = weatherURL + "&city=" + adcode;
        JSONObject result = restTemplate.getForObject(Iurl, JSONObject.class, JSONObject.class);
        JSONObject lives = result.getJSONArray("lives").getJSONObject(0);//实况天气数据信息

        return lives;
    }
}
