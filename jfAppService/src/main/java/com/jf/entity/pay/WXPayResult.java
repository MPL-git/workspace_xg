package com.jf.entity.pay;

/**
 * 微信支付返回参数
 * @author chenwf
 *
 */
public class WXPayResult {

	private String appid;
	private String mch_id;
	private String device_info;
	private String nonce_str;
	private String sign;
	private String result_code;
	private String err_code;
	private String err_code_des;
	private String openid;
	private String is_subscribe;
	private String trade_type;
	private String bank_type;
	private int total_fee;
	private String fee_type;
	private int cash_fee;
	private String cash_fee_type;
	private int coupon_fee;
	private int coupon_count;
	private String coupon_id_0;
	private int coupon_fee_0;
	private String transaction_id;
	private String out_trade_no;
	private String attach;
	private String time_end;
	private String return_code;
	private String return_msg;
	/**  
	 * appid  
	 * @return appid appid  
	 */
	public String getAppid() {
		return appid;
	}
	
	/**  
	 * appid  
	 * @param appid appid  
	 */
	public void setAppid(String appid) {
		this.appid = appid;
	}
	
	/**  
	 * mch_id  
	 * @return mch_id mch_id  
	 */
	public String getMch_id() {
		return mch_id;
	}
	
	/**  
	 * mch_id  
	 * @param mch_id mch_id  
	 */
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	
	/**  
	 * device_info  
	 * @return device_info device_info  
	 */
	public String getDevice_info() {
		return device_info;
	}
	
	/**  
	 * device_info  
	 * @param device_info device_info  
	 */
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	
	/**  
	 * nonce_str  
	 * @return nonce_str nonce_str  
	 */
	public String getNonce_str() {
		return nonce_str;
	}
	
	/**  
	 * nonce_str  
	 * @param nonce_str nonce_str  
	 */
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	
	/**  
	 * sign  
	 * @return sign sign  
	 */
	public String getSign() {
		return sign;
	}
	
	/**  
	 * sign  
	 * @param sign sign  
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	/**  
	 * result_code  
	 * @return result_code result_code  
	 */
	public String getResult_code() {
		return result_code;
	}
	
	/**  
	 * result_code  
	 * @param result_code result_code  
	 */
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	
	/**  
	 * err_code  
	 * @return err_code err_code  
	 */
	public String getErr_code() {
		return err_code;
	}
	
	/**  
	 * err_code  
	 * @param err_code err_code  
	 */
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	
	/**  
	 * err_code_des  
	 * @return err_code_des err_code_des  
	 */
	public String getErr_code_des() {
		return err_code_des;
	}
	
