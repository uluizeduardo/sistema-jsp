package servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ServletLogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public ServletLogin() {
       
    }

    // Receive data via URL in parameters
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	// Receive the data sent via a form
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("idade"));
	}

}
