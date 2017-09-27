package rest.nasa.neows.domain;

public class Page {

	int size;
	long total_elements;
	long total_pages;
	int number;
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public long getTotal_elements() {
		return total_elements;
	}
	public void setTotal_elements(long total_elements) {
		this.total_elements = total_elements;
	}
	public long getTotal_pages() {
		return total_pages;
	}
	public void setTotal_pages(long total_pages) {
		this.total_pages = total_pages;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
}
