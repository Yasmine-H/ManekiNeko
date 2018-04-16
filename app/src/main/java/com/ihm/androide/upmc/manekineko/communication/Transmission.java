package com.ihm.androide.upmc.manekineko.communication;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 
 */

public class Transmission extends Thread {
    public static final String MESSAGE_RECEIVED="com.domotask.test.robotmobilecdta.Transmissions" ;
    Socket socket;
    private AppCompatActivity activity;
    private boolean active=false;
    private int port;
    private String adresse;
    private String msgOut;
    String msgIn="";
    private boolean connected;
    Context context;

    public Transmission(final Context context,AppCompatActivity pActivity,String pAdresse, int pPort, String pMsgOut){
        this.adresse=pAdresse;
        this.activity=pActivity;
        this.context = context;
        msgOut=pMsgOut;
        port=pPort;
        connected=false;

    }


    @Override
    public void run() {
        try {

            Log.d("Transmissions", "run() start");

            IntentFilter filter = new IntentFilter();

            //filter.addAction(RandomActivity.RANDOM_BROADCAST);
            // LocalBroadcastManager.getInstance(context).registerReceiver(broadcastReceiver, filter);

            socket = new Socket (adresse,port);
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                }
            });

            connected=true;
            // Le reste du temps, le robot fera une action si un msg est envoyé
            Log.d("Transmissions", "run() : connected");
            while (connected){
                String answer=messageIn();

            }
        } catch (IOException e) {

            e.printStackTrace();
            //LocalBroadcastManager.getInstance(context).unregisterReceiver(broadcastReceiver);

            affichage("Network problem. Please check your WiFi and reload the app",activity);
            connected=false;

            sendMsgToActivities("disconnected");

        }
        Log.d("Transmissions", "Transmission achevée");

    }


    public void setMsg(String msg1){
        this.msgOut=msg1;
    }

    public void socketClose(){

        if(connected) {
            messageOut("stop,task");
        }
        else
        {
            Log.d("Transmissions", "SocketClose() socket already closed");
        }
    }

    public void socketFree(){

        Log.d("Transmissions", "socketFree received!!!");
        if(connected)
        {
            Log.d("Transmissions", "socketFree and connected");

            connected=false;
        }
        else
        {
            Log.d("Transmissions", "SocketFree() socket already closed");
        }

    }


    // Send message
    public void messageOut(String pmsgOut){

        if(connected)
        {
            try {
                DataOutputStream out= new DataOutputStream(socket.getOutputStream());
                out.writeUTF(getIpAddress()+","+pmsgOut);
                Log.d("Transmission","Msg envoyé: "+pmsgOut);//+" Adresse IP via inet"+socket.getInetAddress().getHostAddress()+" adresse IP via methode rahim"+getIpAddress());

                out.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            Log.d("Transmissions", "messageOut socket already closed");
        }

    }

    private String getIpAddress() {
        String ip = "";
        try {
            Enumeration<NetworkInterface> enumNetworkInterfaces = NetworkInterface
                    .getNetworkInterfaces();
            while (enumNetworkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = enumNetworkInterfaces
                        .nextElement();
                Enumeration<InetAddress> enumInetAddress = networkInterface
                        .getInetAddresses();
                while (enumInetAddress.hasMoreElements()) {
                    InetAddress inetAddress = enumInetAddress.nextElement();

                    if (inetAddress.isSiteLocalAddress()) {
                        ip =inetAddress.getHostAddress();
                       /* ip += "SiteLocalAddress: "
                                + inetAddress.getHostAddress() + "\n";
                    */}

                }

            }

        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            ip += "Something Wrong! " + e.toString() + "\n";
        }

        return ip;
    }



    // read messages
    public String messageIn(){

        msgIn="";
        if(connected)
        {
            try {

                DataInputStream inputStream = new DataInputStream(socket.getInputStream());

                if(inputStream.available()>0)
                {
                    msgIn=inputStream.readUTF();

                    Log.d("Transmission,messageIn","!!!!!!Msg recu: "+ msgIn + " " + msgOut);

                    if(msgIn.contains("active"))
                    {
                        active=true;


                    }
                    sendMsgToActivities(msgIn);
                }

            } catch (IOException e) {
                e.printStackTrace();
                Log.d("Transmissions","msgIn : "+msgIn);
                connected=false;
                socketFree();
                sendMsgToActivities("shutdown");
            }
        }

        else
        {
            Log.d("Transmissions", "messageIn() socket already closed");
        }

        return msgIn;
    }

    public void sendMsgToActivities(String msg)
    {
        Intent intent = new Intent();
        intent.setAction(MESSAGE_RECEIVED);
        intent.putExtra("message",msg);
        //LocalBroadcastManager.getInstance(activity.getBaseContext()).sendBroadcast(intent);
    }


    public void affichage(final String msgAffiche, final AppCompatActivity param2){
        param2.runOnUiThread(new Runnable() {
            public void run() {
                //Toast.makeText(param2, msgAffiche, Toast.LENGTH_LONG).show();

            }
        });
    }
}
