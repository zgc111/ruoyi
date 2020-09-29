package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.IpDataInfo;

import java.util.List;

public interface IpDataInfoService {


    /**
     * 查询所有
     * @return
     */
    List<IpDataInfo> selectIpDataInfoList();

    /**
     * 查询信息根据id
     *
     * @param id
     * @return
     */
    IpDataInfo selectByID(Long id);

    /**
     * 新增
     *
     * @param ipDataInfo
     * @return
     */
    AjaxResult insertIpDataInfo(IpDataInfo ipDataInfo);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int deleteById(Long id);
}
