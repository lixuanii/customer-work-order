package com.lixuan.customerworkorder.service.impl;

import com.lixuan.customerworkorder.entity.CustomerComplaintNode;
import com.lixuan.customerworkorder.mapper.CustomerComplaintNodeMapper;
import com.lixuan.customerworkorder.service.CustomerComplaintNodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客诉信息节点表 服务实现类
 * </p>
 *
 * @author lixuan
 * @since 2023-01-05 11:48:36
 */
@Slf4j
@Service
public class CustomerComplaintNodeServiceImpl extends ServiceImpl<CustomerComplaintNodeMapper, CustomerComplaintNode> implements CustomerComplaintNodeService {


    /**
     * 根据客诉详情id获取客诉节点
     *
     * @param customerComplaintInfoId id
     * @param nodeStatus              节点 状态
     * @return CustomerComplaintNodeEntity
     * @author lixuan
     * @date 2022/3/30 14:54
     **/
    public CustomerComplaintNode getByInfoIdAndStatus(String customerComplaintInfoId, Integer[] nodeStatus) {

        return new CustomerComplaintNode();
    }

}
