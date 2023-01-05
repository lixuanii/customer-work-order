package com.lixuan.customerworkorder.service.customerComplaintNode.process;

import cn.hutool.core.bean.BeanUtil;
import com.lixuan.customerworkorder.constant.CustomerErrorCode;
import com.lixuan.customerworkorder.entity.CustomerComplaintInfo;
import com.lixuan.customerworkorder.entity.CustomerComplaintNode;
import com.lixuan.customerworkorder.enums.InfoStatusEnum;
import com.lixuan.customerworkorder.enums.NodeStatusEnum;
import com.lixuan.customerworkorder.enums.ProxyPayStatusEnum;
import com.lixuan.customerworkorder.form.CustomerComplaintNodeForm;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 5.已办结 节点处理
 *
 * @author lixuan
 * @date 2022-03-30 11:28
 */
@Service
public class ProcessedService extends AbstractService {

    /**
     * 状态校验map
     */
    private static final Map<String, Integer> STATUS_NODE_MAP = new HashMap<>();

    static {
        STATUS_NODE_MAP.put(NODE_STATUS, NodeStatusEnum.PROCESSED.getValue());
        STATUS_NODE_MAP.put(CHECK_INFO_STATUS, InfoStatusEnum.CONFIRMED_PLAN.getValue());
        STATUS_MAP.put(NodeStatusEnum.PROCESSED, STATUS_NODE_MAP);
    }


    @Override
    CustomerComplaintInfo buildCustomerComplaintInfo(CustomerComplaintInfo info, CustomerComplaintNodeForm from) {
        Assert.notNull(info, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_CUSTOMER_COMPLAINT_INFO_IS_NULL);
        Assert.notNull(from, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_FORM_IS_NULL);
        //代理赔付是“未赔付”状态，不能点击【办结】按钮
        Assert.isTrue(!ProxyPayStatusEnum.NOT_PAID.value.equals(info.getProxyPay()), CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_PROXY_PAY_ERROR);
        //已办结
        info.setCustomerComplaintStatus(InfoStatusEnum.PROCESSED.getValue());
        return info;
    }

    @Override
    CustomerComplaintNode buildCustomerComplaintNode(CustomerComplaintNodeForm form) {
        Assert.notNull(form, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_FORM_IS_NULL);
        CustomerComplaintNode node = BeanUtil.copyProperties(form, CustomerComplaintNode.class);
        Assert.notNull(node, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_IS_NULL);
        //设置节点状态为 已办结
        node.setCustomerComplaintNodeStatus(STATUS_NODE_MAP.get(NODE_STATUS));
        return node;
    }


}
