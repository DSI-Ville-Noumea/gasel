package nc.ccas.gasel.model.habitat;

import java.util.Date;

import nc.ccas.gasel.Feminin;
import nc.ccas.gasel.model.ModifListener;
import nc.ccas.gasel.model.ModifUtils;
import nc.ccas.gasel.model.core.Utilisateur;
import nc.ccas.gasel.model.habitat.auto._AideLogement;

@Feminin
public class AideLogement extends _AideLogement implements ModifListener {

	private static final long serialVersionUID = -5655470749582958502L;

	public void modified(Utilisateur user, Date date) {
		ModifUtils.triggerModified(user, date, getDemande());
	}
	
}



