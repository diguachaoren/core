package com.digua.repository.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.digua.repository.db.entity.UserEntity;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM UserEntity")
    List<UserEntity> getAll();

    @Query("SELECT * FROM UserEntity WHERE uid IN (:userIds)")
    List<UserEntity> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM UserEntity WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    UserEntity findByName(String first, String last);

    @Insert
    void insertAll(UserEntity... userEntities);

    @Delete
    void delete(UserEntity userEntity);
}
