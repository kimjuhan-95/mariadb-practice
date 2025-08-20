package bookmall.vo;

public class OrderVo {
	private Long number;
	private String userName;
	private String userEmail;
	private String Status;
	private int Payment;
	private String Shipping;
	
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public int getPayment() {
		return Payment;
	}
	public void setPayment(int payment) {
		Payment = payment;
	}
	public String getShipping() {
		return Shipping;
	}
	public void setShipping(String shipping) {
		Shipping = shipping;
	}
	
	@Override
    public String toString() {
        return "OrderVo [number=" + number + ", userName=" + userName 
            + ", userEmail=" + userEmail + ", Status=" + Status + ", Payment=" + Payment
            + ", Shipping=" + Shipping + "]";
	
	

	}
	}
