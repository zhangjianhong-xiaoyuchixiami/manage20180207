package org.qydata.service.impl;

import org.qydata.entity.Role;
import org.qydata.entity.RoleUser;
import org.qydata.mapper.RoleMapper;
import org.qydata.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/12/3.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRole() throws Exception {
        return roleMapper.findAllRole();
    }

    @Override
    public boolean addRoleUser(RoleUser roleUser) throws Exception {
        return roleMapper.addRoleUser(roleUser);
    }
}
