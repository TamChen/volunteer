package edu.csust.volunteer.support;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SynchroManager {

//		将文件同步得到服务器，这个服务器可能是远端服务器，也有可能就在本地，这里就在本地。后期可改为另外一台机器
//	1：在重新启动时应该就同步一遍
//	2：在添加图片的时候应该同步到服务器端。
	public static void  synBackendAndLocal() {
		//获取本地所有图片的名称，
		//获取远端所有图片的额名称
		
	}
	public static void  synPicToService() {
		//获取本地所有图片的名称，
		//获取远端所有图片的额名称
		
	}
	public static void copy(String strPath,String strPath2 ){
		//strPath是远程路径
		if (!new File(strPath2).exists()) // 目标目录不存在时则创建目录。
		{  			
			if(new File(strPath2).mkdir()){  				
				System.out.println("文件夹 "+strPath2+" 创建成功！"); 
			}else{  	
               System.out.println("文件夹 "+strPath2+" 创建失败！请检查目标路径是否可用!");  				
               System.exit(1);//非正常退出  			
            }  		
		}  
		List < File> vf = new ArrayList < File>();  //将多个元素存在一个集合里		
		vf=getAllFileUnderDir(strPath, vf); //得到当前运行的文件的绝对目录 
        for (int i = 0; i  < vf.size(); i++) { 
			File f=vf.get(i);
			String ss = f.toString(); //源文件的文件路径
//			System.out.println(ss);
			String ss1 = (strPath2 + ss.substring(strPath.length())); //目标文件的文件路径
//			System.out.println(ss1);
            File f2 = new File(ss1);   
            if (!comparef(f, f2)) { 
				copys(f, f2);  
			} else {  		
//				System.out.println("两个文件相同！");  		
            }  
		}  		
		System.out.println("同步已完成！");  	
	}
	    // 递归
	  public static long getFileSize(File f)//取得文件夹大小
	  {
	      long size = 0;
	      File flist[] = f.listFiles();
	      for (int i = 0; i < flist.length; i++)
	      {
	          if (flist[i].isDirectory())
	          {
	              size = size + getFileSize(flist[i]);
	          } else
	          {
	              size = size + flist[i].length();
	          }
	      }
	      return size;
	  }
	   public static long getDirSize(File f){//递归求取目录中文件（包括目录）个数
	        long size = 0;
	        File flist[] = f.listFiles();
	        size=flist.length;
	        for (int i = 0; i < flist.length; i++) {
	            if (flist[i].isDirectory()) {
	                size = size + getDirSize(flist[i]);
	               
	            }
	        }
	        return size;
	    }
	   /**
		 * 功能说明: 比较strPath下的当前f文件与strPath2下的对应f2文件是否相同
		 * 文件长度和最后修改时间都相同，两文件才相同
		 * 
		 * @param f
		 * @param f2
		 * @return boolean 如返回true为两文件相同，返回false为两文件不同
		 */
		public static  boolean comparef(File f, File f2) {
	       if(f.isDirectory()&&f2.isDirectory()&&(getDirSize(f)!=getDirSize(f2)||getFileSize(f)!=getFileSize(f2)))
	                 return false;
			if (f.length() != f2.length() || f.lastModified() != f2.lastModified()) {
				return false;
			} else {
				return true;
			}
		}  
		/**
		 * 功能说明: 将strPath目录下的当前f文件复制到strPath2相应目录下
		 * 如果当前f文件是目录就重新得到当前目录下的子目录，如果是文件就直接复制
		 * @param f   
		 * @param f2
		 */
		public static void copys(File f, File f2) {
			try {
				if (f.isDirectory()) {
					f2.mkdirs();
					f2.setLastModified(f.lastModified());
					// 修改最后修改时间属性。
					String strPath = f.getPath().toString();
					String strPath2 = f2.getPath().toString();
					copy(strPath, strPath2);
				}
				if (f.isFile()) {
						FileInputStream fis = new FileInputStream(f);
						FileOutputStream fos = new FileOutputStream(f2);
						byte[] b = new byte[10240];
						int s = fis.read(b);
						while (s != -1) {
							fos.write(b, 0, s);
							s = fis.read(b);
						}
						fis.close();
						fos.close();
//						System.out.println("文件 " + f2 + " 更新成功！");
						f2.setLastModified(f.lastModified());// 修改最后修改时间属性。
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("文件创建失败！可能是磁盘已满或目标文件拒绝访问！");
			} catch (SecurityException e) {
				e.printStackTrace();
				System.out.println("文件创建失败，可能是因为没有足够的权限！");
			}
		}  
		
		/***************************************************************************
		 * 功能说明: 获得当前文件夹中的文件列表
		 * 
		 * @param strPath 当前文件夹的绝对路径 如：e:/from
		 * @param vf      保存文件目录列表的数组
		 * @author qianshu
		 **************************************************************************/
		public static List < File> getAllFileUnderDir(String strPath, List< File> vf) {
			try {
				File f = new File(strPath);
				File[] fList = f.listFiles();
				for (int j = 0; j < fList.length; j++) {		
					vf.add(fList[j]);					
				} 
				return vf;
			} catch (Exception e) {
				System.out.println("获取源目录列表失败，请检查源目录路径是否正确！");
			}
			return null;
		} 
}
