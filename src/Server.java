import java.io.*;
import java.net.*;

public class Server {
	ServerSocket svrsoc=null;
	
	public Server() throws IOException, InterruptedException
	{
		init();
	}
	
	void init() throws IOException, InterruptedException
	{		
		svrsoc = new ServerSocket(12345);
		System.out.println("Server Started");
		
		Thread mythread=new Thread()
		{
			public void run()
			{
				while(true)
				{
					Socket soc=null;
				
					try {
						soc=svrsoc.accept();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(soc);
					
					try {
						AcceptClient obClient=new AcceptClient(soc);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		
		mythread.start();	
		
	}
	
	public static void main(String args[]) throws IOException, InterruptedException
	{
		Server s=new Server();
	}
}

class AcceptClient extends Thread {
	
	Socket Clientsocket;
	
	AcceptClient(Socket soc) throws IOException, InterruptedException {
		
		Clientsocket=soc;
		init();
	}
	
	void init() throws IOException, InterruptedException
	{
		// code starting point
		

		InputStream in=Clientsocket.getInputStream();
		OutputStream out=Clientsocket.getOutputStream();
		
		DataInputStream din=new DataInputStream(in);
		DataOutputStream dout=new DataOutputStream(out);
		
		String code,ques,user,filecpp,filetxt,filext;
		
		
		user=din.readUTF();
		ques=din.readUTF();
		code=din.readUTF();
		System.out.println(ques);
		System.out.println(code);
		
		filext=user.concat(ques);
		filetxt=filext.concat(".txt");
		filecpp=filext.concat(".cpp");
	
		
		System.out.println("cpp: " + filecpp);
		System.out.println("txt: " + filetxt);
		System.out.println("ext: " + filext);
		
		
		
			File file=new File("/home/vivek/workspace1/OnlineJudge/"+filecpp);
			FileOutputStream filewrite=new FileOutputStream(file);
			filewrite.write(code.getBytes());
			filewrite.close();
			
			System.out.println("process start");
			
			//source output executable name
			
			long start,end = 0;
			
			Process cmdp1;
			
			if(ques.equals("1"))
			{
				String cmd;
				cmd="python sandbox1.py"+" "+filecpp+" "+filetxt+" "+filext;
				System.out.println("cmd_1: "+ cmd);
				
				cmdp1=Runtime.getRuntime().exec(cmd);
				
				start = System.currentTimeMillis();
				
				cmdp1.waitFor();
				
				end = System.currentTimeMillis();
			}
			
			else if(ques.equals("2"))
			{
				String cmd;
				cmd="python sandbox2.py"+" "+filecpp+" "+filetxt+" "+filext;
				System.out.println("cmd_2: "+ cmd);
				
				cmdp1=Runtime.getRuntime().exec(cmd);
				
				start = System.currentTimeMillis();
				
				cmdp1.waitFor();
				
				end = System.currentTimeMillis();
			}
			else
			{
				String cmd;
				cmd="python sandbox3.py"+" "+filecpp+" "+filetxt+" "+filext;
				System.out.println("cmd_3: "+ cmd);
				
				cmdp1=Runtime.getRuntime().exec(cmd);
				
				start = System.currentTimeMillis();
				
				cmdp1.waitFor();
				
				end = System.currentTimeMillis();
			}
		
			long time=(end - start) / 1000;

			//System.out.println("Took : " + ((end - start) / 1000));
			
			StringBuffer output = new StringBuffer();
			
			if(time<=1)
			{
				//start code
				
				System.out.println("process completed");
				
				
				 
				BufferedReader stdoutReader = new BufferedReader(
				         new InputStreamReader(cmdp1.getInputStream()));
				String line;
				while ((line = stdoutReader.readLine()) != null) {
					output.append(line + "\n");
				}
				
				output.toString();
				
				System.out.println(output);
				
				//end code
			}
			else
			{
				System.out.println("Got TLE: "+ time);
				
				output.append("Got TLE" + "\n");
			}
			
			String str=output.toString();

			dout.writeUTF(str);
			
			
			/* delete the files which was created by client.*/
			
			String del;
			del="./del.sh"+" "+filecpp+" "+filetxt+" "+filext;
			Process cmdp=Runtime.getRuntime().exec(del);
			cmdp.waitFor();

			Clientsocket.close();
			
		//code end point
	}
	
}


