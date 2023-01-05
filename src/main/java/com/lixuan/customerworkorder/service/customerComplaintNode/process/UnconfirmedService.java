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
 * 3.待确认方案 节点处理 已出方案
 *
 * @author lixuan
 * @date 2022-03-30 11:26
 */
@Service
public class UnconfirmedService extends AbstractService {


    /**
     * 状态校验map
     */
    private static final Map<String, Integer> STATUS_NODE_MAP = new HashMap<>();

    static {
        // 子节点设置状态  为  待确认方案（已出方案）
        STATUS_NODE_MAP.put(NODE_STATUS, NodeStatusEnum.UNCONFIRMED_PLAN.getValue());
        // 客诉详情校验状态  为  已审核
        STATUS_NODE_MAP.put(CHECK_INFO_STATUS, InfoStatusEnum.REVIEWED.getValue());

        STATUS_MAP.put(NodeStatusEnum.UNCONFIRMED_PLAN, STATUS_NODE_MAP);
    }

    @Override
    CustomerComplaintInfo buildCustomerComplaintInfo(CustomerComplaintInfo info, CustomerComplaintNodeForm from) {
        Assert.notNull(info, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_CUSTOMER_COMPLAINT_INFO_IS_NULL);
        //客诉详情更新status为 待确认方案(已出方案)
        info.setCustomerComplaintStatus(InfoStatusEnum.UNCONFIRMED_PLAN.getValue());
        return info;
    }

    @Override
    CustomerComplaintNode buildCustomerComplaintNode(CustomerComplaintNodeForm form) {
        Assert.notNull(form, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_FORM_IS_NULL);
        CustomerComplaintNode node = BeanUtil.copyProperties(form, CustomerComplaintNode.class);
        Assert.notNull(node, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_IS_NULL);
        //子节点更新状态为 UNCONFIRMED_PLAN 待确认方案
        node.setCustomerComplaintNodeStatus(STATUS_NODE_MAP.get(NODE_STATUS));
        return node;
    }

    @Override
    public void checkNode(CustomerComplaintNodeForm from) {
        Assert.notNull(from, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_FORM_IS_NULL);
//      待确认方案(已出方案)：已经审核 REVIEWED 转交方案 TRANSFER_PLAN
// todo       super.checkProcessorUser(from.getCustomerComplaintInfoId(), new Integer[]{CustomerComplaintNodeStatusEnum.REVIEWED.getValue(), CustomerComplaintNodeStatusEnum.TRANSFER_PLAN.getValue()});
    }

    // TODO: 2022/4/1    并且需要向“信息核实人”关联的邮箱发送一封邮件，
}
