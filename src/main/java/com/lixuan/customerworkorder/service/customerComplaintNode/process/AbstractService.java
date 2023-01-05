package com.lixuan.customerworkorder.service.customerComplaintNode.process;

import com.lixuan.customerworkorder.constant.CustomerErrorCode;
import com.lixuan.customerworkorder.entity.CustomerComplaintInfo;
import com.lixuan.customerworkorder.entity.CustomerComplaintNode;
import com.lixuan.customerworkorder.enums.NodeStatusEnum;
import com.lixuan.customerworkorder.form.CustomerComplaintBaseForm;
import com.lixuan.customerworkorder.form.CustomerComplaintNodeForm;
import com.lixuan.customerworkorder.service.customerComplaintNode.AbstractBaseService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 客诉节点操作 审核、处理方案、确认方案、
 *
 * @author lixuan
 * @date 2022-03-30 15:52
 */
@Component
public abstract class AbstractService extends AbstractBaseService {

    /**
     * 子节点状态
     */
    protected static final String NODE_STATUS = "node";
    /**
     * 校验客诉详情状态
     */
    protected static final String CHECK_INFO_STATUS = "info";
    /**
     * 状态map
     */
    protected static final Map<NodeStatusEnum, Map<String, Integer>> STATUS_MAP = new HashMap<>();

    /**
     * 获取状态
     *
     * @param statusEnum 节点枚举
     * @param statusNode 谁的状态
     * @return Integer
     * @author lixuan
     * @date 2022/4/1 11:00
     **/
    private Integer getStatus(NodeStatusEnum statusEnum, String statusNode) {
        Assert.notNull(statusEnum, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_STATUS_IS_NULL);
        Integer status = Objects.requireNonNull(STATUS_MAP.entrySet()
                        .stream()
                        .filter(Objects::nonNull)
                        .filter(item -> item.getKey().equals(statusEnum))
                        .findFirst()
                        .map(Map.Entry::getValue)
                        .orElse(null))
                .get(statusNode);
        Assert.notNull(status, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_STATUS_MAP_ERROR);
        return status;
    }

    @Override
    protected CustomerComplaintInfo buildInfo(CustomerComplaintBaseForm form, NodeStatusEnum nodeStatus) {
        Assert.notNull(form, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_BASE_FORM_IS_NULL);
        CustomerComplaintNodeForm nodeFrom = (CustomerComplaintNodeForm) form;
        //共同参数校验
        CustomerComplaintInfo info = this.checkCustomerComplaintInfo(nodeFrom, nodeStatus);
        //客诉详情组装
        return buildCustomerComplaintInfo(info, nodeFrom);
    }

    @Override
    protected CustomerComplaintNode buildNode(CustomerComplaintBaseForm form, NodeStatusEnum nodeStatusEnum) {
        Assert.notNull(form, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_FORM_IS_NULL);
        Assert.notNull(nodeStatusEnum, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_STATUS_IS_NULL);
        //子节点参数校验
        this.checkCustomerComplaintNode((CustomerComplaintNodeForm) form, nodeStatusEnum);
        //其余信息组装
        return buildCustomerComplaintNode((CustomerComplaintNodeForm) form);
    }


    /**
     * 客诉详情 公共参数校验 获取客诉详情
     *
     * @param form 客诉节点form
     * @return CustomerComplaintInfo
     * @author lixuan
     * @date 2022/3/30 17:11
     **/
    protected CustomerComplaintInfo checkCustomerComplaintInfo(CustomerComplaintNodeForm form, NodeStatusEnum nodeStatus) {
        Assert.notNull(form, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_FORM_IS_NULL);
        Assert.notNull(form.getCustomerComplaintInfoId(), CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_CUSTOMER_COMPLAINT_INFO_ID_IS_NULL);
        Assert.hasText(form.getNodeContent(), CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_NODE_CONTENT_IS_NULL);
        CustomerComplaintInfo info = customerComplaintInfoService.getById(form.getCustomerComplaintInfoId());
        Assert.notNull(info, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_CUSTOMER_COMPLAINT_INFO_IS_NULL);
        //校验当前客诉详情状态是否正确
        Assert.isTrue(this.getStatus(nodeStatus, CHECK_INFO_STATUS).equals(info.getCustomerComplaintStatus()), CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_STATUS_ERROR);
        return info;
    }

    /**
     * 客诉节点公共参数校验
     *
     * @param form       客诉节点form
     * @param nodeStatus 状态
     * @author lixuan
     * @date 2022/4/1 17:13
     **/
    protected void checkCustomerComplaintNode(CustomerComplaintNodeForm form, NodeStatusEnum nodeStatus) {
        Assert.notNull(form, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_FORM_IS_NULL);
        Assert.notNull(nodeStatus, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_STATUS_IS_NULL);
        //校验客诉节点是否已经存在
        CustomerComplaintNode node = customerComplaintNodeService.getByInfoIdAndStatus(form.getCustomerComplaintInfoId(), new Integer[]{this.getStatus(nodeStatus, NODE_STATUS)});
        Assert.isTrue(node == null, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_ALREADY_EXISTS);
        //子节点 参数校验
        this.checkNode(form);
    }

    /**
     * 校验并组装客诉详情
     *
     * @param info 客诉详情
     * @param from 请求参数
     * @return CustomerComplaintInfo
     * @author lixuan
     * @date 2022/3/30 17:36
     **/
    abstract CustomerComplaintInfo buildCustomerComplaintInfo(CustomerComplaintInfo info, CustomerComplaintNodeForm from);

    /**
     * 组装客诉节点
     *
     * @param form 请求值
     * @return CustomerComplaintNode
     * @author lixuan
     * @date 2022/3/30 17:37
     **/
    abstract CustomerComplaintNode buildCustomerComplaintNode(CustomerComplaintNodeForm form);


    /**
     * 校验子节点信息
     *
     * @param from form参数
     * @author lixuan
     * @date 2022/3/30 17:11
     **/
    protected void checkNode(CustomerComplaintNodeForm from) {

    }
}
