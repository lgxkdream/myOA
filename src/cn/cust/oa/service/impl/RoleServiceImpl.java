package cn.cust.oa.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cust.oa.base.DaoSupportImpl;
import cn.cust.oa.domain.Role;
import cn.cust.oa.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl extends DaoSupportImpl<Role> implements
		RoleService {
	
}
