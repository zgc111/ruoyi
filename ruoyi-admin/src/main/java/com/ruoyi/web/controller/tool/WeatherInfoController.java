//package com.ruoyi.web.controller.tool;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpRequest;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.client.RestTemplate;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * 天气信息
// */
//@Controller
//@RequestMapping("/tool/weather")
//@ResponseBody
//public class WeatherInfoController {
//
//    @Autowired
//    private RestTemplate restTemplate;
//    /**
//     * 天气接口
//     */
//    private String weatherURL = "https://restapi.amap.com/v3/weather/weatherInfo?key=7a9247c712ee77b73fa81ec9bd9de896";
//    /**
//     * 行政区域接口
//     */
//    private String districtURL = "https://restapi.amap.com/v3/config/district?key=7a9247c712ee77b73fa81ec9bd9de896";
//
//    /**
//     * ip接口
//     */
//    private String ipURL = "https://restapi.amap.com/v3/ip?key=7a9247c712ee77b73fa81ec9bd9de896";
//
//    /**
//     * 根据城市名称查询天气
//     *
//     * @param keywords
//     * @return
//     */
//    @RequestMapping("info")
//    @RequiresPermissions("tool:weather:info")
//    public JSONObject info(String keywords) {
//        String url = districtURL + "&keywords=" + "合肥";
//        JSONObject restTemplateForObject = restTemplate.getForObject(url, JSONObject.class, JSONObject.class);
//        JSONArray districts = restTemplateForObject.getJSONArray("districts");//districts得到是json数组
//        JSONObject result = districts.getJSONObject(0);
//        String adcode = result.get("adcode").toString();//得到城市编码
//        String wUrl = weatherURL + "&city=" + adcode;
//        JSONObject weatherObject = restTemplate.getForObject(wUrl, JSONObject.class, JSONObject.class);
//        return weatherObject;
//    }
//}
