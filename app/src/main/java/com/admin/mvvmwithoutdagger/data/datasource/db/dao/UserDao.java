package com.admin.mvvmwithoutdagger.data.datasource.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.admin.mvvmwithoutdagger.data.model.db.UserDataModel;

import java.util.List;

@Dao
public interface UserDao {

    @Delete
    void delete(UserDataModel user);

    @Query("SELECT * FROM users WHERE name LIKE :name LIMIT 1")
    UserDataModel findByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserDataModel user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<UserDataModel> users);

    @Query("SELECT * FROM users")
    List<UserDataModel> loadAll();

    @Query("SELECT * FROM users WHERE id IN (:userIds)")
    List<UserDataModel> loadAllByIds(List<Integer> userIds);
}