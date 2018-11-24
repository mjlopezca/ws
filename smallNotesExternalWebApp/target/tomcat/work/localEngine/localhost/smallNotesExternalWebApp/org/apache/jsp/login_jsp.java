package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fauthz_005fauthorize_0026_005fifNotGranted;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fauthz_005fauthorize_0026_005fifNotGranted = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fauthz_005fauthorize_0026_005fifNotGranted.release();
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE HTML>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("\t<title>Bienvenido a SmallNotesExternalWebApp</title>\n");
      out.write("  \t<style type=\"text/css\">\n");
      out.write("  \t\tbody {background-color:#eaeaea}\n");
      out.write("  \t</style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\t<h1>Bienvenido a SmallNotesExternalWebApp</h1>\n");
      out.write("\t\n");
      out.write("\t<div id=\"contenido\">\n");
      out.write("\t\t");
      if (_jspx_meth_authz_005fauthorize_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t</div>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_authz_005fauthorize_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  authz:authorize
    org.springframework.security.taglibs.authz.JspAuthorizeTag _jspx_th_authz_005fauthorize_005f0 = (org.springframework.security.taglibs.authz.JspAuthorizeTag) _005fjspx_005ftagPool_005fauthz_005fauthorize_0026_005fifNotGranted.get(org.springframework.security.taglibs.authz.JspAuthorizeTag.class);
    _jspx_th_authz_005fauthorize_005f0.setPageContext(_jspx_page_context);
    _jspx_th_authz_005fauthorize_005f0.setParent(null);
    // /login.jsp(19,2) name = ifNotGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_authz_005fauthorize_005f0.setIfNotGranted("IS_AUTHENTICATED_FULLY");
    int _jspx_eval_authz_005fauthorize_005f0 = _jspx_th_authz_005fauthorize_005f0.doStartTag();
    if (_jspx_eval_authz_005fauthorize_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\n");
      out.write("\t\t\t<div id=\"acceso\">\n");
      out.write("\t\t\t\t<div class=\"iniciosesion\">\n");
      out.write("\t\t\t\t\t<h2>Inicie sesión</h2>\n");
      out.write("\t\t\t\t\t<form id=\"login\" name=\"login\" action=\"login.do\" method=\"post\" class=\"cmxform\">\n");
      out.write("\t\t\t\t\t\t<div class=\"field\">\n");
      out.write("\t\t\t\t\t\t\t<label for=\"j_username\">Email</label> \n");
      out.write("\t\t\t\t\t\t\t<input id=\"j_username\" name=\"j_username\"  type=\"text\"/>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t<div class=\"field\">\n");
      out.write("\t\t\t\t\t\t\t<label for=\"j_password\">Contraseña</label>\n");
      out.write("\t\t\t\t\t\t\t<input id=\"j_password\"  name=\"j_password\"  type=\"password\"/>\n");
      out.write("\t\t\t\t\t\t\t<p><input type=\"submit\" value=\"Aceptar\"/></p>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\t<p>[Usuario de prueba: user1 / pwd]</p>\n");
      out.write("\t\t\t\t\t</form>\n");
      out.write("\t\t\t\t\t<div id=\"messageBox\">\n");
      out.write("\t\t\t\t\t\t");
      if (_jspx_meth_c_005fif_005f0(_jspx_th_authz_005fauthorize_005f0, _jspx_page_context))
        return true;
      out.write("\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t");
    }
    if (_jspx_th_authz_005fauthorize_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fauthz_005fauthorize_0026_005fifNotGranted.reuse(_jspx_th_authz_005fauthorize_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fauthz_005fauthorize_0026_005fifNotGranted.reuse(_jspx_th_authz_005fauthorize_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_authz_005fauthorize_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_authz_005fauthorize_005f0);
    // /login.jsp(37,6) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${not empty SPRING_SECURITY_LAST_EXCEPTION.message}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<em class=\"error\" style=\"float:left\">Credenciales no válidas</em>");
        int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
    return false;
  }
}
