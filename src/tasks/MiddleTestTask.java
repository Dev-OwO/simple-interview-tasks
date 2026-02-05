package tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** средние задачи, с регулярными выражениями */
public class MiddleTestTask {
	ProcessingFreelancers processingFreelancers;
	ValidUsers validUsers;
	
	@BeforeEach
	public void createObjects() {
		processingFreelancers = new ProcessingFreelancersImp2();
		validUsers = new ValidUsersImp();
	}
	
	@Test
	public void test1() {
		test1_1("""
				Smith;1985;USA;AB123
				Johnson;1990;Canada;CD456
				Williams;1978;USA;EF789
				Brown;1995;UK;GH012
				Davis;1982;Canada;IJ345
				""",
				"""
				Canada:2
				UK:1
				USA:1
				""");
		
		test1_1("""
				Smith;1950;USA;AB123
				Johnson;1990;C;CD456
				Williams;1978;none;EF789
				Brown;1995;UK;GH0123
				Davis;1982;Canada;IJ34
				""",
				"""
				none:0
				""");
		test1_1(
				"""
				VanDerVliet;1986;Belgium;GH456
				DeVeeschauwer;1972;France;IJ789
				VanHerrewege;1999;Netherlands;KL012
				Verhasselt;1965;Germany;MN345
				VanDamme;1981;USA;OP678
				DeRycke;1975;Spain;QR901
				VanDeWalle;1992;Italy;ST234
				Verstichelen;2003;Portugal;UV567
				VanDerSmissen;1971;Sweden;WX890
				DeBrabander;1984;Norway;YZ123
				""",
				"""
				Belgium:1
				Germany:1
				Italy:1
				Netherlands:1
				Norway:1
				Portugal:1
				""");
		
		test1_1("""
				Janssens;1985;Belgium;AB123
				""",
				"""
				Belgium:1
				""");
		test1_1("""
				VanHaeche;1995;Liechtenstein;CD456
				Verhoeven;1973;SanMarino;EF789
				VanDerMeeren;1986;Monaco;GH012
				DeBreyne;1998;VaticanCity;IJ345
				VanHove;1974;Iceland;KL678
				Verhast;1981;Malta;MN901
				VanDenAbeele;1996;Cyprus;OP234
				DeGroof;2001;Andorra;QR567
				VanDerLinden;1977;Liechtenstein;ST890
				Verstraete;1984;SanMarino;UV123
				""",
				"""
				Andorra:1
				Cyprus:1
				Liechtenstein:1
				Monaco:1
				SanMarino:1
				VaticanCity:1
				""");
	}
	
	private void test1_1(String input, String result) {
		List<String> t = Arrays.asList(
				input.split("\n"));
		Iterator<CountryFreelancer> r = processingFreelancers.generateFreelancersReport(t);
		List<String> cr = Arrays.asList(
				result.split("\n"));
		List<String> rl = StreamSupport.stream(Spliterators.spliteratorUnknownSize(r, Spliterator.ORDERED), false)
				.map(x -> x.toString()).collect(Collectors.toList());
		Assert.assertEquals(cr, rl);
	}
	
	@Test
	public void test2() {
		test2_2("""
				99999:Иванов:Разработка:Программист:1985
				10009:Петров:Маркетинг:Аналитик:1990
				87826:Сидоров:Бухгалтерия:Бухгалтер:1980
				""",
				"""
				Иванов (Разработка - Программист - 1985)
				Петров (Маркетинг - Аналитик - 1990)
				Сидоров (Бухгалтерия - Бухгалтер - 1980)
				""");
		test2_2("""
				10001:Петров:Разработка:Программист:1985
				1000:Иванов:Маркетинг:Аналитик:1990
				10003:Сидоров:Бухгалтерия:Бухгалтер:1934
				""",
				"""
				Петров (Разработка - Программист - 1985)
				ошибка в поле ID
				ошибка в поле ГодРождения
				""");
		test2_2("""
				10001:Иванов:Разработка:Программист:1950
				10002:Петров:Маркетинг:Аналитик:2025
				10003:Сидоров:Бухгалтерия:Бухгалтер:abcd
				10004:Кузнецов:Продажи:Менеджер:3000
				10005:Морозов:Логистика:Логист:0
				10006:Новиков:IT:Тестировщик:1800
				10007:Волков:Аналитика:Аналитик:9999
				10008:Зайцев:HR:Рекрутер:две_тысячи
				""",
				"""
				ошибка в поле ГодРождения
				ошибка в поле ГодРождения
				ошибка в поле ГодРождения
				ошибка в поле ГодРождения
				ошибка в поле ГодРождения
				ошибка в поле Отдел
				ошибка в поле ГодРождения
				ошибка в поле Отдел
				""");
		test2_2("""
				10001:Петров:Разработка:Инженер:2007
				""",
				"""
				ошибка в поле ГодРождения
				""");
		test2_2("""
				99999:Иванов:Разработка:Программист:1985
				10009:Петров:Маркетинг:Аналитик:1990
				87826:Сидоров:Бухгалтерия:Бухгалтер:1980
				""",
				"""
				Иванов (Разработка - Программист - 1985)
				Петров (Маркетинг - Аналитик - 1990)
				Сидоров (Бухгалтерия - Бухгалтер - 1980)
				""");
	}
	
