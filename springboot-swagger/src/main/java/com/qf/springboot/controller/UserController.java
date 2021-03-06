package com.qf.springboot.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.qf.springboot.model.UserInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName: UserController
 * @Description:(使用 swagger 展示API )
 * @author haichangzhang
 * @date 2017年7月25日 上午11:09:23
 * 
 */
@RestController
@RequestMapping("/users")
@Api("userController相关api")
public class UserController {

	static Map<Integer, UserInfo> users = Collections.synchronizedMap(new HashMap<Integer, UserInfo>());

	@ApiOperation(value = "读取用户列表", notes = "")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<UserInfo> getUserList() {
		// 处理"/users/"的GET请求，用来获取用户列表
		// 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
		List<UserInfo> list = new ArrayList<>(users.values());
		return list;
	}

	@ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
	@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "UserInfo")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String creatUser(@RequestBody UserInfo user) {
		// 处理"/users/"的POST请求，用来创建User
		// 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
		users.put(user.getId(), user);
		return "succuess";
	}

	@ApiOperation(value = "获取用户信息", notes = "根据用户ID获取对应用户信息")
	@ApiImplicitParams(@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path"))
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public UserInfo loadUser(@PathVariable Integer id) {
		// 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
		// url中的id可通过@PathVariable绑定到函数的参数中
		return users.get(id);
	}

	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer"),
			@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, paramType = "path",dataType = "UserInfo") })
	@ApiOperation(value = "修改用户信息", notes = "根据用户ID修改对应用户信息")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String updateUser(@PathVariable Integer id, @RequestBody UserInfo user) {
		// 处理"/users/{id}"的PUT请求，用来更新User信息
		UserInfo userinfo = users.get(id);
		userinfo.setEmail(user.getEmail());
		userinfo.setPassword(user.getPassword());
		userinfo.setQq(user.getQq());
		userinfo.setRealname(user.getRealname());
		userinfo.setTel(user.getTel());
		userinfo.setUsername(user.getUsername());
		userinfo.setUsertype(user.getUsertype());
		userinfo.setEnabled(user.getEnabled());
		users.put(id, userinfo);
		return "succuess";
	}

	@ApiImplicitParam(name = "id", value = "用户ID", required = true,paramType = "path", dataType = "Integer")
	@ApiOperation(value = "删除用户信息", notes = "根据用户ID删除对应用户信息")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable Integer id) {
		// 处理"/users/{id}"的DELETE请求，用来删除User
		users.remove(id);
		return "succuess";
	}
}
