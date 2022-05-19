package tw.com.eeit.breadshop.conn;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 產生連線的工廠。 使用資料庫為SQLite，一種內嵌式的小型關聯式資料庫，無須登入。
 */
public class ConnectionFactory {

	private static String DATABASE_PATH = "C:\\temp";
	
	// 取得連線的方法，可替換為SQL Server連線。
	public static Connection getconnection() throws Exception {

		System.out.println("SQLite存放路徑：" + DATABASE_PATH);
		
		File f = new File(DATABASE_PATH);
		if(!f.exists()) {
			f.mkdir();
		}

		Class.forName("org.sqlite.JDBC");

		Connection conn = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_PATH + "\\SQLite.db");


		return conn;
	}

}
