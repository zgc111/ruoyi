package com.ruoyi.web.controller.system;

import java.util.List;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.WeatherDistrict;
import com.ruoyi.system.service.IWeatherDistrictService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 全国城市代码Controller
 *
 * @author ruoyi
 * @date 2020-09-28
 */
@Controller
@RequestMapping("/system/district")
public class WeatherDistrictController extends BaseController {
    private String prefix = "system/district";

    @Autowired
    private IWeatherDistrictService weatherDistrictService;

    @RequiresPermissions("system:district:view")
    @GetMapping()
    public String district() {
        return prefix + "/district";
    }

    /**
     * 查询全国城市代码列表
     */
    @RequiresPermissions("system:district:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WeatherDistrict weatherDistrict) {
        startPage();
        List<WeatherDistrict> list = weatherDistrictService.selectWeatherDistrictList(weatherDistrict);
        return getDataTable(list);
    }

    /**
     * 导出全国城市代码列表
     */
    @RequiresPermissions("system:district:export")
    @Log(title = "全国城市代码", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WeatherDistrict weatherDistrict) {
        List<WeatherDistrict> list = weatherDistrictService.selectWeatherDistrictList(weatherDistrict);
        ExcelUtil<WeatherDistrict> util = new ExcelUtil<WeatherDistrict>(WeatherDistrict.class);
        return util.exportExcel(list, "district");
    }

    /**
     * 导入全国城市代码表
     */
    @RequiresPermissions("system:district:export")
    @Log(title = "全国城市代码", businessType = BusinessType.IMPORT)
    @PostMapping("/import")
    public String importWeatherDistrict(MultipartFile file) throws Exception {
        ExcelUtil<WeatherDistrict> util = new ExcelUtil<>(WeatherDistrict.class);
        List<WeatherDistrict> weatherDistrictList = util.importExcel(file.getInputStream());
        weatherDistrictService.importWeatherDistrict(weatherDistrictList);
        return prefix + "/district";
    }

    /**
     * 新增全国城市代码
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存全国城市代码
     */
    @RequiresPermissions("system:district:add")
    @Log(title = "全国城市代码", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WeatherDistrict weatherDistrict) {
        return toAjax(weatherDistrictService.insertWeatherDistrict(weatherDistrict));
    }

    /**
     * 修改全国城市代码
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        WeatherDistrict weatherDistrict = weatherDistrictService.selectWeatherDistrictById(id);
        mmap.put("weatherDistrict", weatherDistrict);
        return prefix + "/edit";
    }

    /**
     * 修改保存全国城市代码
     */
    @RequiresPermissions("system:district:edit")
    @Log(title = "全国城市代码", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WeatherDistrict weatherDistrict) {
        return toAjax(weatherDistrictService.updateWeatherDistrict(weatherDistrict));
    }

    /**
     * 删除全国城市代码
     */
    @RequiresPermissions("system:district:remove")
    @Log(title = "全国城市代码", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(weatherDistrictService.deleteWeatherDistrictByIds(ids));
    }
}
