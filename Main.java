import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;


public class Main {
	
	public static int[] getIntArray(int size,int max){
		int a[] = new int[size];
		
		Random rand = new Random();
		
		for(int i=0; i< size; i++) {
			a[i] = rand.nextInt(max);
		}
		
		return a;
	}

	public static void main(String[] args) {
		String ping = "あ,い,う,え,お,"
					+ "か,き,く,け,こ,"
					+ "さ,し,す,せ,そ,"
					+ "た,ち,つ,て,と,"
					+ "な,に,ぬ,ね,の,"
					+ "は,ひ,ふ,へ,ほ,"
					+ "ま,み,む,め,も,"
					+ "や,ゆ,よ,"
					+ "ら,り,る,れ,ろ,"
					+ "わ,を";
		String pian = "ア,イ,ウ,エ,オ,"
					+ "カ,キ,ク,ケ,コ,"
					+ "サ,シ,ス,セ,ソ,"
					+ "タ,チ,ツ,テ,ト,"
					+ "ナ,ニ,ヌ,ネ,ノ,"
					+ "ハ,ヒ,フ,ヘ,ホ,"
					+ "マ,ミ,ム,メ,モ,"
					+ "ヤ,ユ,ヨ,"
					+ "ラ,リ,ル,レ,ロ,"
					+ "ワ,ヲ";
		String luoma = "a,i,u,e,o,"
				     + "ka,ki,ku,ke,ko,"
				     + "sa,shi,su,se,so,"
				     + "ta,chi,tsu,te,to,"
				     + "na,ni,nu,ne,no,"
				     + "ha,hi,fu,he,ho,"
				     + "ma,mi,mu,me,mo,"
				     + "ya,yu,yo,"
				     + "ra,ri,ru,re,ro,"
				     + "wa,wo";
		String all = ping +"," + pian;
		int five[] = new int[5];
		for(int i = 0; i< 5;i++) five[i] = 0;
		
		String[] pings = ping.split(",");
		String[] pians = pian.split(",");
		String[] luomas = luoma.split(",");
		String[] alls = all.split(",");
		String allLuoma = luoma +","+luoma;
		String[] allLuomas = allLuoma.split(",");
		
		int length = luomas.length;
		Random rand = new Random();
		Random rand2 = new Random();
		Scanner scanner = new Scanner(System.in);
		Map<Integer,Integer> wrong = new HashMap<Integer, Integer>();
		Map<Integer, Integer> success = new HashMap<Integer, Integer>();
		Map<Integer, Integer> allWrongCount = new HashMap<Integer, Integer>();
		int last = 0;
		int next  =0;
		int bcount = 0;
		while(true) {
			System.out.println("请输入下面假名的罗马音");
			
			String quest = "";
			String astion = "";
			int[] q = getIntArray(5, length*2);
			for(int j = 0; j< q.length; j++) {
				quest += alls[q[j]];
				astion += allLuomas[q[j]];
			}
			System.out.println(quest);
//			do{
//				next = rand.nextInt(length);
//			}while(next == last);
//			
//			/**
//			 * 如果已经学会则不再学习这个
//			 */
//			while(success.containsKey(next) && success.get(next) > 5){
//				next = rand.nextInt(length);
//			}
//			
//			/**
//			 * 每背10个需要复习一个已经学会的。
//			 */
//			if(bcount++ > 10 && success.size() > 0){
//				int i = 0;
//				do{
//					next = rand.nextInt(length);
//					i++;
//				}while(! success.containsKey(next) || (success.get(next) < 6 && i < length));
//				bcount = 0;
//			}
//			
//			String question = rand2.nextInt(2) == 1?pians[next]:pings[next] ;
//			System.out.println(question);
			String result = scanner.nextLine();
			
			if(astion.equals(result.trim())){
				System.out.println("ok,对啦");
//				Integer count = success.get(next);
//				if(count !=null && count > 5) {
//					wrong.remove(next);
//				}
//				success.put(next, count==null?1:count+1);
//				
				
			}else {
				System.out.println("sorry,错啦");
				System.out.println("答案是:"+quest+" - "+astion);
//				Integer wrongcount = wrong.get(next);
//				wrong.put(next, wrongcount == null?1:wrongcount + 1);
//				if(success.containsKey(next)) success.remove(next);
			}
//			
//			if("wrong".equals(result.trim())){
//				for(int key:wrong.keySet()){
//					System.out.println(pings[key] +" "+ pians[key] +" wrong count:" + wrong.get(key) );
//				}
//			}
//			
//			if("ok".equals(result.trim())){
//				for(int key:success.keySet()){
//					System.out.println(pings[key] +" "+ pians[key] +" ok count:" + success.get(key) );
//				}
//			}
//			
//			last = next;
//			int ok = 0;
//			for(int k:success.keySet()) {
//				if(success.get(k) >  5){
//					ok++;
//				}
//			}
//			
//			if(ok == length){
//				System.out.println("ok, 已经全部学会");
//			}
			
		}

	}

}
