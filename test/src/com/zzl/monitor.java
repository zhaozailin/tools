package com.zzl;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


public class monitor {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		Runtime r = Runtime.getRuntime();
		Process p = r.exec(args[0].replace("\\", "/"));
		File file = null;
		if(args.length == 1) {
			file = new File("c:" + File.separator + "记录.txt");
		} else {
			args[1] = args[1].replace("\\", "/");
			file = new File(args[1]);
		}
		if(!file.exists()) {
			file.createNewFile();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		PrintWriter pw = new PrintWriter(new FileWriter(file, true));
		pw.print("开始时间：" + sdf.format(new Date()) + "   ");
		long start = System.currentTimeMillis();
		p.waitFor();
		pw.print("结束时间：" + sdf.format(new Date()) + "   ");
		long end = System.currentTimeMillis();
		pw.println("总共运行时间：" + formatLongToTimeStr(end - start));
		pw.close();
	}
	
	 private static String formatLongToTimeStr(Long l) {
        int hour = 0;
        int minute = 0;
        int second = 0;
        second = l.intValue() / 1000;
        if (second > 60) {
            minute = second / 60;
            second = second % 60;
        }
        if (minute > 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        return (getTwoLength(hour) + "时" + getTwoLength(minute)  + "分"  + getTwoLength(second) + "秒");
	 }
	 
	 private static String getTwoLength(final int data) {
        if(data < 10) {
            return "0" + data;
        } else {
            return "" + data;
        }
	 }
}
