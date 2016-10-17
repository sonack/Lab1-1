package exp1;

import java.util.HashMap;

public class singleTerm {
	private int parameter;
	private HashMap<Character, Integer> var = new HashMap<>();
	
	public singleTerm(String product) {
		parameter = 1;
		String multiplier[] = product.split("\\*");
		for(int i = 0; i < multiplier.length; i++)
		{
			if(multiplier[i].charAt(0) >= 48 && multiplier[i].charAt(0) <= 57) 	 //�������Ϊ����
				parameter *= Integer.parseInt(multiplier[i]);
			else
			{
				String s[] = multiplier[i].split("\\^");                         //�������Ϊ����ĸ      
				char varName = s[0].charAt(0);   									//������
				int power = s.length > 1 ? Integer.parseInt(s[1]) : 1;				//����
				var.put(varName, var.containsKey(varName) ? power + var.get(varName) : power);	
			}		
		}
	}
	
	@Override
	public String toString()
	{
		String s = "";
		s += Integer.toString(parameter);
		for(Character c : var.keySet())
		{
			s += "*";
			if(var.get(c) == 1)
				s += Character.toString(c);
			else
			{
				s += Character.toString(c);
				s += "^";
				s += Integer.toString(var.get(c));
			}
		}
		if(s.toString().startsWith("1*"))
			return s.substring(2);
		return s;
	}
	
	public void simplify(char varName, int value)
	{
		if(var.containsKey(varName))
		{
			parameter *= Math.pow(value, var.get(varName));
			var.remove(varName);
		}
	}
	
	public void derivative(char varName)
	{
		if(var.containsKey(varName)){
			int power = var.get(varName);
			parameter *= power;
			var.put(varName, power - 1);
			if(var.get(varName) == 0)
				var.remove(varName);
		}
		else{
			parameter = 0;
		}
	}
}
