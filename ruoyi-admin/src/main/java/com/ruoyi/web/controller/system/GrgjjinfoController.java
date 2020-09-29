package com.ruoyi.web.controller.system;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
import com.ruoyi.system.domain.Grgjjinfo;
import com.ruoyi.system.service.IGrgjjinfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.client.RestTemplate;

/**
 * 个人公积金明细信息Controller
 *
 * @author ruoyi
 * @date 2020-09-12
 */
@Controller
@RequestMapping("/system/grgjjinfo")
public class GrgjjinfoController extends BaseController {
    private String prefix = "system/grgjjinfo";

    @Autowired
    private IGrgjjinfoService grgjjinfoService;

    @Autowired
    private RestTemplate restTemplate;

    String hBgjjUrl = "http://58.242.86.120:8000/api/HbgjjCx";//淮北公积金查询接口
    String sZGjjUrl = "http://60.171.196.71:8000/api/SzgjjCx";//宿州公积金查询接口

    @RequiresPermissions("system:grgjjinfo:view")
    @GetMapping()
    public String grgjjinfo() {
        return prefix + "/grgjjinfo";
    }

    /**
     * 查询个人公积金明细信息列表
     */
    @RequiresPermissions("system:grgjjinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Grgjjinfo grgjjinfo) {
        startPage();
        List<Grgjjinfo> list = grgjjinfoService.selectGrgjjinfoList(grgjjinfo);
        return getDataTable(list);
    }

    /**
     * 导出个人公积金明细信息列表
     */
    @RequiresPermissions("system:grgjjinfo:export")
    @Log(title = "个人公积金明细信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Grgjjinfo grgjjinfo) {
        List<Grgjjinfo> list = grgjjinfoService.selectGrgjjinfoList(grgjjinfo);
        ExcelUtil<Grgjjinfo> util = new ExcelUtil<Grgjjinfo>(Grgjjinfo.class);
        return util.exportExcel(list, "个人公积金明细");
    }

    /**
     * 新增个人公积金明细信息
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存个人公积金明细信息
     */
    @RequiresPermissions("system:grgjjinfo:add")
    @Log(title = "个人公积金明细信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Grgjjinfo grgjjinfo) {
        return toAjax(grgjjinfoService.insertGrgjjinfo(grgjjinfo));
    }

    /**
     * 修改个人公积金明细信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Grgjjinfo grgjjinfo = grgjjinfoService.selectGrgjjinfoById(id);
        mmap.put("grgjjinfo", grgjjinfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存个人公积金明细信息
     */
    @RequiresPermissions("system:grgjjinfo:edit")
    @Log(title = "个人公积金明细信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Grgjjinfo grgjjinfo) {
        return toAjax(grgjjinfoService.updateGrgjjinfo(grgjjinfo));
    }

    /**
     * 删除个人公积金明细信息
     */
    @RequiresPermissions("system:grgjjinfo:remove")
    @Log(title = "个人公积金明细信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(grgjjinfoService.deleteGrgjjinfoByIds(ids));
    }


