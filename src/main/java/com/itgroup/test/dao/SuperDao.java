package com.itgroup.test.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class SuperDao {
    /*
    1)드라이버 로딩
    driver, url, id, password를 String 인스턴스 객체 선언하고
    SuperDao 생성자 안에 객체를 만들고 값을 넣어 준다.
    */

    private String driver;
    private String url = null;
    private String id = null;
    private String password = null;

    // SuperDao 생성자
    public SuperDao() {
        this.driver = "oracle.jdbc.driver.OracleDriver";
        this.url = "jdbc:oracle:thin:@localhost:1521:xe";
        this.id = "leejongchan";
        this.password = "jongchan";

        //Oracle 드라이버 클래스 객체 생성
        try{
            Class.forName(driver); //드라이버 예외처리
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /*
    2)데이터 베이스 연결
    Connection 객체가 있어야 sql 문장 처리해주는 PreparedStatement객체를 반환 받을 수 있다.
    따라서, DriverManager.getConnection(url, id, password)로 conn객체를 반환 받는 DBConnection() 메소드를 만든다.

    1.Connection 객체를 반환하는 DBConnection() 메소드를 만든다.
    2.이 안에서 DriverManager 클래스 .getConnection() 메소드에 url, id, password 값을 넣어 Connection 객체를 반환 받는다.
    3.url, id, password에 대한 예외 처리를 해준다.
     */

    //데이터 베이스 연결 메소드
    protected Connection DBConnection(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url, id, password);
            //conn 객체가 잘 반환 됐는지 확인해보자
            if(conn != null){
                System.out.println("DB 연결");
            }else{
                System.out.println("DB 연결X");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return conn;
    }
}
