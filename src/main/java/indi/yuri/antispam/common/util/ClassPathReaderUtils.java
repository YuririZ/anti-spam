package indi.yuri.antispam.common.util;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取文件的工具类
 *
 * @author yurizhang
 * @date 2020/9/14 17:04
 */
public class ClassPathReaderUtils {
    /**
     * 默认分隔符
     */
    private static final String DEFAULT_SEPARATOR = "\n";
    /**
     * 默认编码
     */
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    /**
     * 读取路径文件
     *
     * @param path 文件路径
     * @return
     */
    public static List<String> getContentList (String path) {
        return getContentList(path, DEFAULT_CHARSET);
    }

    /**
     * 读取路径文件
     *
     * @param path 文件路径
     * @return
     */
    public static String getContent (String path) {
        return getContent(path, DEFAULT_SEPARATOR, DEFAULT_CHARSET);
    }

    /**
     * 读取路径文件
     *
     * @param path 文件路径
     * @param Separator 分隔符
     * @return
     */
    public static String getContent (String path, String Separator) {
        return getContent(path, Separator, DEFAULT_CHARSET);
    }

    /**
     * 读取路径文件
     *
     * @param path 文件路径
     * @param Separator 分隔符
     * @param code 编码
     * @return
     */
    public static String getContent (String path, String Separator, Charset code) {
        StringBuilder content = new StringBuilder();
        try {
            ClassPathResource resource = new ClassPathResource(path);
            InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String data;
            while ((data = reader.readLine()) != null) {
                content.append(data).append("\n");
            }
            reader.close();
            return content.toString();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 读取路径文件
     *
     * @param path 文件路径
     * @param code 编码
     * @return
     */
    public static List<String> getContentList (String path, Charset code) {
        List<String> contentList = new ArrayList<>();
        try {
            ClassPathResource resource = new ClassPathResource(path);
            InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String data;
            while ((data = reader.readLine()) != null) {
                contentList.add(data);
            }
            reader.close();
            return contentList;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
