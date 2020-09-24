package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 个人公积金明细信息对象 grgjjinfo
 *
 * @author ruoyi
 * @date 2020-09-12
 */
public class Grgjjinfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * null
     */
    private Long id;

    /**
     * 证件号码
     */
    @Excel(name = "证件号码")
    private String zjhm;

    /**
     * 个人账号
     */
    @Excel(name = "个人账号")
    private String grzh;

    /**
     * 姓名
     */
    @Excel(name = "姓名")
    private String xingming;

    /**
     * 个人缴存基数
     */
    @Excel(name = "个人缴存基数")
    private Double grjcjs;

    /**
     * 单位缴存比例
     */
    @Excel(name = "单位缴存比例")
    private Double dwjcbl;

    /**
     * 个人缴存比例
     */
    @Excel(name = "个人缴存比例")
    private Double grjcbl;

    /**
     * 单位月缴存额
     */
    @Excel(name = "单位月缴存额")
    private Double dwyjce;

    /**
     * 个人月缴存额
     */
    @Excel(name = "个人月缴存额")
    private Double gryjce;

    /**
     * 月缴存额
     */
    @Excel(name = "月缴存额")
    private Double monthpayamt;

    /**
     * 缴存银行
     */
    @Excel(name = "缴存银行")
    private String gjbank;

    /**
     * 个人账户余额
     */
    @Excel(name = "个人账户余额")
    private Double grzhye;

    /**
     * 当前汇缴月份
     */
    @Excel(name = "当前汇缴月份", width = 30, dateFormat = "yyyy-MM")
    private Date jzrq;

    /**
     * 末次汇缴月份
     */
    @Excel(name = "末次汇缴月份", width = 30)
    private String lastpaymonth;

    /**
     * 单位名称
     */
    @Excel(name = "单位名称")
    private String dwmc;

    /**
     * 单位账户
     */
    @Excel(name = "单位账户")
    private String dwzh;

    /**
     * 摘要
     */
    @Excel(name = "摘要")
    private String zhaiyao;

    private Date createtime;

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    public String getZjhm() {
        return zjhm;
    }

    public void setGrzh(String grzh) {
        this.grzh = grzh;
    }

    public String getGrzh() {
        return grzh;
    }

    public void setXingming(String xingming) {
        this.xingming = xingming;
    }

    public String getXingming() {
        return xingming;
    }

    public void setGrjcjs(Double grjcjs) {
        this.grjcjs = grjcjs;
    }

    public Double getGrjcjs() {
        return grjcjs;
    }

    public void setDwjcbl(Double dwjcbl) {
        this.dwjcbl = dwjcbl;
    }

    public Double getDwjcbl() {
        return dwjcbl;
    }

    public void setGrjcbl(Double grjcbl) {
        this.grjcbl = grjcbl;
    }

    public Double getGrjcbl() {
        return grjcbl;
    }

    public void setDwyjce(Double dwyjce) {
        this.dwyjce = dwyjce;
    }

    public Double getDwyjce() {
        return dwyjce;
    }

    public void setGryjce(Double gryjce) {
        this.gryjce = gryjce;
    }

    public Double getGryjce() {
        return gryjce;
    }

    public void setMonthpayamt(Double monthpayamt) {
        this.monthpayamt = monthpayamt;
    }

    public Double getMonthpayamt() {
        return monthpayamt;
    }

    public void setGjbank(String gjbank) {
        this.gjbank = gjbank;
    }

    public String getGjbank() {
        return gjbank;
    }

    public void setGrzhye(Double grzhye) {
        this.grzhye = grzhye;
    }

    public Double getGrzhye() {
        return grzhye;
    }

    public void setJzrq(Date jzrq) {
        this.jzrq = jzrq;
    }

    public Date getJzrq() {
        return jzrq;
    }

    public void setLastpaymonth(String lastpaymonth) {
        this.lastpaymonth = lastpaymonth;
    }

    public String getLastpaymonth() {
        return lastpaymonth;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    public String getDwmc() {
        return dwmc;
    }

    public void setDwzh(String dwzh) {
        this.dwzh = dwzh;
    }

    public String getDwzh() {
        return dwzh;
    }

    public void setZhaiyao(String zhaiyao) {
        this.zhaiyao = zhaiyao;
    }

    public String getZhaiyao() {
        return zhaiyao;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("zjhm", getZjhm())
                .append("grzh", getGrzh())
                .append("xingming", getXingming())
                .append("grjcjs", getGrjcjs())
                .append("dwjcbl", getDwjcbl())
                .append("grjcbl", getGrjcbl())
                .append("dwyjce", getDwyjce())
                .append("gryjce", getGryjce())
                .append("monthpayamt", getMonthpayamt())
                .append("gjbank", getGjbank())
                .append("grzhye", getGrzhye())
                .append("jzrq", getJzrq())
                .append("lastpaymonth", getLastpaymonth())
                .append("dwmc", getDwmc())
                .append("dwzh", getDwzh())
                .append("zhaiyao", getZhaiyao())
                .append("createtime", getCreatetime())
                .toString();
    }
}
