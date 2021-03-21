package com.lsp.springstudy01.spring事务.编程式事务;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @FileName: TranctionalUtil
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/11/20 07:14
 */
//@Component
//@Aspect
@Slf4j
public class MyTranctionalAspect {

    @Autowired
    private MyTranctionalUtil tranctionalUtil;


    @Around("execution(* com.lsp.springstudy01.spring事务.编程式事务.CodeTrancationController.test1())")
    public void aroundInform(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        TransactionStatus transactionStatus = tranctionalUtil.begin();
        proceedingJoinPoint.proceed();
        tranctionalUtil.commit(transactionStatus);
    }

    @AfterThrowing("execution(* com.lsp.springstudy01.spring事务.编程式事务.CodeTrancationController.*())")
    public void throwExceptionInform() throws Throwable {
        TransactionStatus transactionStatus = TransactionAspectSupport.currentTransactionStatus();
        if (ObjectUtils.isNotNull(transactionStatus)){
            transactionStatus.setRollbackOnly();
        }else {
            log.error("当前事务为空");
        }
    }

}
