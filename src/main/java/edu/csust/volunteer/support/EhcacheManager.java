package edu.csust.volunteer.support;
import java.net.URL;  

import org.apache.log4j.Logger;

import antlr.Utils;

import net.sf.ehcache.Cache;  
import net.sf.ehcache.CacheManager;  
import net.sf.ehcache.Element;  
import net.sf.ehcache.Status;  
public class EhcacheManager {  
	private static final Logger LOGGER = Logger.getLogger(EhcacheManager.class);
    public static net.sf.ehcache.CacheManager cacheManager = null;  
    private static String configPath="ehcache/ehcache_volunteer.xml";//配置文件  
    //缓存定义  
    //这里用ehcache作为第一级缓存，hibernate的二级缓存作为二级缓存;当改变时应该刷新缓存
    private static String CACHE_MYCOMMONCACHE="myCommonCache";//定义文件中配置的缓存  
    //model对象，在这里ehcache相当于作为一个dao层
    public static String CACHE_ACTIVITYCACHE="activityCache";//活动
    public static String CACHE_ALBUMCACHE="albumCache";//相册
    public static String CACHE_ATTENTIONCACHE="attentionCache";//关注
    public static String CACHE_INFOCACHE="INFOCache";//新闻
    public static String CACHE_NOTICECACHE="NOTICECache";//关注
    public static String CACHE_PICTURECACHE="PICTURECache";//照片
    public static String CACHE_STATISTICSCACHE="STATISTICSCache";//访问量
    public static String CACHE_USERCACHE="UserCache";//用户信息
    public static String CACHE_USERACTIVITYCACHE="USERACTIVITYCache";//用户活动
    public static String CACHE_USERDIARYCACHE="USERDIARYCache";//用户日志

      
      
    //实例化cacheManager，单例模式  
    public static CacheManager getCacheManagerInstance(){  
        if (cacheManager == null) {
        	ClassLoader cl = Thread.currentThread().getContextClassLoader();
        	System.out.println(cl.getResource(configPath).getPath());
        	/*String path=EhcacheManager.class.getClass().getResource(configPath).getPath();
        	System.out.println(path);*/
            cacheManager = CacheManager.newInstance(cl.getResource(configPath).getPath());
        }  
        return cacheManager;  
    }  
    public static net.sf.ehcache.CacheManager getCacheManager() {  
        return getCacheManagerInstance();//单例缓存管理  
    }  
      
    public static void setCacheManager(net.sf.ehcache.CacheManager cacheManager) {  
        EhcacheManager.cacheManager = cacheManager;  
    }  
      
    //添加新缓存  
    public static void addCacheByName(String cacheName){  
        if(cacheName==null||cacheName.trim().equals("")){  
            System.out.println("cacheName is null");  
        }else{  
            if(getCacheManager().getCache(cacheName.trim())!=null){  
                getCacheManager().removeCache(cacheName.trim());  
            }  
            getCacheManager().addCache(cacheName.trim());  
            System.out.println(cacheName+ "重新添加");  
        }  
    }  
    //得到cache对象  
    public static Cache getCacheByName(String cacheName){  
        Cache cache=null;  
        if(cacheName==null||cacheName.trim().equals("")){  
            System.out.println("cacheName is null");  
        }else{  
            if(getCacheManager().getCache(cacheName.trim())!=null){  
                cache=getCacheManager().getCache(cacheName.trim());  
            }  
        }  
          
        return cache;  
    }  
      
      
    //往缓存中添加元素  
    public static void putElementToCache(String cacheName,String elementKey,Object elementValue){  
        Cache cache=null;  
        if(cacheName==null||cacheName.trim().equals("")){  
            System.out.println("添加缓存元素失败，cacheName is null");  
        }else if(elementKey==null||elementValue==null){  
            System.out.println("添加缓存元素失败，elementKey or elementValue is null");  
        }else{  
            if(getCacheByName(cacheName.trim())!=null){//缓存存在  
                cache=getCacheByName(cacheName.trim());  
            }else{//缓存不存在  
                addCacheByName(cacheName.trim());  
                cache=getCacheByName(cacheName.trim());  
            }  
            //对cache对象添加Element  
            Element element=null;  
            if(cache.get(elementKey.trim())!=null){  
                cache.remove(elementKey.trim());  
            }  
            element=new Element(elementKey.trim(),elementValue);  
            cache.put(element);  
            System.out.println("添加缓存元素:"+elementKey+"成功！");  
        }  
          
    }  
      
