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
 * 客诉类型表
 * </p>
 *
 * @author lixuan
 * @since 2023-01-05 11:48:36
 */
@Getter
@Setter
@TableName("biz_customer_complaint_type")
public class CustomerComplaintType extends BaseEntity<CustomerComplaintType> {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 客诉id
     */
    @TableId("customer_complaint_type_id")
    private String customerComplaintTypeId;

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
