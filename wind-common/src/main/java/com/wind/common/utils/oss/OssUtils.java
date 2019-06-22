package com.wind.common.utils.oss;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;

import com.wind.common.utils.date.DateUtils;
import com.wind.common.utils.mbplus.IdCreateUtils;
import com.wind.common.utils.properties.ResourceUtils;
import com.wind.common.utils.rsa.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;


//阿里云对象存储服务OSS工具
public class OssUtils {

	/**
	 * 获取图片基本信息 请求URL：@info
	 * 
	 * @infoexif 将图缩略成宽度为100，高度为100，按长边优先 ：@100h_100w_0e
	 *           将图缩略成宽度为100，高度为100，按短边优先： @100h_100w_1e 放大两倍：@200p 缩小一半：@50p
	 */

	private static Logger logger = Logger.getLogger(OssUtils.class);
	// 演示，创建Bucket的时候，endpoint不能带上.
	// 图片上传和简单的图片访问也可以用这个。
	public static String endpoint;
	// 图片处理，需要用单独的地址。访问、裁剪、缩放、效果、水印、格式转换等服务。
	public static String endpointImg;
	public static String accessKeyId;
	public static String accessKeySecret;
	public static String bucketName;
	public static String imgUrl;
	// 单例，只需要建立一次链接
	private static OSSClient client = null;

	public static Map<String, String> ossProperties =  ResourceUtils.getResource("oss/oss").getMap();

	static {
		endpoint = ossProperties.get("oss.endpoint");
		endpointImg = ossProperties.get("oss.endpointImg");
		accessKeyId = ossProperties.get("oss.accessKeyId");
		accessKeySecret = ossProperties.get("oss.accessKeySecret");
		bucketName = ossProperties.get("oss.bucketName");
		imgUrl = ossProperties.get("oss.imgurl");
		// 创建OssClient客户端
		client = getOssClient();
	}

	// 配置参数
	static ClientConfiguration config() {
		ClientConfiguration conf = new ClientConfiguration();
		conf.setMaxConnections(Integer.valueOf(ossProperties.get("oss.maxconnections")));
		conf.setConnectionTimeout(Integer.valueOf(ossProperties.get("oss.connectiontimeout")));
		conf.setMaxErrorRetry(Integer.valueOf(ossProperties.get("oss.maxerrorretry")));
		conf.setSocketTimeout(Integer.valueOf(ossProperties.get("oss.sockettimeout")));
		return conf;
	}

	// 客户端
	public static OSSClient getOssClient() {
		if (client == null) {
			ClientConfiguration conf = config();
			client = new OSSClient(endpoint, accessKeyId, accessKeySecret, conf);
			makeBucket(client, bucketName);
		}
		return client;
	}

	// 创建Bucket
	public static void makeBucket(String bucketName) {
		makeBucket(client, bucketName);
	}

	// 创建Bucket
	public static void makeBucket(OSSClient client, String bucketName) {
		boolean exist = client.doesBucketExist(bucketName);
		if (exist) {
			return;
		}
		client.createBucket(bucketName);
	}

	// 上传一个文件，InputStream
	public static void uploadFile(InputStream is, String key) throws IOException {
		OSSClient client = getOssClient();
		// 创建上传Object的Metadata
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(is.available());
		//objectMetadata.setCacheControl("no-cache");
		objectMetadata.setHeader("Pragma", "no-cache");
		objectMetadata.setContentEncoding("utf-8");
		PutObjectRequest putObjectRequest = new PutObjectRequest(OssUtils.bucketName, key, is, objectMetadata);
		client.putObject(putObjectRequest);
		client.shutdown();
	}

	// 上传一个文件，File
	public static void uploadFile(File file, String key) {
		OSSClient client = getOssClient();
		PutObjectRequest putObjectRequest = new PutObjectRequest(OssUtils.bucketName, key, file);
		client.putObject(putObjectRequest);
	}

	// 下载一个文件到本地
	public static OSSObject downloadFile(String key) {
		GetObjectRequest getObjectRequest = new GetObjectRequest(OssUtils.bucketName, key);
		OSSObject object = client.getObject(getObjectRequest);
		return object;
	}

