/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2018-10-12 19:40:36 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp.commons_005fmeitao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class main_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("<section>\r\n");
      out.write("\t<div class=\"main-display\">\r\n");
      out.write("\t\t<!-- order information -->\r\n");
      out.write("\r\n");
      out.write("\t\t<div class=\"keep-parallel\">\r\n");
      out.write("\t\t\t<div class=\"maintab-display\">\r\n");
      out.write("\t\t\t\t<!-- <h1 class=\"subtitle\">填写并核对订单信息</h1> -->\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<div class=\"main\">\r\n");
      out.write("\t\t\t\t\t<h3 class=\"customfont\" style=\"float: left\">收货地址&emsp;</h3>\r\n");
      out.write("\t\t\t\t\t<h3 class=\"customfont add\" onclick=\"openNewAddressWindow()\">\r\n");
      out.write("\t\t\t\t\t\t<span>新增&nbsp;</span><img\r\n");
      out.write("\t\t\t\t\t\t\tsrc=\"/images_meitao/icons/plus-symbol.svg\" alt=\"\"\r\n");
      out.write("\t\t\t\t\t\t\tclass=\"add-addr\">\r\n");
      out.write("\t\t\t\t\t</h3>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<div id=\"address_drop_list\">\r\n");
      out.write("\t\t\t\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "main/address_drop_list.jsp", out, false);
      out.write("</div>\r\n");
      out.write("\t\t\t\t\t<form id=\"submitNewAddressForm\" onsubmit=\"return false\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"address-list\">\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"main2\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"info-block\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"add-desc\">使用新地址</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<br> <br>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"customfont\">First Name</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\"firstName\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\tclass=\"username-fill fillup\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"customfont\">Last Name</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\"lastName\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\tclass=\"username-fill fillup\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"email-phone\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"email-get customfont\">Phone</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"email-phone-fill keep-parallel\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<!-- <input type=\"email\" name=\"emailaddr\" class=\"emailaddr-fill fillup\"> -->\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<input id=\"addAddressShippingPhone1\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\tclass=\"phonenum-fill fillup\" type=\"number\" placeholder=\"\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\tmaxlength=\"3\" value=\"\" />\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"phonemark\">-</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<input id=\"addAddressShippingPhone2\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\tclass=\"phonenum-fill fillup\" type=\"number\" placeholder=\"\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\tmaxlength=\"3\" value=\"\" />\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"phonemark\">-</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<input id=\"addAddressShippingPhone3\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\tclass=\"phonenum-fill fillup\" type=\"number\" placeholder=\"\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\tmaxlength=\"4\" value=\"\" />\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<input id=\"addAddressShippingPhone\" name=\"shippingPhone\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\ttype=\"hidden\" class=\"emailaddr-fill fillup\" value=\"\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<br>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"home\">Street</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\"street\" class=\"address-fill fillup\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"city-state-zip keep-parallel\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"city\">City</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"state\">State</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"zip\">Zip Code</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"city-state-zip-fill keep-parallel\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\"city\" class=\"city-fill fillup\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<!-- <input type=\"text\" name=\"state\" class=\"state-fill fillup\"> -->\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"city-state-zip-fill keep-parallel\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<select class=\"state-fill fillup\" name=\"state\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"AL\">AL</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"AK\">AK</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"AR\">AR</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"AZ\">AZ</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"CA\">CA</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"CO\">CO</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"CT\">CT</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"DC\">DC</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"DE\">DE</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"FL\">FL</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"GA\">GA</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"HI\">HI</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"IA\">IA</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"ID\">ID</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"IL\">IL</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"IN\">IN</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"KS\">KS</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"KY\">KY</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"LA\">LA</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"MA\">MA</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"MD\">MD</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"ME\">ME</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"MI\">MI</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"MN\">MN</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"MO\">MO</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"MS\">MS</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"MT\">MT</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"NC\">NC</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"NE\">NE</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"NH\">NH</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"NJ\">NJ</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"NM\">NM</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"NV\">NV</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"NY\">NY</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"ND\">ND</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"OH\">OH</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"OK\">OK</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"OR\">OR</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"PA\">PA</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"RI\">RI</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"SC\">SC</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"SD\">SD</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"TN\">TN</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"TX\">TX</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"UT\">UT</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"VT\">VT</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"VA\">VA</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"WA\">WA</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"WI\">WI</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"WV\">WV</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"WY\">WY</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\"zipcode\" class=\"zip-fill fillup\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<br> <br>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<p id=\"order_shipping_address\" class=\"error_message\"></p>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"saveaddr-save\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<!-- <span> <input id=\"saveAddressCheckBox\" type=\"checkbox\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\tclass=\"save-addr\">保存地址\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</span>  -->\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<button class=\"submit-btn\" onclick=\"submitNewAddress()\">保存地址</button>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</span>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t<br>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<div class=\"shipping-method\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t配送方式\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t\t\t\t<ul class=\"select-list\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<li class=\"normalship\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tonclick=\"selectGroudShipping(");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${primaryAddress.id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(")\"><b>平邮</b></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<li class=\"fastship\" onclick=\"selectExpressShipping()\"><b>加急</b></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</form>\r\n");
      out.write("\t\t\t\t\t<br> <br>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<br> <br> <br>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<!-- payment method -->\r\n");
      out.write("\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t<!-- <h1 class=\"subtitle\">支付方式</h1> -->\r\n");
      out.write("\t\t\t\t\t<!-- <h4 class=\"subtitle\">选择支付方式</h4> -->\r\n");
      out.write("\t\t\t\t\t<div class=\"main\">\r\n");
      out.write("\t\t\t\t\t\t<h3 class=\"customfont\" style=\"float: left\">支付方式&emsp;</h3>\r\n");
      out.write("\t\t\t\t\t\t<h3 class=\"customfont add\" onclick=\"openNewCardWindow()\">\r\n");
      out.write("\t\t\t\t\t\t\t<span>新增&nbsp;</span><img\r\n");
      out.write("\t\t\t\t\t\t\t\tsrc=\"/images_meitao/icons/plus-symbol.svg\" alt=\"\"\r\n");
      out.write("\t\t\t\t\t\t\t\tclass=\"add-pay\">\r\n");
      out.write("\t\t\t\t\t\t</h3>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"creditcard-pay\">\r\n");
      out.write("\t\t\t\t\t\t\t<!-- <input type=\"radio\" name=\"paymethod\" value=\"creditcart\" onclick=\"chooseCreditPay()\"> <img class=\"pay-img\" src=\"/images_meitao/creditcard.svg\">信用卡<br> -->\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"creditcard-list\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t<div id=\"card_drop_list\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "main/card_drop_list.jsp", out, false);
      out.write("</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"add-card-info\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<form id=\"submitNewCardForm\" onsubmit=\"return false\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"info-block\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<div class=\"add-desc\">请确保持卡人姓名及其他信息与卡片上的一致</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<br> <br>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"customfont\">First Name</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\"firstName\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tclass=\"username-fill fillup\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"customfont\">Last Name</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\"lastName\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tclass=\"username-fill fillup\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<div class=\"name-phone\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"username\">Card Number</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<div class=\"name-phone-fill\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\"cardNumber\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tclass=\"username-fill fillup\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<div class=\"home\">Expiration</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<br>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<div class=\"home\">Month</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<select name=\"month\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"01\">01</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"02\">02</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"03\">03</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"04\">04</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"05\">05</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"06\">06</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"07\">07</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"08\">08</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"09\">09</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"10\">10</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"11\">11</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"12\">12</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<br>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<div class=\"home\">Year</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<select name=\"year\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"2018\">2018</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"2019\">2019</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"2020\">2020</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"2021\">2021</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"2022\">2022</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"2023\">2023</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"2024\">2024</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"2025\">2025</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"2026\">2026</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"2027\">2027</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"2028\">2028</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"2029\">2029</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"2030\">2030</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"2031\">2031</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"2032\">2032</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"2033\">2033</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"2034\">2034</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"2035\">2035</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"2036\">2036</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"2037\">2037</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"2038\">2038</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<p id=\"order_payment_method\" class=\"error_message\"></p>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<div class=\"savecard-save\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<!-- <span> <input id=\"saveCardCheckBox\" type=\"checkbox\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tclass=\"save-addr\">保存信用卡\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t</span> -->\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<button class=\"submit-btn\" onclick=\"submitNewCard()\">保存信用卡</button>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t</span>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</form>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"billing-info\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"customfont\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<span>账单地址</span> <span class=\"useAddr\"> 使用当前收货地址作为账单地址\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</span> <input id=\"isUsingShippingAddressAsBillingAddress\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\ttype=\"hidden\" value=\"false\"></input>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<br>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<form id=\"submitBillingAddressForm\" onsubmit=\"return false\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"info-block2\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"customfont\">First Name</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\"firstName\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tclass=\"username-fill fillup\" required>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"customfont\">Last Name</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\"lastName\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tclass=\"username-fill fillup\" required>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<div class=\"email-phone\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"email-get customfont\">Phone</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<div class=\"email-phone-fill keep-parallel\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<!-- <input type=\"email\" name=\"emailaddr\" class=\"emailaddr-fill fillup\"> -->\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<input id=\"addBillingShippingPhone1\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tclass=\"phonenum-fill fillup\" type=\"number\" placeholder=\"\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tmaxlength=\"3\" value=\"\" />\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"phonemark\">-</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<input id=\"addBillingShippingPhone2\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tclass=\"phonenum-fill fillup\" type=\"number\" placeholder=\"\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tmaxlength=\"3\" value=\"\" />\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"phonemark\">-</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<input id=\"addBillingShippingPhone3\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tclass=\"phonenum-fill fillup\" type=\"number\" placeholder=\"\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tmaxlength=\"4\" value=\"\" />\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<input id=\"addBillingShippingPhone\" name=\"shippingPhone\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\ttype=\"hidden\" class=\"emailaddr-fill fillup\" value=\"\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<br>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<div class=\"home\">Street</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\"street\" class=\"address-fill fillup\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\trequired>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<div class=\"city-state-zip keep-parallel\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"city\">City</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"state\">State</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"zip\">Zip Code</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<div class=\"city-state-zip-fill keep-parallel\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\"city\" class=\"city-fill fillup\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"city-state-zip-fill keep-parallel\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<select class=\"state-fill fillup\" name=\"state\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"AL\">AL</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"AK\">AK</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"AR\">AR</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"AZ\">AZ</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"CA\">CA</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"CO\">CO</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"CT\">CT</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"DC\">DC</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"DE\">DE</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"FL\">FL</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"GA\">GA</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"HI\">HI</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"IA\">IA</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"ID\">ID</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"IL\">IL</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"IN\">IN</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"KS\">KS</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"KY\">KY</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"LA\">LA</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"MA\">MA</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"MD\">MD</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"ME\">ME</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"MI\">MI</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"MN\">MN</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"MO\">MO</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"MS\">MS</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"MT\">MT</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"NC\">NC</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"NE\">NE</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"NH\">NH</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"NJ\">NJ</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"NM\">NM</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"NV\">NV</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"NY\">NY</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"ND\">ND</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"OH\">OH</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"OK\">OK</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"OR\">OR</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"PA\">PA</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"RI\">RI</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"SC\">SC</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"SD\">SD</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"TN\">TN</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"TX\">TX</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"UT\">UT</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"VT\">VT</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"VA\">VA</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"WA\">WA</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"WI\">WI</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"WV\">WV</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"WY\">WY</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\"zipcode\" class=\"zip-fill fillup\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<p id=\"order_billing_address\" class=\"error_message\"></p>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</form>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<!-- <div class=\"zhifubao-pay\">\r\n");
      out.write("                                <input type=\"radio\" name=\"paymethod\" value=\"zhifubao\" onclick=\"chooseZhifubaoPay()\"> <img class=\"pay-img\" src=\"/images_meitao/zhifubao.svg\">支付宝<br>\r\n");
      out.write("                                <div class=\"zhifubao-list\">\r\n");
      out.write("                                    \r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <div>\r\n");
      out.write("                                <input type=\"radio\" name=\"paymethod\" value=\"wechat\" onclick=\"chooseWechatPay()\"> <img class=\"pay-img\" src=\"/images_meitao/wechat.svg\">微信<br>  \r\n");
      out.write("                                <div class=\"wechat-list\">\r\n");
      out.write("                                    \r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div> -->\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<br> <br>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<!-- 订单详情中的购物车列表 -->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<div class=\"back\">\r\n");
      out.write("\t\t\t\t\t<a href=\"http://192.168.1.100:8090/cart/cart.html\"><img\r\n");
      out.write("\t\t\t\t\t\tsrc=\"/images_meitao/icons/left-arrow.svg\" alt=\"\">返回购物车</a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<div class=\"submit-form\">\r\n");
      out.write("\t\t\t\t\t<button class=\"submit-btn\" onclick=\"finalCheckout()\">确认支付</button>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t<div class=\"side-count container\">\r\n");
      out.write("\t\t\t\t<h3>订单摘要</h3>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<div id=\"order_item_list\">\r\n");
      out.write("\t\t\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "main/order_item_list.jsp", out, false);
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<div id=\"final_prices_block\">\r\n");
      out.write("\t\t\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "main/final_prices_block.jsp", out, false);
      out.write("</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("</section>");
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