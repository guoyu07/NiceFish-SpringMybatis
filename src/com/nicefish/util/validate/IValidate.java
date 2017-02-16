package com.nicefish.util.validate;

import com.nicefish.util.page.Result;

/**
 * 字符串验证
 * @author Lord
 *
 */
public interface IValidate {
	Result<String> Validate(String value);
}
