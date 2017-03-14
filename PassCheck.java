package SIH;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Servlet implementation class PassCheck
 */
@WebServlet("/PassCheck")
public class PassCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PassCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder sb = new StringBuilder();
	    try 
	    {
	      BufferedReader reader = request.getReader();
	      String line = null;
	      while ((line = reader.readLine()) != null)
	      { sb.append(line);
	      }
	    } catch (Exception e) { e.printStackTrace(); }
	
	    JSONParser parser = new JSONParser();
	    JSONObject joUser = null;
        String username="";
        String password="";
        try
	    {
	    	//creating JSONObject out of JSON formatted String
	      joUser = (JSONObject) parser.parse(sb.toString());
	      username= joUser.get("username").toString();
	      password=joUser.get("password").toString();

	    } catch (Exception e) { e.printStackTrace(); }

		
	PassFunc pf = new PassFunc();
	pf.getDocument(username);
	boolean res=pf.checkPass(password);
	PrintWriter out = response.getWriter();
    out.println(res);
	}

}
