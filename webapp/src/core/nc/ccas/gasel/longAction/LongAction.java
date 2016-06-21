package nc.ccas.gasel.longAction;

import java.util.List;

public interface LongAction extends Runnable {

	public static interface StepProgress {
		String getLabel();

		Float getProgress();
	}

	public static interface Result {
		String getContentType();

		String getFileName();

		byte[] getBytes();
	}

	public static enum Status {
		PENDING("En attente"), //
		RUNNING("En cours"), //
		SUCCESS("Terminé"), //
		FAILURE("Échec");

		String label;

		Status(String label) {
			this.label = label;
		}
	}

	String getTitle();

	List<StepProgress> getProgress();

	Result getResult();

}
