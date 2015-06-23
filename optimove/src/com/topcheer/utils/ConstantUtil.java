package com.topcheer.utils;

public class ConstantUtil {
	public static String IS_LOGIN = "IS_LOGIN";
	public static String LOGIN_USER = "LOGIN_USER";
	public static String SUCCEED = "ok";
	public static String FAIL = "fail";
	public static String WORKID = "WorkId";
	public static String OPERATORNAME = "UserLoginName";
	public static String BRANCHCODE = "BranchCode";
	public static String BRANCHNAME = "BranchName";
	public static String SUBBRANCKCODE = "SubbranchCode";
	public static String SUBBRANCKNAME = "SubbranchName";
	public static String PASSWORD = "UserPwd";
	public static String USER_GRPID = "grpId";
	//默认密码
	public static String DF_PASSWORD = "88888888";
	//上传文件夹
	public static String UPLOAD_FOLDER = "upload";
	public static String UNKNOW_FOLDER = "unkonw";
	public static String CHECK_TOTAL_FOLDER = "checkTotal";
	//文件字段分隔符
	public static String FILE_SPLIT = "|";
	//批量写入文件
	public static int FILE_WRITE_BATCH = 10;
	//交易成功
	public static String TRANS_STATUS_SUCCESS = "0";
	//交易失败
	public static String TRANS_STATUS_FAILED = "1";
	//交易进程标志（0-发送第三方成功；1-发送第三方失败；2-未发送；3-发送第三方超时）
	public static String SEND_FLAG_SUCCESS = "0";
	public static String SEND_FLAG_FAIL = "1";
	public static String SEND_FLAG_INIT = "2";
	public static String SEND_FLAG_TIMEOUT = "3";
	//缴存、支付交易类型（1-正常；R-冲正；B-退票）
	public static String TRANS_TYPE_NORMAL = "1";
	public static String TRANS_TYPE_REVERSE = "R";
	public static String TRANS_TYPE_GIVEBACK = "B";
	//冲正、退票交易类型(1 缴存冲正 2 支付冲正 3 支付退票)
	public static String TRANS_TYPE_HOLD_REVERSE = "1";
	public static String TRANS_TYPE_PAY_REVERSE = "2";
	public static String TRANS_TYPE_PAY_GIVEBACK = "3";
	//对帐交易类型(60 补息	70退票)
	public static String CHECK_ACC_TYPE_INTEREST = "60";
	public static String CHECK_ACC_TYPE_GIVEBACK = "70";
	//对帐标识（Y-已对帐; N-未对帐）
	public static String CHECK_ACC_FLAG_Y = "Y";
	public static String CHECK_ACC_FLAG_N = "N";
	
	//分行机构代码
	public static String NC_BANK_CODE = "6400";
	//分行机构名称
	public static String NC_BANK_NAME = "天正银行";
	//交易成功返回码
	public static String TANS_SUCCESS = "00";
	//发送报文后缀
	public static String SEND_SUFFIX = "S";
	//接收报文后缀
	public static String RECEIVE_SUFFIX = "R";
	//帐号对帐明细
	public static String TANS_CODE_CHECK_TOTAL = "01";
	//应缴资金查询
	public static String TANS_CODE_QUERY_WITHHOLD="02";
	//应缴资金确认
	public static String TANS_CODE_PAY_WITHHOLD="03";
	//资金支付查询交易码
	public static String TANS_CODE_PAY_QUERY = "04";
	//资金支付确认交易码
	public static String TANS_CODE_PAY_CONFIRM = "05";
	//冲正确认
	public static String TANS_CODE_PAY_REVERSE="06";
	//利息补录
	public static String TANS_CODE_INTEREST_CONFIRM="07";
	//退票交易确认交易码
	public static String TANS_CODE_GIVE_BACK_CONFIRM = "08";
	//不明款台帐交易码
	public static String TANS_CODE_UNKNOW_CONFIRM = "09";
	//监管账户状态查询
	public static String TANS_CODE_ACCOUNT_QUERY="10";
	//银行冻结监管账户确认
	public static String TANS_CODE_DJACCOUNT_CONFIRM="11";
	//银行解冻监管账户确认
	public static String TANS_CODE_JDACCOUNT_CONFIRM="12";
	
	//日期分割符号
	public static String DTAE_FORMAT_SPLIT = "/";
	//时间分割符号
	public static String TIME_FORMAT_SPLIT = ":";
	
	//报文返回码
	public static String RET_MSG_FG1 = "第三方响应报文有误";
	public static String RET_MSG_FG2 = "对账日期应小于当前日期";
	public static String RET_MSG_FG3 = "监管账号不存在";
	public static String RET_MSG_FG4 = "系统生成对帐文件失败";
	public static String RET_MSG_FG5 = "文件上传ftp服务器错误!";
	public static String RET_MSG_FG6 = "第三方通讯失败";
	
}
