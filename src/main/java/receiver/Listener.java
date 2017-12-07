package receiver;


import javax.jms.*;


public class Listener implements MessageListener{


    
    public void onMessage(Message m) {     
        try{     
            if(m instanceof TextMessage){ //接收文本消息     
                TextMessage message = (TextMessage)m;     
                try {  
                    //输出接收到的消息  
                    System.out.println("HaHa: I'v got " + message.getText());  
                } catch (JMSException e) {  
                    e.printStackTrace();  
                }  
            }else if(m instanceof MapMessage){ //接收键值对消息     
                MapMessage message = (MapMessage)m;     
                System.out.println(message.getLong("age"));     
                System.out.println(message.getDouble("sarray"));     
                System.out.println(message.getString("username"));     
            }else if(m instanceof StreamMessage){ //接收流消息     
                StreamMessage message = (StreamMessage)m;     
                System.out.println(message.readString());     
                System.out.println(message.readLong());     
            }else if(m instanceof BytesMessage){ //接收字节消息     
                byte[] b = new byte[1024];     
                int len = -1;     
                BytesMessage message = (BytesMessage)m;     
                while((len=message.readBytes(b))!=-1){     
                    System.out.println(new String(b, 0, len));     
                }     
            }else{     
                System.out.println(m);     
            }     
                 
     
        }catch(JMSException e){     
         
            e.printStackTrace();     
        }     
    }     

}
