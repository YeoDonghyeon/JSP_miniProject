package notice;

import org.json.JSONArray;
import org.json.JSONObject;

public class Ex01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JSONObject object1 = new JSONObject();
		JSONObject object2 = new JSONObject();
		
		object1.put("name", "김철수");
		object1.put("age", 23);
		object1.put("height", 170.3);
		
		object2.put("kor", 56);
		object2.put("eng", 23);
		object2.put("met", 73);
		
		object1.put("scores", object2);
		
		System.out.println(object1);
		
		JSONArray array = new JSONArray();
		array.put(object1);
		
		System.out.println(array);
		
//		JSONObject object = new JSONObject();
//		
//		object.put("name1", 1);
//		object.put("name2", 3.14);
//		object.put("name3", "나는야 퉁퉁이~");
//		
////		{"name": 1, "name2": 3.14, "name3": "나는야 퉁퉁이~"}
//		
//		System.out.println(object);
//		
//		JSONArray array = new JSONArray();
//		
//		array.put(1);
//		array.put(3.14);
//		array.put("나는야 퉁퉁이~");
//		
//		System.out.println(array);
	}

}
