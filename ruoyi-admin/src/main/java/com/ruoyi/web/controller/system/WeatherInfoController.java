package com.ruoyi.web.controller.system;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.WeatherInfo;
import com.ruoyi.system.service.IWeatherInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 百度天气信息Controller
 *
 * @author zgc
 * @date 2020-09-28
 */
@Controller
@RequestMapping("/system/WeatherInfo")
public class WeatherInfoController extends BaseController {
    private String prefix = "system/WeatherInfo";

    @Autowired
    private IWeatherInfoService weatherInfoService;

    @RequiresPermissions("system:WeatherInfo:view")
    @GetMapping()
    public String WeatherInfo() {
        return prefix + "/WeatherInfo";
    }

    /**
     * 查询百度天气信息列表
     */
    @RequiresPermissions("system:WeatherInfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WeatherInfo weatherInfo) {
        startPage();
        List<WeatherInfo> list = weatherInfoService.selectWeatherInfoList(weatherInfo);
        return getDataTable(list);
    }

    /**
     * 导出百度天气信息列表
     */
    @RequiresPermissions("system:WeatherInfo:export")
    @Log(title = "百度天气信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WeatherInfo weatherInfo) {
        List<WeatherInfo> list = weatherInfoService.selectWeatherInfoList(weatherInfo);
        ExcelUtil<WeatherInfo> util = new ExcelUtil<WeatherInfo>(WeatherInfo.class);
        return util.exportExcel(list, "WeatherInfo");
    }

    /**
     * 新增百度天气信息
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存百度天气信息
     */
    @RequiresPermissions("system:WeatherInfo:add")
    @Log(title = "百度天气信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WeatherInfo weatherInfo) {
        return toAjax(weatherInfoService.insertWeatherInfo(weatherInfo));
    }

    /**
     * 修改百度天气信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        WeatherInfo weatherInfo = weatherInfoService.selectWeatherInfoById(id);
        mmap.put("weatherInfo", weatherInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存百度天气信息
     */
    @RequiresPermissions("system:WeatherInfo:edit")
    @Log(title = "百度天气信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WeatherInfo weatherInfo) {
        return toAjax(weatherInfoService.updateWeatherInfo(weatherInfo));
    }

    /**
     * 删除百度天气信息
     */
    @RequiresPermissions("system:WeatherInfo:remove")
    @Log(title = "百度天气信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(weatherInfoService.deleteWeatherInfoByIds(ids));
    }
}
