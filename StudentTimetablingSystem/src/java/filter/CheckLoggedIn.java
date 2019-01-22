package filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.faces.application.ResourceHandler;
import javax.inject.Inject;
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
import managedbean.AuthenticationManagedBean;

@WebFilter(filterName = "CheckLoggedIn", urlPatterns =
{
    "/*"
})
public class CheckLoggedIn implements Filter
{
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    @Inject
    AuthenticationManagedBean authenticationManagedBean;

	private static final String AJAX_REDIRECT_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><partial-response><redirect url=\"%s\"></redirect></partial-response>";
	
	// permitted web pages
	private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("", "/faces/index.xhtml", "/faces/login.xhtml", "/faces/message.xhtml", "/faces/register.xhtml")));
		
    public CheckLoggedIn()
    {
    }

    /*
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
		throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String loginURI = request.getContextPath() + "/faces/login.xhtml";
        String registerURI = request.getContextPath() + "/faces/register.xhtml";

        boolean loggedIn = authenticationManagedBean.isCredentialsOK();
        boolean loginRequest = request.getRequestURI().equals(loginURI);
        boolean registerRequest = request.getRequestURI().equals(registerURI);
		boolean ajaxRequest = "partial/ajax".equals(request.getHeader("Faces-Request"));
		
		//Allow specific pages without login
		String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", ""); 
		boolean allowedPath = ALLOWED_PATHS.contains(path);
		
		// Activating JSF Resource Requests
		boolean resourceRequest = request.getRequestURI().startsWith(request.getContextPath() + "/faces" + ResourceHandler.RESOURCE_IDENTIFIER);
		
		// OR resourceRequest
		if (loggedIn || loginRequest || registerRequest || resourceRequest || allowedPath)
        {
			// Prevent restricted pages from being cached.
			if (!resourceRequest) 
			{ 
				response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
				response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
				response.setDateHeader("Expires", 0); // Proxies.
			}

            chain.doFilter(request, response);
        }
		else if (ajaxRequest) {
            response.setContentType("text/xml");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().printf(AJAX_REDIRECT_XML, loginURI); // So, return special XML response instructing JSF ajax to send a redirect.
        }
        else
        {
            response.sendRedirect(loginURI);
        }
    }

    /*
     * Destroy method for this filter
     */
    @Override
    public void destroy()
    {
        this.filterConfig = null;
    }

    /*
     * Init method for this filter
     */
    @Override
    public void init(FilterConfig filterConfig)
    {
        this.filterConfig = filterConfig;
    }

    public void log(String msg)
    {
        filterConfig.getServletContext().log(msg);
    }

}
