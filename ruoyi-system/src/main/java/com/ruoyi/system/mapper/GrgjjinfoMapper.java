package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Grgjjinfo;

import java.util.List;

/**
 * 个人公积金明细信息Mapper接口
 *
 * @author ruoyi
 * @date 2020-09-12
 */
public interface GrgjjinfoMapper {
    /**
     * 查询个人公积金明细信息
     *
     * @param id 个人公积金明细信息ID
     * @return 个人公积金明细信息
     */
    public Grgjjinfo selectGrgjjinfoById(Long id);

    /**
     * 查询个人公积金明细信息列表
     *
     * @param grgjjinfo 个人公积金明细信息
     * @return 个人公积金明细信息集合
     */
    public List<Grgjjinfo> selectGrgjjinfoList(Grgjjinfo grgjjinfo);

    /**
     * 新增个人公积金明细信息
     *
     * @param grgjjinfo 个人公积金明细信息
     * @return 结果
     */
    public int insertGrgjjinfo(Grgjjinfo grgjjinfo);

    /**
     * 修改个人公积金明细信息
     *
     * @param grgjjinfo 个人公积金明细信息
     * @return 结果
     */
    public int updateGrgjjinfo(Grgjjinfo grgjjinfo);

    /**
     * 删除个人公积金明细信息
     *
     * @param id 个人公积金明细信息ID
     * @return 结果
     */
    public int deleteGrgjjinfoById(Long id);

    /**
     * 批量删除个人公积金明细信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGrgjjinfoByIds(String[] ids);
}
