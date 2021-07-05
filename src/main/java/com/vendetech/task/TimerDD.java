package com.vendetech.task;

import com.vendetech.common.utils.JobConstant;
import com.vendetech.common.utils.SecurityUtils;
import com.vendetech.common.utils.StringUtils;
import com.vendetech.framework.dingtalk.ali.Methods;
import com.vendetech.service.ISysOperLogService;
import com.vendetech.service.ISysUserService;
import com.vendetech.vo.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.taobao.api.ApiException;

import java.util.List;

/**
 * @author vendetech
 */
@Component
@EnableScheduling
@Controller
@EnableAsync
public class TimerDD {

    protected final Logger logger = LoggerFactory.getLogger(TimerDD.class);
    @Autowired
    private ISysUserService userService;

    /**
     * Daily 从钉钉全量更新员工花名册数据到TSMS系统。
     * 根据员工号检查，发现新员工则插入，其余的更新。
     */
    @Async
    @Scheduled(cron = "0 20 0/2 * * ? ") // 每天0点20分开始，每两个小时更新一次
    // @Scheduled(fixedRate = 60000000)
    public void updateSysUser() throws ApiException {

        List<SysUser> sysUsers = Methods.getUsers();
        logger.info("SysUsers qty" + sysUsers.size());
        // 根据员工表批量更新系统用户
        userService.updateSysUserByTask(sysUsers);

    }

    @Scheduled(fixedRate = 600000) // 10分钟跑一次
    public void resetUserErrorCount() {
        userService.resetUserErrorCount();
    }

    public static void main(String[] args) throws ApiException {
        // List<Employee> notInSysUsersEmpl = new ArrayList<>();
        // List<SysUser> notInSysUsers = new ArrayList<>();
        // List<SysUserRole> notInSysRoles = new ArrayList<>();
        // List<Employee> employees = new ArrayList<>();
        // List<SysUser> sysUsers = new ArrayList<>();
        // for (long i = 0; i < 10; i++) {
        //     Employee e = new Employee();
        //     e.setEmployeeId(i);
        //     SysUser u = new SysUser();
        //     u.setEmployeeId(i + 3);
        //
        //     employees.add(e);
        //     sysUsers.add(u);
        // }
        //
        // for(SysUser sysUser : sysUsers) {
        //     employees = employees.stream().filter(employee ->
        //             !(sysUser.getEmployeeId().equals(employee.getEmployeeId()))).collect(Collectors.toList());
        // }
        // logger.info();(notInSysUsersEmpl);

        // try {
        // 	List<String> queryDimissionList = Methods.queryDimission(0L);
        // 	if(queryDimissionList.size()>0){
        // 		logger.info();(queryDimissionList.size());
        // 		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/smartwork/hrm/employee/listdimission");
        // 		OapiSmartworkHrmEmployeeListdimissionRequest req = new OapiSmartworkHrmEmployeeListdimissionRequest();
        // 		req.setUseridList(queryDimissionList.toString().replace(" ", "").replace("[", "").replace("]", ""));
        // 		OapiSmartworkHrmEmployeeListdimissionResponse response = client.execute(req , AuthHelper.getAccessToken());
        // 		if(0==response.getErrcode()){
        // 			List<EmpDimissionInfoVo> ddUserIdList = response.getResult();
        // 			Date date =new Date(1575043200000L);
        // 			tsmsService.batchUpdateLastWorkDate(ddUserIdList);
        // 		}else{
        // 			throw new ApiException(response.getErrmsg());
        // 		}
        // 	}
        // } catch (Exception e) {
        // 	e.printStackTrace();
        // }
        // logger.info();("0:"+new Date());
        // Timerdd dd  = new Timerdd();
        // dd.updateAttendance();
        // logger.info();("1:"+new Date());
        // List<Map<String, Object>> attds = getAttendances(users);
        // logger.info();("2:"+new Date());
        // int size = attds.size();
        // logger.info();(size);
    }
}
