//--------------------------------------------------------------------------------------//
//																						//
// File Name:	UrlConnectionManager.java												//
// Programmer:	J.T. Blevins (jt.blevins@gmail.com)										//
// Date:		09/23/2018																//
// Purpose:		A class to allow connections to a specified url (using Java's			//
//				HttpURLConnection class).												//
//																						//
//--------------------------------------------------------------------------------------//

package com.sugarcubes.myglucose.urlconnections;

import android.content.ContentResolver;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import static com.sugarcubes.myglucose.activities.MainActivity.DEBUG;

public class UrlConnection
{
	protected ContentResolver contentResolver;
	private String LOG_TAG = getClass().getSimpleName();
	private HttpURLConnection httpURLConnection;

	public UrlConnection( URL url )
	{
		try
		{
			httpURLConnection = (HttpURLConnection) url.openConnection();
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
		setup();

	} // constructor

	private void setup()
	{
		httpURLConnection.setReadTimeout( 15000 );
		httpURLConnection.setConnectTimeout( 15000 );
//		try
//		{
//			TLSSocketFactory tls = new TLSSocketFactory( context );
//			// https://blog.dev-area.net/2015/08/13/android-4-1-enable-tls-1-1-and-tls-1-2/
//			// Android Jelly Bean devices require TLS to be specified explicitly
//			HttpsURLConnection.setDefaultSSLSocketFactory( tls );
//			setSSLSocketFactory( tls );
//		}
//		catch( Exception e )
//		{
//			e.printStackTrace();
//		}
		httpURLConnection.setDoInput( true );
		httpURLConnection.setDoOutput( true );

	} // setup


	/**
	 * performRequest
	 * Returns a Json-encoded string, sent from the server
	 * @param postDataParams - a set of parameters to specify what data to send
	 * @return a Json-encoded string
	 */
	public String performRequest( HashMap<String, String> postDataParams )
	{
		StringBuilder responseStringBuilder = new StringBuilder();
		try
		{
			httpURLConnection.setRequestMethod( "POST" );						// URL is already set in constructor
			OutputStream outputStream = httpURLConnection.getOutputStream();	// Get ref to stream to write to server
			BufferedWriter bufferedWriter = new BufferedWriter(
					new OutputStreamWriter( outputStream, "UTF-8" ) );
			bufferedWriter.write( getPostDataString( postDataParams ) ); // send the data (buffered)

			bufferedWriter.flush();							// Clear the buffer
			bufferedWriter.close();							// Close the buffer stream
			outputStream.close();							// Close the connection
			int responseCode = httpURLConnection.getResponseCode();			// Return the response code

			if( responseCode == HttpsURLConnection.HTTP_OK )
			{
				if( DEBUG ) Log.e( LOG_TAG, "HTTP Response is OK" );
				String line;
				InputStreamReader inputStreamReader 		// Get ref to stream the response
						= new InputStreamReader( httpURLConnection.getInputStream() );
				BufferedReader br 							// buffer the response
						= new BufferedReader( inputStreamReader );
				while( ( line = br.readLine() ) != null )	// Read each line
				{
					responseStringBuilder.append( line );	// And append it to a stringReader object
				}
			}
			else
			{
				if( DEBUG ) Log.e( LOG_TAG, "Either no HTTP Response, or bad request..." );
				responseStringBuilder = new StringBuilder();// At least instantiate the object
			}

		}
		catch( Exception e )
		{
			e.printStackTrace();
		}

		return responseStringBuilder.toString();

	} // performPostCall


	/**
	 * getPostDataString
	 * Converts a set of parameters to a url-encoded string
	 * @param params - the parameters to convert
	 * @return a url-encoded string
	 * @throws UnsupportedEncodingException when encoding unknown
	 */
	private String getPostDataString( HashMap<String, String> params ) throws UnsupportedEncodingException
	{
		StringBuilder result = new StringBuilder();
		boolean first = true;
		for( Map.Entry<String, String> entry : params.entrySet() )
		{
			if( first )
			{
				first = false;
			}
			else
			{
				result.append( "&" );
			}

			result.append( URLEncoder.encode( entry.getKey(), "UTF-8" ) );
			result.append( "=" );
			result.append( URLEncoder.encode( entry.getValue(), "UTF-8" ) );
		}

		return result.toString();

	} // getPostDataString


} // class
