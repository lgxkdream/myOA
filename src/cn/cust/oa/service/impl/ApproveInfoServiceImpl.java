package cn.cust.oa.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cust.oa.base.DaoSupportImpl;
import cn.cust.oa.domain.ApproveInfo;
import cn.cust.oa.service.ApproveInfoService;

@Service
@Transactional
public class ApproveInfoServiceImpl extends DaoSupportImpl<ApproveInfo>
		implements ApproveInfoService {

}
