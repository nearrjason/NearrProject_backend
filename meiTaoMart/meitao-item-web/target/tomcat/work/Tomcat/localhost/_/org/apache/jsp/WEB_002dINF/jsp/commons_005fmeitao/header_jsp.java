/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2018-10-05 13:17:44 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp.commons_005fmeitao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class header_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<div class=\"header\">\r\n");
      out.write("\t<div class=\"logo\">\r\n");
      out.write("\t\t<a href=\"\"><img src=\"/images_meitao/logo.svg\" alt=\"\"></a>\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("\t<div class=\"search\">\r\n");
      out.write("\t\t<form class=\"search_form\" action=\"http://192.168.1.100:8085/search.html\" id=\"searchForm\"\r\n");
      out.write("\t\t\tname=\"query\" method=\"GET\">\r\n");
      out.write("\t\t\t<input type=\"text\" class=\"text\" name=\"keyword\" id=\"keyword\" value=\"\"\r\n");
      out.write("\t\t\t\tstyle=\"color: rgb(153, 153, 153);\" autocomplete=\"off\">\r\n");
      out.write("\t\t\t<button id=\"searchButton\" class=\"submit-btn\">\r\n");
      out.write("\t\t\t\t<img src=\"/images_meitao/icons/search.svg\" alt=\"\">\r\n");
      out.write("\t\t\t</button>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("\t<div class=\"cart\">\r\n");
      out.write("\t\t<div class=\"wrapper\" id=\"cart\">\r\n");
      out.write("\t\t\t<a href=\"http://192.168.1.100:8090/cart/cart.html\"><img src=\"/images_meitao/icons/shopping-cart.svg\" alt=\"\">购物车&nbsp;\r\n");
      out.write("\t\t\t\t<span>28</span></a>\r\n");
      out.write("\t\t\t<!--shopping cart popup-->\r\n");
      out.write("\t\t\t<div class=\"cart-popup\" id=\"cart-popup\">\r\n");
      out.write("\t\t\t\t<div class=\"cart-view\">\r\n");
      out.write("\t\t\t\t\t<div class=\"s-item\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"div1\">\r\n");
      out.write("\t\t\t\t\t\t\t<img src=\"/images_meitao/design1.png\" alt=\"\">\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"div2\">\r\n");
      out.write("\t\t\t\t\t\t\t<p class=\"itemName\">台湾联华食品 卡迪那 德州薯条 茄汁味 168g\r\n");
      out.write("\t\t\t\t\t\t\t</h2>\r\n");
      out.write("\t\t\t\t\t\t\t<p class=\"itemCount\">数量：3</p>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"div3\">\r\n");
      out.write("\t\t\t\t\t\t\t<p class=\"itemPrice\">$11.49</p>\r\n");
      out.write("\t\t\t\t\t\t\t<a href=\"\">删除</a>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<hr>\r\n");
      out.write("\t\t\t\t\t<div class=\"s-item\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"div1\">\r\n");
      out.write("\t\t\t\t\t\t\t<img src=\"/images_meitao/design1.png\" alt=\"\">\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"div2\">\r\n");
      out.write("\t\t\t\t\t\t\t<p class=\"itemName\">台湾联华食品 卡迪那 德州薯条 茄汁味 168g\r\n");
      out.write("\t\t\t\t\t\t\t<p class=\"itemCount\">数量：3</p>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"div3\">\r\n");
      out.write("\t\t\t\t\t\t\t<p class=\"itemPrice\">$11.49</p>\r\n");
      out.write("\t\t\t\t\t\t\t<a href=\"\">删除</a>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<hr>\r\n");
      out.write("\t\t\t\t<div class=\"check-cart\">\r\n");
      out.write("\t\t\t\t\t<a href=\"\">查看购物车</a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
