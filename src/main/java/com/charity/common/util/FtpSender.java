package com.charity.common.util;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ZhangXu on 2017/9/27.
 */
public class FtpSender {

    private static Logger log = LoggerFactory.getLogger(FtpSender.class);

    private static String host;
    private static String name;
    private static String passwd;
    private static String encoding;
    private static String imgurl;
    private static String prefix;
    private static String finalurl;

    /**
     * 不存在工作池,同步
     */
    public static void SendFile(String fileName, InputStream file){
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(host);
            ftpClient.login(name,passwd);
//            ftpClient.setControlEncoding(encoding);
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.changeWorkingDirectory(prefix);
            ftpClient.storeFile(fileName,file);
            ftpClient.logout();
            ftpClient.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String SendDirectoryFile(String fileName, InputStream file,String... Directorys) throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(host);
        ftpClient.login(name,passwd);
//      ftpClient.setControlEncoding(encoding);
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        ftpClient.changeWorkingDirectory(prefix);
        for(String Directory:Directorys){
            ftpClient.makeDirectory(Directory);
            ftpClient.changeWorkingDirectory(Directory);
        }
        //存储位置
        StringBuilder sb = new StringBuilder(imgurl);
        sb.append(ftpClient.printWorkingDirectory());
        sb.append("/");
        sb.append(fileName);
        ftpClient.storeFile(fileName,file);
        ftpClient.logout();
        ftpClient.disconnect();
        return sb.toString();
    }
    public static void setconf(String host,String name,String passwd,String encoding,String imgurl,String prefix){
        FtpSender.host=host;
        FtpSender.passwd=passwd;
        FtpSender.name=name;
        FtpSender.encoding=encoding;
        FtpSender.imgurl=imgurl;
        FtpSender.prefix=prefix;
        FtpSender.finalurl=imgurl+"/"+prefix;
    }
}
