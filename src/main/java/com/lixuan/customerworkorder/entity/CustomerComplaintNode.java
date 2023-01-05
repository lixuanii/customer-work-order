package com.lixuan.customerworkorder.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lixuan.customerworkorder.entity.base.BaseEntity;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 客诉信息节点表
 * </p>
 *
 * @author lixuan
 * @since 2023-01-05 11:48:36
 */
@Getter
@Setter
@TableName("biz_customer_complaint_node")
public class CustomerComplaintNode extends BaseEntity<CustomerComplaintNode> {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 客诉节点id
     */
    @TableId("customer_complaint_node_id")
    private String customerComplaintNodeId;

    /**
     * 客诉详情id
     */
    @TableField("customer_complaint_info_id")
    private String customerComplaintInfoId;

    /**
     * 客诉节点状态（0 已核实，1 转交审核，2 已审核(待出方案)，3 转交方案，4 待确认方案，5 确认方案，6 赔付客户，7 代理赔付，8 已办结）
     */
    @TableField("customer_complaint_node_status")
    private Integer customerComplaintNodeStatus;

    /**
     * 节点内容
     */
    @TableField("node_content")
    private String nodeContent;

    /**
     * 节点内容备注、描述
     */
    @TableField("node_content_remarks")
    private String nodeContentRemarks;

    /**
     * 赔付类型（赔付客户 payCustomers，代理赔付 proxyPay ）
     */
    @TableField("customer_complaint_node_pay_type")
    private String customerComplaintNodePayType;

    /**
     * 赔付金额
     */
    @TableField("pay_amount")
    private BigDecimal payAmount;

    /**
     * 货币代码
     */
    @TableField("currency_code")
    private String currencyCode;

    /**
     * 当前处理人
     */
    @TableField("current_user_id")
    private String currentUserId;

    /**
     * 转交处理人
     */
    @TableField("transfer_user_id")
    private String transferUserId;

    /**
     * 处理者（审核人，方案处理人）
     */
    @TableField("processor_user_id")
    private String processorUserId;

    /**
     * 附件
     */
    @TableField("enclosure")
    private String enclosure;

    /**
     * 创建时间
     */
    @TableField("created_time")
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    @TableField("updated_time")
    private LocalDateTime updatedTime;

    /**
     * 创建人id
     */
    @TableField("create_user_id")
    private String createUserId;

    /**
     * 更新人id
     */
    @TableField("update_user_id")
    private String updateUserId;

    /**
     * 删除状态
     */
    @TableField("is_delete")
    private Byte isDelete;
}
