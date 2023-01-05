package com.lixuan.customerworkorder.service.customerComplaintNode.process;

import cn.hutool.core.bean.BeanUtil;
import com.lixuan.customerworkorder.constant.CustomerErrorCode;
import com.lixuan.customerworkorder.entity.CustomerComplaintInfo;
import com.lixuan.customerworkorder.entity.CustomerComplaintNode;
import com.lixuan.customerworkorder.enums.InfoStatusEnum;
import com.lixuan.customerworkorder.enums.NodeStatusEnum;
import com.lixuan.customerworkorder.form.CustomerComplaintNodeForm;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 4.确认方案（待办结） 节点处理
 *
 * @author lixuan
 * @date 2022-03-30 11:27
 */
@Service
public class ConfirmedService extends AbstractService {

    /**
     * 状态校验map
     */
    private static final Map<String, Integer> STATUS_NODE_MAP = new HashMap<>();

    static {
        //子节点设置状态为 确认方案
        STATUS_NODE_MAP.put(NODE_STATUS, NodeStatusEnum.CONFIRMED_PLAN.getValue());
        //客诉详情 校验状态为 待确认方案
        STATUS_NODE_MAP.put(CHECK_INFO_STATUS, InfoStatusEnum.UNCONFIRMED_PLAN.getValue());

        STATUS_MAP.put(NodeStatusEnum.CONFIRMED_PLAN, STATUS_NODE_MAP);
    }

    @Override
    CustomerComplaintInfo buildCustomerComplaintInfo(CustomerComplaintInfo info, CustomerComplaintNodeForm from) {
        Assert.notNull(info, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_CUSTOMER_COMPLAINT_INFO_IS_NULL);
        boolean isAgree = Boolean.parseBoolean(from.getNodeContent());
        // 不接受，详情状态变更为“待出方案”(已审核) REVIEWED
        // 接受 详情 状态变更为 “确认方案” CONFIRMED_PLAN
        info.setCustomerComplaintStatus(!isAgree ? InfoStatusEnum.REVIEWED.getValue() : InfoStatusEnum.CONFIRMED_PLAN.getValue());
        return info;
    }

    @Override
    CustomerComplaintNode buildCustomerComplaintNode(CustomerComplaintNodeForm form) {
        Assert.notNull(form, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_FORM_IS_NULL);
        CustomerComplaintNode node = BeanUtil.copyProperties(form, CustomerComplaintNode.class);
        Assert.notNull(node, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_IS_NULL);
        boolean isAgree = Boolean.parseBoolean(node.getNodeContent());
        // 不接受 节点状态变更为 REVIEWED “已审核（待出方案）”
        // 接受 节点状态变更为 CONFIRMED_PLAN “确认方案”
        node.setCustomerComplaintNodeStatus(!isAgree ? NodeStatusEnum.REVIEWED.getValue() : NodeStatusEnum.CONFIRMED_PLAN.getValue());
        node.setNodeContent(!isAgree ? "客户不接受" : "客户接受");
        return node;
    }
}
