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
 * 0 已核实
 *
 * @author lixuan
 * @date 2022-03-30 11:16
 */
@Service
public class UnreviewedService extends AbstractService {
    /**
     * 状态校验map
     */
    private static final Map<String, Integer> STATUS_NODE_MAP = new HashMap<>();

    static {
        // 子节点为 已核实
        STATUS_NODE_MAP.put(NODE_STATUS, NodeStatusEnum.VERIFIED.getValue());
        // 客诉详情为 待核实
        STATUS_NODE_MAP.put(CHECK_INFO_STATUS, InfoStatusEnum.UNVERIFIED.getValue());

        STATUS_MAP.put(NodeStatusEnum.VERIFIED, STATUS_NODE_MAP);
    }

    /**
     * 待审核节点 组装客诉详情
     *
     * @param info 客诉详情
     * @param form 客诉节点form
     * @return CustomerComplaintInfo
     * @author lixuan
     * @date 2022/3/31 10:37
     **/
    @Override
    CustomerComplaintInfo buildCustomerComplaintInfo(CustomerComplaintInfo info, CustomerComplaintNodeForm form) {
        Assert.notNull(info, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_CUSTOMER_COMPLAINT_INFO_IS_NULL);
        Assert.notNull(form, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_FORM_IS_NULL);
        Assert.notNull(form.getProxyPay(), CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_PROXY_PAY_IS_NULL);
        //客诉详情更新status为 已核实
        info.setCustomerComplaintStatus(InfoStatusEnum.UNREVIEWED.getValue());
        //设置代理赔付
        info.setProxyPay(!form.getProxyPay() ? ProxyPayStatusEnum.No_NEED_TO_PAY.value : ProxyPayStatusEnum.NOT_PAID.value);
        return info;
    }

    /**
     * 待验证节点 组装节点详情
     *
     * @param form 请求参数
     * @return CustomerComplaintNode
     * @author lixuan
     * @date 2022/3/31 10:38
     **/
    @Override
    protected CustomerComplaintNode buildCustomerComplaintNode(CustomerComplaintNodeForm form) {
        Assert.notNull(form, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_FORM_IS_NULL);
        CustomerComplaintNode node = BeanUtil.copyProperties(form, CustomerComplaintNode.class);
        Assert.notNull(node, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_IS_NULL);
        //设置状态
        node.setCustomerComplaintNodeStatus(STATUS_NODE_MAP.get(NODE_STATUS));
        return node;
    }

    /**
     * 待审核节点处理中 独有的校验
     *
     * @param from 节点form
     * @author lixuan
     * @date 2022/3/30 11:54
     **/
    @Override
    public void checkNode(CustomerComplaintNodeForm from) {
        Assert.notNull(from, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_FORM_IS_NULL);
        //代理赔付
        Assert.notNull(from.getProxyPay(), CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_PROXY_PAY_IS_NULL);
        //审核人
        Assert.hasText(from.getProcessorUserId(), CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_PROCESSOR_USER_ID_IS_NULL);
    }

    // TODO: 2022/4/1 发送邮件给审核人

}
