package sys;
/**
  * @author  chenwf: 
  * @date 创建时间：2017年7月15日 上午9:12:10 
  * @version 1.0 
  * @parameter  
  * @return  
*/
public class TestMethod {

	public static void main(String[] args) {
		String string = "";
		for (int i = 0; i < 2; i++) {
			string += "def"+i +" - ";
			
		}
		System.out.println(string.substring(0,string.length()-3));
	}

}
