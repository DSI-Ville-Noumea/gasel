package nc.ccas.gasel.longAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nc.ccas.gasel.longAction.LongAction.Result;
import nc.ccas.gasel.longAction.LongAction.Status;
import nc.ccas.gasel.longAction.LongAction.StepProgress;

public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 8302192673293517893L;

	private static final Pattern PATH_RE = Pattern
			.compile("/([^/]+)(/result)?");

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {
		String path = req.getPathInfo();
		if (path == null || "".equals(path) || "/".equals(path)) {
			notFound(resp, "Pas de liste pour ce service.");
			return;
		}
		Matcher pathMatcher = PATH_RE.matcher(path);
		if (!pathMatcher.matches()) {
			notFound(resp, "Chemin invalide: " + path);
			return;
		}

		String id = pathMatcher.group(1);

		LongAction iface = LongActions.get(id);
		if (iface == null) {
			notFound(resp, "Pas d'action longue " + id);
			return;
		}

		if ("/result".equals(pathMatcher.group(2))) {
			sendResult(resp, id, iface);
			return;
		}

		sendStatusPage(req, resp, id, iface);
	}

	private void notFound(HttpServletResponse resp, String title)
			throws IOException {
		resp.setStatus(404);
		PrintWriter out = resp.getWriter();
		htmlHeader(out, title, false);
		out.close();
	}

	private void sendResult(HttpServletResponse resp, String id,
			LongAction iface) throws IOException {
		Result result = iface.getResult();
		if (result == null) {
			resp.sendError(404, "Pas de résultat pour l'action " + id);
			return;
		}

		resp.setContentType(result.getContentType());
		resp.setHeader("Content-Disposition",
				"attachment; filename=" + result.getFileName());

		byte[] bytes = result.getBytes();
		resp.setContentLength(bytes.length);

		ServletOutputStream out = resp.getOutputStream();
		out.write(bytes);
		out.close();
	}

	private void sendStatusPage(HttpServletRequest req,
			HttpServletResponse resp, String id, LongAction iface)
			throws IOException {
		resp.setContentType("text/html; charset=utf-8");

		// no cache
		resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Expires", "0");

		PrintWriter out = resp.getWriter();

		Status status = LongActions.statusOf(id);

		switch (status) {
		case SUCCESS:
		case FAILURE:
			htmlHeader(out, iface, false);
			break;

		case PENDING:
		case RUNNING:
			htmlHeader(out, iface, true);
			break;
		}

		out.print("<p>");
		out.print(status.label);
		out.println("</p>");

		List<StepProgress> progress = iface.getProgress();
		if (progress != null && progress.size() > 0) {
			out.println("<ul>");
			for (StepProgress p : progress) {
				out.print("<li>");
				out.println(p.getLabel());
				Float v = p.getProgress();
				if (v != null) {
					progressBar(out, v);
				}
				out.println("</li>");
			}
			out.println("</ul>");
		}

		switch (status) {
		case SUCCESS:
		case FAILURE:
			out.println("<button class=\"btn btn-primary\" onclick=\"window.close()\">Fermer</button>");
		default:
			break;
		}

		if (status == Status.SUCCESS) {
			if (iface.getResult() != null) {
				out.print("<a class=\"btn btn-success\" href=\"");
				out.print(id);
				out.println("/result\">Télécharger</a>");
				out.print("<script>document.location.href = \"");
				out.print(id);
				out.println("/result\";</script>");
			} else {
				out.println("(pas de résultat à télécharger)");
			}
		}

		htmlFooter(out);
		out.close();
	}

	private static final String BOOTSTRAP_TAG = "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\" integrity=\"sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7\" crossorigin=\"anonymous\">";

	private void htmlHeader(PrintWriter out, LongAction iface,
			boolean autoRefresh) {
		htmlHeader(out, iface.getTitle(), autoRefresh);
	}

	private void htmlHeader(PrintWriter out, String title, boolean autoRefresh) {
		out.println("<!DOCTYPE html>");
		out.println("<html><head>");
		out.print(BOOTSTRAP_TAG);

		if (title != null) {
			out.print("<title>");
			out.print(title);
			out.println("</title>");
		}

		out.print("</head><body");
		if (autoRefresh) {
			out.print(" onload=\"setTimeout(function(){document.location.reload();}, 1000)\"");
		}
		out.println("><div class=\"container\">");

		if (title != null) {
			out.print("<h1>");
			out.print(title);
			out.println("</h1>");
		}
	}

	private void htmlFooter(PrintWriter out) {
		out.print("</div></body></html>");
	}

	private void progressBar(PrintWriter out, float progress) {
		String pcent = String.format("%d", progress * 100.0);
		out.print("<div class=\"progress\">"
				+ "<div class=\"progress-bar\" role=\"progressbar\" aria-valuenow=\"");
		out.print(pcent);
		out.print("\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: ");
		out.print(pcent);
		out.print("%;\"><span class=\"sr-only\">");
		out.print(pcent);
		out.print("%</span></div></div>");
	}
}
