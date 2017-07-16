package org.iiitb.bmtc.database.sql;

public class DataAccessObjectUsingSSH {

	/*private static void doSshTunnel( String strSshUser, String strSshPassword, String strSshHost, int nSshPort, String strRemoteHost, int nLocalPort, int nRemotePort ) throws JSchException
		  {
		    final JSch jsch = new JSch();
		    Session session = jsch.getSession( strSshUser, strSshHost, 22 );
		    session.setPassword( strSshPassword );
		     
		    final Properties config = new Properties();
		    config.put( "StrictHostKeyChecking", "no" );
		    session.setConfig( config );
		     
		    session.connect();
		    session.setPortForwardingL(nLocalPort, strRemoteHost, nRemotePort);
		  }
		   
		  public static void main(String[] args)
		  {
		    try
		    {
		      String strSshUser = "administrator";                  // SSH loging username
		      String strSshPassword = "!!!Bt@098";                   // SSH login password
		      String strSshHost = "192.168.25.107";          // hostname or ip or SSH server
		      int nSshPort = 22;                                    // remote SSH host port number
		      String strRemoteHost = "192.168.25.107";  // hostname or ip of your database server
		      int nLocalPort = 3306;                                // local port number use to bind SSH tunnel
		      int nRemotePort = 3306;                               // remote port number of your database 
		      String strDbUser = "root";                    // database loging username
		      String strDbPassword = "bmtcgps";                    // database login password
		       
		      DataAccessObjectUsingSSH.doSshTunnel(strSshUser, strSshPassword, strSshHost, nSshPort, strRemoteHost, nLocalPort, nRemotePort);
		       
		      Class.forName("com.mysql.jdbc.Driver");
		      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:"+nLocalPort, strDbUser, strDbPassword);
		      con.close();
		    }
		    catch( Exception e )
		    {
		      e.printStackTrace();
		    }
		    finally
		    {
		      System.exit(0);
		    }
		  }	*/
	}
		
