package com.lixuan.customerworkorder.controller;

import com.lixuan.customerworkorder.entity.CustomerComplaintNode;
import com.lixuan.customerworkorder.enums.NodeStatusEnum;
import com.lixuan.customerworkorder.form.CustomerComplaintNodeForm;
import com.lixuan.customerworkorder.form.CustomerComplaintPayForm;
import com.lixuan.customerworkorder.form.CustomerComplaintTransferForm;
import com.lixuan.customerworkorder.service.customerComplaintNode.BaseService;
import com.lixuan.customerworkorder.service.customerComplaintNode.NodeServiceStrategyFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 客诉信息节点Controller
 *
 * @author lixuan
 * @date Mon Mar 28 18:17:53 CST 2022
 */
@RestController
@RequestMapping("/customerComplaintNode")
public class CustomerComplaintNodeController {

    /**
     * 客诉信息核实
     */
    @PostMapping("/verified")
    public CustomerComplaintNode verified(@RequestBody @Valid CustomerComplaintNodeForm form) {
        BaseService nodeService = NodeServiceStrategyFactory.getInstance(NodeStatusEnum.VERIFIED);
        return nodeService.createNode(form, NodeStatusEnum.VERIFIED);
    }

    /**
     * 审核转交
     */
    @PostMapping("/transferReviewed")
    public CustomerComplaintNode reviewedTransfer(@RequestBody @Valid CustomerComplaintTransferForm form) {
        BaseService transferService = NodeServiceStrategyFactory.getInstance(NodeStatusEnum.TRANSFER_REVIEW);
        return transferService.createNode(form, NodeStatusEnum.TRANSFER_REVIEW);
    }

    /**
     * 客诉信息审核
     */
    @PostMapping(value = "/reviewed")
    public CustomerComplaintNode reviewed(@RequestBody @Valid CustomerComplaintNodeForm form) {
        BaseService nodeService = NodeServiceStrategyFactory.getInstance(NodeStatusEnum.REVIEWED);
        return nodeService.createNode(form, NodeStatusEnum.REVIEWED);
    }

    /**
     * 方案处理转交
     */
    @PostMapping(value = "/transferPlan")
    public CustomerComplaintNode solutionTransfer(@RequestBody @Valid CustomerComplaintTransferForm form) {
        BaseService transferService = NodeServiceStrategyFactory.getInstance(NodeStatusEnum.TRANSFER_REVIEW);
        return transferService.createNode(form, NodeStatusEnum.TRANSFER_PLAN);
    }

    /**
     * 方案处理（出方案）
     */
    @PostMapping(value = "/unconfirmedPlan")
    public CustomerComplaintNode solution(@RequestBody @Valid CustomerComplaintNodeForm form) {
        BaseService nodeService = NodeServiceStrategyFactory.getInstance(NodeStatusEnum.UNCONFIRMED_PLAN);
        return nodeService.createNode(form, NodeStatusEnum.UNCONFIRMED_PLAN);
    }


    /**
     * 确认方案
     */
    @PostMapping(value = "/confirmPlan")
    public CustomerComplaintNode confirm(@RequestBody @Valid CustomerComplaintNodeForm form) {
        BaseService nodeService = NodeServiceStrategyFactory.getInstance(NodeStatusEnum.CONFIRMED_PLAN);
        return nodeService.createNode(form, NodeStatusEnum.CONFIRMED_PLAN);
    }

    /**
     * 赔付客户
     */
    @PostMapping(value = "/payCustomers")
    public CustomerComplaintNode payCustomers(@RequestBody @Valid CustomerComplaintPayForm form) {
        BaseService nodeService = NodeServiceStrategyFactory.getInstance(NodeStatusEnum.PAY_CUSTOMER);
        return nodeService.createNode(form, NodeStatusEnum.PAY_CUSTOMER);
    }

    /**
     * 代理赔付
     */
    @PostMapping(value = "/proxyPay")
    public CustomerComplaintNode proxyPay(@RequestBody @Valid CustomerComplaintPayForm form) {
        BaseService nodeService = NodeServiceStrategyFactory.getInstance(NodeStatusEnum.PROXY_PAY);
        return nodeService.createNode(form, NodeStatusEnum.PROXY_PAY);
    }


    /**
     * 办结
     */
    @PostMapping(value = "/processed")
    public CustomerComplaintNode processed(@RequestBody @Valid CustomerComplaintNodeForm form) {
        BaseService nodeService = NodeServiceStrategyFactory.getInstance(NodeStatusEnum.PROCESSED);
        return nodeService.createNode(form, NodeStatusEnum.PROCESSED);
    }

}
