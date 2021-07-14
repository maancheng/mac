package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class RuleFilter
 */
@WebFilter("/RuleFilter")
public class RuleFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public RuleFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		HttpSession session = request.getSession();
		boolean login = false;
		if (session.getAttribute("login") != null && session.getAttribute("login") != "") {
			login = (Boolean) session.getAttribute("login");
		}
		System.out.println("判断用户合法性：:" + login);
		if (login) {
			chain.doFilter(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/fail.jsp");
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
