package com.khem.appspring.springphoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.khem.appspring.springphoneshop.config.security.AuthUser;
import com.khem.appspring.springphoneshop.model.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    AuthUser toUser(User user);
}
