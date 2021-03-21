package com.lsp.springstudy01.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

//@Mapper
public interface BankBMapper {

    @Update("update bank_b set money = money + #{money} where userId = #{userId}")
    void addMoney(String userId,Long money);
}
