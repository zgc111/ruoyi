package com.ruoyi.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.GrgjjinfoMapper;
import com.ruoyi.system.domain.Grgjjinfo;
import com.ruoyi.system.service.IGrgjjinfoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 个人公积金明细信息Service业务层处理
 *
 * @author ruoyi
 * @date 2020-09-12
 */
@Service
public class GrgjjinfoServiceImpl implements IGrgjjinfoService {
    @Autowired
    private GrgjjinfoMapper grgjjinfoMapper;

    /**
     * 查询个人公积金明细信息
     *
     * @param id 个人公积金明细信息ID
     * @return 个人公积金明细信息
     */
    @Override
    public Grgjjinfo selectGrgjjinfoById(Long id) {
        return grgjjinfoMapper.selectGrgjjinfoById(id);
    }

    /**
     * 查询个人公积金明细信息列表
     *
     * @param grgjjinfo 个人公积金明细信息
     * @return 个人公积金明细信息
     */
    @Override
    public List<Grgjjinfo> selectGrgjjinfoList(Grgjjinfo grgjjinfo) {
        return grgjjinfoMapper.selectGrgjjinfoList(grgjjinfo);
    }

    /**
     * 新增个人公积金明细信息
     *
     * @param grgjjinfo 个人公积金明细信息
     * @return 结果
     */
    @Override
    public int insertGrgjjinfo(Grgjjinfo grgjjinfo) {
        return grgjjinfoMapper.insertGrgjjinfo(grgjjinfo);
    }

    /**
     * 修改个人公积金明细信息
     *
     * @param grgjjinfo 个人公积金明细信息
     * @return 结果
     */
    @Override
    public int updateGrgjjinfo(Grgjjinfo grgjjinfo) {
        return grgjjinfoMapper.updateGrgjjinfo(grgjjinfo);
    }

    /**
     * 删除个人公积金明细信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGrgjjinfoByIds(String ids) {
        return grgjjinfoMapper.deleteGrgjjinfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除个人公积金明细信息信息
     *
     * @param id 个人公积金明细信息ID
     * @return 结果
     */
    @Override
    public int deleteGrgjjinfoById(Long id) {
        return grgjjinfoMapper.deleteGrgjjinfoById(id);
    }
}
