package com.imooc.merchants.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * <h1>商户对象模型</h1>
 * @author BobJiang
 * @version 1.0
 * @date 2020/2/18 16:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "merchants")
public class Merchants {
    /** 商户id，主键 */
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false)
    private Integer id;

    /** 商户名称，全局唯一 */
    @Basic
    @Column(name = "name",unique = true,nullable = false)
    private String name;

    /** 商户logo */
    private String logoUrl;

    /** 商户营业执照 */
    private String businessLicenseUrl;

    /** 商户联系电话 */
    private String phone;

    /** 商户地址 */
    private String address;

    /** 商户是否通过审核 */
    private Boolean isAudit=false;
}
