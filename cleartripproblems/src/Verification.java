public class Verification {
	private final static String url = "http://www.cleartrip.com/signin/service?username=&pwd=hello";

	public static void main(String[] args) {
		try {

			String[] urlBreaker = url.split("\\?");
			if(urlBreaker.length>1){
				String params = urlBreaker[urlBreaker.length-1];
				String[] paramsArray = params.split("\\&");
				
				for(String paramSplitter: paramsArray){
					String[] p = paramSplitter.split("\\=");
					if(p.length == 2)	
						System.out.println(p[0]+": "+p[1]);
					else
						System.out.println(p[0]+": ");
				}
			}		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
