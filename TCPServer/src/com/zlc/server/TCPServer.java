package com.zlc.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class TCPServer {
	private static final int BUFSIZE = 1024;
	public static int servPort = 6666;//�������˿ں�
	public static int newVersion = 2;//Ĭ�ϰ汾��Ϊ2
	// 1.����һ��ServerSocketʵ�����ƶ����ض˿ڡ����׽��ֵĹ������������ƶ��˿��յ������ӡ�
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
				// �����½����Ŀͻ������ӣ�����һ��Socketʵ��������accept()��������
				clntSock = servSock.accept();
				SocketAddress clientAddress = clntSock.getRemoteSocketAddress();
				System.out.println("Handling client at " + clientAddress);
				// b,ʹ�������ص�Socketʵ����InputStream��OutputStream��ͻ��˽���ͨ��
				in = clntSock.getInputStream();
				out = clntSock.getOutputStream();
				String rev = "";
				recvMsgSize = in.read(receiveBuf);
				System.out.println(recvMsgSize);
				System.out.println(receiveBuf.length);
				rev = new String(receiveBuf, 0, recvMsgSize, "utf-8");
				System.out.println(rev);
				// c��ͨ����ɺ�ʹ��Socket��close()�����رոÿͻ����׽�������
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
