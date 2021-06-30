package com.work.seckill.mybatis.dao;

import com.work.seckill.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    /**
     * 根据id查找user
     * @param id user的id
     * @return
     */
    @Select("SELECT * FROM sk_user WHERE id = #{id}")
    User getById(long id);

    /**
     * 根据手机号查找user
     * @param phoneNum
     * @return
     */
    @Select("SELECT * FROM sk_user WHERE phone_num = #{phoneNum}")
    User getByPhoneNum(long phoneNum);

    /**
     * 更新密码
     * @param newUser
     */
    @Update("UPDATE sk_user SET password = #{password} WHERE id = #{id}")
    void update(User newUser);

    @Insert("INSERT INTO sk_user (nickname,phone_num,password,salt,head,register_date) " +
            "VALUES (#{nickname},#{phoneNum},#{password},#{salt},#{head},#{registerDate, typeHandler=DateTypeHandler})")
    void add(User user);

}
