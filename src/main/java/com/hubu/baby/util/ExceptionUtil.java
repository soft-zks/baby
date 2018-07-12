package com.hubu.baby.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Description: 异常工具类
 * @Source: JDK 1.8
 * @Author: ZhaoKunsong
 * @Date: 2018-04-26 14:32
 * @Since: version 1.0
 **/
public class ExceptionUtil {

    /**
     * 获取异常的堆栈信息
     *
     * @param t
     * @return
     */
    public static String getStackTrace(Throwable t) {

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        try {
            t.printStackTrace(pw);
            return sw.toString();
        } finally {
            pw.close();
        }
    }
}
