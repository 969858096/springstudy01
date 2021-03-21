package com.lsp.springstudy01.spring事务.编程式事务;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * @FileName: MyTranctionalUtil
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/11/20 07:21
 */
//@Component
@Slf4j
public class MyTranctionalUtil {

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;


    public TransactionStatus begin(){
        log.info("---开启事务---");
        return dataSourceTransactionManager.getTransaction(new DefaultTransactionDefinition());
    }

    public void commit(TransactionStatus transactionStatus){
        dataSourceTransactionManager.commit(transactionStatus);
        log.info("---事务提交---");
    }

    public void rollBack(TransactionStatus transactionStatus){
        dataSourceTransactionManager.rollback(transactionStatus);
        log.info("---事务回滚---");
    }
}
