package com.lsp.springstudy01.dao;

import com.lsp.springstudy01.mapper.BankBMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @FileName: BankBDao
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/11/18 22:30
 */
//@Service
public class BankBDao {
    @Autowired
    private BankBMapper bankBMapper;

    public void addMoney(String userId,Long money) {
        bankBMapper.addMoney(userId,money);
    }
}
