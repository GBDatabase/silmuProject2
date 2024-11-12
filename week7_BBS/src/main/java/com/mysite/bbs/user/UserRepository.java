package com.mysite.bbs.user;

import org.springframework.data.jpa.repository.JpaRepository;

//siteUser 테이블만 사용. 타입은 long
public interface UserRepository extends JpaRepository<SiteUser, Long> {

}
