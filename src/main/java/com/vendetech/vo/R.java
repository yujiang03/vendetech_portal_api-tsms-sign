package com.vendetech.vo;

import java.util.HashMap;
import java.util.Map;

import com.vendetech.common.constant.HttpStatus;

public class R extends HashMap<String, Object> {
	//
	private static final long serialVersionUID = -8157613083634272196L;

	public R() {
		put("code", HttpStatus.SUCCESS);
		put("msg", "success");
	}
	public static R error(int code,String msg, Object obj){
		R r = new R();
		r.put("code",code);
		r.put("msg",msg);
		r.put("data",obj);
		return r;
	}

	public static R error() {
		return error(HttpStatus.ERROR, "未知异常，请联系管理员");
	}

	public static R error(String msg) {
		return error(HttpStatus.ERROR, msg);
	}

	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}

	public static R data(Object obj) {
		R r = new R();
		r.put("data", obj);
		return r;
	}

	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}

	public static R ok() {
		return new R();
	}

	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}

	public static R errorForMissingNecessaryParam() {
		return R.error(HttpStatus.BAD_REQUEST, "参数列表错误（缺少，格式不匹配）");
	}
}