	/**
	 * 上传某个文件到某个目录，key是自动生成的
	 * 
	 * @param file
	 * @param dir
	 *            设置上传的目录
	 * @return
	 * @throws IOException
	 */
	public static String uploadFile(MultipartFile file, String dir) throws IOException {
		// String urlStr = endpointImg.replace("http://", "http://" + bucketName
		// + ".");
		String urlStr = imgUrl;
		String key = null;
		if (null != file && !file.isEmpty() && file.getSize() > 0) {
			String fileName = IdCreateUtils.createId() + "."
					+ StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
			String ymd =  DateUtils.dateToString(new Date(), DateUtils.yyyyMMdd_HHmmss);
			key = dir + "/" + ymd + "/" + fileName;
			
			/*ServletContext  servletcontext = SpringAppContextUtil.getServletContext();
			String realPath = servletcontext.getRealPath("/WEB-INF/water.png");
	        File waterImg = new File(realPath);  
	        InputStream bufferedImage  = FileUtils.addWaterMark(file.getInputStream(), waterImg,-1, -1, 0.3f);
			OssUtils.uploadFile(bufferedImage, key);*/
			OssUtils.uploadFile(file.getInputStream(), key);
			return urlStr + "/" + key;
		}
		return urlStr + "/" + key;
	}

	/**
	 * 上传文本以base64传输
	 * @param imgStr
	 * @param fileName
	 * @param dir
	 * @return
	 * @throws IOException
	 */
	public static String uploadBase64Image(String imgStr,String fileName, String dir) throws IOException {
		// String urlStr = endpointImg.replace("http://", "http://" + bucketName
		// + ".");
		String urlStr = imgUrl;
		String key = null;
		if (null != imgStr && !imgStr.isEmpty()) {
			 fileName = IdCreateUtils.createId() +fileName;
			String ymd =  DateUtils.dateToString(new Date(), DateUtils.yyyyMMdd_HHmmss);
			key = dir + "/" + ymd + "/" + fileName;
			byte[] imageByte = Base64.decode(imgStr);
			OssUtils.uploadFile(new ByteArrayInputStream(imageByte), key);
			// OssUtils.uploadFile(file, key);
			return urlStr + "/" + key;
		}
		return urlStr + "/" + key;
	}
	
	/**
	 * 上传Base64位数组
	 * @throws IOException 
	 * */
	public static String loadBase64Img(String base64Img) throws IOException{
		base64Img=base64Img.substring(base64Img.indexOf(",")+1);
		// 获得一个图片文件流，我这里是从flex中传过来的
		byte[] result = Base64.decode(base64Img);
		for (int i = 0; i < result.length; ++i) {
			if (result[i] < 0) {// 调整异常数据
			result[i] += 256;
			}
		}
		//ServletContext  servletcontext = SpringAppContextUtil.getServletContext();
		//String realPath = servletcontext.getRealPath("/WEB-INF/water.png");
        //File waterImg = new File(realPath);  
		//InputStream bufferedImage  = FileUtils.addWaterMark(new ByteArrayInputStream(result), waterImg,-1, -1, 0.0f);
		String ymd =  DateUtils.dateToString(new Date(), DateUtils.yyyyMMdd_HHmmss);
		String key = "tm" + "/" + ymd + "/" + "baseImg.png";
		//OssUtils.uploadFile(bufferedImage, key);
		OssUtils.uploadFile(new ByteArrayInputStream(result), key);
		return imgUrl + "/" + key;
	}
	
	// 删除某个文件
	public static void delete(String key) {
		try {
			client.deleteObject(OssUtils.bucketName, key);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 创建目录，不能以斜杠“/”开头
	public static void makeDir(String keySuffixWithSlash) {

		if (StringUtils.isEmpty(keySuffixWithSlash)) {
			return;
		}
		if (!keySuffixWithSlash.endsWith("/")) {
			keySuffixWithSlash += "/";
		}
		client.putObject(bucketName, keySuffixWithSlash, new ByteArrayInputStream(new byte[0]));
	}

}