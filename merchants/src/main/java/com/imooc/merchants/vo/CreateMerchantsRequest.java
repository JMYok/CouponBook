package com.imooc.merchants.vo;

import com.imooc.merchants.constant.ErrorCode;
import com.imooc.merchants.dao.MerchantsDao;
import com.imooc.merchants.entity.Merchants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1>创建商户请求对象</h1>
 * @author BobJiang
 * @version 1.0
 * @date 2020/2/19 11:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMerchantsRequest {

    /** 商户名称 */
    private String name;

    /** 商户logo */
    private String logoUrl;

    /** 商户营业执照 */
    private String businessLicenseUrl;

    /** 商户联系电话 */
    private String phone;

    /** 商户地址 */
    private String address;

    /**
     * <h2>验证请求的有效性</h2>
     * @param merchantsDao {@link MerchantsDao}
     * @return {@link ErrorCode}
     */
    public ErrorCode validate(MerchantsDao merchantsDao){
        if(this.name == null){
            return ErrorCode.EMPTY_NAME;
        }
        if(merchantsDao.findByName(this.name)!=null){
            return ErrorCode.DUPLICATE_NAME;
        }
        if(this.logoUrl == null){
            return ErrorCode.EMPTY_LOGO;
        }
        if(this.businessLicenseUrl == null){
            return ErrorCode.EMPTY_BUSSINESS_LICENSE;
        }
        if(this.address == null){
            return ErrorCode.EMPTY_ADDRESS;
        }
        /** 可增加校验 */
        if(this.phone == null){
            return ErrorCode.EMPYT_PHONE;
        }
        return ErrorCode.SUCEESS;
    }

    /**
     * <h2>将请求对象转换为商户对象</h2>
     * @return {@link Merchants}
     */
    public Merchants toMerchants(){

        Merchants merchants = new Merchants();

        merchants.setName(name);
        merchants.setLogoUrl(logoUrl);
        merchants.setBusinessLicenseUrl(businessLicenseUrl);
        merchants.setPhone(phone);
        merchants.setAddress(address);

        return merchants;
    }
}
