package com.company;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        // write your code here
        String fileName = "/Users/andreibudin/Documents/По учёбе/Тестовое задание/Тестовое задание - Java/testlog.log/";
        String string;
        Pattern p_entry = Pattern.compile("entry with");
        Pattern p_exit = Pattern.compile("exit with");

        Map<String,String> entryMap = new HashMap<>();
        Map<String,Long> exitMap = new HashMap<>();
        Map<String,Map<String,Long>> methods = new HashMap<String,Map<String,Long>>();
        List<Long> process_Client = new ArrayList<>();
        List<Long> process_Action = new ArrayList<>();
        List<Long> get_Data = new ArrayList<>();
        List<Long> get_Actions = new ArrayList<>();
        List<Long> accept_Token = new ArrayList<>();
        List<Long> check_Auth = new ArrayList<>();
        List<Long> add_Client = new ArrayList<>();


        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            //for (int i = 0; i < 60000; i++) {
            while((string =reader.readLine()) != null ){
                Matcher m1 = p_entry.matcher(string);
                Matcher m2 = p_exit.matcher(string);

                if(m1.find()){
                   entryMap.put(MyRegExp.getMethodNameID(string), MyRegExp.getTimes(string));
                }
                if(m2.find()){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss,SSS");
                    //Date date1 = dateFormat.parse(MyRegExp.getTimes(string));
                    Date date1 = dateFormat.parse(MyRegExp.getTimes(string));
                    Date date2 = dateFormat.parse(entryMap.get(MyRegExp.getMethodNameID(string)));
                    long diff = date1.getTime() - date2.getTime();
                    exitMap.put(MyRegExp.getMethodNameID(string), diff);

                    Matcher m_processClient = M_Names.processClient.matcher(string);
                    Matcher m_processAction = M_Names.processAction.matcher(string);
                    Matcher m_getData = M_Names.getData.matcher(string);
                    Matcher m_getActions = M_Names.getActions.matcher(string);
                    Matcher m_acceptToken = M_Names.acceptToken.matcher(string);
                    Matcher m_checkAuth = M_Names.checkAuth.matcher(string);
                    Matcher m_addClient = M_Names.addClient.matcher(string);

                        if(m_processClient.find()) process_Client.add(diff);
                        if(m_processAction.find()) process_Action.add(diff);
                        if(m_getData.find()) get_Data.add(diff);
                        if(m_getActions.find()) get_Actions.add(diff);
                        if(m_acceptToken.find()) accept_Token.add(diff);
                        if(m_checkAuth.find()) check_Auth.add(diff);
                        if(m_addClient.find()) add_Client.add(diff);
                }

            }
            /*for (Map.Entry<String, String> entry : entryMap.entrySet()) {
                System.out.println("Ключ:  " + entry.getKey() + " Значение: " + entry.getValue());
            }*/
            //for (Map.Entry<String, Long> entry : exitMap.entrySet()) {
             //   System.out.println("Ключ:  " + entry.getKey() + " Значение: " + entry.getValue());
            //}
            Collections.sort(process_Client);
            Collections.sort(process_Action);
            Collections.sort(get_Data);
            Collections.sort(get_Actions);
            Collections.sort(accept_Token);
            Collections.sort(check_Auth);
            Collections.sort(add_Client);


            Map<String, Long> pC = new HashMap<>();
            pC.put("max", process_Client.get(process_Client.size()-1));
            pC.put("min", process_Client.get(0));
            pC.put("avg", avgColl((ArrayList<Long>) process_Client));
            pC.put("count", (long) process_Client.size());

            methods.put("processClient", pC);

            Map<String,Long> pA = new HashMap<>();
            pA.put("max", process_Action.get(process_Action.size()-1));
            pA.put("min", process_Action.get(0));
            pA.put("avg", avgColl((ArrayList<Long>) process_Action));
            pA.put("count", (long) process_Action.size());

            methods.put("processAction", pA);

            Map<String,Long> gD = new HashMap<>();
            gD.put("max", get_Data.get(get_Data.size()-1));
            gD.put("min", get_Data.get(0));
            gD.put("avg", avgColl((ArrayList<Long>) get_Data));
            gD.put("count", (long) get_Data.size());

            methods.put("getData", gD);

            Map<String,Long> gA = new HashMap<>();
            gA.put("max", get_Actions.get(get_Actions.size()-1));
            gA.put("min", get_Actions.get(0));
            gA.put("avg", avgColl((ArrayList<Long>) get_Actions));
            gA.put("count", (long) get_Data.size());

            methods.put("getActions", gA);

            Map<String,Long> aT = new HashMap<>();
            aT.put("max", accept_Token.get(accept_Token.size()-1));
            aT.put("min", accept_Token.get(0));
            aT.put("avg", avgColl((ArrayList<Long>) accept_Token));
            aT.put("count", (long) accept_Token.size());

            methods.put("acceptToken", aT);

            Map<String,Long> cA = new HashMap<>();
            cA.put("max", check_Auth.get(check_Auth.size()-1));
            cA.put("min", check_Auth.get(0));
            cA.put("avg", avgColl((ArrayList<Long>) check_Auth));
            cA.put("count", (long) check_Auth.size());

            methods.put("checkAuth", cA);

            Map<String,Long> aC = new HashMap<>();
            aC.put("max", add_Client.get(add_Client.size()-1));
            aC.put("min", add_Client.get(0));
            aC.put("avg", avgColl((ArrayList<Long>) add_Client));
            aC.put("count", (long) add_Client.size());

            methods.put("addClient", aC);


            String p1 = new PropertiesMethods("getData", gD.get("min"), gD.get("max"), gD.get("avg"), gD.get("count")).toString();
            System.out.println(p1);

            String p2 = new PropertiesMethods("addClient", aC.get("min"), aC.get("max"), aC.get("avg"), aC.get("count")).toString();
            System.out.println(p2);

            String p3 = new PropertiesMethods("checkAuth", cA.get("min"), cA.get("max"), cA.get("avg"), cA.get("count")).toString();
            System.out.println(p3);

            String p4 = new PropertiesMethods("getActions", gA.get("min"), gA.get("max"), gA.get("avg"), gA.get("count")).toString();
            System.out.println(p4)
            ;
            String p5 = new PropertiesMethods("acceptToken", aT.get("min"), aT.get("max"), aT.get("avg"), aT.get("count")).toString();
            System.out.println(p5);

            String p6 = new PropertiesMethods("processAction", pA.get("min"), pA.get("max"), pA.get("avg"), pA.get("count")).toString();
            System.out.println(p6);

            String p7 = new PropertiesMethods("processClient", pC.get("min"), pC.get("max"), pC.get("avg"), pC.get("count")).toString();
            System.out.println(p7);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    
    }

    public static long avgColl(ArrayList<Long> coll) {
        long j = 0L;
            for (int i = 0; i < coll.size() - 1; i++) {
            j += coll.get(i);
            }
        j /= coll.size();
        return j;
    }
}
