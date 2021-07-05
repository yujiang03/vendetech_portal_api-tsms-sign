package com.vendetech.controller;

import com.vendetech.common.constant.UserConstants;
import com.vendetech.common.utils.*;
import com.vendetech.common.utils.poi.ExcelUtil;
import com.vendetech.framework.aspectj.lang.annotation.Log;
import com.vendetech.framework.aspectj.lang.enums.BusinessType;
import com.vendetech.framework.security.LoginUser;
import com.vendetech.framework.security.service.TokenService;
import com.vendetech.framework.web.controller.BaseController;
import com.vendetech.framework.web.domain.AjaxResult;
import com.vendetech.framework.web.page.TableDataInfo;
import com.vendetech.service.ISysUserService;
import com.vendetech.service.impl.kit.GetRamdom;
import com.vendetech.vo.R;
import com.vendetech.vo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

/**
 * 用户信息
 * 
 * @author vendetech
 */
@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController
{
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private TokenService tokenService;
    
    /**
     * 获取用户列表
     */
    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysUser user)
    {
        startPage();
        List<SysUser> list = sysUserService.selectUserList(user);
        return getDataTable(list);
    }

    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('system:user:export')")
    @GetMapping("/export")
    public AjaxResult export(SysUser user)
    {
        List<SysUser> list = sysUserService.selectUserList(user);
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        return util.exportExcel(list, "用户数据");
    }

    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('system:user:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        List<SysUser> userList = util.importExcel(file.getInputStream());
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String operName = loginUser.getUsername();
        String message = sysUserService.importUser(userList, updateSupport, operName);
        return AjaxResult.success(message);
    }
    
    
    @PostMapping("/dataSourceTest")
    public AjaxResult dataSourceTest(@RequestBody HashMap<String, Object> param) throws Exception
    {
    	List<SysUser> master = sysUserService.getMasterUser(); //MASTER
    	List<SysUser> slave = sysUserService.getSlaveUser();  //SLAVE
    	List<SysUser> three = sysUserService.getUser();  //SLAVE
    	logger.info("主数据库用户数：" + master.size());
    	logger.info("从数据库用户数：" + slave.size());
    	logger.info("数据库用户数：" + three.size());
        return AjaxResult.success();
    }

    @GetMapping("/importTemplate")
    public AjaxResult importTemplate()
    {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        return util.importTemplateExcel("用户数据");
    }

    /**
     * 新增用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:add')")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysUser user)
    {
        if (UserConstants.NOT_UNIQUE.equals(sysUserService.checkUserNameUnique(user.getUserName())))
        {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(sysUserService.checkPhoneUnique(user)))
        {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(sysUserService.checkEmailUnique(user)))
        {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setCreateBy(SecurityUtils.getUsername());
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        return toAjax(sysUserService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysUser user)
    {
        sysUserService.checkUserAllowed(user);
        if (UserConstants.NOT_UNIQUE.equals(sysUserService.checkPhoneUnique(user)))
        {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(sysUserService.checkEmailUnique(user)))
        {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(sysUserService.updateUser(user));
    }

    /**
     * 删除用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:remove')")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(sysUserService.deleteUserByIds(userIds));
    }

    /**
     * 重置密码
     */
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/resetPwd")
    public AjaxResult resetPwd(@RequestBody SysUser user)
    {
        sysUserService.checkUserAllowed(user);
        SysUser user1 = sysUserService.selectByEmail(user.getUserId());
        String password = GetRamdom.getRamdom();
        user.setPassword(SecurityUtils.encryptPassword(password));
        user.setUpdateBy(SecurityUtils.getUsername());
		MailSentThread mailThread = new MailSentThread(user1.getEmail(), MailConstant.Send_Subject,
				MailConstant.Send_Msg + password);
		mailThread.start();
        return toAjax(sysUserService.resetPwd(user));
    }

	/**
	 * 状态修改
	 */
	@PreAuthorize("@ss.hasPermi('system:user:edit')")
	@Log(title = "用户管理", businessType = BusinessType.UPDATE)
	@PutMapping("/changeStatus")
	public AjaxResult changeStatus(@RequestBody SysUser user) {
		sysUserService.checkUserAllowed(user);
		user.setUpdateBy(SecurityUtils.getUsername());
		return toAjax(sysUserService.updateUserStatus(user));
	}

	/**
	 * 用户列表
	 */
	@GetMapping(value = "/getUserListByKeyword")
	public TableDataInfo listByKeyword(SysUser userDTO) {
		// 开始分页
		startPage();
		// 列表展示
		List<HashMap<String, Object>> hashMaps = sysUserService.selectUserListByKeyword(userDTO.getKeyword());
		return getDataTable(hashMaps);
	}

    /**
     * 添加账号
     */
    @PostMapping("/saveAccount")
    @ResponseBody
    public R saveAccount(@RequestBody SysUser createUserDTO) {
		if (createUserDTO != null) {
			List<SysUser> lists = sysUserService.selectByUserName(createUserDTO.getUserName());
			if (lists.size() == 0) {
				sysUserService.saveAccount(createUserDTO);
				return R.ok("添加成功");
			}
			List<SysUser> sysUsers = sysUserService.selectDelFlag(createUserDTO.getUserName());
			if (sysUsers.size() == 0) {
				return R.error("该用户已存在");
			} else {
				for (SysUser sysUser : lists) {
					createUserDTO.setUserId(sysUser.getUserId());
				}
				// sysUserService.updateDelFlag(createUserDTO);
				return R.ok("添加成功");
			}
		}
		return R.error("添加失败");
    }
    

	/**
	 * 删除
	 */
	@DeleteMapping("/deleteByUserId")
	public R deleteByUserId(Long userId) {
		if (userId != null) {
			sysUserService.deleteByUserId(userId);
			return R.ok("删除成功");
		}
		return R.error("删除失败");
	}
    
    
    /***
     * 修改
     */
	@PutMapping("/updateRoleByUserId")
	@ResponseBody
	public R updateRoleByUserId(@RequestBody SysUser createUserDTO) {
		if (createUserDTO != null) {
			sysUserService.updateRoleByUserId(createUserDTO);
			return R.ok("修改成功");
		}

		return R.error("修改失败");
	}

	@GetMapping("/getByUserName")
	public R getByUserName() {
		SysUser currentUser = this.getSysUser();
		List<SysUser> byUserName = sysUserService.getByUserName(currentUser.getUserName());
		return R.data(byUserName);
	}
}