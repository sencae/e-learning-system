package com.e_learning_system.dao;

import com.e_learning_system.entities.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserInfoRepository extends JpaRepository<UserInfoEntity,Long> {
    @Modifying
    @Query("update UserInfoEntity ui set ui.avatarUrl=null where ui.avatarUrl=?1")
    int clearPageImgUrl(String url);

    @Modifying
    @Query("update UserInfoEntity ui set ui.avatarUrl=?1 where ui.id=?2")
    int setPageImgUrl(String url,Long id);
}
