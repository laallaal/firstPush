package com.qfedu.mapper;

import com.qfedu.entry.Admin;


public interface AdminMapper {

    int selectAdminByUserNameAndPassword(Admin admin);
}
