package com.iss.service;

import java.util.List;
import java.util.Map;

import com.iss.dao.TickerDao;
import com.iss.service.inter.ITickerService;

public class TickerService implements ITickerService {

	public List<Map<String, Object>> getTickerAll() {
		// TODO Auto-generated method stub
		TickerDao tickerDao = new TickerDao();
		
		return tickerDao.getTickerAll();
	}
	
	@Override
	public int vote(int id) {
		TickerDao tickerDao = new TickerDao();
		return tickerDao.vote(id);
	}

}
