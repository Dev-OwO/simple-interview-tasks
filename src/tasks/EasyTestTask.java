package tasks;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** простые задачи */
public class EasyTestTask {
	ShapeInfo2 shapeInfo2;
	LogProcessing3 logProcessing3;
	Emplpoyee4 emplpoyee4;
	IntegerSum5 integerSum5;
	XmlTransform6 xmlTransform6;
	EmailGroup7 emailGroup7;
	CompanyProfit8 companyProfit8;
	UserLoginCreator9 userLoginCreator9;
	
	@BeforeEach
	public void createObjects() {
		shapeInfo2 = new ShapeInfo2Imp();
		logProcessing3 = new LogProcessing3Imp();
		emplpoyee4 = new Emplpoyee4Imp();
		integerSum5 = new IntegerSum5Imp();
		xmlTransform6 = new XmlTransform6Imp();
		emailGroup7 = new EmailGroup7Imp();
		companyProfit8 = new CompanyProfit8Imp();
		userLoginCreator9 = new UserLoginCreator9Imp();
	}
	
	@Test
	public void test1() {
		StatsCalculator1 sc = new StatsCalculator1();
		String r = sc.calculateStats("5 -2 0 0 7 8 -1");
		Assert.assertEquals("выше нуля: 3, ниже нуля: 2, равна нулю: 2", r);
		r = sc.calculateStats("");
		Assert.assertEquals("выше нуля: 0, ниже нуля: 0, равна нулю: 0", r);
		r = sc.calculateStats("     ");
		Assert.assertEquals("выше нуля: 0, ниже нуля: 0, равна нулю: 0", r);
	}
	
	@Test
	public void test2() {
		String r = shapeInfo2.getShapeInfo("круг 3");
		Assert.assertEquals("28.27 18.85", r);
		r = shapeInfo2.getShapeInfo("квадрат");
		Assert.assertEquals("1.00 4.00", r);
		r = shapeInfo2.getShapeInfo("круг");
		Assert.assertEquals("3.14 6.28", r);
		
		r = shapeInfo2.getShapeInfo("круг 0");
		Assert.assertEquals("0.00 0.00", r);
		r = shapeInfo2.getShapeInfo("квадрат 0");
		Assert.assertEquals("0.00 0.00", r);
		r = shapeInfo2.getShapeInfo("круг 10");
		Assert.assertEquals("314.16 62.83", r);
		r = shapeInfo2.getShapeInfo("квадрат 10");
		Assert.assertEquals("100.00 40.00", r);
		r = shapeInfo2.getShapeInfo("круг 1.5");
		Assert.assertEquals("7.07 9.42", r);
		r = shapeInfo2.getShapeInfo("квадрат 1.5");
		Assert.assertEquals("2.25 6.00", r);
		r = shapeInfo2.getShapeInfo("круг 100");
		Assert.assertEquals("31415.93 628.32", r);
		r = shapeInfo2.getShapeInfo("квадрат 100");
		Assert.assertEquals("10000.00 400.00", r);
		
		r = shapeInfo2.getShapeInfo("квадрат  ");
		Assert.assertEquals("1.00 4.00", r);
		r = shapeInfo2.getShapeInfo("круг  ");
		Assert.assertEquals("3.14 6.28", r);
	}
	
	@Test
	public void test3() {
		java.util.List<LogProcessing3> uoList = java.util.Arrays.asList(
				logProcessing3, LogProcessing3Imp::logTransform1);
		
		for(LogProcessing3 uo: uoList) {
			String r = uo.logTransform("Hello world 123");
			Assert.assertEquals("LOG_dlrow_olleH", r);
			r = uo.logTransform("abc123def");
			Assert.assertEquals("LOGfedcba", r);
			
			r = uo.logTransform("1234567890");
			Assert.assertEquals("LOG", r);
			r = uo.logTransform("abc def ghi");
			Assert.assertEquals("LOGihg_fed_cba", r);
			r = uo.logTransform("LOG");
			Assert.assertEquals("LOGGOL", r);
			r = uo.logTransform("a1b2c3d4e5f6g7h8i9j0");
			Assert.assertEquals("LOGjihgfedcba", r);
			r = uo.logTransform("1a2b3c4d5e6f7g8h9i0j");
			Assert.assertEquals("LOGjihgfedcba", r);
			r = uo.logTransform("1 2 3 4 5 6 7 8 9 0");
			Assert.assertEquals("LOG_________", r);
			r = uo.logTransform("");
			Assert.assertEquals("LOG", r);
		}
	}
	
