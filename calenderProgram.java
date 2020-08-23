package calender;

import java.util.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class calenderProgram  {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private static final Date fromString( String spec ) {
        try {
            return dateFormat.parse( spec );
        } catch( ParseException dfe ) {
            return null;
        }
    }
	
	private static final boolean datecompare( Date dat1,Date dat2) {
		Calendar cal1=  Calendar.getInstance();
		Calendar cal2=  Calendar.getInstance();
		cal1.setTime(dat1);
		cal2.setTime(dat2);
		if(cal1.get(Calendar.DAY_OF_MONTH)==cal2.get(Calendar.DAY_OF_MONTH) && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH))
		{
			return true;
		}
        
		return false;
    }
	
	public static void main(String[] args) throws IOException
	{
		Date jobInitiateDate;
		Date jobEndDate;
		SimpleDateFormat formatter =  new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal;
		Date [] dates = {
				fromString("31/12/2020"),
				fromString("01/01/2021"), 
				fromString("18/01/2021"),
			    fromString("15/02/2021"), 
			    fromString("31/05/2021"),
			    fromString("05/07/2021"), 
			    fromString("06/09/2021"),
			    fromString("11/10/2021"), 
			    fromString("11/11/2021"),
			    fromString("25/11/2021"),
			    fromString("24/12/2021"), 
			    fromString("25/12/2021"),
			    fromString("31/12/2021")
			 };
		
		String absoluteFilePath = "C:\\Users\\dhiru\\Documents\\queries.txt";
		File file = new File(absoluteFilePath);
		boolean fvar = file.createNewFile();
		if(fvar){
            System.out.println(absoluteFilePath+" File Created");
        }else System.out.println("File "+absoluteFilePath+" already exists");
		
		FileWriter fos = new FileWriter(file);
		
		
		//String str = "2020-12-30";
		 cal = Calendar.getInstance();
		cal.set(2020, Calendar.DECEMBER,31); //Year, month and day of month
		Date date = cal.getTime();
		jobInitiateDate = date;
		//jobInitiateDate = format(date);
		cal.set(2021, Calendar.JANUARY,04); //Year, month and day of month
		date = cal.getTime();
		jobEndDate =date;
		//System.out.println(jobInitiateDate+" ::: "+jobEndDate);
		
		int num1,num2;
		//String str1 ="insert into(";
		for(int i=1;i<=1;i++)
		{
			//String str2 = "Insert into('akdhsabncsjknc','"+i+"','";
			
			for(int j =1;j<=31;j++)
			{
				date = jobInitiateDate;
				int flag=0,flag1=0;
				cal.setTime(date);
				cal.add(Calendar.DAY_OF_MONTH, 1);
				date = cal.getTime();
				while(true)
				{
					num2 = cal.get(Calendar.DAY_OF_WEEK);
					if(num2==1 || num2 == 7)
					{
						cal.add(Calendar.DAY_OF_MONTH, 1);
						date = cal.getTime();
					}
					else
					{
						flag1=0;
						for(Date date1 : dates)
						{
							//System.out.println("Foreach"+date1+" ::::"+date);
							
							if(datecompare(date,date1))
							{
								cal.add(Calendar.DAY_OF_MONTH, 1);
								date = cal.getTime();
								
								flag1=1;
								//System.out.println("datecompare"+date);
								break;
							}
						}
						if(flag1==0)
							flag=1;
					}
					if(flag==1)
					break;
				}
				jobEndDate =date;
				cal.setTime(jobEndDate);
				num1= cal.get(Calendar.DAY_OF_MONTH);
			//	System.out.println(j+":::"+num1);
				long difference = jobEndDate.getTime() - jobInitiateDate.getTime();
			    float daysBetween = (difference / (1000*60*60*24));
			    int diff = (int)daysBetween;
			    //System.out.println(j+":::"+diff);
			    
					String str2 = "Insert into('akdhsabncsjknc','"+i+"','"+j+"','"+ formatter.format(jobInitiateDate)+"','"
				+formatter.format(jobEndDate)+"')\n";
					fos.write(str2);
					//System.out.println("i :"+i+",j :"+ j+"inDate=" +jobInitiateDate  +",outdate :"+jobEndDate);
					diff--;
					System.out.println(j+":::"+num1);
			    while(diff>0)
			    {
			    	j++;
			    	str2 = "Insert into('akdhsabncsjknc','"+i+"','"+j+"','"+ formatter.format(jobInitiateDate)+"','"
							+formatter.format(jobEndDate)+"')\n";
					fos.write(str2);
					//System.out.println("i :"+i+",j :"+ j+"inDate=" +jobInitiateDate  +",outdate :"+jobEndDate);
					diff--;
					System.out.println(j+":::"+num1);
					if(j>=num1)
					{
						j--;
						break;
					}
			    }
					
					
				//System.out.println("inDate=" +jobInitiateDate +",j :"+ j +",outdate :"+jobEndDate);
				jobInitiateDate = jobEndDate;
			}
			fos.flush();
			fos.close();
		}
		
	}

}
