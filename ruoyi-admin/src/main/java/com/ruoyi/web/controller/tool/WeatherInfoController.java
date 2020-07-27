package com.ruoyi.web.controller.tool;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
    private String districtURL = " https://restapi.amap.com/v3/config/district?key=7a9247c712ee77b73fa81ec9bd9de896";

    /**
     * ip接口
     */
    private String ipURL = "https://restapi.amap.com/v3/ip?key=7a9247c712ee77b73fa81ec9bd9de896";

    @RequestMapping("info")
    @RequiresPermissions("tool:weather:info")
    public JSONObject info() {
        restTemplate.getForObject(districtURL,null,JSONObject.class);
        return null;
    }
}
