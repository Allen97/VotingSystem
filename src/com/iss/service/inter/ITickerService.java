package com.iss.service.inter;

import java.util.List;
import java.util.Map;

public interface ITickerService {
	public List<Map<String, Object>> getTickerAll();
	int vote(int id);
}
