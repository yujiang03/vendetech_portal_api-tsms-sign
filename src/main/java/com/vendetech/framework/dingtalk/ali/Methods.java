package com.vendetech.framework.dingtalk.ali;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiDepartmentListRequest;
import com.dingtalk.api.request.OapiUserListbypageRequest;
import com.dingtalk.api.response.OapiDepartmentListResponse;
import com.dingtalk.api.response.OapiUserListbypageResponse;
import com.dingtalk.api.response.OapiUserListbypageResponse.Userlist;
import com.taobao.api.ApiException;
import com.vendetech.common.utils.StringUtils;
import com.vendetech.vo.SysUser;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class Methods {

    /**
     * 获取企业部门
     */
    private static List<String> getDepartments() throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/department/list");
        OapiDepartmentListRequest request = new OapiDepartmentListRequest();
        request.setHttpMethod("GET");
        OapiDepartmentListResponse response = client.execute(request, AuthHelper.getAccessToken());
        List<String> departments = new ArrayList<String>();
        if (response.getErrcode() == 0) {
            for (int i = 0; i < response.getDepartment().size(); i++) {
                departments.add(response.getDepartment().get(i).getId().toString());
            }
        } else {
            departments = null;
            throw new ApiException(response.getErrmsg());
        }
        return departments;
    }

    /**
     * list<SysUser> 去重
     */
    private static List<SysUser> removeDuplicateWithSysUser(List<SysUser> list) {
        Set set = new HashSet();
        List<SysUser> newList = new ArrayList<>();
        for (SysUser element : list) {
            if (set.add(element.getUserName())) {
                newList.add(element);
            }
        }
        list.clear();
        list.addAll(newList);
        return list;
    }

    /**
     * 获取部门人员信息
     */
    public static List<SysUser> getUsers() throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/listbypage");
        OapiUserListbypageRequest request = new OapiUserListbypageRequest();
        List<String> departmentIds = getDepartments();
        List<SysUser> userList = new ArrayList<>();

        for (String departmentId : departmentIds) {
            for (int j = 0; j < 5; j++) {    // 假设部门下属人员都不超过500
                request.setDepartmentId(Long.parseLong(departmentId));
                request.setOffset(100 * (long) j);
                request.setSize(100L);
                request.setOrder("entry_desc");
                request.setHttpMethod("GET");
                OapiUserListbypageResponse execute = client.execute(request, AuthHelper.getAccessToken());
                if (execute.getUserlist() == null) {
                    log.warn("execute.getUserlist()  isnull");
                    continue;
                }
                for (int x = 0; x < execute.getUserlist().size(); x++) {
                    Userlist dUser = execute.getUserlist().get(x);

                    if(StringUtils.isBlank(dUser.getJobnumber())) {
                        continue;
                    }

                    SysUser user = new SysUser();
                    user.setUserName(dUser.getJobnumber());
                    user.setDdUserId(dUser.getUserid());
                    user.setNickName(dUser.getName());
                    user.setMobile(dUser.getMobile());
                    user.setAvatar(dUser.getAvatar());
                    user.setStatus("0");
                    user.setUserType("01");
                    user.setEmail(dUser.getOrgEmail());
                    userList.add(user);
                }
            }
        }
        return removeDuplicateWithSysUser(userList);
    }

    public static void main(String[] args) throws ApiException {

        List<SysUser> list = Methods.getUsers();
        System.out.println(list);

    }

}
