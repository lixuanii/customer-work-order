package com.lixuan.customerworkorder.service.customerComplaintNode.transfer;

import com.lixuan.customerworkorder.enums.InfoStatusEnum;
import com.lixuan.customerworkorder.enums.NodeStatusEnum;
import org.springframework.stereotype.Service;

/**
 * 审核 转交
 *
 * @author lixuan
 * @date 2022-03-31 14:29
 */
@Service
public class ReviewedTransferService extends AbstractTransferService {


    static {
        //待审核（已核实）
        CHECK_INFO_STATUS_MAP.put(NodeStatusEnum.TRANSFER_REVIEW, InfoStatusEnum.UNREVIEWED.getValue());
    }

    @Override
    Integer[] buildCheckNodeStatus() {
        //审核转交：已经核实 VERIFIED 转交审核 TRANSFER_REVIEW
        return new Integer[]{NodeStatusEnum.VERIFIED.getValue(), NodeStatusEnum.TRANSFER_REVIEW.getValue()};
    }

    // TODO: 2022/4/1  发送邮件给 审核转交人
}
