package com.imooc.passbook.controller;

import com.imooc.passbook.log.LogConstants;
import com.imooc.passbook.log.LogGenerator;
import com.imooc.passbook.service.IFeedbackService;
import com.imooc.passbook.service.IGainPassTemplateService;
import com.imooc.passbook.service.IIventoryService;
import com.imooc.passbook.service.IUserPassService;
import com.imooc.passbook.vo.Feedback;
import com.imooc.passbook.vo.GainPassTemplateRequest;
import com.imooc.passbook.vo.Pass;
import com.imooc.passbook.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <h1>PassBook Rest Controller</h1>
 * @author BobJiang
 * @version 1.0
 * @date 2020/2/26 10:50
 */
@RestController
@Slf4j
@RequestMapping("/passbook")
public class PassbookController {

    /** 用户优惠券服务 */
    private final IUserPassService userPassService;

    /** 优惠券库存服务 */
    private final IIventoryService inventoryService;

    /** 领取优惠券服务 */
    private final IGainPassTemplateService gainPassTemplateService;

    /** 反馈服务 */
    private final IFeedbackService feedbackService;

    private final HttpServletRequest httpServletRequest;

    @Autowired
    public PassbookController(IUserPassService userPassService, IIventoryService inventoryService, IGainPassTemplateService gainPassTemplateService, IFeedbackService feedbackService, HttpServletRequest httpServletRequest) {
        this.userPassService = userPassService;
        this.inventoryService = inventoryService;
        this.gainPassTemplateService = gainPassTemplateService;
        this.feedbackService = feedbackService;
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * <h2>获取用户个人的优惠券信息</h2>
     * @param userId 用户 id
     * @return {@link Response}
     * @throws Exception
     */
    @ResponseBody
    @GetMapping("/userpassinfo")
    Response userPassInfo(Long userId) throws Exception{
        LogGenerator.genlog(
                httpServletRequest,
                userId,
                LogConstants.ActionName.USER_PASS_INFO,
                null
        );
        return userPassService.getUserPassInfo(userId);
    }

    /**
     * <h2>获取用户使用了的优惠券信息</h2>
     * @param userId 用户 id
     * @return {@link Response}
     * @throws Exception
     */
    @ResponseBody
    @GetMapping("/userusedpassinfo")
    Response userUsedPassInfo(Long userId) throws Exception{

        LogGenerator.genlog(
                httpServletRequest,
                userId,LogConstants.ActionName.USER_PASS_INFO,
                null
        );
        return userPassService.getUserPassInfo(userId);
    }

    /**
     * <h2>用户使用优惠券</h2>
     * @param pass {@link Pass}
     * @return {@link Response}
     */
    @ResponseBody
    @PostMapping("/userusepass")
    Response userUsePass(Pass pass){

        LogGenerator.genlog(
                httpServletRequest,
                pass.getUseId(),
                LogConstants.ActionName.USER_USE_PASS,
                pass
        );
        return userPassService.userUsePass(pass);
    }

    /**
     * <h2>获取库存信息</h2>
     * @param userId 用户 id
     * @return {@link Response}
     * @throws Exception
     */
    @ResponseBody
    @GetMapping("/inventoryinfo")
    Response inventoryInfo(Long userId) throws Exception{

        LogGenerator.genlog(
                httpServletRequest,
                userId,
                LogConstants.ActionName.INVENTORY_INFO,
                null
        );
        return inventoryService.genInventoryInfo(userId);
    }

    /**
     * <h2>用户领取优惠券</h2>
     * @param request {@link GainPassTemplateRequest}
     * @return {@link Response}
     * @throws Exception
     */
    @ResponseBody
    @PostMapping("/gainpasstemplate")
    Response gainPassTemplate(@RequestBody GainPassTemplateRequest request) throws Exception{

        LogGenerator.genlog(
                httpServletRequest,
                request.getUserId(),
                LogConstants.ActionName.GAIN_PASS_TEMPLATE,
                request
        );

        return gainPassTemplateService.gainPassTemplate(request);
    }

    /**
     * <h2>用户创建评论</h2>
     * @param feedback {@link Feedback}
     * @return {@link Response}
     */
    @ResponseBody
    @PostMapping("/createfeedback")
    Response createFeedback(@RequestBody Feedback feedback){
        LogGenerator.genlog(
                httpServletRequest,
                feedback.getUserId(),
                LogConstants.ActionName.CREATE_FEEDBACK,
                feedback
        );
        return feedbackService.createFeedback(feedback);
    }

    /**
     * <h2>用户获取评论信息</h2>
     * @param userId 用户 id
     * @return {@link Response}
     */
    @ResponseBody
    @GetMapping("/getfeedback")
    Response getFeedback(Long userId){
        LogGenerator.genlog(
                httpServletRequest,
                userId,
                LogConstants.ActionName.GET_FEEDBACK,
                null
        );
        return  feedbackService.getFeedback(userId);
    }

    /**
     * <h2>异常演示接口</h2>
     * @return {@link Response}
     * @throws Exception
     */
    Response exception() throws Exception{
        throw  new Exception("Welcome to imooc");
    }
}
