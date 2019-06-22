package com.wind.common.exceptions;

/**
 * 基础异常
 * 
 * @author linxiaoqing
 */
public abstract class BaseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 具体异常码
	 */
	protected Integer code;

	/**
	 * 异常信息
	 */
	protected String msg;

	/**
	 * 异常码前缀，-1为默认不校验前缀 线程安全
	 */
	private static ThreadLocal<String> exCodePrefix = new ThreadLocal<String>() {

		protected String initialValue() {
			return "-1";
		}

	};

	/**
	 * 异常码后缀，-1为默认不校验后缀 线程安全
	 */
	private static ThreadLocal<String> exCodeSuffix = new ThreadLocal<String>() {

		protected String initialValue() {
			return "-1";
		}

	};

	public BaseException() {
		super();
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

	public BaseException(String message) {
		super(message);
		this.msg = message;
	}

	public BaseException(String msgFormat, Object... args) {
		super(msgFormat = String.format(msgFormat, args));
		this.msg = msgFormat;
	}

	public BaseException(Throwable cause, String message) {
		super(message, cause);
		this.msg = message;
	}

	public BaseException(Throwable cause, String msgFormat, Object... args) {
		super(msgFormat = String.format(msgFormat, args), cause);
		this.msg = msgFormat;
	}

	public BaseException(Integer code, String message) {
		super(code + ":" + message);
		this.code = code;
		this.msg = message;
	}

	public BaseException(Integer code, String msgFormat, Object... args) {
		super(msgFormat = code + ":" + String.format(msgFormat, args));
		this.code = code;
		this.msg = msgFormat;
	}

	public BaseException(Throwable cause, Integer code, String msgFormat, Object... args) {
		super(msgFormat = code + ":" + String.format(msgFormat, args), cause);
		this.code = code;
		this.msg = msgFormat;
	}

	public String getMsg() {
		return msg;
	}

	public Integer getCode() {
		return code;
	}

	public String getExCodePrefix() {
		return exCodePrefix.get();
	}

	public void setExCodePrefix(String exCodePrefix) {
		BaseException.exCodePrefix.set(exCodePrefix);
	}

	public String getExCodeSuffix() {
		return exCodeSuffix.get();
	}

	public void setExCodeSuffix(String exCodeSuffix) {
		BaseException.exCodeSuffix.set(exCodeSuffix);
	}

	public static void throwEx(BaseException ex) {
		if (ex == null)
			throw new RuntimeException("抛BaseException异常不能为空！");
		checkExCode(ex);
		throw ex;
	}

	private static void checkExCode(BaseException ex) {

		if (ex.getCode() == 0)
			return;

		if ("-1".equals(exCodePrefix.get()) && "-1".equals(exCodeSuffix.get()))
			return;

		String codeStr = String.valueOf(ex.getCode());
		if (codeStr.length() < 5)
			throw new RuntimeException("自定义异常的code最少五位数");
		if (!"-1".equals(exCodePrefix.get()) && !exCodePrefix.get().equals(codeStr.substring(0, 2)))
			throw new RuntimeException(ex.getClass() + "异常类型的code必须以" + exCodePrefix.get() + "开头。");

		if (!"-1".equals(exCodeSuffix.get()) && !exCodeSuffix.get().equals(codeStr.substring(2, 5)))
			throw new RuntimeException(ex.getClass() + "异常类型的code必须包含正确的微服务编号：" + exCodeSuffix.get());
	}

	/**
	 * String.format("Hi,%s", "王力") String.format("Hi,%s:%s.%s",
	 * "王南","王力","王张"); 详情使用请参考main函数
	 * 
	 * @param code
	 * @param msgFormat
	 *            替换个格式
	 * @param args
	 *            替换的参数
	 */
	public static void main(String[] args) {
		String str = null;
		str = String.format("Hi,%s", "王力");
		System.out.println(str);
		str = String.format("Hi,%s:%s.%s", "王南", "王力", "王张");
		System.out.println(str);
		System.out.printf("字母a的大写是：%c %n", 'A');
		System.out.printf("3>7的结果是：%b %n", 3 > 7);
		System.out.printf("100的一半是：%d %n", 100 / 2);
		System.out.printf("100的16进制数是：%x %n", 100);
		System.out.printf("100的8进制数是：%o %n", 100);
		System.out.printf("50元的书打8.5折扣是：%f 元%n", 50 * 0.85);
		System.out.printf("上面价格的16进制数是：%a %n", 50 * 0.85);
		System.out.printf("上面价格的指数表示：%e %n", 50 * 0.85);
		System.out.printf("上面价格的指数和浮点数结果的长度较短的是：%g %n", 50 * 0.85);
		System.out.printf("上面的折扣是%d%% %n", 85);
		System.out.printf("字母A的散列码是：%h %n", 'A');

	}

}
