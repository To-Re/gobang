package server;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import client.Config;

/**
 * Created by Administrator on 2018/5/3.
 */
public class ServerThread extends Thread {

    private Socket socket;
    private Map<String, Socket> map = new HashMap<String, Socket>();
    private Map<String, String> oppo = new HashMap<String, String>();
    private Stack<String> stack = new Stack<String>();
//    private int count;
    String who[] = new String[2];
    int cur = 0;
    // 棋盘信息
    public int[][] exist = new int[Config.ROWS][Config.COLUMNS];
	public String order, usrname;
	public int tot;
	
    public ServerThread(Socket socket, Map<String, Socket> wtf, Map<String, String> wtf2, Stack<String> wtf3, int wtf4) {
        this.socket = socket;
        this.map = wtf;
        this.oppo = wtf2;
        this.stack = wtf3;
//        this.count = wtf4;
    }

    @Override
    public void run() {
    	System.out.println("!!!!!!");
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        OutputStream outputStream = null;
        PrintWriter printWriter = null;

        try {
            // server接收消息
            inputStream = socket.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String str = Commun.receive(socket);
            if (str != null) {
                System.out.println("I am Server, now get message from Client: " + str);
                usrname = str;
                stack.push(str);
                map.put(str,socket);
            }
            if(stack.size() >= 2) {
            	who[0] = stack.peek();
            	stack.pop();
            	who[1] = stack.peek();
            	stack.pop();
            	oppo.put(who[0], who[1]);
            	oppo.put(who[1], who[0]);
            	// server发送消息
            	Commun.send(map.get(who[0]), "1");
            	Commun.send(map.get(who[1]), "2");
            	// 开始下棋
            	cur = 0;
            	order = "";
        		tot = 0;
        		for(int i = 0; i < Config.ROWS; ++i) {
        			for(int j = 0; j < Config.COLUMNS; ++j) exist[i][j] = 0;
        		}
            	while(true) {
            		try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		System.out.println(who[cur] + "   " + "1");
            		Commun.send(map.get(who[cur]), "1"); // 1 表示落子
            		String flag = Commun.receive(map.get(who[cur])); // 接受 1 表示已落子 1xy
            		System.out.println(flag + "flag ???");
            		if(flag.substring(0,1).equals("1")) {
            			int x = flag.charAt(1)-'a';
            			int y = flag.charAt(2)-'a';
            			System.out.println(x + "####" + y);
            			if(x < 0 || x >= 15 || y < 0 || y >= 15 || exist[x][y] != 0) continue;
            			exist[x][y] = cur+1;
            			Commun.send(map.get(who[0]), "4"); // 4xyc 表示落子 x,y color
                    	try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
            			Commun.send(map.get(who[0]), flag.substring(1,3)+String.valueOf((char)(cur+1))); // 4xyc 表示落子 x,y color
            			
            			Commun.send(map.get(who[1]), "4");
            			try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
            			Commun.send(map.get(who[1]), flag.substring(1,3)+String.valueOf((char)(cur+1)));
            			
            			System.out.println("服务器落子"+flag.substring(1,3)+String.valueOf((char)(cur+1)));
            			order += flag.substring(1,3);
            			if(win(cur+1)) {
            				try {
    							Thread.sleep(100);
    						} catch (InterruptedException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						}
            				Commun.send(map.get(who[cur]), "2"); // 2 win
                			Commun.send(map.get(who[cur^1]), "3"); // 3 lose
                			break;
            			}
            		}
            		else if(flag.substring(0,1).equals("2")) { // 投降
            			try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
            			Commun.send(map.get(who[cur^1]), "2"); // 2 win
            			Commun.send(map.get(who[cur]), "3"); // 3 lose
            			break;
            		}
            		cur ^= 1;
            	}
            }
            while(map.containsKey(usrname) == true) {
//            	if(oppo.containsKey(usrname)) {
//            		map.remove(usrname);
//            		oppo.remove(usrname);
//            	}
        	}
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (printWriter != null) {
                    printWriter.close();
                }
                if (outputStream != null) {
                    outputStream.close();

                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (socket != null) {
                	System.out.println("关闭");
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public Boolean win(int color) { // 判断当前颜色能否赢 
    	for(int i = 0; i < 15; ++i) {
    		int tot = 0;
    		for(int j = 0; j < 15; ++j) {
    			if(exist[i][j] == color) ++tot;
    			else tot = 0;
    			if(tot >= 5) return true;
    		}
    	}
    	for(int i = 0; i < 15; ++i) {
    		int tot = 0;
    		for(int j = 0; j < 15; ++j) {
    			if(exist[j][i] == color) ++tot;
    			else tot = 0;
    			if(tot >= 5) return true;
    		}
    	}
    	for(int i = 0; i < 15; ++i) {
    		for(int j = 0; j < 15; ++j) {
    			int tot = 0;
    			for(int q = 0; q < 5; ++q) {
    				if(i+q >= 15 || j+q >= 15) break;
    				if(exist[i+q][j+q] == color) ++tot;
    			}
    			if(tot == 5) return true;
    			tot = 0;
    			for(int q = 0; q < 5; ++q) {
    				if(i+q >= 15 || j-q < 0) break;
    				if(exist[i+q][j-q] == color) ++tot;
    			}
    			if(tot == 5) return true;
    		}
    	}
    	return false;
    }
}