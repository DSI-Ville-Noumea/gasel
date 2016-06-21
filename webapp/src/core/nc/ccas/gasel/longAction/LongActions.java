package nc.ccas.gasel.longAction;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import nc.ccas.gasel.longAction.LongAction.Status;

import org.apache.log4j.Logger;

import edu.emory.mathcs.backport.java.util.Collections;

public class LongActions {

	/**
	 * Délai avant suppression si le résultat n'est pas récupéré.
	 */
	private static final long EXPIRATION_DELAY_MS = 8 * 60 * 60 * 1000;

	private static final Logger LOG = Logger.getLogger(LongAction.class);

	static class Action implements Runnable {
		String uuid;
		Status status;
		LongAction iface;

		@Override
		public void run() {
			try {
				status = Status.RUNNING;
				iface.run();
				status = Status.SUCCESS;
			} catch (Throwable t) {
				LOG.error("Long action failed", t);
				status = Status.FAILURE;
			} finally {
				try {
					Thread.sleep(EXPIRATION_DELAY_MS);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					expire(uuid);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static Map<String, Action> actions = Collections
			.synchronizedMap(new TreeMap<>());

	/**
	 * Starts a long action and returns its ID.
	 */
	public static String start(LongAction iface) {
		String uuid = UUID.randomUUID().toString();

		final Action action = new Action();
		action.uuid = uuid;
		action.status = Status.PENDING;
		action.iface = iface;

		actions.put(uuid, action);

		new Thread(action).start();

		return uuid;
	}

	/**
	 * Returns a long action from its ID.
	 */
	public static LongAction get(String id) {
		Action action = actions.get(id);
		if (action == null) {
			return null;
		}
		return action.iface;
	}

	/**
	 * Returns the status of a long action (from its id).
	 */
	public static Status statusOf(String id) {
		Action action = actions.get(id);
		if (action == null) {
			return null;
		}
		return action.status;
	}

	public static void expire(String id) {
		actions.remove(id);
	}

}
