package com.pnt.mobileshop.serviceImpl;

import com.pnt.mobileshop.enity.Role;
import com.pnt.mobileshop.repository.RoleRepository;
import com.pnt.mobileshop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }
}
