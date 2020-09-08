package com.ruoyi.web.controller.system;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Weatherinfo;
import com.ruoyi.system.service.IWeatherinfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.client.RestTemplate;

/**
 * 天气信息Controller
 *
 * @author ruoyi
 * @date 2020-09-07
 */
@Controller
@RequestMapping("/system/weatherinfo")
public class WeatherinfoController extends BaseController {
    private String prefix = "system/weatherinfo";

    @Autowired
    private IWeatherinfoService weatherinfoService;

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

    @RequiresPermissions("system:weatherinfo:view")
    @GetMapping()
    public String weatherinfo() {
        return prefix + "/weatherinfo";
    }

    /**
     * 查询天气信息列表
     */
    @RequiresPermissions("system:weatherinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Weatherinfo weatherinfo) {
        startPage();
        Long id = weatherinfoService.selectWeatherinfoOne();
        Weatherinfo weatherinfoById = weatherinfoService.selectWeatherinfoById(id);
        System.out.println(weatherinfoById.toString());
        List<Weatherinfo> list = new ArrayList<>();
        list.add(weatherinfoById);
//        List<Weatherinfo> list = weatherinfoService.selectWeatherinfoList(weatherinfo);
        return getDataTable(list);
    }

    /**
     * 导出天气信息列表
     */
    @RequiresPermissions("system:weatherinfo:export")
    @Log(title = "天气信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Weatherinfo weatherinfo) {
        List<Weatherinfo> list = weatherinfoService.selectWeatherinfoList(weatherinfo);
        ExcelUtil<Weatherinfo> util = new ExcelUtil<Weatherinfo>(Weatherinfo.class);
        return util.exportExcel(list, "weatherinfo");
    }

    /**
     * 新增天气信息
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存天气信息
     */
    @RequiresPermissions("system:weatherinfo:add")
    @Log(title = "天气信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Weatherinfo weatherinfo) {
        return toAjax(weatherinfoService.insertWeatherinfo(weatherinfo));
    }

    /**
     * 修改天气信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Weatherinfo weatherinfo = weatherinfoService.selectWeatherinfoById(id);
        mmap.put("weatherinfo", weatherinfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存天气信息
     */
    @RequiresPermissions("system:weatherinfo:edit")
    @Log(title = "天气信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Weatherinfo weatherinfo) {
        return toAjax(weatherinfoService.updateWeatherinfo(weatherinfo));
    }

    /**
     * 删除天气信息
     */
    @RequiresPermissions("system:weatherinfo:remove")
    @Log(title = "天气信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(weatherinfoService.deleteWeatherinfoByIds(ids));
    }


    /**
     * 查询天气信息
     *
     * @param keywords
     * @return
     */
    @RequestMapping("/info")
    @RequiresPermissions("system:weatherinfo:view")
    public String info(String keywords) {
        String url;
        if (keywords.isEmpty()) {
            JSONObject template = restTemplate.getForObject(ipURL, JSONObject.class, JSONObject.class);//如果城市名为空，则取客户HTTP之中的请求来进行定位
            String city = template.get("city").toString();
            url = districtURL + "&keywords=" + city;
        } else {
            url = districtURL + "&keywords=" + keywords;
        }
        JSONObject restTemplateForObject = restTemplate.getForObject(url, JSONObject.class, JSONObject.class);
        JSONArray districts = restTemplateForObject.getJSONArray("districts");//districts得到是json数组
        JSONObject result = districts.getJSONObject(0);
        String adcode = result.get("adcode").toString();//得到城市编码
        String wUrl = weatherURL + "&city=" + adcode;
        JSONObject weatherObject = restTemplate.getForObject(wUrl, JSONObject.class, JSONObject.class);
        JSONArray lives = weatherObject.getJSONArray("lives");//实况天气数据信息
        JSONObject livesJSONObject = lives.getJSONObject(0);
        if (livesJSONObject == null) {
            return "请求无数据,请重试!";
        }
        Weatherinfo weatherinfo = new Weatherinfo();
        weatherinfo.setProvince(livesJSONObject.get("province").toString());
        weatherinfo.setCity(livesJSONObject.get("city").toString());
        weatherinfo.setAdcode(livesJSONObject.get("adcode").toString());
        weatherinfo.setWeather(livesJSONObject.get("weather").toString());
        weatherinfo.setTemperature(livesJSONObject.get("temperature").toString());
        weatherinfo.setWinddirection(livesJSONObject.get("winddirection").toString());
        weatherinfo.setWindpower(livesJSONObject.get("windpower").toString());
        weatherinfo.setHumidity(livesJSONObject.get("humidity").toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date reporttime = sdf.parse(livesJSONObject.get("reporttime").toString());
            weatherinfo.setReporttime(reporttime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        weatherinfo.setCreateTime(new Date());
        weatherinfoService.insertWeatherinfo(weatherinfo);
        return prefix + "/weatherinfo";
    }


}
