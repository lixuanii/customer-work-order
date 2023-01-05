package com.lixuan.customerworkorder.form;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 工单转交 form
 * @author lixuan
 * @date 2022-03-31 14:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerComplaintTransferForm  extends CustomerComplaintBaseForm {

    /**
     * 转交处理人
     */
    private String transferUserId;

    /**
     * 节点内容描述、备注
     */
    private String nodeContentRemarks;
}
