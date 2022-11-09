package com.hali.openhangout.filter;

import java.io.IOException;
import java.sql.Connection;

import com.hali.util.DaoHelper;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.ext.ReaderInterceptor;
import jakarta.ws.rs.ext.ReaderInterceptorContext;

@Provider
public class JDBCInterceptor implements ReaderInterceptor {

	@Override
	public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
		// TODO Auto-generated method stub
		System.out.println("JDBCInterceptor Runnig ...");
		Connection conn = DaoHelper.getDbConn();
		if(conn == null) {
		System.out.println("DATABASE CONNECTION ERROR");
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		System.out.println("DATABASE IS UP");
		try {
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

		return context.proceed();
	}

}
