package bookshop.example;

public class Book {
	private int no;
	private String title;
	private String author;
	private int status;
	
	public Book(int no, String title, String author) {
		this.no = no;
        this.title = title;
        this.author = author;
        this.status = 1;
	}
	
	public void rent() {
		if (status == 1) {
			status = 0;
			System.out.println(title + "(이)가 대여되었습니다.");
	  } else {
		  System.out.println(title + "(은)는 이미 대여중입니다.");
		  
	  }
	}
	
	public void print() {
		String statusText = (status == 1) ? "재고있음" : "대여중";
		System.out.printf("[%d] 제목:%s, 작가:%s, 상태:%s%n", no, title, author, statusText);
	}
	
	public int getNo() {
		return no;
	}
}	

