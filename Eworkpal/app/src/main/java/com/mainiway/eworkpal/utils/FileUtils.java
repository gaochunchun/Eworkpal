package com.mainiway.eworkpal.utils;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * ===========================================
 * 作    者：gao_chun
 * 版    本：1.0
 * 创建日期：2016-12-20.
 * 描    述：File 操作类
 * ===========================================
 */
public class FileUtils {

    private FileUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }


    /**
     * 复制单个文件
     *
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     * @return boolean
     */
    public static void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { // 文件存在时
                InputStream inStream = new FileInputStream(oldPath); // 读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                //int length;
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; // 字节数 文件大小
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();

        }

    }

    /**
     * 复制整个文件夹内容
     *
     * @param oldPath String 原文件路径 如：c:/fqf
     * @param newPath String 复制后路径 如：f:/fqf/ff
     * @return boolean
     */
    public static void copyFolder(String oldPath, String newPath) {

        try {
            (new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
            File a = new File(oldPath);
            String[] file = a.list();
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }

                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath
                            + "/" + (temp.getName()).toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) {// 如果是子文件夹
                    copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
                }
            }
        } catch (Exception e) {
            System.out.println("复制整个文件夹内容操作出错");
            e.printStackTrace();

        }

    }

    public static long getFileSizes(File f) {// 取得文件大小
        long s = 0;
        try {
            if (f.exists()) {
                FileInputStream fis = null;
                fis = new FileInputStream(f);
                s = fis.available();
            } else {
                f.createNewFile();
                System.out.println("文件不存在");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }


    public static String calculateSize(long file) {
        long l = file;
        float fl = 0;
        String unit = "B";
        if (l < 1024) {
            return l + unit;
        } else if (l < 1024 * 1024) {
            fl = l / 1024f;
            unit = "KB";
        } else if (l < 1024 * 1024 * 1024) {
            fl = l / 1024f / 1024f;
            unit = "MB";
        } else if (l < 1024 * 1024 * 1024 * 1024) {
            fl = l / 1024f / 1024f / 1024f;
            unit = "GB";
        }

        fl = Math.round(fl * 100) / 100f;
        BigDecimal money = new BigDecimal(Float.toString(fl));

        return money + unit;
    }


    public static String calculateSize(File file) {

        return calculateSize(file.length());
    }

    /**
     * 图片内存最小值 5K
     */
    private final static int PIC_FILE_SIZE_MIN = 5;
    /**
     * 图片内存最大值 300K
     */
    private final static int PIC_FILE_SIZE_MAX = 6000;


    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param folderPath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); // 删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            myFilePath.delete(); // 删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 删除指定文件夹下所有文件
    // param path 文件夹完整绝对路径
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);// 再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

    // 返回某个文件夹下的所有文件
    public static List<File> getFiles(String path, String fileSuffix) {
        File file = new File(path);
        File[] files = file.listFiles();
        List<File> fileList = new ArrayList<File>();
        if (files == null) {
            return fileList;
        } else {
            for (int i = 0; i < files.length; i++) {

                if (files[i].isDirectory()) {
                    getFiles(files[i].getAbsolutePath(), fileSuffix);// 采用递归调用

                } else if (files[i].getName().lastIndexOf(fileSuffix) != -1) {
                    fileList.add(files[i]);
                }

            }
        }

        return fileList;
    }

    /**
     * 删除路径文件
     *
     * @param path
     */
    public static void deleteTempFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }


    /**
     * 读取Assets目录下的txt文件
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getFromAssets(Context context, String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static final String FILENAME_USERINFO = "userinfo.txt";          //用户信息 文件名

    /**
     * 将信息写到应用程序文件夹下
     * /data/data//files目录
     *
     * @param context context
     * @param mObject 需要写入的对象
     */
    public static void writeData(Context context, Object mObject) {
        ObjectOutputStream oos = null;
        //getFilesDir()方法用于获取/data/data//files目录，是Context类的方法，获取应用程序的数据文件夹的绝对路径
        String mPath = context.getFilesDir() + File.separator + FILENAME_USERINFO;
        //String mPath = context.getFilesDir() + File.separator + fileName;

        try {
            File file = new File(mPath);
            if (!file.exists()) {
                file.createNewFile();
            }
            oos = new ObjectOutputStream(new FileOutputStream(file));

            oos.writeObject(mObject);

        } catch (Exception e) {
            Log.e("gao_chun", "--------->Error：" + e.getMessage());
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 读取保存的信息
     * /data/data//files目录
     *
     * @param context context
     * @return
     */
    public static Object readData(Context context) {
        Object mObject = new Object();
        ObjectInputStream ois = null;
        String mPath = context.getFilesDir().toString() + File.separator + FILENAME_USERINFO;
        //String mPath = context.getFilesDir().toString() + File.separator + fileName;
        try {
            ois = new ObjectInputStream(new FileInputStream(mPath));
            mObject = (Object) ois.readObject();

        } catch (Exception e) {
            Log.e("gao_chun", "readData--------->Error：" + e.getMessage());
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mObject;
    }


    /**
     * 删除/data/data/files/目录下指定文件
     *
     * @param context  context
     * @param fileName 文件名
     * @return
     */
    public static boolean deleteTheUserInfoFile(Context context, String fileName) {
        //String mPath = context.getFilesDir() + fileName;
        return context.deleteFile(fileName);

    }


    /**
     * 转换文件大小
     *
     * @param fileS
     * @return
     */
    public static String FormetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }


    /**
     * 创建指定目录
     *
     * @return
     */
    public static void initFileDir() {

        if (SDCardUtils.isSDCardEnable()) {

            //创建Photo目录
            File mPhotoDir = new File(SDCardUtils.PHOTO_PATH);
            if (!mPhotoDir.exists()) {
                mPhotoDir.mkdirs();
            }

            //创建File目录
            File mFileDir = new File(SDCardUtils.FILE_PATH);
            if (!mFileDir.exists()) {
                mFileDir.mkdirs();
            }

        } else {
            System.out.println("......SDcard不可用");
        }
    }
}
