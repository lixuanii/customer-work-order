package com.lixuan.customerworkorder.service.customerComplaintNode.pay;

import com.lixuan.customerworkorder.constant.CustomerErrorCode;
import com.lixuan.customerworkorder.entity.CustomerComplaintInfo;
import com.lixuan.customerworkorder.enums.NodePayTypeEnum;
import com.lixuan.customerworkorder.enums.ProxyPayStatusEnum;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 代理赔付
 *
 * @author lixuan
 * @date 2022-04-01 14:22
 */
@Service
public class ProxyPayService extends AbstractPayService {

    @Override
    CustomerComplaintInfo nodeBuildInfo(CustomerComplaintInfo info) {
        Assert.notNull(info, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_CUSTOMER_COMPLAINT_INFO_IS_NULL);
        Assert.isTrue(NodePayTypeEnum.PROXY_PAY.getValue().equals(info.getCustomerComplaintType()), CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_PAY_TYPE_ERROR);
        //校验客诉详情 proxy_pay = 未赔付
        Assert.isTrue(
                !ProxyPayStatusEnum.No_NEED_TO_PAY.getValue().equals(info.getProxyPay()) || !ProxyPayStatusEnum.PAID.getValue().equals(info.getProxyPay())
                , CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_PROXY_PAY_ERROR);
        //设置 赔付状态为 已赔付
        info.setProxyPay(ProxyPayStatusEnum.PAID.value);
        return info;
    }

}
