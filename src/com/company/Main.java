package com.company;

import java.io.*;
import java.sql.*;


/**
 * В этом главном классе, примере создадим и покажем авто-закрытие методов
 */
public class Main {


    public static void main(String[] args) {

        //Создаём редар(читалку), но не всё так просто,
        // ведь нужно обработать ошибку, того что он не найден
        // и после закрыть.
        // Поэтому такая портянка и так каждый раз, при работе с файлом,
        // ведь он имплементет 2 интерфейса Readable и Closeable
        Reader reader = null;
        try{
            reader = new FileReader(new File("my file"));
            // Что-то делаем с файлом ...
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        // но можно сделать проще, теперь не нужно его закрывать,
        // авто-закрытие файла
        try(Reader reader2 = new FileReader(new File("my file"))){
            // Что-то делаем с файлом ...
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Так же можно ещё сильнее сократить, создав класс с авто закрытием
     */
    static class MyClose implements AutoCloseable{

        @Override
        public void close() throws Exception {

        }
    }

    void method(){
        try (
                MyClose myClose = new MyClose();
                Reader reader = new FileReader(new File("my file"))
                    ){
            // Что-то делаем с файлом ...
        } catch (Exception e) {
            e.printStackTrace();
        }

        // где может пригодиться авто-закрытие
        //stream
        Reader reader;
        Writer writer;
        InputStream inputStream;
        OutputStream outputStream;
        // gdbc
        Connection connection;
        Statement statement;
        ResultSet resultSet;
    }
}
