package crispy_octo_moo.dto;

public class JsonObject {
	public JsonObject(Object data) {
		this.data = data;
	}

	// Setter,getters
	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
