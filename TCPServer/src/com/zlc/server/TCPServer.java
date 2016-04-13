package com.zlc.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class TCPServer {
	private static final int BUFSIZE = 1024;
	public static int servPort = 6666;//服务器端口号
	public static int newVersion = 2;//默认版本号为2
	// 1.创建一个ServerSocket实例并制定本地端口。此套接字的功能是侦听该制定端口收到的连接。
	public static ServerSocket servSock;
	public static int recvMsgSize;
	public static byte[] receiveBuf = new byte[BUFSIZE];
	public static InputStream in;
	public static OutputStream out;
	public static Socket clntSock;
	
	public static void main(String[] args) {
		try {
			servSock  = new ServerSocket(servPort);
			while (true) {
				// 基于新建立的客户端连接，创建一个Socket实例，并由accept()方法返回
				clntSock = servSock.accept();
				SocketAddress clientAddress = clntSock.getRemoteSocketAddress();
				System.out.println("Handling client at " + clientAddress);
				// b,使用所返回的Socket实例的InputStream和OutputStream与客户端进行通信
				in = clntSock.getInputStream();
				out = clntSock.getOutputStream();
				String rev = "";
				recvMsgSize = in.read(receiveBuf);
				System.out.println(recvMsgSize);
				System.out.println(receiveBuf.length);
				rev = new String(receiveBuf, 0, recvMsgSize, "utf-8");
				System.out.println(rev);
				// c，通信完成后，使用Socket的close()方法关闭该客户端套接字链接
				back2Client();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void back2Client(){
		String msg;
			msg = new String("Hello Client");
		try {
			System.out.println("compateVersion is in");
			out.write(msg.getBytes());
			System.out.println("compateVersion is out");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("here is a error");
			e.printStackTrace();
		}finally{
			try {
				in.close();
				out.close();
				clntSock.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			in = null;
			out = null;
			clntSock = null;
		}
}
}
