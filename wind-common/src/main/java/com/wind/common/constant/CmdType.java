package com.wind.common.constant;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 接口类型
 * 
 * @author linxiaoqing
 * 
 */
public enum CmdType {

	OrderQuery {
		public String getDesc() {
			return "订单查询";
		}
	},
	OrderRefund {
		public String getDesc() {
			return "订单退款";
		}
	};

	public static Map<String, String> toStringMap() {
		CmdType[] type = values();
		Map<String, String> map = new LinkedHashMap<String, String>();
		if (type != null) {
			for (CmdType e : type) {
				map.put(e.toString(), e.getDesc());
			}
		}
		return map;
	}

	public abstract String getDesc();

}
