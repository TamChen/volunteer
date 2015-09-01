package edu.csust.volunteer.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
 
import javax.servlet.ServletOutputStream;


public class FileOperateUtil {
    public static String FILEDIR = null;
    /**
     * 上传
     * @param request
     * @throws IOException
     */

    
	public static void deleteFile(String downloadfFileName) {
    	File file=new File(FILEDIR + "/" + downloadfFileName);
    	file.delete();
    }
    
    public static void download(String downloadfFileName, ServletOutputStream out) {
        try {
            FileInputStream in = new FileInputStream(new File(FILEDIR + "/" + downloadfFileName));
            write(in, out);
        } catch (FileNotFoundException e) {
            try {
                FileInputStream in = new FileInputStream(new File(FILEDIR + "/"
                        + new String(downloadfFileName.getBytes("iso-8859-1"),"utf-8")));
                write(in, out);
            } catch (IOException e1) {              
                e1.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }       
    }
    /**
     * 写入数据
     * @param in
     * @param out
     * @throws IOException
     */
    public static void write(InputStream in, OutputStream out) throws IOException{
        try{
            byte[] buffer = new byte[1024];
            int bytesRead = -1;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            out.flush();
        } finally {
            try {
                in.close();
            }
            catch (IOException ex) {
            }
            try {
                out.close();
            }
            catch (IOException ex) {
            }
        }
    }  	
}