	@Test
	public void test4() {
		String r = emplpoyee4.employeeData("Иван, 28, Инженер;Олег, 34, HR;Денис, 45, Маркетинг;Анна, 30, Инженер;Борис, 24, Логистика");
		Assert.assertEquals("24 30 45", r);
		r = emplpoyee4.employeeData("Иван, 25, Инженер;Олег, 25, HR;Денис, 25, Маркетинг");
		Assert.assertEquals("25 25 25", r);
		r = emplpoyee4.employeeData("Иван, 20, Инженер;Олег, 30, HR;Денис, 40, Маркетинг;Анна, 50, Инженер;Борис, 60, Логистика");
		Assert.assertEquals("20 40 60", r);
		r = emplpoyee4.employeeData("Иван, 20, Инженер;Олег, 30, HR;Денис, 40, Маркетинг;Анна, 50, Инженер;Борис, 60, Логистика; Карина, 25, Красотка");
		Assert.assertEquals("20 35 60", r);
		r = emplpoyee4.employeeData("Иван, 18, Инженер;Олег, 19, HR;Денис, 20, Маркетинг;Анна, 21, Инженер;Борис, 22, Логистика");
		Assert.assertEquals("18 20 22", r);
		r = emplpoyee4.employeeData("Иван, 100, Инженер;Олег, 100, HR;Денис, 100, Маркетинг");
		Assert.assertEquals("100 100 100", r);
		r = emplpoyee4.employeeData("Иван, 25, Инженер");
		Assert.assertEquals("25 25 25", r);
	}
	
	@Test
	public void test5() {
		int result = integerSum5.getSum("2", "5 6");
		Assert.assertEquals(11, result);
		result = integerSum5.getSum("4", "1 2 3 4");
		Assert.assertEquals(10, result);
		result = integerSum5.getSum("1", "1");
		Assert.assertEquals(1, result);
		result = integerSum5.getSum("4", "-1 2 -3 4");
		Assert.assertEquals(2, result);
		result = integerSum5.getSum("100", "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 61 62 63 64 65 66 67 68 69 70 71 72 73 74 75 76 77 78 79 80 81 82 83 84 85 86 87 88 89 90 91 92 93 94 95 96 97 98 99 100");
		Assert.assertEquals(5050, result);
		result = integerSum5.getSum("3", "1 2 3 4");
		Assert.assertEquals(6, result);
		result = integerSum5.getSum("10", "1 1 1 1 1 1 1 1 1 1");
		Assert.assertEquals(10, result);
	}
	
	@Test
	public void test6() {
		String test = "<projects>\n"
				+ "    <project name=\"xml\">\n"
				+ "        <member role=\"developer\" name=\"Fedya\"/>\n"
				+ "        <member role=\"manager\" name=\"Ivan\"/>\n"
				+ "        <member role=\"manager\" name=\"Fedya\"/>\n"
				+ "    </project>\r\n"
				+ "</projects>";
		String expected = "<members>\n"
				+ "    <member name=\"Fedya\">\n"
				+ "        <role project=\"xml\" name=\"developer\"/>\n"
				+ "        <role project=\"xml\" name=\"manager\"/>\n"
				+ "    </member>\n"
				+ "    <member name=\"Ivan\">\n"
				+ "        <role project=\"xml\" name=\"manager\"/>\n"
				+ "    </member>\n"
				+ "</members>";
		String result = xmlTransform6.transformXml(test);
		Assert.assertEquals(expected, result);
		
		test = """
<projects>
    <project name="xml">
        <member role="developer" name="Fedya"/>
        <member role="manager" name="Ivan"/>
        <member role="manager" name="Fedya"/>
    </project>
    <project name="rpc">
        <member role="developer" name="Fedya"/>
    </project>
</projects>""";
		expected = """
<members>
    <member name="Fedya">
        <role project="rpc" name="developer"/>
        <role project="xml" name="developer"/>
        <role project="xml" name="manager"/>
    </member>
    <member name="Ivan">
        <role project="xml" name="manager"/>
    </member>
</members>""";
		result = xmlTransform6.transformXml(test);
		Assert.assertEquals(expected, result);
	}
	
	@Test
	public void test7() {
		String[] result = emailGroup7.groupEmails("6", "DEVELOPER@gmail.com", 
				"t+es+ter@gmail.com", 
				"T@mail.com", 
				"a@mail.com.ru", 
				"D.eveloper@gmail.com", 
				"a+b@gmail.com.ru");
		String[] er = {"5", "2 DEVELOPER@gmail.com D.eveloper@gmail.com", "1 t+es+ter@gmail.com",
				"1 T@mail.com", "1 a@mail.com.ru", "1 a+b@gmail.com.ru"};
		Assert.assertArrayEquals(er, result);
	}
	
