package jp.co.atraente.green.saml.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.ssl.Base64;
import org.joda.time.DateTime;
import org.opensaml.Configuration;
import org.opensaml.DefaultBootstrap;
import org.opensaml.common.SAMLVersion;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.core.AuthnRequest;
import org.opensaml.saml2.core.Issuer;
import org.opensaml.saml2.core.NameIDPolicy;
import org.opensaml.saml2.core.impl.AuthnRequestBuilder;
import org.opensaml.saml2.core.impl.IssuerBuilder;
import org.opensaml.saml2.core.impl.NameIDPolicyBuilder;
import org.opensaml.xml.ConfigurationException;
import org.opensaml.xml.io.Marshaller;
import org.opensaml.xml.io.MarshallingException;
import org.w3c.dom.Element;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

import jp.co.atraente.green.saml.common.Constants;

public class StartSSO implements Servlet {

	@Override
	public void destroy() {

	}

	@Override
	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public String getServletInfo() {
		return null;
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

		System.out.println("start StartSSO >>");

		try {
			DefaultBootstrap.bootstrap();
		} catch (ConfigurationException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		IssuerBuilder issuerBuilder = new IssuerBuilder();
		Issuer issuer = issuerBuilder.buildObject(SAMLConstants.SAML20_NS, "Issuer", "samlp");
		issuer.setValue(Constants.SP_ID);

		NameIDPolicyBuilder nameIdPolicyBuilder = new NameIDPolicyBuilder();
		NameIDPolicy nameIdPolicy = nameIdPolicyBuilder.buildObject();
		nameIdPolicy.setFormat("urn:oasis:names:tc:SAML:1.1:nameid-format:unspecified");

		AuthnRequestBuilder authnRequestBuilder = new AuthnRequestBuilder();
		AuthnRequest authnRequest = authnRequestBuilder.buildObject("urn:oasis:names:tc:SAML:2.0:protocol",
				"AuthnRequest", "samlp");
		authnRequest.setIssuer(issuer);
		authnRequest.setNameIDPolicy(nameIdPolicy);
		authnRequest.setAssertionConsumerServiceURL(Constants.SP_ACS_URL);
		authnRequest.setIssueInstant(new DateTime());
		authnRequest.setProtocolBinding("urn:oasis:names:tc:SAML:2.0:bindings:HTTP-POST");
		authnRequest.setVersion(SAMLVersion.VERSION_20);
		authnRequest.setID("ctestid890123456789012345678901234567890");

		Marshaller marshaller = Configuration.getMarshallerFactory().getMarshaller(authnRequest);
		Element authDOM;
		try {
			authDOM = marshaller.marshall(authnRequest);

			DOMImplementationLS domImplLS = (DOMImplementationLS) authDOM.getOwnerDocument().getImplementation();
			LSSerializer serializer = domImplLS.createLSSerializer();
			serializer.getDomConfig().setParameter("xml-declaration", false);

			String messageXml = serializer.writeToString(authDOM);

			Deflater deflater = new Deflater(Deflater.DEFLATED, true);
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream, deflater);
			deflaterOutputStream.write(messageXml.getBytes());
			deflaterOutputStream.close();

			String samlRequest = new String(Base64.encodeBase64(byteArrayOutputStream.toByteArray(), false));
			samlRequest = URLEncoder.encode(samlRequest, "UTF-8");

			// -------------------------------------------------
			// 送付するパラメータの準備
			// -------------------------------------------------
			HttpServletResponse tmpRes = (HttpServletResponse) res;
			String url = Constants.IDP_SSO_URL + "?SAMLRequest=" + samlRequest;
			//String url = "https://127.1.1.0:8080/idp/sso?client_id=1234567890&response_type=code&redirect_uri=xxxx&SAMLRequest="
			//		+ samlRequest;
			// Print Log
			System.out.println("RedirectTo:" + url);
			tmpRes.sendRedirect(url);
		} catch (MarshallingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		System.out.println("<< end StartSSO");
	}
}
