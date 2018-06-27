package jp.co.atraente.green.saml.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.xml.security.utils.Base64;
import org.joda.time.DateTime;
import org.opensaml.DefaultBootstrap;
import org.opensaml.common.SAMLVersion;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.Audience;
import org.opensaml.saml2.core.AudienceRestriction;
import org.opensaml.saml2.core.AuthnStatement;
import org.opensaml.saml2.core.Conditions;
import org.opensaml.saml2.core.Issuer;
import org.opensaml.saml2.core.NameID;
import org.opensaml.saml2.core.Response;
import org.opensaml.saml2.core.Status;
import org.opensaml.saml2.core.StatusCode;
import org.opensaml.saml2.core.Subject;
import org.opensaml.saml2.core.SubjectConfirmation;
import org.opensaml.saml2.core.SubjectConfirmationData;
import org.opensaml.saml2.core.impl.AssertionBuilder;
import org.opensaml.saml2.core.impl.AudienceBuilder;
import org.opensaml.saml2.core.impl.AudienceRestrictionBuilder;
import org.opensaml.saml2.core.impl.AuthnStatementBuilder;
import org.opensaml.saml2.core.impl.ConditionsBuilder;
import org.opensaml.saml2.core.impl.IssuerBuilder;
import org.opensaml.saml2.core.impl.NameIDBuilder;
import org.opensaml.saml2.core.impl.ResponseBuilder;
import org.opensaml.saml2.core.impl.StatusBuilder;
import org.opensaml.saml2.core.impl.StatusCodeBuilder;
import org.opensaml.saml2.core.impl.SubjectBuilder;
import org.opensaml.saml2.core.impl.SubjectConfirmationBuilder;
import org.opensaml.saml2.core.impl.SubjectConfirmationDataBuilder;
import org.opensaml.xml.Configuration;
import org.opensaml.xml.ConfigurationException;
import org.opensaml.xml.io.Marshaller;
import org.opensaml.xml.io.MarshallingException;
import org.w3c.dom.Element;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

import jp.co.atraente.green.saml.common.Constants;

public class SndAuthnRes implements Servlet {

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

		System.out.println("start SndAuthnRes >>");

		String inResponseTo = (String) req.getParameter("inResponseTo");
		String email = (String) req.getParameter("email");
		String acs = (String) req.getParameter("acs");
		String relayState = (String) req.getParameter("relayState");