	private void test2_2(String input, String result) {
		List<String> t = Arrays.asList(
				input.split("\n"));
		List<String> r = validUsers.processUsers(t);
		List<String> cr = Arrays.asList(
				result.split("\n"));
		Assert.assertEquals(cr, r);
	}

}

// Финансеры со всего света
interface ProcessingFreelancers {
	boolean validateFreelancer(String data, Wrapper<String[]> wrap);
	
	public Iterator<CountryFreelancer> generateFreelancersReport(List<String> inputLines);
}

class Wrapper<T> {
	T d;
}

class CountryFreelancer implements Comparable<CountryFreelancer> {
	String s;
	int c;
	
	CountryFreelancer(String s, int c) {
		this.s = s;
		this.c = c;
	}
	
	@Override
	public int compareTo(CountryFreelancer o) {
		if(c - o.c != 0)
			return o.c - c;
		return s.compareTo(o.s);
	}
	
	@Override
	public String toString() {
		return s + ":" + c;
	}
}

class ProcessingFreelancersImp implements ProcessingFreelancers {

	@Override
	public boolean validateFreelancer(String data, Wrapper<String[]> wrap) {
		String[] d = data.split(";");
		String f = d[0].trim().toLowerCase();
		if(f.length() < 1 || f.length() > 40)
			return false;
		for(char c: f.toCharArray()) {
			if(c < 'a' || c > 'z')
				return false;
		}
		try {
			int y = Integer.valueOf(d[1].trim());
			if(y < 1960 || y > 2007)
				return false;
		} catch(Exception e) {
			return false;
		}
		String c = d[2].trim().toLowerCase();
		if(c.length() < 2 || c.length() > 40)
			return false;
		for(char cc: c.toCharArray()) {
			if(cc < 'a' || cc > 'z')
				return false;
		}
		if(c.equals("none"))
			return false;
		String i = d[3].trim();
		if(i.length() != 5)
			return false;
		for(int ii=0; ii<i.length(); ii++) {
			char cc = i.charAt(ii);
			if(ii == 0 || ii == 1) {
				if(cc < 'A' || cc > 'Z')
					return false;
			} else {
				if(cc < '0' || cc > '7')
					return false;
			}
		}
		wrap.d = d;
		return true;
	}

	@Override
	public Iterator<CountryFreelancer> generateFreelancersReport(List<String> inputLines) {
		List<String[]> dList = new LinkedList<>();
		for(String il: inputLines) {
			Wrapper<String[]> w = new Wrapper<>();
			if(!validateFreelancer(il, w))
				continue;
			dList.add(w.d);
		}
		
		Map<String, Integer> ccm = new HashMap<>();
		for(String[] d: dList) {
			ccm.put(d[2], ccm.getOrDefault(d[2], 0) + 1);
		}
		List<CountryFreelancer> cfl = new ArrayList<>();
		for(String s: ccm.keySet()) {
			CountryFreelancer cfi = new CountryFreelancer(s, ccm.get(s));
			cfl.add(cfi);
		}
		if(cfl.isEmpty()) {
			CountryFreelancer cfi = new CountryFreelancer("none", 0);
			cfl.add(cfi);
		}
			
		Collections.sort(cfl);
		return cfl.iterator();
	}
}

