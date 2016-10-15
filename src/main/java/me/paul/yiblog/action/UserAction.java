package me.paul.yiblog.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.apache.struts2.ServletActionContext;

import me.paul.yiblog.entity.Power;
import me.paul.yiblog.entity.User;
import me.paul.yiblog.service.IUserService;
import me.paul.yiblog.util.CommonUtil;
import me.paul.yiblog.util.JavaMailUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {

	private static final long serialVersionUID = -9213858011869747588L;

	private User user;

	public User getUser() {
		return user;
	}
	
	private int page;

	private int userPerPage;
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getUserPerPage() {
		return userPerPage;
	}

	public void setUserPerPage(int userPerPage) {
		this.userPerPage = userPerPage;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private IUserService userService;

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	//查看用户信息
	public String getById() {
		long id = user.getId();
		user = userService.get(id);
		ActionContext.getContext().getContextMap().put("viewUser", user);
		return "viewUser";
	}

	//注册用户
	public String save() {
		
		Power power = new Power();
		power.setId(2);
		user.setPower(power);
		user.setPassword(CommonUtil.generateMD5(user.getPassword()));
		Date date = new Date();
		user.setRegisterDate(date);
		user.setLastLoginDate(date);
		synchronized (UserAction.class) {
			userService.save(user);
		}
		return "signin";
	}

	//用户列表
	public String viewUsers() {
		List<User> list = userService.getUsers(page, userPerPage);
		int userCount = userService.getUserCount();
		int pageCount = (int) Math.ceil(userCount * 1.0 / userPerPage);
		Map<String,Integer> pageMap = CommonUtil.getPageMap(pageCount, page);
		Map<String,Object> request = ActionContext.getContext().getContextMap(); 
		request.put("listUser", list);
		request.put("pageMap", pageMap);
		request.put("currentPage", page);
		request.put("nextPage", page + 1);
		request.put("lastPage", page - 1);
		request.put("pageCount", pageCount);
		return "viewUsers";
	}

	//登录
	public String login() {
		User userGet = userService.getByName(user.getName());
		if(userGet == null){
			userGet = userService.getByEmail(user.getName());
		}
		if (userGet != null) {
			if (userGet.getPassword()
					.trim()
					.equalsIgnoreCase(
							CommonUtil.generateMD5(user.getPassword()))) {
				Map<String, Object> sessionMap = ActionContext.getContext()
						.getSession();
				sessionMap.put("currentUser", userGet);
				userGet.setLastLoginDate(new Date());
				userService.update(userGet);
				return "index";
			}
		}
		ActionContext.getContext().getContextMap().put("loginError", "用户名或密码错误");
		return "signin";
	}

	//退出
	public String logout() {
		if (ActionContext.getContext().getSession().containsKey("currentUser")) {
			ActionContext.getContext().getSession().remove("currentUser");
		}
		return "index";
	}

	//ajax 查询邮箱是否已经被占用
	public String checkEmail() throws IOException {
		String email = user.getEmail();
		User user = userService.getByEmail(email);
		PrintWriter writer = ServletActionContext.getResponse().getWriter();
		if (user == null) {
			writer.write("valid");
		} else {
			writer.write("unvalid");
		}
		writer.flush();
		return null;
	}

	//ajax 查询用户名是否已经被占用
	public String checkName() throws IOException {
		String name = user.getName();
		User user = userService.getByName(name);
		PrintWriter writer = ServletActionContext.getResponse().getWriter();
		if (user == null) {
			writer.write("valid");
		} else {
			writer.write("unvalid");
		}
		writer.flush();
		return null;
	}

	//ajax请求发送验证码
	public String sendVerifyEmail() throws IOException, MessagingException {
		String email = user.getEmail();
		if (email == null || email.isEmpty())
			return null;
		if(!email.matches(".+@.+")){
			return null;
		}
		String code = CommonUtil.generateVerifyCode();
		// String content = "这是一封来自yiblog的验证邮件\n\n以下是你的验证代码:\n\t\t" + code;
		JavaMailUtil.sendEmail(email, code);
		PrintWriter writer = ServletActionContext.getResponse().getWriter();
		writer.write(code);
		writer.flush();
		return null;
	}
	
	//获取用户信息以编辑
	public String editProfile(){
		long id = user.getId();
		User userGet = userService.get(id);
		ActionContext.getContext().getContextMap().put("userGet", userGet);
		return "editUser";
	}
	
	//提交编辑
	public synchronized String submitUpdate(){
		User userGet = userService.get(user.getId());
		userGet.setBirthDate(user.getBirthDate());
		userGet.setName(user.getName());
		userGet.setPhonenumber(user.getPhonenumber());
		userGet.setSex(user.getSex());
		userService.update(userGet);
		ActionContext.getContext().getContextMap().put("viewUser", userGet);
		return "viewUser";
	}
	
}
