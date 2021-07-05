package com.vendetech.service.impl;

import com.vendetech.common.constant.UserConstants;
import com.vendetech.common.exception.CustomException;
import com.vendetech.common.utils.SecurityUtils;
import com.vendetech.common.utils.StringUtils;
import com.vendetech.framework.aspectj.lang.annotation.DataScope;
import com.vendetech.framework.aspectj.lang.annotation.DataSource;
import com.vendetech.framework.aspectj.lang.enums.DataSourceType;
import com.vendetech.mapper.SysUserMapper;
import com.vendetech.service.ISysUserService;
import com.vendetech.vo.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 用户 业务层处理
 *
 * @author vendetech
 */
@Service
public class SysUserServiceImpl implements ISysUserService {
    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserMapper sysUserMapper;


    /**
     * 根据条件分页查询用户列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<SysUser> selectUserList(SysUser user) {
        return sysUserMapper.selectUserList(user);
    }

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserByUserName(String userName) {
        return sysUserMapper.selectUserByUserName(userName);
    }

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserById(Long userId) {
        return sysUserMapper.selectUserById(userId);
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param userName 用户名称
     * @return 结果
     */
    @Override
    public String checkUserNameUnique(String userName) {
        int count = sysUserMapper.checkUserNameUnique(userName);
        if (count > 0) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param user 用户信息
     */
    @Override
    public String checkPhoneUnique(SysUser user) {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        SysUser info = sysUserMapper.checkPhoneUnique(user.getMobile());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验email是否唯一
     *
     * @param user 用户信息
     * @return
     */
    @Override
    public String checkEmailUnique(SysUser user) {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        SysUser info = sysUserMapper.checkEmailUnique(user.getEmail());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验用户是否允许操作
     *
     * @param user 用户信息
     */
    @Override
    public void checkUserAllowed(SysUser user) {
        if (StringUtils.isNotNull(user.getUserId()) && user.isAdmin()) {
            throw new CustomException("不允许操作超级管理员用户");
        }
    }

    /**
     * 新增保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertUser(SysUser user) {
        // 新增用户信息
        return sysUserMapper.insertUser(user);
    }

    /**
     * 修改保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateUser(SysUser user) {
        return sysUserMapper.updateUser(user);
    }

    /**
     * 修改用户状态
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUserStatus(SysUser user) {
        return sysUserMapper.updateUser(user);
    }

    @Override
    public List<HashMap<String, Object>> selectByUserList(String param) {
        return sysUserMapper.selectByUserList(param);
    }

    @Override
    public List<SysUser> selectByUserName(String userName) {
        return sysUserMapper.selectByUserName(userName);
    }

    @Override
    public List<HashMap<String, Object>> selectUserListByKeyword(String param) {
        return sysUserMapper.selectUserListByKeyword(param);
    }

    @Override
    public int saveAccount(SysUser createUserDTO) {
        SysUser user = new SysUser();
        user.setUserName(createUserDTO.getUserName());
        user.setNickName(createUserDTO.getNickName());
        user.setEmail(createUserDTO.getEmail());
        user.setAvatar(createUserDTO.getAvatar());
        user.setPassword(SecurityUtils.encryptPassword(createUserDTO.getPassword()));
        sysUserMapper.saveAccount(user);
        return 0;
    }

    @Override
    public int deleteByUserId(Long userId) {
        return sysUserMapper.updateByStatus(userId);
    }

    @Override
    public int updateRoleByUserId(SysUser createUserDTO) {
        SysUser sysUser = new SysUser();
        sysUser.setEmail(createUserDTO.getEmail());
        sysUser.setUserId(createUserDTO.getUserId());
        return sysUserMapper.updateUser(sysUser);
    }

    /**
     * 修改用户基本信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUserProfile(SysUser user) {
        return sysUserMapper.updateUser(user);
    }

    /**
     * 修改用户头像
     *
     * @param userName 用户名
     * @param avatar   头像地址
     * @return 结果
     */
    @Override
    public boolean updateUserAvatar(String userName, String avatar) {
        return sysUserMapper.updateUserAvatar(userName, avatar) > 0;
    }

    /**
     * 重置用户密码
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int resetPwd(SysUser user) {
        return sysUserMapper.updateUser(user);
    }

    /**
     * 重置用户密码
     *
     * @param userName 用户名
     * @param password 密码
     * @return 结果
     */
    @Override
    public int resetUserPwd(String userName, String password) {
        return sysUserMapper.resetUserPwd(userName, password);
    }

    /**
     * 通过用户ID删除用户
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public int deleteUserById(Long userId) {
        return sysUserMapper.deleteUserById(userId);
    }

    /**
     * 批量删除用户信息
     *
     * @param userIds 需要删除的用户ID
     * @return 结果
     */
    @Override
    public int deleteUserByIds(Long[] userIds) {
        for (Long userId : userIds) {
            checkUserAllowed(new SysUser(userId));
        }
        return sysUserMapper.deleteUserByIds(userIds);
    }

    /**
     * 导入用户数据
     *
     * @param userList        用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName        操作用户
     * @return 结果
     */
    @Override
    public String importUser(List<SysUser> userList, Boolean isUpdateSupport, String operName) {
        if (StringUtils.isNull(userList) || userList.size() == 0) {
            throw new CustomException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (SysUser user : userList) {
            try {
                // 验证是否存在这个用户
                SysUser u = sysUserMapper.selectUserByUserName(user.getUserName());
                if (StringUtils.isNull(u)) {
                    user.setPassword(SecurityUtils.encryptPassword(user.getUserName()));
                    user.setCreateBy(operName);
                    this.insertUser(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getUserName() + " 导入成功");
                } else if (isUpdateSupport) {
                    user.setUpdateBy(operName);
                    this.updateUser(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getUserName() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、账号 " + user.getUserName() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + user.getUserName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new CustomException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }


    @Override
    public int getErrorCount(String username) {
        return sysUserMapper.getErrorCount(username);
    }


    @Override
    public void updateErrorCount(Map<String, Object> param) {
        sysUserMapper.updateErrorCount(param);
    }

    @Override
    public List<SysUser> getByUserName(String loginName) {
        List<SysUser> byUserName = sysUserMapper.getByUserName(loginName);
        return byUserName;
    }

    @Override
    public List<SysUser> selectDelFlag(String userName) {
        return sysUserMapper.selectDelFlag(userName);
    }

    @Override
    @DataSource(DataSourceType.MASTER)
    public List<SysUser> getMasterUser() {
        SysUser user = new SysUser();
        return sysUserMapper.selectUserList(user);
    }

    @Override
    @DataSource(DataSourceType.SLAVE)
    public List<SysUser> getSlaveUser() {
        SysUser user = new SysUser();
        return sysUserMapper.selectUserList(user);
    }

    @Override
    public List<SysUser> getUser() {
        SysUser user = new SysUser();
        return sysUserMapper.selectUserList(user);
    }

    @Override
    public SysUser selectByPassword(Long userId) {
        return sysUserMapper.selectByPassword(userId);
    }

    @Override
    public SysUser selectByEmail(Long userId) {
        return sysUserMapper.selectByEmail(userId);
    }

    @Override
    public List<SysUser> selectUserListForBatchInsert() {
        return sysUserMapper.selectUserListForBatchInsert();
    }

    @Override
    public int updateSysUserByTask(List<SysUser> sysUsers) {
        List<SysUser> existSysUsers = this.selectUserListForBatchInsert();

        for (SysUser existSysUser : existSysUsers) {
            sysUsers = sysUsers.stream().filter(sysUser ->
                    !(existSysUser.getUserName().equals(sysUser.getUserName()))).collect(Collectors.toList());
        }

        log.info("prepare to insert sysUser: " + sysUsers.size());

        for (SysUser user : sysUsers) {
            String password = user.getUserName().substring(3); // 截取后6位
            user.setPassword(SecurityUtils.encryptPassword(password));
            user.setCreateBy("systemTaskJob");
            user.setCreateTime(new Date());
        }

        if (sysUsers.isEmpty()) {
            log.info("没有需要更新的用户数据。");
            return 0;
        }

        int userCount = sysUserMapper.batchInsertSysUser(sysUsers);
        log.info("sys_user表成功更新 " + userCount + " 条数据");
        return 0;
    }

    @Override
    public void resetUserErrorCount() {
        sysUserMapper.resetUserErrorCount();
    }

}