    //从缓存中获取指定key的值  
    public static Object getElementValueFromCache(String cacheName,String elementKey){  
        Object result=null;  
        Cache cache=null;  
        if(cacheName==null||cacheName.trim().equals("")){  
            System.out.println("获取缓存元素失败，cacheName is null");  
        }else if(elementKey==null){  
            System.out.println("获取缓存元素失败，elementKey  is null");  
        }else{  
            if(getCacheByName(cacheName.trim())!=null){//缓存存在  
                cache=getCacheByName(cacheName.trim());  
                  
                Element element=null;  
                if(cache.get(elementKey.trim())!=null){  
                    element=cache.get(elementKey.trim());  
                    if(element.getObjectValue()==null){  
                        System.out.println("缓存中"+elementKey+" 的值为空.");  
                    }else{  
                        result=element.getObjectValue();  
                    }  
                }else{  
                    System.out.println("缓存中"+elementKey+" 的Element值为空.");  
                }  
            }else{//缓存不存在  
                System.out.println("获取缓存元素失败，缓存"+cacheName+" 为空.");  
            }  
        }  
          
        return result;  
    }  
      
    /** 
     * 把所有cache中的内容删除，但是cache对象还是保留. 
     * Clears the contents of all caches in the CacheManager, 
     *  but without removing any caches. 
     */  
    public static void clearAllFromCacheManager(){  
        if(getCacheManager()!=null){  
            getCacheManager().clearAll();  
            System.out.println("CacheManager was clearAll...");  
        }  
    }   
      
    /** 
     * 把所有cache对象都删除。慎用！ 
     * Removes all caches using removeCache(String) for each cache. 
     */  
    public static void removalAllFromCacheManager(){  
        if(getCacheManager()!=null){  
            getCacheManager().removalAll();  
            System.out.println("CacheManager was removalAll...");  
        }  
    }   
    //不用缓存时，要关闭，不然会占用cpu和内存资源。  
    public static void shutdownCacheManager(){  
        if(getCacheManager()!=null){  
            getCacheManager().shutdown();  
            System.out.println("CacheManager was shutdown...");  
        }  
    }  
      
      
    //打印方法1，为了测试用  
    public static void printCache(Cache cache){  
        System.out.println("缓存状态： "+cache.getStatus().toString());  
        if(cache==null){  
            System.out.println("cache is null,no print info.");  
        }else if(cache.getStatus().toString().equals(Status.STATUS_UNINITIALISED)){  
            System.out.println("缓存状态： 未初始化"+cache.getStatus().toString());  
        }else if(cache.getStatus().toString().equals(Status.STATUS_SHUTDOWN)){  
            System.out.println("缓存状态： 已关闭"+cache.getStatus().toString());  
        }else if(cache.getStatus().toString().equals(Status.STATUS_ALIVE)){  
            if(cache.getKeys().size()==0){  
                System.out.println(cache.getName()+" exits,but no value.");  
            }else{  
                for(int i=0;i<cache.getKeys().size();i++){  
                    Object thekey=cache.getKeys().get(i);  
                    Object thevalue=cache.get(thekey);  
                    System.out.println(cache.getName()+"--"+i+",key:"+thekey.toString()+",value:"+thevalue.toString());  
                }  
            }  
        }  
          
          
    }  
      
