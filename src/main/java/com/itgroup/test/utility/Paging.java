package com.itgroup.test.utility;

public class Paging {
    //페이징 관련 변수
    private int totalCount = 0 ; // 33 총 레코드 건수 33
    private int totalPage = 0 ; // 4 전체 페이지 수

    private int pageNumber = 0 ; // 1 보여줄 페이지 넘버(표현 가능한 페이지는 1부터 totalPage까지이다.)
    private int pageSize = 0 ; // 10 한 페이지에 보여줄 건수
    private int beginRow = 0 ; //현재 페이지의 시작 행
    private int endRow = 0 ; //현재 페이지의 끝 행

    private int pageCount = 10 ; //보여줄 페이지 링크 수
    private int beginPage = 0 ; //페이징 처리 시작 페이지 번호
    private int endPage = 0 ; //페이징 처리 끝 페이지 번호

    private String url = "" ; //예시 ==>  http://localhost:8989/MyServlet/list.do
    private String pagingHtml = "";//하단의 숫자 페이지 링크
    private String pagingStatus = ""; // 총 33건[1/4] 상단 우측의 현재 페이지 위치 표시

    //검색을 위한 변수 추가
    private String mode = "" ; //검색 모드(작성자, 글제목, 전체 검색은 all) 등등


    private String keyword = "" ; //검색할 단어

    //pagination Size 변수
    private String paginationSize = " pagination-sm" ; //  pagination-lg   pagination-sm    pagination-xs

    public Paging(String _pageNumber, String _pageSize, int totalCount, String url, String mode, String category01, String negotiation, String category02, String keyword) {
        //총 레코드 건수 : 257
        this.totalCount = totalCount;

        //전체 페이지 수 : 26

        this.pageSize = Integer.parseInt(_pageSize);
        this.totalPage = (int)Math.ceil((double)totalCount / pageSize) ;

//        totalPage = totalCount/pageSize + 1;

        //보여줄 페이지 넘버 : 12
        if(_pageNumber == null || _pageNumber.equals("null") || _pageNumber.equals("")){
            _pageNumber = "1";
        }
        this.pageNumber = Integer.parseInt(_pageNumber);

        //한 페이지에 보여줄 건수 : 10
        if(_pageSize == null || _pageSize.equals("null") || _pageSize.equals("")){
            _pageSize = "10";
        }
        this.pageSize = Integer.parseInt(_pageSize);

        //현재 페이지의 시작 행 : 111
        //1페이지일때 첫행 1
        //2페이지 일때 첫행 11
        //3페이지 일떄 첫행 21
        //12페이지 일때 첫행 111
        this.beginRow = this.pageNumber*this.pageSize-(this.pageSize-1);

        //현재 페이지의 끝 행 : 120
        this.endRow = this.pageNumber*this.pageSize;
        if(this.totalCount < this.endRow){
            this.endRow = this.totalCount;
        }

        //정수라 가능
        //페이징 처리 시작 페이지 번호 : 11
        this.beginPage = (this.pageNumber-1) / this.pageSize * this.pageSize + 1;

        //페이징 처리 끝 페이지 번호 : 20
        this.endPage = this.beginPage + this.pageCount -1; //쌤 버젼: pageSize -> pagecount
        if(this.endPage > this.totalPage){
            this.endPage = this.totalPage;
        }

        //요청 URL : abc.html
        this.url = url;

        //상단 우측의 현재 페이지 위치 표시 : 총 257건[12/26]
        this.pagingStatus = "총 " + this.totalCount + "건[" + this.pageNumber + "/" + this.totalPage + "]";

        //검색 모드 : bread
        this.mode = mode == null ? "" : mode;



        //검색 키워드 : 크로와상
        this.keyword = keyword == null ? "" : keyword;

        //int su = Integer.parseInt("150") ; //문자로 숫자로 바꾸는 방법
        //System.out.println("숫자 : " + su);
    }

