package com.lixuan.customerworkorder.service.customerComplaintNode.transfer;

import cn.hutool.core.bean.BeanUtil;
import com.lixuan.customerworkorder.constant.CustomerErrorCode;
import com.lixuan.customerworkorder.entity.CustomerComplaintInfo;
import com.lixuan.customerworkorder.entity.CustomerComplaintNode;
import com.lixuan.customerworkorder.enums.NodeStatusEnum;
import com.lixuan.customerworkorder.form.CustomerComplaintBaseForm;
import com.lixuan.customerworkorder.form.CustomerComplaintTransferForm;
import com.lixuan.customerworkorder.service.customerComplaintNode.AbstractBaseService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 工单转交
 *
 * @author lixuan
 * @date 2022-03-31 14:09
 */
@Component
public abstract class AbstractTransferService extends AbstractBaseService {

    /**
     * 状态校验map
     */
    protected static final Map<NodeStatusEnum, Integer> CHECK_INFO_STATUS_MAP = new ConcurrentHashMap<>(2);

    @Override
    protected CustomerComplaintInfo buildInfo(CustomerComplaintBaseForm form, NodeStatusEnum nodeStatus) {
        return this.checkCustomerComplaintInfo((CustomerComplaintTransferForm) form, nodeStatus);
    }

    @Override
    protected CustomerComplaintNode buildNode(CustomerComplaintBaseForm form, NodeStatusEnum nodeStatusEnum) {
        Assert.notNull(form, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_BASE_FORM_IS_NULL);
        Assert.notNull(nodeStatusEnum, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_STATUS_IS_NULL);

        // 校验操作人 状态为当前 转让状态
//       todo super.checkProcessorUser(node.getCustomerComplaintInfoId(), this.buildCheckNodeStatus());
        return buildCustomerComplaintNode((CustomerComplaintTransferForm) form, nodeStatusEnum.getValue());

    }

    /**
     * 构建 转交节点
     *
     * @param transferForm 请求参数
     * @param status       修改之后的状态
     * @return CustomerComplaintNode
     * @author lixuan
     * @date 2022/3/31 15:05
     **/
    protected CustomerComplaintNode buildCustomerComplaintNode(CustomerComplaintTransferForm transferForm, Integer status) {
        Assert.notNull(transferForm, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_TRANSFER_FORM_IS_NULL);
        Assert.hasText(transferForm.getCustomerComplaintInfoId(), CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_CUSTOMER_COMPLAINT_INFO_ID_IS_NULL);
        Assert.notNull(status, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_STATUS_IS_NULL);
        CustomerComplaintNode node = BeanUtil.copyProperties(transferForm, CustomerComplaintNode.class);
        //客诉详情id
        node.setCustomerComplaintInfoId(transferForm.getCustomerComplaintInfoId());
        //当前处理人
        node.setCurrentUserId("userId");
        //转交状态
        node.setCustomerComplaintNodeStatus(status);
        return node;
    }


    /**
     * 共有校验客诉详情
     *
     * @param form       转交form
     * @param nodeStatus 节点状态
     * @return CustomerComplaintInfo
     * @author lixuan
     * @date 2022/3/31 14:52
     **/
    private CustomerComplaintInfo checkCustomerComplaintInfo(CustomerComplaintTransferForm form, NodeStatusEnum nodeStatus) {
        Assert.notNull(form, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_TRANSFER_FORM_IS_NULL);
        Assert.notNull(form.getCustomerComplaintInfoId(), CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_CUSTOMER_COMPLAINT_INFO_ID_IS_NULL);
        Assert.notNull(form.getTransferUserId(), CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_TRANSFER_USER_ID_IS_NULL);
        Assert.notNull(nodeStatus, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_STATUS_IS_NULL);
        CustomerComplaintInfo info = customerComplaintInfoService.getById(form.getCustomerComplaintInfoId());
        Assert.notNull(info, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_CUSTOMER_COMPLAINT_INFO_IS_NULL);
        //校验客诉详情info状态
        Assert.isTrue(CHECK_INFO_STATUS_MAP.get(nodeStatus).equals(info.getCustomerComplaintStatus()), CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_STATUS_ERROR);
        return info;
    }

    /**
     * 构建子节点校验的状态
     *
     * @return Integer
     * @author lixuan
     * @date 2022/4/1 9:29
     **/
    abstract Integer[] buildCheckNodeStatus();

}
