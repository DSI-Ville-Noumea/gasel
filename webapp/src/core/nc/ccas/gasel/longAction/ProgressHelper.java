package nc.ccas.gasel.longAction;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Helper for long actions with steps.
 */
public class ProgressHelper {

	private static class Step implements LongAction.StepProgress {

		final String label;
		final float count;

		final long startTime = System.currentTimeMillis();

		float progress = 0;

		Step(String label, float count) {
			this.label = label;
			this.count = count;
		}

		@Override
		public String getLabel() {
			return label;
		}

		@Override
		public Float getProgress() {
			if (count <= 0) {
				return null;
			}
			return progress / count;
		}
	}

	private final String label;
	private final Logger log;

	private final LinkedList<LongAction.StepProgress> steps = new LinkedList<>();
	private final long startTime = System.currentTimeMillis();

	public ProgressHelper(String label, Logger log) {
		this.label = label;
		this.log = log;
	}

	public List<LongAction.StepProgress> getProgress() {
		return steps;
	}

	public void start(String label) {
		start(label, 0);
	}

	public void start(String label, float count) {
		logTimeOfLastStep();
		steps.add(new Step(label, 0));
	}

	public void progressBy(int count) {
		((Step) steps.getLast()).progress += count;
	}

	public void finished() {
		logTimeOfLastStep();
		logDuration("action", startTime, 0);
	}

	private void logTimeOfLastStep() {
		if (steps.size() == 0) {
			return;
		}
		Step lastStep = (Step) steps.getLast();
		lastStep.progress = lastStep.count; // we are at 100%
		logDuration("step «" + lastStep.getLabel() + "»", lastStep.startTime,
				lastStep.count);
	}

	private void logDuration(String s, long startTime, float count) {
		long duration = (System.currentTimeMillis() - startTime);
		String message = String.format("%s: %s took %d ms", label, s, duration);
		if (count > 0) {
			message += String.format(" (%.2f ms/item)", duration / count);
		}
		log.info(message);
	}

}