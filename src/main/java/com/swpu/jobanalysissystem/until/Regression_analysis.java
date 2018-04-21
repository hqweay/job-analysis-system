package com.swpu.jobanalysissystem.until;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

public class Regression_analysis {
	//训练数据文件路径
	private final static String filePath = "/root/00000-0"; 
	private final static String server = "118.126.110.120"; 
	private final static String scriptPath = "/root/test.R"; 
	/**
	 * 给定参数做出回归分析
	 * @param xueli	学历
	 * @param place	地点
	 * @param exep	经验
	 * @param group	工作所在组id
	 * @param prop	工作性质
	 * @return 
	 * @throws REXPMismatchException 
	 */
	public static String getPredict(String xueli,String place, String exep, String group, String prop) throws REXPMismatchException{
		  // TODO Auto-generated method stub  
        RConnection connection = null;  
        try {  
            //创建对象  
            connection = new RConnection(server);  
           
        } catch (RserveException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        REXP rResponseObject = null;
        System.out.println("执行脚本");  
        try {  
        	connection.setStringEncoding("utf8");
        	connection.eval("source('"+scriptPath+"')");
            connection.assign("xueli",xueli);
            connection.assign("place", place);
            connection.assign("exep", exep);
            connection.assign("group", group);
            connection.assign("prop", prop);
            rResponseObject = connection.eval("try(myAnalysis(xueli,place,exep,group,prop))");
           if (rResponseObject.inherits("try-error")) { 
        	   System.out.println("R Serve Eval Exception :"+rResponseObject.asString()); 
          }
        }catch (RserveException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
          
	}
        return String.format("%.2f",Double.parseDouble(rResponseObject.asStrings()[1]))+"-"+String.format("%.2f",Double.parseDouble(rResponseObject.asStrings()[2]));
	}
	
	public static void main(String[] args) throws REXPMismatchException, REngineException {
		//test
		System.out.println(getPredict("高中", "成都", "1", "0", "民营公司"));
	}
}
