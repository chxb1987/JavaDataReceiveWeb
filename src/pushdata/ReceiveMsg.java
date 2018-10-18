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
 * servlet�ǵ������߳�ģʽ�������ڷ��ʹ�����ԴʱҪ�����̰߳�ȫ����
 */
public class ReceiveMsg extends HttpServlet {

	//����һ����̬����飬��servlet����ʼ����ʱ��,
	//�½��߳�Ҳ������һ��servletListenerʵ�֣�һ�㲻Ҫ��servlet���¿�һ���߳�����ɹ���
//	static// �¿�һ���̣߳�ʵ�ֶ��������ݵ�ȡ��
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

		// ���ȵõ�������
		InputStream inputStream = request.getInputStream();
		int totalbytes = request.getContentLength();// ���ݵĳ���

		// ���ַ�������ȡ����
		/*
		 * BufferedReader reader = new BufferedReader(new
		 * InputStreamReader(inputStream)); StringBuilder sb = new
		 * StringBuilder(); String line = null; while ((line =
		 * reader.readLine()) != null) { sb.append(line + "\n"); }
		 * inputStream.close(); System.out.println(sb.toString());
		 */

		// ���ֽ�������ȡ����
		
		int length = 0;
		int hadReceivedCount=0; //�Ѿ���ȡ�����ݵĳ���
		String jsonString = null;
		byte[] buf = new byte[totalbytes];
		while ((length = inputStream.read(buf,hadReceivedCount, totalbytes-hadReceivedCount )) != -1) { // ����������û������ʱ���᷵��-1����ʱ��ֹ�����ִ��			
			hadReceivedCount+=length;
		}
		//���ݽ���
//		byte[] srcData =AESEncryptApi.verEncryptionFuntion(buf);
//		jsonString = new String(srcData, 0, hadReceivedCount, "utf-8"); // �õõ��Ļ����������¹����ַ��������������̨
		jsonString = new String(buf, 0, hadReceivedCount, "utf-8"); // �õõ��Ļ����������¹����ַ��������������̨
		
		inputStream.close();
		System.out.println();System.out.println();
		System.out.println(".................................�������յ�����................................."); 
//		System.out.println(jsonString);//����õ����ַ���
		// �����ַ���
		// basic data processing
		if ((jsonString != null) && jsonString.startsWith("data="))//��η��Ϲ���Ž�����һ���Ĵ���  
		{
			jsonString = jsonString.substring(5);	
			System.out.println("�յ��ַ�����" + jsonString);//����õ���json�ַ���
			
			//���ַ���ת��Ϊ���ݶ���ģ��
			CommonData cd = parseCommonDataFromJson(jsonString);//������,������̰߳�ȫ����
			if ((cd != null) && (cd.getDt() != null)) //����õ������ݲ�Ϊ������Խ��д�������������ȷ��Ч������
			{
				try {
					savedsuccessfully=ProjectData.offerCommonDataToQueue(cd);					
				} catch (Exception e) {
					e.printStackTrace();
				}								
			}
			//���cd == null�����ý��к����Ĵ�����Ϊ������ݲ���������Ҫ��
		}
		//��Ҫ��������
		OutputStream outputStream = response.getOutputStream();
		// DataPushService.pushToSql(jsonString)
		if (savedsuccessfully) {
			outputStream.write(resultofPost.getBytes());
			System.out.println("�����Ѵ��뻺��"); 					
		}else {
			outputStream.write(resultofExcep.getBytes());
		}
		outputStream.close();//�ر������		
	}
		
	/**
	 * ��json�ַ���ת��Ϊ���ݶ���������Ĵ��벻һ�����̰߳�ȫ��
	 * @param jstr
	 * @return
	 */
	//������Ĵ��벻һ�����̰߳�ȫ��
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






