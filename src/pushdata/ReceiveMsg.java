package pushdata;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.ProjectData;
import encryption.aes.AESEncryptApi;
import model.CommonData;
import net.sf.json.JSONObject;

/*
 * servlet是单例多线程模式，所以在访问公用资源时要考虑线程安全问题
 */
public class ReceiveMsg extends HttpServlet {

	//建立一个静态代码块，在servlet被初始化的时候,
	//新建线程也可以用一个servletListener实现，一般不要在servlet中新开一个线程来完成工作
//	static// 新开一个线程，实现队列中数据的取出
//	{
//		
//	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	
	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// basic setting
		boolean savedsuccessfully = false;
		String resultofPost = "ac";
		String resultofExcep = "ero";
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// 首先得到输入流
		InputStream inputStream = request.getInputStream();
		int totalbytes = request.getContentLength();// 内容的长度

		// 用字符流来读取数据
		/*
		 * BufferedReader reader = new BufferedReader(new
		 * InputStreamReader(inputStream)); StringBuilder sb = new
		 * StringBuilder(); String line = null; while ((line =
		 * reader.readLine()) != null) { sb.append(line + "\n"); }
		 * inputStream.close(); System.out.println(sb.toString());
		 */

		// 用字节流来读取数据
		
		int length = 0;
		int hadReceivedCount=0; //已经读取的数据的长度
		String jsonString = null;
		byte[] buf = new byte[totalbytes];
		while ((length = inputStream.read(buf,hadReceivedCount, totalbytes-hadReceivedCount )) != -1) { // 当输入流中没有数据时，会返回-1，此时终止程序的执行			
			hadReceivedCount+=length;
		}
		//数据解密
//		byte[] srcData =AESEncryptApi.verEncryptionFuntion(buf);
//		jsonString = new String(srcData, 0, hadReceivedCount, "utf-8"); // 用得到的缓冲数组重新构建字符串并输出到控制台
		jsonString = new String(buf, 0, hadReceivedCount, "utf-8"); // 用得到的缓冲数组重新构建字符串并输出到控制台
		
		inputStream.close();
		System.out.println();System.out.println();
		System.out.println(".................................服务器收到数据................................."); 
//		System.out.println(jsonString);//输出得到的字符串
		// 处理字符串
		// basic data processing
		if ((jsonString != null) && jsonString.startsWith("data="))//如何符合规则才进行下一步的处理  
		{
			jsonString = jsonString.substring(5);	
			System.out.println("收到字符串：" + jsonString);//输出得到的json字符串
			
			//将字符串转化为数据对象模型
			CommonData cd = parseCommonDataFromJson(jsonString);//有问题,这个是线程安全的吗？
			if ((cd != null) && (cd.getDt() != null)) //如果得到的数据不为空则可以进行处理，表明这是正确有效的数据
			{
				try {
					savedsuccessfully=ProjectData.offerCommonDataToQueue(cd);					
				} catch (Exception e) {
					e.printStackTrace();
				}								
			}
			//如果cd == null，则不用进行后续的处理，因为这个数据不是我们需要的
		}
		//需要返回数据
		OutputStream outputStream = response.getOutputStream();
		// DataPushService.pushToSql(jsonString)
		if (savedsuccessfully) {
			outputStream.write(resultofPost.getBytes());
			System.out.println("数据已存入缓存"); 					
		}else {
			outputStream.write(resultofExcep.getBytes());
		}
		outputStream.close();//关闭输出流		
	}
		
	/**
	 * 将json字符串转化为数据对象，这里面的代码不一定是线程安全的
	 * @param jstr
	 * @return
	 */
	//这里面的代码不一定是线程安全的
	private CommonData parseCommonDataFromJson(String jstr){
		CommonData cd = null;
		try{
			JSONObject jobject = JSONObject.fromObject(jstr);
			cd = (CommonData)JSONObject.toBean(jobject, CommonData.class);
		}catch(Exception e){
			cd = null;
			e.printStackTrace();
		}
		return cd;
	}
}






