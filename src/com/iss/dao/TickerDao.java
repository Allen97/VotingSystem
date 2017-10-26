package com.iss.dao;

import java.util.List;
import java.util.Map;

import com.iss.dao.inter.ITickerDao;
import com.iss.dbutils.DBUtils;

public class TickerDao implements ITickerDao {
	DBUtils dbutils =new DBUtils();
	@Override
	public List<Map<String, Object>> getTickerAll() {
		// TODO Auto-generated method stub
		String sql="select * from tb_ticker";
		return dbutils.query(sql);
	}
	
	public int vote(int id) {
		String sql= "updata tb_ticker set number=number+1 where id=?";
		return dbutils.update(sql, id);
	}
}
