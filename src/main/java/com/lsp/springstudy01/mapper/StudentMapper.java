package com.lsp.springstudy01.mapper;
import com.lsp.springstudy01.bean.Student;
import org.apache.ibatis.annotations.*;


/**
 * @FileName: StudentDao
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/11/18 07:44
 */
//@Mapper
public interface StudentMapper{

    @Insert("insert into student values(#{student.id},#{student.name},#{student.age})")
    void add(@Param("student") Student student);

    @Delete("delete from student where id = #{id}")
    void delete(String id);

    @Select("select * from student where id = #{id}")
    Student get(String id);
}
