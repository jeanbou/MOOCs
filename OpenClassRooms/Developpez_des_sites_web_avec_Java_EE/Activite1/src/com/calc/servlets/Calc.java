package com.calc.servlets;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Calc")
public class Calc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String regexDecimal = "^-?\\d*\\.\\d+$";
	private static final String regexInteger = "^-?\\d+$";
	private static final String decimalPattern = regexDecimal + "|" + regexInteger;
	
    public Calc() {
        super();        
    }

    private boolean validateOperation(int op_ind, String num_field1Str, String num_field2Str)
    {
    	boolean validRes = Pattern.matches(decimalPattern, num_field1Str) && Pattern.matches(decimalPattern, num_field2Str); 
    			
    	// Specific check of devision on zero that could occur
    	if (validRes && (op_ind == 4) ) // it is already a number
    	{
    		validRes = (Double.parseDouble(num_field2Str) != 0); // devision on zero case
    	}    	
    		
    	return validRes;
    }
    
    private Double calculateResult(int op_ind, double num1, double num2)
    {
    	double result = 0.0;
    	
    	switch (op_ind) {
        case 1:  result = num1+num2;
                 break;
        case 2:  result = num1-num2;
                 break;
        case 3:  result = num1*num2;
                 break;
        case 4:  result = num1/num2;
                 break;        
        }
    	
    	return result;
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get values
		String numField1Str = (String) request.getParameter("num_field1");
		String numField2Str = (String) request.getParameter("num_field2");
		// Interpretation of the empty field
		if(numField1Str == null || numField1Str.isEmpty())
		{
			numField1Str = "0.0";
		}
		if(numField2Str == null || numField2Str.isEmpty())
		{
			numField2Str = "0.0";
		}
		// Get operation index, there is no doubt it is integer, no extra check required, more in index.html
		int operation = Integer.parseInt( (String) request.getParameter("operation") );
				
		boolean isOperationValid = validateOperation(operation,numField1Str,numField2Str);
		
		String resultStr = "";
		if (isOperationValid)
		{
			double val1 = Double.parseDouble(numField1Str);
			double val2 = Double.parseDouble(numField2Str);
			Double res = calculateResult(operation,val1,val2);
			resultStr = res.toString();			
		}
		else
		{
			resultStr = "Error :: Please read carefully instruction below and act";
		} 
				
		request.setAttribute("result", resultStr);
		
		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
