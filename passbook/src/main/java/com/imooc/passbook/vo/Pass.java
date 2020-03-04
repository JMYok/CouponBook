package com.imooc.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <h2>用户领取的优惠券</h2>
 * @author BobJiang
 * @version 1.0
 * @date 2020/2/23 11:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pass {

    /** 用户id */
    private  Long useId;

    /** pass 在 Hbase 中的 rowkey */
    private String rowKey;

    /** PassTemplate 在Hbase中的RowKey */
    private String templateId;

    /** 优惠券token 有可能是null  则填充 -1 */
    private String token;

    /** 领取日期 */
    private Date assignedDate;

    /** 消费日期 ，不为空表示已经被消费  */
    private Date conDate;

}
