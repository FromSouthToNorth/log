package vip.hyzt.api.controller.system;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vip.hyzt.common.annotation.Log;
import vip.hyzt.common.constant.UserConstants;
import vip.hyzt.common.core.page.TableDataInfo;
import vip.hyzt.common.enums.BusinessType;
import vip.hyzt.common.exception.ServiceException;
import vip.hyzt.common.utils.spring.SpringUtils;
import vip.hyzt.common.utils.uuid.IdUtils;
import vip.hyzt.core.domain.Result;
import vip.hyzt.core.security.SecurityService;
import vip.hyzt.core.web.controller.BaseController;
import vip.hyzt.system.domain.SysRole;
import vip.hyzt.system.domain.SysUser;
import vip.hyzt.system.service.ISysRoleService;
import vip.hyzt.system.service.ISysUserService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户信息
 *
 * @author hy
 */
@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;

    /**
     * 获取用户列表
     */
    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysUser user) {
        startPage();
        List<SysUser> list = userService.selectUserList(user);
        return getDataTable(list);
    }


    /**
     * 根据用户编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:user:query')")
    @GetMapping(value = {"/", "/{userId}"})
    public Result getInfo(@PathVariable(value = "userId", required = false) String userId) {
        if (!SysUser.isAdmin(SecurityService.getUserId())) {
            SysUser user = new SysUser();
            user.setUserId(userId);
            List<SysUser> users = SpringUtils.getAopProxy(userService).selectUserList(user);
            if (ObjectUtils.isEmpty(users)) {
                throw new ServiceException("没有权限访问用户数据！");
            }
        }
        Result ajax = Result.success();
        List<SysRole> roles = roleService.selectRoleAll();
        ajax.put("roles", SysUser.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        if (!ObjectUtils.isEmpty(userId)) {
            ajax.put(Result.DATA_TAG, userService.selectUserById(userId));
            ajax.put("roleIds", roleService.selectRoleListByUserId(userId));
        }
        return ajax;
    }

    /**
     * 新增用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:add')")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public Result add(@Validated @RequestBody SysUser user) {
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName()))) {
            return Result.error("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
        }
        else if (!ObjectUtils.isEmpty(user.getPhone())
                && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return Result.error("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        else if (!ObjectUtils.isEmpty(user.getEmail())
                && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return Result.error("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setUserId(IdUtils.simpleUUID());
        user.setCreateBy(getUsername());
        user.setPassword(SecurityService.encryptPassword(user.getPassword()));
        return toAjax(userService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public Result edit(@Validated @RequestBody SysUser user) {
        userService.checkUserAllowed(user);
        if (!ObjectUtils.isEmpty(user.getPhone())
                && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return Result.error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        } else if (!ObjectUtils.isEmpty(user.getEmail())
                && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return Result.error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setUpdateBy(getUsername());
        return toAjax(userService.updateUser(user));
    }

    /**
     * 删除用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:remove')")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userIds}")
    public Result remove(@PathVariable String[] userIds) {
        if (ArrayUtils.contains(userIds, getUserId())) {
            return error("当前用户不能删除");
        }
        return toAjax(userService.deleteUserByIds(userIds));
    }

    /**
     * 重置密码
     */
    @PreAuthorize("@ss.hasPermi('system:user:resetPwd')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/resetPwd")
    public Result resetPwd(@RequestBody SysUser user) {
        userService.checkUserAllowed(user);
        user.setPassword(SecurityService.encryptPassword(user.getPassword()));
        user.setUpdateBy(getUsername());
        return toAjax(userService.resetPwd(user));
    }

    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public Result changeStatus(@RequestBody SysUser user) {
        userService.checkUserAllowed(user);
        user.setUpdateBy(getUsername());
        return toAjax(userService.updateUserStatus(user));
    }

    /**
     * 根据用户编号获取授权角色
     */
    @PreAuthorize("@ss.hasPermi('system:user:query')")
    @GetMapping("/authRole/{userId}")
    public Result authRole(@PathVariable("userId") String userId) {
        Result ajax = Result.success();
        SysUser user = userService.selectUserById(userId);
        List<SysRole> roles = roleService.selectRolesByUserId(userId);
        ajax.put("user", user);
        ajax.put("roles", SysUser.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        return ajax;
    }

    /**
     * 用户授权角色
     */
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Log(title = "用户管理", businessType = BusinessType.GRANT)
    @PutMapping("/authRole")
    public Result insertAuthRole(String userId, String[] roleIds) {
        userService.insertUserAuth(userId, roleIds);
        return success();
    }

}