    /**
     * 查询个人公积金
     *包括宿州和淮北，其中若是在综合服务平台没有注册，将取不到个人账户，则返回为空数据
     * @param zjhm
     * @return
     */
    @RequiresPermissions("system:grgjjinfo:view")
    @RequestMapping("/findByZjhm")
    public String findGjjByZjhmOrGrzh(String zjhm) {
        if (zjhm == null || zjhm.isEmpty()) {
            return "redirect:/system/grgjjinfo";
        }
        String szUrlByZjhm = sZGjjUrl + "?asGrgjjxxSfzhm=" + zjhm;
        String hbUrlByZjhm = hBgjjUrl + "?asGrgjjxxSfzhm=" + zjhm;
        List<Grgjjinfo> grgjjinfoList = new ArrayList<>();
        JSONArray szGjjArray = restTemplate.postForObject(szUrlByZjhm, Object.class, JSONArray.class);//根据身份证查询宿州
        JSONArray hbGjjArray = restTemplate.postForObject(hbUrlByZjhm, Object.class, JSONArray.class);//根据身份证查询淮北
        System.out.println("打印宿州:" + szGjjArray + " 打印淮北:" + hbGjjArray);
        JSONArray gjjArray = null;//定义空的jsonArray接收数据
        if (szGjjArray == null && hbGjjArray == null) {
            return "redirect:/system/grgjjinfo";
        } else if (szGjjArray != null && hbGjjArray == null) {
            gjjArray = szGjjArray;
        } else if (hbGjjArray != null && szGjjArray == null) {
            gjjArray = hbGjjArray;
        }
        JSONObject ResultByZjhm = gjjArray.getJSONObject(0);
        if (ResultByZjhm != null) {//代表此人在宿州有数据
            try {
                String dwzh = ResultByZjhm.get("dwzh").toString();
                String dwmc = ResultByZjhm.get("dwmc").toString();
                String grzh = ResultByZjhm.get("grzh").toString();
                Double grzhye = Double.valueOf(ResultByZjhm.get("grzhye").toString());
                Double grjcjs = Double.valueOf(ResultByZjhm.get("grjcjs").toString());
                Double dwyjce = Double.valueOf(ResultByZjhm.get("dwyjce").toString());
                Double gryjce = Double.valueOf(ResultByZjhm.get("gryjce").toString());
                Double dwjcbl = Double.valueOf(ResultByZjhm.get("dwjcbl").toString());
                Double grjcbl = Double.valueOf(ResultByZjhm.get("grjcbl").toString());
                String gjbank = ResultByZjhm.get("gjbank").toString();
                Double monthpayamt = Double.valueOf(ResultByZjhm.get("monthpayamt").toString());
////            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
                String lastpaymonth = ResultByZjhm.get("lastpaymonth").toString();
                String szUrlByGrzh = sZGjjUrl + "?asGrzhmxGrzh=" + grzh + "&asGrzhmxJzrq=201701";//根据个人账户查询数据，宿州
                String hbUrlByGrzh = hBgjjUrl + "?asGrzhmxGrzh=" + grzh + "&asGrzhmxJzrq=201701";//根据个人账户查询数据，淮北
                JSONArray resultByGrzh = null;
                JSONArray szResultByGrzh = restTemplate.postForObject(szUrlByGrzh, Object.class, JSONArray.class);
                JSONArray hbResultByGrzh = restTemplate.postForObject(hbUrlByGrzh, Object.class, JSONArray.class);
                if (szResultByGrzh == null && hbResultByGrzh == null) {
                    return "redirect:/system/grgjjinfo";
                }
                if (szResultByGrzh == null) {
                    resultByGrzh = hbResultByGrzh;
                } else {
                    resultByGrzh = szResultByGrzh;
                }

                for (int i = 0; i < resultByGrzh.size(); i++) {
                    Grgjjinfo grgjjinfo = new Grgjjinfo();
                    JSONObject gjjInfoResult = resultByGrzh.getJSONObject(i);
                    String xingming = gjjInfoResult.get("xingming").toString();
                    String zhaiyao = gjjInfoResult.get("zhaiyao").toString();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
                    try {
                        Date jzrq = sdf.parse(gjjInfoResult.get("jzrq").toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    grgjjinfo.setDwzh(dwzh);
                    grgjjinfo.setDwmc(dwmc);
                    grgjjinfo.setDwjcbl(dwjcbl);
                    grgjjinfo.setGrjcbl(grjcbl);
                    grgjjinfo.setDwyjce(dwyjce);
                    grgjjinfo.setGryjce(gryjce);
                    grgjjinfo.setGrjcjs(grjcjs);
                    grgjjinfo.setGjbank(gjbank);
                    grgjjinfo.setGrzh(grzh);
                    grgjjinfo.setGrzhye(grzhye);
                    grgjjinfo.setXingming(xingming);
                    grgjjinfo.setZhaiyao(zhaiyao);
                    grgjjinfo.setZjhm(zjhm);
                    grgjjinfo.setMonthpayamt(monthpayamt);
                    grgjjinfo.setLastpaymonth(lastpaymonth);
                    grgjjinfo.setCreatetime(new Date());
                    grgjjinfoService.insertGrgjjinfo(grgjjinfo);
                }
            }catch (Exception e){
                return "redirect:/system/grgjjinfo";
            }
//            JSONArray szResultByGrzh = restTemplate.postForObject(szUrlByGrzh, Object.class, JSONArray.class);
//            System.out.println(szUrlByGrzh.length() + "数组长度");
        }
//        restTemplate.postForObject(hBgjjUrl, Object.class, Object.class);
        return "redirect:/system/grgjjinfo";
    }
}
