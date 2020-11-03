package com.green.biz.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.admin.AdminService;
import com.green.biz.admin.WorkerVO;
@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDAO admin;
	

	public int workerCheck(WorkerVO vo) {
		int result = -1;
		String pwd = admin.workerCheck(vo);
		
		if (pwd == null) {
			result = -1;
		}else if (pwd.equals(vo.getPwd())) {
			result = 1;
		} else {
			result = 0;
		} 
		
		return result;
	}


	public WorkerVO getEmployee(String id) {
		return admin.getEmployee(id);
	}

}
