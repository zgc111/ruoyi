package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.IpDataInfo;
import com.ruoyi.system.mapper.IpDataInfoMapper;
import com.ruoyi.system.service.IpDataInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service()
public class IpDataInfoServiceImpl implements IpDataInfoService {
    /**
     * 百度地图Ak
     * ak = dBuj4EMh4isu5LwoaeWMGBCPSzN1NRBr
     */
    @Autowired
    private IpDataInfoMapper ipDataInfoMapper;

    @Autowired
    private RestTemplate restTemplate;

    private static String url = "http://api.map.baidu.com/location/ip?ak=dBuj4EMh4isu5LwoaeWMGBCPSzN1NRBr&coor=bd09ll";//百度定位接口

    @Override
    public List<IpDataInfo> selectIpDataInfoList() {
        List<IpDataInfo> ipDataInfoList = ipDataInfoMapper.selectIpDataInfoList();
        if (ipDataInfoList == null) {
            return null;
        }

        return ipDataInfoList;
    }

    @Override
    public IpDataInfo selectByID(Long id) {
        IpDataInfo ipDataInfo = ipDataInfoMapper.selectByID(id);
        if (ipDataInfo == null) {
            return null;
        }
        return ipDataInfo;
    }

    @Override
    @Transactional
    public AjaxResult insertIpDataInfo(IpDataInfo ipDataInfo) {
        JSONObject jsonObject = restTemplate.postForObject(url, JSONObject.class, JSONObject.class);
        Integer status1 = Integer.valueOf(jsonObject.get("status").toString());
        if (jsonObject == null | status1 != 0) {
            return AjaxResult.warn("系统错误,无数据!");
        }
        JSONObject content = jsonObject.getJSONObject("content");//获取content下所有的数据
        String status = jsonObject.get("status").toString();
        String address = content.get("address").toString();
        JSONObject address_detail = content.getJSONObject("address_detail");
        String city = address_detail.get("city").toString();
        String cityCode = address_detail.get("city_code").toString();
        String province = address_detail.get("province").toString();
        JSONObject point = content.getJSONObject("point");
        String x = point.get("x").toString();
        String y = point.get("y").toString();

        IpDataInfo nowIpDataInfo = new IpDataInfo();
        nowIpDataInfo.setAddress(address);
        nowIpDataInfo.setCity(city);
        nowIpDataInfo.setCityCode(cityCode);
        nowIpDataInfo.setStatus(status);
        nowIpDataInfo.setProvince(province);
        nowIpDataInfo.setX(x);
        nowIpDataInfo.setY(y);
        nowIpDataInfo.setCreateTime(new Date());
        ipDataInfoMapper.insertIpDataInfo(nowIpDataInfo);
        IpDataInfo ipDataInfoResult = ipDataInfoMapper.selectByID(nowIpDataInfo.getId());
        if (ipDataInfoResult == null) {
            return AjaxResult.warn("系统错误,无数据!");
        }
        AjaxResult ajaxResult = AjaxResult.success("请求成功", ipDataInfoResult);
        return ajaxResult;
    }

    @Override
    public int deleteById(Long id) {
        int i = ipDataInfoMapper.deleteById(id);
        return i;
    }
}
