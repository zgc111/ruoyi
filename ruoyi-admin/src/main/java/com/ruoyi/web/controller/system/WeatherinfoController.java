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
import com.ruoyi.system.domain.Weatherinfo;
import com.ruoyi.system.service.IWeatherinfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 天气信息Controller
 * 
 * @author ruoyi
 * @date 2020-09-07
 */
@Controller
@RequestMapping("/system/weatherinfo")
public class WeatherinfoController extends BaseController
{
    private String prefix = "system/weatherinfo";

    @Autowired
    private IWeatherinfoService weatherinfoService;

    @RequiresPermissions("system:weatherinfo:view")
    @GetMapping()
    public String weatherinfo()
    {
        return prefix + "/weatherinfo";
    }

    /**
     * 查询天气信息列表
     */
    @RequiresPermissions("system:weatherinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Weatherinfo weatherinfo)
    {
        startPage();
        List<Weatherinfo> list = weatherinfoService.selectWeatherinfoList(weatherinfo);
        return getDataTable(list);
    }

    /**
     * 导出天气信息列表
     */
    @RequiresPermissions("system:weatherinfo:export")
    @Log(title = "天气信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Weatherinfo weatherinfo)
    {
        List<Weatherinfo> list = weatherinfoService.selectWeatherinfoList(weatherinfo);
        ExcelUtil<Weatherinfo> util = new ExcelUtil<Weatherinfo>(Weatherinfo.class);
        return util.exportExcel(list, "weatherinfo");
    }

    /**
     * 新增天气信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存天气信息
     */
    @RequiresPermissions("system:weatherinfo:add")
    @Log(title = "天气信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Weatherinfo weatherinfo)
    {
        return toAjax(weatherinfoService.insertWeatherinfo(weatherinfo));
    }

    /**
     * 修改天气信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
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
    public AjaxResult editSave(Weatherinfo weatherinfo)
    {
        return toAjax(weatherinfoService.updateWeatherinfo(weatherinfo));
    }

    /**
     * 删除天气信息
     */
    @RequiresPermissions("system:weatherinfo:remove")
    @Log(title = "天气信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(weatherinfoService.deleteWeatherinfoByIds(ids));
    }
}
