package nc.ccas.gasel.services;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class AuthImpl implements Auth {

	public AuthResult authenticate(String userName) {
		ObjectNode user = Radi.getUser(userName);

		if (user == null) {
			// Utilisateur non trouvé ou erreur
			return AuthResult.invalid();
		}

		String email = user.get("mail").asText();
		String displayName = user.get("displayName").asText();
		String ref = user.get("distinguishedName").asText();

		AuthResult retval;
		int rindex = displayName.lastIndexOf(' ');
		if (rindex >= 0) {
			retval = AuthResult.valid( //
					displayName.substring(0, rindex).trim(), // nom
					displayName.substring(rindex + 1), // prénom
					email, ref);
		} else {
			retval = AuthResult.valid(displayName, "", email, ref);
		}
		return retval;
	}

	public AuthResult authenticate(String user, String password) {
		return authenticate(user);
	}

}