    private String getPagingHtml( String url ){ //페이징 문자열을 만든다.
        String result = "" ;

        //add_param 변수 : 검색 관련하여 추가되는 파라미터 리스트
        String add_param = "&mode=" + mode + "&keyword=" + keyword ;

        result += "<ul class='pagination" + paginationSize + "'>";
        if ( pageNumber <= pageCount ) {//1부터 10페이지까지는 [맨처음]과 [이전]이 없다
            //result += "맨처음&nbsp;&nbsp;";
            //result += "이전&nbsp;&nbsp;";
        } else {
            result += "<li><a href='" + url + "?pageNumber=" + 1 +
                    "&pageSize=" + pageSize + add_param + "'>맨처음</a></li>&nbsp;&nbsp;";

            result += "<li><a href='" + url + "?pageNumber=" + (beginPage - 1) +
                    "&pageSize=" + pageSize + add_param + "'>이전</a></li>&nbsp;&nbsp;";
        }
        //페이지 시작 번호 부터 ~ 끝 번호 까지 표시

        for (int i = beginPage ; i <= endPage ; i++) {
            if(i == pageNumber){ //현재 페이지이면 링크는 없고, 빨간색으로 표시
                result += "<li class='active'><a><font color='red'><b>" + i + "</b></font></a></li>&nbsp;";
            }else{
                result += "<li><a href='" + url + "?pageNumber=" + i +
                        "&pageSize=" + pageSize + add_param + "'>" + i + "</li></a>&nbsp;";
            }
        }

        //마지막에는 [다음]과 [맨끝]이 없다
        if ( pageNumber >= (totalPage / pageCount * pageCount + 1) ) {
            //result += "다음&nbsp;&nbsp;";
            //result += "맨 끝&nbsp;&nbsp;";
        } else {
            result += "<li><a href='" + url + "?pageNumber=" + (endPage + 1) +
                    "&pageSize=" + pageSize + add_param + "'>다음</a></li>&nbsp;&nbsp;";

            result += "<li><a href='" + url + "?pageNumber=" + totalPage +
                    "&pageSize=" + pageSize + add_param + "'>맨 끝</a></li>";
        }
        result += "</ul>";
        return result ;
    }

    public void displayInformation() {
        System.out.println("총 레코드 건수 : " + totalCount + "\n");
        System.out.println("전체 페이지 수 : " + totalPage + "\n");
        System.out.println("보여줄 페이지 넘버 : " + pageNumber + "\n");
        System.out.println("한 페이지에 보여줄 건수 : " + pageSize + "\n");
        System.out.println("현재 페이지의 시작 행 : " + beginRow + "\n");
        System.out.println("현재 페이지의 끝 행 : " + endRow + "\n");
        System.out.println("보여줄 페이지 링크 수 : " + pageCount + "\n");
        System.out.println("페이징 처리 시작 페이지 번호 : " + beginPage + "\n");
        System.out.println("페이징 처리 끝 페이지 번호 : " + endPage + "\n");
        System.out.println("요청 URL : " + url + "\n");
        //System.out.println("하단의 숫자 페이지 링크 : " + pagingHtml + "\n");
        System.out.println("상단 우측의 현재 페이지 위치 표시 : " + pagingStatus + "\n");
        System.out.println("검색 모드 : " + mode + "\n");
        System.out.println("검색 키워드 : " + keyword + "\n");
    }

    public String getPagingHtml() {	return pagingHtml;}
    public int getPageNumber() {	return pageNumber;}
    public int getPageSize() {	return pageSize;}
    public String getPagingStatus() {	return pagingStatus;}
    public int getBeginRow() {	return beginRow;}
    public int getEndRow() {	return endRow;}

    public int getTotalPage() {
        return totalPage;
    }

//	public int getBeginPage() {
//		return beginPage;
//	}
//
//	public int getEndPage() {
//		return endPage;
//	}

    //상세 검색을 위하여 검색 모드와 검색 키워드 항목이 추가됨
    public String getMode() {return mode;}
    public String getKeyword() { return keyword; 	}
}






