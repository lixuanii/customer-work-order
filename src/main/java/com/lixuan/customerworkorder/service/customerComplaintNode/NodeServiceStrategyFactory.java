package com.lixuan.customerworkorder.service.customerComplaintNode;

import com.lixuan.customerworkorder.enums.NodeStatusEnum;
import com.lixuan.customerworkorder.service.customerComplaintNode.pay.PayCustomersService;
import com.lixuan.customerworkorder.service.customerComplaintNode.pay.ProxyPayService;
import com.lixuan.customerworkorder.service.customerComplaintNode.process.ConfirmedService;
import com.lixuan.customerworkorder.service.customerComplaintNode.process.ProcessedService;
import com.lixuan.customerworkorder.service.customerComplaintNode.process.ReviewedService;
import com.lixuan.customerworkorder.service.customerComplaintNode.process.UnconfirmedService;
import com.lixuan.customerworkorder.service.customerComplaintNode.process.UnreviewedService;
import com.lixuan.customerworkorder.service.customerComplaintNode.transfer.PlanTransferService;
import com.lixuan.customerworkorder.service.customerComplaintNode.transfer.ReviewedTransferService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 客诉处理
 *
 * @author lixuan
 * @date 2022-03-30 11:10
 */
public class NodeServiceStrategyFactory {


    private static final Map<NodeStatusEnum, BaseService> NODE_SERVICE_MAP = new ConcurrentHashMap<>(7);


    public static BaseService getInstance(NodeStatusEnum statusEnum) {
        return NODE_SERVICE_MAP.get(statusEnum);
    }

    static {

        //节点操作
        NODE_SERVICE_MAP.put(NodeStatusEnum.VERIFIED, new UnreviewedService());
        NODE_SERVICE_MAP.put(NodeStatusEnum.REVIEWED, new ReviewedService());
        NODE_SERVICE_MAP.put(NodeStatusEnum.UNCONFIRMED_PLAN, new UnconfirmedService());
        NODE_SERVICE_MAP.put(NodeStatusEnum.CONFIRMED_PLAN, new ConfirmedService());
        NODE_SERVICE_MAP.put(NodeStatusEnum.PROCESSED, new ProcessedService());

        //转交
        NODE_SERVICE_MAP.put(NodeStatusEnum.TRANSFER_REVIEW, new ReviewedTransferService());
        NODE_SERVICE_MAP.put(NodeStatusEnum.TRANSFER_PLAN, new PlanTransferService());

        //赔付
        NODE_SERVICE_MAP.put(NodeStatusEnum.PAY_CUSTOMER, new PayCustomersService());
        NODE_SERVICE_MAP.put(NodeStatusEnum.PROXY_PAY, new ProxyPayService());
    }

}
