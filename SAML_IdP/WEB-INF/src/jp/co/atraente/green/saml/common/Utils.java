package jp.co.atraente.green.saml.common;

public class Utils {

	/**
	 * 文字列が空文字やNullであるかを確認する。
	 *
	 * @param 対象文字列t
	 * @return
	 */
	public static boolean isEmpty(String target) {
		if ((null == target) || (target.length() == 0)) {
			return true;
		} else {
			return false;
		}
	}
}
