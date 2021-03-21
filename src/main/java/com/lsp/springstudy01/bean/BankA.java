package com.lsp.springstudy01.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @FileName: BankA
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/11/18 22:21
 */
@Component
@Data
public class BankA {

    private String userId;
    private Long money;
    private BankB bankB = new BankB();

    public void setBankB(BankB bankB) {
        this.bankB = bankB;
    }
}
