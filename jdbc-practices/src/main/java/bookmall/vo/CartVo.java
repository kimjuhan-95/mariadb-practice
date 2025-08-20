package bookmall.vo;

public class CartVo {
	private Long no;
    private Long userNo;
    private Long bookNo;
    private int quantity;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public Long getBookNo() {
		return bookNo;
	}
	public void setBookNo(Long bookNo) {
		this.bookNo = bookNo;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "CartVo [no=" + no + ", userNo=" + userNo + ", bookNo=" + bookNo + ", quantity=" + quantity + "]";
		
	}

}
