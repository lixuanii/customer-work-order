package com.lixuan.customerworkorder.service;

import com.lixuan.customerworkorder.entity.CustomerComplaintNode;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 客诉信息节点表 服务类
 * </p>
 *
 * @author lixuan
 * @since 2023-01-05 11:48:36
 */
public interface CustomerComplaintNodeService extends IService<CustomerComplaintNode> {

    /**
     * 根据客诉id和节点数组获取详情
     *
     * @param customerComplaintInfoId 客诉id
     * @param nodeStatus              节点数组
     * @return CustomerComplaintNode 详情
     * @author lixuan
     * @date 2023/1/5 13:48
     **/
    CustomerComplaintNode getByInfoIdAndStatus(String customerComplaintInfoId, Integer[] nodeStatus);
}
