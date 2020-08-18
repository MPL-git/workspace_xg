package com.jf.entity.pay;
/**
  * @author  chenwf: 
  * @date 创建时间：2018年6月12日 下午3:16:27 
  * @version 1.0 
  * @parameter  
  * @return  
*/
public class WxRedPacketRecordRes {
	private String status;
	/**
	 * SUCCESS/FAIL此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
	 */
	private String return_code;
	/**
	 * 返回信息，如非空，为错误原因 
	 */
	private String return_msg;
	/**
	 * SUCCESS/FAIL
	 */
	private String result_code;
	/**
	 * 错误码信息
	 */
	private String err_code;
	/**
	 * 商户订单号（每个订单号必须唯一）组成：mch_id+yyyymmdd+10位一天内不能重复的数字
	 */
	private String err_code_des;
	/**  
	 * status  
	 * @return status status  
	 */
	public String getStatus() {
		return status;
	}
	
	/**  
	 * status  
	 * @param status status  
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**  
	 * SUCCESSFAIL此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断  
	 * @return return_code SUCCESSFAIL此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断  
	 */
	public String getReturn_code() {
		return return_code;
	}
	
	/**  
	 * SUCCESSFAIL此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断  
	 * @param return_code SUCCESSFAIL此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断  
	 */
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	
	/**  
	 * 返回信息，如非空，为错误原因  
	 * @return return_msg 返回信息，如非空，为错误原因  
	 */
	public String getReturn_msg() {
		return return_msg;
	}
	
	/**  
	 * 返回信息，如非空，为错误原因  
	 * @param return_msg 返回信息，如非空，为错误原因  
	 */
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	
	/**  
	 * SUCCESSFAIL  
	 * @return result_code SUCCESSFAIL  
	 */
	public String getResult_code() {
		return result_code;
	}
	
	/**  
	 * SUCCESSFAIL  
	 * @param result_code SUCCESSFAIL  
	 */
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	
	/**  
	 * 错误码信息  
	 * @return err_code 错误码信息  
	 */
	public String getErr_code() {
		return err_code;
	}
	
	/**  
	 * 错误码信息  
	 * @param err_code 错误码信息  
	 */
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	
	/**  
	 * 商户订单号（每个订单号必须唯一）组成：mch_id+yyyymmdd+10位一天内不能重复的数字  
	 * @return err_code_des 商户订单号（每个订单号必须唯一）组成：mch_id+yyyymmdd+10位一天内不能重复的数字  
	 */
	public String getErr_code_des() {
		return err_code_des;
	}
	
	/**  
	 * 商户订单号（每个订单号必须唯一）组成：mch_id+yyyymmdd+10位一天内不能重复的数字  
	 * @param err_code_des 商户订单号（每个订单号必须唯一）组成：mch_id+yyyymmdd+10位一天内不能重复的数字  
	 */
	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}
	
	
}
