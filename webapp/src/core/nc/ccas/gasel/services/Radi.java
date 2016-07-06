package nc.ccas.gasel.services;

import java.io.IOException;

import nc.ccas.gasel.AppContext;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public abstract class Radi {

	private static ObjectMapper mapper;

	public static ObjectNode getUser(String userName) {
		if (mapper == null) {
			mapper = new ObjectMapper();
		}
		
		String radiUrl = AppContext.getInitParameter("RADI.url");
		if (radiUrl == null) {
			// Not configured: fake authentication
			return fakeUser(userName);
		}

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(radiUrl + "/" + userName);

		CloseableHttpResponse response;
		try {
			response = httpclient.execute(httpget);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		int sc = response.getStatusLine().getStatusCode();
		if (sc < 200 || sc >= 300) {
			return null;
		}

		try {
			return mapper.readValue(response.getEntity().getContent(),
					ObjectNode.class);

		} catch (UnsupportedOperationException | IOException e) {
			e.printStackTrace();
			return null;

		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	private static ObjectNode fakeUser(String userName) {
		ObjectNode node = mapper.createObjectNode();
		node.put("distinguishedName", "CN=Test User,OU=test,DC=test");
		node.put("displayName", "Test User");
		node.put("mail", AppContext.mail("default-recipient"));
		return node;
	}

}