	/**  
	 * err_code_des  
	 * @param err_code_des err_code_des  
	 */
	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}
	
	/**  
	 * openid  
	 * @return openid openid  
	 */
	public String getOpenid() {
		return openid;
	}
	
	/**  
	 * openid  
	 * @param openid openid  
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	/**  
	 * is_subscribe  
	 * @return is_subscribe is_subscribe  
	 */
	public String getIs_subscribe() {
		return is_subscribe;
	}
	
	/**  
	 * is_subscribe  
	 * @param is_subscribe is_subscribe  
	 */
	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
	}
	
	/**  
	 * trade_type  
	 * @return trade_type trade_type  
	 */
	public String getTrade_type() {
		return trade_type;
	}
	
	/**  
	 * trade_type  
	 * @param trade_type trade_type  
	 */
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	
	/**  
	 * bank_type  
	 * @return bank_type bank_type  
	 */
	public String getBank_type() {
		return bank_type;
	}
	
	/**  
	 * bank_type  
	 * @param bank_type bank_type  
	 */
	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}
	
	/**  
	 * total_fee  
	 * @return total_fee total_fee  
	 */
	public int getTotal_fee() {
		return total_fee;
	}
	
	/**  
	 * total_fee  
	 * @param total_fee total_fee  
	 */
	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}
	
	/**  
	 * fee_type  
	 * @return fee_type fee_type  
	 */
	public String getFee_type() {
		return fee_type;
	}
	
	/**  
	 * fee_type  
	 * @param fee_type fee_type  
	 */
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	
	/**  
	 * cash_fee  
	 * @return cash_fee cash_fee  
	 */
	public int getCash_fee() {
		return cash_fee;
	}
	
	/**  
	 * cash_fee  
	 * @param cash_fee cash_fee  
	 */
	public void setCash_fee(int cash_fee) {
		this.cash_fee = cash_fee;
	}
	
	/**  
	 * cash_fee_type  
	 * @return cash_fee_type cash_fee_type  
	 */
	public String getCash_fee_type() {
		return cash_fee_type;
	}
	
	/**  
	 * cash_fee_type  
	 * @param cash_fee_type cash_fee_type  
	 */
	public void setCash_fee_type(String cash_fee_type) {
		this.cash_fee_type = cash_fee_type;
	}
	
	/**  
	 * coupon_fee  
	 * @return coupon_fee coupon_fee  
	 */
	public int getCoupon_fee() {
		return coupon_fee;
	}
	
	/**  
	 * coupon_fee  
	 * @param coupon_fee coupon_fee  
	 */
	public void setCoupon_fee(int coupon_fee) {
		this.coupon_fee = coupon_fee;
	}
	
	/**  
	 * coupon_count  
	 * @return coupon_count coupon_count  
	 */
	public int getCoupon_count() {
		return coupon_count;
	}
	
	/**  
	 * coupon_count  
	 * @param coupon_count coupon_count  
	 */
	public void setCoupon_count(int coupon_count) {
		this.coupon_count = coupon_count;
	}
	
	/**  
	 * coupon_id_0  
	 * @return coupon_id_0 coupon_id_0  
	 */
	public String getCoupon_id_0() {
		return coupon_id_0;
	}
	
	/**  
	 * coupon_id_0  
	 * @param coupon_id_0 coupon_id_0  
	 */
	public void setCoupon_id_0(String coupon_id_0) {
		this.coupon_id_0 = coupon_id_0;
	}
	
	/**  
	 * coupon_fee_0  
	 * @return coupon_fee_0 coupon_fee_0  
	 */
	public int getCoupon_fee_0() {
		return coupon_fee_0;
	}
	
	/**  
	 * coupon_fee_0  
	 * @param coupon_fee_0 coupon_fee_0  
	 */
	public void setCoupon_fee_0(int coupon_fee_0) {
		this.coupon_fee_0 = coupon_fee_0;
	}
	
	/**  
	 * transaction_id  
	 * @return transaction_id transaction_id  
	 */
	public String getTransaction_id() {
		return transaction_id;
	}
	
	/**  
	 * transaction_id  
	 * @param transaction_id transaction_id  
	 */
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	
	/**  
	 * out_trade_no  
	 * @return out_trade_no out_trade_no  
	 */
	public String getOut_trade_no() {
		return out_trade_no;
	}
	
	/**  
	 * out_trade_no  
	 * @param out_trade_no out_trade_no  
	 */
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	
	/**  
	 * attach  
	 * @return attach attach  
	 */
	public String getAttach() {
		return attach;
	}
	
	/**  
	 * attach  
	 * @param attach attach  
	 */
	public void setAttach(String attach) {
		this.attach = attach;
	}
	
	/**  
	 * time_end  
	 * @return time_end time_end  
	 */
	public String getTime_end() {
		return time_end;
	}
	
	/**  
	 * time_end  
	 * @param time_end time_end  
	 */
	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}
	
	/**  
	 * return_code  
	 * @return return_code return_code  
	 */
	public String getReturn_code() {
		return return_code;
	}
	
	/**  
	 * return_code  
	 * @param return_code return_code  
	 */
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	
	/**  
	 * return_msg  
	 * @return return_msg return_msg  
	 */
	public String getReturn_msg() {
		return return_msg;
	}
	
	/**  
	 * return_msg  
	 * @param return_msg return_msg  
	 */
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	
	
	

}
