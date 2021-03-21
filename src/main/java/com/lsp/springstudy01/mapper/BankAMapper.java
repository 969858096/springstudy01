package com.lsp.springstudy01.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

//@Mapper
public interface BankAMapper {
    @Update("update bank_a set money = money - #{money} where userId = #{userId}")
    void deleteMoney(String userId,Long money);

    @Insert("insert into bank_a (userId,money) value (#{userId},#{money})")
    void addMoney(String userId,Long money);
}
