package com.zlc.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {
	public static int servPort = 6666;// ·þÎñÆ÷¶Ë¿ÚºÅ
	private static final int BUFSIZE = 1024;
	public static byte[] receiveBuf = new byte[1024*20];
	public static InputStream in;
	public static OutputStream out;
	public static Socket clntSock;
	public static int recvMsgSize;

	public static void main(String[] args) {
		try {
			clntSock = new Socket("127.0.0.1", servPort);
			in = clntSock.getInputStream();
			out = clntSock.getOutputStream();
			String str = "Hello server";
			out.write(str.getBytes(), 0, str.length());
			System.out.println("################");
			while((recvMsgSize = in.read(receiveBuf, 0, BUFSIZE)) != -1){
				System.out.println(recvMsgSize);
				System.out.println(new String(receiveBuf,0,recvMsgSize));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
