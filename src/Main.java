import java.net.*;

public class Main {
    public static void main(String[] args) throws Exception {
        int portNumber = 5000;
        if(args.length >= 1){
            portNumber = Integer.parseInt(args[0]);
        }

        MulticastSocket serverMulticastSocket = new MulticastSocket(portNumber);
        System.out.println("MulticastSocket is created at port " + portNumber);

        InetAddress group = InetAddress.getByName("225. 4. 5. 6");

        serverMulticastSocket.joinGroup(group);
        System.out.println("joinGroup method is called...");
        boolean infinite = true;

        while(infinite){
            byte buf[] = new byte[1024];
            DatagramPacket data = new DatagramPacket(buf, buf.length);

            serverMulticastSocket.receive(data);
            String msg = new String(data.getData()).trim();
            System.out.println("message recived from client = " + msg);
        }
        serverMulticastSocket.close();
    }
}