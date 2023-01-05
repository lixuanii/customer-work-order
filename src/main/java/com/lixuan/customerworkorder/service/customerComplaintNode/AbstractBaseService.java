package com.lixuan.customerworkorder.service.customerComplaintNode;


import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.lixuan.customerworkorder.constant.CustomerErrorCode;
import com.lixuan.customerworkorder.entity.CustomerComplaintInfo;
import com.lixuan.customerworkorder.entity.CustomerComplaintNode;
import com.lixuan.customerworkorder.enums.InfoStatusEnum;
import com.lixuan.customerworkorder.enums.NodeStatusEnum;
import com.lixuan.customerworkorder.form.CustomerComplaintBaseForm;
import com.lixuan.customerworkorder.service.CustomerComplaintInfoService;
import com.lixuan.customerworkorder.service.CustomerComplaintNodeService;
import com.lixuan.customerworkorder.service.customerComplaintNode.process.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.util.Assert;


/**
 * @author lixuan
 * @date 2022-03-31 15:26
 */
@Component
public abstract class AbstractBaseService implements BaseService {

    private static final Logger log = LoggerFactory.getLogger(AbstractService.class);

    protected CustomerComplaintNodeService customerComplaintNodeService = SpringUtil.getBean(CustomerComplaintNodeService.class);

    protected CustomerComplaintInfoService customerComplaintInfoService = SpringUtil.getBean(CustomerComplaintInfoService.class);

    protected DataSourceTransactionManager dataSourceTransactionManager = SpringUtil.getBean(DataSourceTransactionManager.class);

    protected TransactionDefinition transactionDefinition = SpringUtil.getBean(TransactionDefinition.class);

    @Override
    public CustomerComplaintNode createNode(CustomerComplaintBaseForm form, NodeStatusEnum nodeStatusEnum) {
        CustomerComplaintNode node = this.buildNode(form, nodeStatusEnum);
        CustomerComplaintInfo info = this.buildInfo(form, nodeStatusEnum);
        this.insertNode(node, info, nodeStatusEnum);
        return node;
    }

    /**
     * 构建客诉信息
     *
     * @param form       请求值
     * @param nodeStatus 子节点状态
     * @return CustomerComplaintNode
     * @author lixuan
     * @date 2022/3/31 15:41
     **/
    protected abstract CustomerComplaintInfo buildInfo(CustomerComplaintBaseForm form, NodeStatusEnum nodeStatus);

    /**
     * 构建节点信息
     *
     * @param form       请求值
     * @param nodeStatus 子节点状态
     * @return CustomerComplaintNode
     * @author lixuan
     * @date 2022/3/31 15:41
     **/
    protected abstract CustomerComplaintNode buildNode(CustomerComplaintBaseForm form, NodeStatusEnum nodeStatus);

    /**
     * 创建节点信息，更新客诉详情
     *
     * @param node 节点信息
     * @param info 客诉详情
     * @author lixuan
     * @date 2022/3/31 15:42
     **/
    public final void insertNode(CustomerComplaintNode node, CustomerComplaintInfo info, NodeStatusEnum nodeStatus) {
        Assert.notNull(node, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_IS_NULL);
        Assert.notNull(info, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_CUSTOMER_COMPLAINT_INFO_IS_NULL);
        Assert.notNull(nodeStatus, CustomerErrorCode.CUSTOMER_COMPLAINT_INFO_STATUS_IS_NULL);
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        try {
            //新增node
            if (customerComplaintNodeService.save(node)) {
                Assert.notNull(node.getCustomerComplaintNodeId(), CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_ID_IS_NULL);
                //设置当前节点id
                info.setCustomerComplaintNodeId(
                        //确认方案时 如果 方案不同意 那么客诉详情的节点id修改为 已审核节点id
                        NodeStatusEnum.CONFIRMED_PLAN.equals(nodeStatus) && !Boolean.parseBoolean(node.getNodeContent())
                                ? customerComplaintNodeService.getByInfoIdAndStatus(info.getCustomerComplaintInfoId(), new Integer[]{InfoStatusEnum.REVIEWED.getValue()}).getCustomerComplaintNodeId()
                                : node.getCustomerComplaintNodeId()
                );
                //更新info
                customerComplaintInfoService.updateById(info);
            }
            dataSourceTransactionManager.commit(transactionStatus);
        } catch (Exception e) {
            log.error("AbstractBaseNodeService createNode error service is {}", this);
            dataSourceTransactionManager.rollback(transactionStatus);
        }
    }

    /**
     * 校验操作者是否为当前登录者
     *
     * @param customerComplaintInfoId 客诉详情id
     * @param nodeStatus              状态（可能会出现转让的情况，所以status为数组）
     * @author lixuan
     * @date 2022/3/31 18:16
     **/
    protected void checkProcessorUser(String customerComplaintInfoId, Integer[] nodeStatus) {
        Assert.notNull(customerComplaintInfoId, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_CUSTOMER_COMPLAINT_INFO_ID_IS_NULL);
        Assert.notNull(nodeStatus, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_STATUS_IS_NULL);
        CustomerComplaintNode node = customerComplaintNodeService.getByInfoIdAndStatus(customerComplaintInfoId, nodeStatus);
        Assert.notNull(node, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_IS_NULL);
        //审核人、转交人 二取一
        String processorUserId = StrUtil.isNotBlank(node.getProcessorUserId()) ? node.getProcessorUserId() : node.getTransferUserId();
        Assert.hasText(processorUserId, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_PROCESSOR_USER_ID_IS_NULL);
//        if (!processorUserId.equals("userId")) {
//            log.error("AbstractTransferService checkProcessorUser 当前操作人id：{} ,处理人id：{}", SecurityUtil.getUserId(), processorUserId);
//            throw new BusinessLogicException(CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_TRANSFER_MISMATCH);
//        }
    }
}
