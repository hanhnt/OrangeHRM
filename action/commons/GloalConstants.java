package commons;

import java.io.File;

public class GloalConstants {
	public static final String USER_LINK="https://demo.nopcommerce.com/";
	public static final String ADMIN_LINK="https://admin-demo.nopcommerce.com/login?ReturnUrl=/admin/";
	
	public static final String PROJECT_PATH= System.getProperty("user.dir");
	public static final String UPLOAD_FOLDER_PATH=PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
	public static final String DOWNLOAD_FOLDER_PATH=PROJECT_PATH + File.separator + "downloadFiles";
	
	public static final long LONG_TIME_OUT=30;
	public static final long SHORT_TIME_OUT=5;
}
