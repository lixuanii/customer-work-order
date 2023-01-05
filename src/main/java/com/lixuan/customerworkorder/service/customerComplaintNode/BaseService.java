package com.lixuan.customerworkorder.service.customerComplaintNode;

import com.lixuan.customerworkorder.entity.CustomerComplaintNode;
import com.lixuan.customerworkorder.enums.NodeStatusEnum;
import com.lixuan.customerworkorder.form.CustomerComplaintBaseForm;


/**
 * @author lixuan
 * @date 2022-03-31 15:27
 */
public interface BaseService {

    /**
     * 创建节点
     *
     * @param form           请求值
     * @param nodeStatusEnum 子节点状态
     * @return CustomerComplaintNodeModel
     * @author lixuan
     * @date 2022/3/31 15:28
     **/
    CustomerComplaintNode createNode(CustomerComplaintBaseForm form, NodeStatusEnum nodeStatusEnum);
}
