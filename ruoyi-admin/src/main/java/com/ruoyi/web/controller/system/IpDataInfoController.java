package com.ruoyi.web.controller.system;


import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;

import com.ruoyi.system.domain.IpDataInfo;
import com.ruoyi.system.service.IpDataInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/system/ipDataInfo")
public class IpDataInfoController extends BaseController {

    @Autowired
    private IpDataInfoService ipDataInfoService;

    /**
     * 调用接口
     *
     * @return
     */
    @GetMapping(value = "/ip")
    public AjaxResult insertIpDataInfo(IpDataInfo ipDataInfo) {

        return ipDataInfoService.insertIpDataInfo(ipDataInfo);
    }

}
