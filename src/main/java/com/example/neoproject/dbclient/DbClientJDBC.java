package com.example.neoproject.dbclient;
import com.example.neoproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.sql.*;
import java.time.Instant;
import java.util.Random;

public class DbClientJDBC {


    private PostolettoRepository p;


    private static long startTime = 0, endTime = 0, timeElapsed = 0;

    public static void main(String arg[]) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/neonataldb";
        String username = "venturino";
        String password = "1234";
        String query = "insert into observationtemp values (?,?,?,?,?,?)";
        Connection  c = null;
        try{
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(url,username, password);
            if(c!= null)
                System.out.println("connessione avvenuta");
        }catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

        try(PreparedStatement ps = c.prepareStatement(query)){
            for (int j = 11; j <=5000; j++) {
                System.out.println("neonato numero: " + j);
                startTime = System.currentTimeMillis();
                c.setAutoCommit(false);
                int num_obs = new Random().nextInt(5000, 12000);
                for (int i = 1; i <= 20000; i++) {
                    ps.setTimestamp(1, Timestamp.from(Instant.now()));
                    ps.setInt(2, j);
                    ps.setInt(3, new Random().nextInt(60, 120));
                    ps.setInt(4, j);
                    ps.setInt(5, j);
                    ps.setInt(6, i);
                    ps.executeUpdate();
                }
                c.commit();
                endTime = System.currentTimeMillis();
                timeElapsed = endTime - startTime;
                System.out.println("------------tempo trascorso per 1 bambino: " + timeElapsed + " --------------");
            }
        }
        catch (SQLException s){
            c.rollback();
            s.fillInStackTrace();
        }
    }
}
