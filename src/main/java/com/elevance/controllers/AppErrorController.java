package com.elevance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class AppErrorController implements ErrorController {

    @Autowired
    private ErrorAttributes errorAttributes;
    @RequestMapping("/error")
    @ResponseBody
    public String handleError(HttpServletRequest request) {
        ServletWebRequest servletWebRequest = new ServletWebRequest(request);
        ErrorAttributeOptions options = ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE, ErrorAttributeOptions.Include.STACK_TRACE, ErrorAttributeOptions.Include.EXCEPTION);


        Map<String, Object> errorAttributes = this.errorAttributes.getErrorAttributes(servletWebRequest, options);
        /*Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        return String.format("<html><body><h2>Error Page</h2><div>Status code: <b>%s</b></div>"
                        + "<div>Exception Message: <b>%s</b></div><body></html>",
                statusCode, exception==null? "N/A": exception.getMessage());*/
        final StringBuilder errorDetails = new StringBuilder();
        errorAttributes.forEach((attribute, value) -> {
            errorDetails.append("<tr><td>")
                    .append(attribute)
                    .append("</td><td><pre>")
                    .append(value)
                    .append("</pre></td></tr>");
        });

        return String.format("<html><head><style>td{vertical-align:top;border:solid 1px #666;}</style>"
                + "</head><body><h2>Error Page</h2><table>%s</table></body></html>", errorDetails);
    }
}