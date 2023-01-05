package com.lixuan.customerworkorder.service.customerComplaintNode.pay;

import cn.hutool.core.bean.BeanUtil;
import com.lixuan.customerworkorder.constant.CustomerErrorCode;
import com.lixuan.customerworkorder.entity.CustomerComplaintInfo;
import com.lixuan.customerworkorder.entity.CustomerComplaintNode;
import com.lixuan.customerworkorder.enumValidator.EnumUtils;
import com.lixuan.customerworkorder.enums.InfoStatusEnum;
import com.lixuan.customerworkorder.enums.NodePayTypeEnum;
import com.lixuan.customerworkorder.enums.NodeStatusEnum;
import com.lixuan.customerworkorder.form.CustomerComplaintBaseForm;
import com.lixuan.customerworkorder.form.CustomerComplaintPayForm;
import com.lixuan.customerworkorder.service.customerComplaintNode.AbstractBaseService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.math.RoundingMode;

/**
 * 客诉赔付
 *
 * @author lixuan
 * @date 2022-04-01 13:51
 */
@Component
public abstract class AbstractPayService extends AbstractBaseService {


    @Override
    protected CustomerComplaintInfo buildInfo(CustomerComplaintBaseForm form, NodeStatusEnum nodeStatus) {
        Assert.notNull(form, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_BASE_FORM_IS_NULL);
        CustomerComplaintPayForm payForm = (CustomerComplaintPayForm) form;
        CustomerComplaintInfo info = this.checkCustomerComplaintInfo(payForm, nodeStatus);
        info = this.nodeBuildInfo(info);
        return info;
    }

    @Override
    protected CustomerComplaintNode buildNode(CustomerComplaintBaseForm form, NodeStatusEnum nodeStatus) {
        Assert.notNull(form, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_BASE_FORM_IS_NULL);
        Assert.notNull(nodeStatus, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_STATUS_IS_NULL);

        return buildCustomerComplaintNode(form.getCustomerComplaintInfoId(), (CustomerComplaintPayForm) form, nodeStatus.getValue());
    }

    /**
     * 构建 节点
     *
     * @param infoId  客诉详情id
     * @param payForm 参数
     * @param status  状态
     * @return CustomerComplaintNode
     * @author lixuan
     * @date 2022/4/1 15:04
     **/
    protected CustomerComplaintNode buildCustomerComplaintNode(String infoId, CustomerComplaintPayForm payForm, Integer status) {
        Assert.notNull(payForm, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_PAY_FORM_IS_NULL);
        Assert.hasText(infoId, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_CUSTOMER_COMPLAINT_INFO_ID_IS_NULL);
        Assert.notNull(status, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_STATUS_IS_NULL);
        Assert.notNull(payForm.getPayAmount(), CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_PAY_AMOUNT_IS_NULL);
        //校验赔付 是否存在
        Assert.isTrue(customerComplaintNodeService.getByInfoIdAndStatus(infoId, new Integer[]{status}) == null, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_ALREADY_EXISTS);
        CustomerComplaintNode node = BeanUtil.copyProperties(payForm, CustomerComplaintNode.class);
        //校验类型
        Assert.isTrue(EnumUtils.isExist(NodePayTypeEnum.class, node.getCustomerComplaintNodePayType()), CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_PAY_TYPE_IS_NULL);
        node.setCustomerComplaintInfoId(infoId);
        node.setCustomerComplaintNodeStatus(status);
        node.setPayAmount(payForm.getPayAmount().setScale(2, RoundingMode.HALF_UP));
        return node;
    }

    /**
     * 共有属性校验
     *
     * @param form       赔付form
     * @param nodeStatus 节点状态
     * @return CustomerComplaintInfo
     * @author lixuan
     * @date 2022/4/1 14:25
     **/
    private CustomerComplaintInfo checkCustomerComplaintInfo(CustomerComplaintPayForm form, NodeStatusEnum nodeStatus) {
        Assert.notNull(form.getCustomerComplaintInfoId(), CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_CUSTOMER_COMPLAINT_INFO_ID_IS_NULL);
        Assert.notNull(nodeStatus, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_STATUS_IS_NULL);
        CustomerComplaintInfo info = customerComplaintInfoService.getById(form.getCustomerComplaintInfoId());
        Assert.notNull(info, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_CUSTOMER_COMPLAINT_INFO_IS_NULL);
        Assert.notNull(EnumUtils.getEnumByValue(NodePayTypeEnum.class, info.getCustomerComplaintType()), CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_PAY_TYPE_ERROR);
        //校验客诉详情状态是否为 CONFIRMED_PLAN “待办结（确认方案）”
        Assert.isTrue(InfoStatusEnum.CONFIRMED_PLAN.getValue().equals(info.getCustomerComplaintStatus()), CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_STATUS_ERROR);
        return info;
    }


    /**
     * 客诉详情 私有属性校验，设置
     *
     * @param info 客诉详情
     * @return CustomerComplaintInfo
     * @author lixuan
     * @date 2022/4/1 16:39
     **/
    abstract CustomerComplaintInfo nodeBuildInfo(CustomerComplaintInfo info);
}
