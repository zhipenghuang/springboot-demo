package com.hzp.demo.repository;


import com.hzp.demo.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {


    @Query(value = "update User set name  = ?2 where id = ?1", nativeQuery = true)
    @Modifying
    int updateById(Long id, String name);

    @Query(value = "update User set gender  = ?2 where name = ?1", nativeQuery = true)
    @Modifying
    int updateGenderById(String name, Integer gender);

    @Query(value = "select * from USER where name = ?1 for update ", nativeQuery = true)
    User selectById(@Param("name") String name);

    @Query(value = "select * from USER where name = ?1 ", nativeQuery = true)
    User selectById2(@Param("name") String name);
}
