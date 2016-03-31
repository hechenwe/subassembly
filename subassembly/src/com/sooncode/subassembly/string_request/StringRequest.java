package com.sooncode.subassembly.string_request;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

/**
 * 从Request字符流中获取字符串
 * 
 * @author pc
 *
 */
public class StringRequest {

	/**
	 * 读取字符流
	 * 
	 * @param is
	 * @param contentLen
	 * @return
	 */
	public static final String getString(HttpServletRequest request) {
		int contentLen = request.getContentLength();
		if (contentLen > 0) {
			int readLen = 0;

			int readLengthThisTime = 0;

			byte[] message = new byte[contentLen];

			try {
				InputStream inputStream = request.getInputStream();
				while (readLen != contentLen) {

					readLengthThisTime = inputStream.read(message, readLen, contentLen - readLen);

					if (readLengthThisTime == -1) {// Should not happen.
						break;
					}

					readLen += readLengthThisTime;
				}

				return new String(message);
			} catch (IOException e) {

			}
		}

		return null;
	}
}
