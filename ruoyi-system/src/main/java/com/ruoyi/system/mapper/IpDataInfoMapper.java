package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.IpDataInfo;

import java.util.List;

public interface IpDataInfoMapper {

    /**
     * 查询所有
     * @return
     */
    List<IpDataInfo> selectIpDataInfoList();

    /**
     * 查询信息根据id
     * @param id
     * @return
     */
    IpDataInfo selectByID(Long id);

    /**
     * 新增
     * @param ipDataInfo
     * @return
     */
    int insertIpDataInfo(IpDataInfo ipDataInfo);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteById(Long id);
}
