package com.lixuan.customerworkorder.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lixuan.customerworkorder.entity.base.BaseEntity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 客诉信息表
 * </p>
 *
 * @author lixuan
 * @since 2023-01-05 11:48:35
 */
@Getter
@Setter
@TableName("biz_customer_complaint_info")
public class CustomerComplaintInfo extends BaseEntity<CustomerComplaintInfo> {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 客诉id
     */
    @TableId("customer_complaint_info_id")
    private String customerComplaintInfoId;

    /**
     * 提单id
     */
    @TableField("lading_id")
    private String ladingId;

    /**
     * 提单号
     */
    @TableField("lading_number")
    private String ladingNumber;

    /**
     * 当前节点id
     */
    @TableField("customer_complaint_node_id")
    private String customerComplaintNodeId;

    /**
     * 运输代理（公司主体id）
     */
    @TableField("group_company_id")
    private String groupCompanyId;

    /**
     * 客户（往来单位id）
     */
    @TableField("correspondent_unit_id")
    private String correspondentUnitId;

    /**
     * 客诉状态 默认0 （0待核实，1待审核，2已审核，3待确认，4已确认，5已处理）
     */
    @TableField("customer_complaint_status")
    private Integer customerComplaintStatus;

    /**
     * 赔付客户 默认0（0未赔付 1已赔付）
     */
    @TableField("pay_customers")
    private Integer payCustomers;

    /**
     * 代理赔付 默认0 （0无需赔付 1已赔付 2未赔付）
     */
    @TableField("proxy_pay")
    private Integer proxyPay;

    /**
     * 客诉类型
     */
    @TableField("customer_complaint_type")
    private String customerComplaintType;

    /**
     * 客诉原因
     */
    @TableField("customer_complaint_reason")
    private String customerComplaintReason;

    /**
     * 投诉时间
     */
    @TableField("customer_complaint_time")
    private LocalDateTime customerComplaintTime;

    /**
     * 备注
     */
    @TableField("remarks")
    private String remarks;

    /**
     * 附件
     */
    @TableField("enclosure")
    private String enclosure;

    /**
     * 航空公司
     */
    @TableField("airline_company")
    private String airlineCompany;

    /**
     * 航班号
     */
    @TableField("flight_no")
    private String flightNo;

    /**
     * eta时间
     */
    @TableField("eta_time")
    private LocalDateTime etaTime;

    /**
     * etd时间
     */
    @TableField("etd_time")
    private LocalDateTime etdTime;

    /**
     * ata时间
     */
    @TableField("ata_time")
    private LocalDateTime ataTime;

    /**
     * atd时间
     */
    @TableField("atd_time")
    private LocalDateTime atdTime;

    /**
     * 起运港
     */
    @TableField("departure_port")
    private String departurePort;

    /**
     * 目的港
     */
    @TableField("destination_port")
    private String destinationPort;

    /**
     * 预报数
     */
    @TableField("container_num")
    private Integer containerNum;

    /**
     * 起飞数(航空实际数)
     */
    @TableField("air_actual_container_num")
    private Integer airActualContainerNum;

    /**
     * 落地数
     */
    @TableField("arrive_num")
    private Integer arriveNum;

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
