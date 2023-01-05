package com.lixuan.customerworkorder.form;

import cn.hutool.json.JSONArray;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lixuan
 * @date 2022-04-01 11:46
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerComplaintNodeForm extends CustomerComplaintBaseForm {


    /**
     * 客诉节点id
     */
    private String customerComplaintNodeId;

    /**
     * 提单号
     */
    private String ladingNumber;

    /**
     * 节点内容 （当为 “确认方案” 时，当前属性会强转为 boolean (客户接受，客户不接受)）
     */
    private String nodeContent;

    /**
     * 节点内容描述、备注
     */
    private String nodeContentRemarks;

    /**
     * 代理赔付 0 false 1 true
     */
    private Boolean proxyPay;

    /**
     * 处理者（信息审核人，方案处理人）
     */
    private String processorUserId;

    /**
     * 附件
     */
    private JSONArray enclosure;

}
