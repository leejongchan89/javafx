package com.itgroup.test.controller;

import com.itgroup.test.bean.Item;
import com.itgroup.test.dao.ItemDao;
import com.itgroup.test.utility.Paging;
import com.itgroup.test.utility.Utility;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BackOfficeController implements Initializable {
    @FXML private TableView<Item> itemTable;
    @FXML private ImageView itemView;
    @FXML private ToggleGroup genderRBgroup;
    @FXML private RadioButton allRB;
    @FXML private RadioButton menRB;
    @FXML private RadioButton womenRB;
    @FXML private CheckBox negoCheck;
    @FXML private ComboBox <String> categoryCombo;
    @FXML private Pagination pagination;
    @FXML private Label pageStatus;
    @FXML private Pane pane;
    @FXML private Label user_id;
    @FXML private Label brand;
    @FXML private Label item_name;
    @FXML private Label item_size_condition;
    @FXML private Label price;
    @FXML private Label category;
    @FXML private TextArea item_descripton;

    private ItemDao dao = null;
    // 필터 컨트롤을 위한 mode 변수
    private String category01 = null;
    private String negotiation = null;
    private String category02 = null;
    private String mode = null; // 필드 검색을 위한 mode 변수
    private ObservableList<Item> itemObList = null;



    public void initialize(URL url, ResourceBundle resourceBundle) {
        //초기화 공간 : 이벤트 핸들러들 넣기 좋다?
        //깃허브 3번째 수정
        //깃허브 4번째 수정

        this.dao = new ItemDao();

        setTableColumns(); // 테이블뷰 컬럼 이름 셋팅
        setPagination(0); // 테이블뷰 인덱스 1로 시작


        //콤보 박스에 리스트 넣기 //질문 매개변수 category02 4개 변수를 어떻게 가져오지? 하드코딩 해야하나?
        List<String> cmbData = dao.selectByCmb();
        ObservableList<String> itemObList2 = FXCollections.observableArrayList(cmbData);
        categoryCombo.setItems(itemObList2);
        categoryCombo.getSelectionModel().selectFirst(); //최초 콤보 박스 리스트 '전체 보기' 선택 디폴트값


        /*
        테이블뷰 목록 선택 시 액션 메소드 추가

        1. ChangeListener 객체 tableListener 생성자를 만들면 changed() 메소드가 오버라이딩 된다.
        2. .getSelectionModel(). 메소드를 사용해 'tableListener'객체를 'itemTable'객체에 넣는다.
           - getSelectionModel은 목록을 담을 수 있는 객체에 다 사용 가능
        3. 테이블뷰 목록을 선택하면 이미지가 뜨게 연결한다.
         */

        ChangeListener<Item> tableListener = new ChangeListener<Item>() {
            @Override
            public void changed(ObservableValue<? extends Item> observableValue, Item oldValue, Item newValue) {
                //메소드 확인 출력
                if(newValue != null) {
                    System.out.println("선택한 아이템 정보");
                    System.out.println(newValue);
                    System.out.println("=============================");


                /*
                이미지 가져오기

                1.이미지 경로 String 객체에 담기
                2.이미지 클래스 객체 생성 -> String 객체를 url로 바꿔서 담고 마지막에 .toString()을 붙여준다.

                 */
                    String imageStringPath = "";
                    String noImageStringPath = "";
                    if (newValue.getImage01() != null) {
                        imageStringPath = Utility.IMAGE_PATH + newValue.getImage01().trim();
                    } else {
                        imageStringPath = Utility.IMAGE_PATH + "noimage.jpg"; // Image01이 notnull이라 사용될 일은 없다.
                    }


                    Image imageUrlPath = null;
                    Image noImageFile = null;

                    noImageStringPath = Utility.IMAGE_PATH + "noimage.jpg";

                    noImageFile = new Image(getClass().getResource(noImageStringPath).toString());

                    if (getClass().getResource(imageStringPath) == null) {
                        itemView.setImage(noImageFile); //DB에 image01 값이 있는데 실제 이미지 폴더에 동일 이름 이미지가 없으면 'noimage'디폴트 이미지를 띄운다.
                    } else {
                        imageUrlPath = new Image(getClass().getResource(imageStringPath).toString());
                        itemView.setImage(imageUrlPath);
                    }

                    // 이미지 출력 시 크기 조정
                    itemView.setFitWidth(242.0);
                    itemView.setFitHeight(282.0);
                    itemView.setPreserveRatio(false);


                    //상품 출력화면 라벨에 컬럼값 넣기
                    user_id.setText(newValue.getUser_id());
                    brand.setText(newValue.getBrand());
                    item_name.setText(newValue.getItem_name());
                    item_size_condition.setText(newValue.getItem_size() + " / " + newValue.getCondition());
                    price.setText(String.valueOf(newValue.getPrice()) + " 원");
                    category.setText(newValue.getCategory01() + " > " + newValue.getCategory02() + " > " + newValue.getCategory03());
                    item_descripton.setText(addLineBreaks(newValue.getItem_description(), 20));
                }
            }
        };
        itemTable.getSelectionModel().selectedItemProperty().addListener(tableListener);
        itemTable.getSelectionModel().selectFirst(); // 최초 실행 시 테이블뷰 첫행 선택 디폴트값

    }

    //줄바꿈 로직
    public String addLineBreaks(String text, int lineLength) {
        if (text == null || lineLength <= 0) {
            return text;
        }

        StringBuilder formattedText = new StringBuilder();
        int length = text.length();

        for (int i = 0; i < length; i += lineLength) {
            int end = Math.min(length, i + lineLength);
            formattedText.append(text, i, end);
            formattedText.append("\n"); // 줄바꿈 추가
        }

        return formattedText.toString();
    }


    /*
    setPagination : 페이지 인덱스를 매개변수로 하는 메소드
    현재 페이지 인덱스 설정 하고, 페이지 호출한다.
    그리고 페이지 인덱스가 변할 때 마다 해당 인덱스 페이지를 호출한다.

    사용된 메소드
    1. setCurrentPageIndex(pageIndex)
    2. createPage(pageIndex);
    3. currentPageIndexProperty().addListener(람다식)
     */
    private void setPagination(int pageIndex) {

        System.out.println("pageIndex : " + pageIndex);

        //현재 페이지 인덱스 설정
        pagination.setCurrentPageIndex(pageIndex);

        //페이지 호출
        createPage(pageIndex);

        //페이지 인덱스가 변할 때마다 해당 인덱스 페이지를 호출한다.
        pagination.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> createPage(newIndex.intValue()));

        //화면 갱신시 이미지 뷰 정보도 없애 주기
        itemView.setImage(null);

    }

    /*
    createPage 메소드 : Node 타입을 반환하는 페이지 인덱스를 매개변수로 하는 메소드 -> itemTable를 반환한다.
    필터링 조건에 따라 데이터를 카운팅하는 메소드 dao.getTotalCount를 totalCount 객체에 담아서 pageInfo 객체 생성자에 넣어준다.
    테이블뷰에 데이터를 넣는 fillTableData(pageInfo) 메소드를 호출한다.
    테이블뷰를 반환한다.


    사용된 메소드 및 객체
    1. dao.getTotalCount(this.category01, this.negotiation, this.category02) : 필터링에 사용되는 변수들을 매개변수로 담았다.
    2. Paging 클래스 객체 pageInfo : Pasing 클래스 생성자에 필터링 변수들을 추가 했다.
    3. fillTableData(pageInfo) :  pageInfo 매개변수
    4. pagination.setPageCount(pageInfo.getTotalPage()) : 전체 페이지 수를 넣어준다.
     */

    private Node createPage(int pageIndex) {

        int totalCount = 0;
        totalCount = dao.getTotalCount(this.category01, this.negotiation, this.category02);

        //pageInfo 객체를 만든다.
        // Paging 클래스에서 생성자 매개변수 참고하기
        // String _pageNumber, String _pageSize, int totalCount, String url, String mode, String category01, String negotiation, String category02, String keyword
        Paging pageInfo = new Paging(String.valueOf(pageIndex+1), "15", totalCount, null, this.mode, this.category01, this.negotiation, this.category02, null);

        // 테이블뷰에 데이터 집어 넣는 메소드
        fillTableData(pageInfo);

        //하단 페이지 수 지정하는 메소드
        //getTotalPage() = 2이다.
        //getTotalPage() 메소드는 Pasing에 있는 totalPage 객체를 리턴한다.
        pagination.setPageCount(pageInfo.getTotalPage());


        return this.itemTable;
    }


    //테이블뷰에 데이터 넣는 메소드
    private void fillTableData(Paging pageInfo) {

        List<Item> allData = dao.getPaginationData(pageInfo, category01, negotiation, category02);

        itemObList = FXCollections.observableArrayList(allData);
        //System.out.println(itemObList.size()); //디버깅 테이블뷰에 데이터가 들어갔는지

        itemTable.setItems(itemObList);

        //new
        //pagination.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> createPage(newIndex.intValue()));

        pageStatus.setText(pageInfo.getPagingStatus()); //현제 페이지 라벨 셋팅

        System.out.println("매개변수 확인");
        System.out.println(allData);
    }

    //테이블뷰 내 객체 셋팅하기
    private void setTableColumns(){
        /*
    테이블뷰에 DB데이터 연결하기
    1.테이블뷰 객체 만들고 셋팅하기
      bean 클래스 변수 이름과 동일한 요소를 같는 배열 'colNames'을 만든다.
      TableColumn 객체 'tcol'를 선언하고 인덱스를 추가한 뒤 그 객체와 'colNames'연결시킨다.

    2.테이블뷰에 값 집어 넣기
      Dao 클래스에 만들어 놓은 selectAll()메소드를 통해
      ObservableList<Item> 객체에 데이터를 집어 넣고
      테이블뷰 객체 'itemTable'에 .setItems(itemObList);을 통해서 넣는다.
     */

        String[] colNames = {
                "item_num",
                "category01",
                "category02",
                "category03",
                "condition",
                "item_size",
                "brand",
                "shipping_fee",
                "price",
                "negotiation",
                "image01",
                "image02",
                "image03",
                "item_name",
                "item_description",
                "item_inputdate",
                "user_id",
                "user_nickname"
        };

        String[] colNamesKor = {
                "아이템 번호",
                "아이템 성별",
                "1차 카테고리",
                "2차 카테고리",
                "상태",
                "사이즈",
                "브랜드",
                "배송비",
                "판매 가격",
                "네고 제안 받기",
                "이미지01",
                "이미지02",
                "이미지03",
                "아이템 이름",
                "아이템 설명",
                "아이템 등록 날짜",
                "유저 아이디",
                "유저 닉네임"
        };


        TableColumn tcol = null;
        for (int i = 0; i < colNames.length; i++) {
            tcol = itemTable.getColumns().get(i); //인덱스 추가
            tcol.setCellValueFactory(new PropertyValueFactory<>(colNames[i])); //연결
            tcol.setStyle("-fx-alignment:center;"); //컬럼 가운데 정렬 셋팅
            tcol.setText(colNamesKor[i]); //컬럼명 한글화
        }
    }



    //라디오 버튼, 체크박스, 콤보박스 이벤트 메소드
    public void btnSelect(ActionEvent event){

        //라디오 버튼
        //String category01 = "";
        if(allRB.isSelected()){
            this.category01 = "모두";
        }else if (menRB.isSelected()){
            this.category01 = "남자";
        }else if (womenRB.isSelected()){
            this.category01 = "여자";
        }

        //체크 박스
        if(negoCheck.isSelected()){
            this.negotiation = "yes";
        }else {
            this.negotiation = "";// 여기가 전체
        }

        //콤보 박스
        if(categoryCombo.getValue() != null){
            this.category02 = categoryCombo.getValue().toString();
        }else {
            this.category02 = "";
        }

        setPagination(0);
        itemTable.getSelectionModel().selectFirst(); // 필터 영역 버튼을 선택했을 때 테이블뷰 첫행을 선택 하는 디폴트값

    };

}

