package com.cml.springbatch.sql;

public class Test {

	public static void main(String[] args) {
		// System.out.println(new QueryBuilder("table1", "alias").where(" 1=?
		// and 2=? ").buildSql());
		// System.out.println(new QueryBuilder("table1").where(" 1=? and 2=?
		// ").buildSql());
		String sql = new QueryBuilder("table1", "t1").innerJoin("table2", "t2", "t1.id=t2.id")
				.leftJoin("table3", "t3", "t3.id=t1.id").where("1=? and 2=?").groupBy("t3.name").having("count(t3)>1")
				.limit(100, 2).limit(2).buildSql(true);
		System.out.println(sql);
	}

}