		try {
			DefaultBootstrap.bootstrap();
		} catch (ConfigurationException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		// ----------------------------
		// ▼▼▼ Response ▼▼▼
		// ----------------------------
		Response response = new ResponseBuilder().buildObject("urn:oasis:names:tc:SAML:2.0:protocol", "Response",
				"saml2p");

		response.setIssueInstant(new DateTime());
		response.setInResponseTo(inResponseTo);
		Issuer issuer = new IssuerBuilder().buildObject(SAMLConstants.SAML20_NS, "Issuer", "samlp");
		issuer.setValue(Constants.IDP_ID);
		response.setIssuer(issuer);
		response.setDestination(acs);

		StatusCode statusCode = new StatusCodeBuilder().buildObject();
		statusCode.setValue("urn:oasis:names:tc:SAML:2.0:status:Success");
		Status status = new StatusBuilder().buildObject();
		status.setStatusCode(statusCode);
		response.setStatus(status);
		response.setVersion(SAMLVersion.VERSION_20);

		// ----------------------------
		// ▼▼ Assertion ▼▼
		// ----------------------------

		// ------------------------------------------------
		// ▼ Subject ▼
		// ------------------------------------------------
		// NameID
		NameID nameId = new NameIDBuilder().buildObject();
		nameId.setFormat("urn:oasis:names:tc:SAML:1.1:nameid-format:emailAddress");
		nameId.setValue(email);
		// SubjectConfirmationData;
		SubjectConfirmationData subjectConfirmationData = new SubjectConfirmationDataBuilder().buildObject();
		subjectConfirmationData.setInResponseTo(inResponseTo);
		subjectConfirmationData.setNotOnOrAfter(new DateTime(2018, 12, 31, 12, 30));
		subjectConfirmationData.setRecipient(acs);
		// SubjectConfirmation
		SubjectConfirmation subjectConfirmation = new SubjectConfirmationBuilder().buildObject();
		subjectConfirmation.setMethod("urn:oasis:names:tc:SAML:2.0:cm:bearer");
		subjectConfirmation.setSubjectConfirmationData(subjectConfirmationData);
		// Subject
		SubjectBuilder subjectBuilder = new SubjectBuilder();
		Subject subject = subjectBuilder.buildObject();
		subject.setNameID(nameId);
		subject.getSubjectConfirmations().add(subjectConfirmation);
		// ------------------------------------------------
		// ▲ Subject ▲
		// ------------------------------------------------

		// ------------------------------------------------
		// ▼ Conditions ▼
		// ------------------------------------------------
		Audience audience = new AudienceBuilder().buildObject();
		audience.setAudienceURI(acs);
		AudienceRestriction audienceRestriction = new AudienceRestrictionBuilder().buildObject();
		audienceRestriction.getAudiences().add(audience);

		Conditions conditions = new ConditionsBuilder().buildObject();
		conditions.setNotBefore(new DateTime(2017, 12, 1, 12, 30));
		conditions.setNotOnOrAfter(new DateTime(2018, 12, 31, 21, 30));

		conditions.getAudienceRestrictions().add(audienceRestriction);
		// ------------------------------------------------
		// ▲ Conditions ▲
		// ------------------------------------------------

		// ------------------------------------------------
		// ▼ AuthnStatement ▼
		// ------------------------------------------------
		AuthnStatement authnStatement = new AuthnStatementBuilder().buildObject();
		authnStatement.setAuthnInstant(new DateTime());
		authnStatement.setSessionIndex("1234567890");
		authnStatement.setSessionNotOnOrAfter(new DateTime(2018, 12, 31, 12, 30));

		// ------------------------------------------------
		// ▲ AuthnStatement ▲
		// -----------------------------------------------

		Assertion assertion = new AssertionBuilder().buildObject("urn:oasis:names:tc:SAML:2.0:assertion", "Assertion",
				"saml2");
		assertion.setID("asdfghjkl");
		assertion.setIssueInstant(new DateTime());
		assertion.setVersion(SAMLVersion.VERSION_20);
		assertion.setSubject(subject);
		assertion.setConditions(conditions);
		assertion.getAuthnStatements().add(authnStatement);
		response.getAssertions().add(assertion);
		// ----------------------------
		// ▲▲ Assertion ▲▲
		// ----------------------------

		Marshaller marshaller = Configuration.getMarshallerFactory().getMarshaller(response);
		Element authDOM;
		try {
			authDOM = marshaller.marshall(response);

			DOMImplementationLS domImplLS = (DOMImplementationLS) authDOM.getOwnerDocument().getImplementation();
			LSSerializer serializer = domImplLS.createLSSerializer();
			serializer.getDomConfig().setParameter("xml-declaration", false);

			String messageXml = serializer.writeToString(authDOM);

			// ★ Print Log
			System.out.println("Message: " + messageXml);

			// Deflater deflater = new Deflater(Deflater.DEFLATED, true);
			// ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			// DeflaterOutputStream deflaterOutputStream = new
			// DeflaterOutputStream(byteArrayOutputStream, deflater);
			// deflaterOutputStream.write(messageXml.getBytes());
			// deflaterOutputStream.close();
			// String samlRes =
			// Base64.encodeBase64String(byteArrayOutputStream.toByteArray());

			String samlRes = Base64.encode(messageXml.getBytes());

			System.out.println("SAMLResponse: " + samlRes);
			req.setAttribute("SAMLResponse", samlRes);
			req.setAttribute("RelayState", relayState);
			req.getRequestDispatcher("/jsp/post.jsp").forward(req, res);

			// // -------------------------------------------------
			// // 送付するパラメータの準備
			// // -------------------------------------------------
			// HttpServletResponse tmpRes = (HttpServletResponse) res;
			// String url = Constants.IDP_SSO_URL + "?SAMLResponse=" + samlRes +
			// "&RelayState=" + relayState;
			//
			// // ★ Print Log
			// System.out.println("RedirectTo:" + url);
			// tmpRes.sendRedirect(url);
		} catch (MarshallingException e) {
			e.printStackTrace();
		}

		System.out.println("<< end SndAuthnRes");

	}
}
