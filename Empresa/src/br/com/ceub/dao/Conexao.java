package br.com.ceub.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private Connection conexao = null;
	private java.sql.Connection conexaoMySql;

	private static org.postgresql.jdbc4.Jdbc4Connection conexaoPostGres;

	public static org.postgresql.jdbc4.Jdbc4Connection getCONEXAOPostGres() {
		try {
			if (conexaoPostGres == null || conexaoPostGres.isClosed()) {
				try {
					// String ip = "192.168.0.11";
					String ip = "localhost";
					String url = "jdbc:postgresql://" + ip + ":5432/empresa?user=postgres&password=admin";
					conexaoPostGres = (org.postgresql.jdbc4.Jdbc4Connection) DriverManager.getConnection(url);

				} catch (SQLException e) {
					e.printStackTrace();
					conexaoPostGres = null;
					System.exit(0);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conexaoPostGres;
	}
}
