package Pharser;

import Collection.Organization;
import com.google.gson.Gson;
import java.io.*;
import java.util.ArrayDeque;

/**
 * Класс, который реализует работу с файлом, где хранится коллекция в формате json
 */
public class Pharse {
    static String path = "C:\\Users\\Леонид\\Desktop\\ITMO\\Programming\\1.2\\lab6\\Server\\src\\main\\resources\\Info.json"; // C:\Users\Леонид\Desktop\ITMO\Programming\1.2\lab6\Server\src\main\resources\Info.json
    static Gson gson = new Gson();

    /**
     * Запись в файл
     * @param arrayDeque Коллекция организаций
     * @throws IOException
     */
    public static void write(ArrayDeque arrayDeque) throws IOException {
        Writer writer = new OutputStreamWriter(new FileOutputStream(path), "Windows-1251");
        for(Object i : arrayDeque){
            String json = gson.toJson(i);

            writer.write(json);
            writer.flush();

        }
        writer.close();
    }

    /**
     * Сохранение новой коллекции в файл
     * @return Коллекция организаций
     * @throws IOException
     */
    public static ArrayDeque read() throws IOException, NumberFormatException {
        ArrayDeque<Organization> ArrayNew = new ArrayDeque<Organization>();
        String s;
        StringBuilder sb = new StringBuilder();
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(path));
        byte[] data = new byte[1024];
        int amountData = in.read(data, 0, 1024);

        while (amountData != -1 && amountData == 1024){
            sb.append(new String(data, "Windows-1251"));
            amountData = in.read(data, 0, 1024);
        }

        if (amountData !=-1){
            byte[] residue = new byte[amountData];
            System.arraycopy(data, 0, residue, 0,residue.length);
            sb.append(new String(residue, "Windows-1251"));
        }
        s = String.valueOf(sb).trim();
        if (s.equals("")){
            return ArrayNew;
        }
        else{
            String[] s1 =s.split("}}}");
            for (String i : s1){
                i+="}}}";
                Organization o = gson.fromJson(i, Organization.class);
                ArrayNew.add(o);
            }
            return ArrayNew;
        }
    }
}