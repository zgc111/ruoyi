package com.ruoyi.web.controller.tool;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 天气信息
 */
@Controller
@RequestMapping("/tool/weather")
public class WeatherInfoController {
    /**
     * 天气接口
     */
    private String weatherURL="https://restapi.amap.com/v3/weather/weatherInfo?key=7a9247c712ee77b73fa81ec9bd9de896";
    /**
     * 行政区域接口
     */
    private String districtURL=" https://restapi.amap.com/v3/config/district?key=7a9247c712ee77b73fa81ec9bd9de896";

    /**
     * ip接口
     */
    private String ipURL="https://restapi.amap.com/v3/ip?key=7a9247c712ee77b73fa81ec9bd9de896";


}