    //打印方法2，为了测试用  
    public static void printCacheByName(String cacheName){  
        if(cacheName==null||cacheName.trim().equals("")){  
            System.out.println("cacheName is null,no print info.");  
        }else{  
            if(getCacheManager().getCache(cacheName.trim())!=null){  
                Cache cache=getCacheManager().getCache(cacheName.trim());  
                printCache(cache);  
            }else{  
                System.out.println(cacheName+" --null");  
            }  
        }  
          
          
    }  
      
    public static void main(String[] sdfsf){  
        Cache cache1=EhcacheManager.getCacheByName(EhcacheManager.CACHE_MYCOMMONCACHE);  
        printCache(cache1);  
          
        EhcacheManager.putElementToCache(EhcacheManager.CACHE_MYCOMMONCACHE, "111", "111haah");  
        EhcacheManager.putElementToCache(EhcacheManager.CACHE_MYCOMMONCACHE, "222", "222haah");  
        EhcacheManager.putElementToCache(EhcacheManager.CACHE_MYCOMMONCACHE, "333", "333haah");  
          
        printCache(cache1);  
          
        EhcacheManager.putElementToCache(EhcacheManager.CACHE_MYCOMMONCACHE, "111", "111的新值。");  
          
        System.out.println(EhcacheManager.getElementValueFromCache(EhcacheManager.CACHE_MYCOMMONCACHE, "111"));  
        printCache(cache1);  
          
        clearAllFromCacheManager();  
        printCache(cache1);  
          
        removalAllFromCacheManager();  
        printCache(cache1);  
          
        shutdownCacheManager();  
    }  
    /*打印 
	缓存状态： STATUS_ALIVE 
	添加缓存元素:111成功！ 
	添加缓存元素:222成功！ 
	添加缓存元素:333成功！ 
	缓存状态： STATUS_ALIVE 
	添加缓存元素:111成功！ 
	111的新值。 
	缓存状态： STATUS_ALIVE 
	CacheManager was clearAll... 
	缓存状态： STATUS_ALIVE 
	CacheManager was removalAll... 
	缓存状态： STATUS_SHUTDOWN 
	CacheManager was shutdown... 
 
     */  
    
    ///////////////////上面是对缓存操作的Demo，实际使用逻辑需要改变 /////////////////////////////////////  
      
      
      
      
    ///////////////////以下是web环境下对字典表的缓存操作/////////////////////////////////////  
      
    //得到字典表cache对象（"myCommonCache"）  
    public Cache getMyCommonCache(){  
        Cache cache=null;  
        if(getCacheManager().getCache(EhcacheManager.CACHE_MYCOMMONCACHE)!=null){  
            cache=getCacheManager().getCache(EhcacheManager.CACHE_MYCOMMONCACHE);  
        }else{//如果缓存没有了，这里直接添加，因为在实际中我们不可能告诉应用没有了这个缓存就返回了。  
            //而是应该先去查数据，放入缓存，然后再从缓存中取数据。  
            //当然，如果缓存有数据，那就直接取数据就行了。  
            getCacheManager().addCache(EhcacheManager.CACHE_MYCOMMONCACHE);  
        }  
        return cache;  
    }  
	//从mycommon缓存中获取字典表数据(这里只是简单的把所有值取出来了，实际应用可以更加细化)  
/*    public List getDictionaryListFromMyCommonCache(String codeNo){  
        codeNo=(codeNo==null||codeNo.trim().equals(""))?"":codeNo.trim();  
        List dictionaryList=null;  
        Cache cache=getMyCommonCache();//字典表公用缓存  
        Element element=null;  
        if(cache.get(EhcacheManager.ELEMENT_MYCOMMONCACHE_DICTIONARY)!=null){  
            element=cache.get(EhcacheManager.ELEMENT_MYCOMMONCACHE_DICTIONARY);  
              
            if(element.getObjectValue()==null){//值为空，需要从新添加到缓存  
                List list=this.getEhcacheDao().querydictionaryByCodeNo(codeNo);//查询  
                if(list!=null){  
                    DataObject myCacheObject = new DataObject(list);  
                    element=new Element((Serializable)EhcacheManager.ELEMENT_MYCOMMONCACHE_DICTIONARY,(Serializable)myCacheObject);  
                    //java.io.NotSerializableException:  
                    cache.put(element);  
                    System.out.println("缓存中的字典表元素为空，重新查询添加..");  
                }  
                dictionaryList=list;  
            }else{//有值  
                DataObject myObj=(DataObject)element.getObjectValue();  
                dictionaryList=(List)myObj.getObject();  
                System.out.println("缓存中存在字典表数据，直接获取到。");  
            }  
        }else{//获取Element空，需要从新添加到缓存  
            List list=this.getMyEhcacheDao().querydictionaryByCodeNo("");//查询  
            if(list!=null){  
            	DataObject myCacheObject = new DataObject(list);  
                element=new Element((Serializable)EhcacheManager.ELEMENT_MYCOMMONCACHE_DICTIONARY,(Serializable)myCacheObject);  
                cache.put(element);  
                System.out.println("缓存中的字典表元素为空，重新查询添加......");  
            }  
            dictionaryList=list;  
        }  
        return dictionaryList;  
    }  */
      
