package com.cml.springbatch.sql;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class QueryBuilder {
	private static final String SQL_MARKER = " ";
	private String[] selection;
	private String tableName;
	private String where;
	private String groupBy;
	private String having;
	private String orderBy;
	private String limit;
	private SortedMap<String, String> aliasMap = new TreeMap<String, String>();
	private List<JoinEntity> joinQuene = new ArrayList<QueryBuilder.JoinEntity>();

	public QueryBuilder(String tableName) {
		super();
		this.tableName = tableName;
	}

	public QueryBuilder(String tableName, String alias) {
		super();
		this.tableName = tableName;
		aliasMap.put(tableName, alias);
	}

	public QueryBuilder where(String where) {
		this.where = where;
		return this;
	}

	public QueryBuilder innerJoin(String table, String alias, String on) {
		joinQuene.add(new JoinEntity("INNER JOIN", table, alias, on));
		return this;
	}

	public QueryBuilder leftJoin(String table, String alias, String on) {
		joinQuene.add(new JoinEntity("LEFT JOIN", table, alias, on));
		return this;
	}

	public QueryBuilder rightJoin(String table, String alias, String on) {
		joinQuene.add(new JoinEntity("RIGHT JOIN", table, alias, on));
		return this;
	}

	public QueryBuilder groupBy(String groupBy) {
		this.groupBy = groupBy;
		return this;
	}

	public QueryBuilder orderBy(String orderBy) {
		this.orderBy = orderBy;
		return this;
	}

	public QueryBuilder having(String having) {
		this.having = having;
		return this;
	}

	public QueryBuilder limit(int limitSize) {
		this.limit = String.valueOf(limitSize);
		return this;
	}

	public QueryBuilder limit(int limitSize, int offset) {
		this.limit = limitSize + "," + offset;
		return this;
	}

	public final String buildSql() {
		return this.buildSql(false);
	}

	public final String buildSql(boolean isFormat) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("SELECT");

		// build selection
		buildSelection(sqlBuffer);

		// build from table
		sqlBuffer.append(SQL_MARKER);
		sqlBuffer.append("FROM");
		sqlBuffer.append(SQL_MARKER);
		sqlBuffer.append(tableName);
		if (aliasMap.containsKey(tableName)) {
			sqlBuffer.append(SQL_MARKER).append("AS").append(SQL_MARKER).append(aliasMap.get(tableName));
		}

		// build join
		if (joinQuene.size() > 0) {
			Iterator<JoinEntity> entity = joinQuene.iterator();
			while (entity.hasNext()) {
				JoinEntity entry = entity.next();
				format(sqlBuffer, isFormat);
				sqlBuffer.append(entry.buildSql(isFormat));
			}
		}

		// build where
		if (null != where) {
			format(sqlBuffer, isFormat);
			sqlBuffer.append("WHERE");
			sqlBuffer.append(SQL_MARKER).append(where);
		}

		if (StringUtils.isNotBlank(groupBy)) {
			format(sqlBuffer, isFormat);
			sqlBuffer.append("GROUP BY").append(SQL_MARKER).append(groupBy);
			if (StringUtils.isNotBlank(having)) {
				sqlBuffer.append(SQL_MARKER).append("HAVING").append(SQL_MARKER).append(having);
			}
		}

		if (StringUtils.isNotBlank(orderBy)) {
			format(sqlBuffer, isFormat);
			sqlBuffer.append("ORDER BY").append(groupBy);
		}
		if (StringUtils.isNotBlank(limit)) {
			format(sqlBuffer, isFormat);
			sqlBuffer.append("LIMIT").append(SQL_MARKER).append(limit);
		}

		return sqlBuffer.toString();
	}

	private static void format(StringBuffer sqlBuffer, boolean format) {
		if (format) {
			sqlBuffer.append("\n");
		} else {
			sqlBuffer.append(SQL_MARKER);
		}
	}

	private void buildSelection(StringBuffer sqlBuffer) {
		sqlBuffer.append(SQL_MARKER);
		if (null == selection) {
			sqlBuffer.append("*");
		} else {
			int selectLen = selection.length;
			for (int i = 0; i < selectLen; i++) {
				sqlBuffer.append(selection[i]);
				if (i != selectLen - 1) {
					sqlBuffer.append(",");
				}
			}
		}
	}

	private static class JoinEntity {
		public String prefix;
		public String table;
		public String alias;
		public String on;

		public JoinEntity(String prefix, String table, String alias, String on) {
			super();
			this.prefix = prefix;
			this.table = table;
			this.alias = alias;
			this.on = on;
		}

		public String buildSql(boolean format) {
			StringBuffer sqlBuilder = new StringBuffer();
			sqlBuilder.append(prefix);
			sqlBuilder.append(SQL_MARKER).append(table);
			if (StringUtils.isNotBlank(alias)) {
				sqlBuilder.append(SQL_MARKER).append("AS").append(SQL_MARKER).append(alias);
			}
			if (StringUtils.isNotBlank(on)) {
				format(sqlBuilder, format);
				sqlBuilder.append("ON").append(SQL_MARKER).append(on);
			}
			return sqlBuilder.toString();
		}

	}
}