class ProcessingFreelancersImp2 implements ProcessingFreelancers {
	
	private final Pattern fp = Pattern.compile("^[a-zA-Z]{1,40}$");
	private final Pattern cp = Pattern.compile("^[a-zA-Z]{2,40}$");
	private final Pattern ip = Pattern.compile("^[A-Z]{2}[0-7]{3}$");

	@Override
	public boolean validateFreelancer(String data, Wrapper<String[]> wrap) {
		String[] d = data.split(";");
		wrap.d = d;
		
		String f = d[0].trim();
		if(!fp.matcher(f).matches())
			return false;
		try {
			int y = Integer.valueOf(d[1].trim());
			if(y < 1960 || y > 2007)
				return false;
		} catch(Exception e) {
			return false;
		}
		String c = d[2].trim();
		if(!cp.matcher(c).matches() && !c.equals("none"))
			return false;
		String i = d[3].trim();
		if(!ip.matcher(i).matches())
			return false;
		return true;
	}

	@Override
	public Iterator<CountryFreelancer> generateFreelancersReport(List<String> inputLines) {
		List<String[]> dList = new LinkedList<>();
		for(String il: inputLines) {
			Wrapper<String[]> w = new Wrapper<>();
			if(!validateFreelancer(il, w))
				continue;
			dList.add(w.d);
		}
		
		Map<String,Integer> ccm = dList.stream().collect(Collectors.groupingBy(
				x -> x[2], Collectors.summingInt(x -> 1)));
		List<CountryFreelancer> rl = ccm.entrySet().stream()
				.map(x -> new CountryFreelancer(x.getKey(), x.getValue()))
				.sorted().toList();
		if(rl.isEmpty())
			rl = Collections.singletonList(new CountryFreelancer("none", 0));
		return rl.iterator();
	}
}

// Валидация кандидатов
interface ValidUsers {
	public List<String> processUsers(List<String> inputLines);
}

class ValidUsersImp implements ValidUsers {

	@Override
	public List<String> processUsers(List<String> inputLines) {
		List<String> ol = new LinkedList<>();
		for(String i: inputLines) {
			String[] ia = i.split(":");
			User u = new User();
			u.id = ia[0];
			u.ser = ia[1];
			u.dep = ia[2];
			u.pos = ia[3];
			u.bday = ia[4];
			
			checkUser(u);
			
			if(u.m != null)
				ol.add(u.m);
			else
				ol.add(String.format("%s (%s - %s - %s)", u.ser, u.dep, u.pos, u.bday));
		}
		return ol;
	}
	
	private void checkUser(User u) {
		String pid = "[1-9]{1}\\d{4}";
		String pfam = "[а-яА-ЯёЁ]+";
		String ppos = ".{4,15}";
		
		if(!u.id.isEmpty() && !u.id.matches(pid))
			u.m = "ошибка в поле ID";
		else if(u.ser.isEmpty() || !u.ser.matches(pfam))
			u.m = "ошибка в поле Фамилия";
		else if(u.pos.isEmpty() || !u.pos.matches(ppos))
			u.m = "ошибка в поле Должность";
		else if(u.dep.isEmpty() || !u.dep.matches(ppos))
			u.m = "ошибка в поле Отдел";
		else if(!u.bday.isEmpty()) {
			try {
				int b = Integer.parseInt(u.bday);
				if(b < 1960 || b >= 2007)
					u.m = "ошибка в поле ГодРождения";
			} catch(Exception e) {
				u.m = "ошибка в поле ГодРождения";
			}
		}
	}
	
}

class User {
	String id;
	String ser;
	String pos;
	String dep;
	String bday;
	
	String m;
}

// Вид доставки товаров
interface ProcessingSellers {
	public List<String> printSellersByType(List<String> inputLines);
}

interface IWriteSellerProfile {};

interface DAFSeller extends IWriteSellerProfile {};

interface DDPSeller extends IWriteSellerProfile {};

class DAFSellerImp implements DAFSeller {
	String p;
	String[] t;
	
	public String toString() {
		return String.format("{\"ID\": \"%s\",\"goods\":[\"%s\"]}", p, String.join("\",\"", t));
	}
}