	@Test
	public void test8() {
		int r = companyProfit8.getProfitWithDiscount("1000", "0");
		Assert.assertEquals(1000, r);
		r = companyProfit8.getProfitWithDiscount("1000,2000,3000,4000,5000,6000,7000,8000",
				"0,5,10,15,20,25,0,5");
		Assert.assertEquals(32100, r);
		r = companyProfit8.getProfitWithDiscount("10007,20003,30009,40001,50007,60004,70005,80008,90002,100000",
				"1,2,3,4,5,6,7,8,9,10");
		Assert.assertEquals(511545, r);
	}
	
	@Test
	public void test9() {
		String[] r = userLoginCreator9.createLogins("John,Michael,Elizabeth", "Johnson,Williams,Anderson");
		String[] e = {"jOhSnH","mIcIlL","eLiReD"};
		Assert.assertArrayEquals(e, r);
		r = userLoginCreator9.createLogins("Ale,Vic", "Pesko,Skaya");
		e = new String[] {"aLeOkS", "vIcAyA"};
		Assert.assertArrayEquals(e, r);
	}
}

class StatsCalculator1 {
	public String calculateStats(String input) {
		int[] tempArray;
		if(input == null || input.trim().isEmpty()) {
			tempArray = new int[0];
		} else {
			String[] inputArray = input.trim().split(" ");
			tempArray = java.util.stream.Stream.of(inputArray)
					.flatMapToInt(x -> {
						try {
							return java.util.stream.IntStream.of(Integer.parseInt(x));
						} catch(Exception exc) {
							return java.util.stream.IntStream.empty();
						}
					})
					.toArray();
//			tempArray = Stream.of(inputArray)
//					.mapToInt(Integer::parseInt).toArray();
		}
		
		int numPlus = 0;
		int numZero = 0;
		int numMinus = 0;
		for(int t: tempArray) {
			if(t > 0)
				numPlus++;
			else if(t == 0)
				numZero++;
			else if(t < 0)
				numMinus++;
		}
		
		return String.format("выше нуля: %d, ниже нуля: %d, равна нулю: %d",
				numPlus, numZero, numMinus);
	}
}

interface ShapeInfo2 {
	public String getShapeInfo(String shape);
}

class ShapeInfo2Imp implements ShapeInfo2 {
	
	public String getShapeInfo(String shape) {
		double[] sp = getInfo(shape);
		
		return String.format(java.util.Locale.US, "%.2f %.2f", sp[0], sp[1]);
	}
	
	public double[] getInfo(String shape) {
		if(shape == null || shape.trim().isEmpty())
			return new double[] {0, 0};
		
		double s = 0;
		double p = 0;
		String[] shapes = shape.trim().split(" ");
		double side = shapes.length > 1 ? Double.parseDouble(shapes[1]) : 1;
		if(shapes[0].equals("квадрат")) {
			s = side * side;
			p = side * 4;
		} else if(shapes[0].equals("круг")) {
			s = side * side * Math.PI;
			p = 2 * side * Math.PI;
		}
		return new double[] {s, p};
	}
}

interface LogProcessing3 {
	public String logTransform(String line);
}

class LogProcessing3Imp implements LogProcessing3 {
	
	public String logTransform(String line) {
		line = line.replaceAll("\\d", "");
		line = line.replaceAll(" ", "_");
		StringBuilder sb = new StringBuilder(line);
		line = sb.reverse().toString();
		return "LOG" + line;
	}
	
	public static String logTransform1(String line) {
		String newLine = "";
		for(int i=line.length()-1; i>=0; i--) {
			char ch = line.charAt(i);
			if(Character.isDigit(ch))
				continue;
			if(ch == ' ')
				ch = '_';
			newLine += ch;
		}
		return "LOG" + newLine;
	}
}

interface Emplpoyee4 {
	public String employeeData(String employees);
}

class Emplpoyee4Imp implements Emplpoyee4 {
	
	public String employeeData(String employees) {
		java.util.List<Integer> yl = new java.util.LinkedList<>();
		for(String e: employees.split(";")) {
			String y = e.trim().split(", ")[1];
			yl.add(Integer.valueOf(y));
		}
		java.util.Collections.sort(yl);
		int s = yl.size() / 2;
		int avg = yl.get(s);
		if(yl.size() % 2 == 0)
			avg = (avg + yl.get(s-1)) / 2;
		
		return yl.get(0) + " " + avg + " " + yl.get(yl.size()-1);
	}
}

interface IntegerSum5 {
	public int getSum(String n, String ints);
}

class IntegerSum5Imp implements IntegerSum5 {
	
