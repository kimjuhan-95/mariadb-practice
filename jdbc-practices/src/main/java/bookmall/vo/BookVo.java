package bookmall.vo;

public class BookVo {
	private Long no;
	private String title;
	private int price;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
    public String toString() {
		return "BookVo [no=" + no + ", title=" + title + ", price=" + price + "]";
	}

}
