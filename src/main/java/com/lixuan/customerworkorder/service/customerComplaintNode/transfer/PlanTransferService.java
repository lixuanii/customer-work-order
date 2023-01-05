package com.lixuan.customerworkorder.service.customerComplaintNode.transfer;

import com.lixuan.customerworkorder.enums.InfoStatusEnum;
import com.lixuan.customerworkorder.enums.NodeStatusEnum;

/**
 * 方案 转交
 *
 * @author lixuan
 * @date 2022-03-31 14:30
 */
public class PlanTransferService extends AbstractTransferService {

    static {
        //已审核（待出方案）
        CHECK_INFO_STATUS_MAP.put(NodeStatusEnum.TRANSFER_PLAN, InfoStatusEnum.REVIEWED.getValue());
    }

    @Override
    Integer[] buildCheckNodeStatus() {
        //方案转交：已审核 REVIEWED  方案转交 TRANSFER_PLAN
        return new Integer[]{NodeStatusEnum.REVIEWED.getValue(), NodeStatusEnum.TRANSFER_PLAN.getValue()};
    }

    // TODO: 2022/4/1  发送邮件给 方案转交人
}