    /** 
     * 有时需要强制从数据库查询最新的实际数据，所以需要刷新缓存,保证数据的实时性。 
     * 这里的刷新是指把缓存中所有Element清除 
     */  
    public void refreshDictionaryListInMyCommonCache(){  
        Cache cache=getMyCommonCache();//字典表公用缓存  
        Element element=null;  
/*        if(cache.get(EhcacheManager.ELEMENT_MYCOMMONCACHE_DICTIONARY)!=null){  
            for(int i=0;i<cache.getKeys().size();i++){  
                cache.remove(cache.getKeys().get(i));  
            }  
        }  */
        //如果某些缓存刷新后需要马上填充，就可以像下面直接放数据  
//      if(this.getMyEhcacheDao().querydictionaryByCodeNo("")!=null){  
//          List list=this.getMyEhcacheDao().querydictionaryByCodeNo("");//查询  
//          MyCacheObject myCacheObject = new MyCacheObject(list);  
//          element=new Element((Serializable)MyEhcacheManager.ELEMENT_MYCOMMONCACHE_DICTIONARY,(Serializable)myCacheObject);  
//          cache.put(element);  
//          System.out.println("已经强制刷新字典表缓存。。。");  
//      }  
        System.out.println("已经强制清空字典表所有缓存。。。");  
    }  
    /*CacheManager cacheManager = CacheManager.create();
  //或者
  cacheManager = CacheManager.getInstance();
  //或者
  cacheManager = CacheManager.create("/config/ehcache.xml");
  //或者
  cacheManager = CacheManager.create("http://localhost:8080/test/ehcache.xml");
  cacheManager = CacheManager.newInstance("/config/ehcache.xml");
  //.......

  //获取ehcache配置文件中的一个cache
  Cache sample = cacheManager.getCache("sample");
  //获取页面缓存
  BlockingCache cache = new BlockingCache(cacheManager.getEhcache("SimplePageCachingFilter"));
  //添加数据到缓存中
  Element element = new Element("key", "val");
  sample.put(element);
  //获取缓存中的对象，注意添加到cache中对象要序列化 实现Serializable接口
  Element result = sample.get("key");
  //删除缓存
  sample.remove("key");
  sample.removeAll();

  //获取缓存管理器中的缓存配置名称
  for (String cacheName : cacheManager.getCacheNames()) {
   System.out.println(cacheName);
  }
  //获取所有的缓存对象
  for (Object key : cache.getKeys()) {
   System.out.println(key);
  }

  //得到缓存中的对象数
  cache.getSize();
  //得到缓存对象占用内存的大小
  cache.getMemoryStoreSize();
  //得到缓存读取的命中次数
  cache.getStatistics().getCacheHits();
  //得到缓存读取的错失次数
  cache.getStatistics().getCacheMisses();*/ 
      
      
    ///////////////////////////////////////////////////////////////////////////////  
      
}  