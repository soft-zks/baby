package com.hubu.baby.filter;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @Description: 全站乱码过滤器
 * @Source: JDK 1.8
 * @Author: ZhaoKunsong
 * @Date: 2018-04-20 18:52
 * @Since: version 1.0
 **/
@Configuration
public class EncodingFilter implements Filter {

    private FilterConfig filterConfig = null;
    private String encode = null;
    private boolean isNotEncode = true;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        this.filterConfig = filterConfig;
        encode = filterConfig.getInitParameter("encode") == null ?
                "utf-8" : filterConfig.getInitParameter("encode");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        response.setContentType("text/html;charset=" + encode);
        chain.doFilter(new MyHttpRequest((HttpServletRequest) request), response);
    }

    @Override
    public void destroy() {

    }

    class MyHttpRequest extends HttpServletRequestWrapper {

        private HttpServletRequest httpServletRequest = null;

        public MyHttpRequest(HttpServletRequest request) {
            super(request);
            this.httpServletRequest = request;
        }

        public Map getParameterMap() {

            if (httpServletRequest.getMethod().equalsIgnoreCase("POST")) {
                try {
                    httpServletRequest.setCharacterEncoding(encode);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return super.getParameterMap();
            } else if (httpServletRequest.getMethod().equalsIgnoreCase("GET")) {

                Map<String, String[]> map = httpServletRequest.getParameterMap();
                if (isNotEncode && !"UTF-8".equalsIgnoreCase(httpServletRequest.getCharacterEncoding())) {
                    for (Map.Entry<String, String[]> m : map.entrySet()) {
                        String[] v = m.getValue();
                        for (int i = 0; i < v.length; i++) {
                            try {
                                v[i] = new String(v[i].getBytes("ISO8859-1"), encode);
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    isNotEncode = false;   //第二次是查询缓存 防止再次编码
                }
                return map;
            } else {
                return super.getParameterMap();
            }

        }

        public String[] getParameterValues(String name) {

            return (String[]) this.getParameterMap().get(name);
        }

        public String getParameter(String name) {

            return getParameterValues(name) == null ? null : getParameterValues(name)[0];
        }
    }

}
