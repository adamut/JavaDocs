package ro.teamnet.zth.web;

import ro.teamnet.zth.file.LogFileWriter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Cosmin.Adamut on 7/19/2017.
 */
public class HeadersLogFilter implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest,servletResponse);
        LogFileWriter logFileWriter = new LogFileWriter();
        logFileWriter.logHeader("un header", "o valoare");

    }

    @Override
    public void destroy() {

    }
}
