package com.iss.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iss.service.TickerService;

/**
 * Servlet implementation class FindTickerServlet
 */
@WebServlet("/FindTickerServlet")
public class FindTickerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * Default constructor. 
     */
    public FindTickerServlet() {
    	super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TickerService tickerService =new TickerService();
		List<Map<String, Object>> tickerAll=tickerService.getTickerAll();
		HashMap<Object, Object> hashMap=new HashMap<Object,Object>();
		hashMap.put("tickerAll", tickerAll);
		HttpSession session = request.getSession();
		session.setAttribute("hashMap", hashMap);
		response.sendRedirect("index.jsp");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		TickerService tickerService=new TickerService();
		boolean temp=false;
		int i=tickerService.vote(Integer.parseInt(id));
		if(i>0)
			temp =true;
		PrintWriter out =response.getWriter();
		out.print(temp);
		out.flush();
		out.close();
	}

}
