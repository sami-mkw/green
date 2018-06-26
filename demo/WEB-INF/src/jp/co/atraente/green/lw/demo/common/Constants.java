package jp.co.atraente.green.lw.demo.common;

/**
 *
 * @author Sami Maekawa
 *
 */
public class Constants {

	public static String REDIRECT_URI = "http%3a%2f%2f127%2e0%2e0%2e1%3a8080%2fapp%2ftoken";

	public static String GRANT_TYPE_CODE = "authorization_code";

	public static String GRANT_TYPE_JWT = "urn:ietf:params:oauth:grant-type:jwt-bearer";

	// ==========================================================
	// URL
	// ==========================================================

	// public static String API_COMMON_DOMAIN = "https://apis.worksmobile.com/";
	public static String API_COMMON_DOMAIN = "https://apis.worksmobile.com/";

	public static String API_COMMON_DOMAIN_R = "https://apis.worksmobile.com/r/";

	public static String AUTHORIZATION_ENDPOINT = "https://auth.worksmobile.com/ba/" + LwDomainInfo.getApiId()
			+ "/service/authorize";

	public static String TOKEN_ENDPOINT = "https://auth.worksmobile.com/ba/" + LwDomainInfo.getApiId()
			+ "/service/token";

	public static String SERVER_TOKEN_URL = "https://authapi.worksmobile.com/b/" + LwDomainInfo.getApiId()
			+ "/server/token";

	public static String DRIVE_USERINFO_URL = API_COMMON_DOMAIN + LwDomainInfo.getApiId() + "/drive/getUserInfo/v1";

	public static String DRIVE_FILE_UPLOAD_URL = "https://jp1-file.drive.worksmobile.com/file/upload.api";

	public static String GET_BOT_LIST_URL = API_COMMON_DOMAIN + LwDomainInfo.getApiId() + "/message/getBotList/v2";

	public static String REGIST_BOT_URL = API_COMMON_DOMAIN + LwDomainInfo.getApiId() + "/message/registerBot/v2";

	public static String REGIST_BOT2DOMAIN_URL = API_COMMON_DOMAIN + LwDomainInfo.getApiId()
			+ "/message/registerBotDomain/v2";

	public static String SND_BOT_MSG_URL = API_COMMON_DOMAIN + LwDomainInfo.getApiId() + "/message/sendMessage/v2";

	public static String ORG_URL_PREFIX = API_COMMON_DOMAIN_R + LwDomainInfo.getApiId() + "/organization/v2/domains/"
			+ LwDomainInfo.getDomainId() + "/orgunits/";

	public static String REGIST_BOT_MENU_URL = API_COMMON_DOMAIN + LwDomainInfo.getApiId()
			+ "/message/setPersistentMenu";

	public static String GET_DOMAIN_CONTACT = API_COMMON_DOMAIN + LwDomainInfo.getApiId()
			+ "/contact/getDomainContact/v1";

	public static String ADD_PRIVATE_CONTACT_URL = API_COMMON_DOMAIN + "migration/" + LwDomainInfo.getApiId()
			+ "/contact/addPrivateContact/v1";

	public static String ENABLE_LINE_URL = API_COMMON_DOMAIN_R + LwDomainInfo.getApiId() + "/contact/v2/domains/"
			+ LwDomainInfo.getDomainId() + "/worksats/line/accounts";

	public static String ADD_LEVELS_URL = API_COMMON_DOMAIN_R + LwDomainInfo.getApiId() + "/organization/v2/domains/"
			+ LwDomainInfo.getDomainId() + "/levels";
	// ----------------
	// AUDIT APIs
	public static String AUDIT_ADMIN_LOG_URL = "http://audit.worksmobile.com/works/audit/log/admin/log.csv";
	public static String AUDIT_AUTH_LOG_URL = "https://audit.worksmobile.com/works/audit/log/auth/log.csv";
	public static String AUDIT_HOME_LOG_URL = "https://jp1-audit.worksmobile.com/works/audit/log/home/log.csv";
	public static String AUDIT_DRIVE_LOG_URL = "https://jp1-audit.worksmobile.com/works/audit/log/drive/log.csv";
	public static String AUDIT_CALENDER_LOG_URL = "https://jp1-audit.worksmobile.com/works/audit/log/calendar/log.csv";
	public static String AUDIT_CONTACT_LOG_URL = "https://jp1-audit.worksmobile.com/works/audit/log/contact/log.csv";
	public static String AUDIT_FORM_LOG_URL = "https://jp1-audit.worksmobile.com/works/audit/log/form/log.csv";
	public static String AUDIT_SHARE_LOG_URL = "https://jp1-audit.worksmobile.com/works/audit/log/share/log.csv";
	public static String AUDIT_NOTE_LOG_URL = "https://jp1-audit.worksmobile.com/works/audit/log/note/log.csv";
	public static String AUDIT_RCVMAIL_LOG_URL = "https://jp1-audit.worksmobile.com/works/audit/log/received-mail/log.csv";
	public static String AUDIT_SNTMAIL_LOG_URL = "https://jp1-audit.worksmobile.com/works/audit/log/sent-mail/log.csv";
	public static String AUDIT_MESSAGE_LOG_URL = "https://jp1-audit.worksmobile.com/works/audit/log/message/log.csv";

	// ----------------
	// AUDIT APIs
	public static String GRP_ADD_URL = API_COMMON_DOMAIN_R + LwDomainInfo.getApiId() + "/organization/v3/domains/"
			+ LwDomainInfo.getDomainId() + "/groups/";

	// ----------------
	// yakushoku APIs
	public static String POSITION_ADD_URL = API_COMMON_DOMAIN_R + LwDomainInfo.getApiId() + "/organization/v2/domains/"
			+ LwDomainInfo.getDomainId() + "/positions";
	public static String POSITION_REF_URL = API_COMMON_DOMAIN_R + LwDomainInfo.getApiId() + "/organization/v2/domains/"
			+ LwDomainInfo.getDomainId() + "/positions/%e4%b8%80%e8%88%ac%e7%a4%be%e5%93%a1";

	// ----------------
	// member APIs
	public static String MEMBER_CHANGE_URL = API_COMMON_DOMAIN_R + LwDomainInfo.getApiId() + "/organization/v2/domains/"
			+ LwDomainInfo.getDomainId() + "/users/sami.maekawa";

	// ----------------
	// Talk room APIs
	public static String CREATE_ROOM_URL = API_COMMON_DOMAIN + LwDomainInfo.getApiId() + "/message/createRoom";

	public static String CONTENT_TYPE_JSON = "application/json; charset=UTF-8";
	public static String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded; charset=UTF-8";

}