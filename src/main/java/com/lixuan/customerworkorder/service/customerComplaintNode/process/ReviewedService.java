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
 * 2.已审核 节点处理
 *
 * @author lixuan
 * @date 2022-03-30 11:24
 */
@Service
public class ReviewedService extends AbstractService {

    /**
     * 状态校验map
     */
    private static final Map<String, Integer> STATUS_NODE_MAP = new HashMap<>();

    static {
        // 子节点设置状态  为  已审核（待出方案）
        STATUS_NODE_MAP.put(NODE_STATUS, NodeStatusEnum.REVIEWED.getValue());
        // 客诉详情校验状态  为  待审核（已核实）
        STATUS_NODE_MAP.put(CHECK_INFO_STATUS, InfoStatusEnum.UNREVIEWED.getValue());

        STATUS_MAP.put(NodeStatusEnum.REVIEWED, STATUS_NODE_MAP);
    }

    @Override
    CustomerComplaintInfo buildCustomerComplaintInfo(CustomerComplaintInfo info, CustomerComplaintNodeForm from) {
        Assert.notNull(info, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_CUSTOMER_COMPLAINT_INFO_IS_NULL);
        //客诉详情更新status为 已审核
        info.setCustomerComplaintStatus(InfoStatusEnum.REVIEWED.getValue());
        return info;
    }

    @Override
    CustomerComplaintNode buildCustomerComplaintNode(CustomerComplaintNodeForm form) {
        Assert.notNull(form, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_FORM_IS_NULL);
        CustomerComplaintNode node = BeanUtil.copyProperties(form, CustomerComplaintNode.class);
        Assert.notNull(node, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_IS_NULL);
        //设置状态
        node.setCustomerComplaintNodeStatus(STATUS_NODE_MAP.get(NODE_STATUS));
        return node;
    }

    @Override
    protected void checkNode(CustomerComplaintNodeForm from) {
        Assert.notNull(from, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_FORM_IS_NULL);
        //校验当前登录人是否为 转交审核 TRANSFER_REVIEW  已核实 VERIFIED 的操作人
// todo       暂时禁用 super.checkProcessorUser(from.getCustomerComplaintInfoId(), new Integer[]{CustomerComplaintNodeStatusEnum.TRANSFER_REVIEW.getValue(), CustomerComplaintNodeStatusEnum.VERIFIED.getValue()});
        //方案处理人不可为null
        Assert.hasText(from.getProcessorUserId(), CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_PROCESSOR_USER_ID_IS_NULL);
    }

    // TODO: 2022/4/1  发送邮件给 方案处理人
}

