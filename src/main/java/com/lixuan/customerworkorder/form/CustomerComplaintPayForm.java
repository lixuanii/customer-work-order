package com.lixuan.customerworkorder.form;

import cn.hutool.json.JSONArray;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 客诉赔付form
 *
 * @author lixuan
 * @date 2022-04-01 13:46
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerComplaintPayForm extends CustomerComplaintBaseForm {


    /**
     * 赔付类型
     */
    private String customerComplaintNodePayType;
    /**
     * 赔付金额
     */
    private BigDecimal payAmount;
    /**
     * 货币代码
     */
    private String currencyCode;
    /**
     * 节点内容描述、备注
     */
    private String nodeContentRemarks;
    /**
     * 附件
     */
    private JSONArray enclosure;

}
