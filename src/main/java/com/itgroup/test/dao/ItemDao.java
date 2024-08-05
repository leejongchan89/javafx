package com.itgroup.test.dao;

import com.itgroup.test.bean.Item;
import com.itgroup.test.utility.Paging;
import javafx.fxml.FXML;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItemDao extends SuperDao {
    public ItemDao() {
        super(); // 숨어있다.
    }

    public List<Item> getPaginationData(Paging pageInfo, String category01, String negotiation, String category02) {

        List<Item> allData = null;
        allData = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null; //묶음

        String sql = "";


        if (category01 == "" || category01 == null || category01.equals("모두")) {

            sql = " select item_num, category01, category02, category03, condition, item_size, brand, shipping_fee, price, negotiation, image01, image02, image03, item_name, item_description, item_inputdate, user_id, user_nickname ";
            sql += " from ( ";
            sql += " select item_num, category01, category02, category03, condition, item_size, brand, shipping_fee, price, negotiation, image01, image02, image03, item_name, item_description, item_inputdate, user_id, user_nickname, ";
            sql += " rank() over(order by item_num desc) as ranking ";
            sql += " from items ";

            if(negotiation == null || negotiation.equals("")){
                //sql += " where negotiation = '' ";
                if (category02 == null || category02.equals("전체 보기")) {
                    //sql += " where category02 in ('상의', '하의', '모자', '신발') ";
                } else if (category02.equals("상의")) {
                    sql += " where category02 = '상의' ";
                } else if (category02.equals("하의")) {
                    sql += " where category02 = '하의' ";
                } else if (category02.equals("모자")) {
                    sql += " where category02 = '모자' ";
                } else if (category02.equals("신발")) {
                    sql += " where category02 = '신발' ";
                }
            }else if (negotiation.equals("yes")){
                sql += " where negotiation = 'yes' ";
                if (category02 == null || category02.equals("전체 보기")) {
                    //sql += " and category02 in ('상의', '하의', '모자', '신발') ";
                } else if (category02.equals("상의")) {
                    sql += " and category02 = '상의' ";
                } else if (category02.equals("하의")) {
                    sql += " and category02 = '하의' ";
                } else if (category02.equals("모자")) {
                    sql += " and category02 = '모자' ";
                } else if (category02.equals("신발")) {
                    sql += " and category02 = '신발' ";
                }
            }


        } else if (category01.equals("남자")) {

            sql = " select item_num, category01, category02, category03, condition, item_size, brand, shipping_fee, price, negotiation, image01, image02, image03, item_name, item_description, item_inputdate, user_id, user_nickname ";
            sql += " from ( ";
            sql += " select item_num, category01, category02, category03, condition, item_size, brand, shipping_fee, price, negotiation, image01, image02, image03, item_name, item_description, item_inputdate, user_id, user_nickname, ";
            sql += " rank() over(order by item_num desc) as ranking ";
            sql += " from items ";

            sql += " where category01 = '남자' ";

            if (negotiation == null || negotiation.equals("")) {
                //sql += " and negotiation = '' ";
                if (category02 == null || category02.equals("전체 보기")) {
                    //sql += " where category02 in ('상의', '하의', '모자', '신발') ";
                } else if (category02.equals("상의")) {
                    sql += " and category02 = '상의' ";
                } else if (category02.equals("하의")) {
                    sql += " and category02 = '하의' ";
                } else if (category02.equals("모자")) {
                    sql += " and category02 = '모자' ";
                } else if (category02.equals("신발")) {
                    sql += " and category02 = '신발' ";
                }
            } else if (negotiation.equals("yes")) {
                sql += " and negotiation = 'yes' ";
                if (category02 == null || category02.equals("전체 보기")) {
                    //sql += " and category02 in ('상의', '하의', '모자', '신발') ";
                } else if (category02.equals("상의")) {
                    sql += " and category02 = '상의' ";
                } else if (category02.equals("하의")) {
                    sql += " and category02 = '하의' ";
                } else if (category02.equals("모자")) {
                    sql += " and category02 = '모자' ";
                } else if (category02.equals("신발")) {
                    sql += " and category02 = '신발' ";
                }
            }

        } else if (category01.equals("여자")) {
            sql = " select item_num, category01, category02, category03, condition, item_size, brand, shipping_fee, price, negotiation, image01, image02, image03, item_name, item_description, item_inputdate, user_id, user_nickname ";
            sql += " from ( ";
            sql += " select item_num, category01, category02, category03, condition, item_size, brand, shipping_fee, price, negotiation, image01, image02, image03, item_name, item_description, item_inputdate, user_id, user_nickname, ";
            sql += " rank() over(order by item_num desc) as ranking ";
            sql += " from items ";

            sql += " where category01 = '여자' ";

            if (negotiation == null || negotiation.equals("")) {
                //sql += " and negotiation = '' ";
                if (category02 == null || category02.equals("전체 보기")) {
                    //sql += " where category02 in ('상의', '하의', '모자', '신발') ";
                } else if (category02.equals("상의")) {
                    sql += " and category02 = '상의' ";
                } else if (category02.equals("하의")) {
                    sql += " and category02 = '하의' ";
                } else if (category02.equals("모자")) {
                    sql += " and category02 = '모자' ";
                } else if (category02.equals("신발")) {
                    sql += " and category02 = '신발' ";
                }
            } else if (negotiation.equals("yes")) {
                sql += " and negotiation = 'yes' ";
                if (category02 == null || category02.equals("전체 보기")) {
                    //sql += " and category02 in ('상의', '하의', '모자', '신발') ";
                } else if (category02.equals("상의")) {
                    sql += " and category02 = '상의' ";
                } else if (category02.equals("하의")) {
                    sql += " and category02 = '하의' ";
                } else if (category02.equals("모자")) {
                    sql += " and category02 = '모자' ";
                } else if (category02.equals("신발")) {
                    sql += " and category02 = '신발' ";
                }
            }
        }

        sql += " ) ";
        sql += " where ranking between ? and ? ";

        System.out.println(sql); //디버깅

        try { // sql문 예외 처리
            conn = super.DBConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, pageInfo.getBeginRow());
            pstmt.setInt(2, pageInfo.getEndRow());

            rs = pstmt.executeQuery(); // 결과물은 행(bean 수) 열(컬럼 수)를 담은 그룹

            //ResultSet 객체에 담긴 데이터를 while문으로 bean 타입 리스트에 담는다. makeBean()메소드를 만든다.

            while (rs.next() == true) { //.next() 메소드를 사용하면 rs 바닥에 도착하면 false 반환해준다. 반환 타입 boolean
                Item bean = this.makeBean(rs);
                allData.add(bean);
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally { //데이터 조회후 객체들을 clear하는 명목으로 close한다. 그리고 위와 같은 개념으로 예외처리 해준다.
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return allData;
    }

    // setPagination()에 들어가는 전체 데이터 갯수 구하는 메소드
    public int getTotalCount(String category01, String negotiation, String category02) {
        int totalCount = 0; //초기화

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "";

        if (category01 == "" || category01 == null || category01.equals("모두")) { //선택을 안했을떄
            sql = " select count(*) as mycnt from items ";
            if (negotiation == null || negotiation.equals("")) {
                //sql += " where negotiation = '' ";
                if (category02 == null || category02.equals("전체 보기")) {
                    //sql += " where category02 in ('상의', '하의', '모자', '신발') ";
                } else if (category02.equals("상의")) {
                    sql += " where category02 = '상의' ";
                } else if (category02.equals("하의")) {
                    sql += " where category02 = '하의' ";
                } else if (category02.equals("모자")) {
                    sql += " where category02 = '모자' ";
                } else if (category02.equals("신발")) {
                    sql += " where category02 = '신발' ";
                }
            } else if (negotiation.equals("yes")) {
                sql += " where negotiation = 'yes' ";
                if (category02 == null || category02.equals("전체 보기")) {
                    //sql += " and category02 in ('상의', '하의', '모자', '신발') ";
                } else if (category02.equals("상의")) {
                    sql += " and category02 = '상의' ";
                } else if (category02.equals("하의")) {
                    sql += " and category02 = '하의' ";
                } else if (category02.equals("모자")) {
                    sql += " and category02 = '모자' ";
                } else if (category02.equals("신발")) {
                    sql += " and category02 = '신발' ";
                }
            }

        } else if (category01.equals("남자")) {
            sql = " select count(*) as mycnt from items ";
            sql += " where category01 = '남자' ";

            if (negotiation == null || negotiation.equals("")) {
                //sql += " and negotiation = '' ";
                if (category02 == null || category02.equals("전체 보기")) {
                    //sql += " where category02 in ('상의', '하의', '모자', '신발') ";
                } else if (category02.equals("상의")) {
                    sql += " and category02 = '상의' ";
                } else if (category02.equals("하의")) {
                    sql += " and category02 = '하의' ";
                } else if (category02.equals("모자")) {
                    sql += " and category02 = '모자' ";
                } else if (category02.equals("신발")) {
                    sql += " and category02 = '신발' ";
                }
            } else if (negotiation.equals("yes")) {
                sql += " and negotiation = 'yes' ";
                if (category02 == null || category02.equals("전체 보기")) {
                    //sql += " and category02 in ('상의', '하의', '모자', '신발') ";
                } else if (category02.equals("상의")) {
                    sql += " and category02 = '상의' ";
                } else if (category02.equals("하의")) {
                    sql += " and category02 = '하의' ";
                } else if (category02.equals("모자")) {
                    sql += " and category02 = '모자' ";
                } else if (category02.equals("신발")) {
                    sql += " and category02 = '신발' ";
                }
            }

        } else if (category01.equals("여자")) {
            sql = " select count(*) as mycnt from items ";
            sql += " where category01 = '여자' ";

            if (negotiation == null || negotiation.equals("")) {
                //sql += " and negotiation = '' ";
                if (category02 == null || category02.equals("전체 보기")) {
                    //sql += " where category02 in ('상의', '하의', '모자', '신발') ";
                } else if (category02.equals("상의")) {
                    sql += " and category02 = '상의' ";
                } else if (category02.equals("하의")) {
                    sql += " and category02 = '하의' ";
                } else if (category02.equals("모자")) {
                    sql += " and category02 = '모자' ";
                } else if (category02.equals("신발")) {
                    sql += " and category02 = '신발' ";
                }
            } else if (negotiation.equals("yes")) {
                sql += " and negotiation = 'yes' ";
                if (category02 == null || category02.equals("전체 보기")) {
                    //sql += " and category02 in ('상의', '하의', '모자', '신발') ";
                } else if (category02.equals("상의")) {
                    sql += " and category02 = '상의' ";
                } else if (category02.equals("하의")) {
                    sql += " and category02 = '하의' ";
                } else if (category02.equals("모자")) {
                    sql += " and category02 = '모자' ";
                } else if (category02.equals("신발")) {
                    sql += " and category02 = '신발' ";
                }
            }
        }

        try {
            conn = super.DBConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery(); // 결과물은 행(bean 수) 열(컬럼 수)를 담은 그룹


            if(rs.next() == true) { // 1행 1열이라서 while문일 필요가 없다.
                totalCount = rs.getInt("mycnt");
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return totalCount;
    }


    /*
    Dao는 Java에서 SQL문장을 이용해 DB의 데이터들을 만지는 CRUD 관점의 메소드을 만드는 클래스

    메소드 만들 때 필요 한 것:
    Connection 객체

    PreparedStatement 객체
     - DQL(select) : executeQuery()의 반환 타입은 ResultSet 이다.
     - DML(insert, update, delete) : executeUpdate()의 반환 타입은 int 이다.
     - close(); 메소드

    ResultSet 객체 : select문 으로 조회된 데이터의 집합 그룹
     */




    //rs객체에 담긴 sql조회 결과 데이터들을 bean에 담는 메소드
    private Item makeBean(ResultSet rs) {
        Item bean = new Item(); //질문: 생성자를 안만들면 NullPointerException이 뜬다.
        try {
            bean.setItem_num(rs.getInt("item_num"));
            bean.setCategory01(rs.getString("category01"));
            bean.setCategory02(rs.getString("category02"));
            bean.setCategory03(rs.getString("category03"));
            bean.setCondition(rs.getString("condition"));
            bean.setItem_size(rs.getString("item_size"));
            bean.setBrand(rs.getString("brand"));
            bean.setShipping_fee(rs.getInt("shipping_fee"));
            bean.setPrice(rs.getInt("price"));
            bean.setNegotiation(rs.getString("negotiation"));
            bean.setImage01(rs.getString("image01"));
            bean.setImage02(rs.getString("image02"));
            bean.setImage03(rs.getString("image03"));
            bean.setItem_name(rs.getString("item_name"));
            bean.setItem_description(rs.getString("item_description"));
            bean.setItem_inputdate(String.valueOf(rs.getDate("item_inputdate")));
            bean.setUser_id(rs.getString("user_id"));
            bean.setUser_nickname(rs.getString("user_nickname"));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bean;
    }

    //Item 테이블에서 특정 칼럼 변수값을 List<String> 객체에 담는 메소드 / DQL 데이터 질의어
    public List<String> selectByCmb() {
        List<String> data = null;
        data = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select distinct category02 from items order by case category02 when '상의' then 1 when '하의' then 2 when '모자' then 3 when '신발' then 4 end ";

        try {
            conn = super.DBConnection();

            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery(); // 결과물은 행(bean 수) 열(컬럼 수)를 담은 그룹

            data.add("전체 보기");

            while (rs.next() == true) {

                String category01 = rs.getString("category02");
                data.add(category01);
            }


        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return data;
    }



    //// 라디오 박스, 체크 박스, 콤보 박스 카테고리 변수를 분기처리해서 담는 메소드
    // DQL 데이터 질의어
    public List<Item> selectByRB_CB_CBB(String category01, String negotiation, String category02) {
        List<Item> data = null;
        data = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;


        String sql = "";
        if (category01 == "" || category01 == null || category01.equals("모두")) { //선택을 안했을떄
            sql = " select * from items ";
            if (negotiation == null || negotiation.equals("")) {
                //sql += " where negotiation = '' ";
                if (category02 == null || category02.equals("전체 보기")) {
                    //sql += " where category02 in ('상의', '하의', '모자', '신발') ";
                } else if (category02.equals("상의")) {
                    sql += " where category02 = '상의' ";
                } else if (category02.equals("하의")) {
                    sql += " where category02 = '하의' ";
                } else if (category02.equals("모자")) {
                    sql += " where category02 = '모자' ";
                } else if (category02.equals("신발")) {
                    sql += " where category02 = '신발' ";
                }
            } else if (negotiation.equals("yes")) {
                sql += " where negotiation = 'yes' ";
                if (category02 == null || category02.equals("전체 보기")) {
                    //sql += " and category02 in ('상의', '하의', '모자', '신발') ";
                } else if (category02.equals("상의")) {
                    sql += " and category02 = '상의' ";
                } else if (category02.equals("하의")) {
                    sql += " and category02 = '하의' ";
                } else if (category02.equals("모자")) {
                    sql += " and category02 = '모자' ";
                } else if (category02.equals("신발")) {
                    sql += " and category02 = '신발' ";
                }
            }

        } else if (category01.equals("남자")) {
            sql = "select * from items ";
            sql += " where category01 = '남자' ";

            if (negotiation == null || negotiation.equals("")) {
                //sql += " and negotiation = '' ";
                if (category02 == null || category02.equals("전체 보기")) {
                    //sql += " where category02 in ('상의', '하의', '모자', '신발') ";
                } else if (category02.equals("상의")) {
                    sql += " and category02 = '상의' ";
                } else if (category02.equals("하의")) {
                    sql += " and category02 = '하의' ";
                } else if (category02.equals("모자")) {
                    sql += " and category02 = '모자' ";
                } else if (category02.equals("신발")) {
                    sql += " and category02 = '신발' ";
                }
            } else if (negotiation.equals("yes")) {
                sql += " and negotiation = 'yes' ";
                if (category02 == null || category02.equals("전체 보기")) {
                    //sql += " and category02 in ('상의', '하의', '모자', '신발') ";
                } else if (category02.equals("상의")) {
                    sql += " and category02 = '상의' ";
                } else if (category02.equals("하의")) {
                    sql += " and category02 = '하의' ";
                } else if (category02.equals("모자")) {
                    sql += " and category02 = '모자' ";
                } else if (category02.equals("신발")) {
                    sql += " and category02 = '신발' ";
                }
            }

        } else if (category01.equals("여자")) {
            sql = "select * from items ";
            sql += " where category01 = '여자' ";

            if (negotiation == null || negotiation.equals("")) {
                //sql += " and negotiation = '' ";
                if (category02 == null || category02.equals("전체 보기")) {
                    //sql += " where category02 in ('상의', '하의', '모자', '신발') ";
                } else if (category02.equals("상의")) {
                    sql += " and category02 = '상의' ";
                } else if (category02.equals("하의")) {
                    sql += " and category02 = '하의' ";
                } else if (category02.equals("모자")) {
                    sql += " and category02 = '모자' ";
                } else if (category02.equals("신발")) {
                    sql += " and category02 = '신발' ";
                }
            } else if (negotiation.equals("yes")) {
                sql += " and negotiation = 'yes' ";
                if (category02 == null || category02.equals("전체 보기")) {
                    //sql += " and category02 in ('상의', '하의', '모자', '신발') ";
                } else if (category02.equals("상의")) {
                    sql += " and category02 = '상의' ";
                } else if (category02.equals("하의")) {
                    sql += " and category02 = '하의' ";
                } else if (category02.equals("모자")) {
                    sql += " and category02 = '모자' ";
                } else if (category02.equals("신발")) {
                    sql += " and category02 = '신발' ";
                }
            }
        }


        System.out.println("sql");
        System.out.println(sql);

        try {
            conn = super.DBConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery(); // 결과물은 행(bean 수) 열(컬럼 수)를 담은 그룹

            while (rs.next() == true) {
                Item bean = this.makeBean(rs);
                data.add(bean);
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return data;
    }




}

