package me.sso.ti.result;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年12月27日 下午4:39:29
 */
public enum ResultCode {
	Success								(200L, 		"success"),
	Err_Exception						(300L, 		"System Business Error."),
	Error_User_Name						(310L, 		"用户名非法"),
	Error_User_Password					(320L, 		"密码非法"),
	Error_Valid_Request					(330L, 		"参数校验失败"),
	
	Error_Register_User_Exist			(402L, 		"用户名已经注册"),
	Error_Register						(403L, 		"注册失败"),
	
	Error_Login							(500L, 		"登录失败"),

	Error_Article_Empty					(600L, 		"查询文章结果为空"),
	Error_Article_NotExist				(601L, 		"文章不存在"),
	
	Error_Token							(700L, 		"Token校验失败"),
	
	Error_Fav_Article_Empty				(800L, 		"无收藏的文章"),
	;
	public final long code;
	public final String description;
	
	private ResultCode(long code, String description) {
		this.code = code;
		this.description = description;
	}
}