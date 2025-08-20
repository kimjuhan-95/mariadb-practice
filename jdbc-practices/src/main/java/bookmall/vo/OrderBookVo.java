package bookmall.vo;

public class OrderBookVo {
	private Long OrderNo;
	private Long BookNo;
	private int Quantity;
	private int Price;
	
	public Long getOrderNo() {
		return OrderNo;
	}
	public void setOrderNo(Long orderNo) {
		OrderNo = orderNo;
	}
	public Long getBookNo() {
		return BookNo;
	}
	public void setBookNo(Long bookNo) {
		BookNo = bookNo;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	public int getPrice() {
		return Price;
	}
	public void setPrice(int price) {
		Price = price;
	}
	
	@Override
    public String toString() {
		return "OrderBookVo [OrderNo=" + OrderNo + ", BookNo=" + BookNo + ", Quantity=" + Quantity +", Price=" + Price + "]";
	}


}
