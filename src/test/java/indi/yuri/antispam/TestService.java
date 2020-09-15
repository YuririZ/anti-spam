package indi.yuri.antispam;

import indi.yuri.antispam.common.pojo.Node;
import indi.yuri.antispam.common.util.ClassPathReaderUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author yurizhang
 * @date 2020/9/11 14:32
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestService {

    @Test
    public void firstTest() {
        System.out.println("yuri");
    }

    @Test
    public void addNodeTest() {
        Node rootNode = new Node();
        rootNode.setNodeName("root");
        rootNode.setEnd(false);
        rootNode.setSubNodeMap(Collections.emptyMap());
        String[] wordList = {"搜狐汽车", "搜狐", "搜狐新闻", "搜狗", "百度", "狐汽"};
        for (String word : wordList) {
            rootNode.addNode(word);
        }

        String input = "搜狐汽车";
        Set<String> nodeSet = Node.matchNodes(input, rootNode);
        System.out.println(nodeSet);
    }

    @Test
    public void readClassPathTest() {
        String path = "text/badWords.txt";
        List<String> result = ClassPathReaderUtils.getContentList(path);
        System.out.println(result);
    }
}
