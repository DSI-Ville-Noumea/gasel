/**
 * 
 */
package nc.ccas.gasel.jwcs.core.search.critere;

import static nc.ccas.gasel.jwcs.core.search.critere.RawSqlNode.SPACE;

import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.exp.TraversalHandler;
import org.apache.cayenne.exp.parser.ASTDbPath;
import org.apache.cayenne.exp.parser.ASTPath;
import org.apache.cayenne.exp.parser.ExpressionParserTreeConstants;
import org.apache.cayenne.exp.parser.SimpleNode;

public class RawSqlExpr extends SimpleNode {
	private static final long serialVersionUID = -4378545068203714948L;

	private final ASTPath path;

	private final RawSqlNode sql;

	public RawSqlExpr(String path, String sql) {
		this(new ASTDbPath(path), sql);
	}

	public RawSqlExpr(ASTPath path, String sql) {
		super(ExpressionParserTreeConstants.JJTEQUAL);
		this.path = path;
		this.sql = new RawSqlNode(sql);
	}

	@Override
	public int getOperandCount() {
		return 3;
	}

	@Override
	public Object getOperand(int index) {
		switch (index) {
		case 0:
			return path;
		case 1:
			return SPACE;
		case 2:
			return sql;
		default:
			throw new ArrayIndexOutOfBoundsException(index);
		}
	}

	@Override
	protected void traverse(Expression parentExp, TraversalHandler visitor) {
		visitor.startNode(this, parentExp);

		path.traverse(visitor);
		visitor.finishedChild(this, 0, true);
		SPACE.traverse(visitor);
		visitor.finishedChild(this, 1, true);
		sql.traverse(visitor);
		visitor.finishedChild(this, 2, false);

		visitor.endNode(this, parentExp);
	}

	@Override
	public int getType() {
		return -1;
	}

	@Override
	protected Object evaluateNode(Object o) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	protected String getExpressionOperator(int index) {
		throw new UnsupportedOperationException();
		// return "";
	}

	@Override
	public Expression shallowCopy() {
		return new RawSqlExpr(path, sql.getSql());
	}

	@Override
	public String toString() {
		return "<RawSql: " + path + " " + sql + ">";
	}

}