package com.lsp.springstudy01.dao;

import com.lsp.springstudy01.mapper.BankAMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @FileName: BankADao
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/11/18 22:20
 */
//@Service
public class BankADao {

    @Autowired
    private BankAMapper bankAMapper;
    public void deleteMoney(String userId,Long money) {
        bankAMapper.deleteMoney(userId,money);
    }

    public void saveMoney(String userId, Long money) {
        bankAMapper.addMoney(userId,money);
    }
}
