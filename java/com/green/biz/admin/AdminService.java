package com.green.biz.admin;

public interface AdminService {

	int workerCheck(WorkerVO vo);

	WorkerVO getEmployee(String id);

}