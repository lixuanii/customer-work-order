package com.lixuan.customerworkorder.service.customerComplaintNode.pay;

import com.lixuan.customerworkorder.constant.CustomerErrorCode;
import com.lixuan.customerworkorder.entity.CustomerComplaintInfo;
import com.lixuan.customerworkorder.enums.NodePayTypeEnum;
import com.lixuan.customerworkorder.enums.PayCustomersStatusEnum;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 赔付给客户
 *
 * @author lixuan
 * @date 2022-04-01 14:21
 */
@Service
public class PayCustomersService extends AbstractPayService {

    @Override
    CustomerComplaintInfo nodeBuildInfo(CustomerComplaintInfo info) {
        //校验类型
        Assert.notNull(info, CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_CUSTOMER_COMPLAINT_INFO_IS_NULL);
        Assert.isTrue(NodePayTypeEnum.PAY_CUSTOMERS.getValue().equals(info.getCustomerComplaintType()), CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_PAY_TYPE_ERROR);
        //校验 客诉详情 pay_customers = 未赔付
        Assert.isTrue(!PayCustomersStatusEnum.PAID.getValue().equals(info.getPayCustomers()), CustomerErrorCode.CUSTOMER_COMPLAINT_NODE_PAY_CUSTOMERS_ERROR);
        //设置 赔付状态为 已赔付
        info.setPayCustomers(PayCustomersStatusEnum.PAID.value);
        return info;
    }
}