	public int getSum(String n, String ints) {
		int ni = 0;
		try {
			ni = Integer.parseInt(n.trim());
		} catch(NumberFormatException exc) {}
		
		int sum = 0;
		for(String is: ints.split(" ")) {
			try {
				int i = Integer.parseInt(is.trim());
				if(ni > 0) {
					sum += i;
					ni--;
				}
			} catch(NumberFormatException exc) {}
		}
		return sum;
	}
}

interface XmlTransform6 {
	public String transformXml(String xml);
}

class XmlTransform6Imp implements XmlTransform6 {
	
	public String transformXml(String xml) {
		String project = null;
		java.util.Map<String,Set<MemberData>> memberMap = new java.util.TreeMap<>();
		for(String x: xml.split("\n")) {
			if(x.contains("</"))
				continue;
			if(x.contains("projects"))
				continue;
			if(x.contains("project"))
				project = getValue(x, "name");
			if(x.contains("member")) {
				MemberData md = new MemberData();
				md.project = project;
				md.role = getValue(x, "role");
				String name = getValue(x, "name");
				
				if(!memberMap.containsKey(name))
					memberMap.put(name, new TreeSet<>());
				Set<MemberData> mdl = memberMap.get(name);
				mdl.add(md);
			}
		}
		
		java.util.List<String> rList = new java.util.LinkedList<>();
		rList.add("<members>");
		for(String name: memberMap.keySet()) {
			String m = "<member ";
			m += "name=\"" + name + "\">";
			rList.add("    " + m);
			for(MemberData md: memberMap.get(name)) {
				String r = "<role ";
				r += "project=\"" + md.project + "\" ";
				r += "name=\"" + md.role + "\"/>";
				rList.add("        " + r);
			}
			rList.add("    </member>");
		}
		rList.add("</members>");
		return String.join("\n", rList);
	}
	
	private static String getValue(String s, String tag) {
		tag += "=\"";
		int i1 = s.indexOf(tag)+tag.length();
		return s.substring(i1, s.indexOf("\"", i1));
	}
	
	static class MemberData implements Comparable<MemberData> {
		String project;
		String role;
		
		@Override
		public int compareTo(MemberData o) {
			int c = project.compareTo(o.project);
			if(c != 0)
				return c;
			return role.compareTo(o.role);
		}
	}
}

interface EmailGroup7 {
	public String[] groupEmails(String n, String ... emails);
}

class EmailGroup7Imp implements EmailGroup7 {
	
	public String[] groupEmails(String n, String ... emails) {
		java.util.Map<String, java.util.List<String>> emailMap = new java.util.LinkedHashMap<>();
		for(String e: emails) {
			String key = e.toLowerCase();
			String login = key.split("@")[0];
			String domain = key.split("@")[1];
			if(domain.equals("gmail.com")) {
				if(login.indexOf('+') > -1)
					login = login.substring(0, login.indexOf('+'));
				login = login.replaceAll("\\.", "");
				key = login + "@" + domain;
			}
			if(!emailMap.containsKey(key))
				emailMap.put(key, new java.util.LinkedList<>());
			java.util.List<String> eList = emailMap.get(key);
			eList.add(e);
		}
		java.util.List<String> rList = emailMap.values().stream()
				.map(l -> l.size() + " " + String.join(" ", l)).collect(Collectors.toList());
		rList.add(0, String.valueOf(emailMap.size()));
		return rList.toArray(new String[0]);
	}
}

interface CompanyProfit8 {
	int getProfitWithDiscount(String payments, String discounts);
}

class CompanyProfit8Imp implements CompanyProfit8 {
	public int getProfitWithDiscount(String payments, String discounts) {
		String[] pa = payments.split(",");
		String[] da = discounts.split(",");
		int sum = 0;
		for(int i=0; i<pa.length; i++) {
			int p = Integer.valueOf(pa[i]);
			int d = Integer.valueOf(da[i]);
			sum += Math.round((p * (100d - d)) / 100);
		}
		return sum;
	}
}

interface UserLoginCreator9 {
	String[] createLogins(String names, String surnames);
}

class UserLoginCreator9Imp implements UserLoginCreator9 {
	public String[] createLogins(String names, String surnames) {
		String[] na = names.split(",");
		String[] sa = surnames.split(",");
		String [] r = new String[na.length];
		for(int i=0; i<na.length; i++) {
			String l1 = na[i].substring(0,3).toLowerCase();
			String l2 = sa[i].substring(2,5).toLowerCase();
			StringBuilder sb = new StringBuilder(l2);
			l2 = sb.reverse().toString();
			String l = l1 + l2;
			String lr = "";
			for(int j=0; j<l.length(); j++) {
				if(j % 2 == 0)
					lr += l.charAt(j);
				else
					lr += Character.toUpperCase(l.charAt(j));
			}
			r[i] = lr;
		}
		return r;
	}
}
